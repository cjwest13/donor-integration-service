package com.cjwest13.donor.integration.model;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;
import lombok.Data;

import java.util.Date;

@Data
public class ActBlueDonation {

    //Receipt ID
    @CsvBindByName(column = "Receipt ID")
    private String receiptId;
    //Date
    @CsvBindByName(column = "Date")
    @CsvDate("MM/dd/yyyy HH:mm")
    private Date date;
    //Amount
    @CsvBindByName(column = "Amount")
    private double amount;
    //Recurring Total Months
    @CsvBindByName(column = "Recurring Total Months")
    private String recurringTotalMonths;
    //Recurrence Number
    @CsvBindByName(column = "Recurrence Number")
    private int recurrenceNumber;
    //Recipient
    @CsvBindByName(column = "Recipient")
    private String recipientName;
    //Fundraising Page
    @CsvBindByName(column = "Fundraising Page")
    private String fundraisingPage;
    //Fundraising Partner
    @CsvBindByName(column = "Fundraising Partner")
    private String fundraisingPartner;
    //Reference Code 2
    @CsvBindByName(column = "Reference Code 2")
    private String refCode2;
    //Reference Code
    @CsvBindByName(column = "Reference Code")
    private String refCode1;
    //Donor First Name
    @CsvBindByName(column = "Donor First Name")
    private String donorFirstName;
    //Donor Last Name
    @CsvBindByName(column = "Donor Last Name")
    private String donorLastName;
    //Donor Addr1
    @CsvBindByName(column = "Donor Addr1")
    private String donorAddressLine1;
    //Donor Addr2
    @CsvBindByName(column = "Donor Addr2")
    private String donorAddressLine2;
    //Donor City
    @CsvBindByName(column = "Donor City")
    private String donorCity;
    //Donor State
    @CsvBindByName(column = "Donor State")
    private String donorState;
    //Donor ZIP
    @CsvBindByName(column = "Donor ZIP")
    private String donorZip;
    //Donor Country
    @CsvBindByName(column = "Donor Country")
    private String donorCountry;
    //Donor Occupation
    @CsvBindByName(column = "Donor Occupation")
    private String donorOccupation;
    //Donor Employer
    @CsvBindByName(column = "Donor Employer")
    private String donorEmployer;
    //Donor Email
    @CsvBindByName(column = "Donor Email")
    private String donorEmail;
    //Donor Phone
    @CsvBindByName(column = "Donor Phone")
    private String donorPhone;
    //New Express Signup
    @CsvBindByName(column = "New Express Signup")
    private String newExpressSignup;
    //Comments
    @CsvBindByName(column = "Comments")
    private String comments;
    //Check Number
    @CsvBindByName(column = "Check Number")
    private String checkNumber;
    //Check Date
    @CsvBindByName(column = "Check Date")
    private String checkDate;
    //Employer Addr1
    @CsvBindByName(column = "Employer Addr1")
    private String employerAddressLine1;
    //Employer Addr2
    @CsvBindByName(column = "Employer Addr2")
    private String employerAddressLine2;
    //Employer City
    @CsvBindByName(column = "Employer City")
    private String employerCity;
    //Employer State
    @CsvBindByName(column = "Employer State")
    private String employerState;
    //Employer ZIP
    @CsvBindByName(column = "Employer ZIP")
    private String employerZip;
    //Employer Country
    @CsvBindByName(column = "Employer Country")
    private String employerCountry;
    //Donor ID
    @CsvBindByName(column = "Donor ID")
    private String donorID;
    //Fundraiser ID
    @CsvBindByName(column = "Fundraiser ID")
    private String fundraiserID;
    //Fundraiser Recipient ID
    @CsvBindByName(column = "Fundraiser Recipient ID")
    private String fundraiserRecipientID;
    //Fundraiser Contact Email
    @CsvBindByName(column = "Fundraiser Contact Email")
    private String fundraiserContactEmail;
    //Fundraiser Contact First Name
    @CsvBindByName(column = "Fundraiser Contact First Name")
    private String fundraiserContactFirstName;
    //Fundraiser Contact Last Name
    @CsvBindByName(column = "Fundraiser Contact Last Name")
    private String fundraiserContactLastName;
    //Partner ID
    @CsvBindByName(column = "Partner ID")
    private String partnerID;
    //Partner Contact Email
    @CsvBindByName(column = "Partner Contact Email")
    private String partnerContactEmail;
    //Partner Contact First Name
    @CsvBindByName(column = "Partner Contact First Name")
    private String partnerContactFirstName;
    //Partner Contact Last Name
    @CsvBindByName(column = "Partner Contact Last Name")
    private String partnerContactLastName;
    //Reserved
    @CsvBindByName(column = "Reserved")
    private String reserved;
    //Lineitem ID
    @CsvBindByName(column = "Lineitem ID")
    private String lineitemID;
    //AB Test Name
    @CsvBindByName(column = "AB Test Name")
    private String aBTestName;
    //AB Variation
    @CsvBindByName(column = "AB Variation")
    private String aBVariation;
    //Recipient Committee
    @CsvBindByName(column = "Recipient Committee")
    private String recipientCommittee;
    //Recipient ID
    @CsvBindByName(column = "Recipient ID")
    private String recipientID;
    //Recipient Gov ID
    @CsvBindByName(column = "Recipient Gov ID")
    private String recipientGovID;
    //Recipient Election
    @CsvBindByName(column = "Recipient Election")
    private String recipientElection;
    //Reserved
    @CsvBindByName(column = "Reserved")
    private String reserved2;
    //Payment ID
    @CsvBindByName(column = "Payment ID")
    private String paymentID;
    //Payment Date
    @CsvBindByName(column = "Payment Date")
    @CsvDate("MM/dd/yyyy HH:mm")
    private Date paymentDate;
    //Disbursement ID
    @CsvBindByName(column = "Disbursement ID")
    private String disbursementID;
    //Disbursement Date
    @CsvBindByName(column = "Disbursement Date")
    @CsvDate("MM/dd/yyyy HH:mm")
    private Date disbursementDate;
    //Recovery ID
    @CsvBindByName(column = "Recovery ID")
    private String recoveryID;
    //Recovery Date
    @CsvBindByName(column = "Recovery Date")
    @CsvDate("MM/dd/yyyy HH:mm")
    private Date recoveryDate;
    //Refund ID
    @CsvBindByName(column = "Refund ID")
    private String refundID;
    //Refund Date
    @CsvBindByName(column = "Refund Date")
    @CsvDate("MM/dd/yyyy HH:mm")
    private Date refundDate;
    //Fee
    @CsvBindByName(column = "Fee")
    private double fee;
    //Recur Weekly
    @CsvBindByName(column = "Recur Weekly")
    private String recurWeekly;
    //ActBlue Express Lane
    @CsvBindByName(column = "ActBlue Express Lane")
    private String actBlueExpressLane;
    //Reserved
    @CsvBindByName(column = "Reserved")
    private String reserved3;
    //Card Type
    @CsvBindByName(column = "Card Type")
    private String cardType;
    //Reserved
    @CsvBindByName(column = "Reserved")
    private String reserved4;
    //Reserved
    @CsvBindByName(column = "Reserved")
    private String reserved5;
    //Reserved
    @CsvBindByName(column = "Reserved")
    private String reserved6;
    //Reserved
    @CsvBindByName(column = "Reserved")
    private String reserved7;
    //Mobile
    @CsvBindByName(column = "Mobile")
    private String mobile;
    //Recurring Upsell Shown
    @CsvBindByName(column = "Recurring Upsell Shown")
    private String recurringUpsellShown;
    //Recurring Upsell Succeeded
    @CsvBindByName(column = "Recurring Upsell Succeeded")
    private String recurringUpsellSucceeded;
    //Double Down
    @CsvBindByName(column = "Double Down")
    private String doubleDown;
    //Smart Recurring
    @CsvBindByName(column = "Smart Recurring")
    private String smartRecurring;
    //Monthly Recurring Amount
    @CsvBindByName(column = "Monthly Recurring Amount")
    private String monthlyRecurringAmount;
    //Apple Pay
    @CsvBindByName(column = "Apple Pay")
    private String applePay;
    //Card Replaced by Account Updater
    @CsvBindByName(column = "Card Replaced by Account Updater")
    private String cardReplacedbyAccountUpdater;
    //ActBlue Express Donor
    @CsvBindByName(column = "ActBlue Express Donor")
    private String actBlueExpressDonor;
    //Custom Field 1 Label
    @CsvBindByName(column = "Custom Field 1 Label")
    private String customField1Label;
    //Custom Field 1 Value
    @CsvBindByName(column = "Custom Field 1 Value")
    private String customField1Value;
    //Donor US Passport Number
    @CsvBindByName(column = "Donor US Passport Number")
    private String donorUSPassportNumber;
    //Text Message Opt In
    @CsvBindByName(column = "Text Message Opt In")
    private String textMessageOptIn;
    //Gift Identifier
    @CsvBindByName(column = "Gift Identifier")
    private String giftIdentifier;
    //Gift Declined
    @CsvBindByName(column = "Gift Declined")
    private String giftDeclined;
    //Shipping Addr1
    @CsvBindByName(column = "Shipping Addr1")
    private String shippingAddressLine1;
    //Shipping City
    @CsvBindByName(column = "Shipping City")
    private String shippingCity;
    //Shipping State
    @CsvBindByName(column = "Shipping State")
    private String shippingState;
    //Shipping ZIP
    @CsvBindByName(column = "Shipping ZIP")
    private String shippingZip;
    //Shipping Country
    @CsvBindByName(column = "Shipping Country")
    private String shippingCountry;
    //Weekly Recurring Amount
    @CsvBindByName(column = "Weekly Recurring Amount")
    private String weeklyRecurringAmount;
    //Smart Boost Amount
    @CsvBindByName(column = "Smart Boost Amount")
    private String smartBoostAmount;
    //Smart Boost Shown
    @CsvBindByName(column = "Smart Boost Shown")
    private String smartBoostShown;

}