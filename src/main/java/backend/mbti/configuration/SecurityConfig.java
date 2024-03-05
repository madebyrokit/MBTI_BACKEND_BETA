package backend.mbti.configuration;

import backend.mbti.jwt.JwtFilter;
import backend.mbti.sign.SignService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final SignService signService;

    @Value("${jwt.secret}")
    private String secretKey;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .formLogin().disable()
                .httpBasic().disable()
                .csrf().disable();
        httpSecurity
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        httpSecurity
                .authorizeRequests()
                .antMatchers("/**").permitAll()
                .anyRequest().authenticated();
        httpSecurity
                .addFilterBefore(new JwtFilter(signService, secretKey), UsernamePasswordAuthenticationFilter.class)
                .requestMatchers().antMatchers(HttpMethod.POST, "/mypage/**", "/mypage","/post")
                .requestMatchers().antMatchers(HttpMethod.GET, "/mypage", "/mypage/*/posts", "/mypage/**")
                .requestMatchers().antMatchers(HttpMethod.PUT, "/post/*", "/mypage/update-all", "/comment/*")
                .requestMatchers().antMatchers(HttpMethod.DELETE, "/post/*", "/mypage/*", "/comment/*");
        return httpSecurity.build();

    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("http://localhost:3000");
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
        configuration.setAllowedHeaders(Arrays.asList("authorization", "content-type", "x-auth-token"));
        configuration.addExposedHeader(HttpHeaders.AUTHORIZATION);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
