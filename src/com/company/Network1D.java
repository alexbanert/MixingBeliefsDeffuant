package com.company;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Network1D implements Network {

    Agent[] network;
    int numberOfAgents = Config.getNumberOfAgents();
    float threshold = Config.getThreshold();
    int totalIterations = Config.getIterations();
    int currentIteration = 0;
    boolean logResults = Config.isLogResults();
    int logEveryIteration = Config.getLogEveryIteration();
    ArrayList<ResultRow> results = new ArrayList<>();
    Random random = new Random();

    public Network1D() {
        generateAgents();
        currentIteration = 0;
        if (logResults)
            results.add(new ResultRow(currentIteration, networkAsArrayList()));
    }


    @Override
    public void generateAgents() {
        network = new Agent[numberOfAgents];
        for (int i = 0; i < numberOfAgents; i++) {
            network[i] = new AgentContinuous();
        }
    }

    @Override
    public void run() {
        while (currentIteration < totalIterations) {
            step();
            if (logResults && currentIteration % logEveryIteration == 0) {
                results.add(new ResultRow(currentIteration, networkAsArrayList()));
            }
        }
        
        if(logResults)
            logResultsToFile();


    }


    @Override
    public void step() {

        int firstAgentIndex = random.nextInt(numberOfAgents);
        int secondAgentIndex = random.nextInt(numberOfAgents);
        // Avoid picking the same element twice
        while (firstAgentIndex == secondAgentIndex) {
            secondAgentIndex = random.nextInt(numberOfAgents);
        }
        Agent agent1 = network[firstAgentIndex];
        Agent agent2 = network[secondAgentIndex];

        if (agent1.distanceTo(agent2) < threshold) {
            agent1.adjustOpinionWith(agent2);
        }
        currentIteration++;
    }

    private ArrayList<String> networkAsArrayList() {
        ArrayList<String> result = new ArrayList<>();
        for (int i = 0; i < numberOfAgents; i++) {
            result.add(network[i].toString());
        }
        return result;
    }

    private void logResultsToFile(){
        ResultFileWriter rfw = new ResultFileWriter();
        try {
            rfw.writeToFile(results);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
