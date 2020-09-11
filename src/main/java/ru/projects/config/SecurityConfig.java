package ru.projects.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.projects.services.UserService;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    public static final String ADMIN = "ADMIN";
    public static final String USER = "USER";
    public static final String DEFAULT_LOGIN_PROCESSING_URL = "/authenticateTheUser";
    private DataSource myDataSource;
    private UserService userService;
    private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;


    @Autowired
    public void setMyDataSource(DataSource myDataSource) {
        this.myDataSource = myDataSource;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setCustomAuthenticationSuccessHandler(CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler) {
        this.customAuthenticationSuccessHandler = customAuthenticationSuccessHandler;
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        /*User.UserBuilder userBuilder = User.builder();

        auth.inMemoryAuthentication()
                .withUser(userBuilder.username("admin").password("{noop}1").roles(ADMIN, USER))
                .withUser(userBuilder.username("user").password("{noop}1").roles(USER));*/

//        auth.jdbcAuthentication().dataSource(myDataSource);

        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/api/**").hasAnyRole(USER, ADMIN)
                .antMatchers("/api/**").permitAll()
                .and()
                .httpBasic()
                .and()
                .csrf().ignoringAntMatchers("/api/**");

        http.authorizeRequests()
                .antMatchers("/").hasAnyRole(ADMIN, USER)
                .antMatchers("/admin/**").hasAnyRole(ADMIN)
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl(DEFAULT_LOGIN_PROCESSING_URL)
                .successHandler(customAuthenticationSuccessHandler)
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userService);
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }
}
