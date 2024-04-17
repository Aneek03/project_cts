package com.aneek.book.config;


import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//            .csrf().disable()
//            .authorizeRequests()
//            .anyRequest().authenticated()
//            .and()
//            .httpBasic();
//        return http.build();
   
	
	
//    	http
//        .csrf().disable()
//        .authorizeRequests()
//        .requestMatchers(HttpMethod.DELETE, "/user/**").hasRole("ADMIN") // Only allow admins to delete users
//        .anyRequest().authenticated()
//        .and()
//        .httpBasic();
//    return http.build();
    


//   }
   
//   // Define a bean for the password encoder
//  @Bean
//   public BCryptPasswordEncoder passwordEncoder() {
//      return new BCryptPasswordEncoder();
//   }
   
//   @Bean
//   public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
    
   
   @Bean
   public FilterRegistrationBean coresFilter() {
       UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

       CorsConfiguration corsConfiguration = new CorsConfiguration();
       corsConfiguration.setAllowCredentials(true);
       corsConfiguration.addAllowedOriginPattern("*");
       corsConfiguration.addAllowedHeader("Authorization");
       corsConfiguration.addAllowedHeader("Content-Type");
       corsConfiguration.addAllowedHeader("Accept");
       corsConfiguration.addAllowedMethod("POST");
       corsConfiguration.addAllowedMethod("GET");
       corsConfiguration.addAllowedMethod("DELETE");
       corsConfiguration.addAllowedMethod("PUT");
       corsConfiguration.addAllowedMethod("OPTIONS");
       corsConfiguration.setMaxAge(3600L);

       source.registerCorsConfiguration("/**", corsConfiguration);

       FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));

       bean.setOrder(-110);

       return bean;
   }
   
   
}



