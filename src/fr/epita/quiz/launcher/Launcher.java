package fr.epita.quiz.launcher;

import java.util.Scanner;

import fr.epita.quiz.exception.CreateFailedException;
/**
 * 
 * @author mahesh
 *
 */
public class Launcher {

	private static Scanner sc;
	private static int opt;
		

	public static void main(String[] args) throws CreateFailedException {
		System.out.print("Welcome to the epita Quiz\n");
		System.out.print("Please select the option\n");
		System.out.print("1.Admin\n2.Student\n");
		sc = new Scanner(System.in);
		opt = Integer.parseInt(sc.nextLine());
		if(opt==1) {
			System.out.println("You Are Admin");
			new Admin();
		}
		else if(opt==2) {
			System.out.println("You Are A Student");
			new Student();
			
		}
		else 
		{
			System.out.println("Entered Wrong Answer");
		}
		}	



}
