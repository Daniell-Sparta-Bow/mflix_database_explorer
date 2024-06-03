# Read me


This project creates a Java application that uses a MongoDB database which contains a list of movies, movie comments, reviews and users along with other details. This application allows users to query certain fields within the database, while following the spring architecture layout: Entities->Repository->Service->Controller We implement RestAPIs and endpoints to allow the following CRUD methods.

    GET /web/movies/add-city: Adds a new movie.
    GET /web/movie: Retrieves all movies.
    GET /web/movie/{id}: Retrieves a movie by its ID.
    GET /web/movie/delete/{id}: Deletes a movie by its ID.

    The above is similar for the comments and theaters and users controller
    
# Acceptance Criteria

    Interact with the MongoDb mflix World Database
    Use basic CRUD operations
    Provide multiple types of search methods
    Implement the service layer in the application
    Tested with WebMVCTests
    Secure endpoints with an API
    Error handling of API endpoints
    Front end website to interact with mflix Database


# Dependencies
JDK 21, JUnit, Thymeleaf, SpringBoot, Spring Reactive Web, Rest Repositories, Mongo DB Driver

# Connecting to your database
<h2>Connecting to your database</h2>


Firstly, please ensure that you have connected to the mflix mongoDB database. To access it please follow the instructions at the link: https://www.mongodb.com/
You will need to create an account and load sample data. 

To link to your database please fill out the following in your application.properties file.  

```
spring.application.name=<PROJECT NAME>
spring.data.mongodb.database=sample_mflix
spring.data.mongodb.uri= <URL>
 
```
# How to use the Program

Open the project directory and ensure the server is running. We recommend you use the User Interface we create to interact with the data. However, may you prefer to do so, there's an available Rest API you can use to investigate the data. 

📫 If you encounter any bugs, please open up an issue to let us know.
Alternatively, we welcome suggestions for any updates or improvements you would like to see! 
