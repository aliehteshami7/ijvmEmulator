package registers;

import parts.Computer;

public class SP extends Register {
    private int data = 1020;
    private boolean push;
    private boolean pop;

    @Override
    public String toString() {
        return "SP: " + data + "\nsp_push: " + push + "\nsp_pop: " + pop + "\n";
    }

    public int getData() {
        return data;
    }

    public boolean isPush() {
        return push;
    }

    public boolean isPop() {
        return pop;
    }

    @Override
    public void applyNextClockValue() {
        if (pop)
            data += 4;
        if (push)
            data -= 4;
        System.out.println(toString());
    }

    @Override
    public void calculateNextClockValue() {
        push = Computer.getInstance().getCu().getControlLogic().isSpPush();
        pop = Computer.getInstance().getCu().getControlLogic().isSpPop();
    }

    @Override
    public void reset() {
        data = 1020;
        push = false;
        pop = false;
    }
}
