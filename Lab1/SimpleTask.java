import java.util.ArrayList;
import java.util.List;

public class SimpleTask {

    public static String[] getStringsShorterThanAverage(String[] input) {
        if (input == null || input.length == 0) return new String[0];
        
        double totalLength = 0;
        for (String s : input) {
            totalLength += s.length();
        }
        double average = totalLength / input.length;
        
        List<String> result = new ArrayList<>();
        for (String s : input) {
            if (s.length() < average) {
                result.add(s);
            }
        }
        
        return result.toArray(new String[0]);
    }

    public static String[] getStringsLongerThanAverage(String[] input) {
        if (input == null || input.length == 0) return new String[0];
        
        double totalLength = 0;
        for (String s : input) {
            totalLength += s.length();
        }
        double average = totalLength / input.length;
        
        List<String> result = new ArrayList<>();
        for (String s : input) {
            if (s.length() > average) {
                result.add(s);
            }
        }
        
        return result.toArray(new String[0]);
    }
}