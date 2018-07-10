package combinationalCircuits;

public class ControlLogic extends CombinationalCircuit {
    private int read;
    private int write;
    private int scClear;
    private int marLoad;
    private int mdrLoad;
    private int mbrLoad;
    private int busSelect;
    private int pcLoad;
    private int pcInc;
    private int spPush;
    private int pcPop;
    private int hLoad;
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
