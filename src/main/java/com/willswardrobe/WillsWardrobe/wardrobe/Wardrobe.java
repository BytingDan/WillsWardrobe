package com.willswardrobe.WillsWardrobe.wardrobe;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
//@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Wardrobe")
public class Wardrobe {
    @Id
    private String id;
    private String itemURL;
    private String imageURL;
    private String itemName;
    private String itemDescription;
    private String itemPrice;
    public Wardrobe() {

    }

    public String getId() {
        return id;
    }

    public String getItemURL() {
        return itemURL;
    }

    public String getImageURL() {
        return imageURL;
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public String getItemPrice() {
        return itemPrice;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public void setItemPrice(String itemPrice) {
        this.itemPrice = itemPrice;
    }

    public void setItemURL(String itemURL) {
        this.itemURL = itemURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
