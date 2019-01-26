import java.util.LinkedList;

class Edge
{
	int src;
	int dst;
	int wt;
	public Edge(int s,int d,int w)
	{
		src = s;
		dst = d;
		wt = w;
	}	
}

class Graph
{
	int Vertex;
	LinkedList<Edge> adjList[];
	public Graph(int V)
	{
		Vertex = V;
		adjList = new LinkedList[V];
		for(int i=0;i<V;i++)
		{
			adjList[i] = new LinkedList<Edge>();
		}
	}
	public static int getMinIndex(int[] myArr)
	{
		int res = 0;
		int min=Integer.MAX_VALUE;
		for(int i=0;i<myArr.length;i++)
		{
			if(myArr[i]<min)
			{
				res = i;
				min = myArr[i];
			}
		}
		return res;
	}
	public void addEdge(int src,int dst,int wt)
	{
		adjList[src].add(new Edge(src,dst,wt));
		adjList[dst].add(new Edge(dst,src,wt));
	}
	public int[] Dijkstra()
	{
		int[] res = new int[Vertex];
		int[] value = new int[Vertex];
		int[] parent = new int[Vertex];
		boolean[] visited = new boolean[Vertex];
		int count = Vertex;
		for(int i=0;i<Vertex;i++)
		{
			value[i]=Integer.MAX_VALUE;
			parent[i] = Integer.MAX_VALUE;
		}
		value[0]=0;
		parent[0]=-1;
		while(count>0)
		{
			int minIndex = getMinIndex(value);
			visited[minIndex]=true;
			for(int i=0;i<adjList[minIndex].size();i++)
			{
				Edge p = adjList[minIndex].get(i);
				int src = p.src;
				int dst = p.dst;
				int wt = p.wt;
				if(!visited[dst])
				{
					if(value[dst]>(value[minIndex]+wt))
					{
						value[dst]=value[minIndex]+wt;
						parent[dst]=minIndex;
					}
				}
			}
			res[minIndex] = value[minIndex];
			value[minIndex]=Integer.MAX_VALUE;
			count--;
		}
		return res;
	}
}
public class DijkstraAdjacentList
{
	
	public static void main(String[] args)
	{
		Graph myGraph = new Graph(9);
		myGraph.addEdge(0, 1, 4); 
		myGraph.addEdge(0, 7, 8); 
		myGraph.addEdge(1, 2, 8); 
		myGraph.addEdge(1, 7, 11); 
		myGraph.addEdge(2, 3, 7); 
		myGraph.addEdge(2, 8, 2); 
		myGraph.addEdge(2, 5, 4); 
		myGraph.addEdge(3, 4, 9); 
		myGraph.addEdge(3, 5, 14); 
		myGraph.addEdge(4, 5, 10); 
		myGraph.addEdge(5, 6, 2); 
		myGraph.addEdge(6, 7, 1); 
		myGraph.addEdge(6, 8, 6); 
		myGraph.addEdge(7, 8, 7);
		int[] res = myGraph.Dijkstra();
		for(int i=0;i<res.length;i++)
		{
			System.out.println("("+i+","+res[i]+")");
		}
	}
}