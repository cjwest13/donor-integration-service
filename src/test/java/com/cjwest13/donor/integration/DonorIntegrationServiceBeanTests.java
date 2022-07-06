package com.cjwest13.donor.integration;

import com.cjwest13.donor.integration.model.ActBlueCreateCVSRequest;
import com.cjwest13.donor.integration.model.ActBlueCreateCVSResponse;
import com.cjwest13.donor.integration.model.ActBlueDonation;
import com.cjwest13.donor.integration.model.ActBlueGetCVSResponse;
import org.junit.jupiter.api.Test;
import org.meanbean.test.BeanTester;
import org.meanbean.test.Configuration;
import org.meanbean.test.ConfigurationBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class DonorIntegrationServiceBeanTests extends DonorIntegrationServiceApplicationTests {
    private static final List<Class<?>> lombokClasses = getLombokClasses();
    private static final Map<Class<?>, Class<?>> lombokTestEqualsClasses = getLombokTestEqualsClasses();

    @Test
    public void testGettersAndSetters() {
        BeanTester beanTester = new BeanTester();

        Configuration configuration = new ConfigurationBuilder().build();

        for (Class<?> clazz : lombokClasses) {
            beanTester.testBean(clazz, configuration);
        }
    }
    @Test
    public void testEqualsTestClasses() throws Exception {
        for (Map.Entry<Class<?>, Class<?>> entry : lombokTestEqualsClasses.entrySet()) {
            Class<?> mainClass = entry.getKey();
            Class<?> testClass = entry.getValue();
            Object mainObject = mainClass.getDeclaredConstructor().newInstance();
            Object testObject = testClass.getDeclaredConstructor().newInstance();
            assertFalse(mainObject.equals(testObject));
        }
    }

    private static List<Class<?>> getLombokClasses() {
        List<Class<?>> classList = new ArrayList<Class<?>>();
        classList.add(ActBlueCreateCVSRequest.class);
        classList.add(ActBlueCreateCVSResponse.class);
        classList.add(ActBlueGetCVSResponse.class);
        classList.add(ActBlueDonation.class);
        return classList;
    }

    private static Map<Class<?>, Class<?>> getLombokTestEqualsClasses() {
        Map<Class<?>, Class<?>> classMap = new HashMap<>();
        classMap.put(ActBlueCreateCVSRequest.class, ActBlueCreateCVSRequestTest.class);
        classMap.put(ActBlueCreateCVSResponse.class, ActBlueCreateCVSResponseTest.class);
        classMap.put(ActBlueGetCVSResponse.class, ActBlueGetCVSResponseTest.class);
        classMap.put(ActBlueDonation.class, ActBlueDonationsTest.class);
        return classMap;

    }

    static class ActBlueCreateCVSRequestTest extends ActBlueCreateCVSRequest {

        @Override
        protected boolean canEqual(Object other) {
            return other instanceof ActBlueCreateCVSRequestTest;
        }
    }

    static class ActBlueCreateCVSResponseTest extends ActBlueCreateCVSResponse {

        @Override
        protected boolean canEqual(Object other) {
            return other instanceof ActBlueCreateCVSResponseTest;
        }
    }

    static class ActBlueGetCVSResponseTest extends ActBlueGetCVSResponse {

        @Override
        protected boolean canEqual(Object other) {
            return other instanceof ActBlueGetCVSResponseTest;
        }
    }

    static class ActBlueDonationsTest extends ActBlueDonation {

        @Override
        protected boolean canEqual(Object other) {
            return other instanceof ActBlueDonationsTest;
        }
    }

}