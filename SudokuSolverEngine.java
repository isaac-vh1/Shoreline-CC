// Isaac Van Horn
// CS 143
// HW Title and Core Topics: Sudoku #3
//
// This program will Set up the sudoku board, test if it is a valid board, test if it is solved, and solve the sudoku board
import java.io.*;
public class SudokuSolverEngine {

   public static void main(String[] args) throws FileNotFoundException {
      SudokuBoard board = new SudokuBoard("boards/fast-solve.sdk");
      System.out.println("Initial board");
      System.out.println(board);
      System.out.println();

      System.out.print("Solving board...");
      long start = System.currentTimeMillis();
      board.solve();
      long stop = System.currentTimeMillis();
      System.out.printf("SOLVED in %.3f seconds.\n", ((stop-start)/1000.0));
      System.out.println();
      System.out.println(board);
      System.out.println(board.solve());
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
      
   }
}
*/