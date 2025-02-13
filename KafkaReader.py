from confluent_kafka import Consumer
import requests
from bs4 import BeautifulSoup
import json

KAFKA_BROKER = "localhost:9092"
TOPIC = "wardrobeTopic"
GROUP_ID = "url_scraper_group"

consumer_config = {
    "bootstrap.servers": KAFKA_BROKER,
    "group.id":GROUP_ID,
    "auto.offset.reset": "earliest"
}

def consume_messages():
    consumer =Consumer(consumer_config)
    consumer.subscribe([TOPIC])

    print("Listening for messages on topic:", TOPIC)

    try:
        while True:
            msg = consumer.poll(1.0)
            if msg is None:
                continue
            if msg.error():
                print(f"Consumer error: {msg.error()}")
                continue

            print(type(msg))
            print(type(msg.value()))
            print(type(msg.key()))
            decoded = msg.value().decode("utf-8")
            print(f"Received Message: {decoded}")

            try:
                message_json = json.loads(decoded)
                print("Parsed JSON: ", message_json)

                url = message_json['itemName']
                scrap_page(url)
            except json.JSONDecodeError as e:
                print(f"Error parsing JSON: {e}")


    except KeyboardInterrupt:
        print("\nShutting down consumer...")
    finally:
        consumer.close()


def scrap_page(url):
    try:
        response = requests.get(url, headers={"User-Agent":"Mozilla/5.0"})
        response.raise_for_status()
        soup = BeautifulSoup(response.text, "html.parser")

        title = soup.title.string if soup.title else "No title found"
        meta_desc = soup.find("meta", attrs={"name":"description"})
        description = meta_desc["content"] if meta_desc else "No Description Found"

        print(f"Title: {title}")
        print(f"Description: {description}")

        images = [img["src"] for img in soup.find_all("img", src = True)]
        print(f"Found {len(images)} images.")

    except requests.exceptions.RequestException as e:
        print(f"Failed to fetch URL: {e}")

if __name__ == '__main__':
    consume_messages()