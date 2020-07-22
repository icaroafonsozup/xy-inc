package br.com.zup.xyinc.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.bind.annotation.RequestMethod
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.builders.ResponseMessageBuilder
import springfox.documentation.service.ApiInfo
import springfox.documentation.service.Contact
import springfox.documentation.service.ResponseMessage
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2
import java.util.*


@Configuration
@EnableSwagger2
class SwaggerConfiguration {
    @Bean
    open fun api(): Docket? {
        return Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.zup.xyinc.controller"))
                .paths(PathSelectors.any())
                .build()
                .useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.GET, responseMessage())
                .globalResponseMessage(RequestMethod.POST, responseMessage())
                .apiInfo(apiInfo())
    }

    private fun responseMessage(): List<ResponseMessage?>? {
        return object : ArrayList<ResponseMessage?>() {
            init {
                add(ResponseMessageBuilder()
                        .code(400)
                        .message("Bad Request")
                        .build())
                add(ResponseMessageBuilder()
                        .code(404)
                        .message("Not Found")
                        .build())
            }
        }
    }

    private fun apiInfo(): ApiInfo {
        return ApiInfoBuilder()
                .title("Spring Boot REST API")
                .description("Aplicação Spring Boot REST API, construído para teste interno da ZUP")
                .version("1.0.0")
                .license("MIT license")
                .contact(Contact("Icaro", "https://github.com/icaroafonsozup/", "icaro.afonso@zup.com.br"))
                .build()
    }
}