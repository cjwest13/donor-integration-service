package com.cjwest13.donor.integration.mapper;

import com.cjwest13.donor.integration.domain.Constituent;
import com.cjwest13.donor.integration.domain.Transaction;
import com.cjwest13.donor.integration.model.ActBlueDonation;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BloomerangMapperTests {

    @Test
    public void actBlueDonationToConstituentTest() {
        ActBlueDonation actBlueDonation = getActBlueDonation();
        Constituent constituent = BloomerangMapper.MAPPER.actBlueDonationtoConstituent(actBlueDonation);
        assertEquals(actBlueDonation.getDonorFirstName(), constituent.getFirstName());
        assertEquals(actBlueDonation.getDonorLastName(), constituent.getLastName());
        assertEquals(actBlueDonation.getDonorOccupation(), constituent.getJobTitle());
        assertEquals(actBlueDonation.getDonorEmployer(), constituent.getEmployer());
        assertEquals(actBlueDonation.getDonorPhone(), constituent.getPrimaryPhone().getNumber());
        assertEquals(actBlueDonation.getDonorEmail(), constituent.getPrimaryEmail().getValue());
        assertEquals((actBlueDonation.getDonorAddressLine1() + " " + actBlueDonation.getDonorAddressLine2()),
                constituent.getPrimaryAddress().getStreet());
        assertEquals(actBlueDonation.getDonorCity(), constituent.getPrimaryAddress().getCity());
        assertEquals(actBlueDonation.getDonorState(), constituent.getPrimaryAddress().getState());
        assertEquals(actBlueDonation.getDonorZip(), constituent.getPrimaryAddress().getPostalCode());
        assertEquals(actBlueDonation.getDonorCountry(), constituent.getPrimaryAddress().getCountry());
        assertEquals(actBlueDonation.getReceiptId(), constituent.getCustomValues().get(0).getValue());
    }

    @Test
    public void actBlueDonationToTransactionTest() {
        ActBlueDonation actBlueDonation = getActBlueDonation();
        Transaction transaction = BloomerangMapper.MAPPER.actBlueDonationToTransaction(actBlueDonation);
        assertEquals(actBlueDonation.getAmount(), transaction.getAmount());
        assertEquals(actBlueDonation.getCardType(), transaction.getCreditCardType());
        assertEquals(actBlueDonation.getDate(), transaction.getDate());
        assertEquals(actBlueDonation.getAmount(), transaction.getDesignations().get(0).getAmount());
        assertEquals(actBlueDonation.getRefCode1(), transaction.getDesignations().get(0).getNote());
    }

    protected ActBlueDonation getActBlueDonation() {
        ActBlueDonation actBlueDonation = new ActBlueDonation();
        actBlueDonation.setDonorFirstName("Clifton");
        actBlueDonation.setDonorLastName("West");
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
        actBlueDonation.setAmount(25.00);
        actBlueDonation.setRefCode1("212131");
        return actBlueDonation;
    }
}
