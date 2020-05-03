package com.gildedrose;

import com.gildedrose.calcbehaviour.CalcBehaviourFactory;
import com.gildedrose.calcbehaviour.ICalcBeaviour;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        CalcBehaviourFactory calcBehaviourFactory = new CalcBehaviourFactory();
        for (Item item : items) {
            ICalcBeaviour behaviour = calcBehaviourFactory.getBehaviour(item);
            item.quality = behaviour.calcQuality();
            item.sellIn = behaviour.calcSellIn();
        }
    }
}