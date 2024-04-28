# Sudoku Solver 
This Java class provides a Sudoku solver algorithm implemented using backtracking. It solves a given Sudoku puzzle and provides functionalities to find the solution grid, the number of solutions, and the time elapsed for solving.

### Usage
To use this Sudoku solver, you can create an instance of the Sudoku class and pass the Sudoku grid as input. You can then call the solve() method to solve the Sudoku puzzle.
### Example Usage

```java
public class Main {
    public static void main(String[] args) {
        Sudoku sudoku = new Sudoku("530070000600195000098000060800060003400803001700020006060000280000419005000080079");
        sudoku.solve();
    }
}
```

### Input 
The input to the Sudoku class constructor can be provided either as a 2D integer array representing the Sudoku grid or as a single string containing the digits of the Sudoku grid without any separators.

### Output 
 - Solution Grid: The solved Sudoku grid is printed to the console.
 - Number of Solutions: The number of solutions found for the Sudoku puzzle.
 - Time Elapsed: The time taken to solve the Sudoku puzzle, measured in milliseconds.

