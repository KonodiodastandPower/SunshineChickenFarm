package mainpackage;

public class Test {
    public static void main(String[] args) {
        System.out.println(1);
        try {
            Thread.sleep(1000);
        }catch (Exception e){}
        System.out.println(1);
    }
}
