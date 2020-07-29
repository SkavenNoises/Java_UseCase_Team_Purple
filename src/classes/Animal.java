package classes;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


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

    public Animal(String animalName, boolean isHealthy, Resource food, int quantityOfFoodEats, classes.Animal.AnimalSpecies animalSpecies) {
        animalStaticID++;
        this.animalID = animalStaticID;
        this.animalName = animalName;
        this.isHealthy = isHealthy;
        this.food = food;
        this.quantityOfFoodEats = quantityOfFoodEats;
        this.animalSpecies = animalSpecies;
        animalsHash.put(this.animalID, this);
        this.quantityOfMedsRequired = 0;
        this.medication = null;

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

    public static void printAllAnimals() {
        System.out.printf("%n%-10s %-32s %-15s %-20s %-10s %-20s %-10s %-32s",
                "AnimalID", "AnimalName", "isHealthy", "Food", "Quantity", "Medicine", "Quantity", "Species");
        for(Map.Entry<Integer, Animal> entry : animalsHash.entrySet()) {
            System.out.printf("%n%-10d %-32s %-15s %-20s %-10d %-20s %-10s %-32s",
                    entry.getKey(), entry.getValue().getAnimalName(),
                    entry.getValue().isHealthy() ? "Healthy" : "Not Healthy",
                    entry.getValue().getFood().getResourceName(), entry.getValue().getQuantityOfFoodEats(),
                    (entry.getValue().getMedication() == null) ? "None" : entry.getValue().getMedication().getResourceName(),
                    (entry.getValue().getQuantityOfMedsRequired() == 0) ? "None": entry.getValue().getQuantityOfMedsRequired(),
                    entry.getValue().getAnimalSpecies());
        }
    }

    private static Resource resourceGet;

    public static void addNewAnimal() {
        Scanner inputText = new Scanner(System.in);
        Scanner inputNumber = new Scanner(System.in);
        System.out.print("Name of the animal: ");
        String animalName = inputText.nextLine();

        boolean isAnimalHealthy = true;
        boolean loopWhile1 = true;
        while(loopWhile1) {
            System.out.print("Is the animal healthy? Y or N? ");
            String animalHealth = inputText.nextLine();
            if(animalHealth.toUpperCase().equals("Y")) {
                loopWhile1 = false;
            } else if (animalHealth.toUpperCase().equals("N")) {
                isAnimalHealthy = false;
                loopWhile1 = false;
            } else {
                System.out.println("Please select only Y or N");
            }
        }

        boolean loopWhile2 = true;
        Resource food = resourceGet;
        int foodQuantity = 0;
        while(loopWhile2) {
            System.out.print("Enter the id of the food you want to assign to this animal: ");
            int foodID = Integer.parseInt(inputNumber.next());
            if(Resource.resourcesHash.containsKey(foodID) && Resource.resourcesHash.get(foodID).getResourceType().equals(Resource.ResourceType.Food)) {
                food = Resource.resourcesHash.get(foodID);
                System.out.print("Enter the quantity of food this animal consumes in a day: ");
                foodQuantity = Integer.parseInt(inputNumber.next());
                loopWhile2 = false;
            } else {
                System.out.println("Our warehouse does not have this food. Please select a different id.");
            }
        }

        boolean loopWhile3 = true;
        Resource medication = resourceGet;
        int medQuantity = 0;
        while(loopWhile3) {
            System.out.print("Is the animal on medication? Y or N? ");
            String animalMedication = inputText.nextLine();
            if(animalMedication.toUpperCase().equals("Y")) {
                System.out.print("Enter the id of the medicine you want to assign to this animal: ");
                int medID = Integer.parseInt(inputNumber.next());
                if(Resource.resourcesHash.containsKey(medID) && Resource.resourcesHash.get(medID).getResourceType().equals(Resource.ResourceType.Meds)) {
                    medication = Resource.resourcesHash.get(medID);
                    System.out.print("Enter the quantity of medicine required in a day: ");
                    medQuantity = Integer.parseInt(inputNumber.next());
                    loopWhile3 = false;
                } else {
                    System.out.println("Our warehouse does not have this kind of medicine. Please select a different id.");
                }
            } else if (animalMedication.toUpperCase().equals("N")) {
                loopWhile3 = false;
            } else {
                System.out.println("Please select only Y or N");
            }

        }

        int counterSpecies = 0;
        for (Animal.AnimalSpecies animalSpecies : Animal.AnimalSpecies.values()) {
            System.out.println("Press " + counterSpecies + " to choose the Species, " + animalSpecies);
            counterSpecies++;
        }
        int inputSpeciesCounter = Integer.parseInt(inputNumber.next());

        if(medQuantity == 0) {
            Animal animal = new Animal(animalName, isAnimalHealthy, food, foodQuantity, (Animal.AnimalSpecies) Array.get(Animal.AnimalSpecies.values(), inputSpeciesCounter));
        } else {
            Animal animal = new Animal(animalName, isAnimalHealthy, food, foodQuantity, medication, medQuantity, (Animal.AnimalSpecies) Array.get(Animal.AnimalSpecies.values(), inputSpeciesCounter));
        }
        System.out.println("New animal added!");
    }

    public static void averageExpensiveFood() {
        HashMap<AnimalSpecies, Double> animalSpeciesListHashMap = new HashMap<>();
        int totalAnimals = Animal.animalsHash.size();
        double totalCostOfAllAnimals = 0;
        for(Animal.AnimalSpecies animalSpecies : Animal.AnimalSpecies.values()) {
            double totalAverageCostPerDayPerAnimal = 0;
            double totalCostPerDay = 0;
            int counter = 0;
            for(Map.Entry<Integer, Animal> animalSet : animalsHash.entrySet()) {
                if(animalSet.getValue().getAnimalSpecies().equals(animalSpecies)) {
                    counter++;
                    totalCostPerDay += (animalSet.getValue().getQuantityOfFoodEats() * animalSet.getValue().getFood().getResourcePrice());
                    totalCostOfAllAnimals += (animalSet.getValue().getQuantityOfFoodEats() * animalSet.getValue().getFood().getResourcePrice());
                }
            }
            if(counter != 0) {
                totalAverageCostPerDayPerAnimal = totalCostPerDay / counter;
            }

            animalSpeciesListHashMap.put(animalSpecies, totalAverageCostPerDayPerAnimal);
        }

        AnimalSpecies expensiveSpecies = Collections.max(animalSpeciesListHashMap.entrySet(), Map.Entry.comparingByValue()).getKey();
        System.out.printf("The average cost to feed all the animals in the zoo is %.2f€.%n", totalCostOfAllAnimals/totalAnimals);
        System.out.printf("%s is the most expensive species considering the average of the amount of food eaten and price of food. The average cost per day per animal is %.2f€.", expensiveSpecies, Collections.max(animalSpeciesListHashMap.values()));

    }

    public static void averageExpensiveMedicine() {
        HashMap<AnimalSpecies, Double> animalSpeciesListHashMap = new HashMap<>();
        int totalAnimals = Animal.animalsHash.size();
        double totalCostOfAllAnimals = 0;
        for(Animal.AnimalSpecies animalSpecies : Animal.AnimalSpecies.values()) {
            double totalAverageCostPerDayPerAnimal = 0;
            double totalCostPerDay = 0;
            int counter = 0;
            for(Map.Entry<Integer, Animal> animalSet : animalsHash.entrySet()) {
                if(animalSet.getValue().getAnimalSpecies().equals(animalSpecies)) {
                    counter++;
                    if(animalSet.getValue().getQuantityOfMedsRequired() > 0) {
                        totalCostPerDay += (animalSet.getValue().getQuantityOfMedsRequired() * animalSet.getValue().getMedication().getResourcePrice());
                        totalCostOfAllAnimals += (animalSet.getValue().getQuantityOfMedsRequired() * animalSet.getValue().getMedication().getResourcePrice());
                    }

                }
            }
            if(counter != 0) {
                totalAverageCostPerDayPerAnimal = totalCostPerDay / counter;
            }

            animalSpeciesListHashMap.put(animalSpecies, totalAverageCostPerDayPerAnimal);
        }

        AnimalSpecies expensiveSpecies = Collections.max(animalSpeciesListHashMap.entrySet(), Map.Entry.comparingByValue()).getKey();
        System.out.printf("The average cost spent on medications of all the animals inside habitats in the zoo is %.2f€.%n", totalCostOfAllAnimals/totalAnimals);
        System.out.printf("%s is the most expensive species considering the average of the amount of medicine taken and price of medicine. The average cost per day per animal is %.2f€.", expensiveSpecies, Collections.max(animalSpeciesListHashMap.values()));

    }

}
