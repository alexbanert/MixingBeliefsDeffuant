package com.company;

import com.company.agent.AgentType;
import com.company.network.NetworkType;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ResultFileWriter {
    public void writeToFile(ArrayList<ResultRow> results) throws IOException {

        String fileName = constructFilename();
        System.out.println(fileName);
        BufferedWriter writer = new BufferedWriter(new FileWriter("results/" + fileName));
        for( ResultRow rr: results){
            writer.write(rr.toString());
            writer.write("\n");
        }
        writer.close();

    }

    private String constructFilename(){
        StringBuilder sb = new StringBuilder();
        sb.append("N");
        sb.append(Config.getNumberOfAgents());
        sb.append("d");
        sb.append(Config.getThreshold());
        sb.append("mu");
        sb.append(Config.getConvergence());
        sb.append("I");
        sb.append(Config.getIterations());
        if(Config.getNetworkType() == NetworkType.NETWORK1D){
            sb.append("1D");
        }
        else{
            sb.append("2D");
        }
        if(Config.getAgentType() == AgentType.CONTINUOUS){
            sb.append("C");
        }
        else{
            sb.append("V");
        }
        if (Config.getNetworkType() == NetworkType.NETWORK2D){
            sb.append("L");
            sb.append(Config.getLatticeSize());
        }
        if(Config.getAgentType() == AgentType.VECTOR){
            sb.append("m");
            sb.append(Config.getOpinionVectorLength());
        }
        sb.append(".txt");
        return sb.toString();
    }
}
