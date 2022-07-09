import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;

import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class OpenCSVWriter {
    private static final String CSV_FILE_PATH = "src/main/resources/sample.csv";
    public static void main(String[] args) {

        try (Writer writer = Files.newBufferedWriter(Paths.get(CSV_FILE_PATH))) {
                StatefulBeanToCsv<MyUser> beanToCsv = new StatefulBeanToCsvBuilder<MyUser>(writer)
                        .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER).build();
                List<MyUser> myUsers = new ArrayList<>();
                myUsers.add(new MyUser("Olowo", "test@lanre.com", "08120290933", "Nigeria"));
                myUsers.add(new MyUser("Mary", "test@mary.com", "55-455-2334", "Canada"));
                beanToCsv.write(myUsers);
            }
            catch (Exception ex){
            System.out.println("Cannot write file");
        }
    }
}
