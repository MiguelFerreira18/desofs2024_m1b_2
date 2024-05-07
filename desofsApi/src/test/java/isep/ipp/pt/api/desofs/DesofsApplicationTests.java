package isep.ipp.pt.api.desofs;

import jdk.jfr.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class DesofsApplicationTests {


	@Test
	@DisplayName("Simple test")
	void simpleTest(){
		assertEquals(1,1);
	}

}
