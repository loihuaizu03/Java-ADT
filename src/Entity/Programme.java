
package Entity;
import ADT.ArrayList;
import ADT.ListInterface;
import java.io.Serializable;

/**
 *
 * @author Chow Xing Shao
 */
public class Programme implements Serializable, Comparable<Programme>{
    
    private String programmeCode;   //RSW
    private String programmeName;   //Bachelor of Software Engineering
    private Faculty faculty;    //which faculty this programme belongs to
    
    //courses in this programme
    private ListInterface<Course> courseList;
    private int courseCount;     //counter for courses in this programme
    
    //tutorial group in this programme
    private ListInterface<TutorialGroup> tutorialGroupList;
    private int tutorialGroupCount;     //counter for tutorial group in this programme

    public Programme(String programmeCode, String programmeName, Faculty faculty) {
        
        this.programmeCode = programmeCode.toUpperCase();
        this.programmeName = programmeName.toUpperCase();
        this.faculty = faculty;
        
        this.tutorialGroupCount = 0;
        this.courseCount = 0;
        this.courseList = new ArrayList<>();
        this.tutorialGroupList = new ArrayList<>();
    }

    public String getProgrammeCode() {
        return programmeCode;
    }

    public void setProgrammeCode(String programmeCode) {
        this.programmeCode = programmeCode;
    }

    public String getProgrammeName() {
        return programmeName;
    }

    public void setProgrammeName(String programmeName) {
        this.programmeName = programmeName;
    }

    public ListInterface<Course> getCourseList() {
        return courseList;
    }

    public int getCourseCount() {
        return courseCount;
    }

    public void setCourseCount(int courseCount) {
        this.courseCount = courseCount;
    }

    public ListInterface<TutorialGroup> getTutorialGroupList() {
        return tutorialGroupList;
    }

    public int getTutorialgroupCount() {
        return tutorialGroupCount;
    }

    public void setTutorialgroupCount(int tutorialGroupCount) {
        this.tutorialGroupCount = tutorialGroupCount;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }
    
    
    //methods for tutorial group list
    
    //add tutorial group
    public void addTutorialGroup(TutorialGroup tutorialGroup){
        tutorialGroupList.add(tutorialGroup);
        tutorialGroupCount++;
    }
    
    //remove tutorial group
    public TutorialGroup removeTutorialGroup(int tutorialGroup){
        tutorialGroupCount--;
        return tutorialGroupList.remove(tutorialGroup);
    }
    
    //methods for course list

    //add tutorial group
    public void addCourse(Course course){
        courseList.add(course);
        courseCount++;
    }
    
    //remove tutorial group
    public Course removeCourse(int course){
        courseCount--;
        return courseList.remove(course);
    }

    @Override
    public int compareTo(Programme o) {
        return this.programmeCode.compareTo(o.programmeCode);
    }
    
    @Override
    public String toString() {
        return String.format("%-2s%-20s%-20s","", programmeCode, programmeName);
    }
}
