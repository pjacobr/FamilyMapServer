package service;

import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.time.Year;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import model.Event;
import model.Person;

/**
 * Created by jacob on 3/6/2017.
 */

public class DataGenerator {

    static LocationData locData;
    List<Person> ancestors;
    List<Event> eventList;
    private NameData femNames;
    private NameData maleNames;
    private NameData lastNames;
    private PersonNode userNode;
    private String descendant;
    private int numGenerations;
    //GENERATION gap between parents and children
    final private int MIN_GENERATION_GAP = 20;
    final private int MAX_GENERATION_GAP = 30;
    //gender INPUT options
    private final String MALE = "m";
    private final String FEMALE = "f";
    //OPTIONS for name generations
    final private int FEMALE_NAME = 1;
    final private int MALE_NAME = 2;
    final private int LAST_NAME = 3;
    int DEFAULT_GENERATIONS = 4;

    public DataGenerator(Person user) {
        //call the sequence
        //call the other constructor with 4 as the default
        this(user, 4);
    }


    public DataGenerator(Person userNode, int numGenerations) {
        this();
        this.userNode = new PersonNode(userNode);
        this.numGenerations = numGenerations;
    }


    public DataGenerator() {
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

    //for testing purposes
    public static void main(String args[]) {
        DataGenerator dg = new DataGenerator(new Person("jp1232", "jp1232", "Jacob", "Pettingill", "m", "Richard", "Khristine", null, 1994));
        dg.fillTrie();
        System.out.println("Success! Made it here?");
    }

    //get the random location of the person
    private LocationData.Location getLocation(){
        Random rand = new Random();
        int index = rand.nextInt(locData.data.length-1);
        return locData.data[index];
    }

    //Generate the necessary information about the person
    private int generateEventYear(int birthYear, String eventType){
        int year = 0;
        switch(eventType){
            case "birth":
                return year = birthYear;
            case "death":
                return year = birthYear + new Random().nextInt(20) + 60;
            case "marriage":
                return year = birthYear + new Random().nextInt(10) + 20;
            case "baptism":
                return year = birthYear + new Random().nextInt(15);
            default:
                return year = birthYear + new Random().nextInt(60) + 10;
        }
    }
    //generateEvents
    private void generateEvents(Person addBirth, String eventType, int eventYear){
        //new event ID
        String eventID = UUID.randomUUID().toString();
        LocationData.Location loc = getLocation();
        //parse the location data and add it to the event.
        String city = loc.city;
        String country = loc.country;
        double latitude =Double.parseDouble(loc.latitude);
        double longitude = Double.parseDouble(loc.longitude);
        //create the event
        Event event = new Event(eventID, addBirth.getDescendant(), addBirth.getPersonID(), latitude, longitude, country, city, eventType, eventYear);
        eventList.add(event);
    }

    private String generateName(int type) {
        String[] nameToGenerate = null;
        switch (type) {
            case FEMALE_NAME:
                nameToGenerate = femNames.data;
                break;
            case MALE_NAME:
                nameToGenerate = maleNames.data;
                break;
            case LAST_NAME:
                nameToGenerate = lastNames.data;
                break;
        }
        Random rand = new Random();
        int index = rand.nextInt(nameToGenerate.length - 1);
        //return the name generated
        return nameToGenerate[index];

    }

    //generate a new generation gap between 20 and 30 years
    private int genGenGap(int curYear){
        return curYear - (new Random().nextInt(MAX_GENERATION_GAP) + MIN_GENERATION_GAP);
    }

    //Recursively fill the parents up to the number of generations with data
    private void helperTrie(PersonNode curNode, int curLevel, int curYear) {
        if (curLevel == numGenerations) {
            return;
        }
        //give the parents ID's
        String uuidFather = UUID.randomUUID().toString();
        String uuidMother = UUID.randomUUID().toString();
        //set the mother and father's UUID's as the info there
        curNode.info.setFather(uuidFather);
        curNode.info.setMother(uuidMother);
        //fathers
        String firstNameFather = generateName(2);
        String lastNameFather = curNode.getInfo().getLastName();
        //set the mothers info
        String firstNameMother = generateName(1);
        String lastNameMother = generateName(3);
        //generate mother and father birth years
        int fatherBirthYear = genGenGap(curYear);
        int motherBirthYear = genGenGap(curYear);
                //set the parents to a new node so we can recurse
        curNode.father = new PersonNode(new Person(uuidFather, descendant, firstNameFather, lastNameFather, MALE, null, null, uuidMother, fatherBirthYear));
        curNode.mother = new PersonNode(new Person(uuidMother, descendant, firstNameMother, lastNameMother, FEMALE, null, null, uuidFather, motherBirthYear));
        //generate birth events
        String type = "birth";
        generateEvents(curNode.father.info, type, generateEventYear(fatherBirthYear, type));
        generateEvents(curNode.mother.info, type, generateEventYear(motherBirthYear, type));

        //marriage events
        type = "marriage";
        int year = generateEventYear(fatherBirthYear, type);
        generateEvents(curNode.mother.info, type, year);
        generateEvents(curNode.father.info, type, year);

        //baptism?
        if((new Random().nextInt() % 2) == 1) {
            type = "baptism";
            generateEvents(curNode.father.info, type, generateEventYear(fatherBirthYear, type));
            generateEvents(curNode.mother.info, type, generateEventYear(motherBirthYear, type));
        }


        //death
        if(curLevel >= 3){
            type = "death";
            generateEvents(curNode.father.info, type, generateEventYear(fatherBirthYear, type));
            generateEvents(curNode.mother.info, type, generateEventYear(motherBirthYear, type));
        }


        ancestors.add(curNode.father.info);
        ancestors.add(curNode.mother.info);
        //run the algorithm once more
        helperTrie(curNode.father, curLevel+1, fatherBirthYear);
        helperTrie(curNode.mother, curLevel+1, motherBirthYear);
    }

    //initializing data
    public void fillTrie() {
        if (userNode != null) {
            descendant = userNode.getInfo().getPersonID();
            if(userNode.info.getBirthyear() == 0){
                helperTrie(userNode, 0, Year.now().getValue() - (new Random().nextInt(50) + 10));
            }

        }
    }

    //Here is a person node
    class PersonNode {
        private PersonNode mother;
        private PersonNode father;
        private Person info;

        public PersonNode(Person data) {
            info = data;
        }

        public Person getInfo() {
            return info;
        }
    }

    //Data container to read in JSON data with name objects
    public class NameData {
        String[] data;

        public void print() {
            for (String name : data) {
                System.out.println(name);
            }
        }
    }
}
