package parts;

import interfaces.Clockable;
import interfaces.Resetable;

public class Computer implements Clockable, Resetable {
    private static Computer ourINstance = new Computer();
    private DP dp;
    private CU cu;
    private Memory memory;
    private boolean isHlt;

    public boolean isHlt() {
        return isHlt;
    }

    public void setHlt(boolean hlt) {
        isHlt = hlt;
    }

    public DP getDp() {
        return dp;
    }

    public CU getCu() {
        return cu;
    }

    public Memory getMemory() {
        return memory;
    }

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
        isHlt = false;
    }
}
