package config.pojo;

import org.springframework.stereotype.Component;
import config.pojo.definition.*;

@Component
public class Dog implements Animal{
    @Override
    public void eat(){
        System.out.println("eat meat");
    }

    @Override
    public String getState(){
        return "d";
    }

    @Override
    public void setThis(int val){
        
    }
}
