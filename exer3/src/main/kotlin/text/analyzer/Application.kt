package text.analyzer

import io.micronaut.runtime.Micronaut.*

fun main(args: Array<String>) {
	run(*args)
}

/*datasources:
  default:
  	url: jdbc:mysql://localhost:3306/db
    driverClassName: com.mysql.cj.jdbc.Driver
    db-type: mysql
    schema-generate: CREATE_DROP
    dialect: MYSQL*/