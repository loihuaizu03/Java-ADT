
package Entity;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Loi Huai Zu
 */
public class Person implements Serializable{
    
    private String name;
    private int age;
    private char gender;
    private String phoneNum;
    private String ic ;
    private String email ;
    
    private final static int currentyear = 2024;

    public Person(String name,String phoneNum, String ic, String email) {
        this.name = name;
        int yearBorn = Integer.parseInt(ic.substring(0,2));
        if( yearBorn <= 24 && yearBorn >0){
            yearBorn += 2000;
        }
        else if(yearBorn > 24){
            yearBorn += 1900;
        }
        this.age = currentyear - yearBorn;
        if(Integer.parseInt(ic.substring(11)) % 2 == 0){
            this.gender = 'F';
        }
        else
            this.gender = 'M';
        this.phoneNum = phoneNum;
        this.ic = ic;
        this.email = email;
    }

    public Person(String name, int age, char gender, String phoneNum, String ic, String email) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.phoneNum = phoneNum;
        this.ic = ic;
        this.email = email;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }
    
    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getIc() {
        return ic;
    }

    public void setIc(String ic) {
        this.ic = ic;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
        final Person other = (Person) obj;
        return Objects.equals(this.name, other.name);
    }
    
    @Override
    public String toString() {
        return String.format("%-5s%-25s%-5s%-9s%-17s%-20s%-30s","", name, age, gender, phoneNum, ic, email);
    }
}
