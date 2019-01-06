package com.haroldjin.algorithms.graph;

import com.haroldjin.algorithms.model.InvalidArgumentsException;

import java.util.List;

public interface GraphSearch<E> {
    void addEdge(E left, E right);

    List<E> doSearch(E start) throws InvalidArgumentsException;
}
