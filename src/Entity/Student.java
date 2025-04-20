
package Entity;

import ADT.ArrayList;
import ADT.ListInterface;
import java.io.Serializable;

/**
 *
 * @author Loi Huai Zu
 */
public class Student extends Person implements Serializable, Comparable<Person>{
    
    private int id;
    //private String programmeCode;    //which programme
    private ListInterface<Course> courseList;   //courses this student is registered for

    public Student(String name, String phoneNum, String ic, String email, int id) {
        super(name, phoneNum, ic, email);
        this.id = id;
        this.courseList = new ArrayList<>();
    }
    
    //constructor for amend student details
    public Student(int id, String name, String phoneNum, String ic, String email){
        super(name, phoneNum, ic, email);
        this.id = id;
    }

    public Student(int id, String name, int age, char gender, String phoneNum, String ic, String email) {
        super(name, age, gender, phoneNum, ic, email);
        this.id = id;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ListInterface<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(ListInterface<Course> courseList) {
        this.courseList = courseList;
    }
    
    public void addCourse(Course course){
        courseList.add(course);
    }
    
    public Course removeCourse(int courseIndex){
        return courseList.remove(courseIndex);
    }
    
    @Override
    public int compareTo(Person other) {
        // Compare tutors based on their ID
        return this.getName().compareTo(other.getName());
    }

    @Override
    public int hashCode() {
        int hash = 3;
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
        final Student other = (Student) obj;
        return this.id == other.id;
    }

    @Override
    public String toString() {
        return String.format("%-106s%-15s",super.toString(), id);
    }
    
}
