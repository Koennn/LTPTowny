package me.koenn.LTPT.towny;

@SuppressWarnings("unused")
public enum TownRank {

    RESIDENT("Resident", 0), SHERIFF("Sheriff", 1), HELPER("Helper", 1), ASSISTANT("Assistant", 2), LEADER("Leader", 10);

    String rank;
    int value;

    TownRank(String rank, int value) {
        this.rank = rank;
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return rank;
    }
}
