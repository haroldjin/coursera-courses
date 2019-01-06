package com.haroldjin.algorithms.list;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class ArrayTest {
    @Test
    public void minElementArray(){
        Integer[] intElements = {1, 5, 3, 5 , 6};
        Integer min = ArrayProblems.<Integer>minElementArray(intElements);
        Assert.assertEquals(1, min.intValue());
    }


    @Test
    public void firstNonRepeatElementInArray(){
        Integer[] intElements = {1, 1, 3, 3 , 6, 7 };
        Integer firstNonRepeat = ArrayProblems.<Integer>firstNonRepeatElement(intElements);
        Assert.assertEquals(6, firstNonRepeat.intValue());
    }
    @Test
    public void mergeTwoSortedArrays(){
        Integer[] firstSortedArray = {1, 1, 3, 5 , 6, 7 };
        Integer[] secondSortedArray = {0, 2, 2, 4, 8 , 9, 10 };
        Integer[] resultArray = new Integer[firstSortedArray.length + secondSortedArray.length];
        Integer[] expectedResult = {0,1,1,2,2,3,4,5,6,7,8,9,10};
        ArrayProblems.mergeTwoSortedArrays(firstSortedArray, secondSortedArray, resultArray);
        for (Integer i : resultArray){
            System.out.println(i);
        }
        Assert.assertEquals(expectedResult, resultArray);
    }
}
