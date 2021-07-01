package com.company.agent;

import com.company.Config;
import com.company.opinion.Opinion;
import com.company.opinion.OpinionImp;

import java.util.Arrays;

public class AgentVector implements Agent<boolean[]> {

    private Opinion<boolean[]> opinion;

    public AgentVector() {
        int m = Config.getOpinionVectorLength();
        boolean[] vector = new boolean[m];
        for (int i = 0; i < m; i++) {
            vector[i] = Math.random() < 0.5;
        }
        opinion = new OpinionImp<>(vector);
    }

    @Override
    public Opinion<boolean[]> getOpinion() {
        return opinion;
    }

    @Override
    public void adjustOpinionWith(Agent<boolean[]> partner) {
        boolean[] ownOpinion = this.opinion.getValue();
        boolean[] partnerOpinion = partner.getOpinion().getValue();

        float convergence = Config.getConvergence();

        for (int i = 0; i < ownOpinion.length; i++) {
            if(ownOpinion[i] != partnerOpinion[i]){
                if(Math.random() < 0.5){
                    if(Math.random() < convergence){
                        ownOpinion[i] = !ownOpinion[i];
                    }
                }
                else{
                    if(Math.random() < convergence){
                        partnerOpinion[i] = !partnerOpinion[i];
                    }
                }
            }
        }
    }

    @Override
    public float distanceTo(Agent<boolean[]> partner) {
        int distance = 0;
        boolean[] ownOpinion = this.opinion.getValue();
        boolean[] partnerOpinion = partner.getOpinion().getValue();

        for (int i = 0; i < ownOpinion.length; i++) {
            if (ownOpinion[i] != partnerOpinion[i])
                distance++;
        }

        return distance;
    }

    @Override
    public String toString() {
        return Arrays.toString(opinion.getValue()).replace('[','(').replace(']',')').replace("true","1").replace("false", "0");
    }
}
