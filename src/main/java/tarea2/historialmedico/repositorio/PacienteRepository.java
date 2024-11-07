package tarea2.historialmedico.repositorio;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import tarea2.historialmedico.modelo.Paciente;

@Repository
public interface PacienteRepository extends MongoRepository<Paciente, String> {
}
