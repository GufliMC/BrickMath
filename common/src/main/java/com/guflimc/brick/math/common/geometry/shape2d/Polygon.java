package com.guflimc.brick.math.common.geometry.shape2d;

import bentleyottmann.BentleyOttmann;
import bentleyottmann.ISegment;
import com.guflimc.brick.math.common.geometry.pos2.LineSegment2;
import com.guflimc.brick.math.common.geometry.pos2.Vector2;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public record Polygon(List<Vector2> vertices) implements Shape2 {

    public Polygon(List<Vector2> vertices) {
        if (vertices.size() < 3) {
            throw new IllegalArgumentException("A polygon requires at least 3 vertices.");
        }
        this.vertices = List.copyOf(vertices);
    }

    public Polygon(Vector2... vertices) {
        this(List.of(vertices));
    }

    @NotNull
    @Override
    public Iterator<Vector2> iterator() {
        return vertices.iterator();
    }

    //
    
    public boolean isComplex() {
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
}
