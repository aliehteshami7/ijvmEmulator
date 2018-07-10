package combinationalCircuits;

public class ControlLogic extends CombinationalCircuit {
    private boolean read;
    private boolean write;
    private boolean scClear;
    private boolean scHold;
    private boolean marLoad;
    private boolean mdrLoad;
    private boolean mbrLoad;
    private int busSelect;
    private boolean pcLoad;
    private int pcInc;
    private boolean spPush;
    private boolean spPop;
    private boolean hLoad;
    private int aluControl;
    private boolean aluShift;

    public boolean isRead() {
        return read;
    }

    public boolean isScHold() {
        return scHold;
    }

    public boolean isWrite() {
        return write;
    }

    public boolean isScClear() {
        return scClear;
    }

    public boolean isMarLoad() {
        return marLoad;
    }

    public boolean isMdrLoad() {
        return mdrLoad;
    }

    public boolean isMbrLoad() {
        return mbrLoad;
    }

    public int getBusSelect() {
        return busSelect;
    }

    public boolean isPcLoad() {
        return pcLoad;
    }

    public int getPcInc() {
        return pcInc;
    }

    public boolean isSpPush() {
        return spPush;
    }

    public boolean isSpPop() {
        return spPop;
    }

    public boolean ishLoad() {
        return hLoad;
    }

    public int getAluControl() {
        return aluControl;
    }

    public boolean isAluShift() {
        return aluShift;
    }
}
