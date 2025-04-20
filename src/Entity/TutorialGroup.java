
package Entity;
import ADT.ArrayList;
import ADT.ListInterface;
import java.io.Serializable;

/**
 *
 * @author Teo Hua Ying
 */
public class TutorialGroup implements Serializable{
    
    private String programmeCode;       //indicate which programme 
    private String tutorialGroup;      //group 1, group 2
    private int maxStudent;
    
    //students inside the tutorial group within a programme
    private ListInterface<Student> studentList;
    private int studentCount;   //counter for students in this tutorial group
    
    //assignment team inside the tutorial group
    private ListInterface<AssignmentTeam> assignmentTeamList;
    private int teamCount;   //counter for assignment team in this tutorial group

    public TutorialGroup(String programmeCode, int groupCount, int maxStudent) {
        this.programmeCode = programmeCode;
        this.tutorialGroup = programmeCode + " Group " + (groupCount+1);
        this.maxStudent = maxStudent;
        
        this.studentList = new ArrayList<>();
        this.assignmentTeamList = new ArrayList<>();
    }
    
    
    public String getTutorialGroup() {
        return this.tutorialGroup;
    }

    public void setTutorialGroup(String tutorialGroup) {
        this.tutorialGroup = tutorialGroup;
    }

    public int getMaxStudent() {
        return maxStudent;
    }

    public void setMaxStudent(int maxStudent) {
        this.maxStudent = maxStudent;
    }

    public ListInterface<Student> getStudentList() {
        return studentList;
    }

    public int getStudentCount() {
        return studentList.size();
    }

    public ListInterface<AssignmentTeam> getAssignmentTeamList() {
        return assignmentTeamList;
    }

    public int getTeamCount() {
        return assignmentTeamList.size();
    }

    public String getProgrammeCode() {
        return programmeCode;
    }

    public void setProgrammeCode(String programmeCode) {
        this.programmeCode = programmeCode;
    }
    
    
    
    //---------------------
    
    public void addAssignmentTeam(AssignmentTeam assignmentTeam){
        assignmentTeamList.add(assignmentTeam);
        teamCount++;
    }
    
    public AssignmentTeam removeAssignmentTeam(int assignmentTeamIndex){
        teamCount++;
        return assignmentTeamList.remove(assignmentTeamIndex);
    }
    
    public void addStudent(Student student){
        studentList.add(student);
        studentCount++;
    }
    
    public Student removeStudent(int studentIndex){
        studentCount--;
        return studentList.remove(studentIndex);
    }

    @Override
    public String toString() {
        String group = String.format("%-2s%-35s", "",tutorialGroup);
        String number = studentCount + "/" + maxStudent;
        return group + number;
    }
}
