package config.converter;

import javax.persistence.AttributeConverter;

import com.google.gson.Gson;

import config.pojo.Dog;


/**** imports ****/
public class PetConvertJPA 
        implements AttributeConverter<Dog, String>{

    // 将枚举转换为数据库列
    @Override
    public String convertToDatabaseColumn(Dog dog) {
        String dogString = new Gson().toJson(dog);
        return dogString;
    }

    // 将数据库列转换为枚举
    @Override
    public Dog convertToEntityAttribute(String dogString) {
        return new Gson().fromJson(dogString, Dog.class);
    }
}

