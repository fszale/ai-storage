package com.ai;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PersonaFactory {

    private static HashMap<String,Persona> personas = new HashMap<String,Persona>();

    public static void init(){

        if(personas.size() == 0) {
            Persona p = new Persona("AI Person");
            personas.put("AI Person", p);

            // load the personality from a file
            load();
        }

    }

    public static Persona get(String name) {

        Persona p = null;
        if(!personas.containsKey(name)) {
            p = add(name);
        }else{
            p = personas.get(name);
        }
        return p;

    }

    public static void save() {

        for(Map.Entry<String, Persona> entry : personas.entrySet()) {
            Persona p = entry.getValue();
            List<String> memlist = new ArrayList<String>();
            for (int i = 0;i<p.memories.size();i++) {
                memlist.add(p.memories.get(i).traceRaw());
            }
            try {
                Files.write(Paths.get("output/memories-"+p.name.replaceAll(" ","-")+".txt"), memlist, StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }

    }

    public static void load() {

        for(Map.Entry<String, Persona> entry : personas.entrySet()) {
            Persona p = entry.getValue();

            try {
                List<String> slst = Files.readAllLines(Paths.get("output/memories-"+p.name.replaceAll(" ","-")+".txt"));
                String[] thoughts = slst.toArray(new String[]{});
                for(int i=0;i<thoughts.length;i++) {
                    p.addMemory(thoughts[i]);
                }

            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
    }

    public static void trace() {

        for(Map.Entry<String, Persona> entry : personas.entrySet()) {
            System.out.println(entry.getValue().name + " has " + entry.getValue().memories.size() + " memories.");
            String ret = entry.getValue().trace();
            System.out.println(ret);
        }

    }

    public static Persona add(String name) {

        for(Map.Entry<String, Persona> entry : personas.entrySet()) {
            if(entry.getKey() == name) {
                return entry.getValue();
            }
        }
        Persona p = new Persona(name);
        personas.put(name, p);
        return p;

    }


    public static void status() {

        for(Map.Entry<String, Persona> entry : personas.entrySet()) {
            Persona p = entry.getValue();
            System.out.println("Persona " + p.name + " has " + p.memories.size() + " memories.");
        }

    }

}
