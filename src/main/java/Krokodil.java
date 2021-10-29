public class Krokodil {
    enum State{
        REKA
    }

    State currentState;

    public Krokodil() {
        currentState = State.REKA;
    }

    @Override
    public String toString() {
        return "Krokodil{" + currentState +
                '}';
    }
}
