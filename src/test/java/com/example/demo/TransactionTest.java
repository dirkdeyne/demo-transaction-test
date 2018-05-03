package com.example.demo;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.transaction.AfterTransaction;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
public class TransactionTest {
  
  @Autowired PersonRepository repo;
  
  @Autowired
  private EntityManager em;
  
  @Before
  public void showCountBefore() {
    System.err.println("before: " + repo.count());
  }
  
  @After
  public void showCountAfter() {
    System.err.println("after: " + repo.count());
  }
  
  @AfterTransaction
  public void showCountAfterTransaction() {
    System.err.println("after tx: " + repo.count());
  }
  
  @Test
  public void testRollback() {
    repo.save(new Person("Deyne", "Dirk", "dirkdeyne"));
    System.err.println("saved: " + repo.count());
  }
  
  @Test
  public void testRollbackWithEntityManger() {
    Person entity = new Person("Deyne", "Kevin", "kevdeyne");
    em.persist(entity);
    em.flush();
    System.err.println("em save: " + repo.count());
  }
  
}
