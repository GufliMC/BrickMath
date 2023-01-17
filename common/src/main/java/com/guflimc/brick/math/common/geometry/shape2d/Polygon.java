package com.guflimc.brick.math.common.geometry.shape2d;

import bentleyottmann.BentleyOttmann;
import bentleyottmann.ISegment;
import com.guflimc.brick.math.common.geometry.pos2.LineSegment2;
import com.guflimc.brick.math.common.geometry.pos2.Point2;
import com.guflimc.brick.math.common.geometry.pos2.Vector2;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Polygon implements Shape2 {

    private final List<Vector2> vertices;

    private byte complex = -1;
    private byte convex = -1;

    public Polygon(List<Vector2> vertices) {
        if (vertices.size() < 3) {
            throw new IllegalArgumentException("A polygon requires at least 3 vertices.");
        }
        this.vertices = List.copyOf(vertices);
    }

    public Polygon(Vector2... vertices) {
        this(List.of(vertices));
    }

    @Override
    public List<Vector2> vertices() {
        return vertices;
    }

    @Override
    public boolean contains(Point2 point) {
        return false;
    }

    //

    public boolean isComplex() {
        if (complex == -1) {
            complex = (byte) (_isComplex() ? 1 : 0);
        }
        return complex == 1;
    }

    private boolean _isComplex() {
        List<ISegment> segments = new ArrayList<>();
        for (int i = 0; i < vertices.size() - 1; i++) {
            segments.add(new LineSegment2(vertices.get(i), vertices.get(i + 1)));
        }
        segments.add(new LineSegment2(vertices.get(vertices.size() - 1), vertices.get(0)));

        BentleyOttmann bentleyOttmann = new BentleyOttmann(Vector2::new);
        bentleyOttmann.addSegments(segments);
        bentleyOttmann.findIntersections();

        return bentleyOttmann.intersections().size() > 0;
    }

    public boolean isConvex() {
        if ( convex == -1 ) {
            convex = (byte) (_isConvex() ? 1 : 0);
        }
        return convex == 1;
    }

    private boolean _isConvex() {
        if (vertices.size() < 4) {
            return true;
        }

        if (isComplex()) {
            return false;
        }

        boolean sign = false;
        int n = vertices.size();

        for (int i = 0; i < n; i++) {
            double dx1 = vertices.get((i + 2) % n).x() - vertices.get((i + 1) % n).x();
            double dy1 = vertices.get((i + 2) % n).y() - vertices.get((i + 1) % n).y();
            double dx2 = vertices.get(i).x() - vertices.get((i + 1) % n).x();
            double dy2 = vertices.get(i).y() - vertices.get((i + 1) % n).y();
            double zcrossproduct = dx1 * dy2 - dy1 * dx2;

            if (i == 0) {
                sign = zcrossproduct > 0;
            } else if (sign != (zcrossproduct > 0)) {
                return false;
            }
        }

        return true;
    }
    
    public Rectangle boundingBox() {
        double minX = Double.MAX_VALUE, minY = Double.MAX_VALUE, maxX = Double.MIN_VALUE, maxY = Double.MIN_VALUE;
        for (Vector2 vertex : vertices) {
            if (vertex.x() < minX) minX = vertex.x();
            if (vertex.y() < minY) minY = vertex.y();
            if (vertex.x() > maxX) maxX = vertex.x();
            if (vertex.y() > maxY) maxY = vertex.y();
        }
        return new Rectangle(new Vector2(minX, minY), new Vector2(maxX, maxY));
    }

    @NotNull
    @Override
    public Iterator<Point2> iterator() {
        if (!isConvex()) {
            throw new IllegalStateException("Cannot iterate over a non-convex polygon.");
        }

        Vector2 dimensions = boundingBox().dimensions();
        int max = dimensions.blockX() * dimensions.blockY();
        return new Iterator<>() {

            private Point2 next = find();
            private int index = 0;

            @Override
            public boolean hasNext() {
                return next != null;
            }

            @Override
            public Point2 next() {
                Point2 current = next;
                next = find();
                return current;
            }

            private Point2 find() {
                Point2 point;
                do {
                    int x = index % dimensions.blockX();
                    int y = (index / dimensions.blockX()) % dimensions.blockY();
                    point = new Vector2(x, y);
                    index++;
                } while ( !contains(point) || index > max );
                return point;
            }
        };
    }
}
