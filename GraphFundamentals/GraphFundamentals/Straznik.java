package GraphFundamentals.GraphFundamentals;

public class Straznik extends Plaszczak implements Comparable<Straznik> {
    int stamina;
    public Straznik(int stamina) {
        super();
        this.stamina = stamina;
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
      return Integer.compare(o.stamina,this.stamina);
    }

}
