package pe.com.sanluis.clinicasanluis.view;

import java.io.File;
import java.io.IOException;
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

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRPptxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
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

	public void exportarPDF(ActionEvent actionEvent) throws JRException, IOException {
		File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/medicos.jasper"));
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), null,
				new JRBeanCollectionDataSource(this.getMedicosList()));

		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext()
				.getResponse();
		response.addHeader("Content-disposition", "attachment; filename=jsfReporte.pdf");
		ServletOutputStream stream = response.getOutputStream();

		JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
		stream.flush();
		stream.close();

		FacesContext.getCurrentInstance().responseComplete();
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
//	pageHeader

	public void exportarExcel(ActionEvent actionEvent) throws JRException, IOException {
		File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/medicos.jasper"));
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), null,
				new JRBeanCollectionDataSource(this.getMedicosList()));

		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext()
				.getResponse();
		response.addHeader("Content-disposition", "attachment; filename=jsfReporte.xls");
		ServletOutputStream outStream = response.getOutputStream();

		JRXlsExporter exporter = new JRXlsExporter();

		exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outStream));
		exporter.exportReport();

		outStream.flush();
		outStream.close();
		FacesContext.getCurrentInstance().responseComplete();
	}

	public void exportarDOC(ActionEvent actionEvent) throws JRException, IOException {
		File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/medicos.jasper"));
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), null,
				new JRBeanCollectionDataSource(this.getMedicosList()));

		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext()
				.getResponse();
		response.addHeader("Content-disposition", "attachment; filename=jsfReporte.doc");
		ServletOutputStream outStream = response.getOutputStream();

		JRDocxExporter exporter = new JRDocxExporter();
		exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outStream));
		exporter.exportReport();

		outStream.flush();
		outStream.close();
		FacesContext.getCurrentInstance().responseComplete();
	}

	public void exportarPPT(ActionEvent actionEvent) throws JRException, IOException {
		File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/medicos.jasper"));
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), null,
				new JRBeanCollectionDataSource(this.getMedicosList()));

		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext()
				.getResponse();
		response.addHeader("Content-disposition", "attachment; filename=jsfReporte.ppt");
		ServletOutputStream outStream = response.getOutputStream();

		JRPptxExporter exporter = new JRPptxExporter();
		exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outStream));
		exporter.exportReport();

		outStream.flush();
		outStream.close();
		FacesContext.getCurrentInstance().responseComplete();
	}
}
