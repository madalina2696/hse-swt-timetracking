package swt;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.util.HashMap;

public class AllEmployee {

    private HashMap<String, Worker> employeeList = new HashMap<>();// Daten als Schlüsselwert paare gespeichert
//empolyeeList ist die generierte hashmap wert wird einem employee zugeordnet

    AllEmployee() {
    }

    public void createWorker(String id, String firstName, String lastName, String department) { //ids e,s
        employeeList.put(id, new Worker(id, firstName, lastName, department));
    }// empolyee wird bestimmter wert zugeordnet

    public void deleteVacation(String id, LocalDate date){
        employeeList.get(id).deleteVacation(date);
    }

    public Worker getWorker(String id) {
        return employeeList.get(id);
    }

    public int getNumberOfEmployees() {
        return employeeList.size();
    }// wird nicht benutzt -> anzahl aller Employee anzeigen lassen

    public void addDayToWorker(String id, String date, String start, String end, String pauseMin,
            String comment, String absence) {
        employeeList.get(id).addDay(LocalDate.parse(date), Time.valueOf(start), Time.valueOf(end),
                Integer.parseInt(pauseMin), comment, absence.charAt(0));
    }// arbeits tag wird dem MA zugeordnet



    public HashMap<String, Worker> getEmployeeList() {
        return employeeList;
    }

    public void addVacationToWorker(String id, String date, String min, String approved) {
        employeeList.get(id).addVacationDay(LocalDate.parse(date), Integer.parseInt(min),
                Boolean.parseBoolean(approved));
    }

    public void addFlexTimeToWorker(String id, String date, String min, String approved) {
        employeeList.get(id).addFlexTimeDay(LocalDate.parse(date), Integer.parseInt(min),
                Boolean.parseBoolean(approved));
    }

    public void deleteFlextime(String id, LocalDate date) {
        employeeList.get(id).deleteFlextime(date);
    }
}
