## Brief Description

Implementation of simple news management system. There are Articles and Topics. One Topic can have multiple articles and one Article refers to one Topic.

This is a sample project demonstrating some useful technologies:

* Spring Data
* Spring MVC
* Hibernate
* Embedded HSQL DB
* Mockito

## Installation

This project uses Maven as a build system. It means if you want deploy and run this project on your own system you should install Maven first. You should run following command inside root directory of project:
```
	mvn install
	cd ./web
	mvn jetty:run
```
It starts embedded jetty server and listens on 8080 port.

## API Reference
All communications with REST API are made via HTTP

POST `/topics` - creates a topic
```
curl -i -X POST -H "Content-Type:application/json" http://localhost:8080/topics -d '{"title": "Sci-Fi"}'

HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8
Transfer-Encoding: chunked
Server: Jetty(8.1.15.v20140411)

{"title":"Sci-Fi","articles":null,"id":1}
```

GET `/topic/list` - lists all topics
```
curl -i -X GET http://localhost:8080/topics

HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8
Transfer-Encoding: chunked
Server: Jetty(8.1.15.v20140411)

[{"title":"Sci-Fi","articles":null,"id":1}]
```

DELETE `/topics/{id}` - deletes a topic by its id
```
curl -i -X DELETE http://localhost:8080/topics/1
HTTP/1.1 200 OK
Content-Type: text/plain;charset=ISO-8859-1
Content-Length: 26
Server: Jetty(8.1.15.v20140411)

Topic successfully deleted
```

POST `/topics/{topic_id}/articles` - creates an article for topic
```
curl -i -X POST -H "Content-Type:application/json" http://localhost:8080/topics/1/articles -d '{"title": "First Article", "author": "Author", "text": "Very long text"}'

HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8
Transfer-Encoding: chunked
Server: Jetty(8.1.15.v20140411)

{"id":1,"title":"First Article","author":"Author","text":"Very long text"}
```

PUT `/topics/{topic_id}/articles/{article_id}` - modifies an article for topic. For successful modification you should use existing id of article
```
curl -i -X PUT -H "Content-Type:application/json" http://localhost:8080/topics/1/articles/1 -d '{"id":"1", "title": "Modified First Article", "author": "Author", "text": "Very long text"}'
HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8
Transfer-Encoding: chunked
Server: Jetty(8.1.15.v20140411)

{"id":1,"title":"Modified First Article","author":"Author","text":"Very long text"}
```

`/topics/{topic_id}/articles/{article_id}` - shows an article by id
```
curl -i -X GET http://localhost:8080/topics/1/articles/1

HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8
Transfer-Encoding: chunked
Server: Jetty(8.1.15.v20140411)

{"id":1,"title":"Modified First Article","author":"Author","text":"Very long text"}
```

`/topics/{topic_id}/articles` - lists all articles for topic
```
curl -i -X GET http://localhost:8080/topics/1/articles

HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8
Transfer-Encoding: chunked
Server: Jetty(8.1.15.v20140411)

[{"id":1,"title":"Modified First Article","author":"Author","text":"Very long text"}]
```

`/article/delete` - deletes an article by its id
```
curl -i -X GET http://localhost:8080/article/delete?id=1
HTTP/1.1 200 OK
Content-Type: text/plain;charset=ISO-8859-1
Content-Length: 28
Server: Jetty(8.1.15.v20140411)

Article successfully deleted
```

## Tests
There are two types of tests in the project: sample unit tests in module **data** and integration-style test in module **web**.
For running tests use Maven test command
```
 	mvn clean test
```