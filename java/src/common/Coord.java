package common;

public class Coord {
    public int x;
    public int y;

    public static Coord Up = new Coord(0, 1);
    public static Coord Down = new Coord(0, -1);
    public static Coord Left = new Coord(-1, 0);
    public static Coord Right = new Coord(1, 0);

    public Coord(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void Sum(Coord a){
        this.x += a.x;
        this.y += a.y;
    }

    public static Coord Sum(Coord a, Coord b){
        return new Coord(a.x + b.x, a.y + b.y);
    }

    public int ManhattanMetric(Coord a){
        return Math.abs(this.x - a.x) + Math.abs(this.y - a.y);
    }
}
