package com.riddim.editfile;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Edit_File {

	static String lineChange;
	static String replace;

	public static void main(String[] args) {
		//c:\\Favo_Now.m3u

		System.out.print("Program started\nWith this program you can replace text with other text.\n");
		Scanner user_input = new Scanner( System.in ); 
		String FilePath;
		System.out.print("Type the file path from the file you want to change:\nExample: c:\\\\Favo_Now.m3u\n");
		FilePath = user_input.nextLine();

		File f=new File(FilePath);

		FileInputStream fs = null;
		InputStreamReader in = null;
		BufferedReader br = null;
		StringBuffer sb = new StringBuffer();
		String textinLine;


		DefaultProg();

		try {
			fs = new FileInputStream(f);
			in = new InputStreamReader(fs);
			br = new BufferedReader(in);

			while(true)
			{
				textinLine=br.readLine();
				if(textinLine==null)
					break;
				sb.append(textinLine);

				String textToEdit1 = lineChange;
				int cnt1 = sb.indexOf(textToEdit1);
				if (cnt1 >= 0){
					sb.replace(cnt1,cnt1+textToEdit1.length(),"\n" + replace);
				} else {
					int endline = sb.indexOf(textinLine);
					sb.replace(endline,endline ,"\n");
				}  
			}
			fs.close();
			in.close();
			br.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		try{
			FileWriter fstream = new FileWriter(f);
			BufferedWriter outobj = new BufferedWriter(fstream);
			outobj.write(sb.toString());
			outobj.close();

		}catch (Exception e){
			System.err.println("Error: " + e.getMessage());
		}
		System.out.print("Program runned!");
	}

	public static String DefaultProg() {

		Scanner user_input = new Scanner( System.in );   
		System.out.print("Default program? type: y/n\n");
		String defaultProg;
		defaultProg = user_input.nextLine();

		if(defaultProg.equals("y")){
			lineChange = "\\\\DS2\\music\\playlist luuk\\";
			replace = "\n\\\\";
		} else {
			if(defaultProg.equals("n")){
				System.out.print("What do you want to change? Type exact string\n");
				lineChange = user_input.nextLine();
				System.out.print("You want to change: " + lineChange + "\n");
				
				System.out.print("Now change this into? Type exact string\n");
				replace = user_input.nextLine();
				System.out.print("Into:" +  replace + "\n");
			} else{
				System.out.print("U did not type y or n\n");
				DefaultProg();
			}
		}
		user_input.close();
		return lineChange + replace;
	}
}