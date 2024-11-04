package com.tuempresa.historialmedico.controlador;

import com.tuempresa.historialmedico.modelo.Paciente;
import com.tuempresa.historialmedico.modelo.RegistroMedico;
import com.tuempresa.historialmedico.servicio.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @PostMapping
    public ResponseEntity<?> agregarPaciente(@RequestBody Paciente paciente) {
        return pacienteService.agregarPaciente(paciente);
    }

    @PostMapping("/{ci}/registro")
    public ResponseEntity<?> agregarRegistroMedico(@PathVariable String ci, @RequestBody RegistroMedico registro) {
        return pacienteService.agregarRegistroMedico(ci, registro);
    }

    @GetMapping("/{ci}/historial")
    public ResponseEntity<?> consultarHistorial(@PathVariable String ci, 
                                                @RequestParam(defaultValue = "0") int page, 
                                                @RequestParam(defaultValue = "10") int size) {
        return pacienteService.consultarHistorial(ci, page, size);
    }

    @GetMapping("/buscar")
    public ResponseEntity<?> obtenerRegistrosPorCriterio(@RequestParam String tipo,
                                                         @RequestParam(required = false) String diagnostico,
                                                         @RequestParam(required = false) String medico,
                                                         @RequestParam(required = false) String institucion) {
        return pacienteService.obtenerRegistrosPorCriterio(tipo, diagnostico, medico, institucion);
    }
}
