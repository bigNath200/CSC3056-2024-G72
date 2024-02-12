package tests;

import model.Account;

import java.util.Calendar;
import java.util.Date;

public class AccountTest {
    public static void testConstructor(){
        // 1-Setup
        String test_account_number = "1234";
        String test_username_of_account_holder = "bigman26";
        String test_account_type = "checking";
        Date test_account_opening_date = new Date(2000, 11, 21);

        // 2-Exercise
        Account testAccount = new Account(test_account_number, test_username_of_account_holder, test_account_type, test_account_opening_date);

        // 3-Verify
        System.out.println("Starting the assertions of the test method: testConstructor");
        assert testAccount.getAccount_number() == test_account_number;
        assert testAccount.getUsername_of_account_holder() == test_username_of_account_holder;
        assert testAccount.getAccount_type() == test_account_type;
        assert testAccount.getAccount_opening_date() == test_account_opening_date;
        System.out.println("All Java assertions in the test suite passed (none failed)");
    }

    public static void testSetters(){
        // 1-Setup
        // initial account details
        String initial_account_number = "1234";
        String initial_username_of_account_holder = "bigman26";
        String initial_account_type = "checking";
        Date initial_account_opening_date = new Date(2000, Calendar.DECEMBER, 21);
        // test account details
        String test_account_number = "5678";
        String test_username_of_account_holder = "smallman26";
        String test_account_type = "savings";
        Date test_account_opening_date = new Date(2020, Calendar.NOVEMBER, 20);

        // 2-Exercise
        Account testAccount = new Account(initial_account_number, initial_username_of_account_holder, initial_account_type, initial_account_opening_date);

        testAccount.setAccount_number(test_account_number);
        testAccount.setUsername_of_account_holder(test_username_of_account_holder);
        testAccount.setAccount_type(test_account_type);
        testAccount.setAccount_opening_date(test_account_opening_date);

        // 3-Verify
        System.out.println("Starting the assertions of the test method: testSetters");
        assert testAccount.getAccount_number() == test_account_number;
        assert testAccount.getUsername_of_account_holder() == test_username_of_account_holder;
        assert testAccount.getAccount_type() == test_account_type;
        assert testAccount.getAccount_opening_date() == test_account_opening_date;
        System.out.println("All Java assertions in the test suite passed (none failed)");
    }

    public static void main(String[] args){
        testConstructor();
        testSetters();
    }
}
