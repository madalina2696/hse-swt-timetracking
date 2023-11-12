package swt;
public class FlexTimeAndWorker {
    private FlexTime flextime;
    private String id;

    public FlexTimeAndWorker(String id, FlexTime vacation){
        this.id=id;
        this.flextime=vacation;
    }
    public String getId(){
        return id;}

    public FlexTime getFlextime(){
        return  flextime;
    }}