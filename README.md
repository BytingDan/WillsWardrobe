# WillsWardrobe
Prototype for app to create outfits.

Basic idea is to save clothing that you like into the app and be able to play around with creating outfits that you like before wearing
Prototyping first to see if some of the requested features are possible

Prototype reqs
User -> Press the “Search button” on the web-page of a clothing app and see the clothing details (image of the clothing, size, price, URL) appear inside the app

Tools ->
1. Flutter Mobile app (for iOS and Android) for displaying user's outfits
2. Java Spring Boot app as a backend
3. Kafka Queue for sending data to Python app
4. Python/BeautifulSoup app for retrieving information
5. Database (Mongo?) for storing details of clothes saved (probably need blob storage in future for saving images)

Branches:
- flutter-ui -> Frontend mobile app
- python-scaper -> Web scraper for scraping data from clothing website
- spring-backend -> Java backend for handling communication between services/databases


Steps:
- Create UI on phone (simple flutter app)
- Create Spring app with 1/2 endpoints.
- Create Kafka queue for sending info
- Create Python app to read the Kafka
- Create Mongo for data to be stored
