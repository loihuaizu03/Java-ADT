
package control;

import ADT.*;
import Entity.Faculty;
import Entity.Programme;
import boundary.ProgrammeUI;
import dao.CommonDAO;
import utility.MessageUI;

/**
 *
 * @author Chow Xing Shao
 */
public class ProgrammeMaintenance {
    
    //programme list
    private ListInterface<Programme> programmeList = new ArrayList<>();
    
    private ListInterface<Faculty> facultyList = new ArrayList<>();
    
    //dao
    private CommonDAO programmeDAO = new CommonDAO("programme.txt");
    private CommonDAO facultyDAO = new CommonDAO("faculty.txt");

    //object for UI
    ProgrammeUI programmeUI = new ProgrammeUI();

    public ProgrammeMaintenance() {
        programmeList = programmeDAO.retrieveFromFile();
        facultyList = facultyDAO.retrieveFromFile();
    }
    
    public void runProgrammeMaintenance(){
        boolean end = false;
        MessageUI.printLine();
        do { 
            int choice = programmeUI.getMenuChoice();
            switch(choice){
                case 0:
                    MessageUI.displayBackMessage();
                    end = true;
                    break;
                    
                //create new pogramme
                case 1:
                    MessageUI.printLine();
                    createProgramme();
                    break;

                //list all programme
                case 2:
                    MessageUI.printLine();
                    programmeUI.displayProgramme();
                    listAllProgramme();
                    MessageUI.enterPrompt();
                    MessageUI.printLine();
                    break;
                    
                    
                default:
                    MessageUI.displayErrorMessage();
            }
        }while(!end);
    }
    
    //create new programme to this semester
    public void createProgramme(){
        boolean back = false;
        
         do { 
            programmeUI.displayAddingNewProgramme();
            listAllProgramme();
            
            //get programme code
            MessageUI.zeroPrompt();
            String programmeCode = programmeUI.inputProgrammeCode();
            if(programmeCode.equals("0")){
                back = true;
                MessageUI.printLine();
                MessageUI.displayBackMessage();
                break;
            }
             
            //get programme name
            String programmeName = programmeUI.inputProgrammeName();

            int facultyIndex = programmeUI.inputFaculty();
            
            
            Faculty faculty = facultyList.getEntry(facultyIndex);
            
            Programme newProgramme = new Programme(programmeCode, programmeName, faculty);
            
            programmeList.add(newProgramme);
            programmeDAO.saveToFile(programmeList);      //save programme to programmelist
            
             MessageUI.printLine();
            MessageUI.displayAddSuccessful(newProgramme.getProgrammeCode());
            
            
        } while (!back);
    }
    
    
    //list all programmes
    public boolean listAllProgramme() {
        programmeUI.displayheader();
        if(programmeList.isEmpty()){
            programmeUI.displayEmpty();
            //MessageUI.enterPrompt();
        }
        
        programmeList.list();
        return programmeList.isEmpty();
    }
    
    
    public static void main(String[] args) {
        ProgrammeMaintenance pm = new ProgrammeMaintenance();
        pm.runProgrammeMaintenance();
    }
    
}
