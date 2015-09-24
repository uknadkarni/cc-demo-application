package io.pivotal.cc.cacheserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.gemfire.function.config.EnableGemfireFunctionExecutions;
import org.springframework.data.gemfire.function.config.EnableGemfireFunctions;
import org.springframework.data.gemfire.repository.config.EnableGemfireRepositories;
/*
@Configuration
@EnableGemfireFunctions
@EnableGemfireRepositories
//@ImportResource("file:src/main/resources/cache-config.xml")
@ImportResource("classpath:cache-config.xml")
@SpringBootApplication
@ComponentScan(basePackages={"io.pivotal.cc.cacheserver", "io.pivotal.cc.common"})
*/

@Configuration
@EnableGemfireRepositories(basePackages={"io.pivotal.cacheserver","io.pivotal.cc.common"})
@EnableGemfireFunctionExecutions(basePackages={"io.pivotal.cacheserver","io.pivotal.cc.common"})
@ImportResource("cache-config.xml")
@ComponentScan(basePackages={"io.pivotal.cacheserver","io.pivotal.cc.common"})
@SpringBootApplication
public class CcDemoCacheserverApplication {

    public static void main(String[] args) {
         SpringApplication.run(CcDemoCacheserverApplication.class, args);
    }
}
