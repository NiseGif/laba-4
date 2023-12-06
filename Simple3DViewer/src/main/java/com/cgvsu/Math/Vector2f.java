package com.cgvsu.Math;

public class Vector2f {
    public float x, y;
    private static final float eps = 1e-4f;

    public Vector2f() {

    }

    public Vector2f(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Vector2f addition(Vector2f first, Vector2f second) {
        float x = first.x + second.x;
        float y = first.y + second.y;
        return new Vector2f(x, y);
    }


    public Vector2f subtraction(Vector2f first, Vector2f second) {
        float x = first.x - second.x;
        float y = first.y - second.y;
        return new Vector2f(x, y);
    }

    public Vector2f multiplicationByScalar(Vector2f vector, float scalar) {
        float x = vector.x * scalar;
        float y = vector.y * scalar;
        return new Vector2f(x, y);
    }

    public Vector2f divisionByScalar(Vector2f vector, float scalar) {
        if (Math.abs(scalar) <= eps) {
            throw new ArithmeticException("Division by zero");
        }
        float x = vector.x / scalar;
        float y = vector.y / scalar;
        return new Vector2f(x, y);
    }

    public float vectorLength(Vector2f vector) {
        return (float) Math.sqrt(Math.pow(vector.x, 2) + Math.pow(vector.y, 2));
    }

    public Vector2f normalize(Vector2f vector) {
        float length = vectorLength(vector);
        if (Math.abs(length) <= eps) {
            throw new ArithmeticException("Division by zero");
        }
        float invLength = 1 / length;
        float x = vector.x * invLength;
        float y = vector.y * invLength;
        return new Vector2f(x, y);
    }

    public float scalarProduct(Vector2f first, Vector2f second) {
        return (first.x * second.x) + (first.y * second.y);
    }

    @Override
    public String toString() {
        return "Vector (" + x + " , " + y + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Vector2f other)) {
            return false;
        }
        return Math.abs(this.x - other.x) < eps && Math.abs(this.y - other.y) < eps;
    }
}
