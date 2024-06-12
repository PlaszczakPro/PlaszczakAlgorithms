package FenceCrafting;
import GraphFundamentals.GraphFundamentals.Tragarz;

import java.util.*;

public class BipartiteGraph {
    private int liczbaWierzcholkowL;
    private int liczbaWierzcholkowP;
    private List<Integer>[] graf;
    private int[] parowanieL;
    private int[] parowanieP;
    private int[] dystans;

    @SuppressWarnings("unchecked")
    public BipartiteGraph(int liczbaWierzcholkowL, int liczbaWierzcholkowP) {
        this.liczbaWierzcholkowL = liczbaWierzcholkowL;
        this.liczbaWierzcholkowP = liczbaWierzcholkowP;
        graf = (List<Integer>[]) new List[liczbaWierzcholkowL];
        for (int i = 0; i < liczbaWierzcholkowL; i++) {
            graf[i] = new ArrayList<>();
        }
        parowanieL = new int[liczbaWierzcholkowL];
        parowanieP = new int[liczbaWierzcholkowP];
        dystans = new int[liczbaWierzcholkowL];
        Arrays.fill(parowanieL, -1);
        Arrays.fill(parowanieP, -1);
    }

    public void dodajKrawedz(int u, int v) {
        graf[u].add(v);
    }

    public boolean bfs() {
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < liczbaWierzcholkowL; i++) {
            if (parowanieL[i] == -1) {
                dystans[i] = 0;
                queue.add(i);
            } else {
                dystans[i] = Integer.MAX_VALUE;
            }
        }

        boolean znalezionoSciezke = false;

        while (!queue.isEmpty()) {
            int u = queue.poll();
            for (int v : graf[u]) {
                if (parowanieP[v] == -1) {
                    znalezionoSciezke = true;
                } else if (dystans[parowanieP[v]] == Integer.MAX_VALUE) {
                    dystans[parowanieP[v]] = dystans[u] + 1;
                    queue.add(parowanieP[v]);
                }
            }
        }

        return znalezionoSciezke;
    }

    public boolean dfs(int u) {
        for (int v : graf[u]) {
            if (parowanieP[v] == -1 || (dystans[parowanieP[v]] == dystans[u] + 1 && dfs(parowanieP[v]))) {
                parowanieL[u] = v;
                parowanieP[v] = u;
                return true;
            }
        }
        dystans[u] = Integer.MAX_VALUE;
        return false;
    }

    public int maksymalneSkojarzenie() {
        int skojarzenie = 0;
        while (bfs()) {
            for (int i = 0; i < liczbaWierzcholkowL; i++) {
                if (parowanieL[i] == -1 && dfs(i)) {
                    skojarzenie++;
                }
            }
        }
        return skojarzenie;
    }

    public List<ParyTragarzy.Para> getPary(List<Tragarz> dostepni, List<Tragarz> niedostepni) {
        List<ParyTragarzy.Para> pary = new ArrayList<>();
        for (int i = 0; i < liczbaWierzcholkowL; i++) {
            if (parowanieL[i] != -1) {
                pary.add(new ParyTragarzy.Para(dostepni.get(i).getId(), niedostepni.get(parowanieL[i]).getId()));
            }
        }
        return pary;
    }
}
