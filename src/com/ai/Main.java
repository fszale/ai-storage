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

        PersonaFactory pf = new PersonaFactory();
        Persona cperson = pf.get("AI Person");

        String command = "";
        do
        {
            command = Input.readInputLine("Say something ... (type quit. to end)\n");

            switch(command.toLowerCase()) {

                case "quit.":
                    System.exit(0);
                    break;

                case "save.":
                    pf.save();
                    break;

                case "load.":
                    pf.load();
                    break;

                case "trace.":
                    pf.trace();
                    break;

                case "help":
                    showHelp();

                case "optimize":
                    // todo: add code to optimize and write code
                    break;

                case "train.":
                    String name = Input.readInputLine("Name to train? (default is 'AI Person')\n");
                    cperson = pf.get(name);
                    break;

                case "status.":
                    pf.status();
                    System.out.println("Current persona is " + cperson.name);
                    break;

                default:
                    cperson.addMemory(command);
                    break;
            }
        }
        while (true);
    }
}
