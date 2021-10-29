import java.util.ArrayList;

public class Snapshot implements Comparable<Snapshot> {

    Tarzan tarzan;
    Cita cita;
    Krokodil krokodil;
    Papagaj papagaj;
    int nodeId;
    boolean death;
    boolean goal;
    boolean leaf;
    String reason;
    int parentId;
    String lastMove;
    int historyId;
    boolean mock;
    String mockText;
    int mockNum;

    public Snapshot(Tarzan tarzan, Cita cita, Krokodil krokodil, Papagaj papagaj) {
        this.tarzan = tarzan;
        this.cita = cita;
        this.krokodil = krokodil;
        this.papagaj = papagaj;
    }

    public Snapshot() {
        tarzan = new Tarzan(Tarzan.State.LEVA);
        cita = new Cita(Cita.State.LEVA);
        papagaj = new Papagaj(Papagaj.State.LEVA);
        krokodil = new Krokodil();
        leaf = false;
        death = false;
        goal = false;
    }

    public ArrayList<Snapshot> validMoves() {
        ArrayList<Snapshot> validMoves = new ArrayList<>();
        if (tarzan.isMoveValid(Tarzan.State.LEVA)) {
            Snapshot temp = this.deepCopy();
            temp.move("TL");
            validMoves.add(temp);
        }
        if (tarzan.isMoveValid(Tarzan.State.REKA_1)) {
            Snapshot temp = this.deepCopy();
            temp.move("TR");
            validMoves.add(temp);
        }
        if (tarzan.isMoveValid(Tarzan.State.DESNA)) {
            Snapshot temp = this.deepCopy();
            temp.move("TD");
            validMoves.add(temp);
        }
        if (cita.isMoveValid(Cita.State.LEVA)) {
            Snapshot temp = this.deepCopy();
            temp.move("CL");
            validMoves.add(temp);
        }
        if (cita.isMoveValid(Cita.State.REKA)) {
            Snapshot temp = this.deepCopy();
            temp.move("CR");
            validMoves.add(temp);
        }
        if (cita.isMoveValid(Cita.State.DESNA)) {
            Snapshot temp = this.deepCopy();
            temp.move("CD");
            validMoves.add(temp);
        }
        if (papagaj.isMoveValid(Papagaj.State.LEVA)) {
            Snapshot temp = this.deepCopy();
            temp.move("PL");
            validMoves.add(temp);
        }
        if (papagaj.isMoveValid(Papagaj.State.DESNA)) {
            Snapshot temp = this.deepCopy();
            temp.move("PD");
            validMoves.add(temp);

        }

        return validMoves;
    }

    public int move(String move) {
        boolean res = false;
        switch (move) {
            case "TL":
                res = tarzan.move(Tarzan.State.LEVA);
                break;
            case "TR":
                res = tarzan.move(Tarzan.State.REKA_1);
                break;
            case "TD":
                res = tarzan.move(Tarzan.State.DESNA);
                break;
            case "CL":
                res = cita.move(Cita.State.LEVA);
                if (res) tarzan.incrementRekaAndCheckIfDead();
                break;
            case "CR":
                res = cita.move(Cita.State.REKA);
                if (res) tarzan.incrementRekaAndCheckIfDead();
                break;
            case "CD":
                res = cita.move(Cita.State.DESNA);
                if (res) tarzan.incrementRekaAndCheckIfDead();
                break;
            case "PL":
                res = papagaj.move(Papagaj.State.LEVA);
                if (res) tarzan.incrementRekaAndCheckIfDead();
                break;
            case "PD":
                res = papagaj.move(Papagaj.State.DESNA);
                if (res) tarzan.incrementRekaAndCheckIfDead();
                break;
        }
        if (!res) {
            return 1;
        }
        didSomeoneDie();
        isGoal();
        lastMove = move;
        return 0;
    }

    public boolean isLeaf(){
        if (death || goal){
            leaf = true;
        }
        return leaf;
    }

    public boolean didSomeoneDie() {
        if (tarzan.isMrtav()) {
            reason = "Tarzan se udavio.";
            death = true;
        }
        if (cita.getCurrentState() == Cita.State.REKA && !tarzan.isUReci()) {
            reason = "Krokodil je pojeo Citu.";
            death = true;
        }
        return death;
    }

