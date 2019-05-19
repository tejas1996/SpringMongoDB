package mongodata.reference;

import mongodata.Customer;
import mongodata.FundRaisingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InsertManualData {

    @Autowired
    private FundRaisingRepo repository;

//    public void insertData(String first, String second){
//        repository.save(new Customer(first, second));
//    }

}
