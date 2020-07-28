package classes;


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
    // TODO - Get animal's health
    // TODO - Print to file a report

    public HashMap<Integer, ZooEmployee> getEmployeeHashMap() {
        return employeeHashMap;
    }
}
