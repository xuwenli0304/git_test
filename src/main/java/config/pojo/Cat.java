package config.pojo;

import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;
import config.pojo.definition.*;
import config.pojo.ConditionImpl;


@Conditional(ConditionImpl.class)
@Component
public class Cat implements Animal{
    @Override
    public void eat(){
        System.out.println("eat fish");
    }

    @Override
    public String getState(){
        return "c";
    }

    @Override
    public void setThis(int val){
        
    }
}
