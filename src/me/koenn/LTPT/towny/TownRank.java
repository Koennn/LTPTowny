package me.koenn.LTPT.towny;

public enum TownRank {

    RESIDENT("Resident"), LEADER("Leader");

    String rank;

    TownRank(String rank) {
        this.rank = rank;
    }


    @Override
    public String toString() {
        return rank;
    }
}
