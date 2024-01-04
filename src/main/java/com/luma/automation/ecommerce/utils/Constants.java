package com.luma.automation.ecommerce.utils;

import java.util.Arrays;
import java.util.List;

public class Constants {

	public static final String LOGIN_PAGE_TITLE="Customer Login";
	public static final String WELCOME_MESSAGE="Welcome, test user1!";
	public static final String ACCOUNT_PAGE_TITLE="My Account";
	public static final String ACCOUNT_INFO_TITLE="Account Information";
	public static final String ACCOUNT_LOGOUT_MESSAGE="You are signed out";
	public static final String CONTACT_INFO_TITLE="Contact Information";
	public static final int DEFAULT_WAIT_TIMEOUT=5;
	
	/**********************Home Page***************/
	
	public static final String HOME_PAGE_TITLE="Home Page";
	public static final String HOME_PAGE_TRENDING_TITLE="Hot Sellers";
	public static final String HOME_PAGE_TRENDING_SUBTITLE="Here is what`s trending on Luma right now";
	public static final String HOME_PAGE_HOT_SELLER_TITLE="Hot Sellers";
	public static final String HOME_PAGE_HOT_SELLER_SUB_TITLE_INFO="Here is what`s trending on Luma right now";
	public static final List<String> HOME_PAGE_PROMOS = 
			Arrays.asList("20% OFF","Get fit and look fab in new seasonal styles",
			"Even more ways to mix and match",
			"Take it from Erin","Science meets performance",
			"Twice around, twice as nice");
	/*********************Registration/Create Account Page **********/
	public static final String REGISTRATION_PAGE_TITLE="Create New Customer Account";
	public static final String REGISTRATION_PAGE_PERSONAL_INFO_SUBTITLE="Personal Information";
	public static final String REGISTRATION_PAGE_SIGNIN_INFO_SUBTITLE="Sign-in Information";
	public static final String REGISTRATION_PAGE_CREATE_BTN_LABEL="Create an Account";
	public static final String REGISTRATION_PAGE_PASSWORD_ERROR="Minimum length of this field must be equal or greater than 8 symbols. Leading and trailing spaces will be ignored.";
	public static final String REGISTRATION_PAGE_PASSWORD_STRGNTH_METER_LABEL_VSTRONG="Very Strong";
	public static final String REGISTRATION_PAGE_PASSWORD_STRGNTH_METER_LABEL_STRONG="Strong";
	public static final String REGISTRATION_PAGE_PASSWORD_STRGNTH_METER_LABEL_WEAK="Weak";
	public static final String REGISTRATION_PAGE_SUCCESS="Thank you for registering with Main Website Store.";
			
}

