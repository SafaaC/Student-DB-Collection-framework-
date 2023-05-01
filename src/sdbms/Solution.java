package sdbms;

import java.util.Scanner;

import CustomException.InvalidChoiceException;

public class Solution {
	public static void main(String[] args) {
		System.out.println("Welcome to Student DB Project");
		System.out.println("-----------------------------");
		Scanner sc = new Scanner(System.in);
		StudentManagementSystem sms = new StudentManagementSystemImpl();
		while (true) {
			System.out.println(
					"1:ADD STUDENT\n2:DISPLAY STUDENT\n3:DISPLAY ALL STUDENTS\n4:REMOVE STUDENT\n5:REMOVE ALL STUDENTS");
			System.out.println(
					"6:UPDATE STUDENT\n7:COUNT STUDENTS\n8:SORT STUDENTS\n9:GET STUDENT WITH HIGHEST MARK\n10:GET STUDENT WITH LOWEST MARK");
			System.out.println("Enter your choice");
			int choice = sc.nextInt();
			switch (choice) {
			case 1:
				sms.addStudent();
				break;
			case 2:
				sms.displayStudent();
				break;
			case 3:
				sms.displayAllStudents();
				break;
			case 4:
				sms.removeStudent();
				break;
			case 5:
				sms.removeAllStudents();
				break;
			case 6:
				sms.updateStudent();
				break;
			case 7:
				sms.countStudents();
				break;
			case 8:
				sms.sortStudents();
				break;
			case 9:
				sms.getStudentWithHighestMark();
				break;
			case 10:
				sms.getStudentWithLowestMark();
				break;
			case 11:
				System.out.println("Thank you");
				System.exit(0);
			default:
				try {
					String mssg = "Invalid Choice ,Enter Valid Choice!";
					throw new InvalidChoiceException(mssg);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
			System.out.println("----------------------------");
		}
	}
}
