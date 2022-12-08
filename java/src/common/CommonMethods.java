package common;
public class CommonMethods{
    public static int IntTryParse(String x, int exValue){
        try{
            return Integer.parseInt(x);
        } catch(NumberFormatException ex){
            return exValue;
        }
    }
}