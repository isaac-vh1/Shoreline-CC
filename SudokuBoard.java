// Isaac Van Horn
// CS 143
// HW Title and Core Topics: Sudoku #3
//
// This program will Set up the sudoku board, test if it is a valid board, test if it is solved, and solve the sudoku board
//
// pre: and post: comments before each method in your class
import java.io.File;
import java.util.*;
import java.io.*;
import java.io.FileNotFoundException;
public class SudokuBoard {
   private char[][] board = new char[9][9];

   //pre: needs a file name and file in order to add it to the bpard
   //post: adds all characters to the board
   public SudokuBoard(String fileName) throws FileNotFoundException {
      Scanner input = new Scanner(new File(fileName));
      int row = 0;
      int column = 0;
      String rowInput;
      while (input.hasNextLine()) {
         rowInput = input.nextLine();
         for (int i = 0; i < 9; i++) {
            board[row][i] = rowInput.charAt(i);
         }
         row++;
      }
   }
   
   
   //pre: none
   //post: returns a string for the sudoku board
   public String toString() {
      int column = 0;
      String ret = "";
      for(int rowGroup = 0; rowGroup < 3; rowGroup++) {
         for(int row = 0; row < 3; row++){
            for (int group = 0; group < 3; group++){
               ret = ret + board[row][column];
               column++;
               ret = ret + board[row][column];
               column++;
               ret = ret + board[row][column];
               column++;
               ret = ret + "   ";
            }
            column = 0;
            ret += "\n";
         }
         ret += "\n";
      }
      return ret;
   }
   //Pre: none
   //post: returns boolean if board is valid or not 
   public boolean isValid() {
      if(!rowTest()) { return false; }
      if (!columnTest()) { return false; }
      if (!miniTest()) { return false; }
      return true;
   }
   //pre: none
   //post: tests if rows are valid
   private boolean rowTest() {
      Set<Character> row = new HashSet<Character>();
      int size = 0;
      for(int r = 0; r < 9; r++) {
         for(int c = 0; c < 9; c++) {
            char test = board[r][c];
	         if (test != '.' && (test < '1' || test > '9')) { return false; }
            if(row.contains(board[r][c])){ return false; }
            else if(board[r][c] != '.'){
               row.add(board[r][c]);            
            }
            
         }
         row.clear();
      }
      return true;
   }
   //pre: if rows is invalid the method won't be called
   //post: returns if columns are valid or not
   private boolean columnTest() {
      Set<Character> column = new HashSet<Character>();
      int size = 0;
      for(int c = 0; c < 9; c++) {
         for(int r = 0; r < 9; r++) {
            if(column.contains(board[r][c])){ return false; }
            else if(board[r][c] != '.'){
               column.add(board[r][c]);            
            }
         }
         column.clear();
  
      }
      return true;
   }
   //pre: if rows and columns are invalid the method won't be called
   //post: returns if mini squares are valid or not
   private boolean miniTest() {
      Set<Character> mini = new HashSet<Character>();
      int size = 0;
      for(int i = 1; i <= 9; i++) {
         char testArray[] = miniSquare(i);
         for(int x = 0; x < testArray.length; x++){
            if(mini.contains(testArray[x])){ return false; }
            else if(testArray[x] != '.'){
               mini.add(testArray[x]);            
            }
         }
         mini.clear();
      }
      return true;
   }
   //pre: requires an int between 1 and 9 to get the mini square
   //post: returns array for the mini square spot with 9 chars
   private char[] miniSquare(int spot) {
      char[] mini = new char[9];
      int x = 0; char testDot = '.'; char num;
      for(int r = 0; r < 3; r++) {
         for(int c = 0; c < 3; c++) {
            // whoa - wild! This took me a solid hour to figure out (at least)
            // This translates between the "spot" in the 9x9 Sudoku board
            // and a new mini square of 3x3           
            mini[x] = board[(spot - 1) / 3 * 3 + r][(spot - 1) % 3 * 3 + c];
            x++;
         }
      }
      return mini;
   }
   //pre: board must be valid
   //post: returns if board is solved or not
   public boolean isSolved(){
      if(!isValid()) { return false; }
      Map<Character, Integer> solved = new HashMap <>();
      char test;
      for(int r = 0; r < 9; r++) {
         for(int c = 0; c < 9; c++) {
            test = board[r][c];
            if (test == '.') { return false; }
            else if (solved.containsKey(test)) {
               solved.put(test, solved.get(test) + 1);
            } else {
               solved.put(test, 1);
            }
         }
      }
      return solved.keySet().size() == 9 && Collections.frequency(solved.values(),9) == 9;
   }
   //pre: none
   //post: returns if board van be solved, also solves the sudoku board 
   //and stores it in the board variable
   public boolean solve() {
      boolean boardSolved = false;
      if(!isValid()) { return false; }
      else if(isSolved()) { return true; }
      for(int row = 0; row < 9; row++){
         for(int col = 0; col < 9; col++){
            if (board[row][col] == '.'){
               String x = "123456789";
               for(int i = 0; i < 9 && !boardSolved; i++){
                  char num = x.charAt(i);
                  board[row][col] = num;
                  boardSolved = solve();
               }
               if(!boardSolved) {
                  board[row][col] = '.';
               }
            }
         }
      }
      return boardSolved;
   }
}
/*
 Initial board
 .34   678   912   
 .72   195   348   
 198   342   567   
 
 .34   678   912   
 .72   195   348   
 198   342   567   
 
 .34   678   912   
 .72   195   348   
 198   342   567   
 
 
 
 Solving board...SOLVED in 0.846 seconds.
 
 534   678   912   
 672   195   348   
 198   342   567   
 
 534   678   912   
 672   195   348   
 198   342   567   
 
 534   678   912   
 672   195   348   
 198   342   567   
 
 
 true
 */

