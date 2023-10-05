package FileHandling;

import java.util.Scanner;
import java.util.Vector;
import java.io.*;

public class Main {

    public static Scanner input = new Scanner(System.in);
    public static Vector<Course> courses = new Vector<>();

    public static void staffAction(User staffAcc){
        // Staff staff;
    }

    public static void studentAction(User studentAcc){
        System.out.println("student");
    }

    public static void adminAction(User adminAcc){
        Admin admin = new Admin();
        int choose = 1;
        while(choose != 0){
            System.out.println("-------------------------------------------------------");
            System.out.println("Course Management:");
            System.out.println("\t1. Create a new course.");
            System.out.println("\t2. Delete a course.");
            System.out.println("\t3. Edit a course.");
            System.out.println("\t4. Display a course.");
            System.out.println("\t5. Add student to a course.");
            System.out.println("Report:");
            System.out.println("\t6. View all course");
            System.out.println("\t7. View all course that full");
            System.out.println("\t8. View names of students registered to a course");
            System.out.println("\t9. View list of course a student is registered in");
            System.out.println("\t10. Sort courses on current number of students registered");
            System.out.println("0. Exit");
            System.out.println("-------------------------------------------------------");
            choose = Integer.parseInt(input.nextLine());
            if (choose != 0) admin.action(choose);
        }
    }

    public static void main(String[] args) throws IOException{
        Scanner fileSc = new Scanner(new File("src\\FileHandling\\Account.csv"));
        Scanner getCourse = new Scanner(new File("src\\FileHandling\\course.csv"));
        Vector<User> userAccounts = new Vector<>();

        // Skip first line because master column
        fileSc.nextLine();
        while(fileSc.hasNextLine()){
            String[] tmpString = fileSc.nextLine().split(", ");
            if (tmpString.length == 4){
                userAccounts.add(new User(tmpString));
            }
        }

        while(getCourse.hasNextLine()){
            String[] tmpString = getCourse.nextLine().split(", ");
            Course tmpCourse = new Course(tmpString);
            courses.add(tmpCourse);
        }

        System.out.println(courses.size());

        while(true){
            System.out.println("Enter your username and password:");
            String username = input.nextLine();
            String password = input.nextLine();

            String loginStatus = "bruh";
            User loginUser = null;

            for (User user : userAccounts) {
                Integer statusCode = user.tryLogin(username, password);

                if (statusCode == 200){
                    loginStatus = "Login successful";
                    loginUser = user;
                    break;
                }else if (statusCode == 404){
                    loginStatus = "Username not found, try again!";
                }else if (statusCode == 406){
                    loginStatus = "Wrong password, try again";
                    break;
                }
            }

            System.out.println(loginStatus);
            if (loginUser != null){
                if (loginUser.getRole().compareTo("STAFF") == 0) staffAction(loginUser);
                else if (loginUser.getRole().compareTo("STUDENT") == 0) studentAction(loginUser);
                else if (loginUser.getRole().compareTo("ADMIN") == 0) adminAction(loginUser);
            }
        }
    }

    public static Course getByID(String id){
        for (Course course : courses){
            if (course.getID().compareTo(id) == 0){
                return course;
            }
        }
        return null;
    }

    public static void updateCourse(){
        try{
            FileWriter fw = new FileWriter("src\\FileHandling\\course.csv");
            PrintWriter output = new PrintWriter(fw);

            for (Course course : courses) {
                output.println(course);
            }

            System.out.println("\nUpdate succes\n");

            output.close();
            fw.close();
        }catch(IOException e){}
    }
}