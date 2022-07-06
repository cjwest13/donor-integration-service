package com.cjwest13.donor.integration.service;

import com.cjwest13.donor.integration.bloomerang.BloomerangAPI;
import com.cjwest13.donor.integration.domain.Constituent;
import com.cjwest13.donor.integration.domain.Donation;
import com.cjwest13.donor.integration.domain.Transaction;
import com.cjwest13.donor.integration.mapper.BloomerangMapper;
import com.cjwest13.donor.integration.model.ActBlueDonation;
import com.cjwest13.donor.integration.model.BloomerangSearchListResponse;
import com.cjwest13.donor.integration.model.BloomerangSearchResponse;
import com.cjwest13.donor.integration.model.CustomValueSearch;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class BloomerangServiceImpl implements BloomerangService {

    @Autowired
    BloomerangAPI bloomerangAPI;

    /**
     * Method that utilizes the BloomerangMapper class to covert ActBlueDonation objects
     * to Donation objects for Bloomerang. The method assigns a list of donation transactions to a constituent.
     * @param actBlueDonations
     * @return List<Donation> donations
     */
    @Override
    public List<Donation> convertToDonations(List<ActBlueDonation> actBlueDonations) {
        List<Donation> donations = new ArrayList<>();
        boolean match;

        for (ActBlueDonation actBlueDonation : actBlueDonations) {
            match = false;

            for (Donation donation: donations) {
                if (donation.getConstituent().getFirstName().equals(actBlueDonation.getDonorFirstName()) &&
                    donation.getConstituent().getLastName().equals(actBlueDonation.getDonorLastName()) ) {
                    Transaction transaction = BloomerangMapper.MAPPER.actBlueDonationToTransaction(actBlueDonation);
                    donation.getTransactionList().add(transaction);
                    match = true;
                }
            }

            if (!match) {
                Donation donation = createDonation(actBlueDonation);
                donations.add(donation);
            }
        }

        return donations;
    }

    public List<Donation> uploadDonations(List<Donation> donations) throws Exception {
        for (Donation donation: donations) {


            if (!constituentExist(donation.getConstituent())) {
                //uploadConstituentAndTransactions
                //Need to get Constituent Account Id to add to Transactions
                continue;
            }

            //constituentExist
            //Need to get Constituent Account Id to add to Transactions and Search
            //Transactions for Account & minAmount & maxAmount
            //Filter by Date
            //Loop through each transaction
            //CheckIfTransactionExistViaPaymentID
            //If Exist,
            //


        }

        return donations;
    }

    /**
     * Helper method to determine if a Constituent Exist in the Bloomerang DB.
     * @param constituent
     * @return Boolean
     * @throws Exception
     */
    protected boolean constituentExist(Constituent constituent) throws Exception {
        BloomerangSearchListResponse searchResponse = bloomerangAPI.constituentsSearch(constituent.getFirstName(),
                constituent.getLastName());

        //Nothing returned in the search, does not exist
        if (searchResponse.getResultCount() == 0) {
            return false;
        }

        // If its record found, lets lop through it
        for (BloomerangSearchResponse response : searchResponse.getResults()) {

            //ActBlue Receipt ID is most unique value potentially available
            if (response.getCustomValues() != null && constituent.getCustomValues() != null) {
                for (CustomValueSearch customValue : response.getCustomValues()) {
                    if (customValue.getValue().getValue().equals(constituent.getCustomValues().get(0).getValue())) {
                        return true;
                    }
                }
            }

            //Email is 2 most unique value available
            if (response.getPrimaryEmail() != null && constituent.getPrimaryEmail() != null &&
                    constituent.getPrimaryEmail().getValue().equalsIgnoreCase(response.getPrimaryEmail().getValue())) {
                return true;
            }

            /// Expected Folks to have first Name & last Name listed in DB
            // Check If Name Match, if so, then check if address match. (Sorry if folks moved or changed their name)
            if (response.getFirstName().equalsIgnoreCase(constituent.getFirstName()) &&
                    response.getLastName().equalsIgnoreCase(constituent.getLastName())) {
                if (response.getPrimaryAddress() != null && constituent.getPrimaryAddress() != null &&
                        response.getPrimaryAddress().getStreet().equalsIgnoreCase(constituent.getPrimaryAddress().getStreet())
                        && response.getPrimaryAddress().getCity().equalsIgnoreCase(constituent.getPrimaryAddress().getCity())) {
                    return true;
                }

            }
        }
        return false;
    }

    /**
     * Helper method to convert an ActBlueDonation object to Bloomerang Donation object
     * @param actBlueDonation
     * @return Donation
     */
    private Donation createDonation(ActBlueDonation actBlueDonation) {
        Donation donation = new Donation();
        List<Transaction> transactionList = new ArrayList<>();
        Constituent constituent = BloomerangMapper.MAPPER.actBlueDonationtoConstituent(actBlueDonation);
        Transaction transaction = BloomerangMapper.MAPPER.actBlueDonationToTransaction(actBlueDonation);
        donation.setConstituent(constituent);
        transactionList.add(transaction);
        donation.setTransactionList(transactionList);
        return donation;
    }

}
