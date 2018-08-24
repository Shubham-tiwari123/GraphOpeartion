package graphquestions;

import java.util.Queue;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Iterator;

class Node{
    int data;
    Node next;
    Node(int key){
        data=key;
        next=null;
    }
}

public class GraphImplementation {
    Scanner sc=new Scanner(System.in);
    int matA[][]=new int[10][10];
    int vertex; 
    Node[] A=new Node[20];
    int closureMatrix[][] = new int[10][10];
    int checked[] = new int[20];
    LinkedList<Integer> adjList[] =new LinkedList[20];
   // LinkedList<Integer> list=new LinkedList<>();
    
    void graphUsingMatrix(){
        
        System.out.print("\nVertex:-");
        vertex=sc.nextInt();
        
        for(int i=0;i<vertex;i++){
            for(int j=0;j<vertex;j++)
                matA[i][j]=0;
        }
        
        System.out.print("\nNo of Edges:-");
        int edges=sc.nextInt();
        for(int i=0;i<edges;i++){
            System.out.print("\nEdge("+i+")");
            int vertex1=sc.nextInt();
            int vertex2=sc.nextInt();
            matA[vertex1][vertex2]=1;
        }
    }
    
    void displayMatrixGraph(){
        System.out.print("\nDisplay the graph matrix representation:-");
        System.out.print("\n\n   ");
        for(int i=0;i<vertex;i++)
            System.out.print(i+") ");
        
        for(int i=0;i<vertex;i++){
            System.out.print("\n"+i+") ");
            for(int j=0;j<vertex;j++)
                System.out.print(matA[i][j]+"  ");
        }
    }
    
    void graphUsingList(){
        System.out.print("\nVertex:-");
        vertex=sc.nextInt();
        
        for(int i=0;i<vertex;i++){
            A[i] = new Node(i);
        }
        System.out.print("\nEdeges:-");
        int edges=sc.nextInt();
        for(int i=0;i<edges;i++){
            System.out.print("\nEdge("+i+"):-");
            int vertex1=sc.nextInt();
            int vertex2=sc.nextInt();
            
            Node newnode= new Node(vertex2);
            if(A[vertex1].next==null)
                A[vertex1].next=newnode;
            else{
                Node temp=A[vertex1];
                while(temp.next!=null)
                    temp=temp.next;
                temp.next=newnode;
            }
        }
    }
    
    void displayListGraph(){
        for(int i=0;i<vertex;i++){
            System.out.print("\n"+i+")->");
            Node temp=A[i].next;
            while(temp!=null){
                System.out.print(temp.data+" ");
                temp=temp.next;
            }
        }
    }
    
    void BFS(int startVertex){
        Queue<Integer> q = new LinkedList<>();
        int[] visited=new int[vertex];
        for(int i=0;i<vertex;i++)
            visited[i]=0;
        q.add(A[startVertex].data);
        while(!q.isEmpty()){
            int s=q.poll();
            Node temp=A[s];
            visited[s]=1;
            System.out.print(s+" ");
            while(temp!=null){
                if(visited[temp.data]==0){
                    visited[temp.data]=1;
                    q.add(temp.data);
                }
                temp=temp.next;
            }
        }
    }
    
    void motherVertex(){
        Queue<Integer> q= new LinkedList<>();
        int s=0,flag=0;
        int[] visited = new int[vertex];
        for(int i=0;i<vertex;i++)
            visited[i]=0;
        int k=0;
        do{
            q.add(A[k].data);
            while(!q.isEmpty()){
                s=q.poll();
                Node temp=A[s];
                visited[s]=1;
                while(temp!=null){
                    if(visited[temp.data]==0){
                        visited[temp.data]=1;
                        q.add(temp.data);
                    }
                    temp=temp.next;
                }
            }
            for(int i=0;i<vertex;i++){
                if(visited[i]==1){
                    flag=1;
                }
                else{
                    flag=0;
                    for(int j=0;j<vertex;j++)
                        visited[j]=0;
                    k++;
                    break;
                }
            }
        }while(flag!=1);
        System.out.println("\nParent Vertex:-"+k);
    }
    
    void transitiveClosure(){
        int flag=0;
        int s=0;
        for(int i=0;i<vertex;i++){
            for(int j=0;j<vertex;j++){
                closureMatrix[i][j]=0;
            }
        }
        
        Queue<Integer> q = new LinkedList<>();
        int[] visited=new int[vertex];
        for(int i=0;i<vertex;i++)
            visited[i]=0;
        
        for(int k=0;k<vertex;k++){
            q.add(A[k].data);
            while(!q.isEmpty()){
                s=q.poll();
                Node temp=A[s];
                visited[s]=1;
                closureMatrix[k][s]=1;
                while(temp!=null){
                    if(visited[temp.data]==0){
                        visited[temp.data]=1;
                        q.add(temp.data);
                        closureMatrix[k][temp.data]=1;
                    }
                    temp=temp.next;
                }
            }
            for(int i=0;i<vertex;i++)
                visited[i]=0;
        }
        System.out.print("\nResult:-");
        for(int i=0;i<vertex;i++){
            System.out.print("\n");
            for(int j=0;j<vertex;j++){
                System.out.print(closureMatrix[i][j]+" ");
            }
        }
    }
    void initialize(){
        for(int i = 0; i < vertex ; i++){
                adjList[i] = new LinkedList<>();
            }
    }
    void undirectedGraph(int vertex1,int vertex2){
        adjList[vertex1].addFirst(vertex2);
        adjList[vertex2].addFirst(vertex1);
    }
    void display(){
        for(int v = 0; v < vertex; v++)
        {
            System.out.print(v+")");
            for(Integer pCrawl: adjList[v]){
                System.out.print("->"+pCrawl);
            }
            System.out.print(" Length"+adjList[v].size());
            System.out.println("\n");
        }
    }
    void k_cores(int k){
        int count,flag=0;
        for(int i=0;i<vertex;i++)
            checked[i]=0;
        do{
            for(int i=0;i<vertex;i++){
                if(adjList[i].size()<k){
                    adjList[i].clear();
                   // System.out.print("\n "+i);
                    deleteValue(i);
                    //display();
                }
                else{
                    checked[i]=1;
                }
            }
            for(int i=0;i<vertex;i++){
                if(checked[i]==1)
                    flag=1;
                else{
                    flag=0;
                    break;
                }
            }
        }while(flag!=1);
            
    }
    
    void deleteValue(int num){
        Iterator<Integer> itr;
        for(int i = 0; i < vertex; i++){
           // System.out.print(i+" ");
            itr=adjList[i].listIterator();
            while(itr.hasNext()){
                if(itr.next()==num){
                    adjList[i].remove();
                    //itr.remove();
                    checked[i]=0;
                }
            }
        }
    }
}
