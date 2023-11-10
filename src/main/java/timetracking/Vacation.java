package swt;

import java.time.LocalDate;

public class Vacation {
    private LocalDate date;
    private int min;
    private boolean approved;
    
    public Vacation(LocalDate date, int min, boolean approved) {
        this.date=date;
        this.min=min;
        this.approved=approved;
    }
    public boolean getApproved() {
        return approved;
    }
    public void setApproved(boolean approved) {
        this.approved = approved;
    }
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
    public int getMin() {
        return min;
    }
    public void setMin(int min) {
        this.min = min;
    }


    
}
