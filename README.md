# Getting Started

### Guides
The following guides illustrate how to set-up and run the project on your local machine:

* [MongoDB Guide](Mongo_DB_Guide.md)
* [Postgres DB Guide](Postgres_DB_Guide.md)
* [Messaging with RabbitMQ](RabbitMQ_Messaging_Guide.md)

### Maven Parent overrides

Due to Maven's design, elements are inherited from the parent POM to the project POM.
While most of the inheritance is fine, it also inherits unwanted elements like `<license>` and `<developers>` from the parent.
To prevent this, the project POM contains empty overrides for these elements.
If you manually switch to a different parent and actually want the inheritance, you need to remove those overrides.

