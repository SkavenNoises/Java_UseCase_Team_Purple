import classes.*;
import exceptions.AnimalNotFoundException;

import java.util.ArrayList;
import java.util.Scanner;

public class Zoo {

    public static void main(String[] args) {
        // Zoo objects
        ZooManager zooManager = new ZooManager();
        zooManager.hireEmployee(new ZooEmployee());
        zooManager.getEmployeeHashMap().get(1).addEnclosureToEmployee(new AnimalEnclosure("TigerPagoda", true, true, false, Animal.AnimalSpecies.Tiger));
        zooManager.getEmployeeHashMap().get(1).getEmployeeEnclosures().get(0).addAnimal(new Animal("Bao", true, new Resource("Meat", 10.99, "Flesh'r'us", 10, Resource.ResourceType.Food), 1, new Resource("Tranquilisers", 8.99, "SandMan Co", 59, Resource.ResourceType.Meds), 1, Animal.AnimalSpecies.Tiger));
        VetClinic vetClinic = new VetClinic();

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

                            break;

                        case 2: // TODO Add an animal - sinduri

                            break;

                        case 3: // TODO Zoo employee maintaining the habit - sinduri

                            break;

                        case 4: // TODO Which species is eating the most food - sinduri

                            break;

                        case 5: // TODO which species is using the most medication -sinduri

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

                            break;

                        case 8: // TODO List all vet clinic history -zumrut

                            break;

                        case 9: // TODO How many animals are in special care -zumrut

                            break;

                        case 10: // TODO How many times the vet has been called per species -zumrut

                            break;

                        case 11: // TODO list resources -kieran

                            break;

                        case 12: // TODO PrintReport -kieran

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
