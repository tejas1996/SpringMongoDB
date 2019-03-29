package mongodata;

import mongodata.dataImports.ExcelReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class InserService {

    @Autowired
    private CustomerRepository repository;

    @Autowired
    private ExcelReader excelReader;

    public void insert() throws Exception {

        repository.deleteAll();

        // save a couple of customers
        repository.save(new Customer("Tejas", "Dond"));
        repository.save(new Customer("Gaurav", "Massand"));

        // fetch all customers
        System.out.println("Customers found with findAll():");
        System.out.println("-------------------------------");
        for (Customer customer : repository.findAll()) {
            System.out.println(customer);
        }
        System.out.println();

        // fetch an individual customer
        System.out.println("Customer found with findByFirstName('Alice'):");
        System.out.println("--------------------------------");
        System.out.println(repository.findByFirstName("Alice"));

        System.out.println("Customers found with findByLastName('Smith'):");
        System.out.println("--------------------------------");


    }

    public void insertDataArray(ArrayList<Customer> arrayList) throws Exception {

        for (Customer customer : arrayList) {
            repository.save(customer);
        }

        // fetch all customer

    }

}
