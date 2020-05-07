package pe.com.sanluis.clinicasanluis.view;

import java.io.File;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import pe.com.sanluis.clinicasanluis.entity.Especialidad;
import pe.com.sanluis.clinicasanluis.entity.Horario;
import pe.com.sanluis.clinicasanluis.entity.Medico;
import pe.com.sanluis.clinicasanluis.repository.EspecialidadRepocitory;
import pe.com.sanluis.clinicasanluis.repository.MedicoRepocitory;

@Component(value = "medicoView")
@ViewScoped
public class MedicoView implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private MedicoRepocitory medicoRepocitory;

	@Inject
	private EspecialidadRepocitory especialidadRepocitory;

	private List<Medico> medicos;
	private Medico medico;
	private Horario horario;

	@PostConstruct
	public void init() {
		medicos = medicoRepocitory.findAll();
		medico = new Medico();
	}

	@PostConstruct
	public List<Medico> getMedicosList() {
		medicos = medicoRepocitory.findAll();
		return medicos;
	}
	
	public void registrar() {
		boolean exits = medicoRepocitory.existsById(medico.getIdMedico());
		Especialidad esp = especialidadRepocitory.findById(medico.getEspecialidad().getIdEspecialidad()).get();
		medico.setEspecialidad(esp);
		medicoRepocitory.save(medico);
		if (exits) {
			addMessage("Sistema", "Se ha actualizado satisfactoriamente.");
		} else {
			addMessage("Sistema", "Se ha registrado satisfactoriamente.");
		}
		init();
	}

	public void eliminar(Medico med) {
		medicoRepocitory.delete(med);
		addMessage("Sistema", "Se ha eliminado satisfactoriamente.");
		init();
	}

	public void buscar(int id) {
		medico = medicoRepocitory.findById(id).get();
	}

	public List<Medico> getMedicos() {
		return medicos;
	}

	public void setMedicos(List<Medico> medicos) {
		this.medicos = medicos;
	}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	public Horario getHorario() {
		return horario;
	}

	public void setHorario(Horario horario) {
		this.horario = horario;
	}

	public void addMessage(String summary, String detail) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public void verPDF(ActionEvent actionEvent) throws Exception {
		File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/medicos.jasper"));

		byte[] bytes = JasperRunManager.runReportToPdf(jasper.getPath(), null,
				new JRBeanCollectionDataSource(this.getMedicosList()));
		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext()
				.getResponse();
		response.setContentType("application/pdf");
		response.setContentLength(bytes.length);
		ServletOutputStream outStream = response.getOutputStream();
		outStream.write(bytes, 0, bytes.length);
		outStream.flush();
		outStream.close();

		FacesContext.getCurrentInstance().responseComplete();
	}
}
