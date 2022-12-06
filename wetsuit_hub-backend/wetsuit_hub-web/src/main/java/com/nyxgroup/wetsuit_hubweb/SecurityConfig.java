//package com.nyxgroup.wetsuit_hubweb;
//
//import org.jetbrains.annotations.NotNull;
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//
//public class SecurityConfig {
//    @Bean
//    SecurityFilterChain filterChain(@NotNull HttpSecurity http) throws Exception {
//        return http
//                .requiresChannel(channel ->
//                        channel.anyRequest().requiresSecure())
//                .authorizeRequests(authorize ->
//                        authorize.anyRequest().permitAll())
//                .build();
//    }
//
//}
