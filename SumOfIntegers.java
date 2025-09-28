import java.util.ArrayList;
import java.util.Scanner;

public class SumOfIntegers {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Step 1: Take input
        System.out.println("Enter integers separated by spaces:");
        String input = sc.nextLine();

        // Step 2: Split input string into parts
        String[] parts = input.split(" ");

        // Step 3: Store parsed integers in ArrayList (autoboxing)
        ArrayList<Integer> numbers = new ArrayList<>();
        for (String part : parts) {
            Integer num = Integer.parseInt(part); // String -> int -> Autoboxed to Integer
            numbers.add(num);
        }

        // Step 4: Calculate sum using enhanced for loop (unboxing)
        int sum = 0;
        for (Integer num : numbers) {
            sum += num; // Unboxing: Integer -> int
        }

        // Step 5: Display result
        System.out.println("Numbers: " + numbers);
        System.out.println("Sum of integers = " + sum);

        sc.close();
    }
}
