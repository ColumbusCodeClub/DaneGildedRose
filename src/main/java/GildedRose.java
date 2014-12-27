import java.util.ArrayList;
import java.util.List;

public class GildedRose {

	private static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
	private static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
	private static final String AGED_BRIE = "Aged Brie";
	private static List<UsefulItem> items = null;

	public GildedRose() {
		items = new ArrayList<UsefulItem>();
		initItems();
	}

	public void addItem(UsefulItem item) {
		items.add(item);
	}

	public void updateQuality() {

		for (UsefulItem item : items) {
			adjustQualityForItem(item);
		}
	}

	private boolean isAgedBrie(UsefulItem item) {
		return AGED_BRIE.equals(item.name());
	}

	private boolean isBackstage(UsefulItem item) {
		return BACKSTAGE_PASSES.equals(item.name());
	}

	void adjustQualityForItem(UsefulItem item) {
		if (qualityDecreases(item)) {
			if (qualityAboveZero(item)) {
				if (notSulfuras(item)) {
					item.decrementQuality();
				}
			}
		} else {
			item.incrementQuality();
		}

		if (notSulfuras(item)) {
			item.setSellIn(item.sellIn() - 1);
		}

		if (item.sellIn() < 0) {
			if (!isAgedBrie(item)) {
				if (!isBackstage(item)) {
					if (qualityAboveZero(item)) {
						if (notSulfuras(item)) {
							item.decrementQuality();
						}
					}
				} else {
					item.setQuality(0);
				}
			} else {
				item.incrementQuality();
			}
		}
	}

	private boolean notSulfuras(UsefulItem item) {
		return !SULFURAS.equals(item.name());
	}

	private boolean qualityAboveZero(UsefulItem item) {
		return item.quality() > 0;
	}

	private boolean qualityDecreases(UsefulItem item) {
		return (!isAgedBrie(item)) && !isBackstage(item);
	}

	private void initItems() {
		items.add(new UsefulItem(new Item("+5 Dexterity Vest", 10, 20)));
		items.add(new UsefulItem(new Item(AGED_BRIE, 2, 0)));
		items.add(new UsefulItem(new Item("Elixir of the Mongoose", 5, 7)));
		items.add(new UsefulItem(new Item(SULFURAS, 0, 80)));
		items.add(new UsefulItem(new Item(BACKSTAGE_PASSES, 15, 20)));
		items.add(new UsefulItem(new Item("Conjured Mana Cake", 3, 6)));
	}

}