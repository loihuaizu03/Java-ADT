
package control;

import ADT.ArrayList;
import ADT.ListInterface;
import ADT.SortedArrayList;
import ADT.SortedListInterface;
import Entity.Course;
import Entity.Student;
import boundary.CourseUI;
import utility.MessageUI;
import boundary.StudentUI;
import dao.CommonDAO;

/**
 *
 * @author Loi Huai Zu
 */

public class StudentMaintenance{
    
    //all student list 
    private ListInterface<Student> studentList = new ArrayList<>();
    
    //course list
    private ListInterface<Course> courseList = new ArrayList<>();
    
    //filter list
    private ListInterface<Student> FilterList = new ArrayList<>();
    
    //Search Method using sortedList
    private SortedListInterface<Integer> searchStudent = new SortedArrayList<Integer>() ;
    
    //dao
    private CommonDAO studentDAO = new CommonDAO("student.txt");
    private CommonDAO courseDAO = new CommonDAO("course.txt");
    
    //object for UI
    StudentUI studentUI = new StudentUI();
    CourseUI courseUI = new CourseUI();

    public StudentMaintenance() {
        studentList = studentDAO.retrieveFromFile();
        courseList = courseDAO.retrieveFromFile();
    }
    
    public void runStudentMaintenance(){
        boolean back = false;
        MessageUI.printLine();
        do {     
            
            int choice = studentUI.getMenuChoice();
            switch (choice) {
                case 0:
                    MessageUI.displayBackMessage();
                    back = true;
                    break;
                
                case 1:
                    MessageUI.printLine();
                    addStudent();
                    break;

                case 2:
                    MessageUI.printLine();
                    removeStudent();
                    break;
                    
                case 3:
                    MessageUI.printLine();
                    amend();
                    break;
                    
                case 4:
                    MessageUI.printLine();
                    search();
                    break;
                    
                case 5:
                    MessageUI.printLine();
                    addStudentToCourses();
                    break;

                case 6:    
                    MessageUI.printLine();
                    removeStudentFromCourse();
                    break;
                    
                case 7:
                    MessageUI.printLine();
                    calculateFeePaid();
                    break;
                    
                case 8:
                    MessageUI.printLine();
                    filter();
                    break;
                
                case 9:
                    //summary report
                    GenerateReports report = new GenerateReports();
                    MessageUI.printLine();

                    int selection = studentUI.getSubmenuChoice();
                    switch (selection) {
                        case 1:
                            report.studentReport1();
                            MessageUI.enterPrompt();
                            break;
                        case 2:
                            report.studentReport2();
                            MessageUI.enterPrompt();
                            break;
                        default:
                            MessageUI.displayErrorMessage();
                    }
                    break;

                default:
                    MessageUI.displayErrorMessage();
            }
        } while(!back);
    }
    
    //list all students
    public boolean listAllStudents(){
        studentUI.displayHeader();
        if(studentList.isEmpty()){
            studentUI.displayEmpty();
        }
        else{
            studentList.list();
        }
        return studentList.isEmpty();
    }
    
    //list courses available
    public boolean listAllCourses(){
        courseUI.displayHeader();
        if(courseList.isEmpty()){
            courseUI.displayEmpty();
            MessageUI.enterPrompt();
        }
        else{
            courseList.list();
        }
        return courseList.isEmpty();
    }
    
    //list courses taken by student
    public boolean listCoursesFromStudent(int studentIndex){
        Student student = studentList.getEntry(studentIndex);
        
        studentUI.displayCourseTaken(student.getId());
        
        courseUI.displayHeader();
        if(student.getCourseList().isEmpty()){
            courseUI.displayEmpty();
            MessageUI.enterPrompt();
        }
        else{
            student.getCourseList().list();
        }
        return student.getCourseList().isEmpty();
    }
    
    //add new student
    public void addStudent(){
        boolean back =false;
        MessageUI.printLine();
        do{
            studentUI.displayAdding();
            //list all students
            listAllStudents();
            
            //get student detail
            MessageUI.zeroPrompt();
            
            //declare student current id
            int currentID = 0;
            if(studentList.isEmpty()){
                currentID = 2300000;
            }
            else{
                //get id of previous added student
                currentID = studentList.getEntry(studentList.size()).getId();
            }
            
            Student student = studentUI.getStudentDetail(++currentID);
            if(student == null){
                back = true;
                MessageUI.displayBackMessage();
                break;
            }
            studentList.add(student);
            studentDAO.saveToFile(studentList);
            
            MessageUI.printLine();
            MessageUI.displayAddSuccessful(student.getId());
            
        }while(!back);
    }
    
