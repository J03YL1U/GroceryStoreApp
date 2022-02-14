package mcgill.ecse321.grocerystore.dao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestUserPersistence {
	@Autowired
	private UserRepository userRepository;

	@AfterEach
	public void clearDatabase() {
		userRepository.deleteAll();
	}
}