import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.jdbi3.JdbiFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.jdbi.v3.core.Jdbi;

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
        final JdbiFactory factory = new JdbiFactory();
        final Jdbi jdbi = factory.build(environment, helloWorldConfiguration.getDataSourceFactory(), "postgresql");
        environment.jersey().register(resource);
    }
}
