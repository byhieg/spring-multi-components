package com.byhieg;

import com.byhieg.dto.ExampleEntity;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@SpringBootTest(classes = Application.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
class ApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	void post1() {
		ExampleEntity exampleEntity = new ExampleEntity();
		exampleEntity.setAge(-1);
		ResponseEntity<String> response  = restTemplate.postForEntity("/add",exampleEntity,String.class);
		log.info("{}", response.getBody());
	}

	@Test
	void post2() {
		ExampleEntity exampleEntity = new ExampleEntity();
		exampleEntity.setName(" 你");
		exampleEntity.setAge(18);
		exampleEntity.setEmail("aaaa");
		ResponseEntity<String> response  = restTemplate.postForEntity("/add",exampleEntity,String.class);
		log.info("{}", response.getBody());
	}

	@Test
	void post3() {
		ExampleEntity exampleEntity = new ExampleEntity();
		exampleEntity.setSex("aaa");
		ResponseEntity<String> response  = restTemplate.postForEntity("/add",exampleEntity,String.class);
		log.info("{}", response.getBody());
	}

	@Test
	void post4() {
		ExampleEntity exampleEntity = new ExampleEntity();
		exampleEntity.setSex("aaa");
		List<ExampleEntity.OwnerThing> list = new ArrayList<>();
		list.add(new ExampleEntity.OwnerThing());
		exampleEntity.setOwnerThings(list);
		// add时，不检查OwnerThing id
		ResponseEntity<String> response  = restTemplate.postForEntity("/add",exampleEntity,String.class);
		log.info("{}", response.getBody());
	}

	@Test
	void post5() {
		ExampleEntity exampleEntity = new ExampleEntity();
		exampleEntity.setSex("aaa");
		exampleEntity.setTelephone("176000000000");
		// add时，不检查OwnerThing id
		ResponseEntity<String> response  = restTemplate.postForEntity("/add",exampleEntity,String.class);
		log.info("{}", response.getBody());
	}

	@Test
	void post6() {
		ExampleEntity exampleEntity = new ExampleEntity();
		exampleEntity.setName("byhieg");
		exampleEntity.setAge(18);
		List<ExampleEntity.OwnerThing> list = new ArrayList<>();
		ExampleEntity.OwnerThing ownerThing = new ExampleEntity.OwnerThing();
		ownerThing.setPath("aaa");
		list.add(ownerThing);
		exampleEntity.setOwnerThings(list);
		exampleEntity.setSex("male");
		exampleEntity.setTelephone("13588888888");
		exampleEntity.setEmail("byhieg@gmail.com");
		ResponseEntity<String> response  = restTemplate.postForEntity("/add",exampleEntity,String.class);
		log.info("{}", response.getBody());
	}


	@Test
	void put1() {
		ExampleEntity exampleEntity = new ExampleEntity();
		exampleEntity.setSex("aaa");
		List<ExampleEntity.OwnerThing> list = new ArrayList<>();
		list.add(new ExampleEntity.OwnerThing());
		exampleEntity.setOwnerThings(list);
		// update时，检查OwnerThing id
		ResponseEntity<String> response  = restTemplate.postForEntity("/update",exampleEntity,String.class);
		log.info("{}", response.getBody());
	}
	
	

	@Test
	void get1() {
		ResponseEntity<String> response  = restTemplate.getForEntity("/get/1?token=",String.class);
		log.info("{}", response.getBody());
	}

	@Test
	void get2() {
		String id = UUID.randomUUID().toString();
		ResponseEntity<String> response  = restTemplate.getForEntity(String.format("/get/%s?token=",id),String.class);
		log.info("{}", response.getBody());
	}
	
	
}
