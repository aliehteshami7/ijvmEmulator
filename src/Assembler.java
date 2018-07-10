/**
 * Created by ahmotameni on 7/8/2018.
 */

import java.nio.ByteBuffer;
import java.util.*;
import java.io.*;


public class Assembler {
    public static byte[] assemble() {

        byte[] memory = new byte[1024];
        Map lables = new HashMap();
        Map vars = new HashMap();

        File file = new File("C:\\Users\\ahmotameni\\Desktop\\CE@SUT\\Computer Architecture\\ijvmEmulator\\src\\chert.txt");
        Scanner scanner = null;
        try {
            scanner = new Scanner(new FileReader(file));
        } catch (FileNotFoundException ignored) {}

        int line = 0;
        int lv = 512;

        assert scanner != null;
        while (scanner.hasNext()){
            String inp = scanner.next();
//            System.out.println(inp);
//            if (inp.equals("DUP")){
//                memory[line] = 89 - 128;
//                line += 1;
//                continue;
//            }
//            if (inp.equals("IAND")){
//                memory[line] = 126 - 128;
//                line += 1;
//                continue;
//            }
//            if (inp.equals("INVOKEVIRTUAL")){
//                memory[line] = 186 - 128;
//                line += 1;
//
//
//            }
//            if (inp.equals("IOR")){
//                memory[line] = 128 - 128;
//                line += 1;
//                continue;
//            }
//            if (inp.equals("IRETURN")){
//                memory[line] = 172 - 128;
//                line += 1;
//                continue;
//            }
//            if (inp.equals("LDC_W")){
//                memory[line] = 19 - 128;
//                line += 1;
//
//            }
//            if (inp.equals("POP")){
//                memory[line] = 87 - 128;
//                line += 1;
//                continue;
//            }
//            if (inp.equals("SWAP")){
//                memory[line] = 95 - 128;
//                line += 1;
//                continue;
//            }
//            if (inp.equals("WIDE")){
//                memory[line] = 196 - 128;
//                line += 1;
//                continue;
//            }
            if (inp.equals("BIPUSH")){
                memory[line] = 16 - 128;
                line += 1;

                if (scanner.hasNextInt())
                    memory[line] = (byte) scanner.nextInt();

//                else if (scanner.hasNext())
//                    memory[line] = (byte) vars.get(scanner.next());

                line += 1;
                continue;
            }
            if (inp.equals("GOTO")){
                memory[line] = 167 - 128;
                line += 1;
//                if (scanner.hasNextInt()) {
                int offset = scanner.nextInt();
                byte[] b = ByteBuffer.allocate(4).putInt(offset).array();
                memory[line] = b[2];
                line += 1;
                memory[line] = b[3];
//                }
//                else {
//                    String label = scanner.next();
//                    int address = (int) lables.get(label);
//                    int offset = address - line;
//                    memory[line] = (byte) ((offset >> 24) & 0xFF);
//                    line += 1;
//                    memory[line] = (byte) ((offset >> 16) & 0xFF);
//                }
                line += 1;
                continue;
            }
            if (inp.equals("IADD")){
                memory[line] = 96 - 128;
                line += 1;
                continue;
            }
            if (inp.equals("IFEQ")){
                memory[line] = 153 - 128;
                line += 1;
                int offset = scanner.nextInt();
                byte[] b = ByteBuffer.allocate(4).putInt(offset).array();
                memory[line] = b[2];
                line += 1;
                memory[line] = b[3];
                line += 1;
                continue;
            }
            if (inp.equals("IFLT")){
                memory[line] = 155 - 128;
                line += 1;
                int offset = scanner.nextInt();
                byte[] b = ByteBuffer.allocate(4).putInt(offset).array();
                memory[line] = b[2];
                line += 1;
                memory[line] = b[3];
                line += 1;
                continue;
            }
            if (inp.equals("IF_ICMPEQ")){
                memory[line] = 159 - 128;
                line += 1;
                int offset = scanner.nextInt();
                byte[] b = ByteBuffer.allocate(4).putInt(offset).array();
                memory[line] = b[2];
                line += 1;
                memory[line] = b[3];
                line += 1;
                continue;
            }
            if (inp.equals("IINC")){
                memory[line] = 132 - 128;
                line += 1;

                if (scanner.hasNextInt()){
                    memory[line] = ((byte) scanner.nextInt());
                    line += 1;
                    memory[line] = ((byte) scanner.nextInt());
                    line += 1;
                    continue;
                }

                else if (scanner.hasNext()){

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
            if (inp.equals("ILOAD")){
                memory[line] = 21 - 128;
                line += 1;

                if (scanner.hasNextInt()){

                    memory[line] = ((byte) scanner.nextInt());
                    line += 1;
                    continue;
                }

                else if (scanner.hasNext()){

                    String varnum = scanner.next();

                    byte varkey = (byte) vars.get(varnum);
                    varkey = (byte) (varkey >> 2 + 1);
                    memory[line] = varkey;
                    line += 1;
                    continue;
                }
            }

            if (inp.equals("ISTORE")){
                memory[line] = 54 - 128;
                line += 1;

                if (scanner.hasNextInt()){

                    memory[line] = ((byte) scanner.nextInt());
                    line += 1;
                    continue;
                }

                else if (scanner.hasNext()){

                    String varnum = scanner.next();

                    byte varkey = (byte) vars.get(varnum);
                    varkey = (byte) (varkey >> 2 + 1);
                    memory[line] = varkey;
                    line += 1;
                    continue;
                }
            }
            if (inp.equals("ISUB")){
                memory[line] = 100 - 128;
                line += 1;
                continue;
            }
            if (inp.equals("NOP")){
                memory[line] = 0 - 128;
                line += 1;
                continue;
            }
            if (inp.equals("HLT")){
                memory[line] = 1 - 128; // az khodam gozashtam
                line += 1;
            }
            else{
                if (scanner.hasNextInt()){
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

                }
                else {
                    lables.put(inp, line);
                    line += 1;
                }
            }
        }
        for (int i = 0; i < memory.length; i++){
            System.out.println(memory[i]);
        }
        return memory;
    }
}
