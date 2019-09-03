#Local config

#Data base

Postgres

```
sudo docker run --name challenge -e POSTGRES_PASSWORD=123123 -e POSTGRES_USER=challenge -p 5432:5432 -d postgres
```

If you don't wanna a postgres instance through Docker, the application simply expects the following: 

```
database instance: postgres
postgres port: 5432
username: challenge
password: 123123
schema: challenge
```

#API

When you run the application for the first time, the tables are gonna be created
by the `flyway migrations`.
An User will be created with the following credentials:

```
username: challenge
password: challenge
```

URL to get the token:

```
/public/auth?username=challenge&password=challenge
```

The token will be placed on the response's HEADER (when successful).
In order to facilitate the tests, this token may last for around one day.

#Documentation

The application's base path (/) will redirect you to the API's documentation.