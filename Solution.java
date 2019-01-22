import java.util.*;


class Graph
{
	int V;
	int[][] weightMat;
	public Graph(int Vertex)
	{
		V=Vertex;
		weightMat = new int[V][V];
	}
	public void AddEdge(int src,int dst,int weight)
	{
		weightMat[src][dst] = weight;
		weightMat[dst][src] = weight;
	}
	
	public static int getMin(int[] arr)
	{
		int min = Integer.MAX_VALUE;
		int index =0;
		for(int i=0;i<arr.length;i++)
		{
			if(arr[i]<min)
			{
				min = arr[i];
				index = i;
			}
		}
		return index;
	}
	
	public int[][] PrimMST()
	{
		boolean[] visited = new boolean[V];
		int[] arr = new int[V];
		int[] parent = new int[V];
		for(int i=0;i<V;i++)
		{
			arr[i] = Integer.MAX_VALUE;
			parent[i] = Integer.MAX_VALUE;
		}
		arr[0]=0;//My Source Vertex;
		int count=V;
		int[][] res = new int[V][V];//Include the results.
		parent[0]=-1;
		while(count>0)
		{
			count--;
			//Fetch Index of Minimum Vertex;
			int minIndex = getMin(arr);
			//Iterate through the Index and allot the values int array arr[] & put the minIndex in parent[].
			for(int i=0;i<weightMat[minIndex].length;i++)
			{
				int p = weightMat[minIndex][i];
				if(p!=0 && !visited[i])
				{
					//Change the Adjacent Matrix Values & Parent[].
					if(arr[i]>p)
					{
						//Update the Values.
						arr[i]=p;
						parent[i] = minIndex;
						
					}
				}
			}
			//Add the details in the res Matrix;
			if(parent[minIndex]!=-1)
			{
				res[minIndex][parent[minIndex]]=arr[minIndex];
				res[parent[minIndex]][minIndex]=arr[minIndex];
			}
			visited[minIndex]=true;
			//Update the minIndex.
			arr[minIndex] = Integer.MAX_VALUE;
		}
	return res;	
	}
}


public class Solution
{
	public static void main(String[] args)
	{
		Graph myGraph = new Graph(9);
		myGraph.AddEdge(0,1,4);
		myGraph.AddEdge(0,7,8);
		myGraph.AddEdge(1,2,8);
		myGraph.AddEdge(1,7,11);
		myGraph.AddEdge(2,3,7);
		myGraph.AddEdge(2,5,4);
		myGraph.AddEdge(2,8,2);
		myGraph.AddEdge(3,5,14);
		myGraph.AddEdge(3,4,9);
		myGraph.AddEdge(4,5,10);
		myGraph.AddEdge(5,6,2);
		myGraph.AddEdge(6,7,1);
		myGraph.AddEdge(6,8,6);
		myGraph.AddEdge(7,8,7);
		int[][] res = myGraph.PrimMST();
		for(int i=0;i<res.length;i++)
		{
			for(int j=0;j<res.length;j++)
			{
				System.out.print(res[i][j]+",");
			}
			System.out.println();
		}
		}
}