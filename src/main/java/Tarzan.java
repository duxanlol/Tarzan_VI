import jdk.swing.interop.SwingInterOpUtils;

public class Tarzan {
    enum State{
        LEVA, DESNA, REKA_1, REKA_2, REKA_3, MRTAV
    }
    private State currentState;
    private boolean mrtav;
    public Tarzan(State currentState) {
        this.currentState = currentState;
        mrtav = false;
    }

    public boolean isUReci(){
        if (currentState == State.REKA_1 || currentState == State.REKA_2 || currentState == State.REKA_3)
            return true;
        return false;
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

        if (currentState == State.REKA_2){
            if(next == State.REKA_1){
                return false;
            }
        }
        if (currentState == State.REKA_3){
            if(next == State.REKA_1 || next == State.REKA_2){
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

        if (currentState == State.REKA_2){
            if(next == State.REKA_1){
                return false;
            }
        }

        if (currentState == State.REKA_3){
            if(next == State.REKA_1 || next == State.REKA_2){
                return false;
            }
        }

        return true;

    }

    public boolean incrementRekaAndCheckIfDead(){
        if(currentState == State.REKA_1){
            this.move(State.REKA_2);
        }
        else if(currentState == State.REKA_2){
            this.move(State.REKA_3);
        }else if(currentState == State.REKA_3) {
            mrtav = true;
        }
        return mrtav;
    }

    public State getCurrentState() {
        return currentState;
    }

    public boolean isMrtav() {
        return mrtav;
    }

    @Override
    public String toString() {
        return "T{"
                + currentState +
                '}';
    }
}
