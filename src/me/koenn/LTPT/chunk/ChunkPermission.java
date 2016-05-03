package me.koenn.LTPT.chunk;

import me.koenn.LTPT.util.TownUtil;

public class ChunkPermission {

    private boolean build;
    private boolean destroy;
    private boolean access;
    private boolean pvp;

    public ChunkPermission() {
        this.build = false;
        this.destroy = false;
        this.access = true;
        this.pvp = false;
    }

    public ChunkPermission(boolean build, boolean destroy, boolean access, boolean pvp) {
        this.build = build;
        this.destroy = destroy;
        this.access = access;
        this.pvp = pvp;
    }

    @Override
    public String toString() {
        return String.valueOf(build) + "," + String.valueOf(destroy) + "," + String.valueOf(access) + "," + String.valueOf(pvp);
    }

    public void toggleAccess() {
        if (this.access) {
            this.setAccess(false);
        } else {
            this.setAccess(true);
        }
    }

    public void togglePvp() {
        if (this.pvp) {
            this.setPvp(false);
        } else {
            this.setPvp(true);
        }
    }

    public void toggleBuild() {
        if (this.build) {
            this.setBuild(false);
        } else {
            this.setBuild(true);
        }
    }

    public void toggleDestroy() {
        if (this.destroy) {
            this.setDestroy(false);
        } else {
            this.setDestroy(true);
        }
    }

    public boolean isBuild() {
        return build;
    }

    public boolean isDestroy() {
        return destroy;
    }

    public boolean isAccess() {
        return access;
    }

    public boolean isPvp() {
        return pvp;
    }

    public String getAccess() {
        if (this.access) {
            return "Allow";
        } else {
            return "Deny";
        }
    }

    public void setAccess(boolean access) {
        this.access = access;
        TownUtil.saveAllTownsToConfig();
    }

    public String getPvp() {
        if (this.pvp) {
            return "Allow";
        } else {
            return "Deny";
        }
    }

    public void setPvp(boolean pvp) {
        this.pvp = pvp;
        TownUtil.saveAllTownsToConfig();
    }

    public String getBuild() {
        if (this.build) {
            return "Allow";
        } else {
            return "Deny";
        }
    }

    public void setBuild(boolean build) {
        this.build = build;
        TownUtil.saveAllTownsToConfig();
    }

    public String getDestroy() {
        if (this.destroy) {
            return "Allow";
        } else {
            return "Deny";
        }
    }

    public void setDestroy(boolean destroy) {
        this.destroy = destroy;
        TownUtil.saveAllTownsToConfig();
    }
}
