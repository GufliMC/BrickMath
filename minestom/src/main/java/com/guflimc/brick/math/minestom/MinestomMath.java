package com.guflimc.brick.math.minestom;

import com.guflimc.brick.math.common.geometry.pos3.Location;
import com.guflimc.brick.math.common.geometry.pos3.Point3;
import com.guflimc.brick.math.common.geometry.pos3.Position;
import com.guflimc.brick.math.common.geometry.pos3.Vector3;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.coordinate.Vec;
import net.minestom.server.entity.Entity;

public class MinestomMath {

    public static Vector3 toVector(net.minestom.server.coordinate.Point other) {
        return new Vector3(other.x(), other.y(), other.z());
    }

    public static Vec toVec(Point3 other) {
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
            loc = loc.withWorldId(entity.getInstance().getUniqueId());
        }
        return loc;
    }

}
