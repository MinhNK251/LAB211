package util;

public class SeatsMatrixUtil {
    public static void printMatrix (boolean[][] matrix) {
        int rowSize = matrix.length, colSize = matrix[0].length;
        for (float i = -1f; i < rowSize; i += .5f) {
            for (float j = -1f; j < colSize; j++) {
                if (j == 3f) System.out.print("     ");
                
                if ((i < -.5f || j < 0)) printCellPos(i, j);
                else printCell(i, j, matrix);
            }
            System.out.println();
        }
    }
    
    private static void printCellPos (float i, float j) {
        if (j < 0f && i % 1 == 0 && i >= 0) System.out.printf("   %.0f\t", i + 1);
        else if (i == -1 && j >= 0) System.out.printf("  %s  ", (char)((int)'A' + j));
        else System.out.print("\t");
    }
    
    private static void printCell (float i, float j, boolean[][] matrix) {
        if (i % 1 == 0) System.out.printf("| %s |", (matrix[(int)i][(int)j])? "X": " ");
        else if (i % 1 != 0) System.out.print("-----");
    }
    
    public static String seatNumToString (int seatNum) {
        int row = seatNum / 6, col = seatNum % 6;
        StringBuilder seatString = new StringBuilder();
        seatString.append(row + 1).append((char)('A' + col));
        return seatString.toString();
    }
    
    public static int seatStringToNum (String seatString) {
        int row, col;
        row = Integer.parseInt(seatString.substring(0, seatString.length() - 1)) - 1;
        col = (seatString.substring(seatString.length() - 1)).charAt(0) - 'A';
        return row * 6 + col;
    }
}
