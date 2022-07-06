package esc.dc.Configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomLoginSuccessHandler successHandler;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Qualifier("dataSource")
    @Autowired
    private DataSource dataSource;

    @Value("${spring.queries.users-query}")
    private String usersQuery;

    @Value("${spring.queries.roles-query}")
    private String rolesQuery;
//
    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.
                jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery(usersQuery)
                .authoritiesByUsernameQuery(rolesQuery)
                .passwordEncoder(bCryptPasswordEncoder);
    }

    protected void configure(HttpSecurity http) throws Exception{

        http.csrf().disable().authorizeRequests()

                .antMatchers("/dashboard").hasAnyAuthority("CREATER","ADMIN","VIEWER","SUPERADMIN")
                .antMatchers("/view/**").hasAnyAuthority("CREATER","ADMIN","VIEWER","SUPERADMIN")
                .antMatchers("/create/**").hasAnyAuthority("CREATER","ADMIN","SUPERADMIN")
                .antMatchers("/admin/**").hasAnyAuthority("ADMIN","SUPERADMIN")
                .antMatchers("/superadmin/**").access("hasAuthority('SUPERADMIN')")
                .antMatchers("/").permitAll()
                .antMatchers("/error").permitAll()
                .antMatchers("/employee/**").permitAll()
                .antMatchers("/currentDate/**").permitAll()
                .antMatchers("/sideMenu/**").permitAll()
                .antMatchers("/roles").permitAll()
                .antMatchers("/test/**").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/console/**").permitAll()
                .antMatchers("/resources/**").permitAll()
                .antMatchers("/static/**").permitAll()
                .antMatchers("/css/**").permitAll()
                .antMatchers("/js/**").permitAll()
                .antMatchers("/new/**").permitAll()
                .antMatchers("/past/**").permitAll()
                .antMatchers("/img/**").permitAll()
                .antMatchers("/vendors/**").permitAll()
                .antMatchers("/images/**").permitAll()
                .antMatchers("/uploads/**").permitAll()
                .antMatchers("/set/**").permitAll()
                .antMatchers("/registration").permitAll()

//                .antMatchers("/dashboard/admin/**").hasRole("ADMIN")
//                .antMatchers("/dashboard/user/**").hasRole("USER")
//                .anyRequest().authenticated()
//                .and().csrf().disable().formLogin()
//                .loginPage("/login").failureUrl("/login?error=true")
//                .defaultSuccessUrl("/dashboard")
//                .usernameParameter("email")
//                .passwordParameter("password")
//                .and().logout()
//                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//                .logoutSuccessUrl("/").and().exceptionHandling()
//                .accessDeniedPage("/access-denied");


                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .failureUrl("/login?error=true")
                .successHandler(successHandler)
                .permitAll()
                .usernameParameter("email")
                .passwordParameter("password")
                .and()
                .logout().logoutSuccessUrl("/")
                .and()
                .exceptionHandling().accessDeniedPage("/403")
                .and()
                .headers()
                .frameOptions().disable();
        super.configure(http);

    }

}
