package com.gufli.brick.math.common.geometry.rotation3;

import com.gufli.brick.math.common.geometry.pos3.Vector3;

public interface Orientation3 {

    double roll();

    double yaw();

    double pitch();

    Orientation3 with(double roll, double yaw, double pitch);

    //

    default Orientation3 withRoll(double roll) {
        return with(roll, yaw(), pitch());
    }

    default Orientation3 withYaw(double yaw) {
        return with(roll(), yaw, pitch());
    }

    default Orientation3 withPitch(double pitch) {
        return with(roll(), yaw(), pitch);
    }

    default Orientation3 addRoll(double roll) {
        return withRoll(roll() + roll);
    }

    default Orientation3 addYaw(double yaw) {
        return withYaw(yaw() + yaw);
    }

    default Orientation3 addPitch(double pitch) {
        return withPitch(pitch() + pitch);
    }

    default Orientation3 add(double roll, double yaw, double pitch) {
        return with(roll() + roll, yaw() + yaw, pitch() + pitch);
    }

    default Orientation3 add(Orientation3 other) {
        return add(other.roll(), other.yaw(), other.pitch());
    }

    default Orientation3 subtract(double roll, double yaw, double pitch) {
        return add(-roll, -yaw, -pitch);
    }

    default Vector3 direction() {
        double y = -Math.sin(Math.toRadians(pitch()));
        double xz = Math.cos(Math.toRadians(pitch()));
        double x = -xz * Math.sin(Math.toRadians(yaw()));
        double z = xz * Math.cos(Math.toRadians(yaw()));
        return new Vector3(x, y, z);
    }

}
