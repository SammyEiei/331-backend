package se331.lab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://127.0.0.1:5173")  // ระบุต้นทางที่อนุญาตให้เข้าถึง
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")  // อนุญาตวิธี HTTP ที่ต้องการ
                        .allowedHeaders("*")  // อนุญาต headers ทั้งหมด
                        .exposedHeaders("x-total-count")  // headers ที่เปิดเผย
                        .allowCredentials(true);  // อนุญาตให้ส่ง credentials (เช่น cookies)
            }
        };
    }
}