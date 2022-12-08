import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import common.ConfigLoader;

public class Exercise3 {

    public void Execute(){
        long points = 0;
        long badgesPoints = 0;
        try {
            List<String> lines = Files.readAllLines(ConfigLoader.GetConfigKey("input3"));

            for(String line : lines){
                char duplicatedChar = FindDuplicate(line.substring(0, line.length()/2), line.substring(line.length()/2, line.length()), null);
                points += GetPoints(duplicatedChar);
            }

            for(int i = 0; i < lines.size() - 2; i += 3){
                char duplicatedChar = FindDuplicate(lines.get(i), lines.get(i+1), lines.get(i+2));
                badgesPoints += GetPoints(duplicatedChar);
            }

        } catch (IOException e) {}
        System.out.println("Points: " + points);
        System.out.println("Badges points: " + badgesPoints);
    }

    private int GetPoints(char c){
        if(Character.isLowerCase(c)) return Character.getNumericValue(c) - 9;
        return Character.getNumericValue(c) + 17;
    }

    private char FindDuplicate(String first, String second, String third){
        for(char c : first.toCharArray()){
            if(second.indexOf(c) != -1 && (third == null || third.indexOf(c) != -1)) return c;
        }

        throw new IllegalArgumentException("No duplicate for specified input!");
    }
}