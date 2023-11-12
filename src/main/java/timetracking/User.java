package timetracking;

import java.time.LocalDate;
import java.util.HashMap;

public class User {

    private HashMap<String, Employee> employeeList = new HashMap<>();

    User() {
    }

    public void createWorker(String id, String firstName, String lastName, String department) { // ids e,s
        employeeList.put(id, new Employee(id, firstName, lastName, department));
    }

    public void deleteVacation(String id, LocalDate date) {
        /* employeeList.get(id).deleteVacation(date); */
    }

    public Employee getWorker(String id) {
        return employeeList.get(id);
    }

    public int getNumberOfEmployees() {
        return employeeList.size();
    }

    /*
     * public void addDayToWorker(String id, String date, String start, String end,
     * String pauseMin,
     * String comment, String absence) {
     * employeeList.get(id).addDay(LocalDate.parse(date), Time.valueOf(start),
     * Time.valueOf(end),
     * Integer.parseInt(pauseMin), comment, absence.charAt(0));
     * }
     */
    public HashMap<String, Employee> getEmployeeList() {
        return employeeList;
    }

    /*
     * public void addVacationToWorker(String id, String date, String min, String
     * approved) {
     * employeeList.get(id).addVacationDay(LocalDate.parse(date),
     * Integer.parseInt(min),
     * Boolean.parseBoolean(approved));
     * }
     * 
     * public void addFlexTimeToWorker(String id, String date, String min, String
     * approved) {
     * employeeList.get(id).addFlexTimeDay(LocalDate.parse(date),
     * Integer.parseInt(min),
     * Boolean.parseBoolean(approved));
     * }
     * 
     * public void deleteFlextime(String id, LocalDate date) {
     * employeeList.get(id).deleteFlextime(date);
     * }
     */
}
