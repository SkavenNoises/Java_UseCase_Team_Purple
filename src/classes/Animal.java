package classes;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


public class Animal {



    private int animalID;
    private static int animalStaticID = 0;
    private String animalName;
    private boolean isHealthy;
    private Resource food;
    private int quantityOfFoodEats;
    private Resource medication;
    private int quantityOfMedsRequired;

    //Log of meds given, time and quantity
    private HashMap<LocalDateTime, HashMap<Resource, Integer>> medsGivenAt = new HashMap<>();

    //Log of food given, time and quantity
    private HashMap<LocalDateTime, HashMap<Resource, Integer>> foodGivenAt = new HashMap<>();

    public enum AnimalSpecies {
        Tiger, Elephant, Panda, Bear, Monkey, Others
    }

    private AnimalSpecies animalSpecies;

    static HashMap<Integer, Animal> animalsHash = new HashMap<>();

    public Animal() { // default constructor
        this.animalName = "kitty";
        this.animalID = 1;
    }
    public Animal(String animalName, boolean isHealthy, Resource food, int quantityOfFoodEats, Resource medication, int quantityOfMedsRequired, classes.Animal.AnimalSpecies animalSpecies) {
        animalStaticID++;
        this.animalID = animalStaticID;
        this.animalName = animalName;
        this.isHealthy = isHealthy;
        this.food = food;
        this.quantityOfFoodEats = quantityOfFoodEats;
        this.medication = medication;
        this.quantityOfMedsRequired = quantityOfMedsRequired;
        this.animalSpecies = animalSpecies;
        animalsHash.put(this.animalID, this);

    }

    public int getAnimalID() {
        return animalID;
    }

    public void setAnimalID(int animalID) {
        this.animalID = animalID;
    }

    public String getAnimalName() {
        return animalName;
    }

    public void setAnimalName(String animalName) {
        this.animalName = animalName;
    }

    public boolean isHealthy() {
        return isHealthy;
    }

    public void setHealthy(boolean healthy) {
        isHealthy = healthy;
    }

    public Resource getFood() {
        return food;
    }

    public void setFood(Resource food) {
        this.food = food;
    }

    public int getQuantityOfFoodEats() {
        return quantityOfFoodEats;
    }

    public void setQuantityOfFoodEats(int quantityOfFoodEats) {
        this.quantityOfFoodEats = quantityOfFoodEats;
    }

    public Resource getMedication() {
        return medication;
    }

    public void setMedication(Resource medication) {
        this.medication = medication;
    }

    public int getQuantityOfMedsRequired() {
        return quantityOfMedsRequired;
    }

    public void setQuantityOfMedsRequired(int quantityOfMedsRequired) {
        this.quantityOfMedsRequired = quantityOfMedsRequired;
    }

    public HashMap<LocalDateTime, HashMap<Resource, Integer>> getMedsGivenAt() {
        return medsGivenAt;
    }

    public void setMedsGivenAt(HashMap<LocalDateTime, HashMap<Resource, Integer>> medsGivenAt) {
        this.medsGivenAt = medsGivenAt;
    }

    public HashMap<LocalDateTime, HashMap<Resource, Integer>> getFoodGivenAt() {
        return foodGivenAt;
    }

    public void setFoodGivenAt(HashMap<LocalDateTime, HashMap<Resource, Integer>> foodGivenAt) {
        this.foodGivenAt = foodGivenAt;
    }

    public AnimalSpecies getAnimalSpecies() {
        return animalSpecies;
    }

    public void setAnimalSpecies(AnimalSpecies animalSpecies) {
        this.animalSpecies = animalSpecies;
    }

    public static void recordFoodGivenAt(Animal animal) {
        //Gets the time when the method is called;
        LocalDateTime now = LocalDateTime.now();

        HashMap<Resource, Integer> quantityResourceGiven = new HashMap<>();

        quantityResourceGiven.put(animal.getFood(), animal.getQuantityOfFoodEats());

        animal.getFoodGivenAt().put(now, quantityResourceGiven);
        animal.setFoodGivenAt(animal.getFoodGivenAt());
        Resource.subtractResource(animal.getFood(), animal.getQuantityOfFoodEats());
    }

    public static void recordMedsGivenAt(Animal animal) {
        //Gets the time when the method is called;
        LocalDateTime now = LocalDateTime.now();

        HashMap<Resource, Integer> quantityResourceGiven = new HashMap<>();

        quantityResourceGiven.put(animal.getMedication(), animal.getQuantityOfMedsRequired());

        animal.getMedsGivenAt().put(now, quantityResourceGiven);
        animal.setMedsGivenAt(animal.getMedsGivenAt());
        Resource.subtractResource(animal.getMedication(), animal.getQuantityOfMedsRequired());
    }

    public int totalFoodEaten() {
        int totalFood = 0;
        for(Map.Entry<LocalDateTime, HashMap<Resource, Integer>> entry : this.getFoodGivenAt().entrySet()) {
            for(Map.Entry<Resource, Integer> entryValue : entry.getValue().entrySet()) {
                totalFood += entryValue.getValue();
            }
        }
        return totalFood;

    }

    public int totalMedicineTaken() {
        int totalMedication = 0;
        for(Map.Entry<LocalDateTime, HashMap<Resource, Integer>> entry : this.getMedsGivenAt().entrySet()) {
            for(Map.Entry<Resource, Integer> entryValue : entry.getValue().entrySet()) {
                totalMedication += entryValue.getValue();
            }
        }
        return totalMedication;

    }

}
