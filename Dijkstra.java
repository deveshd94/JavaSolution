public class Dijkstra
{
	
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
	
	public static void main(String[] args)
	{
		int graph[][] = new int[][]{{0, 4, 0, 0, 0, 0, 0, 8, 0}, 
            {4, 0, 8, 0, 0, 0, 0, 11, 0}, 
            {0, 8, 0, 7, 0, 4, 0, 0, 2}, 
            {0, 0, 7, 0, 9, 14, 0, 0, 0}, 
            {0, 0, 0, 9, 0, 10, 0, 0, 0}, 
            {0, 0, 4, 14, 10, 0, 2, 0, 0}, 
            {0, 0, 0, 0, 0, 2, 0, 1, 6}, 
            {8, 11, 0, 0, 0, 0, 1, 0, 7}, 
            {0, 0, 2, 0, 0, 0, 6, 7, 0} 
           };
           int[] parent = new int[graph.length];
           int[] value = new int[graph.length];
           boolean[] visited = new boolean[graph.length];
           int[] res = new int[graph.length];
           for(int i=0;i<graph.length;i++)
           {
        	   parent[i] = Integer.MAX_VALUE;
        	   value[i] = Integer.MAX_VALUE;
        	   res[i]=Integer.MAX_VALUE;
           }
           value[0]=0;
           parent[0]=-1;
           res[0]=0;
           visited[0]=true;
           int count = graph.length;
           while(count>0)
           {
        	   int minIndex = getMinIndex(value);
        	   visited[minIndex]=true;
        	   for(int j=0;j<graph.length;j++)
        	   {
        		   int p =graph[minIndex][j];
        		   if(p!=0 && !visited[j])
        		   {
        			   if(value[j]>p+value[minIndex])
        			   {
        				   value[j]=p+value[minIndex];
        				   parent[j]=minIndex;
        			   }
        		   }
        	   }
        	   count--;
        	   res[minIndex]=value[minIndex];
        	   value[minIndex] = Integer.MAX_VALUE;
           }
           
           for(int i=0;i<graph.length;i++)
           {
        	   System.out.println("("+i+","+res[i]+")");
           }
	}
}