import java.util.*;

public class Homework1{
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		System.out.println("Please input a integer from 1 to 99999");
		int num = in.nextInt();
		while (num < 1 || num > 99999){
			System.out.println("Input error,please input again");
			num = in.nextInt();
		}
		System.out.print("Whether " + num + " is a palindromic number?  ");
		
		int flag = 0;
		for (int temp = num; temp > 0; temp /= 10){
			flag = 10*flag + temp%10;
		}
		String result;
		result = (flag == num) ? "Yes":"No";
		System.out.println(result);
	}
}
