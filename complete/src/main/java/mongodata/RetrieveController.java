package mongodata;

import mongodata.objects.FundRaisingSumm;
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
            List<FundRaisingSumm> result = retrieveService.getAll();
            for (FundRaisingSumm year : result) {
                stringBuilder.append(year.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

}
