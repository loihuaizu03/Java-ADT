package control;
import ADT.ArrayList;
import ADT.ListInterface;
import Entity.TutorialGroup;
import Entity.Programme;
import Entity.Student;
import boundary.AssignmentTeamUI;
import boundary.ProgrammeUI;
import boundary.StudentUI;
import utility.MessageUI;
import boundary.TutorialGroupUI;
import dao.CommonDAO;

/**
 *
 * @author Teo Hua Ying
 */
public class TutorialGroupMaintenance {
    
    //list---------------------
    private ListInterface<Programme> programmeList = new ArrayList<>();
    private ListInterface<Student> studentList = new ArrayList<>();
    
    //tutorial group list for all programme
    private ListInterface<TutorialGroup> tutorialGroupList = new ArrayList<>();
    
    //dao-----------------------
    private CommonDAO programmeDAO = new CommonDAO("programme.txt");
    private CommonDAO studentDAO = new CommonDAO("student.txt");
    private CommonDAO tutorialGroupDAO = new CommonDAO("tutorialGroup.txt");
    
    //object for UI--------------
    TutorialGroupUI tutorialGroupUI = new TutorialGroupUI();
    AssignmentTeamUI assignmentTeamUI = new AssignmentTeamUI();
    ProgrammeUI programmeUI = new ProgrammeUI();
    StudentUI studentUI = new StudentUI();
            

    public TutorialGroupMaintenance() {
        this.programmeList = programmeDAO.retrieveFromFile();
        this.studentList = studentDAO.retrieveFromFile();
        this.tutorialGroupList = tutorialGroupDAO.retrieveFromFile();
    }
    
    public void runTutorialGroupMaintenance(){
        boolean end = false;
        MessageUI.printLine();
        do {      
            int choice = tutorialGroupUI.getMenuChoice();
            switch (choice) {
                case 0:
                    end = true;
                    MessageUI.displayBackMessage();
                    break;
                    
                case 1:
                    MessageUI.printLine();
                    addTutorialGroup();
                    break;

                case 2:
                    MessageUI.printLine();
                    removeTutorialGroup();
                    break;
                  
                case 3:
                    MessageUI.printLine();
                    if(!listAllTutorialGroup())
                        MessageUI.enterPrompt();
                    MessageUI.printLine();
                    break;
                    
                case 4:
                    MessageUI.printLine();
                    addStudent();
                    
                    break;

                case 5  :    
                    MessageUI.printLine();
                    removeStudent();
                    break;
                    
                case 6:
                    MessageUI.printLine();
                    //change tutorial group
                    changeTutorialGroup();
                    break;
                    
                case 7:  
                    MessageUI.printLine();
                    if(listAllTutorialGroup()){
                        break;
                    }
                    MessageUI.zeroPrompt();
                    int groupIndex = tutorialGroupUI.chooseTutorialGroup();
                    MessageUI.printLine();
                    listStudentsFromTutorialGroup(groupIndex);
                    MessageUI.enterPrompt();
                    MessageUI.printLine();
                   
                    break;
                    
                case 8:
                    MessageUI.printLine();
                    //merge tutorial group
                    mergeTutorialGroup();
                    
                    break;
                    
                case 9:
                    //summary report
                    GenerateReports report = new GenerateReports();
                    MessageUI.printLine();
                    report.tutGroupReports1();
                    MessageUI.enterPrompt();
                    break;
                    
                default:
                    MessageUI.displayErrorMessage();
            }
            
        } while (!end);
    }
    
    //list all programmes
    public boolean listAllProgramme() {
        programmeUI.displayheader();
        if(programmeList.isEmpty()){
            programmeUI.displayEmpty();
            MessageUI.enterPrompt();
        }
        programmeList.list();
        return programmeList.isEmpty();
    }
    
    //list all tutorial group for all programme
    public boolean listAllTutorialGroup(){
        tutorialGroupUI.displayHeader();
        if(tutorialGroupList.isEmpty()){
            tutorialGroupUI.displayEmpty();
            MessageUI.enterPrompt();
        }
        else{
            tutorialGroupList.list();
        }
        return tutorialGroupList.isEmpty();
    }
    
    //list all students
    public boolean listAllStudents(){
        studentUI.displayHeader();
        if(studentList.isEmpty()){
            studentUI.displayEmpty();
            MessageUI.enterPrompt();
        }
        else{
            studentList.list();
        }
        return studentList.isEmpty();
    }
    
