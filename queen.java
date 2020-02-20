package com.xs.test;
/*********************************************************************************************
 *Author: Puan Xue Sheng
 *Title: My Own Attempt at solving the 8 queen challenge
 *Date: 20 Feb 2020
 * Comments: This is the queen class where all the relevant data about the queen is stored.
 * The queen class automatically creates an array of all coords that it can attack
 * based on the board size. This data will be use when we try to place another queen onto
 * the board.

 *********************************************************************************************/
import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class queen {
    public int X;
    public int Y;
    public String name;
    public int sizeofBoard;
    ArrayList<Integer> coordToAvoid = new ArrayList<Integer>();

    //constructor
    public queen(String name, int x , int y, int boardSize)
    {
        this.name = name;
        this. X = x;
        this.Y = y;
        this.sizeofBoard = boardSize-1; //array starts from zero
        findStraightCoord();
        findDiagCoord();
        Collections.sort(coordToAvoid);//make sure to sort the coordinates before printing
    }
    public void findStraightCoord()
    {
        for(int i = 0; i <=sizeofBoard;i++)
        {
            for(int o = 0; o<=sizeofBoard; o++)
            {
                if(i==X)
                {
                    coordToAvoid.add(Integer.parseInt(String.valueOf(i).concat(String.valueOf(o))));
                }
                else if(o==Y)
                {
                    coordToAvoid.add(Integer.parseInt(String.valueOf(i).concat(String.valueOf(o))));
                }
            }
        }
    }
    public void findDiagCoord()
    {
        for(int p=1;p<=sizeofBoard;p++)
        {
            //Check Right down
            if(X+p<=sizeofBoard && Y+p<=sizeofBoard)
            {
                coordToAvoid.add(Integer.parseInt(String.valueOf(X+p).concat(String.valueOf(Y+p))));
            }
            //Check Left up
            if(X-p>=0 && Y-p>=0)
            {
                coordToAvoid.add(Integer.parseInt(String.valueOf(X-p).concat(String.valueOf(Y-p))));
            }
            //Check right up
            if(X-p>=0 &&Y+p<=sizeofBoard )
            {
                coordToAvoid.add(Integer.parseInt(String.valueOf(X-p).concat(String.valueOf(Y+p))));
            }
            //Check left down
            if(Y-p>=0 &&X+p<=sizeofBoard )
            {
                coordToAvoid.add(Integer.parseInt(String.valueOf(X+p).concat(String.valueOf(Y-p))));
            }
        }

    }
    public void printArrToAvoid()
    {
        int count =0;
        for(int i = 0; i <= this.sizeofBoard;i++) {
            for (int y = 0; y <= this.sizeofBoard; y++) {

                if(count!=(coordToAvoid.size()))
                {
                    if (Integer.parseInt(String.valueOf(i).concat(String.valueOf(y))) == coordToAvoid.get(count))
                    {
                        System.out.print(coordToAvoid.get(count));
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
    @Override
    public String toString() {
        return name;
    }

    //end of class.........................
}
