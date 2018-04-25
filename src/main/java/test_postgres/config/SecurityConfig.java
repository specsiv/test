//package test_postgres.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import test_postgres.security.SecurityAuthenticationProvider;
//
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    private SecurityAuthenticationProvider securityAuthenticationProvider;
//
////    @Bean
////    public SecurityAuthenticationProvider securityAuthenticationProvider() {
////        return new SecurityAuthenticationProvider();
////    }
//
//    @Autowired
//    public SecurityConfig(SecurityAuthenticationProvider securityAuthenticationProvider) {
//        this.securityAuthenticationProvider = securityAuthenticationProvider;
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable().authorizeRequests()
////                .antMatchers(HttpMethod.POST, "/api/auth").permitAll()
//                .antMatchers(HttpMethod.GET, getSwaggerPatterns()).permitAll()
//                .anyRequest().authenticated()
//                .and();
//    }
//
//    private static String[] getSwaggerPatterns() {
//        return new String[]{"/swagger-ui.html", "/webjars/springfox-swagger-ui/**", "/swagger-resources/**", "/v2/api-docs**"};
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(securityAuthenticationProvider);
//    }
//}