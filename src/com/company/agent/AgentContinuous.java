package com.company.agent;

import com.company.Config;
import com.company.opinion.Opinion;
import com.company.opinion.OpinionImp;

public class AgentContinuous implements Agent<Float> {

    private Opinion<Float> opinion;

    public AgentContinuous() {
        opinion = new OpinionImp<Float>((float) Math.random());
    }

    public Opinion<Float> getOpinion() {
        return opinion;
    }


    @Override
    public void adjustOpinionWith(Agent<Float> partner) {
        float ownOpinion = this.opinion.getValue();
        float partnerOpinion = partner.getOpinion().getValue();

        float convergence = Config.getConvergence();

        this.opinion.setValue(ownOpinion + convergence * (partnerOpinion - ownOpinion));
        partner.getOpinion().setValue(partnerOpinion + convergence * (ownOpinion - partnerOpinion));
    }

    @Override
    public float distanceTo(Agent<Float> partner) {
        return Math.abs(opinion.getValue() - partner.getOpinion().getValue());
    }

    @Override
    public String toString() {
        return opinion.getValue().toString();
    }
}
