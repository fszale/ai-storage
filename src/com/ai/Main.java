package com.ai;
import java.util.logging.Logger;
import java.util.*;

public class Main {

    private final static Logger LOGGER = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {

        // debugger off
        //LOGGER.setLevel(Level.OFF);

        // build up the index of keywords
        HashMap<String,Neuron> index = Loader.loadDictionary();
        HashMap<Long,Neuron> memories = new HashMap<Long,Neuron>();

        // load the personality from a file
        Loader.loadPersonality(index, memories);

        System.out.println("memoryid : " + memories.size());

        String inline = "";
        do
        {
            inline = Input.readInputLine("Say something ... (type quit to end)");

            if (inline.equalsIgnoreCase("quit")) {
                System.exit(0);
            } else if (inline.equalsIgnoreCase("save")) {
                Loader.savePersonality(memories);
            } else if (inline.equalsIgnoreCase("trace")) {
                Loader.trace(memories);
            }else {
                // train based on input specified
                long memoryid = memories.size() + 1;
                memories.put(memoryid,Loader.addMemory(memoryid, index, inline));
            }
        }
        while (true);
    }
}