    //remove a student
    public void removeStudent(){
        boolean back =false;
        MessageUI.printLine();
        do{
            studentUI.displayRemoving();
            //get student index
            if(listAllStudents()){
                back = true;
                break;
            }
            
            
            //choose student
            MessageUI.zeroPrompt();
            int studentIndex = studentUI.chooseStudentToRemove();
            if(studentIndex == 0){
                back = true;
                MessageUI.printLine();
                break;
            }
            
            Student student = studentList.getEntry(studentIndex);
            
            studentList.remove(studentIndex);
            studentDAO.saveToFile(studentList);
            
            MessageUI.printLine();
            MessageUI.displayRemoveSuccessful(student.getId());
            
        }while(!back);
    }
    
    //amend student details
    public void amend(){
        boolean back =false;
        do{
            
            studentUI.displayAmending(0);
            //get student index
            if(listAllStudents()){
                back = true;
                break;
            }
            
            //choose student
            MessageUI.zeroPrompt();
            int studentIndex = studentUI.chooseStudentToAmend();
            if(studentIndex == 0){
                back = true;
                MessageUI.printLine();
                break;
            }
            
            Student student = studentList.getEntry(studentIndex);
            
            //declare initial value in the list
            int id = student.getId();
            String name = student.getName();
            String ic = student.getIc();
            String phoneNum = student.getPhoneNum();
            String email = student.getEmail();
            
            boolean end = false;
            do{
                MessageUI.printLine();
                studentUI.displayStudentDetail(student);
                MessageUI.zeroPrompt();
                int studentDetail = studentUI.chooseStudentDetail();
                
                switch (studentDetail) {
                    case 0:
                        end = true;
                        MessageUI.displayBackMessage();
                        break;
                        
                    //amend name
                    case 1:
                        name = studentUI.changeName(student);
                        student.setName(name);
                        break;

                    //amend ic
                    case 2:
                        ic = studentUI.changeIC(student);
                        student.setIc(ic);
                        break;

                    //amend phone number
                    case 3:
                        phoneNum = studentUI.changePhoneNum(student);
                        student.setPhoneNum(phoneNum);
                        break;

                    //amend email
                    case 4:
                        email = studentUI.changeEmail(student);
                        student.setEmail(email);
                        break;
                }
                
                
            }while(!end);
            //create a new student with the amended detail
            Student replaceStudent = new Student(id, name, phoneNum, ic, email);
            
            //replace it inside the list 
            studentList.replace(studentIndex, replaceStudent);
            studentDAO.saveToFile(studentList);
            
            MessageUI.displayChangeSuccessful();
            
        }while(!back);
    }
    
    //search students for registered courses
    public void search() {
    
        boolean back = false;
        
        ListInterface<Student> searchCourseList = new ArrayList<>();
        do {
            listAllCourses();
            
            MessageUI.zeroPrompt();
            int courseIndex = studentUI.chooseCourseToSearch();
            if(courseIndex == 0){
                back = true;
                MessageUI.printLine();
                break;
            }
            
            Course course = courseList.getEntry(courseIndex);
            
            studentUI.displaySearchingForCourse(course);
            MessageUI.printLine();
            
            int keyword = studentUI.inputKeyWords();
            
            Student student = new Student(keyword, "", 0, ' ', "", "", "");
            if(course.getStudentList().contains(student) != null){
                searchCourseList.add(course.getStudentList().contains(student));
                
            }
            MessageUI.printLine();
            
            studentUI.displayHeader();
            
            if(searchCourseList.isEmpty()){
                MessageUI.displayResultNotFound();
                MessageUI.enterPrompt();
            }
            else{
                searchCourseList.list();
                MessageUI.enterPrompt();
            }
            
            searchCourseList.clear();
            MessageUI.printLine();
                    
        }while(!back);
    }

    //add students to a few courses
    public void addStudentToCourses(){
        boolean back = false;
        do{
            studentUI.displayAddingCourse();
            //get student index
            if(listAllStudents()){
                back = true;
                break;
            }
            
            //get student 
            MessageUI.zeroPrompt();
            int studentIndex = studentUI.chooseStudentToAddCourse();
            
            Student student = studentList.getEntry(studentIndex);
            if(studentIndex == 0){
                back = true;
                MessageUI.printLine();
                break;
            }
            
            boolean end = false;
            
            MessageUI.printLine();
            do{
                studentUI.displayAddingCourseTo(student);
                //get course index
                if(listAllCourses()){
                    end = true;
                    break;
                }
                MessageUI.zeroPrompt();
                int courseIndex = studentUI.chooseCourseToAdd();
                if(courseIndex == 0){
                    end = true;
                    MessageUI.printLine();
                    break;
                }
                Course course = courseList.getEntry(courseIndex);
                
                course.getStudentList().add(student);
                courseDAO.saveToFile(courseList);
                
                studentList.getEntry(studentIndex).addCourse(course);
                studentDAO.saveToFile(studentList);
                
                MessageUI.printLine();
                MessageUI.displayAddSuccessful(course.getCourseCode());
                
            
            }while(!end);
            
        }while(!back);
    }
    
