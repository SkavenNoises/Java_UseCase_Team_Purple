package classes;

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

    // TODO - Administer medication to animal
    public void sendAnimalToVet(Animal animal) {

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
