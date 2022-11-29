# BrickMath

Implements some math functionality that can be used universally with a minecraft platform dependency.

## Platforms

* [x] Minestom
* [x] Spigot / Paper

## Usage
### Gradle

```
repositories {
    maven { url "https://repo.jorisg.com/snapshots" }
}
```

```
dependencies {
    // universal
    implementation 'com.guflimc.brick.math:api:1.0-SNAPSHOT'
    
    // database converters
    implementation 'com.guflimc.brick.math:database:1.0-SNAPSHOT'
    
    // minestom converters
    implementation 'com.guflimc.brick.math:minestom:1.0-SNAPSHOT'
    
    // spigot converters
    implementation 'com.guflimc.brick.math:spigot:1.0-SNAPSHOT'
}
```

### Javadoc

You can find the javadocs for all platforms [here](https://guflimc.github.io/BrickMaths)

### Examples
```java
Location position = new Location("world", 1, 1, 1, 0, 0);

org.bukkit.Location loc = SpigotMath.toLocation(position);

Pos pos = MinesomMath.toPos(position);
```
