package com.gildedrose.calcbehaviour;

import com.gildedrose.Item;

public class ConjuredBehaviour extends DefaultBehaviour {
    ConjuredBehaviour(Item item) {
        super(item);
    }

    @Override
    public int calcQuality() {
        Item item = this.getItem();
        int quality = item.quality;
        if (item.sellIn <= 0) {
            quality = quality - 2;
        }
        quality = quality - 2;
        return Math.max(quality, this.getMinValueQuality());
    }
}
