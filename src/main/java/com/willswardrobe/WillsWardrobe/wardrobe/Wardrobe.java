package com.willswardrobe.WillsWardrobe.wardrobe;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Wardrobe")
public class Wardrobe {
  @Id
  private String id;

  private String itemName;

  private String itemURL;
private  String imageURL;

  public String getItemName() {
    return itemName;
  }
  public String getItemURL() {
    return itemURL;
  }
  public String getImageURL() {
    return imageURL;
  }
}