    //remove a student from a course
    public void removeStudentFromCourse(){
        boolean back = false;
        do{
            studentUI.displayRemovingCourse();
            //get student index
            if(listAllStudents()){
                back = true;
                MessageUI.printLine();
                break;
            }
            
            //get student 
            MessageUI.zeroPrompt();
            int studentIndex = studentUI.chooseStudentToRemoveCourse();
            
            Student student = studentList.getEntry(studentIndex);
            if(studentIndex == 0){
                back = true;
                MessageUI.printLine();
                break;
            }
            
            MessageUI.printLine();
            
            
            
            //MessageUI.printLine();
            boolean end = false;
            do{
                //get course index
                studentUI.displayRemovingCourseFrom(student);
                if (listCoursesFromStudent(studentIndex)) {
                    back = true;
                    break;   //get course index
                }
                MessageUI.zeroPrompt();
                int courseIndex = studentUI.chooseCourseToRemove();
                if(courseIndex == 0){
                    end = true;
                    break;
                }
                Course course = courseList.getEntry(courseIndex);
                
                MessageUI.displayRemoveSuccessful(student.removeCourse(courseIndex).getCourseCode());
                studentDAO.saveToFile(courseList);
            
            }while(!end);
            
        }while(!back);
    }
    
    public boolean dislpayFee(int studentIndex){
        Student student = studentList.getEntry(studentIndex);
        
        if(student.getCourseList().isEmpty()){
            courseUI.displayEmpty();
            MessageUI.enterPrompt();
        }
        else{
        }
        return student.getCourseList().isEmpty();
    }
    
    //calculate fee paid for registered course
    public void calculateFeePaid(){
        boolean back = false;
        double total = 0;
        
        do{
            listAllStudents();
            MessageUI.zeroPrompt();
            int Index = studentUI.chooseStudent();
            if(Index == 0){
                back = true;
                MessageUI.printLine();
            }
            else if(Index > 0 && Index <= studentList.size()) {
                Student student = studentList.getEntry(Index);
                
                MessageUI.displayPayment(studentList.getEntry(Index).getName());
                studentUI.displayFeesHeader();
                for(int i = 1; i < student.getCourseList().size()+1;i++){
                    String Code = student.getCourseList().getEntry(i).getCourseCode();
                    String Name = student.getCourseList().getEntry(i).getCourseName();
                    int Hour = student.getCourseList().getEntry(i).getCreditHour();
                    int Type = student.getCourseList().getEntry(i).getType();
                    double Fees = student.getCourseList().getEntry(i).getFee();
                    String type = "";
                    
                    switch (Type) {
                        case 1:
                            type = "MAIN";
                            break;

                        case 2:
                            type = "ELECTIVE";
                            break;

                        case 3:
                            type = "REPEAT";
                            break;

                        case 4:
                            type = "RESIT";
                            break;
                    }
                    
                    studentUI.displayFees(i,Code,Name,Hour, type,Fees);
                    total += Fees;
                }
                System.out.println(MessageUI.GetDivider('-', 100));
                System.out.printf("%83s%8s%.2f\n\n","Total","RM ", total);
                MessageUI.enterPrompt();
                back = true;
            }
            else {
                MessageUI.displayResultNotFound();
                MessageUI.enterPrompt();
                back = true;
            }
        }while(!back);  
    }
    
    public void filterCourse(String Type){
        
        FilterList.clear();
        for(int i = 1; i <= studentList.size(); i++){
            int count = 0;
            Student student = studentList.getEntry(i);
            for (int j = 1; j <= student.getCourseList().size();j++){
                int compare = student.getCourseList().getEntry(j).getType();
                String result = "";
                                        
                switch (compare) {
                    case 1:
                        result = "MAIN";
                        break;

                    case 2:
                        result = "ELECTIVE";
                        break;

                    case 3:
                        result = "REPEAT";
                        break;

                    case 4:
                        result = "RESIT";
                        break;
                }

                if(result == Type.toUpperCase()){
                    count++;
                }   
            }
            if(count > 0){
                FilterList.add(student);
            }
        }
          FilterList.list();
          FilterList.clear();
        
    }
    
