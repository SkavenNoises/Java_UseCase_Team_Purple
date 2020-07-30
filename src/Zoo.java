import classes.*;
import exceptions.AnimalNotFoundException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static classes.AnimalEnclosure.enclosureHash;

public class Zoo {
    public static void main(String[] args) {
        // Dummy Objects
        ZooManager zooManager = new ZooManager();
        VetClinic vetClinic = new VetClinic();

        zooManager.hireEmployee(new ZooEmployee());
        zooManager.hireEmployee(new ZooEmployee());
        zooManager.hireEmployee(new ZooEmployee());

        zooManager.getEmployeeHashMap().get(1).addEnclosureToEmployee(new AnimalEnclosure("Panda Pagoda", true, true, false, Animal.AnimalSpecies.Panda));
        zooManager.getEmployeeHashMap().get(2).addEnclosureToEmployee(new AnimalEnclosure("Bear Club", true, true, false, Animal.AnimalSpecies.Bear));
        zooManager.getEmployeeHashMap().get(3).addEnclosureToEmployee(new AnimalEnclosure("Monkey Business", true, true, false, Animal.AnimalSpecies.Monkey));

        zooManager.getEmployeeHashMap().get(1).getEmployeeEnclosures().get(0).addAnimal(new Animal("Po", true, new Resource("Bamboo", 20.99, "Twigs'r'us", 50, Resource.ResourceType.Food), 5, new Resource("Dexamethasone", 23.99, "Big Pharma", 23, Resource.ResourceType.Meds), 1, Animal.AnimalSpecies.Panda));
        zooManager.getEmployeeHashMap().get(2).getEmployeeEnclosures().get(0).addAnimal(new Animal("Baloo", true, new Resource("Honey", 35.99, "The hive Co.", 83, Resource.ResourceType.Food), 5, new Resource("Prozac", 25.99, "Big Pharma", 50, Resource.ResourceType.Meds), 1, Animal.AnimalSpecies.Bear));
        zooManager.getEmployeeHashMap().get(3).getEmployeeEnclosures().get(0).addAnimal(new Animal("Louis", true, new Resource("Bananas", 20.99, "Fruit Inc.", 42, Resource.ResourceType.Food), 3, new Resource("Cetirizine Hydrochloride", 15.99, "Big Pharma", 17, Resource.ResourceType.Meds), 1, Animal.AnimalSpecies.Monkey));

        zooManager.getEmployeeHashMap().get(1).AddVetCall(zooManager.getEmployeeHashMap().get(1).getEmployeeEnclosures().get(0).getAnimalListInEnclosure().get(0));
        zooManager.getEmployeeHashMap().get(2).AddVetCall(zooManager.getEmployeeHashMap().get(2).getEmployeeEnclosures().get(0).getAnimalListInEnclosure().get(0));

        vetClinic.addSpecialCare(new Animal("Coughing George", false, new Resource("Apples", 4.99, "Fruit Inc", 20, Resource.ResourceType.Food), 20, new Resource("Steroids", 20.98, "Big Pharma", 29, Resource.ResourceType.Meds), 2,  Animal.AnimalSpecies.Monkey));

        var derek = new Animal("Derek", false, new Resource("Vegetarian", 10.99, "XYZ AB",

                10, Resource.ResourceType.Food), 1,

                new Resource("Painkiller X", 8.99, "Unknown Pharmaceuticals", 59, Resource.ResourceType.Meds),

                1, Animal.AnimalSpecies.Bear);

        var jack = new Animal("Jack", false, new Resource("Vegetarian", 10.99, "XYZ AB",

                10, Resource.ResourceType.Food), 1,

                new Resource("Painkiller X", 8.99, "Unknown Pharmaceuticals", 59, Resource.ResourceType.Meds),

                1, Animal.AnimalSpecies.Monkey);

        var sickAnimalList = vetClinic.getSickAnimalList();
        vetClinic.addSickAnimalList(derek);
        vetClinic.addSickAnimalList(jack);
        // Init scanner obj
        Scanner scanner = new Scanner(System.in);

        // App lifecycle
        boolean endLoop = false;

        while (!endLoop) {
            try {
                // Display Menu
                displayUserMenu();

                // Holding the user selection
                int userSelection = Integer.parseInt(scanner.next());

                // Catching out of bounds selection
                if (userSelection < 0 || userSelection > 12) {
                    throw new NumberFormatException();

                } else {
                    // Menu options
                    switch (userSelection) {
                        case 0:
                            System.out.println("Program closing...");
                            endLoop = true;
                            break;

                        case 1: // List all the animals
                            System.out.printf("%n%-10s %-32s %-20s %-32s %-15s %-32s %-15s %-32s %-32s",
                                    "AnimalID", "AnimalName", "isHealthy", "Food", "Quantity", "Medicine", "Quantity", "Species", "Location");
                            for (Map.Entry<Integer, Animal> entry : Animal.animalsHash.entrySet()) {
                                String animalLocation = null;
                                for (Map.Entry<Integer, AnimalEnclosure> entryEnclosure : enclosureHash.entrySet()) {
                                    if (entryEnclosure.getValue().getAnimalListInEnclosure().contains(entry.getValue())) {
                                        animalLocation = "Enclosure: " + entryEnclosure.getValue().getEnclosureName();
                                    }
                                }

                                if (vetClinic.getSickAnimalList().contains(entry.getValue())) {
                                    animalLocation = "Vet Clinic Sick Ward";
                                }

                                if (vetClinic.getSpecialCare().contains(entry.getValue())) {
                                    animalLocation = "Vet Clinic Special Care Ward";
                                }

                                System.out.printf("%n%-10d %-32s %-20s %-32s %-15s %-32s %-15s %-32s %-32s",
                                        entry.getKey(), entry.getValue().getAnimalName(),
                                        entry.getValue().isHealthy() ? "Healthy" : "Not Healthy",
                                        entry.getValue().getFood().getResourceName(), entry.getValue().getQuantityOfFoodEats(),
                                        (entry.getValue().getMedication() == null) ? "None" : entry.getValue().getMedication().getResourceName(),
                                        (entry.getValue().getQuantityOfMedsRequired() == 0) ? "None" : entry.getValue().getQuantityOfMedsRequired(),
                                        entry.getValue().getAnimalSpecies(), animalLocation);
                            }
                            break;

                        case 2: // Add a new animal
                            Animal.addNewAnimal();
                            break;

                        case 3: // Zoo employee maintains the habitat
                            AnimalEnclosure.checkEnclosureMaintenance();
                            break;

                        case 4: // Display which animal is eating the most food
                            Animal.averageExpensiveFood();
                            break;

                        case 5: // Display which animal is using the most meds
                            Animal.averageExpensiveMedicine();
                            break;

                        case 6: // Sending an animal to the vet
                            // Listing all the animals so the user has an onscreen representation of all the animals
                            ArrayList<Animal> animalArrayList = new ArrayList<>();

                            // Putting all the animals into one arraylist for data manipulation
                            for (ZooEmployee zooEmployee : zooManager.getEmployeeHashMap().values()) {
                                for (AnimalEnclosure animalEnclosure : zooEmployee.getEmployeeEnclosures()) {
                                    animalArrayList.addAll(animalEnclosure.getAnimalListInEnclosure());
                                }
                            }

                            // Printout header
                            System.out.println("\nALL animal(s) in Zoo:");
                            String header = String.format("%3s %20s", "ID", "Animal Name");
                            System.out.println(header);
                            System.out.println("-".repeat(header.length()));

                            // Filling the table up with all the animals
                            for (Animal animal : animalArrayList) {
                                System.out.println(String.format("%3s %20s", animal.getAnimalID(), animal.getAnimalName()));
                            }

                            // Wait for user selection to decide which animal to send to the vet
                            try {
                                System.out.println("\nSelect which animal by ID to send to vet:");
                                int animalToVet = Integer.parseInt(scanner.next());
                                Animal foundAnimal = null;

                                // Finding the correct animal from the enclosure related to the ID
                                for (ZooEmployee zooEmployee : zooManager.getEmployeeHashMap().values()) {
                                    for (AnimalEnclosure animalEnclosure : zooEmployee.getEmployeeEnclosures()) {
                                        for (Animal animal : animalEnclosure.getAnimalListInEnclosure()) {
                                            if (animal.getAnimalID() == animalToVet) {
                                                foundAnimal = animal;

                                                // Removing the animal from the enclosure
                                                animalEnclosure.removeAnimal(foundAnimal.getAnimalID());

                                                break;
                                            }
                                        }
                                    }
                                }

                                // Checking to make sure the animal object has been initialised before manipulating it
                                if (foundAnimal != null) {
                                    // Setting the animal as sick
                                    foundAnimal.setHealthy(false);

                                    // Removing the animal from the enclosure
                                    for (ZooEmployee zooEmployee : zooManager.getEmployeeHashMap().values()) {
                                        for (AnimalEnclosure animalEnclosure : zooEmployee.getEmployeeEnclosures()) {
                                            if (animalEnclosure.hasAnimal(foundAnimal)) {
                                                animalEnclosure.removeAnimal(foundAnimal.getAnimalID());
                                            }
                                        }
                                    }

                                    // Add animal to the clinic
                                    vetClinic.addSickAnimalList(foundAnimal);

                                    System.out.println("Animal is now in the clinic and reported sick");

                                } else {
                                    throw new AnimalNotFoundException("Animal cannot be found");
                                }

                            } catch (NumberFormatException e) {
                                System.out.println("Invalid selection");

                            } catch (AnimalNotFoundException e) {
                                System.out.println(e.getMessage());
                            }

                            break;

                        case 7: // Displays all the sick animals

                            System.out.println("Animals in the Veterinary Clinic:");
                            System.out.println("---------------------------------");
                            System.out.printf("%n%3s %20s %20s", "ID", "Species", "Name");

                            System.out.println();
                            sickAnimalList.forEach(sickAnimal -> System.out.printf("%n%3s %20s %20s", sickAnimal.getAnimalID(), sickAnimal.getAnimalSpecies(), sickAnimal.getAnimalName()));

                            break;

                        case 8: // Lists the vet clinic's history

                            System.out.println("Vet Clinic History: ");

                            HashMap<Animal.AnimalSpecies, Integer> hs = vetClinic.getSickAnimalsIndex();

                            for (Map.Entry<Animal.AnimalSpecies, Integer> entry : hs.entrySet()) {
                                System.out.println(entry.getKey() + ": " + entry.getValue());
                            }
                            break;

                        case 9: // Displays how many animals are in special care

                            ArrayList<Animal> specialCareList = vetClinic.getSpecialCare();

                            System.out.println("Number of Animals in Special Care: " + specialCareList.size());

                            break;

                        case 10: // How many times vet has been called per species -
                            var vetCallList = VetClinic.vetCalls;

                            HashMap<Animal.AnimalSpecies, Integer> vetCallsPerSpecies = new HashMap<>();

                            vetCallList.forEach(animal -> {

                                int count = vetCallsPerSpecies.getOrDefault(animal.getAnimalSpecies(), 0) + 1;

                                vetCallsPerSpecies.put(animal.getAnimalSpecies(), count);

                            });

                            vetCallsPerSpecies.forEach((vetCalls, count) -> System.out.println("Species: " + vetCalls.toString() + ", Count: " + count));

                            break;

                        case 11: // List all resources
                            // Finding all the resources for all the animals across all the enclosures
                            ArrayList<Resource> resourceArrayList = new ArrayList<>();
                            for (ZooEmployee zooEmployee : zooManager.getEmployeeHashMap().values()) {
                                for (AnimalEnclosure animalEnclosure : zooEmployee.getEmployeeEnclosures()) {
                                    for (Animal animal : animalEnclosure.getAnimalListInEnclosure()) {
                                        resourceArrayList.add(animal.getFood());
                                        resourceArrayList.add(animal.getMedication());
                                    }
                                }
                            }

                            // Printout header
                            System.out.println("\nALL animal(s) in Zoo:");
                            String resourceHeader = String.format("%3s %30s %20s %20s %20s %20s", "ID", "Name", "Quantity", "Type", "Provider", "Price");
                            System.out.println(resourceHeader);
                            System.out.println("-".repeat(resourceHeader.length()));

                            // Filling the table up with all the animals
                            for (Resource resource : resourceArrayList) {
                                System.out.println(String.format("%3s %30s %20s %20s %20s %20s", resource.getResourceID(), resource.getResourceName(), resource.getQuantityResource(), resource.getResourceType(), resource.getCompanyName(), "€" + resource.getResourcePrice()));
                            }

                            break;

                        case 12:
                            try {
                                // Creating the text file to store the report in
                                File reportFile = new File("zoo_report.txt");
                                if (reportFile.createNewFile()) {
                                    System.out.println("Creating report...");

                                } else if (reportFile.exists()) {
                                    System.out.println("Creating report...");

                                } else {
                                    throw new IOException();
                                }

                                // Building string before adding it to the file
                                StringBuilder stringBuilder = new StringBuilder();
                                stringBuilder.append(String.format("%25s", "Zoo Report")).append("\n");
                                stringBuilder.append("-".repeat(45)).append("\n\n");

                                // Employee List
                                stringBuilder.append("Employees:").append("\n");
                                stringBuilder.append("-".repeat(45)).append("\n");
                                stringBuilder.append(String.format("%3s %20s %20s", "ID", "Employed", "Enclosure")).append("\n");
                                stringBuilder.append("-".repeat(45)).append("\n");

                                for (ZooEmployee zooEmployee : zooManager.getEmployeeHashMap().values()) {
                                    String employmentStatus;
                                    if (zooEmployee.isActiveEmployee()) {
                                        employmentStatus = "Hired";
                                    } else {
                                        employmentStatus = "Fired";
                                    }

                                    stringBuilder.append(String.format("%3s %20s %20s", zooEmployee.getEmployeeID(), employmentStatus, zooEmployee.getEmployeeEnclosures().get(0).getEnclosureName())).append("\n");
                                }

                                // Creating a list of all the animals
                                ArrayList<Animal> animalReportArrayList = new ArrayList<>();
                                for (ZooEmployee zooEmployee : zooManager.getEmployeeHashMap().values()) {
                                    for (AnimalEnclosure animalEnclosure : zooEmployee.getEmployeeEnclosures()) {
                                        animalReportArrayList.addAll(animalEnclosure.getAnimalListInEnclosure());
                                    }
                                }

                                // Adding it to the report stringBuilder
                                stringBuilder.append("\n\n").append("Animal List:").append("\n");
                                String animalListHeader = String.format("%3s %20s %20s %20s %20s %20s %30s %20s", "ID", "Name", "Species", "Health", "Food", "perServing", "Meds", "perServing");
                                stringBuilder.append(animalListHeader).append("\n");
                                stringBuilder.append("-".repeat(animalListHeader.length())).append("\n");

                                for (Animal animal : animalReportArrayList) {
                                    // Determining the status of the animals health in a human readable way
                                    String healthStatus;
                                    if (animal.isHealthy()) {
                                        healthStatus = "Healthy";
                                    } else {
                                        healthStatus = "Sick";
                                    }

                                    stringBuilder.append(String.format("%3s %20s %20s %20s %20s %20s %30s %20s", animal.getAnimalID(), animal.getAnimalName(), animal.getAnimalSpecies(), healthStatus, animal.getFood().getResourceName(), animal.getQuantityOfFoodEats(), animal.getMedication().getResourceName(), animal.getQuantityOfMedsRequired())).append("\n");
                                }

                                // Listing all of the current resources
                                ArrayList<Resource> resourceReportArrayList = new ArrayList<>();
                                for (ZooEmployee zooEmployee : zooManager.getEmployeeHashMap().values()) {
                                    for (AnimalEnclosure animalEnclosure : zooEmployee.getEmployeeEnclosures()) {
                                        for (Animal animal : animalEnclosure.getAnimalListInEnclosure()) {
                                            resourceReportArrayList.add(animal.getFood());
                                            resourceReportArrayList.add(animal.getMedication());
                                        }
                                    }
                                }

                                // Writing the resources to a report format
                                String resourceReportHeader = String.format("%3s %30s %20s %20s %20s", "ID", "Name", "Quantity", "Supplier", "Price");
                                stringBuilder.append("\n\n").append("Resources:").append("\n");
                                stringBuilder.append(resourceReportHeader).append("\n");
                                stringBuilder.append("-".repeat(resourceReportHeader.length())).append("\n");

                                for (Resource resource : resourceReportArrayList) {
                                    stringBuilder.append(String.format("%3s %30s %20s %20s %20s", resource.getResourceID(), resource.getResourceName(), resource.getQuantityResource(), resource.getCompanyName(), "€" + resource.getResourcePrice())).append("\n");
                                }

                                // Writing the vet clinic animals to the report
                                String vetClinicStandardHeader = String.format("%3s %20s %20s", "ID", "Name", "Species");
                                stringBuilder.append("\n\n").append("Vet Clinic:").append("\n");
                                stringBuilder.append(vetClinicStandardHeader).append("\n");
                                stringBuilder.append("-".repeat(vetClinicStandardHeader.length())).append("\n");

                                for (Animal animal : vetClinic.getSickAnimalList()) {
                                    stringBuilder.append(String.format("%3s %20s %20s", animal.getAnimalID(), animal.getAnimalName(), animal.getAnimalSpecies())).append("\n");
                                }

                                // Writing the vet clinic special ward patients to the report
                                String vetClinicIntensiveHeader = String.format("%3s %20s %20s", "ID", "Name", "Species");
                                stringBuilder.append("\n\n").append("Vet Clinic - Special Care:").append("\n");
                                stringBuilder.append(vetClinicIntensiveHeader).append("\n");
                                stringBuilder.append("-".repeat(vetClinicStandardHeader.length())).append("\n");

                                for (Animal animal : vetClinic.getSickAnimalList()) {
                                    stringBuilder.append(String.format("%3s %20s %20s", animal.getAnimalID(), animal.getAnimalName(), animal.getAnimalSpecies())).append("\n");
                                }

                                // Adding the vet callouts to the report
                                stringBuilder.append("\n\n").append(String.format("%s %10s", "Vet Call-Outs", "Total: " + VetClinic.vetCalls.size())).append("\n");
                                String vetCallOutHeader = String.format("%3s %20s", "ID", "Name");

                                stringBuilder.append(vetCallOutHeader).append("\n");
                                stringBuilder.append("-".repeat(vetCallOutHeader.length())).append("\n");

                                for (Animal animal : VetClinic.vetCalls) {
                                    stringBuilder.append(String.format("%3s %20s", animal.getAnimalID(), animal.getAnimalName())).append("\n");
                                }

                                // Writing report to the text file
                                FileWriter writer = new FileWriter(reportFile);
                                writer.write(stringBuilder.toString());
                                writer.close();
                                System.out.println("Report created");

                            } catch (IOException e) {
                                System.out.println("Error creating file");
                            }

                            break;
                    }
                }
            } catch (NumberFormatException e) {

                System.out.println("Please enter a valid selection");

            }

        }

    }


    public static void displayUserMenu() {

        String headerString = String.format("%20s", "Zoo UI");

        System.out.println("\n+" + "-".repeat(35) + "+");

        System.out.println(headerString);

        System.out.println("+" + "-".repeat(35) + "+");


        System.out.println(" 1) List all animals");

        System.out.println(" 2) Add an animal");

        System.out.println(" 3) Enclosure maintenance");

        System.out.println(" 4) List species consuming the most food");

        System.out.println(" 5) List species consuming the most meds");

        System.out.println(" 6) Send animal to vet clinic");

        System.out.println(" 7) List all sick animals");

        System.out.println(" 8) List vet clinic history");

        System.out.println(" 9) List animals in special care");

        System.out.println("10) List vet call outs");

        System.out.println("11) List resources");

        System.out.println("12) Print report");

        System.out.println(" 0) Quit");


        System.out.println("\nMake your selection:");

    }


}