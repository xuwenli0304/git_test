package config.converter;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;


import com.google.gson.Gson;

import config.pojo.Dog;

/**** imports ****/
// 声明JdbcType为整形
@MappedJdbcTypes(JdbcType.VARCHAR)
// 声明JavaType为SexEnum
@MappedTypes(value=Dog.class)
public class PetConvertMB extends BaseTypeHandler<Dog> {
 
    // 通过列名读取性别
    @Override
    public Dog getNullableResult(ResultSet rs, String col) 
            throws SQLException {
        String dogString = rs.getString(col);
        // int sex = rs.getInt(col);
        // if (sex != 1 && sex != 2) {
        //     return null;
        // }
        return new Gson().fromJson(dogString, Dog.class);
    }

    // 通过下标读取性别
    @Override
    public Dog getNullableResult(ResultSet rs, int idx)
            throws SQLException {
        String dogString = rs.getString(idx);
        // int sex = rs.getInt(idx);
        // if (sex != 1 && sex != 2) {
        //     return null;
        // }
        return new Gson().fromJson(dogString, Dog.class);
    }
    
    
    // 通过存储过程读取性别
    @Override
    public Dog getNullableResult(CallableStatement cs, int idx)
            throws SQLException {
        String dogString = cs.getString(idx);
        // int sex = cs.getInt(idx);
        // if (sex != 1 && sex != 2) {
        //     return null;
        // }
        return new Gson().fromJson(dogString, Dog.class);
    }

    // 设置非空性别参数
    @Override
    public void setNonNullParameter(PreparedStatement ps, int idx,
            Dog dog, JdbcType jdbcType) throws SQLException {
        // ps.setInt(idx, sex.getId());
        ps.setString(idx, new Gson().toJson(dog));
    }
}
