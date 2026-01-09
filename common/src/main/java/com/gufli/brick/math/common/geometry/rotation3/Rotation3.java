package com.gufli.brick.math.common.geometry.rotation3;

public record Rotation3(double roll, double yaw, double pitch) implements Orientation3 {

    @Override
    public Rotation3 with(double roll, double yaw, double pitch) {
        return new Rotation3(roll, yaw, pitch);
    }

    //

    @Override
    public Rotation3 withRoll(double roll) {
        return (Rotation3) Orientation3.super.withRoll(roll);
    }

    @Override
    public Rotation3 withYaw(double yaw) {
        return (Rotation3) Orientation3.super.withYaw(yaw);
    }

    @Override
    public Rotation3 withPitch(double pitch) {
        return (Rotation3) Orientation3.super.withPitch(pitch);
    }

    @Override
    public Rotation3 addRoll(double roll) {
        return (Rotation3) Orientation3.super.addRoll(roll);
    }

    @Override
    public Rotation3 addYaw(double yaw) {
        return (Rotation3)  Orientation3.super.addYaw(yaw);
    }

    @Override
    public Rotation3 addPitch(double pitch) {
        return (Rotation3) Orientation3.super.addPitch(pitch);
    }

    @Override
    public Rotation3 add(double roll, double yaw, double pitch) {
        return (Rotation3) Orientation3.super.add(roll, yaw, pitch);
    }

    @Override
    public Rotation3 add(Orientation3 other) {
        return (Rotation3) Orientation3.super.add(other);
    }

    @Override
    public Rotation3 subtract(double roll, double yaw, double pitch) {
        return (Rotation3) Orientation3.super.subtract(roll, yaw, pitch);
    }

    //

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Rotation3 other)) {
            return false;
        }
        if ( other == this ) {
            return true;
        }
        double epsilon = 0.0001d;
        return Math.abs(roll - other.roll) < epsilon
                && Math.abs(yaw - other.yaw) < epsilon
                && Math.abs(pitch - other.pitch) < epsilon;
    }

    @Override
    public String toString() {
        return "Rotation3{r=" + roll + ", y=" + yaw + ", p=" + pitch + "}";
    }


}
