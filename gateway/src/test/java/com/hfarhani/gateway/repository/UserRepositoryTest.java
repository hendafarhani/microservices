package com.hfarhani.gateway.repository;

import com.hfarhani.gateway.model.JpaUser;
import com.hfarhani.gateway.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Calendar;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TestEntityManager entityManager;

    //Test the persistence layer == UserRepository, for this purpose a user will be inserted in a database and will be
//    compared to jpa object fetched by the repository.
    @Test
    public void whenFindByUserName_thenReturnUser() {
        // given
        Calendar c = Calendar.getInstance(); // starts with today's date and time
        c.add(Calendar.DAY_OF_YEAR, 2);  // advances day by 2
        Date passwordExpirationDate = c.getTime();
        JpaUser user = new JpaUser("test2", "password", passwordExpirationDate);
        entityManager.persist(user);
        entityManager.flush();

        // when
        JpaUser foundUser = userRepository.findUserByUsername(user.getUsername());

        // then
        assertThat(foundUser.getUsername())
                .isEqualTo(user.getUsername());
    }


}
