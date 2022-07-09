import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class OpenCSVReader {
    private static final String CSV_FILE_PATH = "src/main/resources/user.csv";
    public static void main(String[] args) {
        try{
            readAll();
        }catch (IOException ex){
            System.out.println("Cannot read file");
        }
    }

    private static void readingLineByLine() throws IOException {
        Reader reader = Files.newBufferedReader(Paths.get(CSV_FILE_PATH));
        CSVReader csvReader = new CSVReader(reader);

        String[] nextRecord;

        while ((nextRecord = csvReader.readNext()) != null){
            System.out.println("Name: " + nextRecord[0]);
            System.out.println("Email: " + nextRecord[1]);
            System.out.println("Phone: " + nextRecord[2]);
            System.out.println("Country: " + nextRecord[3]);
            System.out.println("=".repeat(20));
        }
    }

    private static void readAll() throws IOException {
        Reader reader = Files.newBufferedReader(Paths.get(CSV_FILE_PATH));
//        CSVReader csvReader = new CSVReader(reader);
        CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();
        List<String[]> nextRecord = csvReader.readAll();

        for(String[] record: nextRecord){
            System.out.println("Name: " + record[0]);
            System.out.println("Email: " + record[1]);
            System.out.println("Phone: " + record[2]);
            System.out.println("Country: " + record[3]);
            System.out.println("-".repeat(20));
        }
    }
}
