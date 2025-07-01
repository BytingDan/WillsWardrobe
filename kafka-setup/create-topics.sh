sleep 10

/opt/kafka/bin/kafka-topics.sh --create --topic wardrobeUrlTopic --bootstrap-server localhost:9092

sleep 5

opt/kafka/bin/kafka-topics.sh --create --topic wardrobeInfoTopic --bootstrap-server localhost:9092

sleep 5