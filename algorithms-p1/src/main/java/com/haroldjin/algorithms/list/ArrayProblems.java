package com.haroldjin.algorithms.list;

public class ArrayProblems {
    public static <T extends Comparable> T minElementArray(T[] intElements) {
        if (intElements.length == 0)
            return null;
        T min = intElements[0];
        for (int i = 1; i < intElements.length; i++) {
            if (min.compareTo(intElements[i]) > 0) {
                min = intElements[i];
            }
        }
        return min;
    }

    public static <T extends Comparable> T firstNonRepeatElement(T[] intElements) {
        if (intElements.length == 0)
            return null;
        T element = intElements[0];
        boolean isRepeat = false;

        for (int i = 1; i < intElements.length; i++) {
            if (element.compareTo(intElements[i]) == 0) {
                isRepeat = true;
            } else {
                if (isRepeat == false) {
                    return element;
                }
                isRepeat = false;
            }

            element = intElements[i];
        }
        return null;
    }


    public static <T extends Comparable> void mergeTwoSortedArrays(T[] firstSortedArray, T[] secondSortedArray, T[] newSortedArray) {
        if (firstSortedArray.length == 0)
            return;
        else if (secondSortedArray.length == 0)
            return;


//        T[] newSortedArray = (T[]) new Object[firstSortedArray.length  + secondSortedArray.length];

        int secondArrIndex = 0;
        int indexSorted = 0;

        for (int i = 0; i < firstSortedArray.length; i++) {
            if (secondArrIndex < secondSortedArray.length) {
                while (secondArrIndex < secondSortedArray.length && firstSortedArray[i].compareTo(secondSortedArray[secondArrIndex]) >= 0) {
                    newSortedArray[indexSorted++] = secondSortedArray[secondArrIndex++];
                }
                newSortedArray[indexSorted++] = firstSortedArray[i];
            }

            // second array all sorted in new array finish first array
            if (secondSortedArray.length == secondArrIndex + 1)
                for (; i < firstSortedArray.length; i++)
                    newSortedArray[indexSorted++] = firstSortedArray[i];
        }

        if (secondSortedArray.length > secondArrIndex + 1)
            for (; secondArrIndex < secondSortedArray.length; secondArrIndex++)
                newSortedArray[indexSorted++] = secondSortedArray[secondArrIndex];

        return;
    }
}
