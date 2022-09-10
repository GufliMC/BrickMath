package com.guflimc.brick.maths.api.geo.area;

import com.guflimc.brick.maths.api.geo.pos.Point;

public interface Area {

    boolean contains(Point point);

    Contour contour();

}
