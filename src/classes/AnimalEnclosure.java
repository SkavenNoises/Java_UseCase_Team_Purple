package classes;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AnimalEnclosure {
    private static int enclosureStaticID = 0;
    private int enclosureID;
    private String enclosureName;
    private ArrayList<Animal> animalListInEnclosure = new ArrayList<>();
    private boolean isClean;
    private boolean isEnriched;
    private boolean needsModifications;
    private Animal.AnimalSpecies enclosureSpecies;
    //Log of time cleaned and the Employee
    private HashMap<LocalDateTime, ZooEmployee> cleanEnclosure = new HashMap<>();
    //Log of time enriched and the Employee
    private HashMap<LocalDateTime, ZooEmployee> enrichEnclosure = new HashMap<>();
    //Log of time modified and the Employee
    private HashMap<LocalDateTime, ZooEmployee> modifyEnclosure = new HashMap<>();

    static HashMap<Integer, AnimalEnclosure> enclosureHash = new HashMap<>();

    public AnimalEnclosure(String enclosureName, boolean isClean, boolean isEnriched, boolean needsModifications, Animal.AnimalSpecies animalSpecies) {
        enclosureStaticID++;
        this.enclosureID = enclosureStaticID;
        this.enclosureName = enclosureName;
        this.isClean = isClean;
        this.isEnriched = isEnriched;
        this.needsModifications = needsModifications;
        this.enclosureSpecies = animalSpecies;
        enclosureHash.put(this.enclosureID, this);
    }

    public Animal.AnimalSpecies getEnclosureSpecies() {
        return enclosureSpecies;
    }

    public boolean hasAnimal(Animal animal) {
        boolean containsAnimal = false;

        for (Animal a : this.animalListInEnclosure) {
            if (a.getAnimalID() == animal.getAnimalID()) {
                containsAnimal = true;
                break;
            }
        }

        return containsAnimal;
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

    public HashMap<LocalDateTime, ZooEmployee> getCleanEnclosure() {
        return cleanEnclosure;
    }

    public void setCleanEnclosure(HashMap<LocalDateTime, ZooEmployee> cleanEnclosure) {
        this.cleanEnclosure = cleanEnclosure;
    }

    public HashMap<LocalDateTime, ZooEmployee> getEnrichEnclosure() {
        return enrichEnclosure;
    }

    public void setEnrichEnclosure(HashMap<LocalDateTime, ZooEmployee> enrichEnclosure) {
        this.enrichEnclosure = enrichEnclosure;
    }

    public HashMap<LocalDateTime, ZooEmployee> getModifyEnclosure() {
        return modifyEnclosure;
    }

    public void setModifyEnclosure(HashMap<LocalDateTime, ZooEmployee> modifyEnclosure) {
        this.modifyEnclosure = modifyEnclosure;
    }

    public void removeAnimal(int animalId) {
        if(this.getAnimalListInEnclosure().contains(Animal.animalsHash.get(animalId))) {
            this.getAnimalListInEnclosure().remove(Animal.animalsHash.get(animalId));
        } else {
            System.out.println("Animal not in this enclosure!");
        }
    }

    public void addAnimal(Animal animal) {
        this.getAnimalListInEnclosure().add(animal);
        System.out.println("Animal Added");
    }

    public static void printAnimalEnclosureRecords() {
        for(Map.Entry<Integer, AnimalEnclosure> entry : enclosureHash.entrySet()) {
            System.out.printf("%n%nEnclosureID: %d; Name: %s; iClean: %s; Enriched: %s; Modifications Necessary: %s; Species: %s",
                    entry.getValue().getEnclosureID(), entry.getValue().getEnclosureName(),
                    (entry.getValue().isClean ? "Yes" : "No"),
                    (entry.getValue().isEnriched ? "Yes" : "No"),
                    (entry.getValue().isNeedsModifications() ? "Yes" : "No"),
                    entry.getValue().getEnclosureSpecies());

            System.out.printf("%n%-10s %-32s %-15s %-20s %-10s %-20s %-10s %-32s",
                    "AnimalID", "AnimalName", "isHealthy", "Food", "Quantity", "Medicine", "Quantity", "Species");
            for(int i = 0; i <entry.getValue().getAnimalListInEnclosure().size(); i++) {
                Animal animal  = entry.getValue().getAnimalListInEnclosure().get(i);
                System.out.printf("%n%-10d %-32s %-15s %-20s %-10d %-20s %-10d %-32s",
                        animal.getAnimalID(), animal.getAnimalName(),
                        animal.isHealthy() ? "Healthy" : "Not Healthy",
                        animal.getFood().getResourceName(), animal.getQuantityOfFoodEats(),
                        animal.getMedication().getResourceName(), animal.getQuantityOfMedsRequired(),
                        animal.getAnimalSpecies());
            }

        }
    }

    public void recordModifications(LocalDateTime dateTimeRecord, int zooEmployeeId) {
        if (ZooEmployee.zooEmployeeHash.containsKey(zooEmployeeId)) {
            this.getModifyEnclosure().put(dateTimeRecord, ZooEmployee.zooEmployeeHash.get(zooEmployeeId));
            this.setNeedsModifications(false);
        } else {
            System.out.println("No such employee exists!");
        }
    }

    public void recordEnriching(LocalDateTime dateTimeRecord, int zooEmployeeId) {
        if (ZooEmployee.zooEmployeeHash.containsKey(zooEmployeeId)) {
            this.getEnrichEnclosure().put(dateTimeRecord, ZooEmployee.zooEmployeeHash.get(zooEmployeeId));
            this.setEnriched(true);

        } else {
            System.out.println("No such employee exists!");
        }
    }

    public void recordCleaning(LocalDateTime dateTimeRecord, int zooEmployeeId) {
        if (ZooEmployee.zooEmployeeHash.containsKey(zooEmployeeId)) {
            this.getCleanEnclosure().put(dateTimeRecord, ZooEmployee.zooEmployeeHash.get(zooEmployeeId));
            this.setClean(true);

        } else {
            System.out.println("No such employee exists!");
        }
    }

    public static void checkEnclosureMaintenance() {
        System.out.printf("%n%n%-15s %-32s %-20s %-20s %-20s %-20s %-20s %-20s %-20s %-20s %-20s %-20s",
                "Enclosure ID", "Name", "Species", "isClean", "Last Cleaned", "byEmployeeID", "isEnriched", "Last Enriched", "byEmployeeID", "Needs Modifications", "Last Modified", "byEmployeeID" );
        for(Map.Entry<Integer, AnimalEnclosure> entry : enclosureHash.entrySet()) {
            LocalDateTime cleanTime = null;
            ZooEmployee cleanEmployee = null;
            for(Map.Entry<LocalDateTime, ZooEmployee> entryClean : entry.getValue().getCleanEnclosure().entrySet()) {
                cleanTime = entryClean.getKey();
                cleanEmployee = entryClean.getValue();
            }
            LocalDateTime enrichTime = null;
            ZooEmployee enrichEmployee = null;
            for(Map.Entry<LocalDateTime, ZooEmployee> entryEnrich : entry.getValue().getEnrichEnclosure().entrySet()) {
                enrichTime = entryEnrich.getKey();
                enrichEmployee = entryEnrich.getValue();
            }
            LocalDateTime modifyTime = null;
            ZooEmployee modifyEmployee = null;
            for(Map.Entry<LocalDateTime, ZooEmployee> entryModify : entry.getValue().getModifyEnclosure().entrySet()) {
                modifyTime = entryModify.getKey();
                modifyEmployee = entryModify.getValue();
            }
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

            System.out.printf("%n%-15d %-32s %-20s %-20s %-20s %-20s %-20s %-20s %-20s %-20s %-20s %-20s",
                    entry.getValue().getEnclosureID(), entry.getValue().getEnclosureName(),
                    entry.getValue().getEnclosureSpecies(),
                    (entry.getValue().isClean() ? "Yes" : "No"),
                    cleanTime == null ? "Not cleaned" : cleanTime.format(dateFormatter), cleanEmployee == null ? "None" : cleanEmployee.getEmployeeID(),
                    (entry.getValue().isEnriched() ? "Yes" : "No"),
                    enrichTime == null ? "Not enriched" : enrichTime.format(dateFormatter), enrichEmployee == null ? "None" : enrichEmployee.getEmployeeID(),
                    (entry.getValue().isNeedsModifications() ? "Yes" : "No"),
                    modifyTime == null ? "Not modified" : modifyTime.format(dateFormatter), modifyEmployee == null ? "None" : modifyEmployee.getEmployeeID());
        }
    }

}

