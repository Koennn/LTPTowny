package me.koenn.LTPT.chunk;

public enum PlotType {

    NORMAL("Normal"), FOR_SALE("For Sale"), SHOP("Shop"), EMBASSY("Embassy"), SOLD("Sold");

    String string;

    PlotType(String string) {
        this.string = string;
    }

    @Override
    public String toString() {
        return this.string;
    }

    public String toString(int price) {
        if (!this.equals(FOR_SALE)) {
            return this.string;
        }
        return this.string + " $" + price;
    }
}
