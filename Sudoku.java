public class Sudoku {
    private int[][] grid;
    final static int DIMENSION = 9;
    private int number_of_solution = 0;
    private boolean first_result = true;
    public int[][] solution = new int[DIMENSION][DIMENSION];
    private  long start_time;
    private long end_time;


    public Sudoku(int[][] grid){
        this.grid = new int[DIMENSION][DIMENSION];

        for (int i=0; i<grid.length; i++){
            for (int k=0; k<grid[0].length; k++){
                this.grid[i][k] = grid[i][k];
            }
        }
    }

    // text is string of not separated initial sudoku table values
    public Sudoku(String text){
        this.grid = new int[DIMENSION][DIMENSION];
        try {
            String line_text = to_string(text);
            String[] lines = line_text.split("\n");
            for (int i = 0; i < DIMENSION; i++) {
                for (int k = 0; k < DIMENSION; k++) {
                    this.grid[i][k] = Character.getNumericValue(lines[i].charAt(k));
                }
            }
        }catch (Exception e){
            this.grid= null;
            System.out.println("Input can not be parsed into Sudoku grid");
        }

    }

    private static String to_string(String text){
        StringBuilder res = new StringBuilder();
        for (int i=0; i<DIMENSION; i++){
            res.append(text.substring(i*DIMENSION, (i+1)*DIMENSION));
            res.append("\n");
        }
        return res.toString();
    }

    private boolean row_check(int a, int row){
        for (int i=0; i<DIMENSION; i++){
            if (this.grid[row][i] == a)
                return false;
        }
        return true;
    }

    private boolean column_check( int a, int column){
        for (int[] row : this.grid) {
            if (row[column] == a)
                return false;
        }
        return true;

    }
    private boolean box_check(int a, int row, int column){
        int box_row  = (int) Math.floor((double) row /3);
        int box_col = (int) Math.floor((double) column /3);
        for (int i=box_row*3; i<(box_row+1)*3; i++){
            for (int k=box_col*3; k<(box_col+1)*3; k++){
                if (this.grid[i][k] == a)
                    return false;
            }
        }
        return true;

    }

    private void back_track( int row, int column ) {
        // manual copy is faster btw
        if (row == DIMENSION ) {
            this.number_of_solution++;

            if (this.first_result) {
                this.first_result = false;
                for (int i = 0; i < this.grid.length; i++) {
                    System.arraycopy(this.grid[i], 0, this.solution[i], 0, this.grid[0].length);
                }
            }


        }
        else if (this.grid[row][column] == 0 ) {
            for (int i = 1; i <= DIMENSION; i++) {
                if (row_check( i, row) && column_check( i, column)
                        && box_check(i, row, column)) {

                    this.grid[row][column] = i;

                    if (column<8)
                        back_track( row, column+1);
                    else {
                        back_track( row+1, 0);
                    }
                    this.grid[row][column] = 0;
                }
            }
        }else {
            if (column<8)
                back_track( row, column+1);
            else {
                back_track( row+1, 0);
            }
        }
    }



    // number of solutions and sets the state for getSolutionText() and getElapsed()
    public void solve() {
        this.number_of_solution   = 0;
        this.start_time = System.currentTimeMillis();
        if(this.grid!=null)
            this.back_track(0,0);
        this.end_time = System.currentTimeMillis();

        System.out.println("\nSolution Grid:");
        print_grid(this.solution);

        System.out.println("\nNumber of solutions: " + this.number_of_solution);
        System.out.println("Time elapsed:"+ this.getElapsed());
    }

    private long getElapsed(){
        return this.end_time-this.start_time;
    }

    public static void print_grid(int [][] grid){
        for (int i =0; i<grid.length; i++){
            for (int k=0; k<grid[0].length; k++){
                System.out.print(grid[i][k]+" ");
            }
            System.out.println();
        }
    }





    public static void main(String[] args) {
        // test
        Sudoku s = new Sudoku("530070000600195000098000060800060003400803001700020006060000280000419005000080079");
        s.solve();


    }

}
