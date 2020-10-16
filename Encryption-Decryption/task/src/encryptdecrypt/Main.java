package encryptdecrypt;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {

    public static String readFileAsString(String filename) {
        String text = "";
        try {
            text = new String(Files.readAllBytes(Paths.get(filename)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return text;
    }

    public static String[] parseParameters(String[] args) {
        String mode = "enc", key = "0", data = "", out = "console", alg = "shift";
        int index = 0;
        while (index < args.length) {
            if (args[index].equals("-out")) out = args[index+1];
            if (args[index].equals("-in")) data = readFileAsString(args[index+1]);
            if (args[index].equals("-mode")) mode = args[index + 1];
            if (args[index].equals("-key")) key = args[index+1];
            if (args[index].equals("-data")) data = args[index+1];
            if (args[index].equals("-alg")) alg = args[index+1];
            index += 2;
        }
        return new String[]{mode, data, key, out, alg};
    }

    public static void main(String[] args) {
        String[] params = parseParameters(args);
            String mode = params[0];
        String text = params[1];
        String alg = params[4];
        Context context = new Context();
        int key = Integer.parseInt(params[2]);

        if (alg.equals("unicode")) context.setMethod(new Unicode());
        else context.setMethod(new Shift());

        String output = text;
        if ("enc".equals(mode)) output = context.encode(text, key);
        if ("dec".equals(mode)) output = context.decode(text, key);

        if (params[3].equals("console")) {
            System.out.println(output);
        } else {
            File file = new File(params[3]);
            try (FileWriter writer = new FileWriter(file, false)) {
                writer.write(output);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
