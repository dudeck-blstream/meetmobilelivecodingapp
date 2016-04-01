package com.blstream.kameleon.util;

import java.util.LinkedList;

public class LimitedQueue extends LinkedList<Double> {

    private final int limit;

    LimitedQueue(int limit) {
        this.limit = limit;
    }

    public boolean add(Double newElement) {
        while (size() >= limit) {
            super.remove(0);
        }
        return super.add(newElement);
    }

    public Double getAverage(){
        Double sum = 0.0;
        for(Double item: this){
            sum += item;
        }
        return sum / size();
    }
}