    //list students from tutorial group
    public boolean listStudentsFromTutorialGroup(int tutorialGroupIndex){
        
        TutorialGroup tutorialGroup = tutorialGroupList.getEntry(tutorialGroupIndex);
        studentUI.displayHeader();
        
        if(tutorialGroup.getStudentList().isEmpty()){
            studentUI.displayEmpty();
        }
        else{
            tutorialGroup.getStudentList().list();
        }
        return tutorialGroup.getStudentList().isEmpty();
        
    }
    
    //add a tutorial group to a programme
    public void addTutorialGroup(){
        boolean back = false;
        do { 
            if(listAllProgramme()){
                back = true;
                break;
            }

            //get programme
            MessageUI.zeroPrompt();
            int programmeIndex = tutorialGroupUI.chooseProgrammeToAdd();
            if(programmeIndex == 0){
                back = true;
                MessageUI.printLine();
                MessageUI.displayBackMessage();
                break;
            }
            
            MessageUI.printLine();
            //declare programme that is suppose to add
            Programme programme = programmeList.getEntry(programmeIndex);
            tutorialGroupUI.displayAddingTo(programme);
            
            //get maximum students per tutorial group
            int maxStudent = tutorialGroupUI.inputMaxStudent();

            //declare tutorial group count
            int groupCount = programme.getTutorialgroupCount();
            
            //declare tutorial group to add
            TutorialGroup tutorialGroup = new TutorialGroup(programme.getProgrammeCode(), groupCount, maxStudent);
            
            //add to general tutorial group list
            tutorialGroupList.add(tutorialGroup);
            tutorialGroupDAO.saveToFile(tutorialGroupList);     //save to file
            
            //add inside programme list
            programme.addTutorialGroup(tutorialGroup);
            programmeDAO.saveToFile(programmeList);     //save to file
            
            MessageUI.printLine();
            MessageUI.displayAddSuccessful(tutorialGroup.getTutorialGroup());
            
        } while (!back);
    }
    
    //remove tutorial group from a programme
    //get which programme, and get the tutorial group to remove
    public void removeTutorialGroup(){
        boolean back = false;
        do {      
            if(listAllProgramme()){
                back = true;
                break;
            }

            //get programme
            MessageUI.zeroPrompt();
            int programmeIndex = tutorialGroupUI.chooseProgrammeToRemove();
            if(programmeIndex == 0){
                back = true;
                MessageUI.printLine();
                MessageUI.displayBackMessage();
                break;
            }
            MessageUI.printLine();
            Programme programme = programmeList.getEntry(programmeIndex);

            //list available tutorial group from the programme
            tutorialGroupUI.displayRemovingFrom(programme);
            tutorialGroupUI.displayHeader();
            if(programmeList.getEntry(programmeIndex).getTutorialGroupList().isEmpty()){
                back = true;
                tutorialGroupUI.displayEmpty();
                MessageUI.enterPrompt();
                break;
            }
            
            programmeList.getEntry(programmeIndex).getTutorialGroupList().list();
            
            //get which tutorial group to remove
            int tutorialGroupIndex = tutorialGroupUI.chooseGroupToBeRemove();

            TutorialGroup tutorialgroup = tutorialGroupList.getEntry(tutorialGroupIndex);
            
            for(int i=1;i<=tutorialGroupList.size();i++){
                if(tutorialgroup.equals(tutorialGroupList.getEntry(i))){
                    tutorialGroupList.remove(i);
                    break;
                }
            }
            
            //remove tutorial group
            programmeList.getEntry(programmeIndex).removeTutorialGroup(tutorialGroupIndex);
            programmeDAO.saveToFile(programmeList);
            
            MessageUI.printLine();
            MessageUI.displayRemoveSuccessful("Group " + tutorialGroupIndex);
            
        } while (!back);
    }
    
