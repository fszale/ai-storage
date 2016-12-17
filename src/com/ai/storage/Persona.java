package com.ai.storage;

import com.ai.storage.Memory;

import java.util.*;

// track each personality and its interactions
public class Persona {

    public String name = "";
    public Long memoryidx = 0L;
    public ArrayList<Memory> memories = new ArrayList<Memory>();

    public Persona(String name) {
        this.name = name;
    }

    public Memory addMemory(Long pmemidx, String thought) {

        memoryidx = pmemidx;
        Memory newMem = new Memory(memoryidx, this, thought);
        memories.add(newMem);

        return newMem;
    }

    public Memory addMemory(String thought) {

        memoryidx++;
        Memory newMem = new Memory(memoryidx, this, thought);
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
