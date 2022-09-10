package com.guflimc.brick.maths.api.geo.area;

import com.guflimc.brick.maths.api.geo.pos.Vector2;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;

public record Contour(List<Vector2> vertices) implements Iterable<Vector2> {

    public Contour(List<Vector2> vertices) {
        if (vertices.size() < 3) {
            throw new IllegalArgumentException("A contour requires at least 3 vertices.");
        }
        this.vertices = List.copyOf(vertices);
    }

    public Contour(Vector2... vertices) {
        this(List.of(vertices));
    }

    @NotNull
    @Override
    public Iterator<Vector2> iterator() {
        return vertices.iterator();
    }

    @Override
    public void forEach(Consumer<? super Vector2> action) {
        Iterable.super.forEach(action);
    }

    @Override
    public Spliterator<Vector2> spliterator() {
        return Iterable.super.spliterator();
    }

}
