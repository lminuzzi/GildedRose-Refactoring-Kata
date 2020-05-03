package com.gildedrose.calcbehaviour;

import com.gildedrose.Item;

public class BackstagePassesBehaviour extends DefaultBehaviour {
    BackstagePassesBehaviour(Item item) {
        super(item);
    }

    @Override
    public int calcQuality() {
        final int maxValueQuality = this.getMaxValueQuality();
        Item item = this.getItem();
        int quality = item.quality;
        if(quality >= maxValueQuality) {
            return maxValueQuality;
        }
        if(item.sellIn <= 0) {
            //drop to zero after concert.
            return 0;
        }

        final int tripleIncreaseDayLimit = 5;
        final int doubleIncreaseDayLimit = 10;
        if(item.sellIn <= tripleIncreaseDayLimit) {
            quality = quality + 1;
        }
        if (item.sellIn <= doubleIncreaseDayLimit) {
            quality = quality + 1;
        }
        quality = quality + 1;
        return Math.min(quality, maxValueQuality);
    }
}
