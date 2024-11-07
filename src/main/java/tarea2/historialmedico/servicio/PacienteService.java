package tarea2.historialmedico.servicio;

import tarea2.historialmedico.modelo.Paciente;
import tarea2.historialmedico.modelo.RegistroMedico;
import tarea2.historialmedico.repositorio.PacienteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    public ResponseEntity<?> agregarPaciente(Paciente paciente) {
        if (pacienteRepository.existsById(paciente.getCi())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("El paciente ya existe");
        }
        pacienteRepository.save(paciente);
        return ResponseEntity.status(HttpStatus.CREATED).body("Paciente agregado exitosamente");
    }

    public ResponseEntity<?> agregarRegistroMedico(String ci, RegistroMedico registro) {
        Optional<Paciente> optionalPaciente = pacienteRepository.findById(ci);

        if (optionalPaciente.isPresent()) {
            Paciente paciente = optionalPaciente.get();
            paciente.getHistorialMedico().add(registro);
            pacienteRepository.save(paciente);
            return ResponseEntity.ok("Registro médico agregado");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un paciente con la cédula aportada");
        }
    }


    public ResponseEntity<?> consultarHistorial(String ci, int page, int size) {
        Optional<Paciente> optionalPaciente = pacienteRepository.findById(ci);

        if (optionalPaciente.isPresent()) {
            Paciente paciente = optionalPaciente.get();
            List<RegistroMedico> historial = paciente.getHistorialMedico();
            // Ordenar registros por fecha en orden descendente
            historial.sort((a, b) -> b.getFecha().compareTo(a.getFecha()));

            // Configurar paginación
            int start = Math.min(page * size, historial.size());
            int end = Math.min((page + 1) * size, historial.size());
            List<RegistroMedico> paginatedHistorial = historial.subList(start, end);

            Page<RegistroMedico> pageHistorial = new PageImpl<>(paginatedHistorial, PageRequest.of(page, size), historial.size());
            return ResponseEntity.ok(pageHistorial);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un paciente con la cédula aportada");
        }
    }


    public ResponseEntity<?> obtenerRegistrosPorCriterio(String tipo, String diagnostico, String medico, String institucion) {
        List<Paciente> pacientes = pacienteRepository.findAll();

        // Filtrar registros médicos en base a los criterios
        List<RegistroMedico> resultados = pacientes.stream()
            .map(Paciente::getHistorialMedico) // Obtener la lista de historiales
            .filter(historial -> historial != null) // Asegurarse de que no sea nulo
            .flatMap(List::stream) // Aplana cada lista de registros en un solo stream
            .filter(registro -> (tipo == null || registro.getTipo().equalsIgnoreCase(tipo)) &&
                                (diagnostico == null || registro.getDiagnostico().equalsIgnoreCase(diagnostico)) &&
                                (medico == null || registro.getMedico().equalsIgnoreCase(medico)) &&
                                (institucion == null || registro.getInstitucion().equalsIgnoreCase(institucion)))
            .collect(Collectors.toList());

        return ResponseEntity.ok(resultados);
    }


}
