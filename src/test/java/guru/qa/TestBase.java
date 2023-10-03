package guru.qa;

import guru.qa.config.ApiTestsConfig;
import org.aeonbits.owner.ConfigFactory;

public class TestBase {

    protected static final ApiTestsConfig config = ConfigFactory.create(ApiTestsConfig.class, System.getProperties());

}