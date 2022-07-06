package com.cjwest13.donor.integration.service;

import com.cjwest13.donor.integration.domain.Donation;
import com.cjwest13.donor.integration.model.ActBlueDonation;

import java.util.List;

public interface BloomerangService {

    List<Donation> convertToDonations(List<ActBlueDonation> donation);

    List<Donation> uploadDonations(List<Donation> donations) throws Exception;

}