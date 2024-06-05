package FenceCrafting;

import GraphFundamentals.GraphFundamentals.Tragarz;

class Para {
    private Tragarz tragarz1;
    private Tragarz tragarz2;

    public Para(Tragarz tragarz1, Tragarz tragarz2) {
        this.tragarz1 = tragarz1;
        this.tragarz2 = tragarz2;
    }

    @Override
    public String toString() {
        return "Para: " + tragarz1 + " i " + tragarz2;
    }
}
