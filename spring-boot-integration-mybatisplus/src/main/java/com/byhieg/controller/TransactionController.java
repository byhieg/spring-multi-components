package com.byhieg.controller;

import com.byhieg.component.TransactionUtil;
import com.byhieg.dao.entity.ClassNameEnum;
import com.byhieg.dao.entity.SexEnum;
import com.byhieg.dao.entity.StudentEntity;
import com.byhieg.service.IStudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.FileNotFoundException;

/**
 * Created by byhieg on 2023/4/10.
 * Mail to byhieg@gmail.com
 */
@RestController
@RequestMapping("/")
public class TransactionController {

	private static final Logger log = LoggerFactory.getLogger(TransactionController.class);
	private final IStudentService studentService;
	
	private ApplicationContext applicationContext;

	private TransactionUtil transactionUtil;
	
	@Autowired
	public TransactionController(IStudentService studentService,TransactionUtil transactionUtil){
		this.studentService = studentService;
		this.transactionUtil = transactionUtil;
	}
	
	@Autowired
	public void setApplicationContext(ApplicationContext applicationContext){
		this.applicationContext = applicationContext;
	}
	@GetMapping("/updateButRollbackForRuntimeException")
	@Transactional
	public void updateButRollbackForRuntimeException(){
		StudentEntity student = new StudentEntity();
		student.setId(18L);
		student.setClassName(ClassNameEnum.ONE.getValue());
		studentService.saveOrUpdate(student);
		int a = 1 / 0;	
	}

	@GetMapping("/updateButNotRollbackForException")
	@Transactional
	public void updateButNotRollbackForException() throws FileNotFoundException{
		StudentEntity student = new StudentEntity();
		student.setId(18L);
		student.setClassName(ClassNameEnum.ONE.getValue());
		studentService.saveOrUpdate(student);
		throw new FileNotFoundException("error");
	}

	@GetMapping("/updateButRollbackForException")
	@Transactional(rollbackFor = {Exception.class})
	public void updateButRollbackForException() throws FileNotFoundException{
		StudentEntity student = new StudentEntity();
		student.setId(18L);
		student.setClassName(ClassNameEnum.ONE.getValue());
		studentService.saveOrUpdate(student);
		throw new FileNotFoundException("error");
	}

	@GetMapping("/updateButNotRollback")
	@Transactional(rollbackFor = {Exception.class})
	public void updateButNotRollback() {
		StudentEntity student = new StudentEntity();
		student.setId(18L);
		student.setClassName(ClassNameEnum.ONE.getValue());
		studentService.saveOrUpdate(student);
		try {
			throw new FileNotFoundException("error");
		} catch (FileNotFoundException e) {
			
		}
	}

	@GetMapping("/updateButNotRollbackWithThis")
	public void updateButNotRollbackWithThis() throws Exception{
		StudentEntity student = new StudentEntity();
		student.setId(18L);
		student.setClassName(ClassNameEnum.ONE.getValue());
		save(student);
	}

	@Transactional(rollbackFor = {Exception.class})
	public void save(StudentEntity student) throws Exception{
		studentService.saveOrUpdate(student);
		throw new Exception("error");

	}

	@GetMapping("/updateButRollbackWithThis")
	public void updateButRollbackWithThis() throws Exception{
		StudentEntity student = new StudentEntity();
		student.setId(18L);
		student.setClassName(ClassNameEnum.ONE.getValue());
//		((TransactionController)AopContext.currentProxy()).save(student); 这种方式需要exposeProxy = true，以及引入AspectJ相关依赖。
		// 注入applicationContext,从applicationContext中getBean。或者使用编程事务。
		TransactionController controller = applicationContext.getBean(TransactionController.class);
		controller.save(student);
	}

