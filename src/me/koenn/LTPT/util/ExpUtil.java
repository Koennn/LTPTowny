package me.koenn.LTPT.util;

import org.bukkit.entity.Player;

public class ExpUtil {

    public static void setTotalExperience(Player player, int exp) {
        player.setExp(0.0F);
        player.setLevel(0);
        player.setTotalExperience(0);

        int amount = exp;
        while (amount > 0) {
            int expToLevel = getExpAtLevel(player.getLevel());
            amount -= expToLevel;
            if (amount >= 0) {
                player.giveExp(expToLevel);
            } else {
                amount += expToLevel;
                player.giveExp(amount);
                amount = 0;
            }
        }
    }

    public static int getExpAtLevel(int level) {
        if (level <= 15) {
            return 2 * level + 7;
        }
        if ((level >= 16) && (level <= 30)) {
            return 5 * level - 38;
        }
        return 9 * level - 158;
    }

    public static int getTotalExperience(Player player) {
        int exp = Math.round(getExpAtLevel(player.getLevel()) * player.getExp());
        int currentLevel = player.getLevel();
        while (currentLevel > 0) {
            currentLevel--;
            exp += getExpAtLevel(currentLevel);
        }
        if (exp < 0) {
            exp = Integer.MAX_VALUE;
        }
        return exp;
    }

}
