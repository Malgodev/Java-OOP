package FileHandling;

import java.util.Scanner;
import java.util.Vector;
import java.io.*;

public class Main {

    public static Scanner input = new Scanner(System.in);
    public static Vector<Course> courses = new Vector<>();
    public static Admin admin;
    public static Vector<Student> students = new Vector<>();
    public static Vector<Staff> staffs = new Vector<>();

    public static void adminAction(Admin admin) {
        System.out.println("Login as Admin");
        int choose = 1;
        while (choose != 0) {
            System.out.println("""
                    -------------------------------------------------------
                    Course Management:
                        \t1. Create a new course.
                        \t2. Delete a course.
                        \t3. Edit a course.
                        \t4. Display a course.
                        \t5. Add student to a course.
                    Report:
                        \t6. View all course
                        \t7. View all course that full
                        \t8. View names of students registered to a course
                        \t9. View list of course a student is registered in
                        \t10. Sort courses on current number of students registered
                        0. Exit
                    -------------------------------------------------------
                    """);
            choose = Integer.parseInt(input.nextLine());
            if (choose != 0)
                admin.action(choose);
        }
    }

    public static void staffAction(Staff staffAcc) {
        System.out.println("Login as Staff");
        int choose = 1;
        while (choose != 0) {
            System.out.println("""
            -------------------------------------------------------
                \t1. View all courses.
                \t2. Register teaching a course.
                \t3. Withdraw from a course.
                \t4. View all course that registered.
            0. Exit
            -------------------------------------------------------
            """);
            choose = Integer.parseInt(input.nextLine());
            if (choose != 0) staffAcc.action(choose);
        }
    }

    public static void studentAction(Student studentAcc) {
        System.out.println("Login as Student");
        int choose = 1;
        while (choose != 0) {
            System.out.println("""
            -------------------------------------------------------
                \t1. View all courses.
                \t2. View all courses that are not full.
                \t3. Register a course.
                \t4. Withdraw from a course.
                \t5. View all courses student is registered in.
            0. Exit
            -------------------------------------------------------
            """);
            choose = Integer.parseInt(input.nextLine());
            if (choose != 0) studentAcc.action(choose);
        }
    }

    public static void main(String[] args) throws IOException {
        init();

        while (true) {
            System.out.println("Enter your username and password:");
            String username = input.nextLine();
            String password = input.nextLine();

            System.out.println(admin);

            Object user = getUser(username, password);

            if (user == null)
                System.out.println("Wrong username or password");
            else {
                if (user instanceof Admin)
                    adminAction((Admin) user);
                else if (user instanceof Staff)
                    staffAction((Staff) user);
                else if (user instanceof Student)
                    studentAction((Student) user);
            }
        }
    }

    private static void init() throws IOException {
        Scanner getUser = new Scanner(new File("src\\FileHandling\\Account.csv"));
        Scanner getCourse = new Scanner(new File("src\\FileHandling\\course.csv"));

        // Skip first line because master column
        getUser.nextLine();
        while (getUser.hasNextLine()) {
            String[] tmpString = getUser.nextLine().split(", ");
            if (tmpString[0].compareTo("ADMIN") == 0)
                admin = new Admin(tmpString);
            else if (tmpString[0].compareTo("STAFF") == 0) {
                Staff tmpStaff = new Staff(tmpString);
                staffs.add(tmpStaff);
            } else if (tmpString[0].compareTo("STUDENT") == 0) {
                Student tmpStu = new Student(tmpString);
                students.add(tmpStu);
            }
        }

        // Skip first line because master column
        getCourse.nextLine();
        while (getCourse.hasNextLine()) {
            String[] tmpString = getCourse.nextLine().split(", ");
            Course tmpCourse = new Course(tmpString);
            courses.add(tmpCourse);
        }
    }

    private static Object getUser(String username, String password) {
        if (admin.tryLogin(username, password) == 200) return admin;
        for (Student student : students) {
            if (student.tryLogin(username, password) == 200)
                return student;
        }
        for (Staff staff : staffs) {
            if (staff.tryLogin(username, password) == 200) return staff;
        }
        return null;
    }

    public static Course getByID(String id) {
        for (Course course : courses) {
            if (course.getID().compareTo(id) == 0) {
                return course;
            }
        }
        return null;
    }

    public static void updateCourse() {
        try {
            FileWriter fw = new FileWriter("src\\FileHandling\\course.csv");
            PrintWriter output = new PrintWriter(fw);

            output.println("course ID, name, max student, instructor, section number, location, student, ");

            for (Course course : courses) {
                output.println(course);
            }

            System.out.println("\nUpdate succes\n");

            output.close();
            fw.close();
        } catch (IOException e) {
        }
    }
}