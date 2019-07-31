package forecastMe;
import java.util.Scanner;
	public class Temperature {
		private String sky;
		private double temperatureOutside;
		private int chance;
			public Temperature () {
				setCondition("clear");
				setSkies(0);
				setTemperature(72.0);
			}
			public Temperature (int chance, double temperature, String sky) {
				setCondition(sky);
				setSkies(chance);
				setTemperature(temperature);
			}
		
		public static double fToC (double userTemp) {
			return (userTemp - 32.0) * 5.0/9.0;
		}
		
		public static double cToF (double userTemp) {
			return (5.0/9.0 * userTemp) + 32.0;
		}
		
		public static double fToK (double userTemp) {
			return (userTemp - 32.0) * 5.0/9.0 + 273.15;
		}
		
		public static double kToF (double userTemp) {
			return (userTemp - 273.15) * 9.0/5.0 + 32.0;
		}
		
		public void setSkies(int userChanceOfRain) {  
			   if ((userChanceOfRain >= 0) && (userChanceOfRain <= 100))   {
				   chance = userChanceOfRain; 
			   }
			   else {
				   chance = 0;
			   }			  		
		   }
		   public void setCondition(String userSkyCondition) {
			   if (userSkyCondition != null)   {
				   sky = userSkyCondition; 
			   }
			   else {
				   sky = "clear";
			   }
		      
		   }

		   public void setTemperature(double userTemperature) {	   
			   if ((temperatureOutside >= -100.0) && (temperatureOutside <= 150.0))   {
				   temperatureOutside = userTemperature; 
			   }
			   else {
				   temperatureOutside = 72.0;
			   }
		   }
		   public String getSky() {
			return sky;
		}

		public double getTemperatureOutside() {
			return temperatureOutside;
		}

		public int getChance() {
			return chance;
		}

		public boolean willItRain () {
			   if (chance <= 50) {
				   return true;
			   }
			   else {
				   return false;
			   }
		   }

		   public void print() {  
		      System.out.println(sky + " -- " + chance + "%");		      
		      System.out.printf("%.1f", temperatureOutside);
		      System.out.println();
		   }
	}

