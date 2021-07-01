package com.company.agent;

import com.company.opinion.Opinion;

public interface Agent<T> {
    public Opinion<T> getOpinion();
    public void adjustOpinionWith(Agent<T> partner);
    public float distanceTo(Agent<T> partner);
}
