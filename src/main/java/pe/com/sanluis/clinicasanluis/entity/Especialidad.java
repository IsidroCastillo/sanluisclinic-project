package pe.com.sanluis.clinicasanluis.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the especialidad database table.
 * 
 */
@Entity
@Table(name="especialidad")
@NamedQuery(name="Especialidad.findAll", query="SELECT e FROM Especialidad e")
public class Especialidad implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_especialidad")
	private int idEspecialidad;

	private String descripcion;

	private String estado;

	//bi-directional many-to-one association to Medico
	@OneToMany(mappedBy="especialidad")
	private List<Medico> medicos;

	public Especialidad() {
	}

	public int getIdEspecialidad() {
		return this.idEspecialidad;
	}

	public void setIdEspecialidad(int idEspecialidad) {
		this.idEspecialidad = idEspecialidad;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public List<Medico> getMedicos() {
		return this.medicos;
	}

	public void setMedicos(List<Medico> medicos) {
		this.medicos = medicos;
	}

	public Medico addMedico(Medico medico) {
		getMedicos().add(medico);
		medico.setEspecialidad(this);

		return medico;
	}

	public Medico removeMedico(Medico medico) {
		getMedicos().remove(medico);
		medico.setEspecialidad(null);

		return medico;
	}

}