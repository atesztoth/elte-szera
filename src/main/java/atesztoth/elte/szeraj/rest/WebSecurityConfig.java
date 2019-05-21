package atesztoth.elte.szeraj.rest;

import atesztoth.elte.szeraj.Domain.Role;
import atesztoth.elte.szeraj.service.SzerajUserDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(WebSecurityConfig.class);

    @Autowired
    SzerajUserDetailsService szerajUserDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        http
            .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/dashboard").hasRole(Role.GUEST.toString())
                .antMatchers(HttpMethod.POST, "/dashboard").hasRole(Role.GUEST.toString())
                .antMatchers(HttpMethod.GET, "/guest/**").hasRole(Role.GUEST.toString())
                .antMatchers(HttpMethod.GET,"/receptionist/**").hasRole(Role.RECEPTIONIST.toString())
                .antMatchers("/login*").permitAll()
                .and()
            .csrf().disable()
            .formLogin()
                .loginPage("/login")
                .successForwardUrl( "/guest/dispatch" )
                .permitAll()
                .and()
            .logout()
                .logoutUrl("/logout").permitAll()
                    .logoutSuccessUrl("/")
                    .permitAll();
        // @formatter:on
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(szerajUserDetailsService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }

}