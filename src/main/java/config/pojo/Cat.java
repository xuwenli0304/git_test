package config.pojo;

import org.springframework.stereotype.Component;
import config.pojo.definition.*;

@Component
public class Cat implements Animal{
    @Override
    public void eat(){
        System.out.println("eat fish");
    }
}
