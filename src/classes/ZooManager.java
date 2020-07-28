package classes;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class ZooManager {
    private HashMap<Integer, ZooEmployee> employeeHashMap;

    public ZooManager() {
        employeeHashMap = new HashMap<>();
    }

    public void hireEmployee(ZooEmployee zooEmployee) {
        employeeHashMap.put(zooEmployee.getEmployeeID(), zooEmployee);
        zooEmployee.setActiveEmployee(true);
    }

    public void fireEmployee(ZooEmployee zooEmployee) {
        zooEmployee.setActiveEmployee(false);
        employeeHashMap.remove(zooEmployee.getEmployeeID());
    }

    // TODO - Purchase Animals
    public void purchaseAnimal() {

    }

    // TODO - Get animal's health
    public void getAnimalHealth() {

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

                stringBuilder.append("\n").append(String.format("\n%3s %20s %20s", zooEmployee.getEmployeeID(), employeeStatus, /*TODO add enclosure data*/));
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
