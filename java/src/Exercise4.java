import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import common.ConfigLoader;

public class Exercise4 {

    public void Execute(){
        int overlapFully = 0;
        int overlap = 0;

        try {
            List<String> lines = Files.readAllLines(ConfigLoader.GetConfigKey("input4"));

            for(String line : lines){
                //sections = list of <start,end> one after another, eg: 1, 2, 3, 4 = <1, 2>, <3, 4>
                List<Integer> sections = ParseSections(line);
                if(sections.get(0) <= sections.get(3) && sections.get(1) >= sections.get(2)){
                    overlap++;
                    //nXOR did not work, it's getting late so leaving it like that
                    if((sections.get(0) <= sections.get(2) && sections.get(1) >= sections.get(3)) || sections.get(0) >= sections.get(2) && sections.get(1) <= sections.get(3)) overlapFully++;
                }
            }
            
        } catch (IOException e) {}
        
        System.out.println("Overlapping entirely: " + overlapFully);
        System.out.println("Overlapping: " + overlap);

    }

    private List<Integer> ParseSections(String line){
        List<Integer> sections = new ArrayList<>();

        while(true){
            int dashIndex = line.indexOf('-');
            sections.add(Integer.parseInt(line.substring(0, dashIndex)));
            line = line.substring(dashIndex + 1);
            int commaIndex = line.indexOf(',');
            if(commaIndex == -1){
                sections.add(Integer.parseInt(line));
                break;
            } else {
                sections.add(Integer.parseInt(line.substring(0, commaIndex)));
                line = line.substring(commaIndex + 1);
            }
        }
        return sections;
    }
}