from pymongo import MongoClient
from datetime import datetime

# Conexión a MongoDB
client = MongoClient("mongodb://mongo:27017")
db = client.historialMedicoDB

# Colecciones
pacientes_collection = db.pacientes
registros_collection = db.registros

# Funciones de ayuda para los modelos

def agregar_paciente(ci, nombre, apellido, fecha_nacimiento, sexo):
    if pacientes_collection.find_one({"_id": ci}):
        return None  # Paciente ya existe
    nuevo_paciente = {
        "_id": ci,
        "nombre": nombre,
        "apellido": apellido,
        "fecha_nacimiento": fecha_nacimiento,
        "sexo": sexo
    }
    pacientes_collection.insert_one(nuevo_paciente)
    return nuevo_paciente

def agregar_registro(paciente_ci, fecha, tipo, diagnostico, medico, institucion, descripcion=None, medicacion=None):
    if not pacientes_collection.find_one({"_id": paciente_ci}):
        return None  # Paciente no existe
    nuevo_registro = {
        "pacienteCI": paciente_ci,
        "fecha": fecha,
        "tipo": tipo,
        "diagnostico": diagnostico,
        "medico": medico,
        "institucion": institucion,
        "descripcion": descripcion,
        "medicacion": medicacion
    }
    # Inserta el nuevo registro en la base de datos
    result = registros_collection.insert_one(nuevo_registro)
    # Retorna el documento con el _id que MongoDB genera automáticamente
    nuevo_registro["_id"] = result.inserted_id
    return nuevo_registro