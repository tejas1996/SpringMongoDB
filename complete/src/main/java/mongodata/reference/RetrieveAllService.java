package mongodata.reference;

import mongodata.Customer;
import mongodata.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RetrieveAllService {

    @Autowired
    private CustomerRepository repository;

    public List<Customer> getAll() throws Exception {

        // fetch all customers
        for (Customer customer : repository.findAll()) {
            System.out.println(customer);
        }

        return repository.findAll();


    }

}
