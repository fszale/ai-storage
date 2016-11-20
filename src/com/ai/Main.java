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
        HashMap<String,Neuron> index = Loader.loadDictionary();

        // load the personality from a file
        long memoryid = Loader.loadPersonality(index);

        System.out.println("memoryid : " + memoryid);

    }
}
