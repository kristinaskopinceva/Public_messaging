package connection.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
//класс при старте приложения конфигурирует websecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests() // вкл авторизация
                .antMatchers("/").permitAll() //дял страницы "/" разрешаем полный доступ, для остальных нужно логинится
                .anyRequest().authenticated()
                   .and()
                 .formLogin() // включаем формл логин
                .loginPage("/login") // указывает что страничка авторизации находится на меппинге /login
                .permitAll() // разрешаем этим пользоватся всем(логин.)
                   .and()
                .logout() // включаем возможность разлогиниватся
                .permitAll(); // даем эту возможность всем
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() { // настраивает хранилище пользоввтелей в памяти
        UserDetails user =
                User.withDefaultPasswordEncoder()
                        .username("u")
                        .password("p")
                        .roles("USER")
                        .build();

        return new InMemoryUserDetailsManager(user);

}}