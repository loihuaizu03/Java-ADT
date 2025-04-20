
package utility;

import java.util.Scanner;

/**
 *
 * @author Shao
 */
public class MessageUI {
    static Scanner scanner = new Scanner(System.in);
    
    public static String GetDivider(char symbol, int length){
        StringBuilder stringBuilder = new StringBuilder();
        for(int i=0;i<length;i++){
            stringBuilder.append(symbol);
        }
        stringBuilder.append("");
        return stringBuilder.toString();
    }
    
    public static void printLine(){
        for(int i=0;i<30;i++){
            System.out.println("\n");
        }
        
    }
    
    public static void displayErrorMessage(){
        printLine();
        System.out.println("\nTHERE IS AN ERROR OCCURED, PLEASE TRY AGAIN...");
    }
    
    public static void displayAddSuccessful(Object data){
        System.out.println("\n" + data + " ADDED SUCCESSFULLY!\n");
    }
    
    public static void displayRemoveSuccessful(Object data){
        System.out.println("\n" + data +" REMOVE SUCCESSFULLY!\n");
    }
    
    public static void displayExitMessage() {
        printLine();
        System.out.println("\nEXITING SYSTEM...");
    }
    
    public static void displayBackMessage() {
        printLine();
        System.out.println("\nBACK...");
    }
    
    public static void displayInputPrompt(){
        System.out.println("\nPLEASE ENTER YOUR CHOICE -> ");
    }
    
    public static void enterPrompt() {
        System.out.print("\nENTER KEY TO CONTINUE -> ");
        String enter = scanner.nextLine(); 
        
    }
    
    public static void zeroPrompt() {
        System.out.println("\n*0 TO GO BACK");
    }
    
    public static void displayChangeSuccessful(){
        System.out.println("\nAMEND SUCCESSFULLY!\n");
    }
    
    public static void displayMergeSuccessful(){
        System.out.println("\nMerge SUCCESSFULLY!\n");
    }
    
    public static void displayResultNotFound() {
        System.out.println("Result Not Found .....");
    }
    
    public static void displayPayment(String name) {
        MessageUI.printLine();
        System.out.println("\n         [Total Fees Needed to pay by student " + name + " ]\n");
    }
    
    public static void displayFilterResult(int age) {
        MessageUI.printLine();
        System.out.println("        [The student below the age gap " + age + " ]\n");
    }
}
