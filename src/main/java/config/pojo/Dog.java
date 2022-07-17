package config.pojo;

import org.springframework.stereotype.Component;
import config.pojo.definition.*;

@Component
public class Dog implements Animal{

    private String name;

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

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
