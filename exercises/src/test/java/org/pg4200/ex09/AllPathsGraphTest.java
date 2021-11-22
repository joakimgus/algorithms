package org.pg4200.ex09;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

public class AllPathsGraphTest {

    @Test
    public void testPaths(){
        AllPathsGraph<String> graph = new AllPathsGraph<String>();

        graph.addEdge("0","X");
        graph.addEdge("X","1");
        graph.addEdge("X","Y");
        graph.addEdge("1","2");
        graph.addEdge("2","Y");
        graph.addEdge("1","3");
        graph.addEdge("3","4");
        graph.addEdge("3","5");
        graph.addEdge("4","5");

        List allPaths = graph.findAllPaths("X", "5");

        assertTrue(allPaths.size() > 0);
    }
}
