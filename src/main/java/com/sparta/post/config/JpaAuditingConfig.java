package com.sparta.post.config;

import com.sparta.post.entity.User;
import com.sparta.post.jwt.SecurityUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

@Configuration
public class JpaAuditingConfig {

    @Bean
    public AuditorAware<User> auditorAware() {
        return SecurityUtil::getPrincipal;
    }
}
