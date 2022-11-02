import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TDDTest {

    public void verifyUserProvidedNumberIsANaturalNumber(int number) {
        if(number <= 0) {
            System.out.println(number + " is not a natural number");
            System.exit(0);
        }
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

    public List<Integer> displayNumberOfInstancesWithSameNumberOfFactorsOfAdjacentNumbers(List<Integer> listOfNumbers) {
        List<Integer> listOfNumberOfIntances = new ArrayList<>();
        for(int arrayIndex = 0; arrayIndex < listOfNumbers.size(); arrayIndex++) {
            int numberOfInstances = getNumberOfInstanceHavingSameNumberOfFactorForGivenLimit(listOfNumbers.get(arrayIndex));
            listOfNumberOfIntances.add(numberOfInstances);
        }
        return listOfNumberOfIntances;
    }


    //positive Scenario
    @Test
    public void validateNumberOfInstancesWithSameNumberOfFactorsOfAdjacentNumbers() {
        List<Integer> listOfNumbers = List.of(3, 15, 100);
        Assert.assertEquals(listOfNumbers.size(), 3);
        for (int iterator = 0; iterator < listOfNumbers.size(); iterator++) {
            verifyUserProvidedNumberIsANaturalNumber(listOfNumbers.get(iterator));
        }
        List<Integer> listOfNumberOfInstance = displayNumberOfInstancesWithSameNumberOfFactorsOfAdjacentNumbers(listOfNumbers);
        Assert.assertTrue(listOfNumberOfInstance.get(0) == 1);
        Assert.assertTrue(listOfNumberOfInstance.get(1) == 2);
        Assert.assertTrue(listOfNumberOfInstance.get(2) == 15);
    }

    //negative Scenario
    @Test
    public void validateNumberOfInstancesForNegetiveNumbers() {
        List<Integer> listOfNumbers = List.of(3, -15, 100);
        Assert.assertEquals(listOfNumbers.size(), 3);
        for (int iterator = 0; iterator < listOfNumbers.size(); iterator++) {
            verifyUserProvidedNumberIsANaturalNumber(listOfNumbers.get(iterator));
        }
        List<Integer> listOfNumberOfInstance = displayNumberOfInstancesWithSameNumberOfFactorsOfAdjacentNumbers(listOfNumbers);
        Assert.assertTrue(listOfNumberOfInstance.get(0) == 1);
        Assert.assertTrue(listOfNumberOfInstance.get(1) == 2);
        Assert.assertTrue(listOfNumberOfInstance.get(2) == 15);
    }

    //negative Scenario
    @Test
    public void validateNumberOfInstancesForInvalidTestcaseNumber() {
        List<Integer> listOfNumbers = List.of(3, 15, 100);
        Assert.assertEquals(listOfNumbers.size(), 0);
        for (int iterator = 0; iterator < listOfNumbers.size(); iterator++) {
            verifyUserProvidedNumberIsANaturalNumber(listOfNumbers.get(iterator));
        }
        List<Integer> listOfNumberOfInstance = displayNumberOfInstancesWithSameNumberOfFactorsOfAdjacentNumbers(listOfNumbers);
        Assert.assertTrue(listOfNumberOfInstance.get(0) == 1);
        Assert.assertTrue(listOfNumberOfInstance.get(1) == 2);
        Assert.assertTrue(listOfNumberOfInstance.get(2) == 15);
    }

    //negative Scenario
    @Test
    public void validateNumberOfInstancesForInvalidAssertion() {
        List<Integer> listOfNumbers = List.of(3, 15, 100, 200);
        Assert.assertEquals(listOfNumbers.size(), 4);
        for (int iterator = 0; iterator < listOfNumbers.size(); iterator++) {
            verifyUserProvidedNumberIsANaturalNumber(listOfNumbers.get(iterator));
        }
        List<Integer> listOfNumberOfInstance = displayNumberOfInstancesWithSameNumberOfFactorsOfAdjacentNumbers(listOfNumbers);
        Assert.assertEquals(Optional.ofNullable(listOfNumberOfInstance.get(0)), Optional.ofNullable(1));
        Assert.assertEquals(Optional.ofNullable(listOfNumberOfInstance.get(1)), Optional.ofNullable(2));
        Assert.assertEquals(Optional.ofNullable(listOfNumberOfInstance.get(2)), Optional.ofNullable(15));
        Assert.assertEquals(Optional.ofNullable(listOfNumberOfInstance.get(3)), Optional.ofNullable(100));
    }
}
