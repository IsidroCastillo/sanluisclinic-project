package pe.com.sanluis.clinicasanluis.view;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

import org.springframework.stereotype.Component;

import pe.com.sanluis.clinicasanluis.entity.Especialidad;
import pe.com.sanluis.clinicasanluis.repository.EspecialidadRepocitory;

@Component(value = "especialidadView")
@ViewScoped
public class EspecialidadView implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EspecialidadRepocitory especialidadRepocitory;

	private List<Especialidad> especialidades;

	private Especialidad especialidad;

	@PostConstruct
	public void init() {
		especialidades = especialidadRepocitory.findAll();
		especialidad = new Especialidad();
	}

	public void registrar() {
		boolean exits = especialidadRepocitory.existsById(especialidad.getIdEspecialidad());
		especialidadRepocitory.save(especialidad);
		if (exits) {
			addMessage("Sistema", "Se ha actualizado satisfactoriamente.");
		} else {
			addMessage("Sistema", "Se ha registrado satisfactoriamente.");
		}
		init();
	}

	public void eliminar(Especialidad esp) {
		especialidadRepocitory.delete(esp);
		addMessage("Sistema", "Se ha eliminado satisfactoriamente.");
		init();
	}

	public void buscar(int id) {
		especialidad = especialidadRepocitory.findById(id).get();
	}

	public List<Especialidad> getEspecialidades() {
		return especialidades;
	}

	public Especialidad getEspecialidad() {
		return especialidad;
	}

	public void addMessage(String summary, String detail) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
}
