package graphquestions;

import java.util.Scanner;

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
    Node[] A=new Node[10];
    
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
        System.out.print("\nEdeges");
        int edges=sc.nextInt();
        for(int i=0;i<edges;i++){
            System.out.print("\nEdge("+i+")");
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
}
