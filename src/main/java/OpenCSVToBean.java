import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;

public class OpenCSVToBean {
    private static final String CSV_FILE_PATH = "src/main/resources/user.csv";

    public static void main(String[] args) {
        try{
            readToBeanByStrategy();
        }catch (IOException ex){
            System.out.println("Cannot read file");
        }
    }

    private static void readToBean() throws IOException {
        Reader reader = Files.newBufferedReader(Paths.get(CSV_FILE_PATH));
        CsvToBean<CSVUser> csvToBean = new CsvToBeanBuilder<CSVUser>(reader)
                .withType(CSVUser.class).withIgnoreLeadingWhiteSpace(true)
                .build();
        List<CSVUser> records = csvToBean.parse();

        for(CSVUser record: records){
            System.out.println("Name: " + record.getName());
            System.out.println("Email: " + record.getEmail());
            System.out.println("Phone: " + record.getPhoneNo());
            System.out.println("Country: " + record.getCountry());
            System.out.println("-*".repeat(20));
        }
    }

    private static void readToBeanByPosition() throws IOException {
        Reader reader = Files.newBufferedReader(Paths.get(CSV_FILE_PATH));
        CsvToBean<CSVUserPosition> csvToBean = new CsvToBeanBuilder<CSVUserPosition>(reader)
                .withSkipLines(1)
                .withType(CSVUserPosition.class).withIgnoreLeadingWhiteSpace(true)
                .build();
        List<CSVUserPosition> records = csvToBean.parse();

        for(CSVUserPosition record: records){
            System.out.println("Name: " + record.getName());
            System.out.println("Email: " + record.getEmail());
            System.out.println("Phone: " + record.getPhoneNo());
            System.out.println("Country: " + record.getCountry());
            System.out.println("-+".repeat(20));
        }
    }

    private static void readToBeanByStrategy() throws IOException {
        Reader reader = Files.newBufferedReader(Paths.get(CSV_FILE_PATH));
        ColumnPositionMappingStrategy strategy = new ColumnPositionMappingStrategy();
        strategy.setType(MyUser.class);
        String[] memberFieldsToBindTo = {"name", "email", "phoneNo", "country"};
        strategy.setColumnMapping(memberFieldsToBindTo);

        CsvToBean<MyUser> csvToBean = new CsvToBeanBuilder(reader)
                .withMappingStrategy(strategy)
                .withSkipLines(1)
                .withIgnoreLeadingWhiteSpace(true)
                .build();

        Iterator<MyUser> myUserIterator = csvToBean.iterator();

        while (myUserIterator.hasNext()) {
            MyUser myUser = myUserIterator.next();
            System.out.println("Name : " + myUser.getName());
            System.out.println("Email : " + myUser.getEmail());
            System.out.println("PhoneNo : " + myUser.getPhoneNo());
            System.out.println("Country : " + myUser.getCountry());
            System.out.println("->".repeat(25));
        }
    }


}
