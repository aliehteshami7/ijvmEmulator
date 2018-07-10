package registers;

import parts.Computer;

public class PC extends Register {
    private boolean load;
    private int inc;
    private int data;

    @Override
    public String toString() {
        return "PC: " + data + "\npc_load: " + load + "\npc_inc: " + inc + "\n";
    }

    public boolean isLoad() {
        return load;
    }

    public int getInc() {
        return inc;
    }

    public int getData() {
        return data;
    }

    @Override
    public void applyNextClockValue() {
        if (load)
            data = Computer.getInstance().getDp().getAlu().getOut();
        else
            data += inc;
        System.out.println(toString());
    }

    @Override
    public void calculateNextClockValue() {
        load = Computer.getInstance().getCu().getControlLogic().isPcLoad();
        inc = Computer.getInstance().getCu().getControlLogic().getPcInc();
    }

    @Override
    public void reset() {
        data = 0;
        inc = 0;
        load = false;
    }
}
