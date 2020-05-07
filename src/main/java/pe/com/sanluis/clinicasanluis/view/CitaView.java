package pe.com.sanluis.clinicasanluis.view;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

import org.springframework.stereotype.Component;

import pe.com.sanluis.clinicasanluis.entity.Cita;
import pe.com.sanluis.clinicasanluis.entity.Medico;
import pe.com.sanluis.clinicasanluis.entity.Paciente;
import pe.com.sanluis.clinicasanluis.repository.CitaRepository;
//import pe.com.sanluis.clinicasanluis.repository.EspecialidadRepocitory;
import pe.com.sanluis.clinicasanluis.repository.MedicoRepocitory;
import pe.com.sanluis.clinicasanluis.repository.PacienteRepository;

@Component(value = "citaView")
@ViewScoped
public class CitaView implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CitaRepository citaRepository;

	@Inject
	private MedicoRepocitory medicoRepocitory;

	@Inject
	private PacienteRepository pacienteRepocitory;

//	@Inject
//	private EspecialidadRepocitory especialidadRepocitory;

	private List<Cita> citas;
	private Cita cita;
	private Medico medico;
	private Paciente paciente;

	@PostConstruct
	public void init() {
		citas = citaRepository.findAll();
		cita = new Cita();
		medico = new Medico();
		paciente = new Paciente();
	}

	public void registrar() {
		boolean exits = citaRepository.existsById(cita.getIdCita());
		Medico med = medicoRepocitory.findById(cita.getMedico().getIdMedico()).get();
		cita.setMedico(med);
		Paciente pac = pacienteRepocitory.findById(cita.getPaciente().getIdPaciente()).get();
		cita.setPaciente(pac);
		Date utilDate = new Date();
		long lnMilisegundos = utilDate.getTime();
		Timestamp fechaEmision = new Timestamp(lnMilisegundos);
		cita.setFechaEmision(fechaEmision);
		cita.setEstado("H");
		citaRepository.save(cita);
		if (exits) {
			addMessage("Sistema", "Se ha actualizado satisfactoriamente.");
		} else {
			addMessage("Sistema", "Se ha registrado satisfactoriamente.");
		}
		init();
	}

	public void eliminar(int id) {
		citaRepository.deleteById(id);
		addMessage("Sistema", "Se ha eliminado satisfactoriamente.");
		init();
	}

	public void buscar(int id) {
		cita = citaRepository.findById(id).get();
	}

	public List<Cita> getCitas() {
		return citas;
	}

	public void setCitas(List<Cita> citas) {
		this.citas = citas;
	}

	public Cita getCita() {
		return cita;
	}

	public void setCita(Cita cita) {
		this.cita = cita;
	}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public void addMessage(String summary, String detail) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
}
