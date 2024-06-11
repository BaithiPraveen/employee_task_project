package com.employee.employee.security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.employee.employee.repository.UserRepository;
// import com.employee.employee.service.UserInfoUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig
{

    @Bean
    public InMemoryUserDetailsManager configInMemoryUserDetailsManager(PasswordEncoder passwordEncoder)
    {

        UserDetails admin = User.withUsername("vijay").password(passwordEncoder.encode("vijay12")).roles("ADMIN").build();
        UserDetails manager = User.withUsername("praveen").password(passwordEncoder.encode("praveen12")).roles("MANAGER").build();
        UserDetails employee = User.withUsername("ajay").password(passwordEncoder.encode("ajay12")).roles("EMPLOYEE").build();

        return new InMemoryUserDetailsManager(admin,manager,employee);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .authorizeHttpRequests((authorize) ->
            authorize
                .requestMatchers(HttpMethod.DELETE, "/employee/**").hasAnyRole("ADMIN", "MANAGER")
                .requestMatchers(HttpMethod.POST, "/employee/save_emp_list/**").hasAnyRole("ADMIN", "MANAGER")
                .requestMatchers(HttpMethod.PUT, "/employee/**").hasAnyRole("MANAGER", "EMPLOYEE")
                .requestMatchers(HttpMethod.GET, "/employee/**").hasAnyRole("ADMIN", "MANAGER", "EMPLOYEE")
                .requestMatchers(HttpMethod.POST, "/employee/**").hasAnyRole("EMPLOYEE","ADMIN")
                .anyRequest().authenticated()
        )
        .httpBasic()
        .and()
        .csrf().disable();

    return http.build();
}

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }



    

    // @Bean
    // public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    //     // http
    //     //     .authorizeHttpRequests((authorize)->
    //     //     {
    //     //         authorize.requestMatchers(HttpMethod.DELETE,"employee/**").hasRole("ADMIN")
    //     //                 .requestMatchers(HttpMethod.PUT,"employee/**").hasRole("MANAGER")
    //     //                 // .requestMatchers(HttpMethod.GET,"employee/**").hasRole("EMPLOYEE")
    //     //                 .anyRequest().authenticated();
    //     //     }).httpBasic().and().csrf().disable();
    //     // return http.build();
    //     http
    //         .authorizeHttpRequests((authorize) ->
    //             authorize
    //                 // allow DEETE for admin and manager
    //                 .requestMatchers(HttpMethod.DELETE, "/employee/**").hasAnyRole("ADMIN","MANAGER")
    //                 .requestMatchers(HttpMethod.POST, "/employee/save_emp_list/**").hasAnyRole("ADMIN","MANAGER")


    //                 // allow PUT for manger and EMPLOYEE only
    //                 .requestMatchers(HttpMethod.PUT, "/employee/**").hasAnyRole("MANAGER","EMPLOYEE")

    //                 // allow get for all
    //                 .requestMatchers(HttpMethod.GET, "/employee/**").hasAnyRole("ADMIN", "MANAGER","EMPLOYEE")

    //                 // Allow POST for EMPLOYEE
    //                 .requestMatchers(HttpMethod.POST, "/employee/**").hasRole("EMPLOYEE")

    //                 // Authenticate for any other requests
    //                 .anyRequest().authenticated()
    //         )
    //         .httpBasic()
    //         .and()
    //         .csrf().disable();

    //     return http.build();
    // }

    
}
