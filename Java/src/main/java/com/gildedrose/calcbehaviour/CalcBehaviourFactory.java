package com.gildedrose.calcbehaviour;

import com.gildedrose.Item;

public class CalcBehaviourFactory {
    public ICalcBeaviour getBehaviour(Item item) {
        if (item.name.equals("Aged Brie")) {
            return new AgedBrieBehaviour(item);
        }
        if (item.name.equals("Sulfuras, Hand of Ragnaros")) {
            return new SulfurasBehaviour(item);
        }
        if (item.name.equals("Conjured Mana Cake")) {
            return new ConjuredBehaviour(item);
        }
        if (item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
            return new BackstagePassesBehaviour(item);
        }
        return new DefaultBehaviour(item);
    }
}
