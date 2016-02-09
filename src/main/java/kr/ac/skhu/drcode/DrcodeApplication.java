package kr.ac.skhu.drcode;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@EnableConfigurationProperties
@EnableAutoConfiguration
@SpringBootApplication
public class DrcodeApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(DrcodeApplication.class, args);
	}

	@Bean(initMethod = "init")
	public InitDataLoader initDataLoader(){
		return new InitDataLoader();
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(DrcodeApplication.class);
	}
	
    
	

}