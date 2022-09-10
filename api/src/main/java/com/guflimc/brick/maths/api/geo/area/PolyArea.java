package com.guflimc.brick.maths.api.geo.area;

import com.guflimc.brick.maths.api.geo.pos.Point;
import com.guflimc.brick.maths.api.geo.pos.Vector2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public record PolyArea(double minY, double maxY, List<Vector2> points) implements Area {

    public PolyArea(double minY, double maxY, List<Vector2> points) {
        if (points.size() < 3) {
            throw new IllegalArgumentException("Polygon area requires at least 3 points.");
        }
        this.minY = minY;
        this.maxY = maxY;
        this.points = List.copyOf(points);
    }

    public PolyArea(double minY, double maxY, Vector2... points) {
        this(minY, maxY, Arrays.asList(points));
    }

    public int size() {
        return points.size();
    }

    @Override
    public boolean contains(Point vector) {
        if (points.size() < 3) {
            return false;
        }

        int targetX = (int) Math.floor(vector.x()); //wide
        int targetY = (int) Math.floor(vector.y()); //height
        int targetZ = (int) Math.floor(vector.z()); //depth

        if (targetY < minY || targetY > maxY) {
            return false;
        }

        boolean inside = false;
        int npoints = points.size();
        double xNew, zNew;
        double x1, z1, x2, z2;
        long crossproduct;

        double xOld = points.get(npoints - 1).x();
        double zOld = points.get(npoints - 1).y();

        for (Vector2 point : points) {
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
    public Contour contour() {
        return new Contour(points);
    }
}
