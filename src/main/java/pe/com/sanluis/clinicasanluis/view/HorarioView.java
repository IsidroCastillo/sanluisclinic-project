package pe.com.sanluis.clinicasanluis.view;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

import org.springframework.stereotype.Component;

import pe.com.sanluis.clinicasanluis.entity.Horario;
import pe.com.sanluis.clinicasanluis.repository.HorarioRepository;

@Component(value = "horarioView")
@ViewScoped
public class HorarioView implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	private HorarioRepository horarioRepository;

	private List<Horario> horarios;
	private Horario horario;

	@PostConstruct
	private void init() {
		horarios = horarioRepository.findAll();
		horario = new Horario();
	}

	public List<Horario> getHorarios() {
		return horarios;
	}

	public void setHorarios(List<Horario> horarios) {
		this.horarios = horarios;
	}

	public Horario getHorario() {
		return horario;
	}

	public void setHorario(Horario horario) {
		this.horario = horario;
	}

}
