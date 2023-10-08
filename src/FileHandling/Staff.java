package FileHandling;

class Staff extends User implements StaffInterface{
    private String id;

    public Staff(String [] stringArr){
        super(stringArr[0], stringArr[1], stringArr[2], stringArr[4]);
        this.id = stringArr[3];
    }

    public String getID(){ return this.id;}

    public void action(Integer choose){
        switch(choose){
            case 1:
                this.viewCourse();
                break;
            case 2:
                this.registerCourse();
                break;
            case 3:
                this.withdraw();
                break;
            case 4:
                this.viewStaffCourse();
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
    public void registerCourse() {
        System.out.println("Enter Course ID:");
        String tmpID = Main.input.nextLine();
        Course tmpCourse = Main.getByID(tmpID);
        if (tmpCourse != null){
            tmpCourse.addIntrustorToCourse(this.id);  
        }else System.out.println("bruh");
        Main.updateCourse();
    }
    
    @Override
    public void withdraw() {
        System.out.println("Enter Course ID:");
        String tmpID = Main.input.nextLine();
        Course tmpCourse = Main.getByID(tmpID);
        if (tmpCourse != null){
            tmpCourse.addIntrustorToCourse("null");  
        }else System.out.println("bruh");
        Main.updateCourse();
    }
    
    @Override
    public void viewStaffCourse() {
        for (Course course:Main.courses){
            if (course.getInstructor().compareTo(id) == 0){
                course.displayCourse();
            }
        }
    }

    @Override
    public String toString(){
        return this.id;
    }
}
