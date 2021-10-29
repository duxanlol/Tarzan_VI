import java.util.*;

public class Main {
    static int currentId = 0;
    static int historyId = 0;
    static LinkedHashSet<Snapshot> history = new LinkedHashSet<>();
    static ArrayList<Snapshot> leaves = new ArrayList<>();
    static ArrayList<Cycle> cycles = new ArrayList<>();


    public static void main(String[] args) {
        Queue<Snapshot> queue = new LinkedList<>();
        Snapshot currentSnapshot;
        currentSnapshot = new Snapshot();
        queue.add(currentSnapshot);

        while(!queue.isEmpty()) {
            currentSnapshot = queue.poll();
            currentSnapshot.setNodeId(currentId++);

            if (!history.contains(currentSnapshot)){
                currentSnapshot.setHistoryId(historyId++);
                history.add(currentSnapshot);
                if(!currentSnapshot.isLeaf()) {
                    ArrayList<Snapshot> validMoves = currentSnapshot.validMoves();
                    for (Snapshot ss : validMoves) {
                        if (!history.contains(ss)) queue.add(ss);
                        else{
                            int parent = -1;
                            int child = currentSnapshot.getNodeId();
                            for (Snapshot tr : history) {
                                if (tr.equals(ss)) {
                                    parent = tr.getHistoryId();
                                }
                            }
                            cycles.add(new Cycle(parent, child, ss.lastMove));
                        }
                    }
                }
            }

        }



        JGraphT.graph(history, cycles);
        System.out.println();
        System.out.println("HISTORY SIZE " + history.size());

        //System.out.println(history);
        //System.out.println(cycles);




        }


}
