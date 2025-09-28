import java.io.*;
import java.util.*;

// -------------------- Student Class for Serialization --------------------
class Student implements Serializable {
    private int studentID;
    private String name;
    private String grade;

    public Student(int studentID, String name, String grade) {
        this.studentID = studentID;
        this.name = name;
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "StudentID: " + studentID + ", Name: " + name + ", Grade: " + grade;
    }
}

// -------------------- Employee Class --------------------
class Employee {
    private int id;
    private String name;
    private String designation;
    private double salary;

    public Employee(int id, String name, String designation, double salary) {
        this.id = id;
        this.name = name;
        this.designation = designation;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return id + " | " + name + " | " + designation + " | " + salary;
    }
}

// -------------------- Main Program --------------------
public class Main {
    private static final String EMP_FILE = "employees.txt";
    private static final String STUDENT_FILE = "student.dat";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n=== Main Menu ===");
            System.out.println("1. Sum of Integers (Autoboxing/Unboxing)");
            System.out.println("2. Serialize & Deserialize Student");
            System.out.println("3. Employee Management System");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    sumOfIntegers(sc);
                    break;
                case 2:
                    handleStudentSerialization();
                    break;
                case 3:
                    employeeMenu(sc);
                    break;
                case 4:
                    System.out.println("Exiting program...");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 4);
    }

    // -------------------- Part A: Autoboxing --------------------
    private static void sumOfIntegers(Scanner sc) {
        System.out.println("Enter integers separated by space:");
        String input = sc.nextLine();
        String[] parts = input.split(" ");

        ArrayList<Integer> numbers = new ArrayList<>();
        for (String part : parts) {
            Integer num = Integer.parseInt(part); // Autoboxing
            numbers.add(num);
        }

        int sum = 0;
        for (Integer num : numbers) {
            sum += num; // Unboxing
        }

        System.out.println("Numbers: " + numbers);
        System.out.println("Sum of integers = " + sum);
    }

    // -------------------- Part B: Serialization --------------------
    private static void handleStudentSerialization() {
        // Create student
        Student student = new Student(101, "Alice", "A");

        // Serialize
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(STUDENT_FILE))) {
            oos.writeObject(student);
            System.out.println("Student object serialized successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Deserialize
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(STUDENT_FILE))) {
            Student deserializedStudent = (Student) ois.readObject();
            System.out.println("Deserialized Student: " + deserializedStudent);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // -------------------- Part C: Employee Management --------------------
    private static void employeeMenu(Scanner sc) {
        int choice;
        do {
            System.out.println("\n--- Employee Management ---");
            System.out.println("1. Add Employee");
            System.out.println("2. Display All Employees");
            System.out.println("3. Back to Main Menu");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    addEmployee(sc);
                    break;
                case 2:
                    displayEmployees();
                    break;
                case 3:
                    System.out.println("Returning to Main Menu...");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 3);
    }

    private static void addEmployee(Scanner sc) {
        System.out.print("Enter Employee ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Designation: ");
        String designation = sc.nextLine();

        System.out.print("Enter Salary: ");
        double salary = sc.nextDouble();

        Employee emp = new Employee(id, name, designation, salary);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(EMP_FILE, true))) {
            bw.write(emp.toString());
            bw.newLine();
            System.out.println("Employee added successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void displayEmployees() {
        try (BufferedReader br = new BufferedReader(new FileReader(EMP_FILE))) {
            String line;
            System.out.println("\n--- Employee Records ---");
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
            System.out.println("-------------------------");
        } catch (IOException e) {
            System.out.println("No employee records found.");
        }
    }
}
