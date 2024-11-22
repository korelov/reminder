package com.example.reminder.config;

//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {


//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests(authorizeRequests ->
//                        authorizeRequests
//                                .requestMatchers("/").permitAll()
//                                .anyRequest().authenticated()
//                )
//                .oauth2Login(oauth2Login ->
//                        oauth2Login
//                                .defaultSuccessUrl("/home", true)
//                );
//        return http.build();
//    }


//    @Bean
//    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        return http
//                .authorizeHttpRequests(auth -> {
//                    auth.requestMatchers("/").permitAll();
//                    auth.anyRequest().authenticated();
//                })
//                .oauth2Login(withDefaults())
//                .build();
//    }

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests(authorizeRequests ->
//                        authorizeRequests
//                                .requestMatchers("/").permitAll()
//                                .anyRequest().authenticated()
//                )
//                .oauth2Login(oauth2Login ->
//                        oauth2Login
//                                .defaultSuccessUrl("/home", true)
//                );
//        return http.build();
//    }


//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/api/v1/reminder/**").authenticated()
//                        .anyRequest().permitAll());
//
//        return http.build();
//    }

//}