    //add students to a tutorial group
    public void addStudent(){
        boolean back = false;
        do {
            if(listAllTutorialGroup()){
                back = true;
                break;
            }
            
            //choose tutorial group
            MessageUI.zeroPrompt();
            int tutorialGroupIndex = tutorialGroupUI.chooseGroupToAdd();
            if(tutorialGroupIndex == 0){
                back = true;
                MessageUI.printLine();
                MessageUI.displayBackMessage();
                break;
            }
            
            TutorialGroup tutorialGroup = tutorialGroupList.getEntry(tutorialGroupIndex);
            
            //get programme
            String programmeCode = tutorialGroup.getProgrammeCode();
            
            int programmeIndex = 0;
            for(int i=1;i<=programmeList.size();i++){
                Programme programme = programmeList.getEntry(i);
                if(programme.getProgrammeCode().equals(programmeCode)){
                    programmeIndex = i;
                    break;
                }
            }
            Programme programme = programmeList.getEntry(programmeIndex);
            
            int index = 0;
            for(int i=1;i<=programme.getTutorialGroupList().size();i++){
                TutorialGroup tutorialgroup = programme.getTutorialGroupList().getEntry(i);
                if(tutorialgroup.getTutorialGroup().equals(tutorialGroup.getTutorialGroup())){
                    index = i;
                    break;
                }
            }
            
            TutorialGroup tutorialgroup = programme.getTutorialGroupList().getEntry(index);
            
            
            MessageUI.printLine();
            
            boolean end = false;
            do{
                //display adding to "programme"
                tutorialGroupUI.displayAddingTo(tutorialGroup);

                if(listAllStudents()){
                    end = true;
                    break;
                }

                //choose student
                MessageUI.zeroPrompt();
                int studentIndex = tutorialGroupUI.chooseAddStudent();
                if(studentIndex == 0){
                    end = true;
                    MessageUI.displayBackMessage();
                    break;
                }
                
                Student student = studentList.getEntry(studentIndex);
                
                //add to overall tutorial group list
                tutorialGroup.addStudent(student);
                tutorialGroupDAO.saveToFile(tutorialGroupList);

                //add to tutorial group list under programme
                tutorialgroup.addStudent(student);
                programmeDAO.saveToFile(programmeList);
                
                MessageUI.printLine();
                MessageUI.displayAddSuccessful(student.getId());
                
            }while(!end);
        }while(!back);
    }
    
    //remove students to a tutorial group
    public void removeStudent(){
        boolean back = false;
        do {
            if(listAllTutorialGroup()){
                back = true;
                break;
            }
            
            //choose tutorial group
            MessageUI.zeroPrompt();
            int tutorialGroupIndex = tutorialGroupUI.chooseGroupToRemove();
            if(tutorialGroupIndex == 0){
                back = true;
                MessageUI.printLine();
                break;
            }
            
            TutorialGroup tutorialGroup = tutorialGroupList.getEntry(tutorialGroupIndex);
            
            //get programme
            String programmeCode = tutorialGroup.getProgrammeCode();
            
            int programmeIndex = 0;
            for(int i=1;i<=programmeList.size();i++){
                Programme programme = programmeList.getEntry(i);
                if(programme.getProgrammeCode().equals(programmeCode)){
                    programmeIndex = i;
                    break;
                }
            }
            Programme programme = programmeList.getEntry(programmeIndex);
            
            int index = 0;
            for(int i=1;i<=programme.getTutorialGroupList().size();i++){
                TutorialGroup tutorialgroup = programme.getTutorialGroupList().getEntry(i);
                if(tutorialgroup.getTutorialGroup().equals(tutorialGroup.getTutorialGroup())){
                    index = i;
                    break;
                }
            }
            
            TutorialGroup tutorialgroup = programme.getTutorialGroupList().getEntry(index);
            
            
            MessageUI.printLine();
            
            boolean end = false;
            do{
            
                //display removing from "programme"
                tutorialGroupUI.displayRemovingFrom(tutorialGroup);

                if(listStudentsFromTutorialGroup(tutorialGroupIndex)){
                    end = true;
                    MessageUI.enterPrompt();
                    break;
                }

                //choose student
                MessageUI.zeroPrompt();
                int studentIndex = tutorialGroupUI.chooseRemoveStudent();
                if(studentIndex == 0){
                    end = true;
                    MessageUI.displayBackMessage();
                    break;
                }
                
                Student student = studentList.getEntry(studentIndex);
                tutorialGroup.removeStudent(studentIndex);
                tutorialGroupDAO.saveToFile(tutorialGroupList);
                
                tutorialgroup.removeStudent(studentIndex);
                programmeDAO.saveToFile(programmeList);

                MessageUI.printLine();
                MessageUI.displayRemoveSuccessful(student.getId());

            }while(!end);   
        }while(!back);
    }
    
