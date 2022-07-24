//package com.springboot.chapter1.main;

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
import org.springframework.transaction.annotation.Isolation;

import config.dao.JpaUserRepository;
import config.dao.MyBatisUserDao;
import config.pojo.*;
import config.pojo.definition.Animal;
import config.pojo.definition.AnimalValidator;
import config.pojo.interceptor.MyAspect1;
import config.pojo.interceptor.MyPlugin;


// @SpringBootApplication
// @Controller
// @EnableAutoConfiguration

@RestController
@EnableAutoConfiguration
@ComponentScan(basePackages = {"config.*", "config.dao.*"})
@EnableJpaRepositories(basePackages = "config.dao")
@EntityScan(basePackages = "config.pojo")
@MapperScan(basePackages="config", annotationClass = Repository.class)
public class Chapter1Main {

    @PersistenceContext
    public EntityManager entityManager;

    @Autowired
    private MyBatisUserDao myBatisUserDao;



    @Bean(name = "aspect1")
    public MyAspect1 initMyAspect1(){
        return new MyAspect1();
    }

    @Value("${a.b}")
    private String ab;
	
    @Autowired
    Environment environment;

    @Autowired
    private PrefixLoad prefixLoad;

    @Autowired
    private ConfigFileInjection configFileInjection;

    @Autowired
    private Animal dog;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private JpaUserRepository jpaUserRepository;

	public static void main(String[] args) {
		SpringApplication.run(Chapter1Main.class, args);
	}


    @Autowired 
    SqlSessionFactory sqlSessionFactory = null; 

    // Spring Bean 生命周期执行方法 加入插件
    @PostConstruct 
    public void initMyBatis() { 
    // 插件实例
    Interceptor plugin =new MyPlugin(); 
    // 设置插件属性
    Properties properties = new Properties();
    properties.setProperty ("keyl" , "valuel") ; 
    plugin.setProperties(properties) ; 
    //在 sqlSessionFactory 中添加插件
    sqlSessionFactory.getConfiguration().addInterceptor(plugin); 
    System.out.println("working..........................");
}

    @RequestMapping("/getUserM")
	@ResponseBody
	public UserDB getUserM(Long id) {
        System.out.println("iiiiiiiiiii   " + id);
		UserDB user = myBatisUserDao.getUser(id);
		return user;
	}

    @RequestMapping("/addUserM")
	@ResponseBody
    @org.springframework.transaction.annotation.Transactional(isolation = Isolation.READ_COMMITTED,
    timeout = 1)
	public Map<String, Object> addUserM(String userName, String note) {
		UserDB user = new UserDB();
		user.setUserName(userName);
		user.setNote(note);
		// 结果会回填主键，返回插入条数
		int update = myBatisUserDao.insertUser(user);
		Map<String, Object> result = new HashMap<>();
		result.put("success", update == 1);
		result.put("user", user);
		return result;
	}
    

    @Transactional
    @RequestMapping("/addUser")
	@ResponseBody
    public List<UserDB> insertWithQuery(UserDB user) {
        this.entityManager.createNativeQuery("INSERT INTO t_user_1 (id, user_name, note) VALUES (?,?,?)")
        .setParameter(1, user.getId())
        .setParameter(2, user.getUserName())
        .setParameter(3, user.getNote())
        .executeUpdate();
        return jpaUserRepository.findAll();
    }

    @Transactional
    @RequestMapping("/updateUser")
	@ResponseBody
    public List<UserDB> updateWithQuery(UserDB user) {
        this.entityManager.createNativeQuery("UPDATE t_user_1 set note=? where id=?")
        .setParameter(1, user.getNote())
        .setParameter(2, user.getId())
        .executeUpdate();
        return jpaUserRepository.findAll();
    }

    @Transactional
    @RequestMapping("/updateUserPet")
	@ResponseBody
    public List<UserDB> updateUpWithQuery(Long id, String petName) {
        Dog dog = new Dog();
        dog.setName(petName);
        String dogString = new Gson().toJson(dog);
        System.out.println("now the pet in json " + dogString);
        this.entityManager.createNativeQuery("UPDATE t_user_1 set pet=? where id=?")
        .setParameter(1, dogString)
        .setParameter(2, id)
        .executeUpdate();
        return jpaUserRepository.findAll();
    }

    @Transactional
    @RequestMapping("/deleteUser")
	@ResponseBody
    public List<UserDB> deleteWithQuery(UserDB user) {
        this.entityManager.createNativeQuery("DELETE from t_user_1 where id=?")
        .setParameter(1, user.getId())
        .executeUpdate();
        jpaUserRepository.deleteById(Long.valueOf(1));;
        return jpaUserRepository.findAll();
    }

    @RequestMapping("/getUser")
	@ResponseBody
	public UserDB getUser(Long id) {
		// 使用JPA接口查询对象
        System.out.println("iiiiiiiiiii   " + id);
		UserDB user = jpaUserRepository.findById(id).get();
		return user;
	}

    @RequestMapping("/getUsers")
	@ResponseBody
	public List<UserDB> getUsers(String userName, String note) {
		// 使用JPA接口查询对象
        System.out.println("iiiiiiiiiii comment   " + userName + "  " + note);
		List<UserDB> users = jpaUserRepository.findUsers(userName, note);
		return users;
	}
	
    @RequestMapping("/getUsersJpaMade")
	@ResponseBody
	public List<UserDB> getUsersJpaMade(Long id, String userName, String note) {
		// 使用JPA接口查询对象
        System.out.println("iiiiiiiiiii comment   " + id + " " + userName + "  " + note);
        List<UserDB> res1 = jpaUserRepository.findByUserNameLike("%"+userName+"%");
        List<UserDB> res2 = jpaUserRepository.findByUserNameLikeOrNoteLike("%FAKE%", "%"+note+"%");
        UserDB res3 = jpaUserRepository.getUserById(id);
        System.out.println(res1 + " " + res2 + " " + res3);
        res1.addAll(res2);
        res1.add(res3);

		// List<UserDB> users = jpaUserRepository.findUsers(userName, note);
		return res1;
	}

	@RequestMapping("/test")
	@ResponseBody
	public Map<String, String> test() {
		Map<String, String> map = new HashMap<>();
        // IoCTest i = new IoCTest();
        // i.work();
        // System.out.println(environment.getProperty("server.port"));
        // System.out.println(environment.getProperty("a.b"));
        System.out.println(Arrays.toString(applicationContext.getBeanDefinitionNames()));
        // System.out.println("xxxxxxxxxxxxxxxxxxx");
        // System.out.println(environment);
        System.out.println(configFileInjection.getC());
        // System.out.println("..............." + this.ab);
        boolean met = false;
        String[] names = applicationContext.getBeanDefinitionNames();
        for(String name: names){
            if(name.equals("cat")){
                met = true;
            }
        }
        if(met){
            System.out.println("-----------------met!");
        }else{
            System.out.println("-----------------not met!");
        }
        //System.out.println(prefixLoad.getA());
        dog.setThis(100);
        // AnimalValidator animalValidator = (AnimalValidator)dog;
        // System.out.println("the validate result...... " + animalValidator.validate(dog));
		map.put("key1", "value1");
		return map;
	}
}
