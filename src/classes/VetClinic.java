package classes;

import java.util.*;

public class VetClinic {

    private ArrayList<Animal> sickAnimalList = new ArrayList<>();
    private ArrayList<Animal> specialCare = new ArrayList<>();
    //HashMap<Animal.AnimalSpecies, Integer> index
    public VetClinic(ArrayList<Animal> sickAnimalList, ArrayList<Animal> specialCare) {
        this.sickAnimalList = sickAnimalList;
        this.specialCare = specialCare;
    }

//SickAnimal Size/Add/Remove/List

    public int getAmountOfSickAnimal() {
        return sickAnimalList.size();
    }

    public void addSickAnimalList(Animal animal){
        this.sickAnimalList.add(animal);
    }

    public void removeSickAnimal(Animal animal){
        this.sickAnimalList.remove(animal);
    }

    public ArrayList<Animal> getSickAnimalList() {
        return sickAnimalList;
    }

    //SpecialCare Size/Add/Remove/List

    public int getAmountOfSpecialCare() {
        return specialCare.size();
    }

    public void addSpecialCare(Animal animal){
        this.specialCare.add(animal);
    }

    public void removeSpecialCare(Animal animal){
        this.specialCare.remove(animal);
    }

    public ArrayList<Animal> getSpecialCare() {
        return specialCare;
    }

    //Hashmap for How many times which Species (Enum from the Animal Class) in Vet

    public HashMap<Animal.AnimalSpecies, Integer> getSickAnimalsIndex() {
        HashMap<Animal.AnimalSpecies, Integer> index = new HashMap<>();
        sickAnimalList.forEach(animal -> {
            int count = index.getOrDefault(animal.getAnimalSpecies(), 0) + 1;
            index.put(animal.getAnimalSpecies(), count);
        });
        return index;
    }

    // Print

    @Override
    public String toString() {
        return "VetClinic{" +
                "sickAnimalList=" + sickAnimalList.size() +
                ", specialCare=" + specialCare.size() +
                '}';
    }


}


