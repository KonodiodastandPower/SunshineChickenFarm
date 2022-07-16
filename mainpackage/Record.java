package mainpackage;

public class Record implements java.io.Serializable{
    public int money;
    public int CxkNumber;
    public int egg;
    public int day;
    public Record(int money,int CxkNumber,int egg,int day){
        this.money = money;
        this.CxkNumber = CxkNumber;
        this.egg = egg;
        this.day = day;
    }
}
