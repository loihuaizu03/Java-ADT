
package dao;

import ADT.ArrayList;
import ADT.ListInterface;
import Entity.Course;
import Entity.Faculty;
import Entity.Programme;
import Entity.Student;
import Entity.Tutor;
import Entity.TutorialGroup;
import java.io.Serializable;

/**
 *
 * @author Chow Xing Shao
 */
public class Initializer implements Serializable{
    
    private ListInterface<Faculty> facultyList = new ArrayList<>();
    private ListInterface<Programme> programmeList = new ArrayList<>();
    private ListInterface<Course> courseList = new ArrayList<>();
    private ListInterface<Student> studentList = new ArrayList<>();
    private ListInterface<Tutor> tutorList = new ArrayList<>();
    
    //tutorial group list for all programme
    private ListInterface<TutorialGroup> tutorialGroupList = new ArrayList<>();
    
    private CommonDAO facultyDAO = new CommonDAO("faculty.txt");
    
    //programme dao
    private CommonDAO programmeDAO = new CommonDAO("programme.txt");
    
    //course dao
    private CommonDAO courseDAO = new CommonDAO("course.txt");
    
    private CommonDAO studentDAO = new CommonDAO("student.txt");
    
    private CommonDAO tutorDAO = new CommonDAO("tutor.txt");
    
    private CommonDAO tutorialGroupDAO = new CommonDAO("tutorialGroup.txt");

    public Initializer() {
        
        facultyList.clear();
        programmeList.clear();
        courseList.clear();
        studentList.clear();
        tutorList.clear();
        tutorialGroupList.clear();
        
        initializeData();
        tutorialGroupDAO.saveToFile(tutorialGroupList);
        
    }
    
    public void initializeData(){
        facultyData();
        facultyDAO.saveToFile(facultyList);
        
        courseData();
        courseDAO.saveToFile(courseList);
        
        studentdata();
        studentDAO.saveToFile(studentList);
        
        this.courseList = courseDAO.retrieveFromFile();
        programmeData();
        programmeDAO.saveToFile(programmeList);
        
        this.studentList = studentDAO.retrieveFromFile();
        this.programmeList = programmeDAO.retrieveFromFile();
        
        tutorialGroupData();
        
        tutorialGroupDAO.saveToFile(tutorialGroupList);
        programmeDAO.saveToFile(programmeList);
        
        
        
        tutorData();
        tutorDAO.saveToFile(tutorList);
    }
    
    public void facultyData(){
        facultyList.add(new Faculty("FAFB", "Faculty Of Accountancy, Finance and Business"));
        facultyList.add(new Faculty("FOCS", "Faculty of Computing and Technology"));
        facultyList.add(new Faculty("FCCI", "Faculty of Communication and Creative Industries"));
        facultyList.add(new Faculty("FOAS", "Faclulty of Applied Science"));
    }
    
    public void programmeData(){
        programmeList.add(new Programme("RSW", "Bachelor of Software Engineer", facultyList.getEntry(2)));
        
        //courses in RSW
        programmeList.getEntry(1).addCourse(courseList.getEntry(1));
        programmeList.getEntry(1).addCourse(courseList.getEntry(6));
        programmeList.getEntry(1).addCourse(courseList.getEntry(10));
        programmeList.getEntry(1).addCourse(courseList.getEntry(7));
        programmeList.getEntry(1).addCourse(courseList.getEntry(1));
        programmeList.getEntry(1).addCourse(courseList.getEntry(9));
        
        
        programmeList.add(new Programme("RDS", "Bachelor of Data Science", facultyList.getEntry(2)));
        programmeList.add(new Programme("RCA", "Bachelor of Marketing", facultyList.getEntry(1)));
        programmeList.add(new Programme("RCV", "Bachelor of Finance and Accounting", facultyList.getEntry(1)));
        programmeList.add(new Programme("RDG", "Bachelor of Design in Graphic Design", facultyList.getEntry(3)));
        programmeList.add(new Programme("RBI", "Bachelor of Investment", facultyList.getEntry(1)));
        programmeList.add(new Programme("RFS", "Bachelor of Food Science", facultyList.getEntry(4)));
        programmeList.add(new Programme("RCH", "Bachelor of Chemistry", facultyList.getEntry(4)));
        programmeList.add(new Programme("RBA", "Bachelor of Business Administration", facultyList.getEntry(1)));
        programmeList.add(new Programme("RBM", "Bachelor of Business Analytic", facultyList.getEntry(1)));
        programmeList.add(new Programme("RMC", "Bachelor of Multimedia", facultyList.getEntry(3)));
        
    }
    
