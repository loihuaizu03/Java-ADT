
package Entity;

import ADT.ArrayList;
import ADT.ListInterface;
import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Chow Xing Shao
 */
public class Faculty implements Serializable, Comparable<Faculty>{
    
    private String facultyCode;
    private String facultyName;
    
    //private ListInterface<Programme> programmeList;

    public Faculty(String facultyCode, String facultyName) {
        this.facultyCode = facultyCode;
        this.facultyName = facultyName;
        
        //this.programmeList = new ArrayList<>(); 
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
        final Faculty other = (Faculty) obj;
        if (!Objects.equals(this.facultyCode, other.facultyCode)) {
            return false;
        }
        return Objects.equals(this.facultyName, other.facultyName);
    }
    
    

    public String getFacultyCode() {
        return facultyCode;
    }

    public void setFacultyCode(String facultyCode) {
        this.facultyCode = facultyCode;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

    @Override
    public int compareTo(Faculty o) {
        return this.facultyCode.compareTo(o.facultyCode);
    }
    
    

    @Override
    public String toString() {
        return String.format("%2s%-8s%s", "", facultyCode, facultyName);
    }
    
    
    
    
    
}
