package com.maple;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.ImportResource;


@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackages = {"com"})
@ImportResource(value = "classpath:applicationContext.xml")
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class MapleSpringTrialApplication {

	public static void main(String[] args) {
		SpringApplication.run(MapleSpringTrialApplication.class, args);
	}
}
