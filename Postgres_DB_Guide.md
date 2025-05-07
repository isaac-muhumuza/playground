## PostgreSQL setup

You need postgres DB locally installed to connect to. You can download and install from [postgreSQL](https://www.postgresql.org/download/) onto your machine.

Once installed, you can connect to it via:

* Using the `application.yml` file, edit the following properties under `# PostgreSQL connection` to connect:
    - ```
      postgres:
        url: jdbc:postgresql://localhost:5432/demoDB # connection url as explained [here](https://www.postgresql.org/docs/current/libpq-connect.html#LIBPQ-CONNSTRING-URIS), this project uses defaults and a created 'demoDB'
        jdbc_pass:                                   # Connection password
        jdbc_user: postgres                          # username
      ```