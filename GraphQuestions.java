package graphquestions;

import java.util.Scanner;

public class GraphQuestions {
    
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        GraphImplementation graph=new GraphImplementation();
        int choice;
        do{
            System.out.print("\n1.Create Graph Using Matrix\n"
                    + "2.Create Graph using List\n3.Display Matrix Graph\n15.Exit");
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
            }
        }while (choice!=15);
    }
    
}
