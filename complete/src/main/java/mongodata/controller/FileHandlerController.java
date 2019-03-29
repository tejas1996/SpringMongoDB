package mongodata.controller;

import mongodata.Customer;
import mongodata.InserService;
import mongodata.InsertController;
import mongodata.dataImports.ExcelReader;
import mongodata.service.AmazonS3ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/files")
public class FileHandlerController {

    @Autowired
    private AmazonS3ClientService amazonS3ClientService;

    @Autowired
    InserService inserService;

    @Autowired
    ExcelReader excelReader;


    @PostMapping
    public Map<String, String> uploadFile(@RequestPart(value = "file") MultipartFile file) throws Exception {

        ArrayList<Customer> result = excelReader.getDataFfromFile(file);
        inserService.insertDataArray(result);
        this.amazonS3ClientService.uploadFileToS3Bucket(file, true);

        Map<String, String> response = new HashMap<>();
        response.put("message", "file [" + file.getOriginalFilename() + "] uploading request submitted successfully.");

        return response;
    }

    @DeleteMapping
    public Map<String, String> deleteFile(@RequestParam("file_name") String fileName)
    {
        this.amazonS3ClientService.deleteFileFromS3Bucket(fileName);

        Map<String, String> response = new HashMap<>();
        response.put("message", "file [" + fileName + "] removing request submitted successfully.");

        return response;
    }
}