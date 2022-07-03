package config.pojo;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class ConditionImpl implements Condition{
    @Override
	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
		Environment env = context.getEnvironment();
        boolean res = env.containsProperty("database.driverName") && env.containsProperty("database.url") 
        && env.containsProperty("database.username") && env.containsProperty("database.password");
        // return true;
        res = false;
        System.out.println("the match res is... " + res);
		return res;
	}
}
