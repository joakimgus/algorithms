package org.pg4200.ex04;

public class FibonacciImplMemoized implements Fibonacci{

    private int [] x = new int[100];

    @Override
    public int compute(int n) throws IllegalArgumentException {

        if(n < 0){
            throw new IllegalArgumentException();
        }

        int result = 0;

        if(n == 0){
            result = 0;
        } else if(n == 1) {
            result = 1;
        } else if(n < x.length && x[n] != 0){
            result = x[n];
        } else if(n < x.length){
            result = compute(n-2) + compute(n-1);
            x[n] = result;
        }

        return result;
    }
}
