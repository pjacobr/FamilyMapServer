package service;

import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;

import javax.xml.crypto.Data;

import model.Person;

/**
 * Created by jacob on 3/6/2017.
 */

public class DataGenerator {

    static LocationData locData;
    private NameData femNames;
    private NameData maleNames;
    private NameData lastNames;
    private PersonNode userNode;



    public static void main(String args[]){
        DataGenerator dg = new DataGenerator();
    }

    public DataGenerator(Person userNode){
        //call the sequence
        //call the other constructor with 4 as the default
        final int DEFAULT_GENERATIONS = 4;
        this(userNode, DEFAULT_GENERATIONS);
    }

    public DataGenerator(Person userNode, int numGenerations){
        this();
        this.userNode = new PersonNode(userNode);
        this.numGenerations = numGenerations;
    }


    public DataGenerator(){
        //fill the name and location data when a datagenerator is created.
        Gson gson = new Gson();
        Reader reader = null;
        try {
            reader = new FileReader("C:\\Users\\jacob\\AndroidStudioProjects\\FamilyMapServer\\mapserver\\locations.json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        locData = gson.fromJson(reader, LocationData.class);
        gson = new Gson();
        try {
            reader = new FileReader("C:\\Users\\jacob\\AndroidStudioProjects\\FamilyMapServer\\mapserver\\fnames.json");
            femNames = gson.fromJson(reader, NameData.class);

            reader = new FileReader("C:\\Users\\jacob\\AndroidStudioProjects\\FamilyMapServer\\mapserver\\mnames.json");
            maleNames = gson.fromJson(reader, NameData.class);

            reader = new FileReader("C:\\Users\\jacob\\AndroidStudioProjects\\FamilyMapServer\\mapserver\\snames.json");
            lastNames = gson.fromJson(reader, NameData.class);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //this.userNode = new PersonNode(userNode);
    }

    private int numGenerations;
    private helperTrie(PersonNode curNode, int curLevel, int curYear){

    }
    public void fillTrie(int curLevel, int curYear){
        

    }
    //Here is a person node
    class PersonNode {
        private PersonNode mother;
        private PersonNode father;
        private Person info;

        public PersonNode(Person data){
            info = data;
        }

        public Person getInfo() {
            return info;
        }
    }

    public class NameData{
        String[] data;
        public void print(){
            for(String name : data){
                System.out.println(name);
            }
        }
    }



}
