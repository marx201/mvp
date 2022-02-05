package com.openworld.mvp.bm.router;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class RouterImportCsvHelper {
    public static String TYPE = "text/csv";
    private static final String SERIAL_NUMBER_HEADER_LINE = "SerialNumber";
    private static final String MAC_ADDRESS_HEADER_LINE = "MacAddress";

    public static List<RouterBE> csvToRouters(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {
            List<RouterBE> routers = new ArrayList<RouterBE>();
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();
            for (CSVRecord csvRecord : csvRecords) {
                RouterBE router = new RouterBE();
                router.setMacAddress(csvRecord.get(MAC_ADDRESS_HEADER_LINE));
                router.setSerialNumber(csvRecord.get(SERIAL_NUMBER_HEADER_LINE));
                routers.add(router);
            }
            return routers;
        } catch (IOException e) {
            throw new RuntimeException("Fail to parse CSV file: " + e.getMessage());
        }
    }
}