    public boolean isGoal() {
        if (tarzan.getCurrentState() == Tarzan.State.DESNA && cita.getCurrentState() == Cita.State.DESNA && papagaj.getCurrentState() == Papagaj.State.DESNA) {
            goal = true;
        }
        return goal;
    }

    public Snapshot deepCopy() {
        Tarzan tarzanCopy = new Tarzan(tarzan.getCurrentState());
        Cita citaCopy = new Cita(cita.getCurrentState());
        Krokodil krokodilCopy = new Krokodil();
        Papagaj papagajCopy = new Papagaj(papagaj.getCurrentState());
        Snapshot copy = new Snapshot(tarzanCopy, citaCopy, krokodilCopy, papagajCopy);
        copy.setParentId(this.getNodeId());
        return copy;
    }

    public int getHistoryId() {
        return historyId;
    }

    public void setHistoryId(int historyId) {
        this.historyId = historyId;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public Tarzan getTarzan() {
        return tarzan;
    }

    public void setTarzan(Tarzan tarzan) {
        this.tarzan = tarzan;
    }

    public Cita getCita() {
        return cita;
    }

    public void setCita(Cita cita) {
        this.cita = cita;
    }

    public Krokodil getKrokodil() {
        return krokodil;
    }

    public void setKrokodil(Krokodil krokodil) {
        this.krokodil = krokodil;
    }

    public Papagaj getPapagaj() {
        return papagaj;
    }

    public void setPapagaj(Papagaj papagaj) {
        this.papagaj = papagaj;
    }

    public int getNodeId() {
        return nodeId;
    }

    public void setNodeId(int nodeId) {
        this.nodeId = nodeId;
    }

    @Override
    public int compareTo(Snapshot o) {
        if (this.getTarzan().getCurrentState() != o.getTarzan().getCurrentState())
            return -1;
        if (this.getCita().getCurrentState() != o.getCita().getCurrentState())
            return -1;
        if (this.getPapagaj().getCurrentState() != o.getPapagaj().getCurrentState())
            return -1;
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Snapshot)) return false;
        Snapshot snapshot = (Snapshot) o;

        if (snapshot.isMock() && this.isMock()){
            if(snapshot.mockText.equals(this.mockText)){

                return false;
            }
        }


        if (this.getTarzan().getCurrentState() != snapshot.getTarzan().getCurrentState())
            return false;
        if (this.getCita().getCurrentState() != snapshot.getCita().getCurrentState())
            return false;
        if (this.getPapagaj().getCurrentState() != snapshot.getPapagaj().getCurrentState())
            return false;
        return true;
    }

    public void setMock(boolean mock) {
        this.mock = mock;
    }

    public boolean isMock(){
        return mock;
    }

    public void setMockText(String mockText) {
        this.mockText = mockText;
    }


    @Override
    public int hashCode() {
        if(mock) return mockText.hashCode();
        int rekaAdd = 0;
        if (tarzan.isUReci()){
            rekaAdd = Character.valueOf(tarzan.getCurrentState().toString().charAt(5));
        }

        int hash = ((int)tarzan.getCurrentState().toString().charAt(0)) + rekaAdd + ((int)cita.getCurrentState().toString().charAt(0))*1000 + ((int)papagaj.getCurrentState().toString().charAt(0))*1000000;
        return hash;

    }

    public String toStringCompact(){
        String rekaAdd = "";
        if (tarzan.isUReci()){
            rekaAdd = ""+tarzan.getCurrentState().toString().charAt(5);
        }
        return ""+"'"+tarzan.getCurrentState().toString().substring(0,1) + rekaAdd + cita.getCurrentState().toString().substring(0,1) + papagaj.getCurrentState().toString().substring(0,1) + "'";
    }

    public String toString() {
        if (mock){
            return mockText;
        }
        String addon = "";
        if (death) addon += "\nSMRT " + reason;
        if (goal) addon += "\nCILJ!";
        return ""+ historyId+ ": " + tarzan + " " + cita + " " + papagaj + addon + "\n";
    }

}
