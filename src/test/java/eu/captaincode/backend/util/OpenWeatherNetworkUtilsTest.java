package eu.captaincode.backend.util;

import eu.captaincode.backend.spring.SpringConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfiguration.class, loader = AnnotationConfigContextLoader.class)
public class OpenWeatherNetworkUtilsTest {

    private static final String CITY = "Budapest";
    private static final String EMPTY_STRING = "";
    @Autowired
    private OpenWeatherNetworkUtils testNetworkUtils;

    @Test
    public void whenCalledWithValidCityName_thenReturnsHttpResponseString() {
        // when
        String response = testNetworkUtils.getCityWeatherJson(CITY);

        // then
        assertThat("Response is not type of String", response, instanceOf(String.class));
    }

    @Test
    public void whenCalledWithEmptyString_thenReturnsNull() {
        // when
        String response = testNetworkUtils.getCityWeatherJson(EMPTY_STRING);

        //then
        assertNull("Returned with response", response);
    }
}