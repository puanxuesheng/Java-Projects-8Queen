/*********************************************************************************************
 *Author: Puan Xue Sheng
 *Title: My Own Attempt at solving the 8 queen challenge
 *Date: 20 Feb 2020
 * Comments: The solution uses back tracking and recursion to solve the problem. You can either select a solution
 * that you want to output or set the solution number to 9999999999999 to make the algorithm count the number of
 * possible solutions.
 *
 * The solution places the queen in the chess board in a row by row fashion.
 * Step 1: place queen in chess board
 * Step 2: Check where previous queen was place
 * Step 3: Check where are the possible coords left for current queen.
 * Step 4: Iterate through possible coords and place the next queen by recursion

 *********************************************************************************************/
package com.xs.test;

import java.util.ArrayList;
import java.util.Scanner;

public class myEightQueens {
    public static int SIZEOFBOARD = 8;
    public static queen[][] chessBoardQueen = new queen[SIZEOFBOARD][SIZEOFBOARD];
    public static int count = 0;
    public static int solution =0;


    public static void main(String[] args)
    {
        queen[][] Queen8 = chessBoardQueen;
        queen[] QueenTest = new queen[SIZEOFBOARD];
        boolean stop = false;

        //set the solution u want. Put 9999999999 to force program to count number of possible solution
        solution = 1;

        for(int i=0; i<SIZEOFBOARD;i++)
        {
            QueenTest[0] = new queen("Ash0",0,i, SIZEOFBOARD);
            stop = recursiveCall(0,QueenTest);
            if(stop)
                break;
        }
        System.out.println(count);

        if(count == solution)
        {
            //print queen position
            for(queen oneQueen : QueenTest)
            {
                Queen8[oneQueen.X][oneQueen.Y] = oneQueen;
            }
            printChessBoard(Queen8);
        }
        else
            System.out.println("you have found "+ count+" possible solutions");

     //end of void main
    }
    public static<T> void printChessBoard(T[][] data)
    {
        for(T[] subArr : data)
        {
            for(T queen: subArr)
            {
                try{
                    System.out.print(queen.toString()+"\t");
                }
                catch(NullPointerException e)
                {
                    System.out.print(".....\t");
                }

            }
            System.out.println();
        }
    }
    public static void printAreaThatisUsed(ArrayList<Integer> arr)
    {
        int count =0;
        for(int i = 0; i < SIZEOFBOARD; i++) {
            for (int y = 0; y < SIZEOFBOARD; y++) {

                if(count!=(arr.size()))
                {
                    if (Integer.parseInt(String.valueOf(i).concat(String.valueOf(y))) == arr.get(count))
                    {
                        System.out.print(arr.get(count));
                        count++;
                    }
                    else {
                        System.out.print("..");
                    }
                }
                else {
                    System.out.print("..");
                }
                System.out.print("\t");
            }
            System.out.println();
        }
    }
    public static boolean recursiveCall(int prequeenNo, queen[] QueenTest)
    {
        boolean found =false;
        boolean finish = false;
        int currQueenNo = prequeenNo +1;
        ArrayList<Integer> yAvailble = new ArrayList<>();

        //Check which coord is available for you to put queen in
        for (int z = (currQueenNo * 10); z < ((currQueenNo * 10) + SIZEOFBOARD); z++)
        {
                        //System.out.println(QueenTest[currQueenNo - 1].coordToAvoid.contains(z));
                        for(int p =currQueenNo; p>=1;p--)
                        {
                            if (!(QueenTest[p - 1].coordToAvoid.contains(z)))
                            {
                                if(!yAvailble.contains(z))
                                {
                                    yAvailble.add(z);
                                }
                            }
                            else
                            {
                                if(yAvailble.contains(z))
                                {
                                    yAvailble.remove(yAvailble.indexOf(z));
                                }
                                break;
                            }
                        }
                    }

        // processing data from coords in yAvailable
                if(yAvailble.size()!=0)
                {
                    if(currQueenNo == SIZEOFBOARD -1)
                    {

                        found = true;
                        //finish = false;
                        count++;
                        if(count == solution)
                        {
                            finish = true;
                        }
                            if(finish)
                            {
                                QueenTest[currQueenNo] = new queen("Ash0",Integer.parseInt(yAvailble.get(0).toString().substring(0,1)),
                                        Integer.parseInt(yAvailble.get(0).toString().substring(1)), SIZEOFBOARD);
                            }
                    }
                    else
                    {
                        for(int i:yAvailble)
                        {
                            QueenTest[currQueenNo] = new queen("Ash0",Integer.parseInt(String.valueOf(i).substring(0,1)),
                                        Integer.parseInt(String.valueOf(i).substring(1)), SIZEOFBOARD);
                            found = recursiveCall(currQueenNo,QueenTest);
                            if(found)
                            {
                                finish = true;
                                break;
                            }
                        }
                    }

                }
        //end of recursive call
        return found&finish;
    }
    //end of class
}











//ALL OTHER CODE FOR TESTING PURPOSE
//////////////////////////////////////////////////////////////////////////
//        //combine list of coords that cannot be used.
//        for (Integer x : Queen8[1][1].coordToAvoid){
//            if (!Queen8[0][0].coordToAvoid.contains(x))
//                Queen8[0][0].coordToAvoid.add(x);
//        }
//        Collections.sort(Queen8[0][0].coordToAvoid);
//
//                //combine list of coords that cannot be used.
//        for (Integer x : QueenTest[1].coordToAvoid){
//            if (!QueenTest[0].coordToAvoid.contains(x))
//                QueenTest[0].coordToAvoid.add(x);
//        }
//        Collections.sort(QueenTest[0].coordToAvoid);
//        QueenTest[0].printArrToAvoid();

//System.out.println(QueenTest[0].coordToAvoid.size());

/////////////////////////////////////////////////////////////////


//check what numbers within a row is still available for you to put the number
//        for(int i = 1; i< 2;i++)
//        {
//           for(int y= (i*10);y<((i*10)+SizeofBoard);y++)
//           {
//
//               System.out.println(QueenTest[i-1].coordToAvoid.contains(y));
//           }
//        }
//        QueenTest[0].printArrToAvoid();
//        Queen8[0][0] = new queen("Ash0",0,0,SizeofBoard);
//        Queen8[1][1]= new queen("Ash1",1,1,SizeofBoard);
//        Queen8[2][2]= new queen("Ash2",2,2,SizeofBoard);
//        Queen8[3][3]= new queen("Ash3",3,3,SizeofBoard);
//        Queen8[4][4]= new queen("Ash4",4,4,SizeofBoard);
//        Queen8[5][5]= new queen("Ash5",5,5,SizeofBoard);
//        Queen8[6][6]= new queen("Ash6",6,6,SizeofBoard);
//        Queen8[7][7]= new queen("Ash7",7,7,SizeofBoard);

//recursion search to find the 8 queen location based on ...............
//        for(int i = 0; i <SizeofBoard;i++)
//        {
//            for(int y=0;y<SizeofBoard;y++)
//            {
//                QueenTest[i] = new queen("Ash0",i,y,SizeofBoard);
//
//            }
//        }