## Service Broker for [Map Server](https://github.com/Pivotal-Japan/map-server)


### Deploy Service Broker

After pushing [map server](https://github.com/Pivotal-Japan/map-server#run-on-pivotal-web-services)

``` console
$ ./mvnw clean package -DskipTests=true
$ cf push map-server-service-broker -p target/map-server-service-broker-0.0.1-SNAPSHOT.jar -m 512m -b java_buildpack --no-start
$ cf set-env SECURITY_USER_NAME map
$ cf set-env SECURITY_USER_PASSWORD map
$ cf set-env MAP_SERVICE_ADMIN_USERNAME admin
$ cf set-env MAP_SERVICE_ADMIN_PASSWORD admin
$ cf set-env MAP_SERVICE_URI http://map-server.cfapps.io
$ cf start map-server-service-broker
```

### Enable Service Broker

``` console
$ cf create-service-broker p-map map map http://map-server-service-broker.cfapps.io --space-scoped
```


### Use Map Server Service

``` console
$ cd example
$ cf create-service p-map free map
$ cf push
```

* Put current time to the map service instance [http://map-server-client.cfapps.io/?update](http://map-server-client.cfapps.io/?update)
* Get the time from the map service instance [http://map-server-client.cfapps.io/](http://map-server-client.cfapps.io/)