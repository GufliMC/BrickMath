package com.guflimc.brick.math.common.geometry.shape2d;

import bentleyottmann.BentleyOttmann;
import bentleyottmann.IPoint;
import bentleyottmann.ISegment;
import com.guflimc.brick.math.common.geometry.pos2.Point2;
import com.guflimc.brick.math.common.geometry.pos2.Vector2;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class Polygon implements Shape2 {

    private final List<Vector2> vertices;

    private transient byte complex = -1;
    private transient byte convex = -1;

    private transient final Rectangle bounds;

    public Polygon(List<Vector2> vertices) {
        if (vertices.size() < 3) {
            throw new IllegalArgumentException("A polygon requires at least 3 vertices.");
        }
        this.vertices = List.copyOf(vertices);

        // calculate bounding box
        double minX = Double.MAX_VALUE, minY = Double.MAX_VALUE, maxX = Double.MIN_VALUE, maxY = Double.MIN_VALUE;
        for (Vector2 vertex : vertices) {
            if (vertex.x() < minX) minX = vertex.x();
            if (vertex.y() < minY) minY = vertex.y();
            if (vertex.x() > maxX) maxX = vertex.x();
            if (vertex.y() > maxY) maxY = vertex.y();
        }
        bounds = new Rectangle(new Vector2(minX, minY), new Vector2(maxX, maxY));
    }

    public Polygon(Vector2... vertices) {
        this(List.of(vertices));
    }

    @Override
    public List<Vector2> vertices() {
        return vertices;
    }

    @Override
    public Rectangle bounds() {
        return bounds;
    }

    //

    @Override
    public boolean contains(Point2 point) {
        // fast check for outside bounds
        if ( point.x() < bounds.min().x() || point.x() > bounds.max().x()
                || point.y() < bounds.min().y() || point.y() > bounds.max().y() ) {
            return false;
        }

        // this would only work for a convex polygon
//        int i, j;
//        boolean result = false;
//        for (i = 0, j = vertices.size() - 1; i < vertices.size(); j = i++) {
//            if ((vertices.get(i).y() > point.y()) != (vertices.get(j).y() > point.y()) &&
//                    (point.x() < (vertices.get(j).x() - vertices.get(i).x())
//                            * (point.y() - vertices.get(i).y())
//                            / (vertices.get(j).y()-vertices.get(i).y()) + vertices.get(i).x())) {
//                result = !result;
//            }
//        }
//        return result;

        // modified algorithm from WorldEdit:
        // https://github.com/EngineHub/WorldEdit/blob/cd95d207f223b7b381d953311ea8761aa81bd99a/worldedit-core/src/main/java/com/sk89q/worldedit/regions/Polygonal2DRegion.java#L312

        int targetX = point.blockX();
        int targetY = point.blockY();

        boolean inside = false;
        int npoints = vertices.size();
        int i, xNew, yNew, x1, y1, x2, y2;
        long crossproduct;

        int xOld = vertices.get(npoints - 1).blockX();
        int yOld = vertices.get(npoints - 1).blockY();

        for (i = 0; i < npoints; ++i) {
            xNew = vertices.get(i).blockX();
            yNew = vertices.get(i).blockY();

            // corner matches
            if (xNew == targetX && yNew == targetY) {
                return true;
            }

            if (xNew > xOld) {
                x1 = xOld;
                x2 = xNew;
                y1 = yOld;
                y2 = yNew;
            } else {
                x1 = xNew;
                x2 = xOld;
                y1 = yNew;
                y2 = yOld;
            }

            if (x1 <= targetX && targetX <= x2) {
                crossproduct = ((long) targetY - (long) y1) * (long) (x2 - x1)
                        - ((long) y2 - (long) y1) * (long) (targetX - x1);
                if (crossproduct == 0) {
                    if ((y1 <= targetY) == (targetY <= y2)) {
                        return true; //on edge
                    }
                } else if (crossproduct < 0 && (x1 != targetX)) {
                    inside = !inside;
                }
            }
            xOld = xNew;
            yOld = yNew;
        }

        return inside;
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
            segments.add(new LineSegment2(BOIPoint.of(vertices.get(i)), BOIPoint.of(vertices.get(i + 1)), null));
        }
        segments.add(new LineSegment2(BOIPoint.of(vertices.get(vertices.size() - 1)), BOIPoint.of(vertices.get(0)), null));

        BentleyOttmann bentleyOttmann = new BentleyOttmann(BOIPoint::new);
        bentleyOttmann.addSegments(segments);
        bentleyOttmann.findIntersections();

        System.out.println(bentleyOttmann.intersections());
        return bentleyOttmann.intersections().size() > 0;
    }

    private record BOIPoint(double x, double y) implements IPoint {
        static BOIPoint of(Point2 point) {
            return new BOIPoint(point.x(), point.y());
        }
    }
    private record LineSegment2(BOIPoint p1, BOIPoint p2, String name) implements ISegment {}

    //

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

    //

    @NotNull
    @Override
    public Iterator<Point2> iterator() {
//        if (!isConvex()) {
//            throw new IllegalStateException("Cannot iterate over a non-convex polygon.");
//        }

        Vector2 dimensions = bounds().dimensions();
        return new Iterator<>() {

            private Point2 next = forward();
            private int x = bounds.min().blockX(), y = bounds.min().blockY();

            @Override
            public boolean hasNext() {
                return next != null;
            }

            @Override
            public Point2 next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }

                Point2 current = next;
                next = forward();
                return current;
            }

            private Point2 forward() {
                Point2 point;
                do {
                    point = new Vector2(bounds.min().blockX() + x, bounds.min().blockY() + y);
                    if ( ++x <= bounds.max().blockX() ) {
                        continue;
                    }
                    x = bounds.min().blockX();

                    if ( ++y <= bounds.max().blockY() ) {
                        continue;
                    }
                    return null;
                } while ( !contains(point) );
                return point;
            }
        };
    }
}
