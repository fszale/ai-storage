package com.ai;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;

// track each personality and its interactions
public class Persona {

    public String name = "";
    public ArrayList<Memory> memories = new ArrayList<Memory>();

    public Persona(String name) {
        this.name = name;
    }

    public Memory addMemory(String thought) {

        long memoryid = memories.size() + 1;
        Memory newMem = new Memory(memoryid, this, thought);
        memories.add(newMem);

        return newMem;
    }

    public String trace() {

        String ret = "";
        for (int i = 0;i<memories.size();i++) {
            ret += memories.get(i).trace();
        }

        return ret;
    }
}
