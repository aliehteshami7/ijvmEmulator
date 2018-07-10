import java.nio.ByteBuffer;
import java.util.*;
import java.io.*;


public class Assembler {
    public static byte[] assemble() {

        byte[] memory = new byte[1028];
        Map lables = new HashMap();
        Map vars = new HashMap();

        File file = new File("code.txt");
        Scanner scanner = null;
        try {
            scanner = new Scanner(new FileReader(file));
        } catch (FileNotFoundException exception) {
            System.out.println(exception);
        }

        int line = 0;
        int lv = 512;

        assert scanner != null;
        while (scanner.hasNext()) {
            String inp = scanner.next();
            if (inp.equals("BIPUSH")) {
                memory[line] = 0x10;
                line += 1;

                if (scanner.hasNextInt())
                    memory[line] = (byte) scanner.nextInt();
                line += 1;
                continue;
            }
            if (inp.equals("GOTO")) {
                memory[line] = (byte) 0xa7;
                line += 1;
                int offset = scanner.nextInt();
                byte[] b = ByteBuffer.allocate(4).putInt(offset).array();
                memory[line] = b[2];
                line += 1;
                memory[line] = b[3];
                line += 1;
                continue;
            }
            if (inp.equals("IADD")) {
                memory[line] = 0x60;
                line += 1;
                continue;
            }
            if (inp.equals("IFEQ")) {
                memory[line] = (byte) 0x99;
                line += 1;
                int offset = scanner.nextInt();
                byte[] b = ByteBuffer.allocate(4).putInt(offset).array();
                memory[line] = b[2];
                line += 1;
                memory[line] = b[3];
                line += 1;
                continue;
            }
            if (inp.equals("IFLT")) {
                memory[line] = (byte) 0x9b;
                line += 1;
                int offset = scanner.nextInt();
                byte[] b = ByteBuffer.allocate(4).putInt(offset).array();
                memory[line] = b[2];
                line += 1;
                memory[line] = b[3];
                line += 1;
                continue;
            }
            if (inp.equals("IF_ICMPEQ")) {
                memory[line] = (byte) 0x9f;
                line += 1;
                int offset = scanner.nextInt();
                byte[] b = ByteBuffer.allocate(4).putInt(offset).array();
                memory[line] = b[2];
                line += 1;
                memory[line] = b[3];
                line += 1;
                continue;
            }
            if (inp.equals("IINC")) {
                memory[line] = (byte) 0x84;
                line += 1;

                if (scanner.hasNextInt()) {
                    memory[line] = ((byte) scanner.nextInt());
                    line += 1;
                    memory[line] = ((byte) scanner.nextInt());
                    line += 1;
                    continue;
                } else if (scanner.hasNext()) {

                    String varnum = scanner.next();
                    byte constant = (byte) scanner.nextInt();
                    int varkey = (int) vars.get(varnum);
                    byte vvv = (byte) ((varkey >> 2) + 1);
                    memory[line] = vvv;
                    line += 1;

                    memory[line] = constant;
                    line += 1;

                    continue;
                }
            }
            if (inp.equals("ILOAD")) {
                memory[line] = 0x15;
                line += 1;

                if (scanner.hasNextInt()) {

                    memory[line] = ((byte) scanner.nextInt());
                    line += 1;
                    continue;
                } else if (scanner.hasNext()) {

                    String varnum = scanner.next();

                    byte varkey = (byte) vars.get(varnum);
                    varkey = (byte) (varkey >> 2 + 1);
                    memory[line] = varkey;
                    line += 1;
                    continue;
                }
            }

            if (inp.equals("ISTORE")) {
                memory[line] = 0x36;
                line += 1;

                if (scanner.hasNextInt()) {

                    memory[line] = ((byte) scanner.nextInt());
                    line += 1;
                    continue;
                } else if (scanner.hasNext()) {

                    String varnum = scanner.next();

                    byte varkey = (byte) vars.get(varnum);
                    varkey = (byte) (varkey >> 2 + 1);
                    memory[line] = varkey;
                    line += 1;
                    continue;
                }
            }
            if (inp.equals("ISUB")) {
                memory[line] = 0x64;
                line += 1;
                continue;
            }
            if (inp.equals("NOP")) {
                memory[line] = 0x00;
                line += 1;
                continue;
            }
            if (inp.equals("HLT")) {
                memory[line] = 0x01; // az khodam gozashtam
                line += 1;
            } else {
                if (scanner.hasNextInt()) {
                    vars.put(inp, lv - 512 + 1);
                    int n = scanner.nextInt();
                    byte[] bytes = new byte[4];

                    bytes[0] = (byte) ((n >> 24) & 0xFF);
                    bytes[1] = (byte) ((n >> 16) & 0xFF);
                    bytes[2] = (byte) ((n >> 8) & 0xFF);
                    bytes[3] = (byte) (n & 0xFF);

                    memory[lv] = bytes[0];
                    lv += 1;
                    memory[lv] = bytes[1];
                    lv += 1;
                    memory[lv] = bytes[2];
                    lv += 1;
                    memory[lv] = bytes[3];
                    lv += 1;

                } else {
                    lables.put(inp, line);
                    line += 1;
                }
            }
        }
        return memory;
    }
}
