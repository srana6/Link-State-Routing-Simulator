package cs542.lsr.project.lsrAlgo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JOptionPane;

public class LsrRoutingAlgo {
	public int[][] backupCostLSR;
	public int[][] restoreCostLSR;
	
	public int[][] costLSR;
	public int[][] tempCostLSR;
	public String lineValues[];
	private String fileContent;
	public static int sourceNode;
	public static int destinationNode;
	public static int[][] nextHop;
	private BufferedReader bReader, bReaderInternal;
	FileReader inputFileReader;
	public int routerCount, row, column;
	public String traverseLine;
	public String traverseLine1;
	public StringBuffer bufferToDisplay;
	
// MJ changes
	public int[] checkAgainNodes;
	/**
	 * Creates A constructor with filePath as Parameter to initialize the
	 * Network
	 * 
	 * @param filePath
	 * @throws Exception
	 */
	public LsrRoutingAlgo(String fileContent) throws Exception {
		this.fileContent = fileContent;
		routerCount = 0;
		inputFileReader = new FileReader(this.fileContent);
		displayTopology(inputFileReader);
	}

	
	private void displayTopology(FileReader inputFileReader) throws Exception{
		int i, j, k;
		int rowCount = 0;
		bReader = new BufferedReader(inputFileReader);
		if (bReader == null){ 
			System.out.println("No proper input specified !");
		}else{
		if((traverseLine = (bReader.readLine().trim())).isEmpty()) {
			System.out.println("Input is empty !");	
		}else{

			String[] cost = traverseLine.split(" ");
			for (i = 0; i<cost.length; i++) {
				if (!cost[i].trim().equals("")) {
					routerCount++;
				}
			}
			
		}
	}

	// Got total number of routers

	costLSR = new int[routerCount][routerCount];
	backupCostLSR = new int[routerCount][routerCount];
	tempCostLSR = new int[routerCount][routerCount];
	restoreCostLSR = new int[routerCount][routerCount];
	
	bReaderInternal = new BufferedReader(new FileReader(fileContent));
	traverseLine = bReaderInternal.readLine();
	if(traverseLine == null) {
		System.out.println("Internal input is empty !");	
	}else{
		i=j=k=0;
		while(traverseLine != null)
		{	
			rowCount = 0;
			lineValues = traverseLine.split(" ");
			for (i = 0; i<lineValues.length; i++) {
				if (!lineValues[i].trim().equals("")) {
		//		System.out.println(lineValues[i]+" ");
					rowCount++;
				}
			}

			if(rowCount == routerCount){
				for(i = 0; i<lineValues.length; i++){
					if(!lineValues[i].equals("")){
						String temp1;
						temp1 = lineValues[i];
						costLSR[j][k] = Integer.parseInt(temp1);
						tempCostLSR[j][k] = Integer.parseInt(temp1);
						backupCostLSR[j][k] = Integer.parseInt(temp1);
						restoreCostLSR[j][k] = Integer.parseInt(temp1);
						k++;
					}
				}
				k = 0;
				j++;
				traverseLine = bReaderInternal.readLine();
	
			}else{
				System.out.println("Input cost matrix is not symmetric");
				
			}

		}
	}
	
	
	}

	/**
	 * This method returns the String version of the Topology Matrix
	 * 
	 * @return buf{toString()}
	 */

	public String costLSRToString() {
		
		bufferToDisplay = new StringBuffer();
		bufferToDisplay.append("\n"+"Input Topology ->"+"\n\n\n");

		bufferToDisplay.append("      R"+(row+1));

		for(row = 1; row< routerCount; row++){
			bufferToDisplay.append("   R"+(row+1));
		}

		bufferToDisplay.append("\n\n");
		
		for (row = 0; row < routerCount; row++) {
			if(row+1 >= 10)
			{
				bufferToDisplay.append("R"+(row+1)+"  ");
				
			}else{
				bufferToDisplay.append("R"+(row+1)+"   ");
			}
			
			for (column = 0; column < routerCount; column++) {
				bufferToDisplay.append(costLSR[row][column]+"   ");
				
			}
			bufferToDisplay.append("\n");
		}

		return bufferToDisplay.toString();
		
	}

	/**
	 * This method extracts the minimum path from the {nextHop} matrix
	 * 
	 * @param source
	 * @param destination
	 * @param nextHop
	 * @return path{toString}
	 */
	public String getMinimumPath(int source, int destination, int[][] nextHop) {
		int sourceNode = source - 1;
		int destinationNode = destination - 1;
		int i, j = 0;
		StringBuffer path = new StringBuffer();
		checkAgainNodes = new int[routerCount];
		for(i = 0; i<routerCount; i++)
			checkAgainNodes[i] = -1;
		if (destinationNode != sourceNode
				&& destinationNode + 1 <= routerCount) {

			for (i = routerCount - 1; i >= 0; i--) {
				if (nextHop[destinationNode][i] != 0) {
					path.append(nextHop[destinationNode][i] + "=>");
					checkAgainNodes[j] = nextHop[destinationNode][i];
//					System.out.println(checkAgainNodes[j]+" check it.."+nextHop[destinationNode][i]);
					j++;
				}

			}
			path.append(Integer.toString(destinationNode + 1));
			checkAgainNodes[j] = destinationNode+1;
		}

//		path.append("Check again nodes : "+checkAgainNodes[0]+"  ...MJ Trial...!");
		return path.toString();

	}

}
