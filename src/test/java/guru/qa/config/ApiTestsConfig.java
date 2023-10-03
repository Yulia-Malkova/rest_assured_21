package guru.qa.config;

import org.aeonbits.owner.Config;

@ApiTestsConfig.Sources({
        "classpath:config.properties"
})

public interface ApiTestsConfig extends Config {

    @Key("baseUrl")
    @DefaultValue("https://reqres.in")
    String baseUrl();

    @Key("basePath")
    @DefaultValue("/api")
    String basePath();
}
