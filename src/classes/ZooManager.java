package classes;

import exceptions.AnimalNotFoundException;
import exceptions.AnimalPurchaseException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class ZooManager {
    private HashMap<Integer, ZooEmployee> employeeHashMap = new HashMap<>();;

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

    // TODO - Print to file a report
    // Prints all relevant information to a text file
    public void printReport() {
        String workingDir = System.getProperty("User.dir");

        try {
            File reportFile = new File(workingDir + File.separator + "Zoo_Report.txt");
            FileWriter writer = new FileWriter(reportFile);

            // Constructing what to write before writing it to file
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("\n+").append("-".repeat(35)).append("+");
            stringBuilder.append(String.format("%20s", "Zoo Report"));
            stringBuilder.append("\n+").append("-".repeat(35)).append("+");

            // Adding a list of all employees and their enclosures
            stringBuilder.append("\nEmployees:");

            String employeeHeader = String.format("\n%3s %20s %20s", "ID", "Status", "Enclosure");
            stringBuilder.append("\n").append(employeeHeader);
            stringBuilder.append("\n").append("-".repeat(employeeHeader.length()));

            for (ZooEmployee zooEmployee : employeeHashMap.values()){
                String employeeStatus = "";
                if (zooEmployee.isActiveEmployee()) {
                    employeeStatus = "Active";
                } else {
                    employeeStatus = "Fired";
                }

                stringBuilder.append("\n").append(String.format("\n%3s %20s %20s", zooEmployee.getEmployeeID(), employeeStatus, zooEmployee.getEmployeeEnclosures().get(0).getEnclosureID()));
            }

            // TODO - Add other elements to add to the report file

            // Writing the text to file
            writer.write(stringBuilder.toString());
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public HashMap<Integer, ZooEmployee> getEmployeeHashMap() {
        return employeeHashMap;
    }
}
