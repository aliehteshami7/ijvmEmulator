package parts;

import combinationalCircuits.ControlLogic;
import interfaces.Clockable;
import interfaces.Resetable;
import registers.*;

public class CU implements Clockable, Resetable {
    private SC sc;
    private Z z;
    private N n;
    private Read read;
    private Write write;
    private ControlLogic controlLogic;

    public ControlLogic getControlLogic() {
        return controlLogic;
    }

    public SC getSc() {
        return sc;
    }

    public Z getZ() {
        return z;
    }

    public N getN() {
        return n;
    }

    public Read getRead() {
        return read;
    }

    public Write getWrite() {
        return write;
    }

    public CU(){
        sc = new SC();
        z = new Z();
        n = new N();
        read = new Read();
        write = new Write();
        controlLogic = new ControlLogic();
    }

    @Override
    public void reset() {
        sc.reset();
        z.reset();
        n.reset();
        read.reset();
        write.reset();
    }

    @Override
    public void applyNextClockValue() {
        sc.applyNextClockValue();
        z.applyNextClockValue();
        n.applyNextClockValue();
        read.applyNextClockValue();
        write.applyNextClockValue();
    }

    @Override
    public void calculateNextClockValue() {
        controlLogic.calculateOutput();

        sc.calculateNextClockValue();
        z.calculateNextClockValue();
        n.calculateNextClockValue();
        read.calculateNextClockValue();
        write.calculateNextClockValue();
    }
}
