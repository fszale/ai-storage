package com.ai.storage;

import com.ai.storage.Feeling;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileReader;
import java.nio.file.Paths;
import java.util.ArrayList;

public class FeelingFactory {

    private final static Memory feeling = null;
    private final static ArrayList<Feeling> feelings = new ArrayList<Feeling>(); // build up index of feelings

    // todo finish adding feelings to the json file

    public static void init() {

        //feelings are either positive or negative therefore binary
        //its the number of emotions involved that sway a memory/thought whichever way hence its a weighted judgement
        //the weight associated with each of the binary feelings is deeply rooted in one's accumulated experiences hence everyone is different from each other

        Memory feeling = new Memory(0L, PersonaFactory.get("AI Person"),"feeling");
        ArrayList<Neuron> ret = new ArrayList<Neuron>();

        // do recursive load of feelings from the json file
        try {

            FileReader fr = new FileReader(Paths.get("data/feeling.json").toString());
            JSONTokener tk = new JSONTokener(fr);
            JSONArray ja = new JSONArray(tk);
            for(int ix=0;ix<ja.length();ix++) {
                ret.addAll(loadNeuronRelation(ja.getJSONObject(ix)));
            }

        } catch (Throwable e) {
            e.printStackTrace();
        }

        for(int i=0;i<ret.size();i++){
            Feeling f = new Feeling();
            f.neuron = ret.get(i);
            //f.weight
            feelings.add(f);
        }
    }

    private static ArrayList<Neuron> loadNeuronRelation(JSONObject neuron) {

        ArrayList<Neuron> ret = new ArrayList<Neuron>();

        Neuron n = NeuronFactory.getNeuron(neuron.get("neuron").toString());
        ret.add(n);

        if(neuron.has("relate")) {
            JSONArray relatearr = neuron.getJSONArray("relate");
            if (relatearr != null) {
                for (int i = 0; i < relatearr.length(); i++) {
                    ArrayList<Neuron> related = loadNeuronRelation(relatearr.getJSONObject(i));
                    ret.addAll(related);

                    for (int ix = 0; ix < related.size(); ix++) {
                        n.relatedNeurons.put(feeling, related.get(ix));
                    }
                }
            }
        }

        return ret;
    }

    public static void status() {

        System.out.println("Feelings array has " + feelings.size() + " entries.");

    }
}
