import java.io.BufferedReader; 
import java.io.FileReader; 
import java.io.IOException; 
import java.util.Arrays; 
 
public class MatrixMultiplication { 
    public static void main(String[] args) throws IOException { 
        if (args.length != 2) { 
            System.out.println("Usage: java MatrixMultiplication file1.csv file2.csv"); 
            return; 
        } 
 
        String file1 = args[0]; 
        String file2 = args[1]; 
 
        double[][] matrix1, matrix2; 
        try { 
            matrix1 = readMatrixFromFile(file1); 
            matrix2 = readMatrixFromFile(file2); 
        } catch (Exception exp) { 
            System.out.println(exp.getMessage()); 
            return; 
        } 
 
        int rows1 = matrix1.length; 
        int cols1 = matrix1[0].length; 
        int rows2 = matrix2.length; 
        int cols2 = matrix2[0].length; 
 
        if (cols1 != rows2) { 
            System.out.println("Error: matrices cannot be multiplied"); 
            return; 
        } 
 
        double[][] result = multiplyMatrices(matrix1, matrix2); 
 
        printNumpyMatrix(result); 
    } 
 
    private static double[][] readMatrixFromFile(String filename) throws IOException, Exception { 
        BufferedReader reader = new BufferedReader(new FileReader(filename)); 
 
        String line = reader.readLine(); 
        if (line == null) { 
            throw new Exception("Error! Empty file: " + filename); 
        } 
        String[] parts = line.split(","); 
        int cols = parts.length; 
 
        double[][] matrix = new double[10][cols]; 
        int rows = 0; 
 
        do { 
            if (rows == matrix.length) { 
                matrix = Arrays.copyOf(matrix, matrix.length * 2); 
            } 
 
            if (parts.length != cols) { 
                reader.close(); // Close the reader in case of inconsistency 
                String message = "Error! Column size is not the same in " + filename + " at line " + (rows + 1) + ", "; 
                throw new Exception(message); 
            } 
 
            for (int i = 0; i < cols; i++) { 
                matrix[rows][i] = Double.parseDouble(parts[i]); 
            } 
 
            rows++; 
 
            line = reader.readLine(); 
            if (line != null) { 
                parts = line.split(","); 
            } 
        } while (line != null); 
 
        reader.close(); // Close the reader after reading the file 
 
        return Arrays.copyOf(matrix, rows); 
    } 
 
    private static double[][] multiplyMatrices(double[][] matrix1, double[][] matrix2) { 
        int rows1 = matrix1.length; 
        int cols1 = matrix1[0].length; 
        int cols2 = matrix2[0].length; 
 
        double[][] result = new double[rows1][cols2]; 
 
        for (int i = 0; i < rows1; i++) { 
            for (int j = 0; j < cols2; j++) { 
                for (int k = 0; k < cols1; k++) { 
                    result[i][j] += matrix1[i][k] * matrix2[k][j]; 
                } 
            } 
        } 
 
        return result; 
    } 
 
    private static void printNumpyMatrix(double[][] matrix) { 
        int rows = matrix.length; 
        int cols = matrix[0].length; 
 
        System.out.print("["); 
        for (int i = 0; i < rows; i++) { 
            System.out.print("["); 
            for (int j = 0; j < cols; j++) { 
                System.out.print(matrix[i][j]); 
                if (j < cols - 1) { 
                    System.out.print(","); 
                } 
            } 
            System.out.print("]"); 
            if (i < rows - 1) { 
                System.out.println(","); 
            } else { 
                System.out.println("]"); 
            } 
        } 
    } 
}