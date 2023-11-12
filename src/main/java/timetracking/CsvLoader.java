package timetracking;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.stream.Stream;

public class CsvLoader {

    private CsvLoader() {
    }

    public static String[][] load(String fileName) throws IOException {
        File verzeichnis = new File(fileName + ".csv");
        Path pfad = Paths.get(fileName + ".csv");
        Scanner fileScanner = new Scanner(verzeichnis, StandardCharsets.UTF_8);
        try {
            fileScanner = new Scanner(verzeichnis, StandardCharsets.UTF_8);
        } catch (FileNotFoundException e) {
            System.out.println("Not found\n" + e.toString());
        }
        int rowCounter;
        try (Stream<String> stream = Files.lines(pfad, StandardCharsets.UTF_8)) {
            rowCounter = (int) stream.count();
        }
        int rowIndex = 0;
        String line = fileScanner.nextLine();
        String[] column = line.split(";");
        String[][] table = new String[rowCounter][column.length];
        table[rowIndex] = column;
        rowIndex++;
        while (fileScanner.hasNextLine()) {
            line = fileScanner.nextLine();
            column = line.split(";");
            table[rowIndex] = column;
            rowIndex++;
        }
        fileScanner.close();
        return table;
    }

    public static void save(String fileName, String[][] table) throws IOException {
        try (PrintWriter out = new PrintWriter(fileName + ".csv", StandardCharsets.UTF_8)) {
            out.println(string2dToText(table));
        }
    }

    private static String string2dToText(String[][] s) {
        int lenRow = s[0].length;
        int lenColumn = s.length;
        StringBuilder sb = new StringBuilder();
        for (int y = 0; y < lenColumn; y++) {
            if (y > 0) {
                sb.append("\n");
            }
            for (int x = 0; x < lenRow; x++) {
                sb.append(s[y][x]);
                if (x + 1 < lenRow) {
                    sb.append(";");
                }
            }
        }
        return sb.toString();
    }
}