    public void tutorialGroupData(){
        //add group to RSW
        TutorialGroup group1 = new TutorialGroup(programmeList.getEntry(1).getProgrammeCode(), 0, 25);
        TutorialGroup group2 = new TutorialGroup(programmeList.getEntry(1).getProgrammeCode(), 1, 25);
        TutorialGroup group3 = new TutorialGroup(programmeList.getEntry(1).getProgrammeCode(), 2, 25);
        programmeList.getEntry(1).addTutorialGroup(group1);
        //add students to RSW GROUP 1
        programmeList.getEntry(1).getTutorialGroupList().getEntry(1).addStudent(studentList.getEntry(1));
        programmeList.getEntry(1).getTutorialGroupList().getEntry(1).addStudent(studentList.getEntry(2));
        programmeList.getEntry(1).getTutorialGroupList().getEntry(1).addStudent(studentList.getEntry(3));
        programmeList.getEntry(1).getTutorialGroupList().getEntry(1).addStudent(studentList.getEntry(4));
        programmeList.getEntry(1).getTutorialGroupList().getEntry(1).addStudent(studentList.getEntry(5));
        programmeList.getEntry(1).addTutorialGroup(group2);
        
        
        programmeList.getEntry(1).addTutorialGroup(group3);
        //add students to RSW GROUP 3
        programmeList.getEntry(1).getTutorialGroupList().getEntry(3).addStudent(studentList.getEntry(6));
        programmeList.getEntry(1).getTutorialGroupList().getEntry(3).addStudent(studentList.getEntry(7));
        programmeList.getEntry(1).getTutorialGroupList().getEntry(3).addStudent(studentList.getEntry(8));
        programmeList.getEntry(1).getTutorialGroupList().getEntry(3).addStudent(studentList.getEntry(9));
        
        //add group to RDS
        TutorialGroup group4 = new TutorialGroup(programmeList.getEntry(2).getProgrammeCode(), 0, 25);
        TutorialGroup group5 = new TutorialGroup(programmeList.getEntry(2).getProgrammeCode(), 1, 25);
        TutorialGroup group6 = new TutorialGroup(programmeList.getEntry(2).getProgrammeCode(), 2, 25);
        programmeList.getEntry(2).addTutorialGroup(group4);
        programmeList.getEntry(2).addTutorialGroup(group5);
        programmeList.getEntry(2).addTutorialGroup(group6);
        
        
        tutorialGroupList.add(group1);
        tutorialGroupList.add(group2);
        tutorialGroupList.add(group3);
        tutorialGroupList.add(group4);
        tutorialGroupList.add(group5);
        tutorialGroupList.add(group6);
    }
    
    public void courseData(){
        courseList.add(new Course("BACS2024", "Introduction to Computer Network", 1));
        courseList.add(new Course("BBBL2013", "Law & Practise of Meetings", 1));
        courseList.add(new Course("BBFA1053", "Business Accounting", 2));
        courseList.add(new Course("BBMF2093", "Corporate Finance", 1));
        courseList.add(new Course("BBFA2034", "Financial Accounting Framework", 3));
        courseList.add(new Course("BJEL2013", "English For Career Preparation", 3));
        courseList.add(new Course("BACS2063", "Data Structure and Algorithm", 3));
        
        
        courseList.getEntry(1).addStudent(studentList.getEntry(1));
        courseList.getEntry(2).addStudent(studentList.getEntry(1));
        courseList.getEntry(3).addStudent(studentList.getEntry(1));
        courseList.getEntry(4).addStudent(studentList.getEntry(1));
        courseList.getEntry(5).addStudent(studentList.getEntry(1));
        /*
        
        courseList.add(new Course("BBBD3093", "Professional Practice & Ethics", 1));
        courseList.add(new Course("BBDM3053", "E-Business", 2));
        courseList.add(new Course("BMIT2713", "Information and IT Security", 3));
       
        courseList.add(new Course("BCOM3023", "Business Presentation", 3));
        courseList.add(new Course("BACC3034", "Advanced Corporate Accounting", 4));
        courseList.add(new Course("BFIN3043", "Financial Risk Management", 3));
        courseList.add(new Course("BHRM3042", "Leadership in HRM", 2));
        courseList.add(new Course("BOBS3063", "Supply Chain Management", 3));
        courseList.add(new Course("BENT3023", "Innovation Management", 3));
        courseList.add(new Course("BCOM3074", "Negotiation Skills", 4));
        courseList.add(new Course("BSTM3022", "Change Management", 2));
        courseList.add(new Course("BINT3042", "Global Trade Operations", 2));
        courseList.add(new Course("BHRM3053", "Employee Relations Management", 3));
        courseList.add(new Course("BBMG3014", "Global Business", 4));
        courseList.add(new Course("BBMN3013", "Marketing Management", 3));
        courseList.add(new Course("BHRM3012", "Human Resource Management", 2));
        courseList.add(new Course("BOBS3013", "Operations and Business Strategy", 3));
        courseList.add(new Course("BENT3013", "Entrepreneurship", 3));
        courseList.add(new Course("BCOM3012", "Communication Skills", 3));
        courseList.add(new Course("BSTM3014", "Strategic Management", 4));
        courseList.add(new Course("BINT3013", "International Business", 3));
        courseList.add(new Course("BLAW3014", "Business Law", 4));
        courseList.add(new Course("BCOM3053", "Professional Communication", 3));
        
        */
    }
    
