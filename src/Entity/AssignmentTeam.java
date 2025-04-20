
package Entity;

import ADT.ArrayList;
import ADT.ListInterface;
import java.io.Serializable;

/**
 *
 * @author Varsyathini
 */
public class AssignmentTeam implements Serializable, Comparable<AssignmentTeam>{
    
    private String teamName;
    private int numOfStudents, maxStudents;
    
    //students inside the assignment team
    private ListInterface<Student> students;

    public AssignmentTeam(int maxStudents, String teamName) {
        this.maxStudents = maxStudents;
        this.teamName = teamName;
        this.students = new ArrayList<>();
    }

    public String getTeamName() {
        return teamName;
    }

    public int getNumOfStudents() {
        return numOfStudents;
    }

    public int getMaxStudents() {
        return maxStudents;
    }

    public ListInterface<Student> getStudents() {
        return students;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public void setNumOfStudents(int numOfStudents) {
        this.numOfStudents = numOfStudents;
    }

    public void setMaxStudents(int maxStudents) {
        this.maxStudents = maxStudents;
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }
    
    public void addStudent(Student student){
        numOfStudents++;
        students.add(student);
    }
    
    public Student removeStudent(int studentIndex){
        numOfStudents--;
        return students.remove(studentIndex);
    }

    @Override
    public int compareTo(AssignmentTeam o) {
        return this.teamName.compareTo(o.teamName);
    }
    
    

    @Override
    public String toString() {
        String teamName = String.format("%-2s%-25s","", this.teamName);
        String number = numOfStudents + "/" + maxStudents;
        return teamName + number;
    }
    
}
