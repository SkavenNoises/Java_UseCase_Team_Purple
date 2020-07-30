import classes.*;
import exceptions.AnimalNotFoundException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Zoo {
    public static void main(String[] args) {
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

                        case 1: // TODO list all animals - sinduri
                            Animal.printAllAnimals();
                            break;

                        case 2: // TODO Add an animal - sinduri
                            Animal.addNewAnimal();
                            break;

                        case 3: // TODO Zoo employee maintaining the habit - sinduri
                            AnimalEnclosure.checkEnclosureMaintenance();
                            break;

                        case 4: // TODO Which species is eating the most food - sinduri
                            Animal.averageExpensiveFood();
                            break;

                        case 5: // TODO which species is using the most medication -sinduri
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

                        case 7: // TODO list all sick animals - zumrut

                        var sickAnimalList = vc.getSickAnimalList();
                        sickAnimalList.forEach(sickAnimal -> System.out.println(sickAnimal.getAnimalName()));

                        //sickAnimalList.getSickAnimalList.displayAnimal();

                        /*ArrayList<Animal> temp = vc.getSickAnimalList();

                         for (Integer i = 0; i < temp.size(); i++) {

                         //System.out.println("Animal: "+ temp.get(i).getAnimalName() + "..." + "|");

                         System.out.println(temp.get(i));

                        }*/
                        break;

                        case 8: // TODO List all vet clinic history -zumrut

                        HashMap<Animal.AnimalSpecies, Integer> hs = vc.getSickAnimalsIndex();

                        for( Map.Entry<Animal.AnimalSpecies, Integer> entry : hs.entrySet() ){
                            System.out.println( entry.getKey() + ": " + entry.getValue() );

                        }
                        break;

                        case 9: // TODO How many animals are in special care -zumrut

                        ArrayList<Animal> specialCareList = vc.getSpecialCare();

                        System.out.println("Count:" + specialCareList.size());

                        break;

                        case 10: // TODO How many times the vet has been called per species -

                        var vetCallList = zooEmployee.GetVetCalls();

                        HashMap<Animal.AnimalSpecies, Integer> vetCallsPerSpecies = new HashMap<>();

                        vetCallList.forEach(animal -> {

                            int count = vetCallsPerSpecies.getOrDefault(animal.getAnimalSpecies(), 0) + 1;

                            vetCallsPerSpecies.put(animal.getAnimalSpecies(), count);

                        });

                        vetCallsPerSpecies.forEach((vetCalls,count) -> System.out.println("Species:"+ vetCalls.toString()+", Count:" + count));
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
                            String resourceHeader = String.format("%3s %20s %20s %20s %20s %20s", "ID", "Name", "Quantity", "Type", "Provider", "Price");
                            System.out.println(resourceHeader);
                            System.out.println("-".repeat(resourceHeader.length()));

                            // Filling the table up with all the animals
                            for (Resource resource : resourceArrayList) {
                                System.out.println(String.format("%3s %20s %20s %20s %20s %20s", resource.getResourceID(), resource.getResourceName(), resource.getQuantityResource(), resource.getResourceType(), resource.getCompanyName(), "€" + resource.getResourcePrice()));
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
                                String animalListHeader = String.format("%3s %20s %20s %20s %20s %20s %20s %20s", "ID", "Name", "Species", "Health", "Food", "perServing", "Meds", "perServing");
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

                                    stringBuilder.append(String.format("%3s %20s %20s %20s %20s %20s %20s %20s", animal.getAnimalID(), animal.getAnimalName(), animal.getAnimalSpecies(), healthStatus, animal.getFood().getResourceName(), animal.getQuantityOfFoodEats(), animal.getMedication().getResourceName(), animal.getQuantityOfMedsRequired())).append("\n");
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
                                String resourceReportHeader = String.format("%3s %20s %20s %20s %20s", "ID", "Name", "Quantity", "Supplier", "Price");
                                stringBuilder.append("\n\n").append("Resources:").append("\n");
                                stringBuilder.append(resourceReportHeader).append("\n");
                                stringBuilder.append("-".repeat(resourceReportHeader.length())).append("\n");

                                for (Resource resource : resourceReportArrayList) {
                                    stringBuilder.append(String.format("%3s %20s %20s %20s %20s", resource.getResourceID(), resource.getResourceName(), resource.getQuantityResource(), resource.getCompanyName(), "€" + resource.getResourcePrice())).append("\n");
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