This project have goal provide the HTTP-based mini game,  so register user's scores.

The project used the HashMap and LinkedHashSet collections (in order to maintain order and, consequently, determine the users' positions according to the score), as well as to obtain an O (1) complexity, very important when inserting and retrieving data.

Another point would be the use of thread pools with the Executor Framework, to mitigate competition problems, through asynchronous processing. (Executors.newSingleThreadExecutor ())

For the development, I used the springboot framework, and maven as a builder and dependency manager

I created a variable in the application.yaml to control the quantity of items returned in the highScoreList endpoint, by default this value is 20.

To run it locally:

- Run project:

    - ```mvn clean install```

    - ```mvn spring-boot:run```

- Link Swagger for help:

    - ```http://localhost:8090/pipa/mini-game-score/swagger-ui.html```


