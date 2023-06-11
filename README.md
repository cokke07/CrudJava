# Proyecto: API Personas (CRUD)

_El CRUD tiene como objetivo, exponer una API que pueda Registar un Objeto de tipo Usuario en un llamado por Postman_

## Tecnologias Utilizdas 🚀
* Spring Boot
* Java 11
* JPA 
* Lombok
* Gradle
* Base de datos en memoria H2
* OpenAPI
* Swagger
* JUnit5
* Mockito

### Pre-requisitos 📋

_Para poner en marcha el proyecto, se requiere ir al repositorio y descargarlo en ZIP o clonarlo a un repositorio local como
se muestra en el ejemplo a continuación:_

```
git clone https://github.com/cokke07/CrudJava.git
```
_Despúes se debe abrir en el IDE para que se realice la descarga automatica de dependencias_
### Instalación 🔧

_Dentro del proyecto se subio la coleccion de Postman, a fin de poder realizar pruebas del proyecto._

_Ademas cuenta con la documentacion en Swagger, en caso que no se tenga disponible Postman, se puede ejecutar el proyecto y probarlo en la siguiente pagina al momento de comenzar a correr el API:_

```
http://localhost:9095/swagger-ui/index.html
```

_Se recomienda primero insertar un Usuario en el endpoint de "crear"
como se muestra en el siguiente ejemplo:_

```
{
    "name":"Nombre Apellido",
    "email": "prueba@mail.com",
    "password":"Clav1234",
    "phones":[
        {
            "number":"123456789",
            "citycode":"56",
            "countrycode":"9"
        }
    ]
}
```

_Ejecutando ese Endpoint de "Crear" se pueden ir probando los otros EndPoint_

### Analisis las pruebas 🔩

_El proyecto cuenta con algunas pruebas unitarias para validar los servicios y respuestas_


## Autor ✒️

* **Jorge Gómez** - *Ingeniero en Software* 


---
Realizado por [Cokke07](https://github.com/Cokke07) 😊