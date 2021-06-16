package com.company;

import java.util.ArrayList;
import java.util.Arrays;

public class ResultRow {
    int iteration;
    ArrayList<String> values;

    public ResultRow(int iteration, ArrayList<String> values) {
        this.iteration = iteration;
        this.values = values;
    }

    @Override
    public String toString() {
        return iteration + ";; " + Arrays.toString(values.toArray()).replace("[", "").replace("]", "");
    }
}
