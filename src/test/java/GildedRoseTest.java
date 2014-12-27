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
		UsefulItem usefulItem = new UsefulItem(createGenericItemWithQuality(1));
		
		usefulItem.incrementQuality();

		assertEquals(2, usefulItem.quality());
	}

	@Test
	public void ifQualityIsFiftyThenQualityDoesNotIncrement() {
		UsefulItem usefulItem = new UsefulItem(createGenericItemWithQuality(MAX_QUALITY));
		
		usefulItem.incrementQuality();

		assertEquals(MAX_QUALITY, usefulItem.quality());
	}

	@Test
	public void updateQualityForGeneralItem() {
		UsefulItem usefulItem = new UsefulItem(createGenericItemWithQuality(MAX_QUALITY));

		underTest.adjustQualityForItem(usefulItem);

		assertEquals(49, usefulItem.quality());
	}

	@Test
	public void qualityNeverNegative() {
		UsefulItem usefulItem = new UsefulItem(new ItemBuilder().setName(genericItemName).setQuality(0)
				.setSellIn(1).build());

		underTest.adjustQualityForItem(usefulItem);

		assertEquals(0, new ItemBuilder().setName(genericItemName).setQuality(0)
				.setSellIn(1).build().quality);
	}
	
	@Test
	public void qualityForBrieIncreasesUntilFifty() {
		UsefulItem usefulItem = new UsefulItem(new ItemBuilder().setName(AGED_BRIE).setQuality(1).setSellIn(1).build());
		checkQualityTimes(usefulItem, MAX_QUALITY_INCREASE);
		
		assertEquals(50, usefulItem.quality());
	}
	
	@Test
	public void sellInDateContinuesToDecreaseBelowZero() {
		UsefulItem usefulItem = new UsefulItem(new ItemBuilder().setName(AGED_BRIE).setQuality(1).setSellIn(1).build());
		checkQualityTimes(usefulItem, MAX_QUALITY_INCREASE);
		
		assertEquals(-49, usefulItem.sellIn());
	}
	
	@Test
	public void sellInDateDoesNotChangeForSulfuras() {
		UsefulItem usefulItem = new UsefulItem(new ItemBuilder().setName(SULFURAS).setQuality(1).setSellIn(1).build());
		checkQualityTimes(usefulItem, MAX_QUALITY_INCREASE);
		
		assertEquals(1, usefulItem.sellIn());
	}
	
	@Test
	public void qualityForBackStagePassesIncreaseByThreeIfSellInUnderSix() {
		UsefulItem usefulItem = new BackStagePass(new ItemBuilder().setName(BACKSTAGE_PASSES).setQuality(1).setSellIn(1).build());
		checkQualityTimes(usefulItem, 1);
		
		assertEquals(4, usefulItem.quality());
	}
	
	@Test
	public void qualityForBackStagePassesIncreaseByTwoIfSellinOver11() {
		UsefulItem usefulItem = new BackStagePass(new ItemBuilder().setName(BACKSTAGE_PASSES).setQuality(1).setSellIn(12).build());
		checkQualityTimes(usefulItem, 1);
		
		assertEquals(3, usefulItem.quality());
	}
	
	@Test
	public void  sellInUnderZeroForGeneralItemReducesQuality() {
		UsefulItem usefulItem = new UsefulItem(new ItemBuilder().setName(genericItemName).setQuality(3).setSellIn(-1).build());
		checkQualityTimes(usefulItem, 1);
		
		assertEquals(1, usefulItem.quality());
	}

	@Test
	public void  sellInUnderZeroForGeneralItemWithQualityAlreadyZero() {
		UsefulItem usefulItem = new UsefulItem(new ItemBuilder().setName(genericItemName).setQuality(0).setSellIn(-1).build());
		checkQualityTimes(usefulItem, 1);
		
		assertEquals(0, usefulItem.quality());
	}
	
	@Test
	public void  sellInUnderZeroForSulfarsDoesNotDecrementQuality() {
		UsefulItem usefulItem = new UsefulItem(new ItemBuilder().setName(SULFURAS).setQuality(1).setSellIn(-1).build());
		checkQualityTimes(usefulItem, 1);
		
		assertEquals(1, usefulItem.quality());
	}
	
	@Test
	public void  sellInUnderZeroForBackStagePassReduceQualityToZero() {
		UsefulItem usefulItem = new UsefulItem(new ItemBuilder().setName(BACKSTAGE_PASSES).setQuality(10).setSellIn(-1).build());
		checkQualityTimes(usefulItem, 1);
		
		assertEquals(0, usefulItem.quality());
	}
	
	@Test
	public void shouldTakeListOfItemsAndCallAdjustQualityForAll() {
		UsefulItem usefulItem = new UsefulItem(new ItemBuilder().setName(BACKSTAGE_PASSES).setQuality(10).setSellIn(-1).build());
		UsefulItem usefulItem2 = new UsefulItem(new ItemBuilder().setName(genericItemName).setQuality(3).setSellIn(-1).build());
		
		underTest.addItem(usefulItem);
		underTest.addItem(usefulItem2);
		underTest.updateQuality();

		assertEquals(0, usefulItem.quality());
		assertEquals(1, usefulItem2.quality());
	}
	
	@Test
	public void usefulItemShouldTakeAndReturnPartsOfIt() {
		UsefulItem useful = new UsefulItem(new Item("Some Item", 1, 2));
		assertEquals(2, useful.quality());
		assertEquals(1, useful.sellIn());
		assertEquals("Some Item", useful.name());
	}

	private void checkQualityTimes(UsefulItem item, int timesToCheck) {
		for(int qualityChecks = 1; qualityChecks <=timesToCheck; qualityChecks++) {
			underTest.adjustQualityForItem(item);
		}
	}
	
	private Item createGenericItemWithQuality(int quality) {
		return new ItemBuilder().setName(genericItemName).setQuality(quality).setSellIn(1).build();
	}
}
