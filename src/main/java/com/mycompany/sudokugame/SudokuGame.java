/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.sudokugame;

/**
 *
 * @author chinm
 */
public class SudokuGame {
	        
        int sudokuBoard [][] ={
        {9,0,0,1,0,0,0,0,5},
			{0,0,5,0,9,0,2,0,1},
			{8,0,0,0,4,0,0,0,0},
			{0,0,0,0,8,0,0,0,0},
			{0,0,0,7,0,0,0,0,0},
			{0,0,0,0,2,6,0,0,9},
			{2,0,0,3,0,0,0,0,6},
			{0,0,0,2,0,0,9,0,0},
			{0,0,1,9,0,4,5,7,0},
        };
	public static final int EMPTY_CELL = 0; 
	public static final int SUDOKU_SIZE = 9; 
	
	
	private boolean checkRow(int row, int number) {
		for (int i = 0; i < SUDOKU_SIZE; i++)
			if (sudokuBoard[row][i] == number)
				return true;
		
		return false;
	}
	
	private boolean checkCol(int col, int number) {
		for (int i = 0; i < SUDOKU_SIZE; i++)
			if (sudokuBoard[i][col] == number)
				return true;
		
		return false;
	}
	
	private boolean checkSquare(int row, int col, int number) {
		int r = row - row % 3;
		int c = col - col % 3;
		
		for (int i = r; i < r + 3; i++)
			for (int j = c; j < c + 3; j++)
				if (sudokuBoard[i][j] == number)
					return true;
		
		return false;
	}
	
	private boolean checkValid(int row, int col, int number) {
		return !checkRow(row, number)  &&  !checkCol(col, number)  &&  !checkSquare(row, col, number);
	}
	
	// Solve method. We will use a recursive BackTracking algorithm.
       public boolean solveSudoku() {
        for (int row = 0; row < SUDOKU_SIZE; row++) {
         for (int col = 0; col < SUDOKU_SIZE; col++) {
          if (sudokuBoard[row][col] == EMPTY_CELL) {
            for (int number = 1; number <= SUDOKU_SIZE; number++) {
              if (checkValid(row, col, number)) {
                sudokuBoard[row][col] = number;
                if (solveSudoku()) { 
                  return true;
                } else { 
                  sudokuBoard[row][col] = EMPTY_CELL;
                }
             }
            }
            return false; 
           }
          }
         }
         return true; 
	}
	
	public void printSudoku() {
		for (int i = 0; i < SUDOKU_SIZE; i++) {
			for (int j = 0; j < SUDOKU_SIZE; j++) {
				System.out.print(" " + sudokuBoard[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		SudokuGame sudokuGame = new SudokuGame();
		System.out.println("Given Sudoku");
		sudokuGame.printSudoku();
		
		if (sudokuGame.solveSudoku()) {
			System.out.println("Solved Sudoku");
			sudokuGame.printSudoku();
		} else {
			System.out.println("Sudoku not solvable");
		}
	}

}