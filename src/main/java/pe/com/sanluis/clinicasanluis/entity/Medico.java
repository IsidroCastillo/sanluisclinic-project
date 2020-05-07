package pe.com.sanluis.clinicasanluis.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the medico database table.
 * 
 */
@Entity
@Table(name = "medico")
@NamedQuery(name = "Medico.findAll", query = "SELECT m FROM Medico m")
public class Medico implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_medico")
	private int idMedico;

	@Column(name = "ape_mat_med")
	private String apeMatMed;

	@Column(name = "ape_pat_med")
	private String apePatMed;

	private String dni;

	private String email;

	private String estado;

	private String formacion;

	private String names;
	
	// bi-directional many-to-one association to Especialidad
	@ManyToOne
	@JoinColumn(name = "id_especialidad")
	private Especialidad especialidad;

	public Medico() {
	}

	public int getIdMedico() {
		return this.idMedico;
	}

	public void setIdMedico(int idMedico) {
		this.idMedico = idMedico;
	}

	public String getApeMatMed() {
		return this.apeMatMed;
	}

	public void setApeMatMed(String apeMatMed) {
		this.apeMatMed = apeMatMed;
	}

	public String getApePatMed() {
		return this.apePatMed;
	}

	public void setApePatMed(String apePatMed) {
		this.apePatMed = apePatMed;
	}

	public String getDni() {
		return this.dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getFormacion() {
		return this.formacion;
	}

	public void setFormacion(String formacion) {
		this.formacion = formacion;
	}

	public String getNames() {
		return this.names;
	}

	public void setNames(String names) {
		this.names = names;
	}

	public Especialidad getEspecialidad() {
		return this.especialidad;
	}

	public void setEspecialidad(Especialidad especialidad) {
		this.especialidad = especialidad;
	}

	public String getNombreCompleto() {
		StringBuilder sb = new StringBuilder();
		sb.append(names);
		sb.append(" ");
		sb.append(apePatMed);
		sb.append(" ");
		sb.append(apePatMed);
		return sb.toString();
	}

}