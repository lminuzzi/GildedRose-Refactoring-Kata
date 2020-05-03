package com.gildedrose;

import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {
    @Test
    void shouldIncreaseQualityEachUpdate() {
        assertCalculatedValues(getIncreaseQualityItems(),
                new GildedRose(getIncreaseQualityItems()), CalcQuality.INCREASE);
    }

    @Test
    void shouldRetainPropsEachUpdate() {
        assertCalculatedValues(getRetainPropsItems(),
                new GildedRose(getRetainPropsItems()), CalcQuality.NOTHING);
    }

    @Test
    void shouldIncreaseQualityTwiceEachUpdate() {
        assertCalculatedValues(getIncreaseTwiceQualityItems(),
                new GildedRose(getIncreaseTwiceQualityItems()), CalcQuality.INCREASETWICE);
    }

    @Test
    void shouldIncreaseQualityThreeEachUpdate() {
        assertCalculatedValues(getIncreaseThreeQualityItems(),
                new GildedRose(getIncreaseThreeQualityItems()), CalcQuality.INCREASETHREE);
    }

    @Test
    void shouldIncreaseQualityTo50EachUpdate() {
        assertCalculatedValues(getIncreaseTo50QualityItems(),
                new GildedRose(getIncreaseTo50QualityItems()), CalcQuality.INCREASETO50);
    }

    @Test
    void shouldDecreaseQualityEachUpdate() {
        assertCalculatedValues(getDecreaseQualityItems(),
                new GildedRose(getDecreaseQualityItems()), CalcQuality.DECREASE);
    }

    @Test
    void shouldDecreaseQualityTwiceEachUpdate() {
        assertCalculatedValues(getDecreaseTwiceQualityItems(),
                new GildedRose(getDecreaseTwiceQualityItems()), CalcQuality.DECREASETWICE);
    }

    @Test
    void shouldDecreaseQualityToZeroEachUpdate() {
        assertCalculatedValues(getDecreaseToZeroQualityItems(),
                new GildedRose(getDecreaseToZeroQualityItems()), CalcQuality.DECREASETOZERO);
    }

    @Test
    void shouldDecreaseQualityFourEachUpdate() {
        assertCalculatedValues(getDecreaseFourQualityItems(),
                new GildedRose(getDecreaseFourQualityItems()), CalcQuality.DECREASEFOUR);
    }

    @Test
    void shouldPrintPropsCorrectly() {
        Item item = new Item("Elixir of the Mongoose", 5, 7);
        assertEquals(item.toString(), item.name + ", " + item.sellIn + ", " + item.quality);
    }

    private Item[] getRetainPropsItems() {
        return new Item[]{
                new Item("Sulfuras, Hand of Ragnaros", 0, 80),
                new Item("Sulfuras, Hand of Ragnaros", -1, 80)
        };
    }

    private Item[] getIncreaseQualityItems() {
        return new Item[]{
                new Item("Aged Brie", 2, 0),
                new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20),
                new Item("Backstage passes to a TAFKAL80ETC concert", 10, 49),
                new Item("Backstage passes to a TAFKAL80ETC concert", 5, 49)
        };
    }

    private Item[] getIncreaseTwiceQualityItems() {
        return new Item[]{
                new Item("Backstage passes to a TAFKAL80ETC concert", 10, 20),
                new Item("Aged Brie", -1, 5)
        };
    }

    private Item[] getIncreaseThreeQualityItems() {
        return new Item[]{
                new Item("Backstage passes to a TAFKAL80ETC concert", 5, 20)
        };
    }

    private Item[] getIncreaseTo50QualityItems() {
        return new Item[]{
                new Item("Backstage passes to a TAFKAL80ETC concert", 5, 50),
                new Item("Aged Brie", 5, 50)
        };
    }

    private Item[] getDecreaseQualityItems() {
        return new Item[]{
                new Item("+5 Dexterity Vest", 10, 20),
                new Item("Elixir of the Mongoose", 5, 7),
                new Item("Elixir of the Mongoose", 1, 7)
        };
    }

    private Item[] getDecreaseTwiceQualityItems() {
        return new Item[]{
                new Item("Elixir of the Mongoose", 0, 7),
                new Item("Conjured Mana Cake", 3, 6)
        };
    }

    private Item[] getDecreaseFourQualityItems() {
        return new Item[]{
                new Item("Conjured Mana Cake", 0, 7)
        };
    }

    private Item[] getDecreaseToZeroQualityItems() {
        return new Item[]{
                new Item("Backstage passes to a TAFKAL80ETC concert", 0, 20),
                new Item("+5 Dexterity Vest", -1, 1)
        };
    }

    private void assertCalculatedValues(Item[] originItems, GildedRose app, CalcQuality calcQuality) {
        final int days = 2;
        IntStream.range(1, days).forEach(day -> {
            app.updateQuality();
            IntStream.range(0, originItems.length).forEach(index -> {
                assertEquals(originItems[index].name, app.items[index].name);
                int sellInCalculated = getSellInCalculated(originItems[index].sellIn, calcQuality, day);
                assertEquals(sellInCalculated, app.items[index].sellIn);
                int quantityCalculated = getQualityCalculated(originItems[index].quality, calcQuality, day);
                assertEquals(quantityCalculated, app.items[index].quality);
            });
        });
    }

    private int getSellInCalculated(int sellIn, CalcQuality calcQuality, int day) {
        if (!calcQuality.equals(CalcQuality.NOTHING)) {
            return sellIn - day;
        }
        return sellIn;
    }

    private int getQualityCalculated(int quality, CalcQuality calcQuality, int day) {
        if (calcQuality.equals(CalcQuality.INCREASE)) {
            return quality + day;
        }
        if (calcQuality.equals(CalcQuality.INCREASETWICE)) {
            return quality + (day * 2);
        }
        if (calcQuality.equals(CalcQuality.INCREASETHREE)) {
            return quality + (day * 3);
        }
        if (calcQuality.equals(CalcQuality.INCREASETO50)) {
            return 50;
        }
        if (calcQuality.equals(CalcQuality.DECREASE)) {
            return quality - day;
        }
        if (calcQuality.equals(CalcQuality.DECREASETWICE)) {
            return quality - (day * 2);
        }
        if (calcQuality.equals(CalcQuality.DECREASETOZERO)) {
            return 0;
        }
        if (calcQuality.equals(CalcQuality.DECREASEFOUR)) {
            return quality - (day * 4);
        }
        return quality;
    }
}

enum CalcQuality {
    INCREASE, INCREASETWICE, INCREASETHREE, INCREASETO50, NOTHING,
    DECREASE, DECREASETWICE, DECREASETOZERO, DECREASEFOUR
}
