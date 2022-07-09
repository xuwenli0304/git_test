package config.pojo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("dev")
@Component
public class ConfigFileInjection {
    //@Value("${a.b}")
    @Value("${database.password}")
    private String c;

    public void setC(String c){
        this.c = c;
    }

    public String getC(){
        return this.c;
    }
}
