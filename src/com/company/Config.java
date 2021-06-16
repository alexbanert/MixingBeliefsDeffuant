package com.company;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class Config {

    private static int numberOfAgents;
    private static float threshold;
    private static float convergence;
    private static int iterations;
    private static NetworkType networkType;
    private static AgentType agentType;
    private static int latticeSize;
    private static int opinionVectorLength;
    private static boolean logResults;
    private static int logEveryIteration;

    Properties properties = new Properties();
    String defaultConfigFilename = "resources/parameters.config";

    private static final Config instance = new Config();


    private Config() {

        // Load default properties on start
        loadProperties(defaultConfigFilename);

    }



    public void loadProperties(String filename) {
        try {
            FileInputStream in = new FileInputStream(filename);
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }

        numberOfAgents = Integer.parseInt(properties.getProperty("network.agents"));
        threshold = Float.parseFloat(properties.getProperty("network.threshold"));
        convergence = Float.parseFloat(properties.getProperty("network.convergence"));
        iterations = Integer.parseInt(properties.getProperty("network.iterations"));

        if (properties.getProperty("network.type").equals("2D"))
            networkType = NetworkType.NETWORK2D;
        else
            networkType = NetworkType.NETWORK1D; // Default to 1D

        if (properties.getProperty("agent.type").equals("vector"))
            agentType = AgentType.VECTOR;
        else
            agentType = AgentType.CONTINUOUS;

        latticeSize = Integer.parseInt(properties.getProperty("network.lattice"));
        opinionVectorLength = Integer.parseInt(properties.getProperty("agent.opinionvector"));
        logResults = properties.getProperty("network.logresults").equals("1");
        logEveryIteration = Integer.parseInt(properties.getProperty("network.logevery"));
    }

    public static int getNumberOfAgents() {
        return numberOfAgents;
    }

    public static void setNumberOfAgents(int numberOfAgents) {
        Config.numberOfAgents = numberOfAgents;
    }

    public static float getThreshold() {
        return threshold;
    }

    public static void setThreshold(float threshold) {
        Config.threshold = threshold;
    }

    public static float getConvergence() {
        return convergence;
    }

    public static void setConvergence(float convergence) {
        Config.convergence = convergence;
    }

    public static int getIterations() {
        return iterations;
    }

    public static void setIterations(int iterations) {
        Config.iterations = iterations;
    }

    public static NetworkType getNetworkType() {
        return networkType;
    }

    public static void setNetworkType(NetworkType networkType) {
        Config.networkType = networkType;
    }

    public static AgentType getAgentType() {
        return agentType;
    }

    public static void setAgentType(AgentType agentType) {
        Config.agentType = agentType;
    }

    public static int getLatticeSize() {
        return latticeSize;
    }

    public static void setLatticeSize(int latticeSize) {
        Config.latticeSize = latticeSize;
    }

    public static int getOpinionVectorLength() {
        return opinionVectorLength;
    }

    public static void setOpinionVectorLength(int opinionVectorLength) {
        Config.opinionVectorLength = opinionVectorLength;
    }

    public static boolean isLogResults() {
        return logResults;
    }

    public static void setLogResults(boolean logResults) {
        Config.logResults = logResults;
    }

    public static int getLogEveryIteration() {
        return logEveryIteration;
    }

    public static void setLogEveryIteration(int logEveryIteration) {
        Config.logEveryIteration = logEveryIteration;
    }


}
