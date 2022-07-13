package com.guflimc.brick.maths.minestom.api;

import com.guflimc.brick.maths.api.geo.Location;
import com.guflimc.brick.maths.api.geo.Point;
import com.guflimc.brick.maths.api.geo.Position;
import com.guflimc.brick.maths.api.geo.Vector;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.coordinate.Vec;
import net.minestom.server.entity.Entity;

public class MinestomMaths {

    public static Vector toVector(net.minestom.server.coordinate.Point other) {
        return new Vector(other.x(), other.y(), other.z());
    }

    public static Vec toVec(Point other) {
        return new Vec(other.x(), other.y(), other.z());
    }

    public static Pos toPos(Position other) {
        return new Pos(other.x(), other.y(), other.z(), other.yaw(), other.pitch());
    }

    public static Position toPosition(Pos pos) {
        return toLocation(pos);
    }

    public static Location toLocation(Pos pos) {
        return new Location(null, pos.x(), pos.y(), pos.z(), pos.yaw(), pos.pitch());
    }

    public static Location toLocation(Entity entity) {
        Location loc = toLocation(entity.getPosition());
        if ( entity.getInstance() != null ) {
            loc = loc.withWorldId(entity.getInstance().getUniqueId().toString());
        }
        return loc;
    }

}
