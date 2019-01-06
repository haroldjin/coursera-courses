package com.haroldjin.java.lang.fundamental;

import java.util.*;
import java.util.stream.Collectors;

public class MapSortedExample {
    public static <K, V> String toString(Map<K, V> map) {
        StringBuilder stringBuilder = new StringBuilder();
        map.forEach((k, v) -> {
            stringBuilder.append(k + " " + v + "\n");
        });
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        return stringBuilder.toString();
    }

    public static <K extends Comparable, V> Map<K, V> sortByKeyWithTreeMap(Map<K, V> unsortedMap) {
        Map<K, V> treeMap = new TreeMap<>();
        treeMap.putAll(unsortedMap);
        return treeMap;
    }


    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> unsortedMap) {
        List<Map.Entry<K, V>> list = new ArrayList<>(unsortedMap.entrySet());
        list.sort(Map.Entry.comparingByValue());

        Map<K, V> result = new LinkedHashMap<>();
        for (Map.Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }

    public static <K, V extends Comparable<? super V>> Map<K, V> sortReversedOrderByValueJava8(Map<K, V> unsortedMap) {
        Map<K, V> sorted = unsortedMap.entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (v, v2) -> v, LinkedHashMap::new));
        return sorted;
    }

    public static <K, V extends Comparable<? super V>> Map<K, V> sortReversedOrderByValueJava8V2(Map<K, V> unsortedMap) {
        Map<K, V> sorted = new LinkedHashMap<>();
        unsortedMap.entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .forEach((e) -> sorted.put(e.getKey(), e.getValue()));
        return sorted;
    }

}
