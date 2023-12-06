package com.cgvsu.render_engine;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import com.cgvsu.Math.Matrix3f;
import com.cgvsu.Math.Vector3f;
import com.cgvsu.Math.Matrix4f;
import javafx.scene.canvas.GraphicsContext;
import com.cgvsu.model.Model;

import javax.vecmath.Point2f;

import static com.cgvsu.render_engine.GraphicConveyor.*;

public class RenderEngine {
    static float[][] modelMatrix = {
            {1, 0, 0, 0,},
            {0, 1, 0, 0,},
            {0, 0, 1, 0,},
            {0, 0, 0, 1}};

    public static void render(
            final GraphicsContext graphicsContext,
            final Camera camera,
            final Model mesh,
            final int width,
            final int height) {

        Matrix4f viewMatrix = camera.getViewMatrix();
        Matrix4f projectionMatrix = camera.getProjectionMatrix();
        Matrix4f modelViewProjectionMatrix = new Matrix4f(modelMatrix);
        Matrix4f res1;
        Matrix4f res2;
        Matrix4f res3;
        res1 = modelViewProjectionMatrix.mul(viewMatrix);
        res2 = modelViewProjectionMatrix.mul(projectionMatrix);
        res3 = res1.mul(res2);

        final int nPolygons = mesh.polygons.size();
        for (int polygonInd = 0; polygonInd < nPolygons; ++polygonInd) {
            final int nVerticesInPolygon = mesh.polygons.get(polygonInd).getVertexIndices().size();

            ArrayList<Point2f> resultPoints = new ArrayList<>();
            for (int vertexInPolygonInd = 0; vertexInPolygonInd < nVerticesInPolygon; ++vertexInPolygonInd) {
                Vector3f vertex = mesh.vertices.get(mesh.polygons.get(polygonInd).getVertexIndices().get(vertexInPolygonInd));
                Vector3f vertexVecmath = new Vector3f(vertex.x, vertex.y, vertex.z);

                Point2f resultPoint = vertexToPoint(multiplyMatrix4ByVector3(res3, vertexVecmath), width, height);


                resultPoints.add(resultPoint);

            }

            for (int vertexInPolygonInd = 1; vertexInPolygonInd < nVerticesInPolygon; ++vertexInPolygonInd) {
                graphicsContext.strokeLine(
                        resultPoints.get(vertexInPolygonInd - 1).x,
                        resultPoints.get(vertexInPolygonInd - 1).y,
                        resultPoints.get(vertexInPolygonInd).x,
                        resultPoints.get(vertexInPolygonInd).y);
            }

            if (nVerticesInPolygon > 0)
                graphicsContext.strokeLine(
                        resultPoints.get(nVerticesInPolygon - 1).x,
                        resultPoints.get(nVerticesInPolygon - 1).y,
                        resultPoints.get(0).x,
                        resultPoints.get(0).y);
        }

    }

    public static void render(
            final GraphicsContext graphicsContext,
            final Camera camera,
            final Model mesh,
            final int width,
            final int height,
            final float s) {

        Matrix4f viewMatrix = camera.getViewMatrix();
        Matrix4f projectionMatrix = camera.getProjectionMatrix();
        Matrix4f modelViewProjectionMatrix = new Matrix4f(modelMatrix);
        Matrix4f res1;
        Matrix4f res2;
        Matrix4f res3;
        res1 = modelViewProjectionMatrix.mul(viewMatrix);
        res2 = modelViewProjectionMatrix.mul(projectionMatrix);
        res3 = res1.mul(res2);

        final int nPolygons = mesh.polygons.size();
        for (int polygonInd = 0; polygonInd < nPolygons; ++polygonInd) {
            final int nVerticesInPolygon = mesh.polygons.get(polygonInd).getVertexIndices().size();

            ArrayList<Point2f> resultPoints = new ArrayList<>();
            for (int vertexInPolygonInd = 0; vertexInPolygonInd < nVerticesInPolygon; ++vertexInPolygonInd) {
                Vector3f vertex = mesh.vertices.get(mesh.polygons.get(polygonInd).getVertexIndices().get(vertexInPolygonInd));

                Vector3f vertexVecmath = new Vector3f(new GraphicConveyor().scale(vertex, s).x, new GraphicConveyor().scale(vertex, s).y, new GraphicConveyor().scale(vertex, s).z);

                Point2f resultPoint = vertexToPoint(multiplyMatrix4ByVector3(res3, vertexVecmath), width, height);


                resultPoints.add(resultPoint);

            }

            for (int vertexInPolygonInd = 1; vertexInPolygonInd < nVerticesInPolygon; ++vertexInPolygonInd) {
                graphicsContext.strokeLine(
                        resultPoints.get(vertexInPolygonInd - 1).x,
                        resultPoints.get(vertexInPolygonInd - 1).y,
                        resultPoints.get(vertexInPolygonInd).x,
                        resultPoints.get(vertexInPolygonInd).y);
            }

            if (nVerticesInPolygon > 0)
                graphicsContext.strokeLine(
                        resultPoints.get(nVerticesInPolygon - 1).x,
                        resultPoints.get(nVerticesInPolygon - 1).y,
                        resultPoints.get(0).x,
                        resultPoints.get(0).y);
        }

    }
}