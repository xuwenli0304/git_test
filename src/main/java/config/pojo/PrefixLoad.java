package config.pojo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource(value = {"file:///D:/git_test/src/main/resources/p.properties"}, 
ignoreResourceNotFound = false)
@ConfigurationProperties("p")
public class PrefixLoad {
    private String a;

    public void setA(String a) {
        this.a = a;
    }

    public String getA(){
        return this.a;
    }
}
