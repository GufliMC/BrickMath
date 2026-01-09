package com.gufli.brick.math.common.geometry.shape2d;

import com.gufli.brick.math.common.geometry.pos2.Point2;

import java.awt.geom.Area;
import java.util.ArrayList;
import java.util.List;


public interface Shape2 extends Iterable<Point2> {

    List<Point2> vertices();

    boolean contains(Point2 point);

    Rectangle bounds();

    default double area() {
        throw new UnsupportedOperationException("Not implemented.");
    }

    default Area geometry() {
        List<Point2> vertices = vertices();
        int[] xpoints = new int[vertices.size()];
        int[] ypoints = new int[vertices.size()];
        for (int i = 0; i < vertices.size(); i++) {
            xpoints[i] = (int) vertices.get(i).x();
            ypoints[i] = (int) vertices.get(i).y();
        }
        return new Area(new java.awt.Polygon(xpoints, ypoints, vertices.size()));
    }

    default boolean intersects(Shape2 other) {
        Area tmp = geometry();
        tmp.intersect(other.geometry());
        return !tmp.isEmpty(); // empty means no intersection
    }

    default List<Line2> contour() {
        List<Line2> result = new ArrayList<>();
        List<Point2> vertices = this.vertices();
        Point2 point = vertices.get(vertices.size() - 1);
        for (Point2 next : vertices) {
            result.add(new Line2(point, next));
            point = next;
        }
        return result;
    }

}
