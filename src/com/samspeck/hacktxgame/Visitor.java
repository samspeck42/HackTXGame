package com.samspeck.hacktxgame;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.NodeVisitor;

public class Visitor implements NodeVisitor {
	private static final int SKYBOX = 10;
	private static final int SPIKE = -1;
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

	// level generation rules
	// jaggies get interleaved with spikes
	// >5 on same level add pits
	private void padMatrix() {
		for (ArrayList<Integer> row : matrix)
			for (int pad = maxSize - row.size(); pad > 0; pad--)
				row.add(0);
	}

	private void transposeMatrix() {
		ArrayList<ArrayList<Integer>> transpose = new ArrayList<ArrayList<Integer>>();
		for (int col = maxSize - 1; col >= 0; col--) {
			ArrayList<Integer> transposeRow = new ArrayList<Integer>();
			for (int row = 0; row < matrix.size(); row++) {
				transposeRow.add(matrix.get(row).get(col));
			}
			transpose.add(transposeRow);
		}
		matrix = transpose;
	}

	private void addSpikes(){
		for(int r = matrix.size() -1; r > 0; r--){
			ArrayList<Integer> row = matrix.get(r);
			for(int c = 1; c<row.size()-1; c++){
				if(row.get(c) == 0 && row.get(c-1) > 0 && row.get(c+1) > 0 && matrix.get(r+1).get(c) > 0){
					row.set(c,-1);
				}
			}
		}
			
	}

	public void writeLevel(String url) {
		try {
			padMatrix();
			transposeMatrix();
			addSpikes();
			addPits();
			
			PrintWriter outFile = new PrintWriter("./levels/" + url.hashCode()
					+ ".level");
			outFile.println("URL: " + url);
			outFile.println("/tiles/block.png " + Level.TILE_IMPASSABLE);
			outFile.println("/tiles/spike.png " + Level.TILE_OBSTACLE);
			outFile.println();
			outFile.println("[Layout]");
//			printing skybox
			StringBuilder sky = new StringBuilder();
			for(int x = 0; x < matrix.get(0).size();x ++)
				sky.append("0 ");
			for(int x = 0; x < SKYBOX; x++)
				outFile.println(sky);
			
			for (int row = 0; row < matrix.size(); row++) {
				for (int col = 0; col < matrix.get(0).size(); col++) {
					if (matrix.get(row).get(col) > 0)
						outFile.print("1 ");
					else if(matrix.get(row).get(col) == -1)
						outFile.print("2 ");
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

	private void addPits() {
		// TODO Auto-generated method stub
		
	}

}
