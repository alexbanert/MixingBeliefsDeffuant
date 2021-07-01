package com.company.opinion;

public interface Opinion<T> {
    public T getValue();
    public void setValue(T value);
}
