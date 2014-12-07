import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class GildedRoseTest {

	private static final int MAX_QUALITY_INCREASE = 50;

	private static final String AGED_BRIE = "Aged Brie";
	private static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
	private static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";

	private static final int MAX_QUALITY = 50;

	private GildedRose underTest;

	private String genericItemName = "name";
	
	@Before
	public void setup() {
		underTest = new GildedRose();
	}

	@Test
	public void ifQualityBelowFiftyThenAddQualityofOne() {
		Item item = createGenericItemWithQuality(1);

		underTest.incrementQuality(item);

		assertEquals(2, item.quality);
	}

	@Test
	public void ifQualityIsFiftyThenQualityDoesNotIncrement() {
		Item item = createGenericItemWithQuality(MAX_QUALITY);

		underTest.incrementQuality(item);

		assertEquals(MAX_QUALITY, item.quality);
	}

	@Test
	public void updateQualityForGeneralItem() {
		Item item = createGenericItemWithQuality(MAX_QUALITY);

		underTest.adjustQualityForItem(item);

		assertEquals(49, item.quality);
	}

	@Test
	public void qualityNeverNegative() {
		Item item = new ItemBuilder().setName(genericItemName).setQuality(0)
				.setSellIn(1).build();

		underTest.adjustQualityForItem(item);

		assertEquals(0, item.quality);
	}
	
	@Test
	public void qualityForBrieIncreasesUntilFifty() {
		Item item = new ItemBuilder().setName(AGED_BRIE).setQuality(1).setSellIn(1).build();
		checkQualityTimes(item, MAX_QUALITY_INCREASE);
		
		assertEquals(50, item.quality);
	}
	
	@Test
	public void sellInDateContinuesToDecreaseBelowZero() {
		Item item = new ItemBuilder().setName(AGED_BRIE).setQuality(1).setSellIn(1).build();
		checkQualityTimes(item, MAX_QUALITY_INCREASE);
		
		assertEquals(-49, item.sellIn);
	}
	
	@Test
	public void sellInDateDoesNotChangeForSulfuras() {
		Item item = new ItemBuilder().setName(SULFURAS).setQuality(1).setSellIn(1).build();
		checkQualityTimes(item, MAX_QUALITY_INCREASE);
		
		assertEquals(1, item.sellIn);
	}
	
	@Test
	public void qualityForBackStagePassesIncreaseByThreeIfSellInUnderSix() {
		Item item = new ItemBuilder().setName(BACKSTAGE_PASSES).setQuality(1).setSellIn(1).build();
		checkQualityTimes(item, 1);
		
		assertEquals(4, item.quality);
	}
	
	@Test
	public void qualityForBackStagePassesIncreaseByTwoIfSellinOver11() {
		Item item = new ItemBuilder().setName(BACKSTAGE_PASSES).setQuality(1).setSellIn(12).build();
		checkQualityTimes(item, 1);
		
		assertEquals(2, item.quality);
	}
	
	@Test
	public void  sellInUnderZeroForGeneralItemReducesQuality() {
		Item item = new ItemBuilder().setName(genericItemName).setQuality(3).setSellIn(-1).build();
		checkQualityTimes(item, 1);
		
		assertEquals(1, item.quality);
	}

	@Test
	public void  sellInUnderZeroForBackStagePassReduceQualityToZero() {
		Item item = new ItemBuilder().setName(BACKSTAGE_PASSES).setQuality(10).setSellIn(-1).build();
		checkQualityTimes(item, 1);
		
		assertEquals(0, item.quality);
	}
	
	@Test
	public void shouldTakeListOfItemsAndCallAdjustQualityForAll() {
		Item item = new ItemBuilder().setName(BACKSTAGE_PASSES).setQuality(10).setSellIn(-1).build();
		Item item2 = new ItemBuilder().setName(genericItemName).setQuality(3).setSellIn(-1).build();
		
		underTest.addItem(item);
		underTest.addItem(item2);
		underTest.updateQuality();

		assertEquals(0, item.quality);
		assertEquals(1, item2.quality);
	}

	private void checkQualityTimes(Item item, int timesToCheck) {
		for(int qualityChecks = 1; qualityChecks <=timesToCheck; qualityChecks++) {
			underTest.adjustQualityForItem(item);
		}
	}
	
	private Item createGenericItemWithQuality(int quality) {
		return new ItemBuilder().setName(genericItemName).setQuality(quality).setSellIn(1).build();
	}
}
