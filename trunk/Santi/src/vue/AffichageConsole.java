package vue;

public class AffichageConsole {
	
	public static void afficheMatrice(int x, int y){
		System.out.print("|");
		for(int k = 0; k < x; k++){
			System.out.print("-------|");
		}
		System.out.println();
		for (int i = 0; i < y; i++) {
			System.out.print("|");
			for (int j = 0; j < x; j++) {
				System.out.print(" ("+j+","+i+") |");
			}
			System.out.println();
			System.out.print("|");
			for(int k = 0; k < x; k++){
				System.out.print("-------|");
			}
			System.out.println();
		}
	}
	
	public static void main(String [] args){
		AffichageConsole.afficheMatrice(8, 6);
	}
}
