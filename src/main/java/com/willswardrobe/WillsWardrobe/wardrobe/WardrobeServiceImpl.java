package com.willswardrobe.WillsWardrobe.wardrobe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class WardrobeServiceImpl implements WardrobeService {

  @Autowired
  private WardrobeRepository wardrobeRepository;

  @Override
  public List<Wardrobe> getAllObjects() {
    return wardrobeRepository.findAll();
  }

  public void addNewItem(String itemName) {
    Wardrobe wardrobe = new Wardrobe();
    wardrobe.setItemName(itemName);
    wardrobeRepository.save(wardrobe);
  }
}
