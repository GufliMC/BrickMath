package com.gufli.brick.math.spigot;

import com.gufli.brick.math.common.geometry.pos3.Vector3;
import com.gufli.brick.math.common.geometry.rotation3.Orientation3;
import com.gufli.brick.math.common.geometry.rotation3.Rotation3;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.util.BlockVector;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class SpigotAdapter {

    // spigot -> brick
    public static Vector3 asBrickVector3(@NotNull Vector other) {
        return new Vector3(other.getX(), other.getY(), other.getZ());
    }

    public static Vector3 asBrickVector3(@NotNull BlockVector other) {
        return new Vector3(other.getX(), other.getY(), other.getZ());
    }

    public static Vector3 asBrickVector3(@NotNull org.bukkit.Location other) {
        return new Vector3(other.getX(), other.getY(), other.getZ());
    }

    public static Rotation3 asBrickRotation3(@NotNull org.bukkit.Location other) {
        return new Rotation3(0, other.getYaw(), other.getPitch());
    }

    // brick -> spigot
    public static Vector asSpigotVector(@NotNull Vector3 other) {
        return new Vector(other.x(), other.y(), other.z());
    }

    public static org.bukkit.Location asSpigotLocation(@NotNull World world, @NotNull Vector3 other, @NotNull Orientation3 orient) {
        return new org.bukkit.Location(world, other.x(), other.y(), other.z(), (float) orient.yaw(), (float) orient.pitch());
    }

}
