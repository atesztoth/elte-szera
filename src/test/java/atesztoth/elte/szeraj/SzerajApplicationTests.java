package atesztoth.elte.szeraj;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SzerajApplicationTests {

	@Test
	public void contextLoads() {
		assertEquals(5, 5);
	}

}
