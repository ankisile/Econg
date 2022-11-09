package com.example.bunjang.config;

import com.example.bunjang.jwt.JwtAccessDeniedHandler;
import com.example.bunjang.jwt.JwtAuthenticationEntryPoint;
import com.example.bunjang.jwt.JwtSecurityConfig;
import com.example.bunjang.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;

//@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) //@PreAuthorize 어노테이션을 메소드 단위로 추가하기 위해 사용
class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
    private final JwtSecurityConfig jwtSecurityConfig;

    private static final String[] PERMIT_URL_ARRAY = {
            /* swagger v2 */
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            /* swagger v3 */
            "/v3/api-docs/**",
            "/swagger-ui/**"
    };

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().disable()		//cors방지
                .formLogin().disable()	//기본 로그인 페이지 없애기
                .headers().frameOptions().disable();

        http
                // token을 사용하는 방식이기 때문에 csrf를 disable합니다.
                .csrf().disable()  // csrf 보안 토큰 disable처리.

                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDeniedHandler)
                // 세션을 사용하지 않기 때문에 STATELESS로 설정
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests() // Http Servlet Request를 사용하는 요청에 대해 접근 권한 설정
                .antMatchers("/app/login").permitAll() //  인증없이 접근 허용
                .antMatchers("/app/register").permitAll()
                .antMatchers("/app/orders/pay/completed").permitAll()
                .antMatchers("/app/orders/pay/fail").permitAll()
                .antMatchers("/app/orders/pay/cancel").permitAll()
                .antMatchers(PERMIT_URL_ARRAY).permitAll()
                .anyRequest().authenticated() // 그외 나머지 요청은 모두 인증받아야 함

                .and()
                .apply(jwtSecurityConfig)
                ;
    }

//    bean을 생성하면 @Autowired를 통해 PasswordEncoder를 선언할때 자동으로 클래스가 바인딩 된다.
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
