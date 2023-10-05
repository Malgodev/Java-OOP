package FileHandling;

public class Admin implements AdminInterface{
    private Integer iDontKnowWhatToDoHelp = 0;

    public Admin(){
        iDontKnowWhatToDoHelp = 1;
    }

    public void action(Integer choose){
        switch(choose){
            case 1:
                this.createCourse();
                break;
            case 2:
                this.deleteCourse();
                break;
            case 3:
                this.editCourse();
                break;
            case 4:
                this.displayCourse();
                break;
            case 5:
                this.addToCourse();
                break;
            case 6:
                this.viewCourse();
                break;
            case 7:
                this.viewFullCourse();
                break;
            case 8:
                this.viewStudentInCourse();
                break;
            case 9:
                this.studentCourse();
                break;
            case 10:
                this.sortStudentNum();
                break;
        }
    }

    public Integer kms() { return iDontKnowWhatToDoHelp;}

    @Override
    public void createCourse(){
        Course newCourse;
        System.out.println("Enter Course ID, name, maximum student, section number, location");
        String tmpID = Main.input.nextLine();
        String tmpName = Main.input.nextLine();
        Integer tmpMax = Integer.parseInt(Main.input.nextLine()); 
        String tmpSec = Main.input.nextLine();
        String tmpLocation = Main.input.nextLine();

        newCourse = new Course(tmpID, tmpName, tmpMax, tmpSec,tmpLocation);

        Main.courses.add(newCourse);
        Main.updateCourse();
    }

    @Override
    public void deleteCourse() {
        System.out.println("Enter Course ID:");
        String tmpID = Main.input.nextLine();
        Course tmpCourse = Main.getByID(tmpID);
        if (tmpCourse != null) Main.courses.remove(tmpCourse);
        Main.updateCourse();
    }

    @Override
    public void editCourse() {
        System.out.println("Enter Course ID:");
        String tmpID = Main.input.nextLine();
        Course tmpCourse = Main.getByID(tmpID);
        if (tmpCourse != null) tmpCourse.editCourse(Main.input); 
        else System.out.println("bruh");
        Main.updateCourse();   
    }

    @Override
    public void displayCourse() {
        System.out.println("Enter Course ID:");
        String tmpID = Main.input.nextLine();
        Course tmpCourse = Main.getByID(tmpID);
        if (tmpCourse != null) {
            tmpCourse.displayCourse(); 
            tmpCourse.displayStudentInCourse();
        }
        else System.out.println("bruh");
    }

    @Override
    public void addToCourse() {
        System.out.println("Enter Course ID:");
        String tmpID = Main.input.nextLine();
        Course tmpCourse = Main.getByID(tmpID);
        if (tmpCourse != null){
            System.out.println("Enter student ID");
            String tmpStuID = Main.input.nextLine();
            tmpCourse.addStudentToCourse(tmpStuID);  
        }else System.out.println("bruh");
        Main.updateCourse();
    }

    @Override
    public void viewCourse() {
        for (Course course:Main.courses){
            course.displayCourse();
        }
    }

    @Override
    public void viewFullCourse() {
        int fullCourseNum = 0;
        for (Course course:Main.courses){
            if (course.isFull()) {
                fullCourseNum++;
                course.displayCourse();
            }
        }
        System.out.println(fullCourseNum);
    }

    @Override
    public void viewStudentInCourse() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'viewStudentInCourse'");
    }

    @Override
    public void studentCourse() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'studentCourse'");
    }

    @Override
    public void sortStudentNum() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'sortStudentNum'");
    }
    
}
