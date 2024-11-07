package tarea2.historialmedico.exception;

public class PacienteYaExiste extends RuntimeException {
	public PacienteYaExiste(String mensaje) {
        super(mensaje);
    }
}
