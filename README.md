# Sistema de Gestión de Historial Médico

Este proyecto implementa un sistema de gestión de historial médico utilizando una API REST desarrollada en Spring Boot y MongoDB como base de datos NoSQL. El sistema permite registrar pacientes y sus historiales médicos, así como consultar y filtrar información relevante; según se solicita en la Tarea2.

## Tecnologías Utilizadas

- **Java 21**
- **Spring Boot**
- **MongoDB**
- **Postman** (para pruebas)
- **Swagger** (para documentación de API)
- **Docker** (para contenedores)

## Instalación y Configuración

### Pre-requisitos

1. **Java 21**.
2. **MongoDB** en ejecución (en localhost).
3. **Maven** para gestionar dependencias.
4. **Docker** y **Docker Compose** instalados (si deseas ejecutar todo en contenedores).

### Configuración de la Base de Datos

Si no utilizas Docker para MongoDB, asegúrate de tener una instancia de MongoDB corriendo en tu máquina. Si utilizas Docker, este paso no es necesario, ya que la base de datos será configurada automáticamente.

## Dockerización de la Aplicación

### 1. Clonar el Repositorio

Clona este repositorio en tu máquina:

git clone https://github.com/PrinceChaos7/NOsql
cd tu-repo

### 2. Archivos Docker

En la raíz del proyecto ya se encuentran los archivos necesarios para dockerizar la aplicación:

- **Dockerfile**: define cómo se construye la imagen de Docker para la aplicación Spring Boot
- **docker-compose.yml**: define los servicios que se ejecutarán (la aplicación Spring Boot y MongoDB)

### 3. Construír y ejecutar los contenedores

Ejecuta el siguiente comando desde la carpeta raíz del proyecto para levantar los contenedores:

**docker-compose up --build**

Esto construirá las imágenes de Docker necesarias y levantará dos contenedores:

- Un contenedor para la aplicación Spring Boot
- Un contenedor para la base de datos MongoDB

### 4. Interactuar con la API REST

Ya que la aplicación no cuenta con interfaz gráfica, los servicios serán consultados a través de servicios REST.
En este caso se utilizó Postman.

### 5. Ejemplos de peticiones con Postman

Puedes utilizar Postman para realizar las siguientes operaciones:

- **POST**: Agregar paciente

- **URL**: http://localhost:8080/api/pacientes
- **Body(JSON)**:
				{
					"ci": "12345678",
					"nombre": "UnNombre",
					"apellido": "UnApellido",
					"fechaNacimiento": "1999-09-09",
					"sexo": "Masculino",
					"historialMedico": [
					{
						"id": "registro01",
						"fecha": "2024-11-16", 
						"tipo": "Consulta",
						"diagnostico": "Dolor abdominal",
						"medico": "Dr. Lopez",
						"institucion": "Hospital de Clínicas",
						"descripcion": "Consulta por un dolor de abdomen",
						"medicacion": "Bupasmol"
					}
					]
				}
				
- **POST**: Agregar registro médico

- **URL**: http://localhost:8080/api/pacientes/{ci}/registro
- **Body(JSON)**:
				{
					"id": "registro01",
					"fecha": "2024-11-16",
					"tipo": "Consulta",
					"diagnostico": "Gripe",
					"medico": "Dra. Susana",
					"institucion": "Hospital Central",
					"descripcion": "Signos de estado gripal",
					"medicacion": "Paracetamol"
				}
				
- **GET**: Consultar historial médico

- **URL**: http://localhost:8080/api/pacientes/{ci}/historial

- **GET**: Obtener registros por criterio

- **URL**: http://localhost:8080/api/pacientes/buscar?tipo=Consulta
- **Parámetros de búsqueda**:
							- **tipo:** tipo de registro (ejemplo: "consulta")
							- **diagnostico:** diagnóstico médico (ejemplo: "gripe")
							- **medico:** nombre del médico
							- **institucion:** nombre de la institución médica
							