package mongodata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RetrieveService {

    @Autowired
    private CustomerRepository repository;

    public List<Customer> getAll() throws Exception {

        // fetch all customers
        for (Customer customer : repository.findAll()) {
            System.out.println(customer);
        }

        return repository.findAll();


    }

    public String getOne(String firstName) {

        // fetch an individual customer
        return repository.findByFirstName(firstName).toString();
    }


}
