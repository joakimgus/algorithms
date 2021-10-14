package org.pg4200.ex02;

import org.pg4200.les02.queue.MyQueue;

public class MyRingArrayQueue<T> implements MyQueue<T> {
    protected Object[] data;
    private int head = -1;
    private int tail = -1;

    public MyRingArrayQueue(){this(10);}

    public MyRingArrayQueue(int capacity){ data = new Object[capacity];}

    @Override
    public void enqueue(T value) {
        if(isEmpty()){
            head = 0;
            tail = 0;
        } else if(tail < data.length - 1){
            //there is space
            tail++;
        } else {
            /*
                we run out of space, where "tail"
                points to the last element in the array.
                What to do?
                Here we consider 2 options, based on the
                position of "head".
             */
            if(size() < data.length / 5){
                /*
                    not so many elements in the array, so
                    just shift them to the left, in a way
                    to align the "head" at the beginning of
                    the array.

                    Note: the choice of "5" is arbitrary...
                 */
                int  size = size();
                for(int i=0; i<size; i++){
                    data[i] = data[i + head];
                }
                head = 0;
                tail = size;
            } else {
                //too many elements... let's just create a new array with double size
                Object[] tmp = new Object[data.length * 2];

                int  size = size();
                for(int i=0; i<size; i++){
                    tmp[i] = data[i + head];
                }
                head = 0;
                tail = size;
                data = tmp;
            }
        }

        data[tail] = value;

    }

    @Override
    public T dequeue() {
        return null;
    }

    @Override
    public T peek() {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }
}
