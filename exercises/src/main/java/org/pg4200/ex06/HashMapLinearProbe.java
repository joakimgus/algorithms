package org.pg4200.ex06;

import org.pg4200.les06.hash.MyHashMap;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HashMapLinearProbe<K, V> implements MyHashMap<K, V> {


    private final int M = 997;

    private class Entry {
        K key;
        V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private Entry[] data = (Entry[]) Array.newInstance(Entry.class, M);

    private int size = 0;


    private int findKey(int i, K key) {

        int k = i;

        /* Checking if key is present in array
        * searching from (positive hashed value % M(997)) to end of array
        */
        while (isPresentButNotMatching(k, key) && k < data.length) {
            k++;
        }

        /*
        * Goes into this if isPresentButNotMatching did not reach end of array
        * ie, it either found an empty position or a position with same key
        */
        if (k < data.length) {
            // if it found an empty position, ie not a matching key, return -1
            if(data[k] == null){
                return -1;
            }
            // if it found a matching key, return the position in the array
            assert key.equals(data[k].key);
            return k;
        }

        /*
        * isPresentButNotMatching did not find empty spot or matching key
        *  ie, it hit end of array.
        * set k = 0 to start searching from start of array.
        */
        k = 0;

        /*
        * Searching from start of array to i,
        *  ie where you started the last search
        */
        while (isPresentButNotMatching(k, key) && k < i) {
            k++;
        }

        /*
        * returns the index position of matching key if found,
        * else returns -1 if there is an empty spot or array is full
        */
        if (k < i && data[k] != null) {
            assert key.equals(data[k].key);
            return k;
        } else {
            return -1;
        }
    }



    private int findEmpty(int i) {

        int k = i;

        /*
        * runs until end of array,
        * found empty spot in array
        * or found null in key property for index in array
        */
        while (!isMissing(k) && k < data.length) {
            k++;
        }

        /*
        * if isMissing found an available spot in array, return the index of it
        */
        if (k < data.length) {
            assert isMissing(k);
            return k;
        }

        /*
        * if isMissing ran out of bounds, set k = 0 and start from beginning of array
        */
        k = 0;
        /*
        * same thing as previously, start searching for empty spot
        * this time searching from start to the spot it started searching from last time
        */
        while (!isMissing(k) && k < i) {
            k++;
        }

        /*
        * if k was found before running out of spots to search, return the spot
        * else return -1, meaning map is full
        */
        if (k < i) {
            return k;
        } else {
            return -1;
        }
    }


    @Override
    public void put(K key, V value) {

        // returns i as (postive hashed value % M(997))
        int i = index(key);

        /*
         *  position is -1 if:
         *  1. Array is full
         *  2. An empty slot has been found, but no matching key
         *
         *  position is spot of matching if key is found
         */
        int position = findKey(i, key);

        /*
        * Need to determine if its full or has an empty space
        */
        if(position < 0){
            position = findEmpty(i);
        }

        /*
        * if findEmpty has returned -1 it means the map is full
        * new entry can NOT be added, throw exception
        */
        if(position < 0){
            throw new IllegalArgumentException("Map is full");
        }

        /*
        * if array spot has empty key and value, put it there and increase size
        * if array spot has empty key, but filled value, add key and write over value
        */
        if (data[position] == null) {
            data[position] = new Entry(key, value);
            size++;
        } else {
            data[position].key = key;
            data[position].value = value;
        }
    }

    private int index(K key) {

        int positiveHash = key.hashCode() & 0x7f_ff_ff_ff;

        return positiveHash % M;
    }

    private boolean isMissing(int i) {
        /*
         * returns true if:
         *  1. index is out of bounds for array
         *  2. the array is empty at index position
         *  3. the key property of array at index position is null (formerly deleted)
         */
        return i == data.length || data[i] == null || data[i].key == null;
    }

    private boolean isPresentButNotMatching(int i, K key) {
        /*
        * returns true (keeps attempting to find key) if:
        *  1. the index is within array length
        *  2. there is something in the array at index position
        *  3. the key at index position in array is not the same as the key we are attempting to put
        *
        * returns false (stops) if:
        *  1. hits end of array
        *  2. finds an empty spot in the array at index position
        *  3. finds a matching key at index position in array
        */
       return i < data.length && data[i] != null && !key.equals(data[i].key);
    }

    @Override
    public void delete(K key) {

        int i = index(key);

        int position = findKey(i, key);

        /*
        * if key cant be found, there is nothing to delete
        */
        if(position < 0){
            return;
        }

        /*
        * delete key and value for position where key was found
        */
        if(data[position] != null){
            data[position].key = null;
            data[position].value = null;
            size--;
        }
    }

    @Override
    public V get(K key) {
        int i = index(key);

        int position = findKey(i, key);
        if (position >= 0 && !isMissing(position)) {
            return data[position].value;
        }

        return null;
    }

    @Override
    public int size() {
        return size;
    }
}
