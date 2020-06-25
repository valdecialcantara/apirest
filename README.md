Esta api usa banco de dados H2 em memória para manter clientes e cidades.


Application.properties

spring.datasource.url=jdbc:h2:file:~/clientedb

spring.datasource.driverClassName=org.h2.Driver

spring.datasource.username=sa

spring.datasource.password=

spring.jpa.database-platform=org.hibernate.dialect.H2Dialect

spring.jpa.show-sql=true

spring.jpa.properties.hibernate.format_sql=true

spring.h2.console.path=/h2-console

spring.h2.console.enabled=true

spring.h2.console.settings.trace=false

spring.h2.console.settings.web-allow-others=false


Browse

h2 web ui http://localhost:8080/h2

URL para end-points

http://localhost:8080/cidades

http://localhost:8080/cidade

http://localhost:8080/clientes

http://localhost:8080/cliente
