package swt;

import java.sql.Time;
import java.time.LocalDate;

public class CalenderDay {

    private LocalDate day;
    private Time start;
    private Time end;
    private int breakMin;
    private int targetMin;
    private String comment;
    private char absence;// Absence No=N, F= Flextime , V = on vacation, S = sick day

    public CalenderDay(LocalDate day, Time start, Time end, int breakMin, String comment, char absence) {
        this.day = day;
        this.start = start;
        this.end = end;
        this.breakMin = breakMin;
        this.targetMin=targetMin;
        this.comment=comment;
        this.absence=absence;
    }

    public LocalDate getCalenderDay() {
        return day;
    }

    public Time getStart() {
        return start;
    }

    public Time getEnd() {
        return end;
    }

    public int getBreakMin() {
        return breakMin;
    }
    

    public int getTargetMin() {
        return targetMin;
    }

    public String getComment() {
        return comment;
    }

    public char getAbsence() {
        return absence;
    }

    
}
