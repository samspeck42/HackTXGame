package com.samspeck.hacktxgame;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.NodeVisitor;

public class Visitor implements NodeVisitor {
	ArrayList<ArrayList<Integer>> matrix;
	HashMap<String, Integer> countTags;
	int value;
	int maxSize;

	public Visitor() {
		matrix = new ArrayList<ArrayList<Integer>>();
		countTags = new HashMap<String, Integer>();
		value = 0;
		maxSize = 0;
	}

	@Override
	public void head(Node node, int depth) {
		if (!(node instanceof Element))
			return;
		Element elem = (Element) node;

		// 0 is reserved for blank space, or something, rewriting code!!!
		if (!(countTags.containsKey(elem.tagName())))
			countTags.put(elem.tagName(), ++value);

		ArrayList<Integer> row = new ArrayList<Integer>();
		for (int x = 0; x < depth; x++)
			row.add(countTags.get(elem.tagName()));

		if (maxSize < row.size())
			maxSize = row.size();
		if (row.size() > 0)
			matrix.add(row);
	}

	@Override
	public void tail(Node node, int depth) {

	}

	public void padMatrix() {
		for (ArrayList<Integer> row : matrix)
			for (int pad = maxSize - row.size(); pad > 0; pad--)
				row.add(0);
	}

	public void writeLevel(String url) {
		try {
			PrintWriter outFile = new PrintWriter("./levels/"+url.hashCode()+".level");
			padMatrix();
			outFile.println("URL: "+url);
			outFile.println("/tiles/block.png");
			outFile.println();
			outFile.println("[Layout]");
			for (int col = maxSize - 1; col >= 0; col--) {
				for (int row = 0; row < matrix.size(); row++) {
					// getting the transpose
					if (matrix.get(row).get(col) != 0)
						outFile.print("1 ");
					else
						outFile.print("0 ");
				}
				outFile.print("\n");
			}
			outFile.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
