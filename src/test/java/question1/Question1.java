package question1;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Question1 {

    public static void main(String[] args) throws IOException {
        String question1Package = "src/test/java/question1/";
        String testDataFileName = "q1.test_data";
        FileWriter csvWriter = null;
        int fileNumber = 1;

        List<String> collect = readTestDataFile(Paths.get(question1Package + testDataFileName));

        for (String each : collect) {
            if (each.contains("smh [record")) {
                if (csvWriter != null) {
                    csvWriter.flush();
                    csvWriter.close();
                }
                csvWriter = new FileWriter(question1Package + "transaction_" + fileNumber++ + ".csv");
            }
            if (each.contains("\"")) {
                String[] split = each.split("\"");
                csvWriter.append(split[0].trim());
                csvWriter.append(",");
                csvWriter.append(split[1]);
                csvWriter.append("\n");
            } else if (each.contains("[void")) {
                String[] split = each.split("\\[");
                csvWriter.append(split[0].trim());
                csvWriter.append(",");
                String voidValue = split[1].replace("]", "");
                String aVoid = voidValue.replaceAll("void", "");
                System.out.println(aVoid);
                String[] s = aVoid.split(" ");
                System.out.println(Arrays.toString(s));
                System.out.println(s[1].trim());
//                BigInteger value = new BigInteger(s[1].trim(), 16);
//                System.out.println(value);
                Long number = Long.decode(s[1].trim());
                System.out.println(number);


//                csvWriter.append(split[1].replace("]", ""));
//                csvWriter.append((char) parseInt);
                csvWriter.append("\n");
            }
        }
        csvWriter.flush();
        csvWriter.close();
    }

    private static List<String> readTestDataFile(Path path) throws IOException {
        Stream<String> stream = Files.lines(path);
        return stream.collect(Collectors.toList());
    }
}