    //change the tutorial group for a student
    public void changeTutorialGroup(){
        boolean back = false;
        do {
            
            tutorialGroupUI.displayChangeGroup();
            
            if(listAllTutorialGroup()){
                back = true;
                break;
            }
            
            //choose tutorial group
            MessageUI.zeroPrompt();
            int tutorialGroupIndex = tutorialGroupUI.chooseGroupToTransferred();
            if(tutorialGroupIndex == 0){
                back = true;
                MessageUI.printLine();
                MessageUI.displayBackMessage();
                break;
            }
            
            TutorialGroup tutorialGroup = tutorialGroupList.getEntry(tutorialGroupIndex);
            
            //get programme
            String programmeCode = tutorialGroup.getProgrammeCode();
            
            int programmeIndex = 0;
            for(int i=1;i<=programmeList.size();i++){
                Programme programme = programmeList.getEntry(i);
                if(programme.getProgrammeCode().equals(programmeCode)){
                    programmeIndex = i;
                    break;
                }
            }
            Programme programme = programmeList.getEntry(programmeIndex);
            
            int index = 0;
            for(int i=1;i<=programme.getTutorialGroupList().size();i++){
                TutorialGroup tutorialgroup = programme.getTutorialGroupList().getEntry(i);
                if(tutorialgroup.getTutorialGroup().equals(tutorialGroup.getTutorialGroup())){
                    index = i;
                    break;
                }
            }
            
            TutorialGroup tutorialgroup = programme.getTutorialGroupList().getEntry(index);
            

            MessageUI.printLine();
            
            tutorialGroupUI.displayChangeGroup();
            
            if(listStudentsFromTutorialGroup(tutorialGroupIndex)){
                back = true;
                MessageUI.enterPrompt();
                break;
            }
            
            //choose student
            int studentIndex = tutorialGroupUI.chooseStudentToChange();
            
            Student student = studentList.getEntry(studentIndex);
            
            //remove student from overall tuto group
            tutorialGroup.removeStudent(studentIndex);
            //remove group under programme
            tutorialgroup.removeStudent(studentIndex);
            
            MessageUI.printLine();
            tutorialGroupUI.displayChanging(student);
            
            //choose new tutorial group to add to
            listAllTutorialGroup();
            int newGroupIndex = tutorialGroupUI.chooseGroupToChange();
            
            TutorialGroup newTutorialGroup = tutorialGroupList.getEntry(newGroupIndex);
            
            //get programme
            String code = tutorialGroup.getProgrammeCode();
            
            int Pindex = 0;
            for(int i=1;i<=programmeList.size();i++){
                Programme newprogramme = programmeList.getEntry(i);
                if(programme.getProgrammeCode().equals(code)){
                    Pindex = i;
                    break;
                }
            }
            Programme newprogramme = programmeList.getEntry(Pindex);
            
            int Gindex = 0;
            for(int i=1;i<=programme.getTutorialGroupList().size();i++){
                TutorialGroup group = newprogramme.getTutorialGroupList().getEntry(i);
                if(group.getTutorialGroup().equals(newTutorialGroup.getTutorialGroup())){
                    Gindex = i;
                    break;
                }
            }
            
            TutorialGroup Newtutorialgroup = newprogramme.getTutorialGroupList().getEntry(Gindex);
            
            //add student to new tutorial group
            newTutorialGroup.addStudent(student);
            
            Newtutorialgroup.addStudent(student);
            
            
            MessageUI.printLine();
            MessageUI.displayChangeSuccessful();
            
        }while(!back);
    }
    
