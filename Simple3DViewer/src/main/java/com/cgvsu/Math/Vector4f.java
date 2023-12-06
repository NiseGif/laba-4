package com.cgvsu.Math;

public class Vector4f {
    private static final float eps = 1e-7f;
    public float x, y, z, w;

    public Vector4f() {

    }

    public Vector4f(float x, float y, float z, float w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public Vector4f addition(Vector4f first, Vector4f second) {
        float x = first.x + second.x;
        float y = first.y + second.y;
        float z = first.z + second.z;
        float w = first.w + second.w;
        return new Vector4f(x, y, z, w);
    }

    public Vector4f subtraction(Vector4f first, Vector4f second) {
        float x = first.x - second.x;
        float y = first.y - second.y;
        float z = first.z - second.z;
        float w = first.w - second.w;
        return new Vector4f(x, y, z, w);
    }

    public Vector4f multiplicationByScalar(Vector4f vector, float scalar) {
        float x = vector.x * scalar;
        float y = vector.y * scalar;
        float z = vector.z * scalar;
        float w = vector.w * scalar;
        return new Vector4f(x, y, z, w);
    }

    public Vector4f divisionByScalar(Vector4f vector, float scalar) {
        if (Math.abs(scalar) <= eps) {
            throw new ArithmeticException("Division by zero");
        }
        float x = vector.x / scalar;
        float y = vector.y / scalar;
        float z = vector.z / scalar;
        float w = vector.w / scalar;
        return new Vector4f(x, y, z, w);
    }

    public float vectorLength(Vector4f vector) {
        return (float) Math.sqrt(Math.pow(vector.x, 2) + Math.pow(vector.y, 2) + Math.pow(vector.z, 2) + Math.pow(vector.w, 2));
    }

    public Vector4f normalize(Vector4f vector) {
        float length = vectorLength(vector);
        if (Math.abs(length) <= eps) {
            throw new ArithmeticException("Division by zero");
        }
        float invLength = 1 / length;
        float x = vector.x * invLength;
        float y = vector.y * invLength;
        float z = vector.z * invLength;
        float w = vector.w * invLength;
        return new Vector4f(x, y, z, w);
    }

    public float scalarProduct(Vector4f first, Vector4f second) {
        return (first.x * second.x) + (first.y * second.y) + (first.z * second.z) + (first.w * second.w);
    }

    public String toString() {
        return "Vector (" + x + " , " + y + " , " + z + " , " + w + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Vector4f other)) {
            return false;
        }
        return Math.abs(x - other.x) < eps && Math.abs(y - other.y) < eps && Math.abs(z - other.z) < eps && Math.abs(w - other.w) < eps;
    }
}
