package config.pojo;

import config.pojo.definition.Animal;
import config.pojo.definition.AnimalValidator;

public class AnimalValidatorImpl implements AnimalValidator{
    @Override
    public boolean validate(Animal animal){
        System.out.println("calling AnimalValidatorImpl.validate...............");
        return animal.getState().equals("d");
    }
}
