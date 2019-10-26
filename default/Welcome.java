/**
 * This program display a greeting for the reader
 */
public class Welcome{
    public static void main(String[] args){
	String greeting = "Welcome to Core java!";
	System.out.println(greeting);
	for (int i = 0; i < greeting.length(); i++){
	    System.out.print("=");
	}
	System.out.println();
    }
}
