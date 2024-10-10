# currencies-updater ...

This is a demo service, it works together with Currency-receiver service. 
It gets currencies from bnb.bg and saves them in a Postgres db.
To prepare the Data base, just install a Postgres server, or compose one using Docker
To build, simply run `mvn install`. It will build classes and on top of that there is `FlyWay` 
plug in, which will create tables and schemas in the Database.