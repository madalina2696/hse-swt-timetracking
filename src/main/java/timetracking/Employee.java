package timetracking;

import java.sql.Time;
import java.time.LocalDate;

public class Employee {
    private String id;
    private String firstName;
    private String lastName;
    private String department;

    public Employee(String id, String firstName, String lastName, String department) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
    }

    public void addWorkTime(LocalDate day, Time begin, Time end, int pauseMin) {
        /* workDays.add(new WorkDay(day, begin, end, pauseMin)); */
    }

    public void deleteVacationDays(LocalDate date) {
        /* vacationRequests.remove(date.toString()); */
    }

    public void deleteFlexTimeVacation(LocalDate date) {

    }
}