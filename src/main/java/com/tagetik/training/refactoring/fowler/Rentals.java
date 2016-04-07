package com.tagetik.training.refactoring.fowler;

import java.util.Iterator;
import java.util.LinkedList;

public class Rentals implements Iterable<Rental> {
    private LinkedList<Rental> list;

    @Override
    public Iterator<Rental> iterator() {
        return list.iterator();
    }
}
