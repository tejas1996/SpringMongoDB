package mongodata;

import mongodata.dataImports.ExcelReader;
import mongodata.objects.FundRaisingSumm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class InserService {

    @Autowired
    private FundRaisingRepo repository;

    public void insert() {

        repository.deleteAll();
        for (FundRaisingSumm year : repository.findAll()) {
            System.out.println(year);
        }



    }

    public void insertDataArray(ArrayList<FundRaisingSumm> arrayList) throws Exception {

        for (FundRaisingSumm year: arrayList) {
            repository.save(year);
        }

        // fetch all customer

    }

}
