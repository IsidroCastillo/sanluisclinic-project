package pe.com.sanluis.clinicasanluis.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the paciente database table.
 * 
 */
@Entity
@Table(name="paciente")
@NamedQuery(name="Paciente.findAll", query="SELECT p FROM Paciente p")
public class Paciente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_paciente")
	private int idPaciente;

	@Column(name="ape_mat")
	private String apeMat;

	@Column(name="ape_pat")
	private String apePat;

	private String direccion;

	private String dni;

	private String email;

	private String estado;

	private String names;

	private String password;

	//bi-directional many-to-one association to Cita
	@OneToMany(mappedBy="paciente")
	private List<Cita> citas;

	public Paciente() {
	}

	public int getIdPaciente() {
		return this.idPaciente;
	}

	public void setIdPaciente(int idPaciente) {
		this.idPaciente = idPaciente;
	}

	public String getApeMat() {
		return this.apeMat;
	}

	public void setApeMat(String apeMat) {
		this.apeMat = apeMat;
	}

	public String getApePat() {
		return this.apePat;
	}

	public void setApePat(String apePat) {
		this.apePat = apePat;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
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

	public String getNames() {
		return this.names;
	}

	public void setNames(String names) {
		this.names = names;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Cita> getCitas() {
		return this.citas;
	}

	public void setCitas(List<Cita> citas) {
		this.citas = citas;
	}

	public Cita addCita(Cita cita) {
		getCitas().add(cita);
		cita.setPaciente(this);

		return cita;
	}

	public Cita removeCita(Cita cita) {
		getCitas().remove(cita);
		cita.setPaciente(null);

		return cita;
	}

	public String getNombreCompleto() {
		StringBuilder sb = new StringBuilder();
		sb.append(names);
		sb.append(" ");
		sb.append(apePat);
		sb.append(" ");
		sb.append(apeMat);
		return sb.toString();
	}
}