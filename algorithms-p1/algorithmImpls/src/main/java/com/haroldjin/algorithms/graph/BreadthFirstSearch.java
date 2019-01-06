package com.haroldjin.algorithms.graph;

import com.haroldjin.algorithms.model.InvalidArgumentsException;

import java.util.*;
import java.util.List;

public class BreadthFirstSearch<E> implements GraphSearch<E> {
    private Map<E, LinkedHashSet<E>> nodeLinks = new HashMap();

    @Override
    public void addEdge(E left, E right) {
        Set links = nodeLinks.get(left);
        if (links == null) {
            nodeLinks.put(left, new LinkedHashSet(Arrays.asList(right)));
        } else {
            links.add(right);
        }
    }

    @Override
    public List<E> doSearch(E start) throws InvalidArgumentsException {
        // if the start node is not in the nodeLinks then we should let the client know the exception
        if (nodeLinks.get(start) == null) {
            throw new InvalidArgumentsException("Invalid start node");
        } else {
            // Avoid performance hit resizing buckets by initializing with max node size + 1
            ArrayDeque<E> arrayDeque = new ArrayDeque<>();
            arrayDeque.addFirst(start);
            Map<E, Boolean> visitedMap = new LinkedHashMap(nodeLinks.size()+1);
            while(arrayDeque.size() != 0) {
                doSearch(visitedMap, arrayDeque);
            }
            return new ArrayList(visitedMap.keySet());
        }
    }

    private void doSearch(Map<E, Boolean> visitedMap, ArrayDeque<E> arrayDeque) {
        // Get the element from queue in depth n, if queue empty, we're done
        E element = arrayDeque.pollFirst();
        if (element == null) return;

        // if node is already visited we return
        if (visitedMap.get(element) != null) return;
        else visitedMap.put(element, true);

        // mark the node as visited
        LinkedHashSet<E> links = nodeLinks.get(element);
        for (E link : links){
            if (visitedMap.get(link) == null){
                arrayDeque.addLast(link);
            }
        }

    }
}
