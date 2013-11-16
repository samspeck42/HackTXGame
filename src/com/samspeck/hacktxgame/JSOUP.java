package com.samspeck.hacktxgame;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class JSOUP {
	public static void main(String[] args){
		String url = "http://jsoup.org/";
		try {
			Document doc = Jsoup.connect(url).get();
			Visitor visitor = new Visitor();
			doc.traverse(visitor);
			visitor.writeLevel(url);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
