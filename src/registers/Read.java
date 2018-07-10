package registers;

import parts.Computer;

public class Read extends Register {
    private boolean data;
    private boolean q;

    public boolean isData() {
        return data;
    }

    public boolean isQ() {
        return q;
    }

    @Override
    public void applyNextClockValue() {
        data = q;
    }

    @Override
    public void calculateNextClockValue() {
        q = Computer.getInstance().getCu().getControlLogic().isRead();
    }

    @Override
    public void reset() {
        data = false;
        q = false;
    }
}
