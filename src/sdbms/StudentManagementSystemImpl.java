package sdbms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import CustomException.InvalidChoiceException;
import CustomException.StudentNotFoundException;
import CustomSorting.SortStudentByAge;
import CustomSorting.SortStudentById;
import CustomSorting.SortStudentByMarks;
import CustomSorting.SortStudentByName;

public class StudentManagementSystemImpl implements StudentManagementSystem {
	private static final int case1 = 0;
	Scanner sc = new Scanner(System.in);
	Map<String, Student> db = new LinkedHashMap<String, Student>();

	@Override
	public void addStudent() {
		System.out.println("enter your age");
		int age = sc.nextInt();
		System.out.println("enter your name");
		String name = sc.next();
		System.out.println("enter your mark");
		int marks = sc.nextInt();
		Student std = new Student(age, name, marks);
		db.put(std.getId(), std);
		System.out.println("Student Record Inserted Successfully");
		System.out.println("student id is " + std.getId());

	}

	@Override
	public void displayStudent() {
		System.out.println("enter the id");
		String id = sc.next(); // String id=sc.next().toUpperCase;
		id = id.toUpperCase();
		if (db.containsKey(id)) {
			Student std = db.get(id);
			System.out.println("Student Details");
			System.out.println("Id : " + std.getId());
			System.out.println("Name : " + std.getName());
			System.out.println("Age : " + std.getAge());
			System.out.println("Mark : " + std.getMarks());
		} else {
			try {
				String message = "Student with id " + id + " Not Found";
				throw new StudentNotFoundException(message);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}

	@Override
	public void displayAllStudents() {
		if (db.size() != 0) {
			Set<String> keys = db.keySet();
			for (String key : keys) {
				Student std = db.get(key);
				System.out.println(std); // System.out.println(db.get(key));
			}
		} else {
			try {
				String message = "Student Database is Empty, Nothing To Display";
				throw new StudentNotFoundException(message);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}

	@Override
	public void removeStudent() {
		System.out.println("Enter the id");
		String id = sc.next();
		id = id.toUpperCase();
		if (db.containsKey(id)) {
			System.out.println("Student record found");
			System.out.println(db.get(id));
			db.remove(id);
			System.out.println("Student Record Removed Successfully");
		} else {
			try {
				String message = "Student with id " + id + " Not Found";
				throw new StudentNotFoundException(message);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}

	@Override
	public void removeAllStudents() {
		if (db.size() != 0) {
			System.out.println("Available Student Records: " + db.size());
			db.clear();
			System.out.println("Deleted All the Student Records Successfully");
		} else {
			try {
				String message = "Student Database is Empty , Nothing to Delete";
				throw new StudentNotFoundException(message);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}

	@Override
	public void updateStudent() {
		System.out.println("Enter the id");
		String id = sc.next();
		id = id.toUpperCase();
		if (db.containsKey(id)) {
			Student std = db.get(id);
			System.out.println("1:Update Age\n2:Update Name\n3:Update Marks\nEnter your choice ");
			int ch = sc.nextInt();
			switch (ch) {
			case 1:
				System.out.println("enter the age to be updated");
				int age = sc.nextInt();
				std.setAge(age); // std.setAge(scan.nextInt());
				break;
			case 2:
				System.out.println("enter the name to be updated");
				String name = sc.next();
				std.setName(name);
				break;
			case 3:
				System.out.println("enter the marks to be updated");
				int marks = sc.nextInt();
				std.setMarks(marks);
				break;
			default:
				try {
					String message = "Invalid Choice ,Enter Valid Choice!";
					throw new InvalidChoiceException(message);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
		} else {
			try {
				String message = "Student with id " + id + " Not Found";
				throw new StudentNotFoundException(message);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}

	}

	@Override
	public void countStudents() {
		System.out.println("Available Student Record: " + db.size());
	}

	@Override
	public void sortStudents() {
		if (db.size() >= 2) {
			Set<String> keys = db.keySet();
			List<Student> list = new ArrayList<Student>();
			for (String key : keys) {
				Student std = db.get(key);
				list.add(std);// list.add(db.get(key));
			}
			System.out.println(
					"1:Sort Student By Id\n2:Sort Student By Age\n3:Sort Student By Nme\n4:Sort Student By Mark\n");
			System.out.println("enter your choice");
			int ch = sc.nextInt();
			switch (ch) {
			case 1:
				Collections.sort(list, new SortStudentById());
				display(list);
				break;
			case 2:
				Collections.sort(list, new SortStudentByAge());
				display(list);
				break;
			case 3:
				Collections.sort(list, new SortStudentByName());
				display(list);
				break;
			case 4:
				Collections.sort(list, new SortStudentByMarks());
				display(list);
				break;
			default:
				try {
					String message = "Invalid Choice ,Enter Valid Choice!";
					throw new InvalidChoiceException(message);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
		} else {
			try {
				String message = "No sufficient student record to sort";
				throw new StudentNotFoundException(message);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}

	private static void display(List<Student> list) {
		for (Student s : list) {
			System.out.println(s);
		}

	}

	@Override
	public void getStudentWithHighestMark() {
		if(db.size()>=2) {
			Set<String> keys = db.keySet();
			List<Student> list = new ArrayList<Student>();
			for (String key : keys) {
				Student std = db.get(key);
				list.add(std);// list.add(db.get(key));
			}
			Collections.sort(list, new SortStudentByMarks());
			System.out.println(list.get(db.size()-1));
		}
		else {
			try {
				String message = "No sufficient student record to compare";
				throw new StudentNotFoundException(message);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			
		}
	}

	@Override
	public void getStudentWithLowestMark() {
		if(db.size()>=2) {
			Set<String> keys = db.keySet();
			List<Student> list = new ArrayList<Student>();
			for (String key : keys) {
				Student std = db.get(key);
				list.add(std);// list.add(db.get(key));
			}
			Collections.sort(list, new SortStudentByMarks());
			System.out.println(list.get(0));
		}
		else {
			try {
				String message = "No sufficient student record to compare";
				throw new StudentNotFoundException(message);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			
		}
	}

}
