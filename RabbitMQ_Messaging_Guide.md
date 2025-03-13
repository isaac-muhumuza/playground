## RabbitMQ setup

You need a RabbitMQ broker to connect to and facilitate asynchronous messaging. 

There's two ways to set up a broker locally:

 * Using Docker to fetch and run a RabbitMQ image:
   - `docker run -d -p 5672:5672 -p 15672:15672 --name my-rabbit rabbitmq:3-management`
   - Port 5672 will be used by the application to connect to RabbitMQ.
   - Port 15672 will be used to access the RabbitMQ management UI via either the management UI: http://localhost:15672 or the HTTP API: http://localhost:15672/api/index.html.
 * The other is via installing RabbitMQ directly on your machine:
   - Download and install RabbitMQ from the [RabbitMQ website](https://www.rabbitmq.com/docs/download#open-source-rabbitmq-server).
   - Start the RabbitMQ service.
   - Access the RabbitMQ management UI via http://localhost:15672 or the HTTP API via http://localhost:15672/api/index.html.