    //Filters students for courses based on criteria
    public void filter(){
        boolean back = false;
        boolean end = false;
        int count = 0;
        
        int min= getMinAge();
        do {
            
            int choice = studentUI.chooseFilter();
            switch(choice){
                case 0:
                    back = true;
                    MessageUI.displayBackMessage();
                    break;
                
                //FIlter By course
                case 1:
                    do {
                        
                        int choose = studentUI.chooseFilterCase1();
                        switch(choice){
                            case 0:
                                end = true;
                                MessageUI.displayBackMessage();
                                break;
                            
                            //Resit
                            case 1:
                                filterCourse("RESIT");
                                MessageUI.enterPrompt();
                                end = true ;
                                break;
                                
                            //Repeat
                            case 2:
                                filterCourse("REPEAT");
                                MessageUI.enterPrompt();
                                end = true ;
                                break;
                                
                            case 3:
                                filterCourse("ELECTIVE");
                                MessageUI.enterPrompt();
                                end = true ;
                                break;
                                
                            default:
                                MessageUI.displayErrorMessage();
                        }
                        
                    }while(!end);
                    
                    break;
                
                //Filter By age
                //use FilterList
                case 2:
                    int compare = studentUI.chooseAge();
                    if (compare == 0){
                        back = true;
                        break;
                    } else if(compare > min){
                            FilterList.clear();
                            
                            for(int j = 1; j <= studentList.size(); j++){
                                Student student = studentList.getEntry(j);
                                int age = studentList.getEntry(j).getAge();
                                if(age <= compare){
                                    FilterList.add(student);
                                }
                            }
                            MessageUI.displayFilterResult(compare);
                            studentUI.displayHeader();
                            FilterList.list();
                            MessageUI.enterPrompt();
                            back = true;
                    }
                    else{
                        MessageUI.displayResultNotFound();
                        MessageUI.enterPrompt();
                        back = true;
                    }
                    break;
                    
                default:
                    MessageUI.displayErrorMessage();
                    
            //sort alphabetical        
            case 3:
                MessageUI.printLine();
                studentUI.displayAlphabeticalOrder();
                studentList.sort();
                listAllStudents();
                MessageUI.enterPrompt();
                break;        
            }
            
            
        }while(!back);
        
        
    }
    
    public int getMinAge(){
        
        int min = 100;
        for(int i = 1; i <= studentList.size();i++){
            if(studentList.getEntry(i).getAge() < min){
                min = studentList.getEntry(i).getAge();
            }
        }
        return min;
    }
    
    public String getMaxCourseGroup(){
        int max = 0;
        
        String courseID = null;
        String courseName = null;
        
        for(int i=1;i <= courseList.size();i++){
            Course course = courseList.getEntry(i);
            if(course.getStudentCount() > max){
                max = course.getStudentCount();
                courseID = course.getCourseCode();
                courseName = course.getCourseName();
            }
        }
        
        return "[" + max + "] " + courseID + " " + courseName + "\n";
        
    }
    
    public String getMinCourseGroup(){
        int min = 100;
        
        String courseID = null;
        String courseName = null;
        
        for(int i=1;i <= courseList.size();i++){
            Course course = courseList.getEntry(i);
            if(course.getStudentCount() < min){
                min = course.getStudentCount();
                courseID = course.getCourseCode();
                courseName = course.getCourseName();
            }
        }
        
        return "[" + min + "] " + courseID + " " + courseName + "\n";
        
    }
        
    public void calculateStudentFee() {
        int total = 0;
        double min = 10000;
        double max = 0;
        String maxName = "";
        String minName = "";
        
        studentUI.displayReportFeesHeader();
        for (int Index = 1; Index <= studentList.size(); Index++){
            Student student = studentList.getEntry(Index);
        

            String name = student.getName();
            char gender = student.getGender();
            int course = student.getCourseList().size();
            
            for(int i = 1; i <= student.getCourseList().size();i++){
                double fees = student.getCourseList().getEntry(i).getFee();
                total += fees ;
            }
                    
            studentUI.displayReportFees(Index,name,gender,course, total);
            
            if(min > total){
                min = total;
                minName = name;
            }
            if(max < total){
                max = total;
                maxName = name;
            }
            total = 0;
        }
        System.out.println(MessageUI.GetDivider('=', 150));
        System.out.println("\nThe Largest Amount of Fees :");
        System.out.println("Total RM "+ max + " by " + maxName);
        
        System.out.println("\nThe Least Amount of Fees :");
        System.out.println("Total RM "+ min + " by " + minName);  
    }
    
    public static void main(String[] args) {
        StudentMaintenance sm = new StudentMaintenance();
        sm.runStudentMaintenance();
        
    }
}
