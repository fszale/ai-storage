package com.ai;
import java.util.*;

public class Evaluator {

    public static void ProcessStatement(Persona source, String statement) {

        // todo look at the full context of the question across four quadrants
        // to capture the core meaning (praise,sarcasm,profound thought)

        // classify the context of the statement

        // add memory of the statement to the persona interaction
        Neuron newMem = source.addMemory(statement);

        // determine response based on the context classification
    }

    public static void FindMemories(HashMap<Long,Neuron> memories) {

        // lunch multiple threads
        // penetrate memories from different entry points, scan through up and down

    }

}
