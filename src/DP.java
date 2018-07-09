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
    private Read read;
    private SP sp;
}
