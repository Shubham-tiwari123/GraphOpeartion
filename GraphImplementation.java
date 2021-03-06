package graphquestions;

import java.util.Queue;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Arrays;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Collections;
class Node{
    int data;
    Node next;
    Node(int key){
        data=key;
        next=null;
    }
}

class NodeWeight{
    int key;
    int weight;
    NodeWeight link;
    NodeWeight(int data,int weight) {
       key =data;
       this.weight=weight;
       link=null;
    }  
}

public class GraphImplementation {
    Scanner sc=new Scanner(System.in);
    int matA[][]=new int[10][10];
    int vertex; 
    Node[] A=new Node[20];
    int closureMatrix[][] = new int[10][10];
    int checked[] = new int[20];
    LinkedList<Integer> adj[];
    boolean visitedArray[] = new boolean[10];
    
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
        for(int i=0;i<vertex;i++){
            A[i] = new Node(i);
            checked[i]=0;
        }
    }
    
    void undirectedGraph(int vertex1,int vertex2){
        
        Node newnode= new Node(vertex2);
        if(A[vertex1].next==null)
            A[vertex1].next=newnode;
        else{
            Node temp=A[vertex1];
            while(temp.next!=null)
                temp=temp.next;
            temp.next=newnode;
        }
        
        Node newnode2= new Node(vertex1);
        if(A[vertex2].next==null)
            A[vertex2].next=newnode2;
        else{
            Node temp=A[vertex2];
            while(temp.next!=null)
                temp=temp.next;
            temp.next=newnode2;
        }
        
    }
    void k_cores(int k){
        int flag=0,count;
        do{
            for(int i=0;i<vertex;i++){
                count=0;
                Node temp=A[i].next;
                while(temp!=null){
                    count++;
                    temp=temp.next;
                }

                if(0<count && count<k){
                    deleteValue(i);
                }
                if(count==0){
                    checked[i]=1;
                }
                if(count>=k){
                    checked[i]=1;
                }
            }
            for(int i=0;i<vertex;i++){
                if(checked[i]==1){
                    flag=1;
                }
                else{
                    flag=0;
                    for(int j=0;j<vertex;j++){
                        checked[j]=0;
                    }
                    break;
                }
            }
        }while(flag!=1);
        
    }
    
    void deleteValue(int num){
        int count=0;
        Node temp1,temp2=null,temp3=null;
        for(int i=0;i<vertex;i++){
            count=0;
            Node temp=A[i].next;
            while(temp!=null){
                count++;
                if(temp.data==num){
                    if(count==1){
                        A[i].next=temp.next;
                        temp.next=null;
                        break;
                    }
                    else if(temp.next==null){
                        temp1=A[i].next;
                        while(temp1.next!=null){
                            temp2=temp1;
                            temp1=temp1.next;
                        }
                        temp2.next=null;
                        break;
                    }
                    else{
                        temp3.next=temp.next;
                        temp.next=null;
                        break;
                    }
                }
                temp3=temp;
                temp=temp.next;
            }
        }
    }
    
    void initializeArray(){
        adj = new LinkedList[vertex];
        for (int i = 0; i < vertex; ++i)
            adj[i] = new LinkedList<>();
        Arrays.fill(visitedArray, false);
    }
    
    void addEdge(int vertex1,int vertex2){
        adj[vertex1].add(vertex2);
    }
    
    int countPath(int source,int destination,boolean visited[],int pathCount){
        visited[source]=true;
        if(source==destination)
            pathCount++;
        else{
            Iterator<Integer> itr=adj[source].listIterator();
            while (itr.hasNext()){
                int n = itr.next();
                if (!visited[n]){
                    pathCount = countPath(n, destination,visited,pathCount);
                }
            }
        }
        visited[source] = false;
        return pathCount;
    }
    
    boolean steppingNumber(int n,int m){
        int prevDigit = -1;
        while(n>0){
            int currentDigit=n%10;
            if(prevDigit!=-1){
                if(Math.abs(currentDigit-prevDigit)!=1)
                    return false;
            }
            n=n/10;
            prevDigit=currentDigit;
        }
        return true;
    }
    
    void minimumHeight(){
        ArrayList<Integer> a= new ArrayList<>();
        int count;
        for(int i=0;i<vertex;i++){
            count=0;
            Node temp=A[i].next;
            while(temp!=null){
                count++;
                temp=temp.next;
            }
            a.add(count);
            
        }
        Collections.sort(a,Collections.reverseOrder());
        int max1=a.remove(0);
        int max2=a.remove(0);
        for(int i=0;i<vertex;i++){
            count=0;
            Node temp=A[i].next;
            while(temp!=null){
                count++;
                temp=temp.next;
            }
            if(count==max1||count==max2){
                System.out.print("Root:-"+i);
            }
        }
    }

    void detectCycle(){
        boolean flag=false;
        for(int i=0;i<vertex;i++){
            Node temp=A[i].next;
            while(temp!=null){
                if(i==temp.data){
                    flag=true;
                    System.out.print("\nGraph contains loop");
                    break;
                }
                temp=temp.next;
            }
        }
        if(flag==false)
            System.out.print("\nGraph does not contain loop");
    }
    
    void detectCylceUndirectedGraph(){
        boolean flag=false;
        for(int i=0;i<vertex;i++){
            Node temp=A[i].next;
            Node temp1;
            while(temp!=null){
                temp1=A[temp.data].next;
                while(temp1!=null){
                    if(i==temp1.data){
                        flag=true;
                        break;
                    }
                    temp1=temp1.next;
                }
                if(flag){
                    System.out.print("\nGraph contains loop");
                    break;
                }
                temp=temp.next;
            }
        }
        if(flag==false){
            System.out.print("\n Graph does not contain loop");
        }
    }

    void checkLoopUsingColor(){
        boolean flag=false;
        String color[] = new String[vertex];
        for(int i=0;i<vertex;i++)
            color[i]="WHITE";
        
        for(int i=0;i<vertex;i++){
            Node temp=A[i].next;
            while(temp!=null){
                if(i==temp.data){
                    color[temp.data]="GREY";
                }
                temp=temp.next;
            }
        }
        for(int i=0;i<vertex;i++){
            if(color[i]=="GREY"){
                System.out.print("\nGraph Contains loop");
                break;
            }
            else
                flag=true;
        }
        if(flag)
            System.out.print("\nNo loop");
    }
    
    void intializeWeight(){
        for(int i=1;i<=vertex;i++){
            B[i]=new NodeWeight(i,0);
        }
    }
    
    void createGraphUsingWeight(int vertex1,int vertex2,int weight){
        //NodeWeight tempVar;
        NodeWeight newnode= new NodeWeight(vertex2,weight);
        if(B[vertex1].link==null)
            B[vertex1].link=newnode;
        else{
            NodeWeight temp=B[vertex1];
            while(temp.link!=null)
                temp=temp.link;
            temp.link=newnode;
        }
        
        NodeWeight newnode2= new NodeWeight(vertex1,weight);
        if(B[vertex2].link==null)
            B[vertex2].link=newnode2;
        else{
            NodeWeight temp=B[vertex2];
            while(temp.link!=null)
                temp=temp.link;
            temp.link=newnode2;
        }
    }
    void displayWeightedGraph(){
        for(int i=1;i<=vertex;i++){
            NodeWeight temp=B[i].link;
            while(temp!=null){
                System.out.print("\n"+i+")->");
                System.out.print(temp.key+" "+temp.weight);
                temp=temp.link;
            }
        }
    }
    boolean isOddSum(){
        int visitedNode[]=new int[vertex+1];
        for(int i=1;i<=vertex;i++)
            visitedNode[i]=0;
        Queue<Integer> q=new LinkedList<>();
        
        for(int i=1;i<=vertex;i++){
            NodeWeight temp=B[i].link;
            while(temp!=null){
                if(visitedNode[temp.key]==0){
                    q.add(temp.weight);
                    visitedNode[temp.key]=1;
                    temp=temp.link;
                }
                else
                    temp=temp.link;
            }
        }
        int sum=0;
        while(!q.isEmpty()){
            sum=sum+q.poll();
        }
        if(sum%2==0)
            return false;
        return true;
    }
    
    void numberOfLoop(){
        Queue<Integer> q = new LinkedList<>();
        Queue<Integer>q1=new LinkedList<>();
        int visitedNode[]=new int[vertex];
        
        for(int i=0;i<vertex;i++){
            q1.add(i);
        }
        
        while(!q1.isEmpty()){
            for(int i=0;i<vertex;i++){
                visitedNode[i]=0;
            }
            for(int i=0;i<vertex;i++){
                Node temp=A[i].next;
                while(temp!=null){
                    if(visitedNode[temp.data]==0){
                    q.add(temp.data);
                    visitedNode[temp.data]=1;
                    temp=temp.next;
                }
                else
                    temp=temp.next;
                }
            }
            while(!q.isEmpty()){
                System.out.print(q.poll()+" ");
            }
            q1.poll();
        }   
    }
}
