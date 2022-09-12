package com.guflimc.brick.maths.spigot.api;

import com.guflimc.brick.maths.api.geo.pos.Location;
import com.guflimc.brick.maths.api.geo.pos.Vector;
import org.bukkit.Bukkit;
import org.bukkit.World;

import java.util.UUID;

public class SpigotMaths {

    public static Vector toVector(org.bukkit.Location other) {
        return new Vector(other.getX(), other.getY(), other.getZ());
    }

    public static org.bukkit.Location toLocation(Location other) {
        World world = null;
        if ( other.worldId() != null ) {
            world = Bukkit.getWorld(other.worldId());
        }
        return new org.bukkit.Location(world, other.x(), other.y(), other.z(), other.yaw(), other.pitch());
    }

    public static Location toPosition(org.bukkit.Location other) {
        UUID worldId = other.getWorld() != null ? other.getWorld().getUID() : null;
        return new Location(worldId, other.getX(), other.getY(), other.getZ(), other.getYaw(), other.getPitch());
    }

}
