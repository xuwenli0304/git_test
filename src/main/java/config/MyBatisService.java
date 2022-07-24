package config;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.google.gson.Gson;

import java.util.List;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;

import config.dao.JpaUserRepository;
import config.dao.MyBatisUserDao;
import config.pojo.*;
import config.pojo.definition.Animal;
import config.pojo.definition.AnimalValidator;
import config.pojo.interceptor.MyAspect1;
import config.pojo.interceptor.MyPlugin;


@Service
public class MyBatisService {

    @Autowired
    public MyBatisUserDao myBatisUserDao;

    @org.springframework.transaction.annotation.Transactional(isolation = Isolation.READ_COMMITTED,
    timeout = 1, propagation = Propagation.REQUIRES_NEW)
    public int addUserMPart(UserDB userDB){
        return myBatisUserDao.insertUser(userDB);
    }
}
