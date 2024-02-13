package in.gov.wildlife.mis.credential.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;



/**
 * Custom class to add the configuration for the interceptors for all the requests
 *
 *
 * */

@Configuration
public class CustomizedWebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(clientRequestInterceptor());
    }

    @Bean
    @Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
    public RequestMeta requestMeta() {
        // stores tenant id and request id which can be accessed
        // anywhere in the application
        return new RequestMeta();
    }

    @Bean
    public ClientRequestInterceptor clientRequestInterceptor() {
        // set requestId and tenantId header values to RequestMeta
        return new ClientRequestInterceptor(requestMeta());
    }

//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/*")
//                .allowedOrigins("http://localhost:3000")
//                .allowedMethods(GET.name(), POST.name(), PUT.name(), PATCH.name(), DELETE.name(), OPTIONS.name())
//                .allowedHeaders("*")
//                .allowCredentials(true)
//
//                .maxAge(MAXAGESECS);
//    }
}
