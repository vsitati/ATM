/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atm;

/**
 *
 * @author Victor
 */
import java.util.*;

public class ATM {

    public static Scanner kbd = new Scanner(System.in);
    // The checkID method determines if acctNum is a valid account number
    // and pwd is the correct password for the account.  If the account information
    // is valid, the method returns the current account balance, as a string.
    // If the account information is invalid, the method returns the string "error".
    public static String checkID(String acctNum, String pwd)
    {
        String result = "error";

        // Strings a, b, and c contain the valid account numbers and passwords.
        // For each string, the account number is listed first, followed by
        // a space, followed by the password for the account, followed by a space,
        // followed by the current balance.
        String a = "0020162480467 1234 1476.45";
        String b = "0042357894521 4321 74.20";
        String c = "4578123654752 9876 796.74";

        if (acctNum.equals(a.substring(0, a.indexOf(" "))) && 
                pwd.equals(a.substring(a.indexOf(" ")+1,a.lastIndexOf(" "))))
            return result = a.substring(a.lastIndexOf(" ") + 1);

        if (acctNum.equals(b.substring(0, b.indexOf(" "))) && 
                pwd.equals(b.substring(b.indexOf(" ")+1,b.lastIndexOf(" "))))
            return result = b.substring(b.lastIndexOf(" ") + 1);

        if (acctNum.equals(c.substring(0, c.indexOf(" "))) && 
                pwd.equals(c.substring(c.indexOf(" ") + 1,c.lastIndexOf(" "))))
            return result = c.substring(c.lastIndexOf(" ") + 1);

        return result;
    }

    public static int menu()
    {
        int menuChoice;
        do
        { 
            System.out.print("\nPlease Choose a Menu option:"
                    + "\n 1. Display Balance \n 2. Deposit"
                    + "\n 3. Withdraw\n 4. Log Out\n\n");

            menuChoice = kbd.nextInt();

            if (menuChoice < 1 || menuChoice > 4){
                System.out.println("Error: choose between the options below");
            }

        }while (menuChoice < 1 || menuChoice > 4);

        return menuChoice;
    }

    public static void displayBalance(double x)
    {
        System.out.printf("\nYour Current Balance is KES%.2f\n", x);
    }

    public static double deposit(double x, double y)
    {
        double depositAmt = y, currentBal = x;
        double newBalance = depositAmt + currentBal;

        System.out.printf("Your New Balance is KES%.2f\n",  newBalance);

        return newBalance;
    }

    public static double withdraw(double x, double y)
    {
        double withdrawAmt = y, currentBal = x, newBalance;

        newBalance = currentBal - withdrawAmt;
        System.out.printf("Your New Balance is KES%.2f\n",newBalance);

        return newBalance;  
    }

    public static void main(String[] args) {

        String accNum, pass, origBal = "error";
        int count = 0, menuOption = 0;
        double depositAmt = 0, withdrawAmt = 0, currentBal=0; 
        boolean  foundNonDigit;
         
        do{
            foundNonDigit = false;
            System.out.println("Please Enter Your Account Number: ");
            accNum = kbd.next();

            System.out.println("Enter Your Password: ");
            pass = kbd.next();

            origBal = checkID(accNum, pass);

            count++;

            if (count >= 3 && origBal.equals("error")){
                System.out.print("Maximum Login Attempts Reached.");
                System.exit(0);
            }
            if (!(origBal.equals("error"))){
                System.out.println("\nYour New Balance is: KES "+ origBal);
            }
            else
                System.out.println(origBal);


        }while(origBal.equals("error"));

        currentBal=Double.parseDouble(origBal);
        

        while (menuOption != 4)
        { 
            menuOption=menu();
            switch (menuOption)
            {
            case 1:
                displayBalance(currentBal);
                break;
            case 2:
                System.out.print("\nEnter Amount You Wish to Deposit: KES ");
                depositAmt = kbd.nextDouble();
                currentBal=deposit(depositAmt, currentBal);
                break;
            case 3:
                System.out.print("\nEnter Amount You Wish to Withdrawl: KES ");
                withdrawAmt = kbd.nextDouble();

                while(withdrawAmt>currentBal){
                    System.out.print("ERROR: INSUFFICIENT FUNDS!! "
                            + "PLEASE ENTER A DIFFERENT AMOUNT: KES");
                    withdrawAmt = kbd.nextDouble();
                }

                currentBal = withdraw(currentBal, withdrawAmt);
                break;
            case 4:
                System.out.print("\nThank For Using This Awesome1 ATM.  Have a Nice Day.  Good-Bye!");
                System.exit(0);
                break;
            }
        }
    }
    
}
