package com.ai;
import java.util.logging.Logger;
import java.util.*;
import java.util.logging.Level;

public class Main {

    private final static Logger LOGGER = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {

        // debugger off
        LOGGER.setLevel(Level.OFF);

        // build up the index of keywords
        HashMap<String,Neuron> index = Loader.loadDictionary();
        Persona aipersona = new Persona("AI Person");

        // load the personality from a file
        Loader.loadPersonality(index, aipersona.memories);

        String inline = "";
        do
        {
            inline = Input.readInputLine("Say something ... (type quit to end)\n");

            if (inline.equalsIgnoreCase("quit")) {
                System.exit(0);

            } else if (inline.equalsIgnoreCase("save")) {
                Loader.savePersonality(aipersona.memories);

            } else if (inline.equalsIgnoreCase("trace")) {
                Loader.trace(aipersona.memories);

            }else {
                // train based on input specified
                long memoryid = aipersona.memories.size() + 1;
                aipersona.memories.put(memoryid,Loader.addMemory(memoryid, index, inline));
            }
        }
        while (true);
    }
}
