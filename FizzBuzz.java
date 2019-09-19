
import java.util.*;
public class FizzBuzz {
	class Solution {
	    public List<String> fizzBuzz(int n) {
	        ArrayList<String> a1 = new ArrayList<String>();        
	        for (int i = 1; i <= n; i++) {            
	            if ((i % 5) == 0 && (i % 3) == 0) {
	                a1.add("FizzBuzz");
	                System.out.println("FizzBuzz");
	            }
	            else if ((i % 5) == 0) {
	                a1.add("Buzz");
	                System.out.println("Buzz");
	            }
	            else if ((i % 3) == 0) {
	                a1.add("Fizz");
	                System.out.println("Fizz");
	            }
	            else {
	                a1.add(Integer.toString(i));
	                System.out.println("" + Integer.toString(i));
	            }
	        }  
	        return a1;
	    }
	}
}
