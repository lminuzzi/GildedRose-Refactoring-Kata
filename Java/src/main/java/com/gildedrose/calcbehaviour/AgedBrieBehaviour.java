package com.gildedrose.calcbehaviour;

import com.gildedrose.Item;

public class AgedBrieBehaviour extends DefaultBehaviour {
    AgedBrieBehaviour(Item item) {
        super(item);
    }

    @Override
    public int calcQuality() {
        if (this.getItem().quality < this.getMaxValueQuality()) {
            if (this.getItem().sellIn <= 0) {
                return this.getItem().quality + 2;
            }
            return this.getItem().quality + 1;
        }
        return this.getMaxValueQuality();
    }
}
