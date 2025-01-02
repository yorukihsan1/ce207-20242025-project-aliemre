package com.ucoruh.publictransportationscheduler.datastructures;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit tests for the GraphSearch class.
 */
public class GraphSearchTest {

    private GraphSearch graphSearch;

    /**
     * @brief Sets up the GraphSearch instance before each test.
     */
    @Before
    public void setUp() {
        graphSearch = new GraphSearch();
    }

    /**
     * @brief Tests adding a single node to the graph.
     */
    @Test
    public void testAddNode() {
        graphSearch.addNode("A");
        assertTrue(graphSearch.getNodes().contains("A"), "Node A should be added to the graph.");
    }

    /**
     * @brief Tests adding an edge between two nodes in the graph.
     */
    @Test
    public void testAddEdge() {
        graphSearch.addEdge("A", "B");
        assertTrue(graphSearch.getNodes().containsAll(Arrays.asList("A", "B")),
                "Nodes A and B should be added to the graph.");
        List<String[]> edges = graphSearch.getEdges();
        assertTrue(edges.stream().anyMatch(e -> Arrays.equals(e, new String[]{"A", "B"})),
                "Edge A-B should exist in the graph.");
        assertTrue(edges.stream().anyMatch(e -> Arrays.equals(e, new String[]{"B", "A"})),
                "Edge B-A should exist in the graph (bidirectional).");
    }

    /**
     * @brief Tests Breadth-First Search (BFS) with a valid start node.
     */
    @Test
    public void testBfsWithValidNode() {
        graphSearch.addEdge("A", "B");
        graphSearch.addEdge("A", "C");
        graphSearch.addEdge("B", "D");

        TestOutputStream output = new TestOutputStream();
        System.setOut(output.getPrintStream());
        graphSearch.bfs("A");

        String bfsOutput = output.getOutput();
        assertTrue(bfsOutput.contains("Visited: A"), "BFS should visit node A.");
        assertTrue(bfsOutput.contains("Visited: B"), "BFS should visit node B.");
        assertTrue(bfsOutput.contains("Visited: C"), "BFS should visit node C.");
        assertTrue(bfsOutput.contains("Visited: D"), "BFS should visit node D.");
    }

    /**
     * @brief Tests Breadth-First Search (BFS) with an invalid start node.
     */
    @Test
    public void testBfsWithInvalidNode() {
        TestOutputStream output = new TestOutputStream();
        System.setOut(output.getPrintStream());
        graphSearch.bfs("Z");
        assertTrue(output.getOutput().contains("Start node not found."),
                "BFS should handle invalid start nodes gracefully.");
    }

    /**
     * @brief Tests Depth-First Search (DFS) with a valid start node.
     */
    @Test
    public void testDfsWithValidNode() {
        graphSearch.addEdge("A", "B");
        graphSearch.addEdge("A", "C");
        graphSearch.addEdge("B", "D");

        TestOutputStream output = new TestOutputStream();
        System.setOut(output.getPrintStream());
        graphSearch.dfs("A");

        String dfsOutput = output.getOutput();
        assertTrue(dfsOutput.contains("Visited: A"), "DFS should visit node A.");
        assertTrue(dfsOutput.contains("Visited: B"), "DFS should visit node B.");
        assertTrue(dfsOutput.contains("Visited: C"), "DFS should visit node C.");
        assertTrue(dfsOutput.contains("Visited: D"), "DFS should visit node D.");
    }

    /**
     * @brief Tests Depth-First Search (DFS) with an invalid start node.
     */
    @Test
    public void testDfsWithInvalidNode() {
        TestOutputStream output = new TestOutputStream();
        System.setOut(output.getPrintStream());
        graphSearch.dfs("Z");
        assertTrue(output.getOutput().contains("Start node not found."),
                "DFS should handle invalid start nodes gracefully.");
    }

    /**
     * @brief Tests retrieving the nodes in the graph.
     */
    @Test
    public void testGetNodes() {
        graphSearch.addNode("A");
        graphSearch.addNode("B");
        List<String> nodes = graphSearch.getNodes();
        assertEquals(2, nodes.size(), "Graph should contain exactly 2 nodes.");
        assertTrue(nodes.contains("A") && nodes.contains("B"),
                "Nodes A and B should exist in the graph.");
    }

    /**
     * @brief Tests setting the nodes in the graph.
     */
    @Test
    public void testSetNodes() {
        graphSearch.setNodes(Arrays.asList("A", "B", "C"));
        List<String> nodes = graphSearch.getNodes();
        assertEquals(3, nodes.size(), "Graph should contain exactly 3 nodes.");
        assertTrue(nodes.containsAll(Arrays.asList("A", "B", "C")),
                "Nodes A, B, and C should exist in the graph.");
    }

    /**
     * @brief Tests retrieving the edges in the graph.
     */
    @Test
    public void testGetEdges() {
        graphSearch.addEdge("A", "B");
        graphSearch.addEdge("A", "C");
        List<String[]> edges = graphSearch.getEdges();
        assertEquals(4, edges.size(), "Graph should contain exactly 4 edges (2 bidirectional).");
    }

    /**
     * @brief Tests setting the edges in the graph.
     */
    @Test
    public void testSetEdges() {
        List<String[]> edges = Arrays.asList(new String[]{"A", "B"}, new String[]{"A", "C"});
        graphSearch.setEdges(edges);
        assertEquals(3, graphSearch.getNodes().size(), "Graph should contain exactly 3 nodes.");
        assertEquals(4, graphSearch.getEdges().size(), "Graph should contain exactly 4 edges (2 bidirectional).");
    }
}
