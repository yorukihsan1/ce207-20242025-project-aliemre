package com.ucoruh.publictransportationscheduler;

import com.ucoruh.publictransportationscheduler.datastructures.GraphSearch;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class RoutePlanningTest {

  private RoutePlanning routePlanning;
  private GraphSearch mockGraphSearch;
  private ByteArrayOutputStream outputStream;

  @BeforeEach
  public void setUp() {
    routePlanning = new RoutePlanning();
    mockGraphSearch = mock(GraphSearch.class);
    routePlanning.graphSearch = mockGraphSearch; // Replace the real GraphSearch with a mock
    outputStream = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outputStream));
  }

  @Test
  public void testAddStartingNode() {
    String simulatedInput = "Node1\n";
    routePlanning.addNode(new java.util.Scanner(new ByteArrayInputStream(simulatedInput.getBytes())), "Starting");
    verify(mockGraphSearch, times(1)).addNode("Node1");
  }

  @Test
  public void testAddEndingNodeWithConnection() {
    String simulatedInput = "Node2\nNode1\n";
    routePlanning.addNode(new java.util.Scanner(new ByteArrayInputStream(simulatedInput.getBytes())), "Ending");
    verify(mockGraphSearch, times(1)).addNode("Node2");
    verify(mockGraphSearch, times(1)).addEdge("Node1", "Node2");
  }

  @Test
  public void testViewSuggestedRoutes() {
    String simulatedInput = "Node1\n";
    routePlanning.viewSuggestedRoutes(new java.util.Scanner(new ByteArrayInputStream(simulatedInput.getBytes())));
    verify(mockGraphSearch, times(1)).bfs("Node1");
  }

  @Test
  public void testExploreAlternativeRoutes() {
    String simulatedInput = "Node1\n";
    routePlanning.exploreAlternativeRoutes(new java.util.Scanner(new ByteArrayInputStream(simulatedInput.getBytes())));
    verify(mockGraphSearch, times(1)).dfs("Node1");
  }

  @Test
  public void testSaveGraph() {
    routePlanning.saveGraph();
    verify(mockGraphSearch, times(1)).getNodes();
    verify(mockGraphSearch, times(1)).getEdges();
  }

  @Test
  public void testLoadGraphWhenFilesExist() throws Exception {
    java.io.File nodesFile = new java.io.File(RoutePlanning.NODES_FILE);
    java.io.File edgesFile = new java.io.File(RoutePlanning.EDGES_FILE);

    nodesFile.createNewFile();
    edgesFile.createNewFile();

    ObjectOutputStream nodesOutput = new ObjectOutputStream(new java.io.FileOutputStream(nodesFile));
    ObjectOutputStream edgesOutput = new ObjectOutputStream(new java.io.FileOutputStream(edgesFile));

    nodesOutput.writeObject(List.of("Node1", "Node2"));
    edgesOutput.writeObject(List.of(new String[]{"Node1", "Node2"}));
    nodesOutput.close();
    edgesOutput.close();

    routePlanning.loadGraph();

    verify(mockGraphSearch, times(1)).setNodes(any());
    verify(mockGraphSearch, times(1)).setEdges(any());

    nodesFile.delete();
    edgesFile.delete();
  }

  @Test
  public void testLoadGraphWhenFilesDoNotExist() {
    routePlanning.loadGraph();
    verify(mockGraphSearch, times(0)).setNodes(any());
    verify(mockGraphSearch, times(0)).setEdges(any());
  }

  @Test
  public void testDisplayMenuWithInvalidChoice() {
    String simulatedInput = "0\n5\n";
    routePlanning.display(new java.util.Scanner(new ByteArrayInputStream(simulatedInput.getBytes())));
    // Verify the system output contains the correct message for invalid choice
    String output = outputStream.toString();
    assertTrue(output.contains("Invalid choice. Please try again."));
  }

  @Test
  public void testDisplayMenuWithValidChoices() {
    String simulatedInput = "1\nNode1\n2\nNode2\nNode1\n3\nNode1\n4\nNode1\n5\n";
    routePlanning.display(new java.util.Scanner(new ByteArrayInputStream(simulatedInput.getBytes())));
    // Verify all actions were invoked
    verify(mockGraphSearch, times(1)).addNode("Node1");
    verify(mockGraphSearch, times(1)).addNode("Node2");
    verify(mockGraphSearch, times(1)).addEdge("Node1", "Node2");
    verify(mockGraphSearch, times(1)).bfs("Node1");
    verify(mockGraphSearch, times(1)).dfs("Node1");
  }
}
