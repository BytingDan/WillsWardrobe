from confluent_kafka import Consumer, Producer
import requests
import random
from bs4 import BeautifulSoup
import json

KAFKA_BROKER = "localhost:9092"
SUBSCRIBE_TOPIC = "wardrobeUrlTopic"
PUBLISH_TOPIC = "wardrobeInfoTopic"
GROUP_ID = "url_scraper_group"

consumer_config = {
    "bootstrap.servers": KAFKA_BROKER,
    "group.id": GROUP_ID,
    "auto.offset.reset": "earliest"
}
producer_config = {
    "bootstrap.servers": KAFKA_BROKER
}


def consume_messages():
    consumer = Consumer(consumer_config)
    consumer.subscribe([SUBSCRIBE_TOPIC])

    print("Listening for messages on topic:", SUBSCRIBE_TOPIC)

    try:
        while True:
            msg = consumer.poll(1.0)
            if msg is None:
                continue
            if msg.error():
                print(f"Consumer error: {msg.error()}")
                continue

            # print(type(msg))
            # print(type(msg.value()))
            # print(type(msg.key()))
            decoded = msg.value().decode("utf-8")
            print(f"Received Message: {decoded}")

            try:
                message_json = json.loads(decoded)
                print("Parsed JSON: ", message_json)

                url = message_json['itemURL']
                image_url = message_json['imageURL']
                scrap_page(url, image_url)
            except json.JSONDecodeError as e:
                print(f"Error parsing JSON: {e}")

    except KeyboardInterrupt:
        print("\nShutting down consumer...")
    finally:
        consumer.close()


def scrap_page(url, image_url):
    try:
        print("******")
        user_agents = [
            "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36",
            "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.212 Safari/537.36",
            "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.114 Safari/537.36"
        ]
        headers = {"User-Agent": random.choice(user_agents)}
        print("Sending Request to: ", url)

        #*******
        # response = requests.get(url, headers=headers)
        # print("Got response: ", response)
        # response.raise_for_status()
        # soup = BeautifulSoup(response.text, "html.parser")
        #
        # title = soup.title.string if soup.title else "No title found"
        # # print(f"Title: {title}")
        # meta_desc = soup.find("meta", attrs={"name": "description"})
        # description = meta_desc["content"] if meta_desc else "No Description Found"
        # # print(f"Description: {description}")
        #
        # price = soup.find("span", class_="edbe20 ac3d9e d9ca8b").text.strip()
        # # print("Price: ", price)
        #
        # # colour = soup.find("span", class_="product-color").text.strip()
        # # print("Colour:", colour)
        #
        # images = [img["src"] for img in soup.find_all("img", src=True)]
        # # print(f"Found {len(images)} images.")
        # # print("Images:)
        #
        # # print("image URL: ", image_url)
        #
        # # TODO data to extrac
        # # -> Title, Images, colours, sizes, price
        #
        # # TODO combine data into object
        # # send data to kafka

        # clothes_data = {
        #     "itemName": title,
        #     "itemDescription": description,
        #     "itemPrice": price,
        #     "imageURL": image_url,
        #     "itemURL": url
        # }
        #******
        clothes_data = {
            "itemName": "new clothing",
            "itemDescription": "clothe",
            "itemPrice": "1 billion",
            "imageURL": url,
            "itemURL": image_url
        }
        #     private String itemURL;
        #     private String imageURL;
        #     private String itemName;
        #     private String itemDescription;
        #     private String itemPrice;
        clothes_json = json.dumps(clothes_data, indent=4)

        publish_scraped_data(clothes_json)

    except requests.exceptions.RequestException as e:
        print(f"Failed to fetch URL: {e}")


def publish_scraped_data(clothes_json):
    print(clothes_json)
    producer = Producer(producer_config)
    producer.produce(PUBLISH_TOPIC, clothes_json, callback=delivery_report)
    producer.flush()


def delivery_report(err, msg):
    if err is not None:
        print(f"Delivery failed: {err}")
    else:
        print(f"Message Delivered")


if __name__ == '__main__':
    consume_messages()
