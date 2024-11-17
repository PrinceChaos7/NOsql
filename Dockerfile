FROM python:3.12-slim

# Define el directorio de trabajo en el contenedor
WORKDIR /app

# Copiar los archivos del proyecto al contenedor
COPY . /app

# Instalar las dependencias
RUN pip install --no-cache-dir -r requirements.txt

# Exponer el puerto en el contenedor
EXPOSE 5000

# Comando para ejecutar la aplicación
CMD ["python", "app.py"]
