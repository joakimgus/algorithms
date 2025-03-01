package org.pg4200.ex01;

public class ArrayUtilsImp implements ArrayUtils {

    @Override
    public int min(int[] array) throws IllegalArgumentException {
        isNullOrEmpty(array);

        int min = array[0];

        for(int i = 0; i < array.length; i++) {
            int current = array[i];
            if(current < min) {
                min = current;
            }
        }
        return min;
    }

    @Override
    public int max(int[] array) throws IllegalArgumentException {
        isNullOrEmpty(array);

        int max = array[0];

        for(int i = 0; i < array.length; i++) {
            int current = array[i];
            if(current > max) {
                max = current;
            }
        }
        return max;
    }

    @Override
    public double mean(int[] array) throws IllegalArgumentException {
        isNullOrEmpty(array);

        double sum = array[0];
        for(int i = 1; i < array.length; i++) {
            sum += array[i];
        }

        double mean = sum / array.length;

        return mean;
    }

    private void isNullOrEmpty(int[] array) {
        if(array == null || array.length == 0) {
            throw new IllegalArgumentException("Array is null or empty");
        }
    }
}
