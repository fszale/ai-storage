package com.ai;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Logger;
import java.util.*;
import java.util.logging.Level;

public class Main {

    private final static Logger LOGGER = Logger.getLogger(Main.class.getName());

    private static void showHelp() {

        try {
            List<String> lst = Files.readAllLines(Paths.get("data/help.txt"));
            String[] content = lst.toArray(new String[]{});
            for(int i=0;i<content.length;i++) {
                System.out.println(content[i]);
            }

        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        // debugger off
        LOGGER.setLevel(Level.OFF);

        PersonaFactory.init();
        Persona cperson = PersonaFactory.get("AI Person");

        FeelingFactory.init();

        String command = "";
        do
        {
            command = Input.readInputLine("Say something ... (type quit. to end)\n\r");

            switch(command.toLowerCase()) {

                case "quit.":
                    System.exit(0);
                    return;

                case "save.":
                    PersonaFactory.save();
                    NeuronFactory.saveDictionary();
                    break;

                case "load.":
                    PersonaFactory.load();
                    break;

                case "trace.":
                    PersonaFactory.trace();
                    break;

                case "help":
                    showHelp();

                case "optimize":
                    // todo: add code to optimize and write code
                    break;

                case "train.":
                    String name = Input.readInputLine("Name to train? (default is 'AI Person')\n\r");
                    cperson = PersonaFactory.get(name);
                    break;

                case "status.":
                    PersonaFactory.status();
                    NeuronFactory.status();
                    FeelingFactory.status();
                    System.out.println("Current persona is " + cperson.name);
                    break;

                default:
                    // if this is a question and current person is AI then we need to create a quest account
                    if(command.trim().endsWith("?") && cperson.name == "AI Person"){
                        name = Input.readInputLine("What is your name?\n\r");
                        cperson = PersonaFactory.get(name);
                    }
                    Evaluator.ProcessStatement(cperson,command);
                    break;
            }
        }
        while (true);
    }
}
