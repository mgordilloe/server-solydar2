package frt.utn.solydar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SolydarServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SolydarServerApplication.class, args);
	}

}


/* Sentencias curl

xidel -s - -e "$json" deja mas lindo el json

curl -v localhost:8080/employees

curl -v localhost:8080/employees/99

curl -X POST localhost:8080/login -d "{\"username\": \"user\", \"password\": \"password\"}"

curl -X POST localhost:8080/employees -H 'Content-type:application/json' -d '{"name": "Samwise Gamgee", "role": "gardener"}'

curl -X PUT localhost:8080/employees/3 -H 'Content-type:application/json' -d '{"name": "Samwise Gamgee", "role": "ring bearer"}'

curl -X DELETE localhost:8080/employees/3
*/