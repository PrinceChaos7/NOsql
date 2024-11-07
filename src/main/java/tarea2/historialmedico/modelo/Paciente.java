package tarea2.historialmedico.modelo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Document(collection = "pacientes")
public class Paciente {

    @Id
    private String ci;
    private String nombre;
    private String apellido;
    private String fechaNacimiento;
    private String sexo;
    private List<RegistroMedico> historialMedico;

    // Constructor vacío
    public Paciente() {}

    // Constructor con parámetros
    public Paciente(String ci, String nombre, String apellido, String fechaNacimiento, String sexo, List<RegistroMedico> historialMedico) {
        this.ci = ci;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.sexo = sexo;
        this.historialMedico = historialMedico;
    }

    // Getters y Setters
    public String getCi() {
        return ci;
    }

    public void setCi(String ci) {
        this.ci = ci;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public List<RegistroMedico> getHistorialMedico() {
        return historialMedico;
    }

    public void setHistorialMedico(List<RegistroMedico> historialMedico) {
        this.historialMedico = historialMedico;
    }
}
