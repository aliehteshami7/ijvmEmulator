import interfaces.Clockable;
import interfaces.Resetable;
import registers.*;

public class CU implements Clockable, Resetable {
    private SC sc;
    private Z z;
    private N n;
    private Read read;
    private Write write;
}
