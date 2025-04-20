
package Entity;

import ADT.ArrayList;
import ADT.ListInterface;
import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Soh Kee Yhang
 */
public class Tutor extends Person implements Serializable, Comparable<Person>{
    
    private String id;
    private String type;
    
    //tutorial group list for this tutor
    private ListInterface<TutorialGroup> tutorialGroupList;
    private int tutorialGroupCount;     //counter for tutorial group for this tutor
    
    //course list for this tutor
    private ListInterface<Course> courseList;
    private int courseCount;     //counter for courses for this tutor
    

    public Tutor(String name, String phoneNum, String ic, String email, int type, int id) {
        super(name, phoneNum, ic, email);
        switch (type) {
            case 1:
                this.type = "TUTORIAL";
                break;
            case 2:
                this.type = "PRACTICAL";
                break;
            case 3:
                this.type = "LECTURE";
                break;
        }
        this.id = this.type.charAt(0) + String.valueOf(id);
        
        this.tutorialGroupList = new ArrayList<>();
        this.courseList = new ArrayList<>();
    }
    
    public Tutor(String name, int age, char gender, String phoneNum, String ic, String email, int type, String id) {
        super(name, age, gender, phoneNum, ic, email);
        switch (type) {
            case 0:
                this.type = null;
                break;
            
            case 1:
                this.type = "TUTORIAL";
                break;
            case 2:
                this.type = "PRACTICAL";
                break;
            case 3:
                this.type = "LECTURE";
                break;
        }
        this.id = id;
        
        this.tutorialGroupList = new ArrayList<>();
        this.courseList = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public ListInterface<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(ListInterface<Course> courseList) {
        this.courseList = courseList;
    }

    public int getCourseCount() {
        return courseCount;
    }

    public void setCourseCount(int courseCount) {
        this.courseCount = courseCount;
    }
    
    public void addTutorialGroup(TutorialGroup tutorialGroup){
        tutorialGroupList.add(tutorialGroup);
        tutorialGroupCount++;
    }
    
    public TutorialGroup addTutorialGroup(int tutorialGroupIndex){
        tutorialGroupCount--;
        return tutorialGroupList.remove(tutorialGroupIndex);
    }
    
    public void addCourse(Course course){
        courseList.add(course);
        courseCount++;
    }
    
    public Course removeCourse(int courseIndex){
        courseCount--;
        return courseList.remove(courseIndex);
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final Tutor other = (Tutor) obj;
        return Objects.equals(this.id, other.id);
    }
    
    @Override
    public int compareTo(Person other) {
        // Compare tutors based on their name
        return this.getName().compareTo(other.getName());
    }

    @Override
    public String toString() {
        return String.format("%-106s%-10s%-10s", super.toString(), id, type);
    }
    
    
    
    
    
    
    
}
