# BrickMaths

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
    implementation 'com.guflimc.brick.maths:api:1.0-SNAPSHOT'
    
    // database converters
    implementation 'com.guflimc.brick.maths:database-api:1.0-SNAPSHOT'
    
    // minestom converters
    implementation 'com.guflimc.brick.maths:minestom-api:1.0-SNAPSHOT'
    
    // spigot converters
    implementation 'com.guflimc.brick.maths:spigot-api:1.0-SNAPSHOT'
}
```

### Javadoc

You can find the javadocs for all platforms [here](https://guflimc.github.io/BrickMaths)

### Examples
```java
Position position = new Position("world", 1, 1, 1, 0, 0);

Location loc = SpigotMaths.toLocation(position);

Pos pos = MinesomMaths.toPos(position);
```
