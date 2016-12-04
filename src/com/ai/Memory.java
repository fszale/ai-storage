package com.ai;

import java.util.ArrayList;

public class Memory {

    public Long memoryId = 0L;
    public Persona person = null;
    public ArrayList<Neuron> neurons = new ArrayList<Neuron>();

    public ArrayList<String> classifiers  = new ArrayList<String>(); // happy, sad, etc.

    // constructor
    public Memory(Long memidx, Persona p, String thought) {

        memoryId = memidx;
        person = p;

        String[] dvalues = thought.split(" ");

        Neuron parentNeuron = null;
        for(int i=0;i<dvalues.length;i++) {

            String cs = dvalues[i].replaceAll("\\?","");
            Neuron n = NeuronFactory.getNeuron(cs);
            neurons.add(n);

            if(parentNeuron != null) {  // relate to parent Neuron when available
                parentNeuron.relatedNeurons.put(this, n);
            }
            parentNeuron = n;
        }

    }

    public String trace () {

        String ret = "";

        for (int i = 0;i<neurons.size();i++) {
            ret += (ret.length() > 0 ? " " : "") + neurons.get(i).trace();
        }
        ret += ret.length() > 0 ? "\n" : "";

        return ret;
    }

    public String traceRaw () {

        String ret = "";

        for (int i = 0;i<neurons.size();i++) {
            ret += (ret.length() > 0 ? " " : "") + neurons.get(i).memory;
        }

        return ret;
    }
}
