package com.guflimc.brick.maths.spigot.api;

import com.guflimc.brick.maths.api.geo.Location;
import com.guflimc.brick.maths.api.geo.Vector;
import org.bukkit.Bukkit;

public class SpigotMaths {

    public static Vector toVector(org.bukkit.Location other) {
        return new Vector(other.getX(), other.getY(), other.getZ());
    }

    public static org.bukkit.Location toLocation(Location other) {
        return new org.bukkit.Location(Bukkit.getWorld(other.worldName()), other.x(), other.y(), other.z(), other.yaw(), other.pitch());
    }

    public static Location toPosition(org.bukkit.Location other) {
        return new Location(other.getWorld().getName(), other.getX(), other.getY(), other.getZ(), other.getYaw(), other.getPitch());
    }

}
