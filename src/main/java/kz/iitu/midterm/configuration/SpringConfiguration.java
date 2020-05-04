package kz.iitu.midterm.configuration;

import kz.iitu.midterm.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SpringConfiguration extends WebSecurityConfigurerAdapter {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("in.toin.studentCourse.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Spring Data API Documentattion")
                .description("Endterm project for Java-2")
                .version("1.0.0")
                .build();
    }
    @Autowired
    private UserServiceImpl userService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/auth/**").permitAll()
                .antMatchers("/users/welcome/**").permitAll()
                .antMatchers("/users/create").hasAuthority("ADMIN")
                .antMatchers("/client/add").hasAuthority("ADMIN")
                .antMatchers("/client/update").hasAuthority("ADMIN")
                .antMatchers("/movie/add").hasAuthority("ADMIN")
                .antMatchers("/movie/update").hasAuthority("ADMIN")
                .antMatchers("/purchase/add").hasAuthority("ADMIN")
                .antMatchers("/purchase/update").hasAuthority("ADMIN")
                .antMatchers("/ticket/add").hasAuthority("ADMIN")
                .antMatchers("/ticket/update").hasAuthority("ADMIN")
//                .antMatchers("/users/create").permitAll()
                .anyRequest().authenticated()
                .and().formLogin()
                .and()
                .addFilter(new JwtTokenGeneratorFilter(authenticationManager()))

                .addFilterAfter(new JwtTokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService)
                .passwordEncoder(passwordEncoder());
    }
}
