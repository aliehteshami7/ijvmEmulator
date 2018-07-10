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

    public int getRead() {
        return read;
    }

    public int getWrite() {
        return write;
    }

    public int getScClear() {
        return scClear;
    }

    public int getMarLoad() {
        return marLoad;
    }

    public int getMdrLoad() {
        return mdrLoad;
    }

    public int getMbrLoad() {
        return mbrLoad;
    }

    public int getBusSelect() {
        return busSelect;
    }

    public int getPcLoad() {
        return pcLoad;
    }

    public int getPcInc() {
        return pcInc;
    }

    public int getSpPush() {
        return spPush;
    }

    public int getPcPop() {
        return pcPop;
    }

    public int gethLoad() {
        return hLoad;
    }

    public int getAluControl() {
        return aluControl;
    }
}
