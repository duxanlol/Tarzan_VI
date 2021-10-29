public class Cita {
    enum State{
        LEVA, DESNA, REKA
    }

    private State currentState;

    public Cita(State currentState) {
        this.currentState = currentState;
    }

    public boolean move(State next){
        if (currentState == next){
            return false;
        }
        if (currentState == State.LEVA){
            if(next == State.DESNA){
                return false;
            }
        }

        if (currentState == State.DESNA){
            if(next == State.LEVA){
                return false;
            }
        }

        currentState = next;
        return true;
    }

    public boolean isMoveValid(State next){
        if (currentState == next){
            return false;
        }
        if (currentState == State.LEVA){
            if(next == State.DESNA){
                return false;
            }
        }

        if (currentState == State.DESNA){
            if(next == State.LEVA){
                return false;
            }
        }

        return true;
    }

    public State getCurrentState() {
        return currentState;
    }

    @Override
    public String toString() {
        return "C{" + currentState +
                '}';
    }
}
