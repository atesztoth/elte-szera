package atesztoth.elte.szeraj.rest;

import atesztoth.elte.szeraj.service.SzerajUserProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(WebSecurityConfig.class);

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .formLogin()
                    .loginPage("/login")
                    .failureUrl("/login-error")
                .and()
                    .logout()
                    .logoutSuccessUrl("/index");
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        SzerajUserProvider szerajUserProvider = new SzerajUserProvider();
        szerajUserProvider.loadUserByUsername("aa");
        InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();
        userDetailsManager.createUser(
                User.withUsername("aa")
                        .password("aa")
                        .roles("USER")
                        .build());
        return userDetailsManager;
    }

}