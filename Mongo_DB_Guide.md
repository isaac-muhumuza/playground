## MongoDB setup

You need mongoDB locally installed to connect and use. 
You can download and install from [MongoDB](https://www.mongodb.com/docs/manual/installation/) onto your machine.

Once installed, you can connect to it via:

* Using the `application.yml` file, edit the following properties under `# MongoDB connection` to connect:
    - ```
      mongodb:
        uri: mongodb://localhost:27017/demoDB # connection uri as explained [here](https://www.mongodb.com/docs/manual/reference/connection-string/#std-label-connections-standard-connection-string-format), this project uses defaults and a created 'demoDB'
        host: localhost
        port: 27017
        database: demoDB
        user: root
        password:
      ```