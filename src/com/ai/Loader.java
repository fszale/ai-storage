package com.ai;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.FileAttribute;
import java.util.logging.Logger;
import java.util.*;

public class Loader {

    private final static Logger LOGGER = Logger.getLogger(Main.class.getName());

    public static HashMap<String,Neuron> loadDictionary() {

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

    public static Neuron addMemory(long memoryid, HashMap<String,Neuron> index, String thought) {

        Neuron startingNeuron = null;
        Neuron previousNeuron = null;
        String[] dvalues = thought.split(" ");

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

        return startingNeuron;
    }

    public static void savePersonality(HashMap<Long,Neuron> memories) {

        List<String> memlist = new ArrayList<String>();
        for(Map.Entry<Long, Neuron> entry : memories.entrySet()) {
            memlist.add(entry.getValue().traceMemory(entry.getKey()));
        }
        try {
            Files.write(Paths.get("data/newPersonality.txt"), memlist, StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    public static void trace(HashMap<Long,Neuron> memories) {

        for(Map.Entry<Long, Neuron> entry : memories.entrySet()) {
            LOGGER.info(entry.getValue().trace(entry.getKey()));
        }
    }

    public static void loadPersonality(HashMap<String,Neuron> index, HashMap<Long,Neuron> memories) {

        long memoryid = 0;

        try {
            List<String> slst = Files.readAllLines(Paths.get("data/personality.txt"));
            String[] thoughts = slst.toArray(new String[]{});
            for(int i=0;i<thoughts.length;i++) {
                memoryid++;
                memories.put(memoryid,addMemory(memoryid, index, thoughts[i]));
            }

        } catch (Throwable e) {
            e.printStackTrace();
        }

    }

}
