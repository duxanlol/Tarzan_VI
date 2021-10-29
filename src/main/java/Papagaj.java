public class Papagaj {
    enum State{
        LEVA, DESNA
    }

    private State currentState;

    public Papagaj(State currentState) {
        this.currentState = currentState;
    }

    public boolean move(Papagaj.State next){
        if (currentState == next){
            return false;
        }
        currentState = next;
        return true;
    }

    public boolean isMoveValid(Papagaj.State next){
        if (currentState == next){
            return false;
        }

        return true;
    }

    public State getCurrentState() {
        return currentState;
    }

    @Override
    public String toString() {
        return "P{" + currentState +
                '}';
    }
}
