package combinationalCircuits;

import parts.Computer;

public class Bus extends CombinationalCircuit {
    private int out;

    public int getOut() {
        return out;
    }

    @Override
    public void calculateOutput() {
        switch (Computer.getInstance().getCu().getControlLogic().getBusSelect()){
            case 0:
                out = Computer.getInstance().getDp().getMdr().getData();
                break;
            case 1:
                out = Computer.getInstance().getDp().getMbr().getOpra();
                break;
            case 2:
                out = Computer.getInstance().getDp().getMbr().getOprb();
                break;
            case 3:
                out = Computer.getInstance().getDp().getMbr().getOprab();
                break;
            case 4:
                out = Computer.getInstance().getDp().getPc().getData();
                break;
            case 5:
                out = Computer.getInstance().getDp().getSp().getData();
                break;
            case 6:
                out = Computer.getInstance().getDp().getLv().getData();
                break;
            // 7 = CPP, 8 = TOS, 9 = OPC
            default:
                System.out.println("Bus Select Value Out Of Range");
        }
        System.out.println("Bus_select: " + Computer.getInstance().getCu().getControlLogic().getBusSelect() + "\n");
    }
}
