package com.cjwest13.donor.integration;

import com.cjwest13.donor.integration.domain.*;
import com.cjwest13.donor.integration.model.*;
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
        classList.add(BloomerangSearchListResponse.class);
        classList.add(BloomerangSearchResponse.class);
        classList.add(CustomValueSearch.class);
        classList.add(ValueSearch.class);
        classList.add(Address.class);
        classList.add(Constituent.class);
        classList.add(CustomValue.class);
        classList.add(Designation.class);
        classList.add(Donation.class);
        classList.add(Email.class);
        classList.add(Phone.class);
        classList.add(Transaction.class);
        return classList;
    }

    private static Map<Class<?>, Class<?>> getLombokTestEqualsClasses() {
        Map<Class<?>, Class<?>> classMap = new HashMap<>();
        classMap.put(ActBlueCreateCVSRequest.class, ActBlueCreateCVSRequestTest.class);
        classMap.put(ActBlueCreateCVSResponse.class, ActBlueCreateCVSResponseTest.class);
        classMap.put(ActBlueGetCVSResponse.class, ActBlueGetCVSResponseTest.class);
        classMap.put(ActBlueDonation.class, ActBlueDonationsTest.class);
        classMap.put(BloomerangSearchListResponse.class, BloomerangSearchListResponseTest.class);
        classMap.put(BloomerangSearchResponse.class, BloomerangSearchResponseTest.class);
        classMap.put(CustomValueSearch.class, CustomValueSearchTest.class);
        classMap.put(ValueSearch.class, ValueSearchTest.class);
        classMap.put(Address.class, AddressTest.class);
        classMap.put(Constituent.class, ConstituentTest.class);
        classMap.put(CustomValue.class, CustomValueTest.class);
        classMap.put(Designation.class, DesignationTest.class);
        classMap.put(Donation.class, DonationTest.class);
        classMap.put(Email.class, EmailTest.class);
        classMap.put(Phone.class, PhoneTest.class);
        classMap.put(Transaction.class, TransactionTest.class);
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

    static class BloomerangSearchListResponseTest extends ActBlueDonation {

        @Override
        protected boolean canEqual(Object other) {
            return other instanceof BloomerangSearchListResponseTest;
        }
    }

    static class BloomerangSearchResponseTest extends ActBlueDonation {

        @Override
        protected boolean canEqual(Object other) {
            return other instanceof BloomerangSearchResponseTest;
        }
    }

    static class CustomValueSearchTest extends ActBlueDonation {

        @Override
        protected boolean canEqual(Object other) {
            return other instanceof CustomValueSearchTest;
        }
    }

    static class ValueSearchTest extends ActBlueDonation {

        @Override
        protected boolean canEqual(Object other) {
            return other instanceof ValueSearchTest;
        }
    }

    static class AddressTest extends ActBlueDonation {

        @Override
        protected boolean canEqual(Object other) {
            return other instanceof AddressTest;
        }
    }

    static class ConstituentTest extends ActBlueDonation {

        @Override
        protected boolean canEqual(Object other) {
            return other instanceof ConstituentTest;
        }
    }

    static class CustomValueTest extends ActBlueDonation {

        @Override
        protected boolean canEqual(Object other) {
            return other instanceof CustomValueTest;
        }
    }

    static class DesignationTest extends ActBlueDonation {

        @Override
        protected boolean canEqual(Object other) {
            return other instanceof DesignationTest;
        }
    }

    static class DonationTest extends ActBlueDonation {

        @Override
        protected boolean canEqual(Object other) {
            return other instanceof DonationTest;
        }
    }

    static class EmailTest extends ActBlueDonation {

        @Override
        protected boolean canEqual(Object other) {
            return other instanceof EmailTest;
        }
    }

    static class PhoneTest extends ActBlueDonation {

        @Override
        protected boolean canEqual(Object other) {
            return other instanceof PhoneTest;
        }
    }

    static class TransactionTest extends ActBlueDonation {

        @Override
        protected boolean canEqual(Object other) {
            return other instanceof TransactionTest;
        }
    }

}