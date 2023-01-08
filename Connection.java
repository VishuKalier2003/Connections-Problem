/* There are n computers numbered from 0 to n - 1 connected by ethernet cables connections forming a network where connections[i] = [ai, bi] represents a connection between computers ai and bi... Any computer can reach any other computer directly or indirectly through the network... You are given an initial computer network connections... You can extract certain cables between two directly connected computers, and place them between any pair of disconnected computers to make them directly connected...Return the minimum number of times you need to do this in order to make all the computers connected... If it is not possible, return -1...
 * Eg 1:   n = 4  connections = [[0,1],[0,2],[1,2]]                                   Output = 1
 * Eg 2:   n = 6  connections = [[0,1],[0,2],[0,3],[1,2],[1,3]]                       Output = 2
 * Eg 3:   n = 6  connections = [[0,1],[0,2],[0,3],[1,2]]                             Output = -1
 */
import java.util.*;
public class Connection
{
    public int[][] SortConnections(int con[][])
    {
        for(int i = 0; i < con[0].length; i++)
        {
            for(int j = 0; j < con[0].length; j++)
            {
                if(con[0][i] < con[0][j])   // Less than sorts in ascending order...
                {
                    int temp = con[0][i];      // Sorting the 2d array by the start indices...
                    con[0][i] = con[0][j];
                    con[0][j] = temp;
                    temp = con[1][i];
                    con[1][i] = con[1][j];
                    con[1][j] = temp;
                }
            }
        }
        System.out.println("Sorted Connection Array on the basis of Stating Computers !!");
        for(int i = 0; i < con[0].length; i++)
            System.out.print("["+con[0][i]+", "+con[1][i]+"] ,");
        System.out.println();
        return con;
    }
    public void CreateConnection(int con[][], int comp)
    {
        int j = 0, i = 0, count = 0, wires = 0;
        while(j < con[0].length)   // Number of extra wires...
        {
            if(con[0][j] == i)    // Since the array is sorted, we check for each computer...
                count++;    // Counting the number of wires of the current computer...
            else if(con[0][j] != i)
            {   // Then we compute the number of wires iteratively...
                wires = wires + (count - 1);
                count = 0;
                i++;           // Moving to the next computer...
            }
            j++;
        }
        wires = wires + count;
        System.out.println("The number of Extra wires : "+wires);
        boolean start[] = new boolean[comp];    // Array of start indices...
        boolean end[] = new boolean[comp];      // Array of end indices...
        int d = 0;
        while(d < con[0].length)
        {
            if(con[0][d] < start.length)    // When the computer is connected by start...
                start[con[0][d]] = true;   // Using the connected array...
            if(con[1][d] < end.length)      // When the computer is connected by end... 
                end[con[1][d]] = true;     // Using the connected array...
            System.out.println(start[d]+", "+end[d]);
            d++;
        }
        int unconnected = 0;
        for(int loop = 0; loop < start.length; loop++)
        {   // When a Computer is not connected by either start or end, it is disconnected...
            if(start[loop] == false && end[loop] == false)
                unconnected++;
        }
        if(wires >= unconnected)    // If we have more number of wires than the disconnected computers...
            System.out.println("The number of Unconnected Computers : "+unconnected);
        else
            System.out.println("The number of Unconnected Computers : "+(-1));
    }
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int k1, k;
        System.out.print("Enter the number of Computers : ");
        k1 = sc.nextInt();
        System.out.print("Enter the number of Connections : ");
        k = sc.nextInt();
        int connect[][] = new int[2][k];
        for(int i = 0 ; i < k ; i++)
        {
            System.out.print("Enter the starting Computer index of "+(i+1)+" connection : ");
            connect[0][i] = sc.nextInt();
        }
        for(int j = 0; j < k; j++)
        {
            System.out.print("Enter the ending Computer index of "+(j+1)+" connection : ");
            connect[1][j] = sc.nextInt();
        }
        System.out.println("The Connection Array is formed !!");
        for(int i = 0 ; i < connect[0].length; i++)
            System.out.print("["+connect[0][i]+", "+connect[1][i]+"] ,");
        System.out.println();
        Connection connection = new Connection();     // Object creation...
        int sorted[][] = connection.SortConnections(connect);
        connection.CreateConnection(sorted, k1);       // Function call...
        sc.close();
    }
}

// Time Complexity  - O(n * m) time...     m is the length of connections...
// Space Complexity - O(n) space...

/* DEDUCTIONS :- 
 * 1. Since a Computer can have more than one wire connected, we check the number of available wires...
 * 2. If the number of available wires are more than the number of disconnected computers, they can be connected...
 * 3. To check iteratively we sort the array once...
*/