package com.willswardrobe.WillsWardrobe.wardrobe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ww/v1")
@CrossOrigin
public class WardrobeController {

    private KafkaTemplate<String, Wardrobe> kafkaTemplate;

    @Autowired
    WardrobeService wardrobeService;

    @Autowired
    public WardrobeController(@Qualifier("wardrobeKafkaTemplate") KafkaTemplate<String, Wardrobe> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @PostMapping("/publish")
    public ResponseEntity<String> publish(@RequestBody Wardrobe wardrobe) {
        if (wardrobe == null) { return ResponseEntity.badRequest().build();}
        kafkaTemplate.send("wardrobeTopic", wardrobe.getItemName(), wardrobe);
        return ResponseEntity.accepted().body("Message sent to Kafka");
    }

    @GetMapping("/")
    public String testBasic() {
        return "Hello";
    }

    @GetMapping("/items")
    public ResponseEntity<?> findAllItems() {
        return ResponseEntity.ok().body(wardrobeService.getAllObjects());
    }

    @PostMapping("/add")
    public void addItem(@RequestBody String itemName) {
        wardrobeService.addNewItem(itemName);
    }
}
