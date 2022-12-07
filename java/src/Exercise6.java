import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.stream.IntStream;

public class Exercise6 {
    private Exercise6() {
    }

    public static void Execute() {
        try {
            String transmission = Files.readString(ConfigLoader.GetConfigKey("input6"));

            FirstApproach(transmission, 4);
            SecondApproach(transmission, 4);

            FirstApproach(transmission, 14);
            SecondApproach(transmission, 14);

        } catch (IOException e) {
        }
    }

    private static void FirstApproach(String transmission, int consecutiveCharacters) {
        System.out.println("Starting sequence at: "
                + IntStream.range(consecutiveCharacters, transmission.length())
                        .filter(x -> transmission.substring(x - consecutiveCharacters, x).chars().distinct().count() == consecutiveCharacters).findFirst()
                        .orElse(-1));
    }

    private static void SecondApproach(String transmission, int consecutiveCharacters){
        ArrayList<Character> transmissionLetters = new ArrayList<>();

            for(char c : transmission.toCharArray()){
                transmissionLetters.add(c);
            }
            
            for(int i = 0; i < transmission.length() - consecutiveCharacters;){
                int startingPosition = i;
                String subString = transmission.substring(i, i + consecutiveCharacters);

                for(char c : subString.toCharArray()){
                    int index = subString.indexOf(c);

                    if(index != subString.lastIndexOf(c)){
                        i += index + 1;
                        break;
                    }         
                }

                if(startingPosition == i){
                    System.out.println("Starting sequence at: " + (i + consecutiveCharacters));
                    break;
                }
            }
    }
}