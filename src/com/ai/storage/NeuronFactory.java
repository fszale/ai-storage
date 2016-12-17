package com.ai.storage;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.logging.Logger;
import java.util.*;

public class NeuronFactory {

    private final static Logger LOGGER = Logger.getLogger(NeuronFactory.class.getName());
    public final static HashMap<String, Neuron> dictionary = NeuronFactory.loadDictionary(); // build up the index of keywords

    private static HashMap<String, Neuron> loadDictionary() {

        // build up the index of keywords
        HashMap<String, Neuron> index = new HashMap<String, Neuron>();

        // load the index from a file
        try {
            String draw = new String(Files.readAllBytes(Paths.get("data/basicword.txt")));
            String [] dvalues = draw.split("\n");
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

    public static void saveDictionary() {

        String dic = "";
        for(Map.Entry<String, Neuron> mems : dictionary.entrySet()) {
            dic += mems.getValue().memory + "\n";
        }
        try {
            Files.write(Paths.get("data/basicword.txt"), dic.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (Throwable e) {
            e.printStackTrace();
        }

    }

    public static Neuron getNeuron(String itemMemory) {

        Neuron n = dictionary.get(itemMemory);

        if(n == null) {
            n = new Neuron();
            n.memory = itemMemory;
            dictionary.put(itemMemory, n);
        }

        return n;
    }

    public static void status() {

        System.out.println("Dictinary has " + dictionary.size() + " entries.");

    }
}
