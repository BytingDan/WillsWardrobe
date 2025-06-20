FROM apache/kafka:4.0.0

COPY create-topics.sh /opt/kafka/create-topics.sh
RUN chmod +x /opt/kafka/create-topics.sh

CMD sh -c "/opt/kafka/bin/kafka-server-start.sh /opt/kafka/config/server.properties &/opt/kafka/create-topics.sh"

