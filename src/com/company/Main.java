package com.company;

import com.company.network.Network;
import com.company.network.Network1D;
import com.company.network.Network2D;
import com.company.network.NetworkType;

import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        Config.loadProperties("fig2.config");
        runExperiment(Config.getExperimentIterations(), Config.isLogOnlyLast());

    }

    private static void runExperiment(int iterations, boolean log) {

        ArrayList<ResultRow> results = new ArrayList<>();

        for (int i = 0; i < iterations; i++) {
            Network network;
            if (Config.getNetworkType() == NetworkType.NETWORK1D) {
                network = new Network1D();
            } else if (Config.getNetworkType() == NetworkType.NETWORK2D) {
                network = new Network2D();
            } else {
                System.out.println("Bad network type");
                return;
            }
            ResultRow lastRow = network.run();
            results.add(lastRow);
            System.out.println(results.size());
        }

        if (log) {
            ResultFileWriter rfw = new ResultFileWriter();
            try {
                rfw.writeToFile(results);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
