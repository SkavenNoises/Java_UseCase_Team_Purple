package classes;

import java.util.ArrayList;
import java.util.HashMap;

public class AnimalEnclosure {
    private static int enclosureStaticID = 0;
    private int enclosureID;
    private String enclosureName;
    private ArrayList<Animal> animalListInEnclosure = new ArrayList<>();
    private boolean isClean;
    private boolean isEnriched;
    private boolean needsModifications;

    static HashMap<Integer, AnimalEnclosure> enclosureHash = new HashMap<>();

    public AnimalEnclosure(String enclosureName, boolean isClean, boolean isEnriched, boolean needsModifications) {
        enclosureStaticID++;
        this.enclosureID = enclosureStaticID;
        this.enclosureName = enclosureName;
        this.isClean = isClean;
        this.isEnriched = isEnriched;
        this.needsModifications = needsModifications;
        enclosureHash.put(this.enclosureID, this);
    }

    public int getEnclosureID() {
        return enclosureID;
    }

    public void setEnclosureID(int enclosureID) {
        this.enclosureID = enclosureID;
    }

    public String getEnclosureName() {
        return enclosureName;
    }

    public void setEnclosureName(String enclosureName) {
        this.enclosureName = enclosureName;
    }

    public boolean isClean() {
        return isClean;
    }

    public void setClean(boolean clean) {
        isClean = clean;
    }

    public boolean isEnriched() {
        return isEnriched;
    }

    public void setEnriched(boolean enriched) {
        isEnriched = enriched;
    }

    public boolean isNeedsModifications() {
        return needsModifications;
    }

    public void setNeedsModifications(boolean needsModifications) {
        this.needsModifications = needsModifications;
    }

    public ArrayList<Animal> getAnimalListInEnclosure() {
        return animalListInEnclosure;
    }

    public void setAnimalListInEnclosure(ArrayList<Animal> animalListInEnclosure) {
        this.animalListInEnclosure = animalListInEnclosure;
    }

    public void removeAnimal(int animalId) {
        if(this.getAnimalListInEnclosure().contains(Animal.animalsHash.get(animalId))) {
            this.getAnimalListInEnclosure().remove(Animal.animalsHash.get(animalId));
        } else {
            System.out.println("Animal not in this enclosure!");
        }
    }

    public void addAnimal(int animalId) {
        this.getAnimalListInEnclosure().add(Animal.animalsHash.get(animalId));
        System.out.println("Animal Added");
    }

}

