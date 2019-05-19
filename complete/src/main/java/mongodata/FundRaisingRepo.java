package mongodata;

import mongodata.Customer;
import mongodata.objects.FundRaisingSumm;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface FundRaisingRepo extends MongoRepository<FundRaisingSumm, String> {

}
