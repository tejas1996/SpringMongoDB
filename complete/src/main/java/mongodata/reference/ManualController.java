package mongodata.reference;

import mongodata.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ManualController {

    @Autowired
    InsertManualData insertManualData;

    @Autowired
    RetrieveAllService getallService;

    @RequestMapping("insert")
    public void insert(@RequestParam String first, @RequestParam String last){

    }

    @RequestMapping("get")
    public String getAll(){
        return null;
    }

}
