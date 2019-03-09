package mongodata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RetrieveController {

    @Autowired
    RetrieveService retrieveService;

    @RequestMapping("/getAll")
    public String getAllData(){

        StringBuilder stringBuilder = new StringBuilder("data is \n");
        try {
            List<Customer> result = retrieveService.getAll();
            for (Customer customer : result) {
                stringBuilder.append(customer.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    @RequestMapping("/getRow")
    public String getRow(@RequestParam String name){
        return retrieveService.getOne(name);
    }
}
