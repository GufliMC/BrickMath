package com.guflimc.brick.maths.spigot.api;

import com.guflimc.brick.maths.api.geo.Position;
import com.guflimc.brick.maths.api.geo.Vector;
import org.bukkit.Bukkit;
import org.bukkit.Location;

public class SpigotMaths {

    public static Vector toVector(Location other) {
        return new Vector(other.getX(), other.getY(), other.getZ());
    }

    public static Location toLocation(Position other) {
        return new Location(Bukkit.getWorld(other.worldId()), other.x(), other.y(), other.z(), other.yaw(), other.pitch());
    }

    public static Position toPosition(Location other) {
        return new Position(other.getWorld().getName(), other.getX(), other.getY(), other.getZ(), other.getYaw(), other.getPitch());
    }

}
