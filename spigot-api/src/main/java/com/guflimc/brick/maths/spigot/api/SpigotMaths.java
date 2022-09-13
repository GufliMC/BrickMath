package com.guflimc.brick.maths.spigot.api;

import com.guflimc.brick.maths.api.geo.pos.Location;
import com.guflimc.brick.maths.api.geo.pos.Vector;
import org.bukkit.Bukkit;
import org.bukkit.World;

import java.util.UUID;

public class SpigotMaths {

    /**
     * @deprecated Use {@link #toBrickVector(org.bukkit.Location)} instead.
     */
    @Deprecated
    public static Vector toVector(org.bukkit.Location other) {
        return toBrickVector(other);
    }

    public static Vector toBrickVector(org.bukkit.Location other) {
        return new Vector(other.getX(), other.getY(), other.getZ());
    }

    public static Vector toBrickVector(org.bukkit.util.Vector other) {
        return new Vector(other.getX(), other.getY(), other.getZ());
    }

    public static Vector toBrickVector(org.bukkit.util.BlockVector other) {
        return new Vector(other.getX(), other.getY(), other.getZ());
    }

    /**
     * @deprecated Use {@link #toSpigotLocation(Location)} instead.
     */
    @Deprecated
    public static org.bukkit.Location toLocation(Location other) {
        return toSpigotLocation(other);
    }

    public static org.bukkit.Location toSpigotLocation(Location other) {
        World world = null;
        if ( other.worldId() != null ) {
            world = Bukkit.getWorld(other.worldId());
        }
        return new org.bukkit.Location(world, other.x(), other.y(), other.z(), other.yaw(), other.pitch());
    }

    /**
     * @deprecated Use {@link #toBrickLocation(org.bukkit.Location)} instead.
     */
    @Deprecated
    public static Location toPosition(org.bukkit.Location other) {
        return toBrickLocation(other);
    }

    public static Location toBrickLocation(org.bukkit.Location other) {
        UUID worldId = other.getWorld() != null ? other.getWorld().getUID() : null;
        return new Location(worldId, other.getX(), other.getY(), other.getZ(), other.getYaw(), other.getPitch());
    }

}
