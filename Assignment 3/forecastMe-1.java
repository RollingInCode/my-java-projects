package forecastMe;
import java.util.*;
public class forecastMe {
	public static void main(String[] args) {
	      Temperature forecast = new Temperature();
	      Temperature forecast2 = new Temperature(10, 55.0, "stormy");
	      double userTemp = 0.0;
	      int select = 0;
	      int chance = 0;
	      String skyCondition;

	      

	      Scanner user_input = new Scanner(System.in);

	      System.out.println("What temperature would you like to convert this to?");
	      System.out.println("1. Fahrenheit to Celsius");
	      System.out.println("2. Celsius to Fahrenheit");
	      System.out.println("3. Fahrenheit to Kelvin");
	      System.out.println("4. Kelvin to Fahrenheit");
	      select = user_input.nextInt();
	      System.out.println("Enter the temperature value.");
	      userTemp = user_input.nextInt();
	      System.out.println("What's the chance of rain today?");
	      chance = user_input.nextInt();
	      if (chance >= 50)  {
	    	  skyCondition = "cloudy";
	      }
	      
	      else {
	    	  skyCondition = "clear";
	      }

	      
	      if (select == 1) {
	    	  System.out.printf("%.1f", Temperature.fToC(userTemp));
	    	  System.out.println(" -- Converted temperature.");
	    	  
	      }
	      	  
	      else if (select == 2) {
	    	  System.out.printf("%.1f", Temperature.cToF(userTemp));
;
	    	  System.out.println(" -- Converted temperature.");


	      }
	      
	      else if (select == 3) {
	    	  System.out.printf("%.1f", Temperature.fToK(userTemp));
	    	  System.out.println(" -- Converted temperature.");


	      }

	      else if (select == 4 ) {
	    	  System.out.printf("%.1f", Temperature.kToF(userTemp));
	    	  System.out.println(" -- Converted temperature.");


	      }
	      
	      else {
	    	  System.out.println("Try again.");
	      }
	      
	      forecast.print();
	      
	      forecast.setSkies(chance); // chance
	      forecast.setTemperature(userTemp); // temperature
	      forecast.setCondition(skyCondition); // condition of skies
	      
	      forecast.print();
	}
}
