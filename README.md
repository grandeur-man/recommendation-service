# Recommendation Service

This service meant to help promote adoption by our customer for some of our other products.

The service expose 2 APIs,

1. Get Recommendations

curl Request

GET /customers/111113/games/recommendations?count=10 HTTP/1.1
Host: {host ip}:8080
Content-Type: application/json
Cache-Control: no-cache

Response

httpStatus Code :200

{
    "customerNumber": 111113,
    "games": [
        "bingo",
        "cashwheel",
        "cashbuster",
        "brilliant",
        "citytrio",
        "crossword",
        "sevenwins",
        "sudoku",
        "sofortlotto",
        "hattrick"
    ]
}



2. Upload Customer Recommendation

curl Request

POST /customers/games/recommendations HTTP/1.1
Host: localhost:8080
Cache-Control: no-cache
Content-Type: multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW

------WebKitFormBoundary7MA4YWxkTrZu0gW
Content-Disposition: form-data; name="file"; filename=""
Content-Type: 


------WebKitFormBoundary7MA4YWxkTrZu0gW--

Response

httpStatus Code :201 




##How to run the application

#Build docker image using docker maven plugin (You require maven setup to do this)

 --- Navigate to the project folder and open a terminal
 --- Run "mvn clean package docker:build"
 
 when the build is complete, the the docker container with the application is ready to run.
 
 --- Run "docker run -dit -p 8080:8080  esailors/recommendation-service:latest".
 
 
 Docker file is located in /src/main/docker
 Sample data file : input.csv
 
 

