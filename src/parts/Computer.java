package parts;

import interfaces.Clockable;
import interfaces.Resetable;

public class Computer implements Clockable, Resetable {
    private static Computer ourINstance = new Computer();
    private DP dp;
    private CU cu;
    private Memory memory;

    public static Computer getInstance() {
        return ourINstance;
    }

    private Computer() {
        dp = new DP();
        cu = new CU();
        memory = new Memory();
    }

    @Override
    public void applyNextClockValue() {
        dp.applyNextClockValue();
        cu.applyNextClockValue();
        memory.applyNextClockValue();
    }

    @Override
    public void calculateNextClockValue() {
        cu.calculateNextClockValue(); // Should be before dp
        dp.calculateNextClockValue();
        memory.calculateNextClockValue();
    }

    @Override
    public void reset() {
        dp.reset();
        cu.reset();
        memory.reset();
    }
}
