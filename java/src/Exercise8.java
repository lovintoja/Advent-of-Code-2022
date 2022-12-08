import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import common.ConfigLoader;
import common.Coord;

public class Exercise8 {
    public void Execute(){
        int countVisible = 0;
        try {
            List<String> lines = Files.readAllLines(ConfigLoader.GetConfigKey("input8"));
            long startTime = System.currentTimeMillis();
            int[][] trees = GetNumericArray(lines);
            countVisible = CountVisible(trees);
            System.out.println("Trees visible: " + countVisible);
            System.out.println("Time: " + (System.currentTimeMillis() - startTime));
        } catch (IOException e) { }
    }

    private int[][] GetNumericArray(List<String> lines){
        int[][] array = new int[lines.size()][lines.get(0).length()];
        for(int i = 0; i < lines.size(); i++){
            for(int j = 0; j < lines.get(i).length(); j++){
                array[i][j] = Character.getNumericValue(lines.get(i).charAt(j));
            }
        }
        return array;
    }

    private int CountVisible(int[][] trees){
        int xLimit = trees[0].length;
        int yLimit = trees.length;
        Coord[] directions = new Coord[]{Coord.Up, Coord.Down, Coord.Left, Coord.Right};
        int countVisible = 0;
        List<Integer> results = new ArrayList<>();

        for(int i = 0; i < trees.length; i++){
            for(int j = 0; j < trees[i].length; j++){
                Coord tree = new Coord(i, j);
                boolean visible = false;
                int multipliedResult = 1;

                for(Coord direction : directions){
                    boolean obstacle = false;
                    Coord moved = Coord.Sum(direction, tree);

                    while((moved.x < xLimit && moved.y < yLimit) && (moved.x > -1 && moved.y > -1)){
                        if(trees[tree.y][tree.x] <= trees[moved.y][moved.x]) {
                            obstacle = true;
                            //distance to the nearest obstacle
                            multipliedResult *= tree.ManhattanMetric(moved);
                            break;
                        }
                        moved.Sum(direction);
                    }

                    //obstacle gets resetted every direction iteration, visibility needs to stay the same if it was true
                    if(!obstacle){
                        visible = true;
                        //if not distance from obstacle, calculate distance from bounds of specified direction
                        multipliedResult *= CheckDistanceFromBounds(xLimit, yLimit, tree, direction);
                    }
                }
                if(visible) countVisible++;
                results.add(multipliedResult);
            }
        }
        System.out.println("Max multiplied score: " + Collections.max(results));
        return countVisible;
    }

    private int CheckDistanceFromBounds(int x, int y, Coord tree, Coord direction){
        if(direction.x == 1) return x - tree.x - 1;
        //if tree is on the border, multiply by 1 to not change the score
        if(direction.x == -1) return tree.x == 0 ? 1 : tree.x;
        if(direction.y == 1) return y - tree.y - 1;
        //same as above
        if(direction.y == -1) return tree.y == 0 ? 1 : tree.y;
        throw new IllegalArgumentException();
    }
}