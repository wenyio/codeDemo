package com.iscolt.weatherforecast;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class WeatherForecastApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeatherForecastApplication.class, args);
    }

    /**
     * 解决RestTemplate 无法自动注入问题, 可以单独配置
     *
     * @return
     */
    @Bean
    public RestTemplate restTemplate () {
        return new RestTemplate();
    }
}
