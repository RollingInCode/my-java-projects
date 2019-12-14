// Franklyn Gonzalez, last edited 10/28/2019 

public class MyGenerics {
	
	public <T> int partOne(T[] array, int length, T position) {
		for (int i = 0; i < length; i++) {
			if (array[i].equals(position)) {
				return i;				
			}
		}

		
		return -1;
	}
	
	public <T extends Value> int partTwo(T a, T b) {
		
		
		if (a.value() > b.value()) {
			return a.value();
		}
		else {
			return b.value();
		}
		
	}

}
