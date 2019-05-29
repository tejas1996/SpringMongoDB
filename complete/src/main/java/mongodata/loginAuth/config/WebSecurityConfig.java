package mongodata.loginAuth.config;

import mongodata.loginAuth.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserRepository userRepository;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "/home").permitAll()
                .antMatchers("/geographical").access("hasRole('user') or hasRole('admin')")
                .antMatchers("/user/retrieve").access("hasRole('admin')")
                .antMatchers("/financial").access("hasRole('admin')")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(inMemoryUserDetailsManager());
    }

    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
        UserDetails user =
                User.withDefaultPasswordEncoder()
                        .username("super")
                        .password("admin")
                        .roles("admin")
                        .build();
        List<mongodata.loginAuth.dao.User> list = userRepository.findAll();
        ArrayList<UserDetails> userDetails = new ArrayList<>();
        userDetails.add(user);
        for (mongodata.loginAuth.dao.User user1 : list) {
            UserDetails users = User.withDefaultPasswordEncoder()
                    .username(user1.userName)
                    .password(user1.password)
                    .roles(user1.role)
                    .build();
            userDetails.add(users);
        }

        return new InMemoryUserDetailsManager(userDetails);
    }

}