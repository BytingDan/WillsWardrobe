package com.willswardrobe.WillsWardrobe.wardrobe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ww/v1/")
@CrossOrigin
public class WardrobeController {
  @Autowired
  WardrobeService wardrobeService;

  @GetMapping("")
  public String testBasic() {
    return "Hello";
  }

  @GetMapping("items")
  public ResponseEntity<?> findAllItems() {
    return ResponseEntity.ok().body(wardrobeService.getAllObjects());
  }

  @PostMapping("add")
  public void addItem(@RequestBody String itemName) {
    wardrobeService.addNewItem(itemName);
  }
}
