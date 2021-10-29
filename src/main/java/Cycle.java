import java.util.ArrayList;

public class Cycle {
    public int parentId;
    public int childId;
    public String lastMove;
    public Cycle(int parentId, int childId, String lastMove) {
        this.parentId = parentId;
        this.childId = childId;
        this.lastMove = lastMove;
    }

    @Override
    public String toString() {
        return "Cycle{" +
                "parentId=" + parentId +
                ", childId=" + childId +
                ", lastMove='" + lastMove + '\'' +
                '}';
    }
}
