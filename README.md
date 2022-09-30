# spring-mariadb-rabbit-mq-poc
POC that show how to combine Spring app + MariaDb + RabbitMQ with async batch message processing.

## Prerequisites
- Docker

## How to use:
Please run [docker compose up](https://docs.docker.com/engine/reference/commandline/compose_up/) - it will start the whole infrastructure.

Backend will be available on `localhost:8080`.

Available API:
- POST http://localhost:8080/api/v1/certificate - Will create single certificate and will put single certiface creation ivent into queue.
- DELETE http://localhost:8080/api/v1/certificate/{id} - Will create single certificate by id and will put single certiface deletion ivent into queue.
- GET http://localhost:8080/api/v1/resource-usage - Will return current resource usage. It calculated by batch RabbitMQ consumer every 30 sec (Such big interval is added for DEMO purposes).
- For more information please check application logs `docker compose logs` (`docker container logs poc_app`)
- Also you can access RabbitMQ Manager UI on `http://localhost:15672` - username and password are - `guest`

After playing with POC do not forget to stop and remove containers: [docker compose down](https://docs.docker.com.xy2401.com/compose/reference/down/)

