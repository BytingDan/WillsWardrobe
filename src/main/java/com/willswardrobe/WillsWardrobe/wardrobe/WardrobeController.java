package com.willswardrobe.WillsWardrobe.wardrobe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
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
        if (wardrobe == null) {
            return ResponseEntity.badRequest().build();
        }
        kafkaTemplate.send("wardrobeUrlTopic", wardrobe);
        return ResponseEntity.accepted().body("Message sent to Kafka");
    }

    //TODO - Update to have two topics to send to/from python

    //TODO - download the image

    @KafkaListener(topics = "wardrobeUrlTopic")
    public void listenWithHeaders(
            @Payload Wardrobe message) {
        System.out.println("Received Message");

    }

    @GetMapping("/")
    public String testBasic() {
        return "Heello";
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
