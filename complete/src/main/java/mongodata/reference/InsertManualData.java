package mongodata.reference;

import mongodata.Customer;
import mongodata.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InsertManualData {

    @Autowired
    private CustomerRepository repository;

    public void insertData(String first, String second){
        repository.save(new Customer(first, second));
    }

}
