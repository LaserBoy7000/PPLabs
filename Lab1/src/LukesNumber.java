/**
 * Represents single number from Lukes sequence
 */
public class LukesNumber{
    public LukesNumber(int number, long value){
        this.number = number;
        this.value = value;
    }
    
    private long value;
    //get
    public long GetValue(){
        return value;
    }
    
    private int number;
    //get
    public int GetNumber(){
        return number;
    }
}