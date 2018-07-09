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
        pc = new PC();
        sp = new SP();
    }

    @Override
    public void reset() {
        alu.reset();
        bus.reset();
        h.reset();
        lv.reset();
        mar.reset();
        mbr.reset();
        pc.reset();
        sp.reset();
    }
}
