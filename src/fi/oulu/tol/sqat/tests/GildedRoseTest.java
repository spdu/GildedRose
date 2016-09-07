package fi.oulu.tol.sqat.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import fi.oulu.tol.sqat.GildedRose;
import fi.oulu.tol.sqat.Item;

public class GildedRoseTest {

// Example scenarios for testing
//   Item("+5 Dexterity Vest", 10, 20));
//   Item("Aged Brie", 2, 0));
//   Item("Elixir of the Mongoose", 5, 7));
//   Item("Sulfuras, Hand of Ragnaros", 0, 80));
//   Item("Backstage passes to a TAFKAL80ETC concert", 15, 20));
//   Item("Conjured Mana Cake", 3, 6));

	@Test
	public void testUpdateEndOfDay_AgedBrie_Quality_10_11() {
		// Arrange
		GildedRose store = new GildedRose();
		store.addItem(new Item("Aged Brie", 2, 10) );
		
		// Act
		store.updateEndOfDay();
		
		// Assert
		List<Item> items = store.getItems();
		Item itemBrie = items.get(0);
		assertEquals(11, itemBrie.getQuality());
	}
    
	@Test
	public void testUpdateEndOfDay_Backstagepass_5to0_20() {
		// Arrange
		GildedRose store = new GildedRose();
		store.addItem(new Item("Backstage passes to a TAFKAL80ETC concert", 5, 20));
		
		// Act
		store.updateEndOfDay();
		store.updateEndOfDay();
		store.updateEndOfDay();
		store.updateEndOfDay();
		store.updateEndOfDay();
		store.updateEndOfDay();
		
		// Assert
		List<Item> items = store.getItems();
		Item itemPass = items.get(0);
		assertEquals(0, itemPass.getQuality());
	}
	@Test
	public void testUpdateEndOfDay_Backstagepass_11to10_20() {
		// Arrange
		GildedRose store = new GildedRose();
		store.addItem(new Item("Backstage passes to a TAFKAL80ETC concert", 11, 20));
		
		// Act
		store.updateEndOfDay();  // A day passes (11 -> 10)
		
		// Assert
		List<Item> items = store.getItems();
		Item itemPass = items.get(0);
		assertEquals(10, itemPass.getSellIn());
		assertEquals(22, itemPass.getQuality());
	}
	
	@Test
	public void testUpdateEndOfDay_Sulfuras_0_80() {
	// Arrange
	GildedRose store = new GildedRose();
	store.addItem(new Item("Sulfuras, Hand of Ragnaros", 0, 80));
	
	// Act
	store.updateEndOfDay();
	store.updateEndOfDay();
	// Assert
	List<Item> items = store.getItems();
	Item itemHand = items.get(0);
	assertEquals(80, itemHand.getQuality());
	assertEquals(0, itemHand.getSellIn());
	}
	
	@Test
	public void testUpdateVest_2_20() {
	// Arrange
	GildedRose store = new GildedRose();
	store.addItem(new Item("+5 Dexterity Vest", 2, 20));
	
	// Act
	store.updateEndOfDay();
	store.updateEndOfDay();
	store.updateEndOfDay();
	// Assert
	List<Item> items = store.getItems();
	Item itemVest = items.get(0);
	//assertEquals(-2, itemVest.getSellIn());
	assertEquals(16, itemVest.getQuality()); //correct
	}
	
	@Test
	public void testUpdateManaCake_3_6() {
	// Arrange
	GildedRose store = new GildedRose();
	store.addItem(new Item("Conjured Mana Cake", 3, 6));
	
	// Act
	store.updateEndOfDay();
	store.updateEndOfDay();
	store.updateEndOfDay();
	// Assert
	List<Item> items = store.getItems();
	Item itemCake = items.get(0);
	//assertEquals(-2, itemVest.getSellIn());
	assertEquals(3, itemCake.getQuality()); //correct
	}
	
	@Test
	public void testBrieQuality_4_49() {
	// Arrange
	GildedRose store = new GildedRose();
	store.addItem(new Item("Aged Brie", 4, 49));
	
	// Act
	store.updateEndOfDay();
	store.updateEndOfDay();

	// Assert
	List<Item> items = store.getItems();
	Item itemBrie = items.get(0);
	//assertEquals(-2, itemVest.getSellIn());
	assertEquals(50, itemBrie.getQuality()); //correct
	}
	
	@Test
	public void testElixir_5_7() {
	// Arrange
	GildedRose store = new GildedRose();
	store.addItem(new Item("Elixir of the Mongoose", 5, 7));
	
	// Act
	store.updateEndOfDay();
	store.updateEndOfDay();

	// Assert
	List<Item> items = store.getItems();
	Item itemElixir = items.get(0);
	assertEquals(5, itemElixir.getQuality()); //correct
	}
	
	@Test
	public void testMultipleItems_Sulfuras_0_80_Elixir_0_8() {
		// Arrange
		GildedRose store = new GildedRose();
		store.addItem(new Item("Sulfuras, Hand of Ragnaros", 0, 80));
		store.addItem(new Item("Elixir of the Mongoose", 0, 8));
		
		// Act
		store.updateEndOfDay();
		
		// Assert
		List<Item> items = store.getItems();
		Item itemHand = items.get(0);
		Item itemElixir = items.get(1);
		assertEquals(80, itemHand.getQuality());
		assertEquals(6, itemElixir.getQuality());
		
	}
	@Test
	public void testBrie_0_20() {
		// Arrange
		GildedRose store = new GildedRose();
		store.addItem(new Item("Aged Brie", 0, 20));
		// Act
		store.updateEndOfDay();
	
		// Assert
		List<Item> items = store.getItems();
		Item itemBrie = items.get(0);
		assertEquals(21, itemBrie.getQuality());
	}
	
	
	
}
