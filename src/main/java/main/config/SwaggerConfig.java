package main.config;

import org.h2.index.MetaIndex;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.models.Contact;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import static springfox.documentation.builders.PathSelectors.regex;

@EnableSwagger2
@Configuration
public class SwaggerConfig {
	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.basePackage("main"))
				.paths(regex("/.*")).build().apiInfo(metaInfo());
	}

	private ApiInfo metaInfo() {
		ApiInfo apiInfo = new ApiInfo("Hadi Alharbi - Wallet API", 
				"Creating Customer and a Wallet by a rest API calls", "1.0",
				"https://www.apache.org/licenses/",null, "https://www.linkedin.com/in/hadihalharbi/",
				"https://www.linkedin.com/in/hadihalharbi/");

		return apiInfo;
	}

}
