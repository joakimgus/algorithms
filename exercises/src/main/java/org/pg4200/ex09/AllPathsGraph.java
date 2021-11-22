package org.pg4200.ex09;

import org.pg4200.les09.UndirectedGraph;

import java.util.*;
import java.util.stream.Collectors;

public class AllPathsGraph<V> extends UndirectedGraph<V> {

    public List<List<V>> findAllPaths(V start, V end) {

        Stack<V> visited = new Stack<V>();
        Queue<V> toVisit = new LinkedList<V>();
        List<List<V>> paths = new ArrayList<>();

        visited.push(start);

        findAllPathsBF(visited, paths, toVisit, start, end);

        return null;
    }

    private void findAllPathsBF(Stack<V> visited, List<List<V>> paths, Queue<V> toVisit, V currentVertex, V end){

        visited.push(currentVertex);

        if(currentVertex == end){
            List<V> path = visited.stream().collect(Collectors.toList());
            paths.add(path);
        }

        Set<V> connected = graph.get(currentVertex);

        for(V vertex : connected){
            if((!visited.contains(vertex)) && (!toVisit.contains(vertex))){
                toVisit.add(vertex);
            }
        }

        while(!toVisit.isEmpty()){
            V nextVertex = toVisit.poll();
            findAllPathsBF(visited, paths, toVisit, nextVertex, end);
        }
    }
}
