package com.cgvsu.Math;

import javax.vecmath.Tuple3f;

public class Vector3f {
    private static final float eps = 1e-7f;
    public float x, y, z;

    public Vector3f() {

    }

    public Vector3f(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public final void add(Vector3f var1) {
        this.x += var1.x;
        this.y += var1.y;
        this.z += var1.z;
    }

    public final void sub(Vector3f var1, Vector3f var2) {
        this.x = var1.x - var2.x;
        this.y = var1.y - var2.y;
        this.z = var1.z - var2.z;
    }

    public Vector3f multiplicationByScalar(Vector3f vector, float scalar) {
        float x = vector.x * scalar;
        float y = vector.y * scalar;
        float z = vector.z * scalar;
        return new Vector3f(x, y, z);
    }

    public Vector3f divisionByScalar(Vector3f vector, float scalar) {
        if (Math.abs(scalar) <= eps) {
            throw new ArithmeticException("Division by zero");
        }
        float x = vector.x / scalar;
        float y = vector.y / scalar;
        float z = vector.z / scalar;
        return new Vector3f(x, y, z);
    }

    public float length() {
        return (float)Math.sqrt((this.x * this.x + this.y * this.y + this.z * this.z));
    }

    public final void normalize() {
        float var1 = (float)(1.0 / length());
        this.x *= var1;
        this.y *= var1;
        this.z *= var1;
    }

    public float dot(Vector3f other) {
        return this.x * other.x + this.y * other.y + this.z * other.z;
    }

    public final void cross(Vector3f var1, Vector3f var2) {
        float var3 = var1.y * var2.z - var1.z * var2.y;
        float var4 = var2.x * var1.z - var2.z * var1.x;
        this.z = var1.x * var2.y - var1.y * var2.x;
        this.x = var3;
        this.y = var4;
    }

    public String toString() {
        return "Vector (" + x + " , " + y + " , " + z + ")";
    }
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Vector3f other)) {
            return false;
        }
        return Math.abs(x - other.x) < eps && Math.abs(y - other.y) < eps && Math.abs(z - other.z) < eps;
    }
}

