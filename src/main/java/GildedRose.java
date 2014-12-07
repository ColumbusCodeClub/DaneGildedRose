import java.util.ArrayList;
import java.util.List;

public class GildedRose {

	private static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
	private static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
	private static final String AGED_BRIE = "Aged Brie";
	private static List<Item> items = null;

	public GildedRose() {
		items = new ArrayList<Item>();
		initItems();
	}

	public void addItem(Item item) {
		items.add(item);
	}

	public void updateQuality() {

		for (Item item : items) {
			adjustQualityForItem(item);
		}
	}

	private boolean isAgedBrie(Item item) {
		return AGED_BRIE.equals(item.getName());
	}

	private boolean isBackstage(Item item) {
		return BACKSTAGE_PASSES.equals(item.getName());
	}

	void adjustQualityForItem(Item item) {
		if (qualityDecreases(item)) {
			if (qualityAboveZero(item)) {
				if (notSulfuras(item)) {
					decrementQuality(item);
				}
			}
		} else if (isBackstage(item)) {
			updateQualityForBackStagePass(item);

		} else {
			incrementQuality(item);
		}

		if (notSulfuras(item)) {
			item.setSellIn(item.getSellIn() - 1);
		}

		if (item.getSellIn() < 0) {
			if (!isAgedBrie(item)) {
				if (!isBackstage(item)) {
					if (qualityAboveZero(item)) {
						if (notSulfuras(item)) {
							decrementQuality(item);
						}
					}
				} else {
					item.setQuality(0);
				}
			} else {
				incrementQuality(item);
			}
		}
	}

	private boolean notSulfuras(Item item) {
		return !SULFURAS.equals(item.getName());
	}

	private boolean qualityAboveZero(Item item) {
		return item.getQuality() > 0;
	}

	private boolean qualityDecreases(Item item) {
		return (!isAgedBrie(item)) && !isBackstage(item);
	}

	private void updateQualityForBackStagePass(Item item) {
		incrementQuality(item);
		if (item.getSellIn() < 11) {
			incrementQuality(item);
		}

		if (item.getSellIn() < 6) {
			incrementQuality(item);
		}
	}

	private void decrementQuality(Item item) {
		item.setQuality(item.getQuality() - 1);
	}

	void incrementQuality(Item item) {
		if (item.getQuality() < 50) {
			item.setQuality(item.getQuality() + 1);
		}
	}

	private void initItems() {
		items.add(new Item("+5 Dexterity Vest", 10, 20));
		items.add(new Item(AGED_BRIE, 2, 0));
		items.add(new Item("Elixir of the Mongoose", 5, 7));
		items.add(new Item(SULFURAS, 0, 80));
		items.add(new Item(BACKSTAGE_PASSES, 15, 20));
		items.add(new Item("Conjured Mana Cake", 3, 6));
	}

}