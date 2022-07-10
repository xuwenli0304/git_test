package config.pojo;

import org.springframework.stereotype.Service;

import config.pojo.definition.HelloService;

//@Service
public class HelloServiceImpl implements HelloService {
    @Override
	public void sayHello(String name) {
		if (name == null || name.trim() == "") {
			throw new RuntimeException ("parameter is null!!");
		}
		System.out.println("hello " + name);
	}
}
