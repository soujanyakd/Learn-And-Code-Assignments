import java.util.Scanner;

import static java.lang.System.exit;

public class ATM {
    public static int balance = 5000;
    public static int pin = 5544;

    public static void validatePin()  {
        Scanner scan = new Scanner(System.in);
        int count = 1;
        System.out.println("Enter Pin\n");
        int userEnteredPin = scan.nextInt();
        while (count < 3) {
            if (userEnteredPin != pin) {
                System.out.println("Incorrect PIN, Please re-enter your pin");
                userEnteredPin = scan.nextInt();
                count++;
            } else if(userEnteredPin == pin){
                System.out.println("ATM Entered Successfully");
                break;
            }
        }
        if(count == 3) {
            try {
                throw new Exception("Reached Maximum Limit");
            } catch (Exception exception) {
                System.out.println("The card got blocked after 3 invalid pin attempts");
                exit(0);
            }
        }
    }

    public static void validateWithdrawalAmount() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the Amount");
        int userEnteredAmount = scan.nextInt();
        try {
            if(userEnteredAmount >= balance) {
                throw new Exception("Insufficient Balance in the Account");
            } else {
                System.out.println("After Withdrawal Available balance is: " +  (balance - userEnteredAmount));
            }
        } catch (Exception exception) {
            System.out.println("Insufficient cash available in ATM machine / Account");
        }
    }

    public static void main(String[] args) {
        System.out.println("Withdraw money from the ATM\n");
        validatePin();
        validateWithdrawalAmount();
    }
}
