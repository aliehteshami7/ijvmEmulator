package combinationalCircuits;

public class ControlLogic extends CombinationalCircuit {
    private boolean read;
    private boolean write;
    private boolean scClear;
    private boolean marLoad;
    private boolean mdrLoad;
    private boolean mbrLoad;
    private int busSelect;
    private boolean pcLoad;
    private int pcInc;
    private boolean spPush;
    private boolean pcPop;
    private boolean hLoad;
    private int aluControl;

    public boolean isRead() {
        return read;
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

    public boolean isPcPop() {
        return pcPop;
    }

    public boolean ishLoad() {
        return hLoad;
    }

    public int getAluControl() {
        return aluControl;
    }
}
