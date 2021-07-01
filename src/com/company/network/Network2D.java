package com.company.network;

import com.company.Config;
import com.company.ResultFileWriter;
import com.company.ResultRow;
import com.company.agent.Agent;
import com.company.agent.AgentContinuous;
import com.company.agent.AgentType;
import com.company.agent.AgentVector;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Network2D implements Network {

    Agent[][] network;
    int latticeSize = Config.getLatticeSize();
    int numberOfAgents = latticeSize * latticeSize;
    float threshold = Config.getThreshold();
    int totalIterations = Config.getIterations();
    int currentIteration = 0;
    boolean logResults = Config.isLogResults();
    int logEveryIteration = Config.getLogEveryIteration();
    ArrayList<ResultRow> results = new ArrayList<>();
    AgentType agentType = Config.getAgentType();
    Random random = new Random();

    public Network2D() {
        generateAgents();
        currentIteration = 0;
        if (logResults && logEveryIteration != 1)
            results.add(new ResultRow(currentIteration, networkAsArrayList()));
    }


    @Override
    public void generateAgents() {
        network = new Agent[latticeSize][latticeSize];
        for (int i = 0; i < latticeSize; i++) {
            for (int j = 0; j < latticeSize; j++) {
                if (agentType == AgentType.CONTINUOUS)
                    network[i][j] = new AgentContinuous();
                else
                    network[i][j] = new AgentVector();
            }
        }
    }

    @Override
    public void step() {

        int firstAgentX = random.nextInt(latticeSize);
        int firstAgentY = random.nextInt(latticeSize);
        int secondAgentX = firstAgentX;
        int secondAgentY = firstAgentY;

        if (Math.random() < 0.5) {
            if (Math.random() < 0.5)
                secondAgentX = (secondAgentX + 1) % latticeSize;

            else {
                secondAgentX = (secondAgentX - 1) % latticeSize;
                if (secondAgentX == -1)
                    secondAgentX = latticeSize - 1;
            }
        }
//        if (Math.random() < 0.5)
        else
            {
            if (Math.random() < 0.5)
                secondAgentY = (secondAgentY + 1) % latticeSize;

            else {
                secondAgentY = (secondAgentY - 1) % latticeSize;
                if (secondAgentY == -1)
                    secondAgentY = latticeSize - 1;
            }
        }
        Agent agent1 = network[firstAgentX][firstAgentY];
        Agent agent2 = network[secondAgentX][secondAgentY];


        if (agent1.distanceTo(agent2) < threshold) {
//            System.out.println(agent1.distanceTo(agent2));

            agent1.adjustOpinionWith(agent2);


        }
        currentIteration++;

    }


    @Override
    public ResultRow run() {

//        int currentAgentX = 0;
//        int currentAgentY = 0;

//        for(int currentAgentX = 0; currentAgentX < latticeSize; currentAgentX++){
//            for(int currentAgentY = 0; currentAgentY < latticeSize; currentAgentY++){
//                nrStep(currentAgentX, currentAgentY);
//            }
//        }

        while (currentIteration < totalIterations) {
            step();
            if (currentIteration % logEveryIteration == 0 && logEveryIteration!=1) {
                results.add(new ResultRow(currentIteration, networkAsArrayList()));
            }
//            for(int currentAgentX = 0; currentAgentX < latticeSize && currentIteration < totalIterations; currentAgentX++){
//                for(int currentAgentY = 0; currentAgentY < latticeSize && currentIteration < totalIterations; currentAgentY++){
//                    nrStep(currentAgentX, currentAgentY);
//                    if (currentIteration % logEveryIteration == 0) {
//                        results.add(new ResultRow(currentIteration, networkAsArrayList()));
//                    }
//                }
//            }

        }

        if (logResults)
            logResultsToFile();

        return results.get(results.size() - 1);

    }


    private ArrayList<String> networkAsArrayList() {
        ArrayList<String> result = new ArrayList<>();
        for (int i = 0; i < latticeSize; i++) {
            for (int j = 0; j < latticeSize; j++) {
                result.add(network[i][j].toString());
            }
        }
        return result;
    }

    private void logResultsToFile() {
        ResultFileWriter rfw = new ResultFileWriter();
        try {
            rfw.writeToFile(results);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