    public void studentdata(){
        studentList.add(new Student("Chow Xing Shao", "01131637633", "040328100969", "jackychow@04gmail.com", 2300000));
        
        studentList.getEntry(1).addCourse(courseList.getEntry(1));
        studentList.getEntry(1).addCourse(courseList.getEntry(2));
        studentList.getEntry(1).addCourse(courseList.getEntry(3));
        studentList.getEntry(1).addCourse(courseList.getEntry(4));
        studentList.getEntry(1).addCourse(courseList.getEntry(5));
        
        studentList.add(new Student("Varsyathini Paramasavam", "0189618933", "020810101152", "varsya1008@gmail.com", 2300001));
        studentList.add(new Student("Loi Huai Zu", "0175095539", "030203103397", "huaizu@09gmail.com", 2300002));
        studentList.add(new Student("Chew Kai Ping", "0120793741 ", "010910142269", "kaiping@03gmail.com", 2300003));
        studentList.add(new Student("John Smith", "0123456789", "010123456789", "john.smith@gmail.com", 2300004));
        studentList.add(new Student("Emily Johnson", "0198765432", "011987654321", "emily.johnson@gmail.com", 2300005));
        studentList.add(new Student("Michael Brown", "0176543210", "012765432109", "michael.brown@gmail.com", 2300006));
        studentList.add(new Student("Sophia Lee", "0165432109", "013654321098", "sophia.lee@gmail.com", 2300007));
        studentList.add(new Student("Daniel Wang", "0187654321", "014876543210", "daniel.wang@gmail.com", 2300008));
        studentList.add(new Student("Charlotte Lim", "0134567890", "015345678901", "olivia.chan@gmail.com", 2300009));
        studentList.add(new Student("Matthew Ng", "0156789012", "016567890123", "matthew.ng@gmail.com", 2300010));
        studentList.add(new Student("Emma Tan", "0145678901", "017456789012", "emma.tan@gmail.com", 2300011));
        studentList.add(new Student("William Lim", "0112345678", "018123456789", "william.lim@gmail.com", 2300012));
        studentList.add(new Student("Ava Lee", "0109876543", "019098765432", "lily.goh@gmail.com", 2300013));
        studentList.add(new Student("Aiden Wong", "0167890123", "020167890123", "aiden.wong@gmail.com", 2300014));
        studentList.add(new Student("Isabella Ho", "0156789012", "021567890123", "isabella.ho@gmail.com", 2300015));
        studentList.add(new Student("Ethan Tan", "0145678901", "022456789012", "ethan.tan@gmail.com", 2300016));
        
        
        /*
        studentList.add(new Student("Mia Lim", "0134567890", "023345678901", "mia.lim@gmail.com", 2300017));
        studentList.add(new Student("James Lee", "0123456789", "024123456789", "james.lee@gmail.com", 2300018));
        studentList.add(new Student("Charlotte Lim", "0112345678", "025123456789", "charlotte.lim@gmail.com", 2300019));
        studentList.add(new Student("Lucas Goh", "0101234567", "026101234567", "lucas.goh@gmail.com", 2300020));
        studentList.add(new Student("Amelia Wong", "0190123456", "027901234567", "amelia.wong@gmail.com", 2300021));
        studentList.add(new Student("Benjamin Tan", "0189012345", "028890123456", "benjamin.tan@gmail.com", 2300022));
        studentList.add(new Student("Harper Ho", "0178901234", "029789012345", "harper.ho@gmail.com", 2300023));
        studentList.add(new Student("Elijah Lim", "0167890123", "030678901234", "elijah.lim@gmail.com", 2300024));
        studentList.add(new Student("Ava Lee", "0156789012", "031567890123", "ava.lee@gmail.com", 2300025));
        studentList.add(new Student("Logan Tan", "0145678901", "032456789012", "logan.tan@gmail.com", 2300026));
        */
    }
    
