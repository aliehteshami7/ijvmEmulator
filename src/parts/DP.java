package parts;

import combinationalCircuits.ALU;
import combinationalCircuits.Bus;
import interfaces.Clockable;
import interfaces.Resetable;
import registers.*;

public class DP implements Clockable, Resetable {
    private ALU alu;
    private Bus bus;
    private H h;
    private LV lv;
    private MAR mar;
    private MBR mbr;
    private MDR mdr;
    private PC pc;
    private SP sp;

    public ALU getAlu() {
        return alu;
    }

    public Bus getBus() {
        return bus;
    }

    public H getH() {
        return h;
    }

    public LV getLv() {
        return lv;
    }

    public MAR getMar() {
        return mar;
    }

    public MBR getMbr() {
        return mbr;
    }

    public MDR getMdr() {
        return mdr;
    }

    public PC getPc() {
        return pc;
    }

    public SP getSp() {
        return sp;
    }

    public DP() {
        alu = new ALU();
        bus = new Bus();
        h = new H();
        lv = new LV();
        mar = new MAR();
        mbr = new MBR();
        mdr = new MDR();
        pc = new PC();
        sp = new SP();
    }

    @Override
    public void reset() {
        h.reset();
        lv.reset();
        mar.reset();
        mbr.reset();
        mdr.reset();
        pc.reset();
        sp.reset();
    }

    @Override
    public void applyNextClockValue() {
        h.applyNextClockValue();
        lv.applyNextClockValue();
        mar.applyNextClockValue();
        mbr.applyNextClockValue();
        mar.applyNextClockValue();
        mbr.applyNextClockValue();
        mdr.applyNextClockValue();
        pc.applyNextClockValue();
        sp.applyNextClockValue();
    }

    @Override
    public void calculateNextClockValue() {
        bus.calculateOutput();
        alu.calculateOutput();

        h.calculateNextClockValue();
        lv.calculateNextClockValue();
        mar.calculateNextClockValue();
        mbr.calculateNextClockValue();
        mdr.calculateNextClockValue();
        pc.calculateNextClockValue();
        sp.calculateNextClockValue();
    }
}
