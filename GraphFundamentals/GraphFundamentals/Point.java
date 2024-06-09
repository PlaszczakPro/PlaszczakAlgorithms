package GraphFundamentals.GraphFundamentals;

public class Point {
    private int x;
    private int y;

    private int brightness;

    public Point(int x, int y, int brightness) {
        this.x = x;
        this.y = y;
        this.brightness = brightness;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }

    public int getBrightness() {
        return brightness;
    }

    public double distance(Point p){
        return Math.sqrt(Math.pow(p.x-x,2)+Math.pow(p.y-y,2));
    }

    @Override
    public String toString() {
        return "Punkt: " +
                "x=" + x +
                ", y=" + y;
    }
}
