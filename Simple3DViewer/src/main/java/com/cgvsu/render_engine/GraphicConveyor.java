package com.cgvsu.render_engine;

import com.cgvsu.Math.Matrix3f;
import com.cgvsu.Math.Matrix4f;
import com.cgvsu.Math.Vector3f;
import com.cgvsu.model.Model;

import javax.vecmath.Point2f;

public class GraphicConveyor {
    static private float[][] scaleDate = {
            {0, 0, 0},
            {0, 0, 0},
            {0, 0, 0},
    };
    Matrix3f scaleMatrix = new Matrix3f(scaleDate);

    public static Matrix4f lookAt(Vector3f eye, Vector3f target) {
        return lookAt(eye, target, new Vector3f(0F, 1.0F, 0F));
    }

    public static Matrix4f lookAt(Vector3f eye, Vector3f target, Vector3f up) {
        Vector3f resultX = new Vector3f();
        Vector3f resultY = new Vector3f();
        Vector3f resultZ = new Vector3f();

        resultZ.sub(target, eye);
        resultX.cross(up, resultZ);
        resultY.cross(resultZ, resultX);

        resultX.normalize();
        resultY.normalize();
        resultZ.normalize();

        float[][] matrix = new float[][]{
                {resultX.x, resultY.x, resultZ.x, 0,},
                {resultX.y, resultY.y, resultZ.y, 0,},
                {resultX.z, resultY.z, resultZ.z, 0,},
                {-resultX.dot(eye), -resultY.dot(eye), -resultZ.dot(eye), 1}};
        return new Matrix4f(matrix);
    }

    public static Matrix4f perspective(
            final float fov,
            final float aspectRatio,
            final float nearPlane,
            final float farPlane) {
        float[][] result = new float[4][4];
        float tangentMinusOnDegree = (float) (1.0F / (Math.tan(fov * 0.5F)));
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                result[i][j] = 0;
            }
        }
        result[0][0] = tangentMinusOnDegree / aspectRatio;
        result[1][1] = tangentMinusOnDegree;
        result[2][2] = (farPlane + nearPlane) / (farPlane - nearPlane);
        result[2][3] = 1.0F;
        result[3][2] = 2 * (nearPlane * farPlane) / (nearPlane - farPlane);
        return new Matrix4f(result);
    }

    public static Vector3f multiplyMatrix4ByVector3(final Matrix4f matrix, final Vector3f vertex) {
        final float[][] matrixData = matrix.getData();
        final float x = (vertex.x * matrixData[0][0]) + (vertex.y * matrixData[1][0]) + (vertex.z * matrixData[2][0]) + matrixData[3][0];
        final float y = (vertex.x * matrixData[0][1]) + (vertex.y * matrixData[1][1]) + (vertex.z * matrixData[2][1]) + matrixData[3][1];
        final float z = (vertex.x * matrixData[0][2]) + (vertex.y * matrixData[1][2]) + (vertex.z * matrixData[2][2]) + matrixData[3][2];
        final float w = (vertex.x * matrixData[0][3]) + (vertex.y * matrixData[1][3]) + (vertex.z * matrixData[2][3]) + matrixData[3][3];
        return new Vector3f(x / w, y / w, z / w);
    }
    //TODO: Добавить тесты для масштабирования
    public Vector3f scale(final Vector3f v, float s) {
        return scaleMatrix.multiplicationByVector(v, s);
    }

    //TODO: Добавить свой класс Point2f
    public static Point2f vertexToPoint(final Vector3f vertex, final int width, final int height) {
        return new Point2f(vertex.x * width + width / 2.0F, -vertex.y * height + height / 2.0F);
    }
}
