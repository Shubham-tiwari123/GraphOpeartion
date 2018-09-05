package graphquestions;

import java.util.Scanner;

public class GraphQuestions {
    
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        GraphImplementation graph=new GraphImplementation();
        int choice;
        do{
            System.out.print("\n1.Create Graph Using Matrix\n"
                    + "2.Create Directed Graph using List\n3.Display Matrix Graph\n"
                    + "4.Display List Graph\n5.BFS\n6.Mother vertex\n"
                    + "7.Transitive closure of a graph\n8.K-cores\n"
                    + "9.Count path\n10.Stepping Number\n"
                    + "11Roots of a tree which give minimum height\n"
                    + "12.Detect cycle in an directed graph\n"
                    + "13.Detect cycle in an undirected graph\n"
                    + "14.Detect Cycle in a directed graph using colors\n"
                    + "15.Check if there is a cycle with odd weight sum in an undirected graph\n"
                    + "16.Cycles of length n in an undirected and connected graph\n17.Exit");
            System.out.print("\nChoice:-");
            choice=sc.nextInt();
            switch(choice){
                case 1: graph.graphUsingMatrix();
                        break;
                case 2: graph.graphUsingList();
                        break;
                case 3: graph.displayMatrixGraph();
                        break;
                case 4: graph.displayListGraph();
                        break;
                case 5: System.out.print("\nEnter start vertex:-");
                        int vertex=sc.nextInt();
                        graph.BFS(vertex);
                        break;
                case 6: graph.motherVertex();
                        break;
                case 7: graph.transitiveClosure();
                        break;
                case 8: graph.vertex=9;
                        graph.initialize();
                        graph.undirectedGraph(0,1);
                        graph.undirectedGraph(0,2);
                        graph.undirectedGraph(1,2);
                        graph.undirectedGraph(1,5);
                        graph.undirectedGraph(2,3);
                        graph.undirectedGraph(2,4);
                        graph.undirectedGraph(2,5);
                        graph.undirectedGraph(2,6);
                        graph.undirectedGraph(3,4);
                        graph.undirectedGraph(3,6);
                        graph.undirectedGraph(3,7);
                        graph.undirectedGraph(4,6);
                        graph.undirectedGraph(4,7);
                        graph.undirectedGraph(5,6);
                        graph.undirectedGraph(5,8);
                        graph.undirectedGraph(6,7);
                        graph.undirectedGraph(6,8);
                        System.out.print("\nEnter maximum edge for one node:-");
                        int edge=sc.nextInt();
                        graph.k_cores(edge);
                        graph.displayListGraph();
                        break;
                        
                case 9: graph.vertex=4;
                        graph.initializeArray();
                        graph.addEdge(0, 1);
                        graph.addEdge(0, 2);
                        graph.addEdge(0, 3);
                        graph.addEdge(2, 0);
                        graph.addEdge(2, 1);
                        graph.addEdge(1, 3);
                        int countPath=graph.countPath(2, 3, graph.visitedArray, 0);
                        System.out.print("\nNumber of path possible:-"+countPath);
                        break;
                        
                case 10:System.out.print("\nEnter n and m value:-");
                        int n =sc.nextInt();
                        int m = sc.nextInt();
                        for(int i=n;i<=m;i++){
                            if(graph.steppingNumber(n, m))
                                System.out.print(i+" ");
                        }
                        break;
                        
                case 11:graph.vertex=6;
                        graph.initialize();
                        graph.undirectedGraph(0, 3);
                        graph.undirectedGraph(1, 3);
                        graph.undirectedGraph(4, 3);
                        graph.undirectedGraph(2, 3);
                        graph.undirectedGraph(5, 4);
                        System.out.print("\n");
                        graph.minimumHeight();
                        break;

		case 12:graph.graphUsingList();
                        graph.detectCycle();
                        break;
                         
                case 13:graph.graphUsingList();
                        graph.displayListGraph();
                        graph.detectCylceUndirectedGraph();
                        break;
                        
                case 14:graph.graphUsingList();
                        graph.displayListGraph();
                        graph.checkLoopUsingColor();
                        break;

		case 15:graph.vertex=4;
                        graph.intializeWeight();
                        graph.createGraphUsingWeight(1, 2, 12);
                        graph.createGraphUsingWeight(2, 3, 1);
                        graph.createGraphUsingWeight(4, 3, 1);
                        graph.createGraphUsingWeight(4, 1, 20);
                        graph.displayWeightedGraph();
                        if(graph.isOddSum())
                            System.out.print("\nNo");
                        else
                            System.out.print("\nYes");
                        break;
                        
                case 16:graph.vertex=5;    
                        graph.initialize();
                        graph.undirectedGraph(0, 1);
                        graph.undirectedGraph(1, 2);
                        graph.undirectedGraph(2, 3);
                        graph.undirectedGraph(3, 4);
                        graph.undirectedGraph(4, 1);
                        graph.undirectedGraph(0, 3);
                        graph.numberOfLoop();
                        break;
            }
        }while (choice!=17);
    }
    
}
