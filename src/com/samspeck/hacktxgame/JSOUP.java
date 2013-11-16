package com.samspeck.hacktxgame;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class JSOUP {

	// TODO allow user to select old levels
	public String promptUser() {
		String url = (String) JOptionPane.showInputDialog("Please enter a URL",
				"http://");
		if (url != null) {
			if (generateLevel(url))
				return url;
		}
		JPanel panel = new JPanel();
		JOptionPane.showMessageDialog(panel, "Could not access " + url,
				"Error", JOptionPane.ERROR_MESSAGE);
		System.exit(0);
		return null;
	}

	private boolean generateLevel(String url) {
		try {
			Document doc = Jsoup.connect(url).get();
			Visitor visitor = new Visitor();
			doc.traverse(visitor);
			visitor.writeLevel(url);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}
}
