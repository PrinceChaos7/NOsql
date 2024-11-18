**1\.Entidades y Atributos**

**Paciente**

* **_id:** Clave primaria, cédula de identidad del paciente (CI). 
* **nombre:** nombre del paciente  
* **apellido:** apellido del paciente  
* **fecha_nacimiento:** fecha de nacimiento  
* **sexo:** sexo del paciente  
* **historialMedico(opcional):** Lista anidada de registros médicos asociados

**RegistroMedico**

* **_id:** Clave primaria (generada automáticamente por MongoDB si está en una colección separada). 
* **pacienteCI:** Referencia al CI del paciente asociado (clave foránea lógica).
* **fecha:** fecha del registro  
* **tipo:** tipo de consulta (ej. consulta, examen, internación)  
* **diagnostico:** diagnóstico de la consulta  
* **medico:** nombre del médico  
* **institucion:** nombre de la institución donde se realizó la consulta  
* **descripcion:** descripción de la consulta o síntomas (opcional)  
* **medicación:** nombre de la medicación (opcional)

**2\.Representación en MongoDB**

**Colección: Pacientes**


{
  "_id": "78945612",
  "nombre": "Martín",
  "apellido": "Sosa",
  "fecha_nacimiento": "1996-12-15",
  "sexo": "Masculino"
}


**Colección: Registros**

{
  "_id": "648f1e8c6e9a8f0001c12345",
  "pacienteCI": "78945612",
  "fecha": "2024-11-16",
  "tipo": "Consulta",
  "diagnostico": "Dolor abdominal",
  "medico": "Dr. Lopez",
  "institucion": "Hospital de Clínicas",
  "descripcion": "Consulta por dolor abdominal",
  "medicacion": "Bupasmol"
}

**3\.Justificación del modelo**

- **Separación en colecciones:** 
Los pacientes y los registros están en colecciones diferentes para mantener escalabilidad y evitar duplicación de datos.
La relación lógica entre ellos se mantiene mediante el campo pacienteCI

- **Generación automática de _id en Registros:**
Esto permite que MongoDB gestione identificadores únicos para los registros médicos.

- **Consultas típicas:**
Obtener todos los registros médicos de un paciente: registros_collection.find({"pacienteCI": "78945612"})
Obtener la información de un paciente junto con sus registros: paciente = pacientes_collection.find_one({"_id": "78945612"})
registros = list(registros_collection.find({"pacienteCI": "78945612"}))
