package GraphFundamentals.GraphFundamentals;

abstract class Plaszczak {
    private static int nextId = 1;
    private int id;

    public Plaszczak() {
        this.id = nextId++;
    }

    public int getId() {
        return id;
    }
}
