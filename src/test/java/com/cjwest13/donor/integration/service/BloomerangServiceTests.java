package com.cjwest13.donor.integration.service;

import com.cjwest13.donor.integration.DonorIntegrationServiceApplicationTests;
import com.cjwest13.donor.integration.actblue.ActBlueAPIImpl;
import com.cjwest13.donor.integration.bloomerang.BloomerangAPI;
import com.cjwest13.donor.integration.bloomerang.BloomerangAPIImpl;
import com.cjwest13.donor.integration.domain.*;
import com.cjwest13.donor.integration.model.ActBlueDonation;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.cjwest13.donor.integration.TestUtils.getSearchList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@Slf4j
public class BloomerangServiceTests extends DonorIntegrationServiceApplicationTests {

    @Mock
    BloomerangAPIImpl api;

    @InjectMocks
    @Autowired
    BloomerangServiceImpl service;

    @Value("${json.dir}")
    private String jsonDir;

    @Value("${json.bloom.response}")
    private String jsonResponseFile;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        when(api.constituentsSearch(anyString(),anyString())).thenReturn(getSearchList(jsonDir, jsonResponseFile));
    }

    @Test
    public void convertToDonationsTest() {
        List<ActBlueDonation> actBlueDonationList = new ArrayList<>();
        actBlueDonationList.add(getActBlueDonation("Clifton", "West", 20.00));
        actBlueDonationList.add(getActBlueDonation("Clifton", "West", 50.00));
        actBlueDonationList.add(getActBlueDonation("Jen", "Near", 75.00));
        List<Donation> donations = service.convertToDonations(actBlueDonationList);
        assertEquals(donations.size(), 2);
        assertEquals(donations.get(0).getTransactionList().size(), 2);
        assertEquals(donations.get(0).getTransactionList().get(0).getDesignations().size(), 1);
        assertEquals(donations.get(0).getTransactionList().get(1).getDesignations().size(), 1);
        assertEquals(donations.get(1).getTransactionList().size(), 1);
        assertEquals(donations.get(0).getTransactionList().get(0).getDesignations().size(), 1);
    }

    @Test
    public void constituentExistTestABReceiptID() throws Exception {
        boolean expected = true;
        boolean response = service.constituentExist(getConstituent());
        assertEquals(expected, response);
    }

    @Test
    public void constituentExistTestEmail() throws Exception {
        boolean expected = true;
        boolean response = service.constituentExist(getConstituent2());
        assertEquals(expected, response);
    }

    @Test
    public void constituentExistTestNameAndAddress() throws Exception {
        boolean expected = true;
        boolean response = service.constituentExist(getConstituent3());
        assertEquals(expected, response);
    }

    @Test
    public void constituentDoesNotMatchTest() throws Exception {
        boolean expected = false;
        boolean response = service.constituentExist(getConstituent4());
        assertEquals(expected, response);
    }

    @Test
    public void constituentNoSearchMatch() throws Exception {
        boolean expected = false;
        when(api.constituentsSearch(anyString(),anyString())).thenReturn(getSearchList(jsonDir, "constituentsSearchResponseNoMatch.json"));
        boolean response = service.constituentExist(getConstituent4());
        assertEquals(expected, response);
    }

    protected Constituent getConstituent() {
        Constituent constituent = new Constituent();
        List<CustomValue> customValueList = new ArrayList<>();
        CustomValue customValue = new CustomValue();
        Address address = new Address();
        Email email = new Email();

        constituent.setFirstName("Jennifer");
        constituent.setLastName("Near");

        email.setValue("jmnear@gmail.com");
        constituent.setPrimaryEmail(email);

        address.setStreet("65 Rollins Rd");
        address.setCity("Rollinsford");
        address.setState("03869");
        constituent.setPrimaryAddress(address);

        customValue.setValue("AB201662022");
        customValueList.add(customValue);
        constituent.setCustomValues(customValueList);
        return constituent;
    }

    protected Constituent getConstituent2() {
        Constituent constituent = new Constituent();
        Address address = new Address();
        Email email = new Email();

        constituent.setFirstName("Jennifer");
        constituent.setLastName("Near");

        email.setValue("jmnear@gmail.com");
        constituent.setPrimaryEmail(email);

        address.setStreet("65 Rollins Rd");
        address.setCity("Rollinsford");
        address.setPostalCode("03869");
        constituent.setPrimaryAddress(address);

        return constituent;
    }

    protected Constituent getConstituent3() {
        Constituent constituent = new Constituent();
        Address address = new Address();
        Email email = new Email();

        constituent.setFirstName("Jennifer");
        constituent.setLastName("Near");

        address.setStreet("65 Rollins Rd");
        address.setCity("Rollinsford");
        address.setPostalCode("03869");
        constituent.setPrimaryAddress(address);

        return constituent;
    }

    protected Constituent getConstituent4() {
        Constituent constituent = new Constituent();
        Address address = new Address();
        Email email = new Email();

        constituent.setFirstName("Jennifer");
        constituent.setLastName("Near");

        address.setStreet("7543 Rollins Rd");
        address.setCity("Rollinsford");
        address.setPostalCode("03866");
        constituent.setPrimaryAddress(address);

        return constituent;
    }

    protected ActBlueDonation getActBlueDonation(String first, String last, double amount) {
        ActBlueDonation actBlueDonation = new ActBlueDonation();
        actBlueDonation.setDonorFirstName(first);
        actBlueDonation.setDonorLastName(last);
        actBlueDonation.setDonorAddressLine1("999 Phoenix Dr");
        actBlueDonation.setDonorAddressLine2("Suite 8130");
        actBlueDonation.setDonorCity("Phoenix");
        actBlueDonation.setDonorState("AZ");
        actBlueDonation.setDonorCountry("United States");
        actBlueDonation.setDonorZip("71910");
        actBlueDonation.setDonorOccupation("Scientist");
        actBlueDonation.setDonorEmployer("NASA");
        actBlueDonation.setDonorEmail("email@mail.com");
        actBlueDonation.setDonorPhone("9999999999");
        actBlueDonation.setDate(new Date());
        actBlueDonation.setAmount(amount);
        actBlueDonation.setRefCode1("212131");
        return actBlueDonation;
    }
}
