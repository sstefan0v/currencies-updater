# currencies-updater service

The purpose of this service is to fetch  currencies from bnb.bg and save them in a Postgres db. It will also send the 
most recent currencies to another service, Currency-receiver (https://github.com/sstefan0v/currencies-receiver). 
For that purpose, Currency-receiver has to be started up and running in advance.

#### Requirements:
- Docker environment
- Java 23

#### Used versions:
- Java 23 2024-09-17
- Spring Boot 3.3.4 
- PostgreSQL 17
- Apache Maven 3.9.4

#### Build:
To build, simply run `mvn install`. This will build all classes, run tests, run Docker and stir up
a Postgres Docker container. Then `FlyWay` plugin will kick off and create tables and schemas in the Database.

#### Usage:
To use the service, simply open a browser and execute http://{hostname}:8011/download-currencies.

#### Demo requirements:
Направете Java Spring микросървис, който да има един REST ендпойнт – “/download-currencies”. Този ендпойнт може да бъде викан много на брой пъти, всеки ден. При хитване на ендпойнта, микросървиса трябва да свали най-актуалната информация за всички валутни курсове от БНБ ( за справка: Българска народна банка (bnb.bg)), в xml формат, но само само ако има промяна на валутните курсове от страна на БНБ. Tози сървис трябва да записва хронологично информацията за всички свалени валутни курсове в PostgreSQL база данни. След това, трябва да изпрати информация само за най-актуално свалените валутни курсове по websocket в JSON формат, като за целта този сървис, трябва да бъде и websocket server. Валутните курсове, да бъдат записани с Българските и с Английските им имена и така да бъдат предавани по websocket.
Направете втори сървис, който да чете данните изпратени от гореописаният сървис и отново да записва данните в PostgreSQL таблица.
(untitled)