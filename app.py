from flask import Flask, request, jsonify
from models import agregar_paciente, agregar_registro, pacientes_collection, registros_collection
from werkzeug.exceptions import BadRequest
from bson import ObjectId  # Importa ObjectId
from pymongo import MongoClient  # Aquí está MongoClient
import os


mongo_uri = os.getenv("MONGO_URI", "mongodb://localhost:27017/")
client = MongoClient(mongo_uri)

app = Flask(__name__)

# Función de ayuda para serializar ObjectId
def json_serializable(doc):
    if isinstance(doc, list):
        return [{k: str(v) if isinstance(v, ObjectId) else v for k, v in item.items()} for item in doc]
    elif isinstance(doc, dict):
        return {k: str(v) if isinstance(v, ObjectId) else v for k, v in doc.items()}
    return doc

# Endpoint para agregar un paciente
@app.route('/pacientes', methods=['POST'])
def agregar_paciente_endpoint():
    try:
        data = request.json
        if not all(key in data for key in ["ci", "nombre", "apellido", "fecha_nacimiento", "sexo"]):
            raise BadRequest("Faltan datos obligatorios.")
        
        nuevo_paciente = agregar_paciente(
            ci=data["ci"],
            nombre=data["nombre"],
            apellido=data["apellido"],
            fecha_nacimiento=data["fecha_nacimiento"],
            sexo=data["sexo"]
        )
        if not nuevo_paciente:
            return jsonify({"mensaje": "El paciente ya existe"}), 401
        return jsonify(json_serializable(nuevo_paciente), {"mensaje": "Paciente agregado correctamente"}), 201
    except Exception as e:
        return jsonify({"mensaje": f"Error interno del servidor: {str(e)}"}), 500

# Endpoint para agregar un registro médico
@app.route('/pacientes/<ci>/registros', methods=['POST'])
def agregar_registro_endpoint(ci):
    try:
        data = request.json
        if not all(key in data for key in ["fecha", "tipo", "diagnostico", "medico", "institucion"]):
            raise BadRequest("Faltan datos obligatorios.")
        
        paciente = pacientes_collection.find_one({"_id": ci})
        if not paciente:
            return jsonify({"mensaje": "No existe un paciente con la cédula aportada."}), 402
        
        nuevo_registro = agregar_registro(
            paciente_ci=ci,
            fecha=data["fecha"],
            tipo=data["tipo"],
            diagnostico=data["diagnostico"],
            medico=data["medico"],
            institucion=data["institucion"],
            descripcion=data.get("descripcion"),
            medicacion=data.get("medicacion")
        )
        return jsonify(json_serializable(nuevo_registro), {"mensaje": "Registro médico agregado correctamente"}), 201
    except Exception as e:
        return jsonify({"mensaje": f"Error interno del servidor: {str(e)}"}), 500

# Endpoint para consultar el historial médico de un paciente
@app.route('/pacientes/<ci>/registros', methods=['GET'])
def consultar_historial_medico(ci):
    try:
        paciente = pacientes_collection.find_one({"_id": ci})
        if not paciente:
            return jsonify({"mensaje": "No existe un paciente con la cédula proporcionada."}), 404
        
        # Paginación
        pagina = int(request.args.get("pagina", 1))
        limite = int(request.args.get("limite", 10))
        registros = list(
            registros_collection.find({"pacienteCI": ci})
            .sort("fecha", -1)
            .skip((pagina - 1) * limite)
            .limit(limite)
        )

        # Convertir registros a JSON serializable
        return jsonify(json_serializable(registros))
    except Exception as e:
        return jsonify({"mensaje": f"Error interno del servidor: {str(e)}"}), 500

# Endpoint para obtener registros por criterio
@app.route('/registros', methods=['GET'])
def obtener_registros_por_criterio():
    criterios = {}
    if "tipo" in request.args:
        criterios["tipo"] = request.args["tipo"]
    if "diagnostico" in request.args:
        criterios["diagnostico"] = request.args["diagnostico"]
    if "medico" in request.args:
        criterios["medico"] = request.args["medico"]
    if "institucion" in request.args:
        criterios["institucion"] = request.args["institucion"]

    try:
        registros = list(registros_collection.find(criterios))
        return jsonify(json_serializable(registros))
    except Exception as e:
        return jsonify({"mensaje": f"Error interno del servidor: {str(e)}"}), 500

# Iniciar la aplicación
if __name__ == '__main__':
    #app.run(debug=True)
    app.run(host="0.0.0.0", port=5000)  # Esto permite que Flask escuche en todas las interfaces