    //merge tutorial group
    public void mergeTutorialGroup(){
        boolean back = false;
        do {
            tutorialGroupUI.displayMergeGroup();
            
            if(listAllProgramme()){
                back = true;
                break;
            }

            //get programme
            MessageUI.zeroPrompt();
            int programmeIndex = tutorialGroupUI.chooseProgrammeToRemove();
            if(programmeIndex == 0){
                back = true;
                MessageUI.printLine();
                MessageUI.displayBackMessage();
                break;
            }
            MessageUI.printLine();
            Programme programme = programmeList.getEntry(programmeIndex);
            
            //list available tutorial group from the programme
            tutorialGroupUI.displayMergeGroup();
            tutorialGroupUI.displayHeader();
            if(programmeList.getEntry(programmeIndex).getTutorialGroupList().isEmpty()){
                back = true;
                tutorialGroupUI.displayEmpty();
                MessageUI.enterPrompt();
                break;
            }
            
            programmeList.getEntry(programmeIndex).getTutorialGroupList().list();
            
            //get which tutorial group to remove
            MessageUI.zeroPrompt();
            int tutorialGroupIndex = tutorialGroupUI.chooseGroupToMerge();

            if(tutorialGroupIndex == 0){
                back = true;
                MessageUI.printLine();
                MessageUI.displayBackMessage();
                break;
            }
            
            TutorialGroup tutorialgroup = programme.getTutorialGroupList().getEntry(tutorialGroupIndex);

            TutorialGroup lastTutorialGroup = programme.getTutorialGroupList().getEntry(programme.getTutorialGroupList().size());
           
            for(int i=1;i<=lastTutorialGroup.getStudentList().size();i++){
                Student student = lastTutorialGroup.getStudentList().getEntry(i);
                tutorialgroup.addStudent(student);
            }
            
            //remove last group in the list
            programme.removeTutorialGroup(programme.getTutorialGroupList().size());
            programmeDAO.saveToFile(programmeList);
                    
            int movedstudentindex = 0;
            int movestudentToindex = 0;
            for(int i=1;i<=tutorialGroupList.size();i++){
                
                //find last tutorial group
                if(lastTutorialGroup.getTutorialGroup().equals(tutorialGroupList.getEntry(i).getTutorialGroup())){
                    
                    //find students in last group
                    movedstudentindex = i;
                }
                
                if(tutorialgroup.getTutorialGroup().equals(tutorialGroupList.getEntry(i).getTutorialGroup())){
                    movestudentToindex = i;
                }
                
            }
            TutorialGroup removedgroup = tutorialGroupList.getEntry(movedstudentindex);
            
            for(int i=1 ;i<=removedgroup.getStudentList().size();i++){
                Student student = removedgroup.getStudentList().getEntry(i);
                
                tutorialGroupList.getEntry(movestudentToindex).addStudent(student);
            }
            
            tutorialGroupList.remove(movedstudentindex);
            tutorialGroupDAO.saveToFile(tutorialGroupList);
            
            programme.removeTutorialGroup(tutorialGroupIndex);
            programmeDAO.saveToFile(programmeList);
            
            MessageUI.printLine();
            MessageUI.displayMergeSuccessful();
            
        }while(!back);
    }
    
    
    public String getMostStudentInTutGroup() {
        int max = 0;

        String tutorialGroup = null;
        
        for (int i=1; i<=tutorialGroupList.size(); i++) {
            TutorialGroup tutoGroup = tutorialGroupList.getEntry(i);
            
            if (tutoGroup != null) {
                int studentCount = tutoGroup.getStudentCount();
                if (max <= studentCount) {
                    max = studentCount;
                    tutorialGroup = tutoGroup.getTutorialGroup();
                }
            }
        }
        return "[" + max + "] " + tutorialGroup;
    }

    public String getLeastStudentInTutGroup() {
        int min = 100;

        String tutorialGroup = null;
        
        for (int i = 1; i < tutorialGroupList.size() + 1; i++) {
            TutorialGroup tutoGroup = tutorialGroupList.getEntry(i);
            
            if (tutoGroup != null) {
                int studentCount = tutoGroup.getStudentCount();
                if (min >= studentCount) {
                    min = studentCount;
                    tutorialGroup = tutoGroup.getTutorialGroup();
                }
            }
        }
        return "[" + min + "] " + tutorialGroup;
    }

    public double getGenderPercentage(TutorialGroup tGroup) {
        int male = 0;
        for (int i = 1; i < tGroup.getStudentList().size() + 1; i++) {

            char gender = tGroup.getStudentList().getEntry(i).getGender();
            if (gender == 'M') {
                male++;
            }
            return ((double) male / tGroup.getStudentList().size()) * 100;
        }
        return -1;
    }
    
    public static void main(String[] args) {
        TutorialGroupMaintenance tm = new TutorialGroupMaintenance();
        tm.runTutorialGroupMaintenance();
    }
}
