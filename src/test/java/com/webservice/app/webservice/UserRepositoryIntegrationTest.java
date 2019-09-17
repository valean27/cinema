package com.webservice.app.webservice;

import com.webservice.app.webservice.model.User;
import com.webservice.app.webservice.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryIntegrationTest {

    @Autowired
    TestEntityManager entityManager;

    @Autowired
    UserRepository userRepository;

    @Test
    public void findByNameReturnUser(){
        User user = new User("Stefan", "Valean", 23);
        entityManager.persist(user);
        entityManager.flush();

        // when
        User found = userRepository.findByFirstName(user.getFirstName());

        // then
        assertEquals(found.getFirstName(), user.getFirstName());
    }

}
