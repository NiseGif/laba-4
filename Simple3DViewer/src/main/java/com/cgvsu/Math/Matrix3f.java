package com.cgvsu.Math;

import java.util.Arrays;

public class Matrix3f {
    private final float[][] matrix;

    public Matrix3f() {
        matrix = new float[3][3];
    }

    public Matrix3f(float[][] matrix) {
        this.matrix = matrix;
    }

    public Matrix3f addition(Matrix3f first, Matrix3f second) {
        if (first.matrix.length != second.matrix.length || first.matrix[0].length != second.matrix[0].length) {
            throw new IllegalArgumentException("Matrices must have the same dimensions");
        }
        float[][] result = new float[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                result[i][j] = first.matrix[i][j] + second.matrix[i][j];
            }
        }
        return new Matrix3f(result);
    }

    public Matrix3f subtraction(Matrix3f first, Matrix3f second) {
        if (first.matrix.length != second.matrix.length || first.matrix[0].length != second.matrix[0].length) {
            throw new IllegalArgumentException("Matrices must have the same dimensions");
        }
        float[][] result = new float[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                result[i][j] = first.matrix[i][j] - second.matrix[i][j];
            }
        }
        return new Matrix3f(result);
    }

    public Matrix3f multiplicationByVector(Matrix3f matrix, Matrix3f columnVector) {
        if (matrix.matrix[0].length != columnVector.matrix.length) {
            throw new IllegalArgumentException("Matrices cannot be multiplied");
        }
        float[][] result = new float[3][1];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 1; j++) {
                result[i][j] = 0;
                for (int k = 0; k < 3; k++) {
                    result[i][j] += matrix.matrix[i][k] * columnVector.matrix[k][j];
                }
            }
        }
        return new Matrix3f(result);
    }
    public Vector3f multiplicationByVector(Vector3f v,float s) {
        float[][] matrix = new float[3][3];
        matrix[0][0] = s;
        matrix[0][1] = this.matrix[0][1];
        matrix[0][2] = this.matrix[0][2];
        matrix[1][0] = this.matrix[1][0];
        matrix[1][1] = s;
        matrix[1][2] = this.matrix[1][2];
        matrix[2][0] = this.matrix[2][0];
        matrix[2][1] = this.matrix[2][1];
        matrix[2][2] = s;

        float xResult = matrix[0][0] * v.x + matrix[0][1] * v.y + matrix[0][2] * v.z;
        float yResult = matrix[1][0] * v.x + matrix[1][1] * v.y + matrix[1][2] * v.z;
        float zResult = matrix[2][0] * v.x + matrix[2][1] * v.y + matrix[2][2] * v.z;

        return new Vector3f(xResult, yResult, zResult);
    }

    public Matrix3f matrixMultiply(Matrix3f first, Matrix3f second) {
        if (first.matrix[0].length != second.matrix.length) {
            throw new IllegalArgumentException("Matrices cannot be multiplied");
        }
        float[][] result = new float[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                float sum = 0;
                for (int k = 0; k < 3; k++) {
                    sum += first.matrix[i][k] * second.matrix[k][j];
                }
                result[i][j] = sum;
            }
        }
        return new Matrix3f(result);
    }

    public Matrix3f transpose(Matrix3f matrix) {
        float[][] result = new float[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                result[i][j] = matrix.matrix[j][i];
            }
        }
        return new Matrix3f(result);
    }

    public Matrix3f identity() {
        float[][] result = new float[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                result[i][j] = (i == j) ? 1 : 0;
            }
        }
        return new Matrix3f(result);
    }

    public Matrix3f zero() {
        float[][] result = new float[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                result[i][j] = 0;
            }
        }
        return new Matrix3f(result);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (matrix[0].length == 1) {
            for (int i = 0; i < 3; i++) {
                sb.append(matrix[i][0]).append("\n");
            }
        } else {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    sb.append(matrix[i][j]).append(" ");
                }
                sb.append("\n");
            }
        }
        return sb.toString();
    }
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Matrix3f other)) {
            return false;
        }
        return Arrays.deepEquals(this.matrix, other.matrix);
    }

}
