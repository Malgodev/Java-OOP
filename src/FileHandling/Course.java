package FileHandling;

import java.util.Scanner;
import java.util.Vector;

public class Course {
    private String id, name, sectionNum, location;
    private Integer maxStudent;
    private Vector<String> students;
    private Staff instructor;
    
    public Course(String id, String name, Integer maxStudent, String sectionNum, String location){
        this.id = id;
        this.name = name;
        this.maxStudent = maxStudent;
        instructor = new Staff();
        this.sectionNum = sectionNum;
        this.location = location;
        students = new Vector<>();
    }

    public Course(String[] strArr){
        this.id = strArr[0];
        this.name = strArr[1];
        this.maxStudent = Integer.parseInt(strArr[2]);
        instructor = new Staff();
        this.sectionNum = strArr[4];
        this.location = strArr[5];

        students = new Vector<>();
        String[] tmpStudents = strArr[6].trim().split("\\|");
        for (String student:tmpStudents) students.add(student);
    }

    public Course(String lmao){
        id = lmao;
    }

    public void editCourse(Scanner sc){
        System.out.println("Enter new Course maximum student, section number, location:");
        this.maxStudent = Integer.parseInt(sc.nextLine());
        this.sectionNum = sc.nextLine();
        this.location = sc.nextLine();
    }

    public void displayCourse(){
        System.out.println("+----------------------------------------------+");
        System.out.printf("Cousre ID: %s \t\tCourse Name: %s\n", this.id, this.name);
        System.out.printf("Instructor: %s\n", this.instructor);
        System.out.printf("Cousre section number: %s \tLocation: %s\n", this.sectionNum, this.location);
        System.out.printf("Maximum student: %s \t\tCurrent students: %s\n", this.maxStudent, this.students.size());
        System.out.println("+----------------------------------------------+");
    }

    public void displayStudentInCourse(){
        for (String string : students) {
            System.out.println(string);
        }
        System.out.println("+----------------------------------------------+");
    }

    public void addStudentToCourse(String newID){
        students.add(newID);
    }

    public boolean isFull(){
        if (students.size() == maxStudent) return true;
        else return false;
    }

    public String getID(){
        return this.id;
    }

    @Override
    public String toString(){
        if (id == "") return "";

        String tmp = String.format("%s, %s, %02d, %s, %s, %s, ", this.id, this.name, this.maxStudent, this.instructor, this.sectionNum, this.location);
        for (int i = 0; i < students.size(); i++) {
            tmp += students.get(i) + "|";
        }     
        tmp += ", ";
        return tmp;
    }
}
