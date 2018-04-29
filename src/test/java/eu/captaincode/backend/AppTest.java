package eu.captaincode.backend;

import eu.captaincode.backend.spring.SpringConfiguration;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.io.ByteArrayInputStream;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfiguration.class, loader = AnnotationConfigContextLoader.class)
public class AppTest {

    private App testApp;

    @Before
    public void setUp() {
        testApp = new App();
    }

    @Test
    public void whenCalledWithValidCityName_thenReturnsString() {
        String input = "Budapest";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        String message = testApp.run();
        assertThat("Message is not a type of String", message, instanceOf(String.class));
    }
}
