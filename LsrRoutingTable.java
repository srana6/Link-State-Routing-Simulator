package cs542.lsr.project.rTable;

public class LsrRoutingTable {
	public int routerCount;
	private int actualSource;
	public int[][] costLSR;
	private int[][] tempCostLSR;
	public int[] distance;
	public int[][] nextHop;
	public int[] visited;
	public StringBuffer shortestPaths;
	int[] path;
	int min;
	int nextVisitedLink = 0;
	int flag = 0;
	public StringBuffer bufferToDisplay;
	
	public LsrRoutingTable(int inputRouterCount, int inputSource, int[][] inputCostLSR, int flag) {
		this.flag = flag;
		if(flag == 1)
			costLSR = inputCostLSR;
		else if(flag == 2)
			tempCostLSR = inputCostLSR;
		routerCount = inputRouterCount;
		actualSource = inputSource - 1;
		visited = new int[routerCount];
		path = new int[routerCount];
		nextHop = new int[routerCount][routerCount];
		//----------------------------------------------------------check if revert must be done acc to flag for tempCostLSR
		//distance = costLSR[actualSource];
		if(flag == 1)
			distance = costLSR[actualSource];
		else if(flag == 2)
			distance = tempCostLSR[actualSource];
	}


	public void initNextHop(){
		for(int i=0; i<routerCount; i++){
			nextHop[i][i] = 0;
		}
	}
	
	
	
	
	/**
	 * This method find the minimum path to a particular destination and
	 * generates the matrix in which the path from a source to the other
	 * remaining nodes is stored
	 */
	public void createRoutingTable() {
		int i,j,k,previous;
		shortestPaths = new StringBuffer();
		
		for(i=0; i<routerCount; i++){
			visited[i] = 0;
			path[i] = actualSource;
			if(distance[i] == -1)
				distance[i] = 99999;
			nextHop[i][i] = 0;
		}

		visited[actualSource] = 1;

		for (i = 0; i < routerCount; i++) {
			min = 99999;
			
			for (j = 0; j < routerCount; j++) {
				if(visited[j] != 1){
					if((distance[j] != 0)&&(distance[j] < min)) {
					//	if(distance[j] == min){
						//	shortestPaths.append("Attached equivalent"+min+" !!!");
						//}
						min = distance[j];
						nextVisitedLink = j;
					}
				}
			}
			
			visited[nextVisitedLink] = 1;
			
			for (k = 0; k < routerCount; k++) {

				if (visited[k] != 1){
					if(flag == 1)
					{
					if(costLSR[nextVisitedLink][k] != -1) {
						if (min + costLSR[nextVisitedLink][k] < distance[k]) {
							distance[k] = min + costLSR[nextVisitedLink][k];
							path[k] = nextVisitedLink;
						}
					}
					}else if(flag == 2){
						if(tempCostLSR[nextVisitedLink][k] != -1) {
							if (min + tempCostLSR[nextVisitedLink][k] < distance[k]) {
								distance[k] = min + tempCostLSR[nextVisitedLink][k];
								path[k] = nextVisitedLink;
							}
						}						
					}
				}
			}

		}
		
		i = j = k = 0;
		while(i < routerCount){
			nextHop[i][j] = path[i] + 1;
			i++;
		}
		for (i = 0; i < routerCount; i++) {
			for (j = 0; j < routerCount; j++) {
				if(nextHop[i][j] != 0){	
					if (nextHop[i][j] != (actualSource + 1)) {
						previous = nextHop[i][j] - 1;
						if (nextHop[previous][k] != previous + 1)
							nextHop[i][j + 1] = nextHop[previous][k];
					}

				}

			}

		}
		
	}

	/**
	 * Extract the path from the {nextHop} Matrix
	 * 
	 * @return buf{toString}
	 */
	public String pathToString() {
		bufferToDisplay = new StringBuffer();
		int i, j, count;
		i = j = count = 0;

		bufferToDisplay.append("Router R" + (actualSource + 1) + " Connection Table ->\n"
				+ "|  Destination  |  Next Hop  |\n"+"________________________________\n");
		for (i = 0; i < routerCount; i++) {
			j = 0;
			if (i != actualSource) {
				do {

					if (nextHop[i][j] == actualSource + 1 && count == 0) {
						bufferToDisplay.append("       R" + (i + 1) + "                "
								+ (i + 1) + "\n");
					}
					if (nextHop[i][j] != actualSource + 1 && nextHop[i][j] != 0) {
						count = count + 1;
					}
					if (nextHop[i][j] == actualSource + 1 && count != 0) {
						bufferToDisplay.append("       R" + (i + 1) + "                "
								+ (nextHop[i][j - 1]) + "\n");
						count = count + 1;
					}
					j = j + 1;
				} while (nextHop[i][j] != 0);
				count = 0;
			} else {
				bufferToDisplay.append("       R" + (i + 1) + "                " + "-" + "\n");
			}
		}
		return bufferToDisplay.toString();
	}
}
