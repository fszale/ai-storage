package com.ai;

import java.util.HashMap;

// track each personality and its interactions
public class Persona {

    public String name = "";
    public HashMap<Long,Neuron> memories = new HashMap<Long,Neuron>();

    public Persona(String name) {
        this.name = name;
    }
}
