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
    private String itemURL;
    private String imageURL;
    private String itemName;
    private String itemDescription;
    private String itemPrice;

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
