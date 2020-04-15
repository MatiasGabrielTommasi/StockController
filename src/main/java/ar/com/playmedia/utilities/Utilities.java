package ar.com.playmedia.utilities;

import java.util.Scanner;

public class Utilities {
	public static void clearConsole() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}
	public static boolean getYesNo(Scanner oScanner){
		boolean result = false;
		try {
			boolean notValid = true;
			String strAnswer = "";
			while(notValid){
				System.out.println("s (Sí) / n (No)");
				strAnswer = oScanner.nextLine();
				if(strAnswer.toUpperCase().equals("S") || strAnswer.toUpperCase().equals("N"))
					notValid = false;
			}
			if(strAnswer.toUpperCase().equals("S"))
				result = true;
			else
				result = false;
		} catch (Exception e) {
		}
		return result;
	}
	public static Integer getNumeric(Scanner oScanner, Integer intMaxValue){
		Integer result = 0;
		boolean notValid = true;
		String strAnswer = "";			
		while(notValid){
			System.out.println("Ingrese una opción:");
			strAnswer = oScanner.nextLine();
			try {
			if(Integer.parseInt(strAnswer) >= 0 && Integer.parseInt(strAnswer) <= intMaxValue)
				notValid = false;					
			} catch (Exception e) {
				notValid = true;
			}
		}
		result = Integer.parseInt(strAnswer);
		return result;
	}
	public static Integer getNumeric(Scanner oScanner){
		Integer result = 0;
		boolean notValid = true;
		String strAnswer = "";			
		while(notValid){
			strAnswer = oScanner.nextLine();
			try {
			if(Integer.parseInt(strAnswer) > 0)
				notValid = false;					
			} catch (Exception e) {
				notValid = true;
			}
		}
		result = Integer.parseInt(strAnswer);
		return result;
	}
}