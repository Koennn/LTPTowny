package me.koenn.LTPT.config;

import me.koenn.LTPT.LTPTowny;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("unused")
public class ConfigManager {

    private static FileConfiguration config;
    private static LTPTowny main;

    public ConfigManager(FileConfiguration c, LTPTowny m) {
        config = c;
        main = m;
    }

    public static void deleteSection(String path) {
        Bukkit.getLogger().info(path);
        for (String value : config.getConfigurationSection(path).getKeys(false)) {
            Bukkit.getLogger().info(config.getConfigurationSection(path).get(value).toString());
            config.getConfigurationSection(path).set(value, null);
        }
        config.set(path, null);
        main.saveConfig();
    }

    public static Location getLocationFromString(String location) {
        try {
            String[] values = location.split(",");
            World world = null;
            double x = 0, y = 0, z = 0;
            float yaw = 0, pitch = 0;
            for (String value : values) {
                if (value.startsWith("world=")) {
                    world = Bukkit.getWorld(value.split("=")[1]);
                } else if (value.startsWith("x=")) {
                    x = Double.parseDouble(value.split("=")[1]);
                } else if (value.startsWith("y=")) {
                    y = Double.parseDouble(value.split("=")[1]);
                } else if (value.startsWith("z=")) {
                    z = Double.parseDouble(value.split("=")[1]);
                } else if (value.startsWith("yaw=")) {
                    yaw = Float.parseFloat(value.split("=")[1]);
                } else if (value.startsWith("pitch=")) {
                    pitch = Float.parseFloat(value.split("=")[1]);
                }
            }
            if (world == null || x == 0 || y == 0 || z == 0) {
                throw new IllegalArgumentException("Invalid location");
            }
            return new Location(world, x, y, z, yaw, pitch);
        } catch (NullPointerException ex) {
            return null;
        }
    }

    public static void setString(String value, String key, String... path) {
        createSection(path).set(key, value);
        main.saveConfig();
    }

    public static String getString(String key, String... path) {
        return getSection(path).get(key).toString();
    }

    public static void setList(List<String> list, String key, String... path) {
        createSection(path).set(key, list);
        main.saveConfig();
    }

    public static List<String> getList(String key, String... path) {
        return getSection(path).getList(key).stream().map(Object::toString).collect(Collectors.toList());
    }

    private static ConfigurationSection createSection(String... path) {
        ConfigurationSection section = null;
        for (String p : path) {
            if (section != null) {
                if (section.getConfigurationSection(p) == null) {
                    section = section.createSection(p);
                } else {
                    section = section.getConfigurationSection(p);
                }
            } else {
                if (config.getConfigurationSection(p) == null) {
                    section = config.createSection(p);
                } else {
                    section = config.getConfigurationSection(p);
                }
            }
        }
        if (section == null) {
            throw new IllegalArgumentException("Section cannot be null");
        }
        return section;
    }

    private static ConfigurationSection getSection(String... path) {
        ConfigurationSection section = null;
        for (String p : path) {
            if (section != null) {
                section = section.getConfigurationSection(p);
            } else {
                section = config.getConfigurationSection(p);
            }
        }
        if (section == null) {
            throw new IllegalArgumentException("Section cannot be null");
        }
        return section;
    }

    public static FileConfiguration getConfiguration() {
        return config;
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public void setupConfig() {
        if (!main.getDataFolder().exists()) {
            main.getDataFolder().mkdir();
        }
        if (!(new File(main.getDataFolder(), "config.yml")).exists()) {
            main.saveDefaultConfig();
        }
    }
}
