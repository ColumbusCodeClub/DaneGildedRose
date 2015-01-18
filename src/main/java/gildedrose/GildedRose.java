package gildedrose;

import java.util.ArrayList;
import java.util.List;

public class GildedRose {

	private static List<Item> items = null;

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		System.out.println("OMGHAI!");

		items = new ArrayList<Item>();
		items.add(new Item("+5 Dexterity Vest", 10, 20));
		items.add(new Item("Aged Brie", 2, 0));
		items.add(new Item("Elixir of the Mongoose", 5, 7));
		items.add(new Item("Sulfuras, Hand of Ragnaros", 0, 80));
		items.add(new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20));
		items.add(new Item("Conjured Mana Cake", 3, 6));

		updateQuality();
	}

	public static void updateQuality() {
		for (int i = 0; i < items.size(); i++) {
			updateQualityOnly(i);
			updateSellInDate(i);
		}
	}

	private static void updateQualityOnly(int i) {
		if (isAgedBrie(i) || isBackStagePass(i)) {
			handleQualityIncreases(i);
		} else {
			handleItemsThatDecreaseInQuality(i);
		}
	}

	private static void updateSellInDate(int i) {
		if (isSulfuras(i)) {
		} else {
			decreaseSellInByOne(i);
		}

		if (passedSellInDate(i)) {
			handlePassingSellInDate(i);
		}
	}

	private static void handleItemsThatDecreaseInQuality(int i) {
		if (hasSomeQuality(i)) {
			handleSomeQuality(i);
		}
	}

	private static void handlePassingSellInDate(int i) {
		if (isAgedBrie(i)) {
			updateAgedBrieQuality(i);
		} else {
			if (isBackStagePass(i)) {
				reduceQualityByOne(i);
			} else {
				handleItemsThatDecreaseInQuality(i);
			}

		}
	}

	private static boolean passedSellInDate(int i) {
		return items.get(i).getSellIn() < 0;
	}

	private static void handleSomeQuality(int i) {
		if (isSulfuras(i)) {

		} else {
			reduceQualityByOne(i);
		}
	}

	private static void decreaseSellInByOne(int i) {
		items.get(i).setSellIn(items.get(i).getSellIn() - 1);
	}

	private static void handleQualityIncreases(int i) {
		if (isLessThanMaxQuality(i)) {
			increaseQuality(i);
			handleBackStagePassQuality(i);
		}
	}

	private static void handleBackStagePassQuality(int i) {
		if (isBackStagePass(i)) {
			setBackStagePassQuality(i);
		}
	}

	private static void increaseQuality(int i) {
		items.get(i).setQuality(items.get(i).getQuality() + 1);
	}

	private static boolean isAgedBrie(int i) {
		return "Aged Brie".equals(items.get(i).getName());
	}

	private static boolean isSulfuras(int i) {
		return "Sulfuras, Hand of Ragnaros".equals(items.get(i).getName());
	}

	private static void reduceQualityByOne(int i) {
		items.get(i).setQuality(
				items.get(i).getQuality() - items.get(i).getQuality());
	}

	private static boolean hasSomeQuality(int i) {
		return items.get(i).getQuality() > 0;
	}

	private static void updateAgedBrieQuality(int i) {
		if (isLessThanMaxQuality(i)) {
			increaseQuality(i);
		}
	}

	private static boolean isLessThanMaxQuality(int i) {
		return items.get(i).getQuality() < 50;
	}

	private static boolean isBackStagePass(int i) {
		return "Backstage passes to a TAFKAL80ETC concert".equals(items.get(i)
				.getName());
	}

	private static void setBackStagePassQuality(int i) {
		if (haveLessThanElevenDaysToSell(i)) {
			updateAgedBrieQuality(i);
		}

		if (haveLessThanSixDaysToSell(i)) {
			updateAgedBrieQuality(i);
		}
	}

	private static boolean haveLessThanSixDaysToSell(int i) {
		return items.get(i).getSellIn() < 6;
	}

	private static boolean haveLessThanElevenDaysToSell(int i) {
		return items.get(i).getSellIn() < 11;
	}

}