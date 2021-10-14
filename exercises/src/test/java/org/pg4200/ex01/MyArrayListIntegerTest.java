package org.pg4200.ex01;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class MyArrayListIntegerTest {

    protected MyArrayListInteger getNewInstance() {
        return new MyArrayListInteger();
    }

    private MyArrayListInteger list;

    @BeforeEach
    public void initTest(){
        //before each test is executed, create a new container
        list = getNewInstance();
    }

    @Test
    public void testEmpty(){
        //a newly created container should be empty
        assertEquals(0, list.size());
    }

    @Test
    public void testAddOneElement(){

        int n = list.size();

        list.add(1);

        assertEquals(n + 1, list.size());

        /*
            An option here would have to be to test if size is just 1, as
            the container (should) starts empty, instead of n+1.
            However, we would conflate the testing of two different features
            in the same test.
            Here, I am testing that adding an element increases the size by 1,
            regardless of its original size (even though I do actually test it
            with just 0 as starting size, although I could add more tests to handle
            further different cases)
         */
    }

    @Test
    public void testAddAndRetrieveElement() {

        Integer value = 1;

        list.add(value);

        /*
            As the container is empty, I m expecting to have it in position 0
         */
        Integer res = list.get(0);

        assertEquals(value, res);
    }

    @Test
    public void testAdd5Elements(){

        assertEquals(0, list.size());
        Integer one = 1;
        Integer two = 2;
        Integer three = 3;

        list.add(one);
        list.add(two);
        list.add(three);
        list.add(one);
        list.add(one);

        assertEquals(one, list.get(0));
        assertEquals(two, list.get(1));
        assertEquals(three, list.get(2));
        assertEquals(one, list.get(3));
        assertEquals(one, list.get(4));
    }

    @Test
    public void testOutOfIndex(){

        assertNull(list.get(-5));
        assertNull(list.get(42));
    }
}