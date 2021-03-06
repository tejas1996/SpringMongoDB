package mongodata.reference;

import mongodata.FundRaisingRepo;
import mongodata.objects.FundRaisingSumm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RetrieveAllService {

    @Autowired
    private FundRaisingRepo repository;

    public List<FundRaisingSumm> getAll() throws Exception {

        // fetch all customers
        for (FundRaisingSumm year: repository.findAll()) {
            System.out.println(year);
        }

        return repository.findAll();


    }

}
