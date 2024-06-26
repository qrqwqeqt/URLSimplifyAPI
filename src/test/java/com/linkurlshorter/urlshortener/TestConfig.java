package com.linkurlshorter.urlshortener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.linkurlshorter.urlshortener.auth.AuthService;
import com.linkurlshorter.urlshortener.jwt.JwtUtil;
import com.linkurlshorter.urlshortener.link.LinkRepository;
import com.linkurlshorter.urlshortener.link.LinkService;
import com.linkurlshorter.urlshortener.link.dto.LinkInfoDtoMapper;
import com.linkurlshorter.urlshortener.link.generator.ShortLinkGenerator;
import com.linkurlshorter.urlshortener.security.CustomUserDetailsService;
import com.linkurlshorter.urlshortener.user.UserRepository;
import com.linkurlshorter.urlshortener.user.UserService;
import jakarta.persistence.EntityManager;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import redis.clients.jedis.JedisPool;

import static org.mockito.Mockito.mock;

/**
 * Configuration class for test environment.
 * Provides bean definitions for mocked services and repositories used in testing.
 *
 * @author Anastasiia Usenko
 */
@TestConfiguration
public class TestConfig {

    /**
     * Creates a mock bean for JwtUtil.
     *
     * @return JwtUtil mock bean
     */
    @Bean
    public JwtUtil jwtUtil() {
        return new JwtUtil();
    }

    /**
     * Creates a bean for CustomUserDetailsService with a mocked UserRepository dependency.
     *
     * @param userRepository UserRepository mock bean
     * @return CustomUserDetailsService bean with mocked UserRepository dependency
     */
    @Bean
    public CustomUserDetailsService customUserDetailsService(UserRepository userRepository) {
        return new CustomUserDetailsService(userRepository);
    }

    /**
     * Creates a bean for LinkInfoDtoMapper.
     *
     * @return LinkInfoDtoMapper bean
     */
    @Bean
    public LinkInfoDtoMapper linkInfoDtoMapper() {
        return new LinkInfoDtoMapper();
    }

    /**
     * Creates a mock bean for EntityManager.
     *
     * @return EntityManager mock bean
     */
    @Bean
    public EntityManager entityManager() {
        return mock(EntityManager.class);
    }

    /**
     * Creates a bean for LinkService with a mocked LinkRepository dependency.
     *
     * @param linkRepository LinkRepository mock bean
     * @return LinkService bean with mocked LinkRepository dependency
     */
    @Bean
    public LinkService linkService(LinkRepository linkRepository, JedisPool jedisPool, ObjectMapper objectMapper) {
        return new LinkService(linkRepository, jedisPool, objectMapper);
    }

    /**
     * Creates a bean for UserService with a mocked UserRepository dependency.
     *
     * @param userRepository UserRepository mock bean
     * @return UserService bean with mocked UserRepository dependency
     */
    @Bean
    public UserService userService(UserRepository userRepository) {
        return new UserService(userRepository);
    }

    /**
     * Creates a mock bean for LinkRepository.
     *
     * @return LinkRepository mock bean
     */
    @Bean
    public LinkRepository linkRepository() {
        return mock(LinkRepository.class);
    }

    /**
     * Creates a mock bean for UserRepository.
     *
     * @return UserRepository mock bean
     */
    @Bean
    public UserRepository userRepository() {
        return mock(UserRepository.class);
    }

    /**
     * Creates a mock bean for AuthService.
     *
     * @return AuthService mock bean
     */
    @Bean
    public AuthService authService() {
        return mock(AuthService.class);
    }

    /**
     * Creates a bean for ShortLinkGenerator with a mocked LinkService dependency.
     *
     * @param linkService LinkService mock bean
     * @return ShortLinkGenerator bean with mocked LinkService dependency
     */
    @Bean
    public ShortLinkGenerator shortLinkGenerator(LinkService linkService) {
        return new ShortLinkGenerator(linkService);
    }

    /**
     * Creates a bean for JedisPool.
     *
     * @return JedisPool bean
     */
    @Bean
    public JedisPool jedisPool() {
        return new JedisPool();
    }
}
