package com.ai;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;

// track each personality and its interactions
public class Persona {

    public String name = "";
    public HashMap<Long,Neuron> memories = new HashMap<Long,Neuron>();

    public Persona(String name) {
        this.name = name;
    }

    public Neuron addMemory(String thought) {

        long memoryid = memories.size() + 1;
        Neuron newMem = NeuronFactory.addMemory(memoryid, thought);
        memories.put(memoryid, newMem);

        return newMem;

    }
}
