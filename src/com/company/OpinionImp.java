package com.company;

public class OpinionImp<T> implements Opinion<T>{

    private T value;

    public OpinionImp(T value) {
        this.value = value;
    }

    @Override
    public T getValue() {
        return this.value;
    }

    @Override
    public void setValue(T value) {
        this.value = value;
    }
}
