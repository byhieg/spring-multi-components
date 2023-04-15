package com.byhieg.component;

import com.zaxxer.hikari.util.IsolationLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.PostConstruct;

import static org.springframework.transaction.TransactionDefinition.ISOLATION_DEFAULT;

/**
 * Created by byhieg on 2023/4/15.
 * Mail to byhieg@gmail.com
 */
@Component
public class TransactionUtil {
	
	private PlatformTransactionManager platformTransactionManager;
	
	private TransactionTemplate requireTemplate;
	private TransactionTemplate requireNewTemplate;
	
	@Autowired
	public TransactionUtil(PlatformTransactionManager platformTransactionManager) {
		this.platformTransactionManager = platformTransactionManager;
	}

	@PostConstruct
	public void init() {
		requireTemplate = new TransactionTemplate();
		requireTemplate.setName("require-transaction-template");
		requireTemplate.setIsolationLevel(TransactionDefinition.ISOLATION_DEFAULT);
		requireTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		requireTemplate.setTransactionManager(platformTransactionManager);
		requireTemplate.setTimeout(TransactionDefinition.TIMEOUT_DEFAULT);

		requireNewTemplate = new TransactionTemplate();
		requireNewTemplate.setName("require-new-transaction-template");
		requireNewTemplate.setIsolationLevel(TransactionDefinition.ISOLATION_DEFAULT);
		requireNewTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		requireNewTemplate.setTransactionManager(platformTransactionManager);
		requireNewTemplate.setTimeout(TransactionDefinition.TIMEOUT_DEFAULT);
		
	}
	public TransactionTemplate getRequireTemplate() {
		return requireTemplate;
	}

	public TransactionTemplate getRequireNewTemplate() {
		return requireNewTemplate;
	}
}
