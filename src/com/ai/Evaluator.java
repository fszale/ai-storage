package com.ai;
import java.util.*;

public class Evaluator {

    public static Memory runningMemory = null;

    public static void ProcessStatement(Persona source, String statement) {

        // todo look at the full context of the question across four quadrants
        // to capture the core meaning (praise,sarcasm,profound thought)

        // classify the context of the statement

        // add memory of the statement to the persona interaction
        Memory newMem = source.addMemory(statement);

        // relate this new memory to the last memory for AI Person

        // add related memory if needed
        if(runningMemory != null) {
            runningMemory.relatedMemories.add(newMem);
        }

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
        ret = statement.replaceFirst("what do you","").replaceAll("\\?","");

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
        for (int i = 0; i < dvalues.length; i++) {
            Neuron n = NeuronFactory.getNeuron(dvalues[i]);
            System.out.println("Found " + n.trace());

            for(Map.Entry<Memory, Neuron> entry : n.relatedNeurons.entrySet()) {
                System.out.println(entry.getKey().trace());
            }

            words.add(n);
        }

    }

}
