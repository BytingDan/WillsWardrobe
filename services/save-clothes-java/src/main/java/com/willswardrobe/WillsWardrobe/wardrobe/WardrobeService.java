package com.willswardrobe.WillsWardrobe.wardrobe;


import java.util.List;


public interface WardrobeService {
  List<Wardrobe> getAllObjects();
  void addNewItem(String itemName);
}