    public void tutorData(){
        tutorList.add(new Tutor("Ivy Moore", "01234567890", "123456789012", "alice@gmail.com", 2, 1000));
        tutorList.add(new Tutor("Bob Johnson", "01234567891", "234567890123", "bob@gmail.com", 3, 1001));
        tutorList.add(new Tutor("Henry Wilson", "01234567892", "345678901234", "charlie@gmail.com", 1, 1002));
        tutorList.add(new Tutor("David Jones", "01234567893", "456789012345", "david@gmail.com", 2, 1003));
        tutorList.add(new Tutor("Jack Taylor", "01234567894", "567890123456", "emma@gmail.com", 3, 1004));
        tutorList.add(new Tutor("Frank Davis", "01234567895", "678901234567", "frank@gmail.com", 1, 1005));
        tutorList.add(new Tutor("Grace Miller", "01234567896", "789012345678", "grace@gmail.com", 2, 1006));
        tutorList.add(new Tutor("Charlie Williams", "01234567897", "890123456789", "henry@gmail.com", 3, 1007));
        tutorList.add(new Tutor("Alice Smith", "01234567898", "901234567890", "ivy@gmail.com", 1, 1008));
        tutorList.add(new Tutor("Emma Brown", "01234567899", "012345678901", "jack@gmail.com", 2, 1009));
        
        /*
        tutorList.add(new Tutor("Alice Smith", "01234567800", "123456789012", "alice2@gmail.com", 3, 1010));
        tutorList.add(new Tutor("Bob Johnson", "01234567801", "234567890123", "bob2@gmail.com", 1, 1011));
        tutorList.add(new Tutor("Charlie Williams", "01234567802", "345678901234", "charlie2@gmail.com", 2, 1012));
        tutorList.add(new Tutor("David Jones", "01234567803", "456789012345", "david2@gmail.com", 3, 1013));
        tutorList.add(new Tutor("Emma Brown", "01234567804", "567890123456", "emma2@gmail.com", 1, 1014));
        tutorList.add(new Tutor("Frank Davis", "01234567805", "678901234567", "frank2@gmail.com", 2, 1015));
        tutorList.add(new Tutor("Grace Miller", "01234567806", "789012345678", "grace2@gmail.com", 3, 1016));
        tutorList.add(new Tutor("Henry Wilson", "01234567807", "890123456789", "henry2@gmail.com", 1, 1017));
        tutorList.add(new Tutor("Ivy Moore", "01234567808", "901234567890", "ivy2@gmail.com", 2, 1018));
        tutorList.add(new Tutor("Jack Taylor", "01234567809", "012345678901", "jack2@gmail.com", 3, 1019));
        
        tutorList.add(new Tutor("Alice Smith", "01234567810", "123456789012", "alice3@gmail.com", 1, 1020));
        tutorList.add(new Tutor("Bob Johnson", "01234567811", "234567890123", "bob3@gmail.com", 2, 1021));
        tutorList.add(new Tutor("Charlie Williams", "01234567812", "345678901234", "charlie3@gmail.com", 3, 1022));
        tutorList.add(new Tutor("David Jones", "01234567813", "456789012345", "david3@gmail.com", 1, 1023));
        tutorList.add(new Tutor("Emma Brown", "01234567814", "567890123456", "emma3@gmail.com", 2, 1024));
        tutorList.add(new Tutor("Frank Davis", "01234567815", "678901234567", "frank3@gmail.com", 3, 1025));
        tutorList.add(new Tutor("Grace Miller", "01234567816", "789012345678", "grace3@gmail.com", 1, 1026));
        tutorList.add(new Tutor("Henry Wilson", "01234567817", "890123456789", "henry3@gmail.com", 2, 1027));
        tutorList.add(new Tutor("Ivy Moore", "01234567818", "901234567890", "ivy3@gmail.com", 3, 1028));
        tutorList.add(new Tutor("Jack Taylor", "01234567819", "012345678901", "jack3@gmail.com", 1, 1029));
        */
    }
    
}