	@GetMapping("/transactionWithSupport")
	// 该方法没事务，doTransactionWithSupport 使用support事务也不会生效。
	public void transactionWithSupport() throws Exception{
		TransactionController controller = applicationContext.getBean(TransactionController.class);
		controller.doTransactionWithSupport();
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public void doTransactionWithSupport() throws Exception{
		StudentEntity student = new StudentEntity();
		student.setId(18L);
		student.setClassName(ClassNameEnum.ONE.getValue());
		studentService.saveOrUpdate(student);
		throw new RuntimeException();
	}

	@GetMapping("/transactionWithSupport2")
	@Transactional
	// 该方法有事务，doTransactionWithSupport 使用support事务会加入这个事务
	public void transactionWithSupport2() throws Exception{
		TransactionController controller = applicationContext.getBean(TransactionController.class);
		controller.doTransactionWithSupport();
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public void doTransactionWithSupport2() throws Exception{
		StudentEntity student = new StudentEntity();
		student.setId(18L);
		student.setClassName(ClassNameEnum.ONE.getValue());
		studentService.saveOrUpdate(student);
		throw new RuntimeException();
	}

	@GetMapping("/transactionWithMandatory")
	// 该方法没事务，doTransactionWithMandatory 使用mandatory事务会扔异常
	// No existing transaction found for transaction marked with propagation 'mandatory'
	public void transactionWithMANDATORY() throws Exception{
		TransactionController controller = applicationContext.getBean(TransactionController.class);
		controller.doTransactionWithMandatory();
	}

	@Transactional(propagation = Propagation.MANDATORY)
	public void doTransactionWithMandatory() throws Exception{
		StudentEntity student = new StudentEntity();
		student.setId(18L);
		student.setClassName(ClassNameEnum.ONE.getValue());
		studentService.saveOrUpdate(student);
		throw new RuntimeException();
	}


	@GetMapping("/transactionWithRequiresNew")
	@Transactional
	// requiresNew会新开一个事物，这个事务与父方法的事务没有任何关系。但是要注意，如果子方法异常父方法不捕获。
	// 则会继续导致父方法因为异常抛出，而继续回滚。
	// requireNew 适合父子方法不需要在同一个事务处理的场景。
	//Creating a new SqlSession
	//Transaction synchronization committing SqlSession
	public void transactionWithRequiresNew() throws Exception{
		TransactionController controller = applicationContext.getBean(TransactionController.class);
		try{
			controller.doTransactionWithRequiresNew();
		}catch (Exception e){
			
		}
		StudentEntity student = new StudentEntity();
		student.setId(18L);
		student.setClassName(ClassNameEnum.TWO.getValue());
		studentService.saveOrUpdate(student);
		
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void doTransactionWithRequiresNew() throws Exception{
		StudentEntity student = new StudentEntity();
		student.setId(18L);
		student.setClassName(ClassNameEnum.ONE.getValue());
		studentService.saveOrUpdate(student);
		throw new RuntimeException();
	}

	@GetMapping("/transactionWithNested1")
	@Transactional
	// nested 子方法会变成嵌套事务，加入父方法。父方法回滚，子事务回滚。子事务回滚，扔出异常。如果父方法不捕获
	// 则会继续导致父方法因为异常抛出，而继续回滚。
	public void transactionWithNested1() throws Exception{
		TransactionController controller = applicationContext.getBean(TransactionController.class);
		controller.doTransactionWithNested1();
		throw new RuntimeException();
	}

	@Transactional(propagation = Propagation.NESTED)
	public void doTransactionWithNested1() throws Exception{
		StudentEntity student = new StudentEntity();
		student.setId(18L);
		student.setClassName(ClassNameEnum.ONE.getValue());
		studentService.saveOrUpdate(student);
	}

	@GetMapping("/transactionWithNested2")
	@Transactional
	// nested 子方法会变成嵌套事务，加入父方法。父方法回滚，子事务回滚。子事务回滚，扔出异常。如果父方法不捕获
	// 则会继续导致父方法因为异常抛出，而继续回滚。
	public void transactionWithNested2() throws Exception{
		TransactionController controller = applicationContext.getBean(TransactionController.class);
		try{
			controller.doTransactionWithNested2();
		}catch (Exception e){
			
		}
		StudentEntity student = new StudentEntity();
		student.setId(18L);
		student.setClassName(ClassNameEnum.TWO.getValue());
		studentService.saveOrUpdate(student);
	}

	@Transactional(propagation = Propagation.NESTED)
	public void doTransactionWithNested2() throws Exception{
		StudentEntity student = new StudentEntity();
		student.setId(18L);
		student.setClassName(ClassNameEnum.ONE.getValue());
		studentService.saveOrUpdate(student);
		throw new RuntimeException();
	}


	@GetMapping("/updateButRollbackForManual")
	@Transactional
	// 发生异常的时候，如果捕获了异常，则可以TransactionAspectSupport 手动回滚事务。
	public void updateButRollbackForManual(){
		StudentEntity student = new StudentEntity();
		student.setId(18L);
		student.setClassName(ClassNameEnum.ONE.getValue());
		studentService.saveOrUpdate(student);
		try{
			int a = 1 / 0;
		}catch (Exception e){
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
	}

	@GetMapping("/rollbackForTransactionTemplate")
	public void rollbackForTransactionTemplate() {
		transactionUtil.getRequireTemplate().executeWithoutResult((status)->{
			StudentEntity student = new StudentEntity();
			student.setId(18L);
			student.setClassName(ClassNameEnum.ONE.getValue());
			studentService.saveOrUpdate(student);
			int a = 1/0;
		});
	}

	@GetMapping("/updateForTransactionTemplate")
	public void updateForTransactionTemplate() {
		StudentEntity student = transactionUtil.getRequireTemplate().execute((status)->{
			StudentEntity record = new StudentEntity();
			record.setId(18L);
			record.setClassName(ClassNameEnum.ONE.getValue());
			studentService.saveOrUpdate(record);
			return record;
		});
		log.info("student is [{}]", student);
	}

	@GetMapping("/insertForTransactionTemplate")
	public void insertForTransactionTemplate() {
		StudentEntity newStudent = new StudentEntity();
		newStudent.setSex(SexEnum.female.name());
		newStudent.setName("小兰");
		newStudent.setAge(18);
		newStudent.setClassName(ClassNameEnum.ONE.name());
		newStudent.setAddressId(1L);
		StudentEntity dbStudent = transactionUtil.getRequireTemplate().execute((status)->{
			studentService.save(newStudent);
			return newStudent;
		});
		log.info("dbStudent is [{}]", dbStudent);
	}

	@GetMapping("/rollbackForTransactionTemplateWithRequireNew")
	public void rollbackForTransactionTemplateWithRequireNew() {
		// 父方法使用require 事务，子方法 使用requireNew 事务。
		transactionUtil.getRequireTemplate().executeWithoutResult(status->{
			StudentEntity student = studentService.getById(18L);
			doRollbackForTransactionTemplateWithRequireNew(student);
			int a = 1 / 0;
		});
		
	}
	
	
	public void doRollbackForTransactionTemplateWithRequireNew(StudentEntity student){
		transactionUtil.getRequireNewTemplate().executeWithoutResult(status -> {
			student.setAge(100);
			studentService.saveOrUpdate(student);
		});
	}

	@GetMapping("/rollbackForTransactionTemplateWithRequire")
	@Transactional
	public void rollbackForTransactionTemplateWithRequire() {
		StudentEntity student = studentService.getById(18L);
		TransactionController controller = applicationContext.getBean(TransactionController.class);
		controller.rollbackForTransactionTemplateWithRequire(student);
		int a = 1 / 0;
	}


	public void rollbackForTransactionTemplateWithRequire(StudentEntity student){
		// transaction template 与@Transaction 底层共用一个。这里都是require 传播方式。父方法异常，子方法也回滚
		transactionUtil.getRequireTemplate().executeWithoutResult(status -> {
			student.setAge(10000);
			studentService.saveOrUpdate(student);
		});
	}

}
