package mongodata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InsertController {

    @Autowired
    InserService inserService;

    @RequestMapping("insert2")
    public void insert(){

        try {
            inserService.insert();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
