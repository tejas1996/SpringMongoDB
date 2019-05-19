package mongodata.dataImports;

import mongodata.objects.FundRaisingSumm;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

@Service
public class ExcelReader {

    public static final String SAMPLE_XLSX_FILE_PATH = "/home/tejas/Desktop/demo.xlsx";

    public static ArrayList<FundRaisingSumm> getDataFromExcel(File file) throws Exception {

        Workbook workbook = WorkbookFactory.create(file);
        ArrayList<ArrayList<String>> aList  = new ArrayList<>();

        Iterator<Sheet> sheetIterator = workbook.sheetIterator();
        while (sheetIterator.hasNext()) {
            Sheet sheet = sheetIterator.next();
            System.out.println("=> " + sheet.getSheetName());
        }
        Sheet sheet = workbook.getSheetAt(0);

        // Create a DataFormatter to format and get each cell's value as String
        DataFormatter dataFormatter = new DataFormatter();

        Iterator<Row> rowIterator = sheet.rowIterator();
        int rowNumber = 0;
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();

            if (rowNumber < 5) {
                rowNumber++;
                continue;
            }
            Iterator<Cell> cellIterator = row.cellIterator();
            ArrayList<String> temp = new ArrayList<String>();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                String cellValue = dataFormatter.formatCellValue(cell);
                System.out.print(cellValue + "\t");
                temp.add(cellValue);
            }
            System.out.println();
            aList.add(temp);
        }
        System.out.println(aList);

        ArrayList<FundRaisingSumm> customerList = new ArrayList<>();
        for (int i = 0; i < aList.size(); i++) {
            FundRaisingSumm year = new FundRaisingSumm(aList.get(i).get(0), aList.get(i).get(1),aList.get(i).get(2),aList.get(i).get(3),aList.get(i).get(4),aList.get(i).get(5));
            customerList.add(year);
        }

        workbook.close();
        return customerList;
    }

    public ArrayList<FundRaisingSumm> getDataFfromFile(MultipartFile file) throws Exception {
        File filenew = convert(file);
        ArrayList<FundRaisingSumm> result = getDataFromExcel(filenew);
        return result;
    }

    public File convert(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        convFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }
}
