import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.LinkedList;

public class Graph {
	int [] elements;
	ArrayList<ArrayList<Edge>> map = new ArrayList<ArrayList<Edge>>();
	Boolean marker = true;
	int source = 0;
	int sink;
	int level[];
	int visited[];
	int totalFlow = 0;
	int currentFlow = 0;
     ////////////Constructor method in which our bipartite graph shall be readjusted topologically to single-source-single-sink configuration
	public Graph(int [] elements, int[] checkpoints, int TG, int TR, int RG, int RR, ArrayList<String> types) {
		this.elements = elements;
		this.sink = elements.length -1;
		int lastGiftBag = elements.length - (TG + TR + RG + RR + 1 );
		for (int i = 0; i <elements.length;i++) {
			map.add(new ArrayList<Edge>());
		}
		for (int i = 1; i < lastGiftBag; i++) {
			map.get(0).add(new Edge(i, elements[i]));
		}
		String Bag;
		for (int i = 1; i < lastGiftBag; i++) {
			marker = true;
			Bag = types.get(i - 1);
			if (Bag.equals("abd")){
				marker = false;
				if (TG>0) {
					addEdge(i, checkpoints[0], checkpoints[0] + TG);
				}
			   
			}
			else if (Bag.equals("abe")){
				marker = false;
				if (RG>0) {
					addEdge(i, checkpoints[2], checkpoints[2] + RG);
			    }

			}
			else if (Bag.equals("acd")){
				marker = false;
				if (TR>0) {
					addEdge(i, checkpoints[1], checkpoints[1] + TR);
				}

			}
			else if (Bag.equals("ace")){
				marker = false;
			        if (RR>0) {
				       addEdge(i, checkpoints[3], checkpoints[3] + RR);
				}

			}
			else if (Bag.equals("ac")){
				marker = false;
				if (TR>0) {
					addEdge(i, checkpoints[1], checkpoints[1] + TR);
				    }
				    if (RR>0) {
					addEdge(i, checkpoints[3], checkpoints[3] + RR);
				    }

			}
			else if (Bag.equals("ab")){
				marker = false;
				if (TG>0) {
					addEdge(i, checkpoints[0], checkpoints[0] + TG);
				    }
				    if (RG>0) {
					addEdge(i, checkpoints[2], checkpoints[2] + RG);
				    }

			}
			else if (Bag.equals("ae")){
				marker = false;
			    if (RG>0) {
				addEdge(i, checkpoints[2], checkpoints[2] + RG);
			    }
			    if (RR>0) {
				addEdge(i, checkpoints[3], checkpoints[3] + RR);
			    }

			}
			else if (Bag.equals("ad")){
				marker = false;
			    if (TG>0) {
				addEdge(i, checkpoints[0], checkpoints[0] + TG);
			    }
			    if(TR>0) {
				addEdge(i, checkpoints[1], checkpoints[1] + TR);
			    }

			}
			else if (Bag.equals("bd")){
				if (TG>0) {
					addEdge(i, checkpoints[0], checkpoints[0] + TG);
				    }

			}
			else if (Bag.equals("be")){
			    if (RG>0) {
				addEdge(i, checkpoints[2], checkpoints[2] + RG);
			    }

			}
			else if (Bag.equals("cd")){
				if (TR>0) {
				       addEdge(i, checkpoints[1], checkpoints[1] + TR);
				}
			}
			else if (Bag.equals("ce")){
				if (RR>0) {
					addEdge(i, checkpoints[3], checkpoints[3] + RR);
				}

			}
			else if (Bag.equals("a")){
				marker = false;
			    if (TG>0) {
				addEdge(i, checkpoints[0], checkpoints[0] + TG);
			    }
			    if(TR>0) {
				addEdge(i, checkpoints[1], checkpoints[1] + TR);
			    }
			    if (RG>0) {
				addEdge(i, checkpoints[2], checkpoints[2] + RG);
			    }
			    if (RR>0) {
				addEdge(i, checkpoints[3], checkpoints[3] + RR);
			    }
			}
			else if (Bag.equals("b")){
			       if (TG>0) {
					addEdge(i, checkpoints[0], checkpoints[0] + TG);
			       }
				if (RG>0) {
					addEdge(i, checkpoints[2], checkpoints[2] + RG);
			       }


			}
			else if (Bag.equals("c")){
				if (TR>0) {
				       addEdge(i, checkpoints[1], checkpoints[1] + TR);
					}
				if (RR>0) {
					addEdge(i, checkpoints[3], checkpoints[3] + RR);
				}

			}
			else if (Bag.equals("d")){
				if (TG>0) {
					addEdge(i, checkpoints[0], checkpoints[0] + TG);
				}
				if (TR>0) {
					addEdge(i, checkpoints[1], checkpoints[1] + TR);
				}

			}
			else if (Bag.equals("e")){
				if (RG>0) {
					addEdge(i, checkpoints[2], checkpoints[2] + RG);
				}
				if (RR>0) {
					addEdge(i, checkpoints[3], checkpoints[3] + RR);
				}

			}
			else{
			  continue;
			}
			/////////////////////
		}
		int distance = this.elements.length - 1;
		for (int i = lastGiftBag; i < distance; i++) {
			map.get(i).add(new Edge( distance, elements[i]));
		}
		this.level = new int[this.elements.length];
		this.visited = new int[this.elements.length];
	}
	////////Adding Edges to entire demarcated by upper and lower boundaries set thanks to the checkpoints array
	public void addEdge(int element, int lowerLimit, int upperLimit) {
		if (marker == true) {
			for (int j = lowerLimit; j < upperLimit; j++) {
				map.get(element).add(new Edge( j, elements[element]));
				map.get(j).add(new Edge(element,0));
			}
		} else {
			for (int j = lowerLimit; j < upperLimit; j++) {
				map.get(element).add(new Edge( j, 1));
				map.get(j).add(new Edge(element,0));
			}
		}
	}
    ///////BFS method for assigning level graphs
	public boolean BFS() {
		Arrays.fill(level, -1);
		Arrays.fill(visited, 0);
		level[0] = 0;
		Queue<Integer> path = new LinkedList<Integer>();
		path.add(0);
		while (path.size() > 0) {
			int point = path.poll();
			visited[point] = 1;
				for (Edge edge : this.map.get(point)) {
					if (visited[edge.endNode] == 0 && edge.flow != 0 && !path.contains(edge.endNode)) {
						level[edge.endNode] = level[point] + 1;
						path.add(edge.endNode);
					}
				}
		}
		return level[this.sink] > -1 ;
	}
    //// DFS method for finding augmented paths using recursion
	public int DFS(int point, int[] track ,int flow) {
		if (point == this.sink) {
			return flow;
		}
		int noEdges = map.get(point).size();
	    while(track[point]<noEdges) {
	    	Edge edge = map.get(point).get(track[point]);
	    	int tmpflow = edge.flow;
	        if (tmpflow > 0 && level[edge.endNode] == level[point] + 1) {
	          int bottleNeck = DFS(edge.endNode, track, Math.min(flow, tmpflow));
	          if (bottleNeck > 0) {
	        	  map.get(point).get(track[point]).updateEdgenegatively(bottleNeck);
	        	  
	        	  for(int i = 0; i<map.get(edge.endNode).size();i++) {
	        		  if(map.get(edge.endNode).get(i).endNode == point ) {
	        			  map.get(edge.endNode).get(i).updateEdgepositively(tmpflow);
	        			  break;
	        		  }
	        	  }
	        	  
	            return bottleNeck;
	          }
	        }
	        track[point]++;
	      } 	
		return 0;
	}
   //////Implementation of Dinic Algorithm
	public void findMaxFlow() {
		int[] track = new int[this.sink + 1];
		while (BFS()) {
			Arrays.fill(track, 0);
			currentFlow = DFS(0, track, Integer.MAX_VALUE);
	        while(currentFlow!=0 ) {
	            this.totalFlow += currentFlow;
	            currentFlow = DFS(0, track, Integer.MAX_VALUE);
	          }
		}
	}
	public int getTotalFlow() {
		return totalFlow;
	}
}

