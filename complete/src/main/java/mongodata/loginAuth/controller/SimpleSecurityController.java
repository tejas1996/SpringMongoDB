package mongodata.loginAuth.controller;

import mongodata.loginAuth.dao.InsertService;
import mongodata.loginAuth.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class SimpleSecurityController {

    private final InMemoryUserDetailsManager inMemoryUserDetailsManager;
    @Autowired
    InsertService insertService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    public SimpleSecurityController(InMemoryUserDetailsManager inMemoryUserDetailsManager) {
        this.inMemoryUserDetailsManager = inMemoryUserDetailsManager;
    }

    @GetMapping("/add")
    public String add(@RequestParam String userName, @RequestParam String password, @RequestParam String role) {
        UserDetails user =
                User.withDefaultPasswordEncoder()
                        .username(userName)
                        .password(password)
                        .roles(role)
                        .build();
        // push to mongo
        inMemoryUserDetailsManager.createUser(user);
        mongodata.loginAuth.dao.User user1 = new mongodata.loginAuth.dao.User(userName, password, role);
        try {
            insertService.insert(user1);
        } catch (Exception e) {
            System.out.println("Error adding user");
        }
        return "user added";
    }

    @GetMapping("/retrieve")
    public String ret() {
        StringBuilder stringBuilder = new StringBuilder("users: ");
        for (mongodata.loginAuth.dao.User customer : userRepository.findAll()) {
            stringBuilder.append(customer);
        }
        return stringBuilder.toString();
    }


}