package classes;

import exceptions.AnimalNotFoundException;
import exceptions.AnimalPurchaseException;

import java.util.ArrayList;
import java.util.HashMap;

public class ZooManager {
    private HashMap<Integer, ZooEmployee> employeeHashMap = new HashMap<>();

    public void hireEmployee(ZooEmployee zooEmployee) {
        employeeHashMap.put(zooEmployee.getEmployeeID(), zooEmployee);
        zooEmployee.setActiveEmployee(true);
    }

    public void fireEmployee(ZooEmployee zooEmployee) {
        if (this.employeeHashMap.containsKey(zooEmployee.getEmployeeID())) {
            zooEmployee.setActiveEmployee(false);
            employeeHashMap.remove(zooEmployee.getEmployeeID());
        } else {
            System.out.println("No employee found");
        }
    }

    public void purchaseAnimal(Animal animal) throws AnimalPurchaseException {
        // Finding out which enclosure contains the correct species
        boolean animalFound = false;
        for (ZooEmployee zooEmployee : employeeHashMap.values()) {
            if (zooEmployee.getEmployeeEnclosures().get(0).getEnclosureSpecies().equals(animal.getAnimalSpecies())) {
                zooEmployee.getEmployeeEnclosures().get(0).getAnimalListInEnclosure().add(animal);
                animalFound = true;
            }
        }

        if (!animalFound) {
            throw new AnimalPurchaseException("Cannot purchase animal");
        }
    }

    public String getAnimalHealth(int animalID) throws AnimalNotFoundException {
        // Finding the correct enclosure that contains that specific animal and returning whether or not the animal is healthy
        String healthStatus = "";

        for (ZooEmployee zooEmployee : employeeHashMap.values()) {
            ArrayList<Animal> animalArrayList = zooEmployee.getEmployeeEnclosures().get(0).getAnimalListInEnclosure();

            for (Animal animal : animalArrayList) {
                if (animal.getAnimalID() == animalID) {
                    if (animal.isHealthy()) {
                        healthStatus = "Healthy";
                    } else {
                        healthStatus = "Sick";
                    }
                }
            }
        }

        // Ensuring that the animal has been found
        if (healthStatus.isEmpty()) {
            throw new AnimalNotFoundException("Cannot locate animal");
        } else {
            return healthStatus;
        }
    }

    public HashMap<Integer, ZooEmployee> getEmployeeHashMap() {
        return employeeHashMap;
    }
}
