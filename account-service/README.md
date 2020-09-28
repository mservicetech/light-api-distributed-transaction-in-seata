# Account service API

## Build and Start

For testing locally, you don't need to create the artifact for the document, source code, and the fatjar. You can build and start the server with the following command.

```
mvn clean install exec:exec
```

or

```
mvn clean package exec:exec
```

If you want to build the fatjar and other artifacts, please use the following command.

```
mvn clean install -Prelease
```

With the fatjar in the target directory, you can start the server with the following command.

```
java -jar target/fat.jar
```

## Test


```
curl -k https://localhost:8443/v1/pets
```

