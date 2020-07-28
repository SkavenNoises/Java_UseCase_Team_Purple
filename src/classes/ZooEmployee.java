package classes;

import exceptions.AnimalNotFoundException;

import java.util.ArrayList;

public class ZooEmployee {
    private static int employeeIDCounter = 0;

    private int employeeID;
    private boolean activeEmployee;
    private ArrayList<AnimalEnclosure> employeeEnclosures;

    public ZooEmployee() {
        employeeIDCounter++;
        this.employeeID = employeeIDCounter;
        this.activeEmployee = true;
    }

    public void sendAnimalToVet(int animalID) throws AnimalNotFoundException {
        // Ensuring that the animal is present in the enclosure
        boolean animalFound = false;
        for (Animal animal : employeeEnclosures.get(0).getAnimalListInEnclosure()) {
            if (animal.getAnimalID() == animalID) {
                // TODO - add animal to the vetClinic


                // Removing the animal from the enclosure
                employeeEnclosures.get(0).removeAnimal(animalID);

                // Setting that the animal has been found in the enclosure
                animalFound = true;
            }
        }

        if (!animalFound) {
            throw new AnimalNotFoundException("Animal not found");
        }
    }

    public void addEnclosureToEmployee(AnimalEnclosure animalEnclosure) {
        this.employeeEnclosures.add(animalEnclosure);
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public boolean isActiveEmployee() {
        return activeEmployee;
    }

    public void setActiveEmployee(boolean activeEmployee) {
        this.activeEmployee = activeEmployee;
    }

    public ArrayList<AnimalEnclosure> getEmployeeEnclosures() {
        return employeeEnclosures;
    }
}
