package pe.com.sanluis.clinicasanluis.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
//import java.sql.Time;
import java.sql.Timestamp;

/**
 * The persistent class for the cita database table.
 * 
 */
@Entity
@Table(name = "cita")
@NamedQuery(name = "Cita.findAll", query = "SELECT c FROM Cita c")
public class Cita implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_cita")
	private int idCita;

	private String estado;

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_cita")
	private Date fechaCita;

	@Column(name = "fecha_emision")
	private Timestamp fechaEmision;

	private String hora;

	private String motivo;

	// bi-directional many-to-one association to Medico
	@ManyToOne
	@JoinColumn(name = "id_medico")
	private Medico medico;

	// bi-directional many-to-one association to Paciente
	@ManyToOne
	@JoinColumn(name = "id_paciente")
	private Paciente paciente;

	public Cita() {
		this.medico = new Medico();
		this.paciente = new Paciente();
	}

	public int getIdCita() {
		return this.idCita;
	}

	public void setIdCita(int idCita) {
		this.idCita = idCita;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Date getFechaCita() {
		return this.fechaCita;
	}

	public void setFechaCita(Date fechaCita) {
		this.fechaCita = fechaCita;
	}

	public Timestamp getFechaEmision() {
		return this.fechaEmision;
	}

	public void setFechaEmision(Timestamp fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public String getMotivo() {
		return this.motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public Medico getMedico() {
		return this.medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	public Paciente getPaciente() {
		return this.paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Cita(int idCita, String estado, Date fechaCita, Timestamp fechaEmision, String hora, String motivo,
			Medico medico, Paciente paciente) {
		super();
		this.idCita = idCita;
		this.estado = estado;
		this.fechaCita = fechaCita;
		this.fechaEmision = fechaEmision;
		this.hora = hora;
		this.motivo = motivo;
		this.medico = medico;
		this.paciente = paciente;
	}

	@Override
	public String toString() {
		return "Cita [idCita=" + idCita + ", estado=" + estado + ", fechaCita=" + fechaCita + ", fechaEmision="
				+ fechaEmision + ", hora=" + hora + ", motivo=" + motivo + ", medico=" + medico + ", paciente="
				+ paciente + "]";
	}

}