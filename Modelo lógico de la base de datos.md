**Entidades y Atributos**

**1\. Paciente**

* **ci:** cédula de identidad (clave primaria)  
* **nombre:** nombre del paciente  
* **apellido:** apellido del paciente  
* **fechaNacimiento:** fecha de nacimiento  
* **sexo:** sexo del paciente  
* **historialMedico:** lista de registros médicos

**2\. RegistroMedico**

* **id:** identificación del registro médico (clave primaria)  
* **fecha:** fecha del registro  
* **tipo:** tipo de consulta (ej. consulta, examen, internación)  
* **diagnostico:** diagnóstico de la consulta  
* **medico:** nombre del médico  
* **institucion:** nombre de la institución donde se realizó la consulta  
* **descripcion:** descripción de la consulta o síntomas  
* **medicación:** nombre de la medicación

**Representación en MongoDB**

Para representar este modelo se podía optar por anidar los registros médicos dentro de los documentos de los pacientes, lo que facilita la consulta de la información relacionada.

**Paciente:**

{  
	"paciente": {  
		"ci": "78945612",  
		"nombre": "Martín",  
		"apellido": "Sosa",  
		"fechaNacimiento": "1996-12-15",  
		"sexo": "Masculino",  
		"historialMedico": \[  
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
		\]  
	}  
}

Si se quisiera mantener los registros médicos como documentos separados para evitar duplicar información, se tendría una estructura así:

**RegistroMedico:**

{  
	"id": "registro02",  
	"fecha": "2024-11-17",  
	"tipo": "Examen",  
	"diagnostico": "Anemia"  
	"medico": "Dra. Susana",  
	"institucion": "Hospital Central",  
	"descripcion": "Examen de sangre para detectar anemia",  
	"medicacion": "Hierro"  
}

**Justificación de la estructura**

- **Anidamiento:** permite tener toda la información del paciente junto con su historial médico, facilitando las consultas.

- **Referencias:** almacenando los registros médicos por separado, se evita duplicar información y es más fácil actualizar registros médicos sin afectar el documento del paciente.

