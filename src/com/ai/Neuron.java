package com.ai;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Neuron {

    private final static Logger LOGGER = Logger.getLogger(Main.class.getName());

    public enum CodeStatus {
        Ready,
        Changing,
        Loading,
        Retire
    };

    // add evaluators for I, It, We, Its (evaluators have a weighted percentage of which
    // perspective is applied more depending on the question asked)
    // add connections to other neurons
    HashMap<Long,Neuron> pathways = new HashMap<Long,Neuron>();

    // I think the evaluators should serve as a context to the neurons and connection pathways first

    // add storage for the actual thought/idea
    String memory = "";
    // add classifiers (tags)
    List<String> classifiers = new ArrayList<String>();
    // add status (dirty,pending classification, ready)

    // add code version
    Integer version = 1;
    // add code status (update,ready,pending)
    CodeStatus codeStatus = CodeStatus.Ready;


    public String trace(Long memoryidx) {

        String ret = " - > " + memory + " (p#" + pathways.size() + ")";

        Neuron mem = pathways.get(memoryidx);
        if(mem != null) {
            ret += mem.trace(memoryidx);
        }

        return ret;
    }

    public String traceMemory(Long memoryidx) {

        String ret = " " + memory;

        Neuron mem = pathways.get(memoryidx);
        if(mem != null) {
            ret += mem.traceMemory(memoryidx);
        }

        return ret;
    }
}
