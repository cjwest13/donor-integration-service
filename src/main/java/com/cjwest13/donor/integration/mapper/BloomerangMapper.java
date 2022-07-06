package com.cjwest13.donor.integration.mapper;

import com.cjwest13.donor.integration.DonorIntegrationServiceApplication;
import com.cjwest13.donor.integration.domain.Constituent;
import com.cjwest13.donor.integration.domain.CustomValue;
import com.cjwest13.donor.integration.domain.Designation;
import com.cjwest13.donor.integration.domain.Transaction;
import com.cjwest13.donor.integration.model.ActBlueDonation;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring", imports = {Arrays.class})
public interface BloomerangMapper {

    BloomerangMapper MAPPER = Mappers.getMapper(BloomerangMapper.class);

    @Mappings({
            @Mapping(target = "type", constant = "Individual"),
            @Mapping(target = "status", constant = "Active"),
            @Mapping(target = "firstName", source = "donorFirstName"),
            @Mapping(target = "lastName", source = "donorLastName"),
            @Mapping(target = "jobTitle", source = "donorOccupation"),
            @Mapping(target = "employer", source = "donorEmployer"),
            @Mapping(target = "primaryEmail.value", source = "donorEmail"),
            @Mapping(target = "primaryPhone.number", source = "donorPhone"),
            @Mapping(target = "primaryAddress.type", constant = "Home"),
            @Mapping(target = "primaryAddress.street", source = ".", qualifiedByName = "translateToFullStreet"),
            @Mapping(target = "primaryAddress.city", source = "donorCity"),
            @Mapping(target = "primaryAddress.state", source = "donorState"),
            @Mapping(target = "primaryAddress.postalCode", source = "donorZip"),
            @Mapping(target = "primaryAddress.country", source = "donorCountry"),
            @Mapping(target = "customValues", source = ".", qualifiedByName = "translateToCustomValue")
    })
    Constituent actBlueDonationtoConstituent(ActBlueDonation donation);

    @Mappings({
            @Mapping(target = "amount", source = "amount"),
            @Mapping(target = "date", source = "date"),
            @Mapping(target = "creditCardType", source = "cardType"),
            @Mapping(target = "designations", source = ".", qualifiedByName = "translateToDesignationList")
    })
    Transaction actBlueDonationToTransaction(ActBlueDonation donation);

    @Named("translateToDesignationList")
    static List<Designation> translateToDesignationList(ActBlueDonation donation) {
        Designation designation = new Designation();
        List<Designation> designationList = new ArrayList<>();
        List<CustomValue> customValueList = new ArrayList<>();
        CustomValue customValue = new CustomValue();
        designation.setAmount(donation.getAmount());
        designation.setNote(donation.getRefCode1());
        designation.setType("Donations");
        designation.setFundId(10); //General; Will have to make more dynamic in later implementations possibly
        customValue.setValue(donation.getPaymentID());
        customValueList.add(customValue);
        designation.setCustomValues(customValueList);
        designationList.add(designation);
        return designationList;

    }

    @Named("translateToFullStreet")
    static String translateToFullStreet(ActBlueDonation donation) {
        return donation.getDonorAddressLine1() + " " + donation.getDonorAddressLine2();
    }

    @Named("translateToCustomValue")
    static List<CustomValue> translateToCustomValue(ActBlueDonation donation) {
        List<CustomValue> customValues = new ArrayList<>();
        CustomValue customValue = new CustomValue();
        customValue.setValue(donation.getReceiptId());
        customValues.add(customValue);
        return customValues;
    }

}
