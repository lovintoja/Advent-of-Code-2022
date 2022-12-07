import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;

public class Exercise7 {
    private Exercise7() {}
    
    public static void Execute(){
        List<String> fileLines;
        try {
            fileLines = Files.readAllLines(ConfigLoader.GetConfigKey("input7"));
            List<Integer> fileSizes = fileLines.stream().map(x -> CommonMethods.IntTryParse(x.substring(0, x.indexOf(' ')), 0)).collect(Collectors.toList());
            long sum = 0;
            for(int size : fileSizes){
                if(size <= 100000) sum+= size;
            }
            System.out.println("Sum of files under 100000: " + sum);
        } catch (IOException e) { }
    }
}
