import com.codahale.metrics.health.HealthCheck;

public class SampleHealthCheck extends HealthCheck {
    private final  String health;

    public SampleHealthCheck(String health) {
        this.health = health;
    }


    @Override
    protected Result check() throws Exception {
        final  String saying = String.format(health, "TEST");
        if (!saying.contains("TEST")){
            return Result.unhealthy("Template doesn't include a name");
        }
        return Result.healthy();
    }
}
