package com.employee.employee.security;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig
{
    @Bean
    public InMemoryUserDetailsManager configInMemoryUserDetailsManager(PasswordEncoder passwordEncoder)
    {
        // UserDetails user1 = User.builder().username("vijay").password("{noop}12345").roles("ADMIN").build();
        // UserDetails user2 = User.builder().username("praveen").password("{noop}12345").roles("Admin","Employee").build();
        // UserDetails user3 = User.builder().username("ajay").password("{bcrypt}12345").roles("Employee").build();

        // InMemoryUserDetailsManager user_list = new InMemoryUserDetailsManager(user1,user2,user3);

        // return user_list;

        UserDetails admin = User.withUsername("vijay").password(passwordEncoder.encode("vijay12")).roles("ADMIN").build();
        UserDetails manager = User.withUsername("praveen").password(passwordEncoder.encode("praveen12")).roles("MANAGER").build();
        UserDetails employee = User.withUsername("ajay").password(passwordEncoder.encode("ajay12")).roles("EMPLOYEE").build();

        return new InMemoryUserDetailsManager(admin,manager,employee);
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // http
        //     .authorizeHttpRequests((authorize)->
        //     {
        //         authorize.requestMatchers(HttpMethod.DELETE,"employee/**").hasRole("ADMIN")
        //                 .requestMatchers(HttpMethod.PUT,"employee/**").hasRole("MANAGER")
        //                 // .requestMatchers(HttpMethod.GET,"employee/**").hasRole("EMPLOYEE")
        //                 .anyRequest().authenticated();
        //     }).httpBasic().and().csrf().disable();
        // return http.build();
        http
            .authorizeHttpRequests((authorize) ->
                authorize
                    // allow DEETE for admin and manager
                    .requestMatchers(HttpMethod.DELETE, "/employee/**").hasAnyRole("ADMIN","MANAGER")
                    .requestMatchers(HttpMethod.POST, "/employee/save_emp_list/**").hasAnyRole("ADMIN","MANAGER")
                    

                    // allow PUT for manger and EMPLOYEE only
                    .requestMatchers(HttpMethod.PUT, "/employee/**").hasAnyRole("MANAGER","EMPLOYEE")

                    // allow get for all
                    .requestMatchers(HttpMethod.GET, "/employee/**").hasAnyRole("ADMIN", "MANAGER","EMPLOYEE")

                    // Allow POST for EMPLOYEE
                    .requestMatchers(HttpMethod.POST, "/employee/**").hasRole("EMPLOYEE")

                    // Authenticate for any other requests
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
}
