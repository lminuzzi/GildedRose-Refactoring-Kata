package com.gildedrose.calcbehaviour;

import com.gildedrose.Item;

public class SulfurasBehaviour extends DefaultBehaviour {
    SulfurasBehaviour(Item item) {
        super(item);
    }

    @Override
    public int getMaxValueQuality() {
        return 80;
    }

    @Override
    public int calcQuality() {
        return Math.min(this.getItem().quality, getMaxValueQuality());
    }

    @Override
    public int calcSellIn() {
        return this.getItem().sellIn;
    }
}
