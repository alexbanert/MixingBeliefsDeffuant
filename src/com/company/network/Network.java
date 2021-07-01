package com.company.network;

import com.company.ResultRow;

public interface Network {
    public void generateAgents();
    public void step();
    public ResultRow run();
}
