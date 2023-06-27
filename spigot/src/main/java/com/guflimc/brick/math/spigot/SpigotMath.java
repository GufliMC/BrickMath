package com.guflimc.brick.math.spigot;

import com.guflimc.brick.math.common.geometry.pos3.Location;
import com.guflimc.brick.math.common.geometry.pos3.Vector3;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.util.Vector;

import java.util.UUID;

/**
 * @deprecated Use {@link SpigotAdapter} instead.
 */
@Deprecated
public class SpigotMath {

    /**
     * @deprecated Use {@link SpigotAdapter#adapt(org.bukkit.Location)} instead.
     */
    @Deprecated
    public static Vector3 toBrickVector(org.bukkit.Location other) {
        return new Vector3(other.getX(), other.getY(), other.getZ());
    }

    /**
     * @deprecated Use {@link SpigotAdapter#adapt(Vector)} instead.
     */
    @Deprecated
    public static Vector3 toBrickVector(org.bukkit.util.Vector other) {
        return new Vector3(other.getX(), other.getY(), other.getZ());
    }

    /**
     * @deprecated Use {@link SpigotAdapter#adapt(org.bukkit.util.BlockVector)} instead.
     */
    @Deprecated
    public static Vector3 toBrickVector(org.bukkit.util.BlockVector other) {
        return new Vector3(other.getX(), other.getY(), other.getZ());
    }

    /**
     * @deprecated Use {@link SpigotAdapter#adapt(Location)} instead.
     */
    @Deprecated
    public static org.bukkit.Location toSpigotLocation(Location other) {
        World world = null;
        if (other.worldId() != null) {
            world = Bukkit.getWorld(other.worldId());
        }
        return new org.bukkit.Location(world, other.x(), other.y(), other.z(), other.yaw(), other.pitch());
    }

    /**
     * @deprecated Use {@link SpigotAdapter#adapt(org.bukkit.Location)} instead.
     */
    @Deprecated
    public static Location toBrickLocation(org.bukkit.Location other) {
        UUID worldId = other.getWorld() != null ? other.getWorld().getUID() : null;
        return new Location(worldId, other.getX(), other.getY(), other.getZ(), other.getYaw(), other.getPitch());
    }

}
