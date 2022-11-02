import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UnitTest {
    public void verifyUserProvidedNumberIsANaturalNumber(int number) {
        if(number <= 0) {
            System.out.println(number + " is not a natural number");
            System.exit(0);
        }
    }

    @Test
    public void verifyUserProvidedNumberIsANaturalNumberTest() {
        verifyUserProvidedNumberIsANaturalNumber(3);
    }

    public int getNumberOfFactorsOfSpecifiedNumber(int number) {
        int count = 0;
        for(int iterator = 1; iterator <= number; ++iterator) {
            if(number % iterator == 0) {
                count++;
            }
        }
        return count;
    }

    @Test
    public void verifyNumberOfFactorsOfSpecifiedNumberTest() {
        Assert.assertEquals(getNumberOfFactorsOfSpecifiedNumber(3), 2);
    }

    public int getNumberOfInstanceHavingSameNumberOfFactorForGivenLimit(int limit) {
        int numberOfInstance = 0;
        for(int number = 1; number <= limit; number++) {
            int count = getNumberOfFactorsOfSpecifiedNumber(number);
            int succeedingCount = getNumberOfFactorsOfSpecifiedNumber(number + 1);
            if(count == succeedingCount) {
                numberOfInstance++;
            }
        }
        return numberOfInstance;
    }

    @Test
    public void verifyNumberOfInstanceHavingSameNumberOfFactorForGivenLimitTest() {
        Assert.assertEquals(getNumberOfInstanceHavingSameNumberOfFactorForGivenLimit(3), 1);
    }

    public List<Integer> displayNumberOfInstancesWithSameNumberOfFactorsOfAdjacentNumbers(List<Integer> listOfNumbers) {
        List<Integer> listOfNumberOfIntances = new ArrayList<>();
        for(int arrayIndex = 0; arrayIndex < listOfNumbers.size(); arrayIndex++) {
            int numberOfInstances = getNumberOfInstanceHavingSameNumberOfFactorForGivenLimit(listOfNumbers.get(arrayIndex));
            listOfNumberOfIntances.add(numberOfInstances);
        }
        return listOfNumberOfIntances;
    }

    @Test
    public void verifydisplayNumberOfInstancesWithSameNumberOfFactorsOfAdjacentNumbersTest() {
        List<Integer> listOfActualValues = displayNumberOfInstancesWithSameNumberOfFactorsOfAdjacentNumbers(List.of(3, 15, 100));
        List<Integer> listOfExpectedValues = List.of(1, 2, 15);
        for(int index = 0; index < listOfActualValues.size(); index++) {
            Assert.assertEquals(listOfActualValues.get(index), listOfExpectedValues.get(index));
        }
    }

    public static void main(String[] args) {
        List<Integer> listOfUserInputs= new ArrayList<>();
        UnitTest test = new UnitTest();
        Scanner userInput = new Scanner(System.in);
        int numberOfTestcases = userInput.nextInt();
        test.verifyUserProvidedNumberIsANaturalNumber(numberOfTestcases);
        for(int iterator = 1; iterator <= numberOfTestcases; iterator++) {
            int number = userInput.nextInt();
            test.verifyUserProvidedNumberIsANaturalNumber(number);
            listOfUserInputs.add(number);
        }
        System.out.println(test.displayNumberOfInstancesWithSameNumberOfFactorsOfAdjacentNumbers(listOfUserInputs));
    }
}
