package com.tuempresa.historialmedico.repositorio;

import com.tuempresa.historialmedico.modelo.Paciente;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepository extends MongoRepository<Paciente, String> {
}
