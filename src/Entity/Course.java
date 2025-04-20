
package Entity;

import ADT.ArrayList;
import ADT.ListInterface;
import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Chow Xing Shao
 */
public class Course implements Serializable, Comparable<Course>{
    
    private String courseCode;  //BACS2024
    private String courseName;  //Data Structure and Algorithm
    private int creditHour;     
    private String type;    //main/repeat/resit    
    
    private double fee;
    
    final static double FEE_PER_CREDIT_HOUR = 259.00;
    
    //students that take this course
    private ListInterface<Student> studentList;
    private int studentCount;   //counter for students in this course
    
    //programme that offer this course
    private ListInterface<Programme> programmeList;
    private int programmeCount;     //counter for programme offered for this course
    
    //faculty that offer this course
    private ListInterface<Faculty> facultyList;
    private int facultyCount;   //counter for faculties offered
    
    //tutor that take this course
    private ListInterface<Tutor> tutorList;
    private int tutorCount;      //counter for number of tutors

    //tutorial groups that takes this course
    private ListInterface<TutorialGroup> tutorialGroupList;
    private int tutorialGroupCount;      //counter for number of tutorial groups
    
    public Course(String courseCode, String courseName, int type) {
        this.courseCode = courseCode.toUpperCase();
        this.courseName = courseName.toUpperCase();
        this.creditHour = Integer.parseInt(courseCode.substring(7));
        switch (type) {
            case 0:
                this.type = null;
                break;
                
            case 1:
                this.type = "MAIN";
                break;
                
            case 2:
                this.type = "ELECTIVE";
                break;

            case 3:
                this.type = "REPEAT";
                break;

            case 4:
                this.type = "RESIT";
                break;
        }
        this.fee = this.creditHour*FEE_PER_CREDIT_HOUR;
        this.studentList = new ArrayList<>();
        this.programmeList = new ArrayList<>();
        this.facultyList = new ArrayList<>();
        this.tutorList = new ArrayList<>();
                
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getCreditHour() {
        return creditHour;
    }

    public void setCreditHour(int creditHour) {
        this.creditHour = creditHour;
    }

    public int getType() {
        int type = 0;
        if(this.type.equals("MAIN")){
            type = 1;
        }
        else if(this.type.equals("ELECTIVE")){
            type = 2;
        }
        else if(this.type.equals("ERPEAT")){
            type=3;
        }
        else if(this.type.equals("RESIT")){
            type = 4;
        }
        
        return type;
    }

    public void setType(int type) {
        switch (type) {
            case 1:
                this.type = "MAIN";
                break;
                
            case 2:
                this.type = "ELECTIVE";
                break;

            case 3:
                this.type = "REPEAT";
                break;

            case 4:
                this.type = "RESIT";
                break;
        }
    }
    

    public ListInterface<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(ListInterface<Student> studentList) {
        this.studentList = studentList;
    }

    public int getStudentCount() {
        return studentCount;
    }

    public void setStudentCount(int studentCount) {
        this.studentCount = studentCount;
    }

    public ListInterface<Programme> getProgrammeList() {
        return programmeList;
    }

    public void setProgrammeList(ListInterface<Programme> ProgrammeList) {
        this.programmeList = ProgrammeList;
    }

    public int getProgrammeCount() {
        return programmeCount;
    }

    public void setProgrammeCount(int programmeCount) {
        this.programmeCount = programmeCount;
    }

    public ListInterface<Faculty> getFacultyList() {
        return facultyList;
    }

    public void setFacultyList(ListInterface<Faculty> facultyList) {
        this.facultyList = facultyList;
    }

    public int getFacultyCount() {
        return facultyCount;
    }

    public void setFacultyCount(int facultyCount) {
        this.facultyCount = facultyCount;
    }

    public ListInterface<Tutor> getTutorList() {
        return tutorList;
    }

    public void setTutorList(ListInterface<Tutor> tutorList) {
        this.tutorList = tutorList;
    }

    public int getTutorCount() {
        return tutorCount;
    }

    public void setTutorCount(int tutorCount) {
        this.tutorCount = tutorCount;
    }

    public double getFee() {
        return fee;
    }

    public ListInterface<TutorialGroup> getTutorialGroupList() {
        return tutorialGroupList;
    }

    public void setTutorialGroupList(ListInterface<TutorialGroup> tutorialGroupList) {
        this.tutorialGroupList = tutorialGroupList;
    }

    public int getTutorialGroupCount() {
        return tutorialGroupCount;
    }

    public void setTutorialGroupCount(int tutorialGroupCount) {
        this.tutorialGroupCount = tutorialGroupCount;
    }

    @Override
    public int compareTo(Course o) {
        return this.courseName.compareTo(o.courseName); 
    }
    
    //--------------------------------------------------
    
    //add programme to course
    public void addProgramme(Programme programme){
        programmeList.add(programme);
        programmeCount++;
        if(facultyList.contains(programme.getFaculty()) == null){
            facultyList.add(programme.getFaculty());
            facultyCount++;
        }
        
    }
    
    //remove programme from course
    public Programme removeProgramme(int programmeIndex){
        Programme removedProgramme = programmeList.remove(programmeIndex);
        programmeCount--;
        //facultyList.remove(programme.getFaculty());
        //facultyCount++;
        return removedProgramme;
    }
    
    //add faculty to course
    public void addFaculty(Faculty faculty){
        facultyList.add(faculty);
        facultyCount++;
    }
    
    //remove faculty from course
    public Tutor removeFaculty(int facultyIndex){
        facultyCount--;
        return tutorList.remove(facultyIndex);
    }
    
    //add student to course
    public void addStudent(Student student){
        studentList.add(student);
        studentCount++;
    }
    
    //remove student from course
    public void removeStudent(int studentIndex){
        studentList.remove(studentIndex);
        studentCount--;
    }
    
    //add tutor to course
    public void addTutor(Tutor tutor){
        tutorList.add(tutor);
        tutorCount++;
    }
    
    //remove tutor from course
    public Tutor removeTutor(int tutorIndex){
        tutorCount--;
        return tutorList.remove(tutorIndex);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Course other = (Course) obj;
        return Objects.equals(this.courseCode, other.courseCode);
    }
    
    @Override
    public String toString() {
        String str = facultyCount + "/" + programmeCount;
        
        return String.format("%-2s%-15s%-40s%-15d%-10s%13s","", courseCode, courseName, creditHour, type, str);
    }
}
