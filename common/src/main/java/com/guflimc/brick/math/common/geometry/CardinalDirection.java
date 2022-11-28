package com.guflimc.brick.math.common.geometry;

import com.guflimc.brick.math.common.geometry.pos3.IPoint3;
import com.guflimc.brick.math.common.geometry.pos3.Vector3;

public enum CardinalDirection {
    NORTH(new Vector3(0, 0, -1)),
    EAST(new Vector3(1, 0, 0)),
    SOUTH(new Vector3(0, 0, 1)),
    WEST(new Vector3(-1, 0, 0));

    private final Vector3 direction;

    CardinalDirection(Vector3 direction) {
        this.direction = direction;
    }

    CardinalDirection opposite() {
        CardinalDirection[] values = values();
        int ordinal = (this.ordinal() + 2) % values.length;
        return values[ordinal];
    }

    CardinalDirection clockwise() {
        CardinalDirection[] values = values();
        int ordinal = (this.ordinal() + 1) % values.length;
        return values[ordinal];
    }

    CardinalDirection counterclockwise() {
        CardinalDirection[] values = values();
        int ordinal = (this.ordinal() + 3) % values.length;
        return values[ordinal];
    }

    public <T extends IPoint3<T>> T forwards(T point, double amount) {
        return point.add(direction.scale(amount));
    }

    public <T extends IPoint3<T>> T backwards(T point, double amount) {
        return point.add(direction.scale(-amount));
    }

    public Vector3 direction() {
        return direction;
    }

    //

    public static CardinalDirection fromYaw(float yaw) {
        if (yaw >= 135 || yaw < -135) {
            return CardinalDirection.NORTH;
        }
        if (yaw >= -135 && yaw < -45) {
            return CardinalDirection.EAST;
        }
        if (yaw >= -45 && yaw < 45) {
            return CardinalDirection.SOUTH;
        }
        return CardinalDirection.WEST;
    }

}
