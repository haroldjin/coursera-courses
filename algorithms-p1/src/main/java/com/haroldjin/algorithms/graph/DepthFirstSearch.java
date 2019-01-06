package com.haroldjin.algorithms.graph;

import com.haroldjin.algorithms.model.InvalidArgumentsException;

import java.util.*;

public class DepthFirstSearch<E> implements GraphSearch<E> {
    private Map<E, ArrayList<E>> nodeLinks = new HashMap();

    public void addEdge(E left, E right) {
        // if the node is not initialized with create a new node with associated links,
        // else just addFirst to the links
        ArrayList links = nodeLinks.get(left);
        if (links == null) {
            nodeLinks.put(left, new ArrayList(Arrays.asList(right)));
        } else {
            links.add(right);
        }
    }

    public List<E> doSearch(E start) throws InvalidArgumentsException {
        // if the start node is not in the nodeLinks then we should let the client know the exception
        if (nodeLinks.get(start) == null) {
            throw new InvalidArgumentsException("Invalid start node");
        } else {
            // Avoid performance hit resizing buckets by initializing with max node size + 1
            Map<E, Boolean> visitedMap = new LinkedHashMap(nodeLinks.size() + 1);
            doSearchRecursive(start, visitedMap);
            return new ArrayList(visitedMap.keySet());
        }
    }

    // Depth First Search takes a node, and the map for visited nodes
    private void doSearchRecursive(E start, Map<E, Boolean> visitedMap) {
        // if node doesnt' have links addFirst that and return, else if visited then return
        ArrayList<E> links = nodeLinks.get(start);
        if (links == null) {
            visitedMap.put(start, true);
        }
        if (visitedMap.get(start) != null) {
            return;
        }

        // put the unvisited node into the visited map
        visitedMap.put(start, true);
        for (E element : links) {
            doSearchRecursive(element, visitedMap);
        }
    }


    public List<E> doSearchNonRecursive(E start) throws InvalidArgumentsException {
        // if the start node is not in the nodeLinks then we should let the client know the exception
        if (nodeLinks.get(start) == null) {
            throw new InvalidArgumentsException("Invalid start node");
        } else {
            // Avoid performance hit resizing buckets by initializing with max node size + 1
            Map<E, Boolean> visitedMap = new LinkedHashMap(nodeLinks.size() + 1);
            Stack<E> depthStack = new Stack<>();
            depthStack.push(start);
            while (depthStack.size() != 0) {
                doSearchNonRecursive(depthStack, visitedMap);
            }

            return new ArrayList(visitedMap.keySet());
        }
    }

    private void doSearchNonRecursive(Stack<E> depthStack, Map<E, Boolean> visitedMap) {
        // pop stack and addFirst to visited map
        E node = depthStack.pop();
        visitedMap.put(node, true);

        // iterate over edges, push next children into the stack if there is an edge and remove that from the links,
        // else the current node is the end with no children
        ArrayList<E> links = nodeLinks.get(node);
        if (links == null || links.iterator().hasNext() != true) {
            return;
        } else {
            E nodeLink = links.iterator().next();
            depthStack.push(nodeLink);
            visitedMap.put(node, true);
            links.remove(nodeLink);
        }
    }
}
