package timetracking;

import java.sql.Time;
import java.time.LocalDate;

public class Calender {
    private int breakMinute;
    private int targetMinute;
    private Time begin;
    private Time end;
    private LocalDate day;
    private String comment;
    private char absence; // (N)o, (F)lextime , (V)acation, (S)ick day

    public Calender(int breakMinute, Time begin, Time end, LocalDate day, String comment, char absence) {
        this.breakMinute = breakMinute;
        this.begin = begin;
        this.end = end;
        this.day = day;
        this.comment = comment;
        this.absence = absence;
    }

    public int getBreakMinute() {
        return breakMinute;
    }

    public int getTargetMinute() {
        return targetMinute;
    }

    public Time getBegin() {
        return begin;
    }

    public Time getEnd() {
        return end;
    }

    public LocalDate getCalender() {
        return day;
    }

    public String getComment() {
        return comment;
    }

    public char getAbsence() {
        return absence;
    }
}
