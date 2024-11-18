# Sistema de Gestión de Historial Médico

Este proyecto implementa un sistema de gestión de historial médico utilizando Flask y MongoDB como base de datos NoSQL. Permite registrar pacientes y sus historiales médicos, así como consultar y filtrar información relevante. Todo está preparado para ejecutarse en contenedores Docker.

## Tecnologías Utilizadas

- **Python 3.12**
- **Flask 2.3.2** (para la API REST)
- **MongoDB**
- **Postman** (para pruebas)
- **Docker Compose** (para orquestación de contenedores)
- **Docker** (para contenedores)
- **JMeter 5.6** (optimización de consultas)

## Instalación y Configuración

### Pre-requisitos

1. **Python 3.12**.
2. **MongoDB** en ejecución (en localhost).
3. **Docker** y **Docker Compose** instalados (si deseas ejecutar todo en contenedores).

### Instalación de Dependencias

Si deseas ejecutar la aplicación localmente sin Docker:

### 1. Clonar el Repositorio

Clona este repositorio en tu máquina:

git clone https://github.com/PrinceChaos7/NOsql
cd tu-repo

### 2. Instala las dependencias

**pip install -r requirements.txt**

### 3. Configura MongoDB

Asegurate que esté corriendo en mongodb://localhost:27017

### 4. Ejecuta la aplicación

**python app.py**

## Dockerización de la Aplicación

### 1. Clonar el Repositorio

Clona este repositorio en tu máquina:

git clone https://github.com/PrinceChaos7/NOsql
cd tu-repo

### 2. Archivos Docker

En la raíz del proyecto ya se encuentran los archivos necesarios para dockerizar la aplicación:

- **Dockerfile**: define cómo se construye la imagen de Docker para la aplicación Flask.
- **docker-compose.yml**: define los servicios que se ejecutarán (la aplicación Flask y MongoDB).

### 3. Construír y ejecutar los contenedores

Ejecuta el siguiente comando desde la carpeta raíz del proyecto para levantar los contenedores:

**docker-compose up --build**

Esto construirá las imágenes de Docker necesarias y levantará dos contenedores:

- Un contenedor para la aplicación Flask.
- Un contenedor para la base de datos MongoDB.

### 4. Interactuar con la API REST

Accede a la API REST en http://localhost:5000

### 5. Ejemplos de peticiones con Postman

Puedes utilizar Postman para realizar las siguientes operaciones:

- **POST**: Agregar paciente

- **URL**: http://localhost:5000/pacientes
- **Body(JSON)**:
				{
					"ci": "12345678",
					"nombre": "UnNombre",
					"apellido": "UnApellido",
					"fecha_nacimiento": "1999-09-09",
					"sexo": "Masculino"
				}
				
- **POST**: Agregar registro médico

- **URL**: http://localhost:5000/pacientes/{ci}/registros
- **Body(JSON)**:
				{
					"fecha": "2024-11-16",
					"tipo": "Consulta",
					"diagnostico": "Dolor abdominal",
					"medico": "Dr. Lopez",
					"institucion": "Hospital de Clínicas",
					"descripcion": "Consulta por dolor abdominal",
					"medicacion": "Bupasmol"
				}
				
- **GET**: Consultar historial médico

- **URL**: http://localhost:5000/pacientes/{ci}/registros

- **GET**: Obtener registros por criterio

- **URL**: http://localhost:5000/registros
- **Parámetros de búsqueda**:
							- **tipo:** tipo de registro (ejemplo: "consulta")
							- **diagnostico:** diagnóstico médico (ejemplo: "gripe")
							- **medico:** nombre del médico
							- **institucion:** nombre de la institución médica
							

### 6. Estructura del proyecto

- **app.py:** archivo principal de la aplicación Flask.
- **models.py:** contiene las funciones para interactuar con MongoDB.
- **requirements.txt:** lista de dependencias necesarias para el proyecto.
- **Dockerfile:** archivo para construir la imagen Docker de la aplicación.
- **docker-compose.yml:** archivo para orquestar los contenedores.
							

### 7. Ejemplo de Datos en MongoDB

**Colección: Pacientes**

{
  "_id": "12345678",
  "nombre": "UnNombre",
  "apellido": "UnApellido",
  "fecha_nacimiento": "1999-09-09",
  "sexo": "Masculino"
}

**Colección: Registros**

{
  "_id": "648f1e8c6e9a8f0001c12345",
  "pacienteCI": "12345678",
  "fecha": "2024-11-16",
  "tipo": "Consulta",
  "diagnostico": "Dolor abdominal",
  "medico": "Dr. Lopez",
  "institucion": "Hospital de Clínicas",
  "descripcion": "Consulta por dolor abdominal",
  "medicacion": "Bupasmol"
}