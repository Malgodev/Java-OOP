package FileHandling;

class Student extends User implements StudentInterface {
    private String id;

    public Student(String[] stringArr){
        super(stringArr[0], stringArr[1], stringArr[2], stringArr[4]);
        this.id = stringArr[3];
    }

    public void action(Integer choose){
        switch(choose){
            case 1:
                this.viewCourse();
                break;
            case 2:
                this.viewCourseNotFull();
                break;
            case 3:
                this.registerCourse();
                break;
            case 4:
                this.withdraw();
                break;
            case 5:
                this.viewStudentCourse();
                break;
        }
    }

    @Override
    public void viewCourse() {
        for (Course course:Main.courses){
            course.displayCourse();
        }
    }

    @Override
    public void viewCourseNotFull() {
        for (Course course:Main.courses){
            if (!course.isFull()) {
                course.displayCourse();
            }
        }
    }

    @Override
    public void registerCourse() {
        System.out.println("Enter Course ID:");
        String tmpID = Main.input.nextLine();
        Course tmpCourse = Main.getByID(tmpID);
        if (tmpCourse != null){
            tmpCourse.addStudentToCourse(this.id);  
        }else System.out.println("bruh");
        Main.updateCourse();
    }

    @Override
    public void withdraw() {
        System.out.println("Enter Course ID:");
        String tmpID = Main.input.nextLine();
        Course tmpCourse = Main.getByID(tmpID);
        if (tmpCourse != null){
            tmpCourse.removeFromCourse(this.id);  
        }else System.out.println("bruh");
        Main.updateCourse();
    }

    @Override
    public void viewStudentCourse() {
        for (Course course:Main.courses){
            if (course.inCourse(this.id)){
                course.displayCourse();
            }
        }
    }
}