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

    // add connections to other neurons
    HashMap<Memory,Neuron> relatedNeurons = new HashMap<Memory,Neuron>();

    // add storage for the actual thought/idea
    String memory = "";

    // add classifiers (tags)
    List<String> classifiers = new ArrayList<String>();
    // add status (dirty,pending classification, ready)

    // add code version
    Integer version = 1;
    // add code status (update,ready,pending)
    CodeStatus codeStatus = CodeStatus.Ready;

    public String trace() {

        return memory + " (rn:" + relatedNeurons.size() + ")";

    }

    /*
    public Pathway getPathway(Persona person) {

        Pathway path = pathways.get(person);
        if(path == null) {
            path = new Pathway();
            pathways.put(person, path);
        }

        return path;
    }

    public String trace(Persona person, Long memoryidx) {

        String ret = " > " + memory;

        Pathway path = pathways.get(person);
        if(path != null) {

            Neuron mem = path.memories.get(memoryidx);
            //ret += " - > " + memory + " (p#" + memories.size() + ")";
            if(mem != null && mem != this) {
                //ret += " > " + mem.memory;
                ret += mem.trace(person, memoryidx);
            }

            //if(classifiers.size()>0) {
                //ret += " " + classifiers.toString();
            //}
        }

        return ret;
    }

    public String traceMemory(Long memoryidx) {

        String ret = " " + memory;

        for(Map.Entry<Persona, Pathway> path : pathways.entrySet()) {

            HashMap<Long, Neuron> memories = path.getValue().memories;
            Neuron mem = memories.get(memoryidx);
            if (mem != null) {
                ret += mem.traceMemory(memoryidx);
            }
        }

        return ret;
    }
    */
}
