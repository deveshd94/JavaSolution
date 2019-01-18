/*package whatever //do not write package name here */

import java.util.*;
import java.lang.*;
import java.io.*;

class Point
{
    int X;
    int Y;
    public Point(int X,int Y)
    {
        this.X = X;
        this.Y = Y;
    }
    
    public Point(Point y)
    {
    	this.X = y.X;
    	this.Y = y.Y;
    }
    
    public Stack<String> getPath(Point dst,int m,int n)
    {
    	if(dst.X>m)
    		return null;
        Queue<Point> res = new LinkedList<Point>();
        Stack<String> path = new Stack<String>();
        HashMap<String,String> parentHash = new HashMap<String,String>();
        HashMap<String,Integer> myHash = new HashMap<String,Integer>();
        res.add(this);
        parentHash.put("0,0", null);
        Point myPoint = new Point(0,0);
        Point tmp = new Point(0,0);
        int diff=0;
        while(!res.isEmpty() && (dst.X!=tmp.X || dst.Y!=tmp.Y))
        {
            tmp = res.remove();
            //System.out.println("X : "+tmp.X+"Y : "+tmp.Y);
            myHash.put(tmp.X+","+tmp.Y,1);
            //Case 1 : Empty a Jug
            myPoint.X = 0;
            myPoint.Y = tmp.Y;
            if(!(myHash.containsKey(myPoint.X+","+myPoint.Y)))
            {
            	parentHash.put(myPoint.X+","+myPoint.Y, tmp.X+","+tmp.Y);
                res.add(new Point(myPoint));
            }
            myPoint.X = tmp.X;
            myPoint.Y = 0;
            if(!(myHash.containsKey(myPoint.X+","+myPoint.Y)))
            {
            	parentHash.put(myPoint.X+","+myPoint.Y, tmp.X+","+tmp.Y);
                res.add(new Point(myPoint));
            }
            //Case 2 : Fill a Jug
            myPoint.X = tmp.X;
            myPoint.Y = n;
            if(!(myHash.containsKey(myPoint.X+","+myPoint.Y)))
            {
            	parentHash.put(myPoint.X+","+myPoint.Y, tmp.X+","+tmp.Y);
                res.add(new Point(myPoint));
            }
            myPoint.X = m;
            myPoint.Y = tmp.Y;
            if(!(myHash.containsKey(myPoint.X+","+myPoint.Y)))
            {
            	parentHash.put(myPoint.X+","+myPoint.Y, tmp.X+","+tmp.Y);
                res.add(new Point(myPoint));
            }
            //Case 3 :Transfer
            if((tmp.X+tmp.Y)>=m)
            {
                diff = m-tmp.X;
                myPoint.X = m;
                myPoint.Y = tmp.Y-diff;
                if(!(myHash.containsKey(myPoint.X+","+myPoint.Y)))
                {
                	parentHash.put(myPoint.X+","+myPoint.Y, tmp.X+","+tmp.Y);
                    res.add(new Point(myPoint));
                }
                if((tmp.X+tmp.Y)>=n)
                {
                    diff = n-tmp.Y;
                    myPoint.X = tmp.X-diff;
                    myPoint.Y = n;
                    if(!(myHash.containsKey(myPoint.X+","+myPoint.Y)))
                    {
                    	parentHash.put(myPoint.X+","+myPoint.Y, tmp.X+","+tmp.Y);
                        res.add(new Point(myPoint));
                    }
                }
            }
            if((tmp.X+tmp.Y)<m)
            {  
                myPoint.X = tmp.X+tmp.Y;//please check from here: Inputs are : 3,4,2
                myPoint.Y=0;
                if(!(myHash.containsKey(myPoint.X+","+myPoint.Y)))
                {
                	parentHash.put(myPoint.X+","+myPoint.Y, tmp.X+","+tmp.Y);
                    res.add(new Point(myPoint));
                }
            }
            if((tmp.X+tmp.Y)<n)
            {
                myPoint.X = 0;
                myPoint.Y = tmp.X+tmp.Y;
                if(!(myHash.containsKey(myPoint.X+","+myPoint.Y)))
                {
                	parentHash.put(myPoint.X+","+myPoint.Y, tmp.X+","+tmp.Y);
                    res.add(new Point(myPoint));
                }    
            }
            
    }
      if(res.isEmpty() && (dst.X!=tmp.X || dst.Y!=tmp.Y))
      {
    	  System.out.print(-1);
    	  return null;
      }
        
      String myRes=tmp.X+","+tmp.Y;
      while(myRes!=null)
      {
    	  //Extract the parent of tmp.
    	  path.push(myRes);
    	  myRes = parentHash.get(myRes);
    	  
      }
      
        return path;
}
    
    
    public Stack<String> getPath(int dst,int m,int n)
    {
        Point one = new Point(dst,0);
        Point two = new Point(0,dst);
        Stack<String> myStack1 = getPath(one,m,n);
        Stack<String> myStack2 = getPath(two,m,n);
        if(myStack2==null)
            return null;
        if(myStack1==null)
        {
            return myStack2;
        }
        if(myStack1.size()>myStack2.size())
        {
            return myStack2;
        }
        return myStack1;
    }
}


public class Solution {
	public static void main (String[] args) {
            Scanner myScanner = new Scanner(System.in);
            int queries = myScanner.nextInt();
            while(queries>0)
            {
                queries--;
                int m = myScanner.nextInt();
                int n = myScanner.nextInt();
                int d = myScanner.nextInt();
                Point initialPoint = new Point(0,0);
                Stack<String> res = initialPoint.getPath(d,m,n);
                while(!res.isEmpty())
                {
                	System.out.println(res.pop());
                }
            }
            myScanner.close();
	}        
}