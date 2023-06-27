package com.guflimc.brick.math.spigot;

import com.guflimc.brick.math.common.geometry.pos3.Location;
import com.guflimc.brick.math.common.geometry.pos3.Vector3;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.util.BlockVector;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class SpigotAdapter {

    // spigot -> brick
    public static Vector3 adapt(@NotNull Vector other) {
        return new Vector3(other.getX(), other.getY(), other.getZ());
    }

    public static Vector3 adapt(@NotNull BlockVector other) {
        return new Vector3(other.getX(), other.getY(), other.getZ());
    }

    public static Location adapt(@NotNull org.bukkit.Location other) {
        UUID worldId = other.getWorld() != null ? other.getWorld().getUID() : null;
        return new Location(worldId, other.getX(), other.getY(), other.getZ(), other.getYaw(), other.getPitch());
    }

    // brick -> spigot
    public static Vector adapt(@NotNull Vector3 other) {
        return new Vector(other.x(), other.y(), other.z());
    }

    public static org.bukkit.Location adapt(@NotNull Location other) {
        World world = other.worldId() != null ? Bukkit.getWorld(other.worldId()) : null;
        return new org.bukkit.Location(world, other.x(), other.y(), other.z(), other.yaw(), other.pitch());
    }

}
