//package pe.com.sanluis.clinicasanluis.bean;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import javax.faces.context.FacesContext;
//import javax.faces.event.ActionEvent;
//import javax.faces.view.ViewScoped;
//import javax.servlet.ServletOutputStream;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.stereotype.Component;
//
//import net.sf.jasperreports.engine.JRException;
//import net.sf.jasperreports.engine.JRExporterParameter;
//import net.sf.jasperreports.engine.JasperExportManager;
//import net.sf.jasperreports.engine.JasperFillManager;
//import net.sf.jasperreports.engine.JasperPrint;
//import net.sf.jasperreports.engine.JasperRunManager;
//import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
//import net.sf.jasperreports.engine.export.JRXlsExporter;
//import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
//import net.sf.jasperreports.engine.export.ooxml.JRPptxExporter;
//import pe.com.sanluis.clinicasanluis.entity.Persona;
//
//@Component(value = "personaBean")
//@ViewScoped
//public class PersonaBean {
//
//	private List<Persona> personas = new ArrayList<Persona>();
//
//	public List<Persona> getPersonas() {
//		Persona per = new Persona();
//		per.setNombres("Mito");
//		per.setApellidos("Code");
//		Calendar cal = Calendar.getInstance();
//		cal.set(1991, 1, 21);
//		per.setFechaNacimiento(cal.getTime());
//		personas.add(per);
//
//		per = new Persona();
//		per.setNombres("Jaime");
//		per.setApellidos("MD");
//		cal = Calendar.getInstance();
//		cal.set(1990, 5, 21);
//		per.setFechaNacimiento(cal.getTime());
//		personas.add(per);
//
//		return personas;
//	}
//
//	public void setPersonas(List<Persona> personas) {
//		this.personas = personas;
//	}
//
//	public void exportarPDF(ActionEvent actionEvent) throws JRException, IOException {
//		Map<String, Object> parametros = new HashMap<String, Object>();
//		parametros.put("txtUsuario", "MitoCode");
//
//		File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/rptJSF.jasper"));
//		JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros,
//				new JRBeanCollectionDataSource(this.getPersonas()));
//
//		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext()
//				.getResponse();
//		response.addHeader("Content-disposition", "attachment; filename=jsfReporte.pdf");
//		ServletOutputStream stream = response.getOutputStream();
//
//		JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
//
//		stream.flush();
//		stream.close();
//		FacesContext.getCurrentInstance().responseComplete();
//	}
//
//	public void exportarExcel(ActionEvent actionEvent) throws JRException, IOException {
//		Map<String, Object> parametros = new HashMap<String, Object>();
//		parametros.put("txtUsuario", "MitoCode");
//
//		File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/rptJSF.jasper"));
//		JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros,
//				new JRBeanCollectionDataSource(this.getPersonas()));
//
//		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext()
//				.getResponse();
//		response.addHeader("Content-disposition", "attachment; filename=jsfReporte.xls");
//		ServletOutputStream stream = response.getOutputStream();
//
//		JRXlsExporter exporter = new JRXlsExporter();
//		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
//		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, stream);
//		exporter.exportReport();
//
//		stream.flush();
//		stream.close();
//		FacesContext.getCurrentInstance().responseComplete();
//	}
//
//	public void exportarPPT(ActionEvent actionEvent) throws JRException, IOException {
//		Map<String, Object> parametros = new HashMap<String, Object>();
//		parametros.put("txtUsuario", "MitoCode");
//
//		File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/rptJSF.jasper"));
//		JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros,
//				new JRBeanCollectionDataSource(this.getPersonas()));
//
//		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext()
//				.getResponse();
//		response.addHeader("Content-disposition", "attachment; filename=jsfReporte.ppt");
//		ServletOutputStream stream = response.getOutputStream();
//
//		JRPptxExporter exporter = new JRPptxExporter();
//		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
//		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, stream);
//		exporter.exportReport();
//
//		stream.flush();
//		stream.close();
//		FacesContext.getCurrentInstance().responseComplete();
//	}
//
//	public void exportarDOC(ActionEvent actionEvent) throws JRException, IOException {
//		Map<String, Object> parametros = new HashMap<String, Object>();
//		parametros.put("txtUsuario", "MitoCode");
//
//		File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/rptJSF.jasper"));
//		JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros,
//				new JRBeanCollectionDataSource(this.getPersonas()));
//
//		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext()
//				.getResponse();
//		response.addHeader("Content-disposition", "attachment; filename=jsfReporte.doc");
//		ServletOutputStream stream = response.getOutputStream();
//
//		JRDocxExporter exporter = new JRDocxExporter();
//		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
//		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, stream);
//		exporter.exportReport();
//
//		stream.flush();
//		stream.close();
//		FacesContext.getCurrentInstance().responseComplete();
//	}
//
//	public void verPDF(ActionEvent actionEvent) throws Exception {
//		File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/rpJSF.jasper"));
//
//		byte[] bytes = JasperRunManager.runReportToPdf(jasper.getPath(), null,
//				new JRBeanCollectionDataSource(this.getPersonas()));
//		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext()
//				.getResponse();
//		response.setContentType("application/pdf");
//		response.setContentLength(bytes.length);
//		ServletOutputStream outStream = response.getOutputStream();
//		outStream.write(bytes, 0, bytes.length);
//		outStream.flush();
//		outStream.close();
//
//		FacesContext.getCurrentInstance().responseComplete();
//	}
//
//}
