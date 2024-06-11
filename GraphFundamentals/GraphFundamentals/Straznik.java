package GraphFundamentals.GraphFundamentals;

public class Straznik extends Plaszczak implements Comparable<Straznik> {
    int stamina;
    int counter=0;
    Point point;
    Point lastPoint=new Point(0,0,0);;
    public Straznik(int stamina) {
        super();
        this.stamina = stamina;
    }
    public void setPoint(Point point) {
        this.point = point;
    }
    public void setLastPoint(Point lastPoint) {
        this.lastPoint = lastPoint;
    }
    public Point getPoint() {
        return point;
    }
    public Point getLastPoint() {
        return lastPoint;
    }
    public int getStamina() {
        return stamina;
    }
    public void setStamina(int stamina) {
        this.stamina = stamina;
    }
    public int getID() {
        return super.getId();
    }

    @Override
    public int compareTo(Straznik o) {
      return Integer.compare(o.stamina, this.stamina);
    }

    public boolean move(Point point){
        this.lastPoint=this.point;
        this.point=point;
        if(point.getBrightness()> lastPoint.getBrightness()){
            this.patrol(point);
            counter=0;
            return true;
        }
        else if(counter==3){
            this.patrol(point);
            counter=0;
            return true;
        }
        else {
            this.counter++;
        }
        return false;
    }
    public void patrol(Point p){
        System.out.println("Straznik "+this.getID()+" patroluje punkt "+p.getX()+" "+p.getY());
        System.out.println("Odsłuchiwanie piosenki");
    }
}
