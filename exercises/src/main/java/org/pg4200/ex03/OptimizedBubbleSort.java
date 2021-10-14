package org.pg4200.ex03;

import java.util.Comparator;

public class OptimizedBubbleSort {

    public <T> int sort(T[] array, Comparator<T> comparator, boolean optimized) {

        if (array == null) {
            return 0;
        }

        int timesCalled = 0;

        boolean ordered = false;

        int lastSwap = array.length - 1;

        while(!ordered) {

            ordered = true;

            int lastUnsortedElement = array.length - 1;

            if(optimized){
                lastUnsortedElement = lastSwap;
            }

            for (int i = 0; i < lastUnsortedElement; i++) {
                int j = i + 1;

                timesCalled++;

                if (comparator.compare(array[i], array[j]) > 0){
                    T tmp = array[i];
                    array[i] = array[j];
                    array[j] = tmp;

                    ordered = false;
                    lastSwap = i;
                }
            }
        }

        return timesCalled;
    }
}
