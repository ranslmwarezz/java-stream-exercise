package application;

import entity.Employee;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        List<Employee> list = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        Locale.setDefault(Locale.US);
        String path = "/home/renanzzz/Área de trabalho/CursoDoNelio/employeeFile.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {

            String line = br.readLine();

            while (line != null) {
                String[] fields = line.split(",");
                list.add(new Employee(fields[0], fields[1], Double.parseDouble(fields[2])));
                line = br.readLine();
            }

            Comparator<String> comparator = (e, w) -> e.toUpperCase().compareTo(w.toUpperCase());
            System.out.println("Enter the value: ");
            double salaryInput = sc.nextDouble();

            List<String> emails = list.stream().filter(e -> e.getSalary() > salaryInput)
                    .map(Employee::getEmail).sorted(comparator).toList();
            System.out.println("Email of people whose salary is more than " + String.format("%.2f", salaryInput) + ":");
            emails.forEach(System.out::println);

            double sum = list.stream().filter(e -> e.getName().startsWith("M"))
                    .mapToDouble(Employee::getSalary).sum();

            System.out.println("Sum of salary of people whose name starts with 'M': " + String.format("%.2f", sum));

        } catch (IOException ioException) {
            System.out.println("Error: " + ioException.getMessage());
        }
        sc.close();
    }
}
