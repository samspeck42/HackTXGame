package com.samspeck.hacktxgame;

import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class JSOUP {
	public static void main(String[] args){
		promptUser();
	}
	
	public static void promptUser(){
		String url;
		Scanner scan = new Scanner(System.in);
		System.out.print("Please input a url to generate a level: ");
		while(scan.hasNext()){
			url = scan.next();
			if(generateLevel(url))
				return;
			System.out.println("Sorry, \"" + url + "\" couldn't be reached =(");
			System.out.print("Please input a url to generate a level: ");
		}
	}

	private static boolean generateLevel(String url) {
		try {
			Document doc = Jsoup.connect(url).get();
			Visitor visitor = new Visitor();
			doc.traverse(visitor);
			visitor.writeLevel(url);
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}
}
