package com.ai;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.FileAttribute;
import java.util.logging.Logger;
import java.util.*;

public class NeuronFactory {

    private final static Logger LOGGER = Logger.getLogger(Main.class.getName());
    private final static HashMap<String,Neuron> dictionary = NeuronFactory.loadDictionary(); // build up the index of keywords

    private static HashMap<String,Neuron> loadDictionary() {

        // build up the index of keywords
        HashMap<String,Neuron> index = new HashMap<String,Neuron>();

        // load the index from a file
        try {
            String draw = new String(Files.readAllBytes(Paths.get("data/basicword.txt")));
            String [] dvalues = draw.split(" ");
            for(int i=0;i<dvalues.length;i++) {
                Neuron n = new Neuron();
                n.memory = dvalues[i];
                index.put(dvalues[i],n);
            }

            LOGGER.info(index.size() + " dictionary entries.");

        } catch (Throwable e) {
            e.printStackTrace();
        }

        return index;
    }



    public static Neuron addMemory(long memoryid, String thought) {

        // todo as memory is added we need to evaluate language context (question,verb,noun,adjective,etc...)

        Neuron startingNeuron = null;
        Neuron previousNeuron = null;
        String[] dvalues = thought.split(" ");

        for(int i2=0;i2<dvalues.length;i2++) {
            if(!dictionary.containsKey(dvalues[i2])) {
                Neuron n = new Neuron();
                n.memory = dvalues[i2];
                dictionary.put(dvalues[i2], n);
            }

            Neuron n = dictionary.get(dvalues[i2]);
            if(previousNeuron != null){
                previousNeuron.pathways.put(memoryid,n);
                //System.out.println("#" + memoryid + " " + previousNeuron.memory + " added pathway to " + n.memory);
                previousNeuron = n;
            }else {
                previousNeuron = n;
                startingNeuron = n;
            }
        }

        // evaluate if the thought is a question
        if((thought.trim().endsWith("?")))
        startingNeuron.classifiers.add("Question");

        LOGGER.info(startingNeuron.trace(memoryid));

        return startingNeuron;
    }

    public static void trace(HashMap<Long,Neuron> memories) {

        for(Map.Entry<Long, Neuron> entry : memories.entrySet()) {
            System.out.println(entry.getValue().trace(entry.getKey()));
        }
    }

}
