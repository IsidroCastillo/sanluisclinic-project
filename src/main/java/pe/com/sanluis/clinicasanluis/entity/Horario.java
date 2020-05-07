package pe.com.sanluis.clinicasanluis.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the horario database table.
 * 
 */
@Entity
@Table(name = "horario")
@NamedQuery(name = "Horario.findAll", query = "SELECT h FROM Horario h")
public class Horario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_horario")
	private int idHorario;

	private String hora;

	public Horario() {
	}

	public int getIdHorario() {
		return this.idHorario;
	}

	public void setIdHorario(int idHorario) {
		this.idHorario = idHorario;
	}

	public String getHora() {
		return this.hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

}