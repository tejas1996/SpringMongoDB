package mongodata.loginAuth.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InsertService {

    @Autowired
    private UserRepository repository;

    public void insert(User user) throws Exception {
        repository.save(user);

    }

}
