package com.gildedrose.calcbehaviour;

import com.gildedrose.Item;

public class DefaultBehaviour implements ICalcBeaviour {
    private Item item;

    DefaultBehaviour(Item item) {
        this.item = item;
    }

    public Item getItem() {
        return item;
    }

    public int getMaxValueQuality() {
        return 50;
    }

    public int getMinValueQuality() {
        return 0;
    }

    @Override
    public int calcQuality() {
        int quality = this.item.quality;
        if (quality > getMinValueQuality()) {
            if (this.item.sellIn <= 0) {
                quality--;
            }
            quality--;
        }
        return Math.max(quality, getMinValueQuality());
    }

    @Override
    public int calcSellIn() {
        return this.item.sellIn - 1;
    }
}
