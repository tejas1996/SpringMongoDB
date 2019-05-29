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

    public static ArrayList<ArrayList<String>> getDataFromExcel(File file, int start) throws Exception {

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

            if (rowNumber < start) {
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
        workbook.close();
        return aList;
    }

    public ArrayList<FundRaisingSumm> getDataFfromFile(MultipartFile file, int begin) throws Exception {
        File filenew = convert(file);
        int start=begin;
        ArrayList<ArrayList<String>> object = getDataFromExcel(filenew,start);

        ArrayList<FundRaisingSumm> fundRaisingList = new ArrayList<>();
        for (int i = 0; i < object.size(); i++) {
            FundRaisingSumm year = new FundRaisingSumm(object.get(i).get(0), object.get(i).get(1),object.get(i).get(2),object.get(i).get(3),object.get(i).get(4),object.get(i).get(5));
            fundRaisingList.add(year);
        }
        return fundRaisingList;
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
