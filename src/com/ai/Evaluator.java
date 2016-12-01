package com.ai;
import java.util.*;

public class Evaluator {

    public static void ProcessStatement(Persona source, String statement) {

        // todo look at the full context of the question across four quadrants
        // to capture the core meaning (praise,sarcasm,profound thought)

        // classify the context of the statement

        // add memory of the statement to the persona interaction
        Memory newMem = source.addMemory(statement);

        // determine response based on the context classification
        Respond(statement);
    }

    public static void Respond(String statement) {

        String ret = statement;

        if(statement.trim().endsWith("?")){
            ret = "Great question!";
            // find memories
            FindMemories(RemoveQuestion(statement));
        }

        System.out.println(ret);
    }

    private static String RemoveQuestion(String statement) {

        String ret;
        ret = statement.replaceFirst("what do you","");

        return ret;
    }

    private static void FindMemories(String statement) {

        Persona cperson = PersonaFactory.get("AI Person");

        // lunch multiple threads
        // penetrate memories from different entry points, scan through up and down
        //cperson.memories

        // look through dictionary, find memories related to the AI Person
        String[] dvalues = statement.split(" ");
        ArrayList<Neuron> words = new ArrayList<Neuron>();
        for (int i2 = 0; i2 < dvalues.length; i2++) {
            Neuron n = NeuronFactory.dictionary.get(dvalues[i2]);
            if(n == null) {
                n = new Neuron();
                n.memory = dvalues[i2];
                NeuronFactory.dictionary.put(dvalues[i2], n);
            }
            words.add(n);
        }
        System.out.println("words " + words.size());
        System.out.println("person is " + cperson.name);
        /*
        for (int i = 0;i<words.size();i++) {
            System.out.println("word " + words.get(i).memory);
            Pathway path = words.get(i).pathways.get(cperson);
            System.out.println("pathways " + words.get(i).pathways.size());
            if(path != null) {
                System.out.println("looking for memories");
                HashMap<Long, Neuron> memories = path.memories;
                for(Map.Entry<Long, Neuron> entry : memories.entrySet()) {
                    System.out.println(entry.getValue().trace(cperson,entry.getKey()) + "\n");
                }
            }
        }
        */
    }

}
