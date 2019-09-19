
import java.util.*;

class NQueens {

	public static void main(String[] args) {
		int n = 1;
		int r = 1;
		placeQueens(n, r);
	}
	
	
	private static void placeQueens(int n, int r) {	
		//System.out.println(n);
		
		int Q[] = {0,0,0,0,0,0};
		boolean legal;
		if (r == n + 1) {
			System.out.println(Q[n]);
		}
		else {
			for (int j = 0; j < n; j++) {
				legal = true;
				for (int i = 1; i < r - 1; i++) {
					if ((Q[i] == j) || (Q[i] == j + r - i) || (Q[i] == j - r + i)) {
						legal = false;
					}
				}
				if (legal == true) {
					Q[r] = j;
					placeQueens(n, r + 1);
				}
				
			}
		}
		
	}
	






}
