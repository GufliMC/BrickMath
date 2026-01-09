package com.gufli.brick.math.common.geometry.shape3d;

import com.gufli.brick.math.common.geometry.pos2.Point2;
import com.gufli.brick.math.common.geometry.pos2.Vector2;
import com.gufli.brick.math.common.geometry.pos3.Point3;
import com.gufli.brick.math.common.geometry.pos3.Vector3;
import com.gufli.brick.math.common.geometry.shape2d.Polygon;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public record PolyPrism(double minY, double maxY, Polygon polygon) implements ShapePrism3 {

    public static PolyPrism of(double minY, double maxY, List<Point2> points) {
        points = new ArrayList<>(points);

        // remove points that are on a straight line between its two surrounding points, also removes duplicates
        int index = 2;
        while (index <= points.size()) {
            Point2 current = index == points.size() ? points.get(0) : points.get(index);
            Vector2 oneToTwo = points.get(index - 1).subtract(points.get(index - 2)).toVector().normalize();
            Vector2 twoToThree = current.subtract(points.get(index - 1)).toVector().normalize();
            if (oneToTwo.equals(twoToThree)) {
                points.remove(index - 1);
                continue;
            }
            index++;
        }

        return new PolyPrism(minY, maxY, new Polygon(points));
    }

    public static PolyPrism of(double minY, double maxY, Point2... points) {
        return of(minY, maxY, List.of(points));
    }

    //

    @Override
    public boolean contains(Point3 vector) {
        if (polygon.vertices().size() < 3) {
            return false;
        }

        int targetX = (int) Math.floor(vector.x()); //wide
        int targetY = (int) Math.floor(vector.y()); //height
        int targetZ = (int) Math.floor(vector.z()); //depth

        if (targetY < minY || targetY > maxY) {
            return false;
        }

        boolean inside = false;
        int npoints = polygon.vertices().size();
        double xNew, zNew;
        double x1, z1, x2, z2;
        long crossproduct;

        double xOld = polygon.vertices().get(npoints - 1).x();
        double zOld = polygon.vertices().get(npoints - 1).y();

        for (Point2 point : polygon().vertices()) {
            xNew = point.x();
            zNew = point.y();

            //Check for corner
            if (xNew == targetX && zNew == targetZ) {
                return true;
            }

            if (xNew > xOld) {
                x1 = xOld;
                x2 = xNew;
                z1 = zOld;
                z2 = zNew;
            } else {
                x1 = xNew;
                x2 = xOld;
                z1 = zNew;
                z2 = zOld;
            }
            if (x1 <= targetX && targetX <= x2) {
                crossproduct = ((long) targetZ - (long) z1) * (long) (x2 - x1)
                        - ((long) z2 - (long) z1) * (long) (targetX - x1);
                if (crossproduct == 0) {
                    if ((z1 <= targetZ) == (targetZ <= z2)) {
                        return true; //on edge
                    }
                } else if (crossproduct < 0 && (x1 != targetX)) {
                    inside = !inside;
                }
            }
            xOld = xNew;
            zOld = zNew;
        }

        return inside;
    }

    @Override
    public Polygon contour() {
        return polygon;
    }

    @Override
    public RectPrism bounds() {
        return new RectPrism(minY, maxY, polygon.bounds());
    }

    @NotNull
    @Override
    public Iterator<Point3> iterator() {
        Iterator<Point2> polygon = polygon().iterator();
        return new Iterator<>() {

            private double y = minY;
            private Point2 point2 = polygon.next();

            @Override
            public boolean hasNext() {
                return y <= maxY && polygon.hasNext();
            }

            @Override
            public Point3 next() {
                if (y > maxY) {
                    y = minY;
                    point2 = polygon.next();
                }
                Point3 point = new Vector3(point2.x(), y, point2.y());
                y++;
                return point;
            }
        };
    }
}
