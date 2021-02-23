package com.delta.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author: ACE.CHIU
 * @create: 2021-02-22
 */
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
//  public static final String AUTHORIZATION_HEADER = "Authorization";

  @Value("${swagger.enabled}")
  private Boolean enabled;

  @Value("${swagger.host.name}")
  private String hostName;

//  @Autowired
//  private BuildProperties buildProperties;

  @Bean
  public Docket createRestApi() throws NullPointerException {
    Docket docket = null;
    docket = new Docket(DocumentationType.SWAGGER_2)
        .enable(enabled)
//        .apiInfo(apiInfo())
        .host(hostName)
        .select()
        .apis(RequestHandlerSelectors.basePackage("com.delta"))
        .paths(PathSelectors.any())
        .build();
    if (docket == null) {
      throw new NullPointerException("docket is null");
    }
//    docket.securityContexts(Lists.newArrayList(securityContext()))
//        .securitySchemes(Lists.newArrayList(apiKey()));
    return docket;
  }

//  private ApiInfo apiInfo() {
//    String gitCommitIdDescribe = buildProperties.get("git.commit.id.describe");
//    return new ApiInfoBuilder()
//        .title(buildProperties.getName())
//        .description(buildProperties.get("project.description"))
//        .termsOfServiceUrl("")
//        .version(buildProperties.getVersion().replace("${git.commit.id.describe}", gitCommitIdDescribe))
//        .build();
//  }

//  private ApiKey apiKey() {
//    return new ApiKey("JWT", AUTHORIZATION_HEADER, "header");
//  }
//
//  private SecurityContext securityContext() {
//    return SecurityContext.builder()
//        .securityReferences(defaultAuth())
//        .forPaths(PathSelectors.any())
//        .build();
//  }
//
//  List<SecurityReference> defaultAuth() {
//    AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
//    AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
//    authorizationScopes[0] = authorizationScope;
//    return Lists.newArrayList(new SecurityReference("JWT", authorizationScopes));
//  }
}
