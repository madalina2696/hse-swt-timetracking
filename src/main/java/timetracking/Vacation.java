package timetracking;

import java.time.LocalDate;

public class Vacation {

    private String id;
    private LocalDate date;
    private int minute;
    private boolean approved;

    public Vacation(LocalDate date, int minute, boolean approved) {
        this.date = date;
        this.minute = minute;
        this.approved = approved;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public boolean getApproved() {
        return approved;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getMinute() {
        return minute;
    }

    public void setMin(int min) {
        this.minute = minute;
    }

    public void setVacationID(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public Vacation getVacation() {
        return this;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

}
