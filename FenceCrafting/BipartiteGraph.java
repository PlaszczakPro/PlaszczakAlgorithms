package FenceCrafting;
import GraphFundamentals.GraphFundamentals.Tragarz;

import java.util.*;

public class BipartiteGraph {
    private int liczbaWierzcholkowL;
    private List<Integer>[] graf;
    private int[] parowanieL;
    private int[] parowanieP;

    private boolean[] visited;

    @SuppressWarnings("unchecked")
    public BipartiteGraph(int liczbaWierzcholkowL, int liczbaWierzcholkowP) {
        this.liczbaWierzcholkowL = liczbaWierzcholkowL;
        graf = new ArrayList[liczbaWierzcholkowL];
        for (int i = 0; i < liczbaWierzcholkowL; i++) {
            graf[i] = new ArrayList<>();
        }
        parowanieL = new int[liczbaWierzcholkowL];
        parowanieP = new int[liczbaWierzcholkowP];
        visited = new boolean[liczbaWierzcholkowL];
        Arrays.fill(parowanieL, -1);
        Arrays.fill(parowanieP, -1);
    }

    public void dodajKrawedz(int u, int v) {
        graf[u].add(v);
    }

    public boolean dfs(int u) {
        if (visited[u]) {
            return false;
        }
        visited[u] = true;
        for (int v : graf[u]) {
            if (parowanieP[v] == -1 || dfs(parowanieP[v])) {
                parowanieL[u] = v;
                parowanieP[v] = u;
                return true;
            }
        }
        return false;
    }

    public int maxMatching() {
        int matching = 0;
        Arrays.fill(parowanieL, -1);
        Arrays.fill(parowanieP, -1);
        for (int u = 0; u < liczbaWierzcholkowL; u++) {
            Arrays.fill(visited, false);
            if (dfs(u)) {
                matching++;
            }
        }
        return matching;
    }

    public void printMatching() {
        for (int u = 0; u < liczbaWierzcholkowL; u++) {
            if (parowanieL[u] != -1) {
                System.out.println("U" + u + " - V" + parowanieL[u]);
            }
        }
    }

    public List<ParyTragarzy.Para> getPary(List<Tragarz> przod, List<Tragarz> tyl) {
        List<ParyTragarzy.Para> pary = new ArrayList<>();
        for (int i = 0; i < liczbaWierzcholkowL; i++) {
            if (parowanieL[i] != -1) {
                pary.add(new ParyTragarzy.Para(przod.get(i).getId(), tyl.get(parowanieL[i]).getId()));
            }
        }
        return pary;
    }
}
