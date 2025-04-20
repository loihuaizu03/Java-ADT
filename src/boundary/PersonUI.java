
package boundary;

import java.util.Scanner;

/**
 *
 * @author Loi Huai Zu
 */
public class PersonUI {
    Scanner scanner = new Scanner(System.in);
    
    public String inputName() {
        System.out.printf("%-20s%s","Full Name"," -> " );
        String name = scanner.nextLine();
        if(name.equals("0")){
            return null;
        }
        return name;
    }
    
    public String inputPhoneNumber() {
        System.out.printf("%-20s%s","Phone Number", " -> ");
        String phone = scanner.nextLine();
        return phone;
    }
    
    public String inputIC() {
        System.out.printf("%-20s%s","IC Number"," -> ");
        String ic = scanner.nextLine();
        return ic;
    }
    
    public String inputEmail(){
        System.out.printf("%-20s%s","Personal Email", " -> ");
        String email = scanner.nextLine();
        return email;
        
    }
}
