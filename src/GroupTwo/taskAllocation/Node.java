package GroupTwo.taskAllocation;

import java.util.HashMap;

public class Node implements Comparable<Node>{
    public HashMap<Integer, Integer> partialSolution;
    public int lowerBound;
    public int depth;
    public Node() {
        partialSolution = new HashMap();
        lowerBound = 0;
        depth = 0;
    }
    @Override
    public int compareTo(Node o) {
        return lowerBound - o.lowerBound;
    }

    //是否为叶子节点---是否为最终解
    protected static boolean isACompleteSolution(Node n) {
        return n.depth == 4;
    }

    protected static boolean feasible(Node n, int i) {
        return !n.partialSolution.containsValue(i);
    }
}
