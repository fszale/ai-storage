package com.ai;
import java.nio.file.Files;
import java.util.HashMap;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    private final static Logger LOGGER = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {

        // debugger off
        //LOGGER.setLevel(Level.OFF);

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

        // load the personality from a file
        long memoryid = 0;
        try {
            List<String> slst = Files.readAllLines(Paths.get("data/personality.txt"));
            String[] thoughts = slst.toArray(new String[]{});
            Neuron previousNeuron;
            for(int i=0;i<thoughts.length;i++) {

                Neuron startingNeuron = null;
                memoryid++;
                previousNeuron = null;
                String[] dvalues = thoughts[i].split(" ");

                for(int i2=0;i2<dvalues.length;i2++) {
                    if(!index.containsKey(dvalues[i2])) {
                        Neuron n = new Neuron();
                        n.memory = dvalues[i2];
                        index.put(dvalues[i2], n);
                    }

                    Neuron n = index.get(dvalues[i2]);
                    if(previousNeuron != null){
                        previousNeuron.pathways.put(memoryid,n);
                        //System.out.println("#" + memoryid + " " + previousNeuron.memory + " added pathway to " + n.memory);
                        previousNeuron = n;
                    }else {
                        previousNeuron = n;
                        startingNeuron = n;
                    }
                }

                LOGGER.info(startingNeuron.trace(memoryid));
            }


        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
