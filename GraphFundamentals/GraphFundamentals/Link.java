package GraphFundamentals.GraphFundamentals;

public class Link {
    Vertex vS;
    Vertex vE;

    public Link(Vertex vS, Vertex vE) {
        this.vS=vS;
        this.vE=vE;
    }

    public Vertex getvE(){
        return vE;
    }

    public Vertex getvS(){
        return vS;
    }

    @Override
    public String toString() {
        return "Link: "+
                "[ vS=" + vS.getId() +
                ", vE=" + vE.getId() +
                " ]\n";
    }
}