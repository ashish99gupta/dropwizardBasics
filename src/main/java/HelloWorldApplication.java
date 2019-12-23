import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class HelloWorldApplication extends Application<HelloWorldConfiguration> {

    public static void main(String[] args) throws Exception {
        new HelloWorldApplication().run(args);
    }

    @Override
    public String getName() {
        return "hello-world";
    }

    @Override
    public void initialize(Bootstrap<HelloWorldConfiguration> bootstrap) {
        // nothing to do yet
    }

    public void run(HelloWorldConfiguration helloWorldConfiguration, Environment environment) {
        final HelloWorldResource resource = new HelloWorldResource(helloWorldConfiguration.getTemplate(),helloWorldConfiguration.getDefaultName());
        final SampleHealthCheck healthCheck = new SampleHealthCheck(helloWorldConfiguration.getTemplate());
        environment.healthChecks().register("template",healthCheck);

        environment.jersey().register(resource);
    }
}
