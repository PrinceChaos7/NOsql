package tarea2.historialmedico.modelo;

import org.springframework.data.annotation.Id;
import java.time.LocalDate;

public class RegistroMedico {

    @Id
    private String id;
    private LocalDate fecha;
    private String tipo; // Ejemplo: "Consulta", "Examen", "Internación"
    private String diagnostico;
    private String medico;
    private String institucion;
    private String descripcion;
    private String medicacion;

    // Constructor vacío
    public RegistroMedico() {}

    // Constructor con parámetros
    public RegistroMedico(String id, LocalDate fecha, String tipo, String diagnostico, String medico, 
                          String institucion, String descripcion, String medicacion) {
        this.id = id;
        this.fecha = fecha;
        this.tipo = tipo;
        this.diagnostico = diagnostico;
        this.medico = medico;
        this.institucion = institucion;
        this.descripcion = descripcion;
        this.medicacion = medicacion;
    }

    // Getters y Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getMedico() {
        return medico;
    }

    public void setMedico(String medico) {
        this.medico = medico;
    }

    public String getInstitucion() {
        return institucion;
    }

    public void setInstitucion(String institucion) {
        this.institucion = institucion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getMedicacion() {
        return medicacion;
    }

    public void setMedicacion(String medicacion) {
        this.medicacion = medicacion;
    }
}
