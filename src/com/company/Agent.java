package com.company;

public interface Agent<T> {
    public Opinion<T> getOpinion();
    public void adjustOpinionWith(Agent<T> partner);
    public float distanceTo(Agent<T> partner);
}
