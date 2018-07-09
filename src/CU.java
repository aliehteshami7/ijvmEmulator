import interfaces.Clockable;
import interfaces.Resetable;
import registers.*;

public class CU implements Clockable, Resetable {
    private SC sc;
    private Z z;
    private N n;
    private Read read;
    private Write write;

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

    }

    @Override
    public void reset() {
        sc.reset();
        z.reset();
        n.reset();
        read.reset();
        write.reset();
    }
}
