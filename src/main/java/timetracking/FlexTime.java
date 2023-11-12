package timetracking;

import java.time.LocalDate;

public class FlexTime {

    private int min;
    private LocalDate date;
    private boolean approved;
    private int flexTime;
    private String id;

    public FlexTime(int min, LocalDate date, boolean approved) {
        this.min = min;
        this.date = date;
        this.approved = approved;
    }

    public void setID(String id) {
        this.id = id;
    }

    public int getMinute() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public boolean getApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public String getId() {
        return id;
    }

    /* public FlexTime getFlextime() {
        return flexTime;
    } */
}
