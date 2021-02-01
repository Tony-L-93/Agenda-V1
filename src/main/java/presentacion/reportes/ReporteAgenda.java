package presentacion.reportes;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import org.apache.log4j.Logger;

import dto.PersonaDTO;
import dto.PersonaReport;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public class ReporteAgenda {
	private JasperReport reporte;
	private JasperViewer reporteViewer;
	private JasperPrint	reporteLleno;
	private Logger log = Logger.getLogger(ReporteAgenda.class);

	//Recibe la lista de personas para armar el reporte
    public ReporteAgenda(List<PersonaDTO> personas)
    {
    	List<PersonaReport> persona = agruparPersonasPorDominio(personas);

    	try		{
			this.reporte = (JasperReport) JRLoader.loadObjectFromFile( "reportes" + File.separator + "ReporteAgenda.jasper" );
			this.reporteLleno = JasperFillManager.fillReport(this.reporte, null, new JRBeanCollectionDataSource(persona));
    		log.info("Se cargó correctamente el reporte");
		}
		catch( JRException ex ) 
		{
			log.error("Ocurrió un error mientras se cargaba el archivo ReporteAgenda.Jasper", ex);
		}
    } 
    
    private List<PersonaReport> agruparPersonasPorDominio(List<PersonaDTO> personas){
    	List<PersonaReport> personasReport = transformarAPersonaReport(personas);
    	
    	for(PersonaReport per : personasReport)
			System.out.println(per.getEmail());
    		personasReport.sort(new Comparator<PersonaReport>() {

			@Override
			public int compare(PersonaReport p1, PersonaReport p2) {
				return p1.getDominio().compareTo(p2.getDominio());
			}
			
		});
		System.out.println("*****************************");
		for(PersonaReport per : personasReport)
			System.out.println(per.getEmail());
		return personasReport;
    }
    
    private List<PersonaReport> transformarAPersonaReport(List<PersonaDTO> personas){
    	List<PersonaReport> personasReport = new ArrayList<PersonaReport>();
    	String dominio="";
    	for(PersonaDTO persona: personas) {
			if(persona.getEmail().equals("")){
				 dominio="S/Email";
			}
			else {
				 dominio = persona.getEmail().substring(persona.getEmail().indexOf("@")+1, persona.getEmail().indexOf("."));
			}
    		PersonaReport personaReport = new PersonaReport(persona,dominio);
    		personasReport.add(personaReport);
    	}
    	
    	return personasReport;
    }
    

    public void mostrar()
	{
		this.reporteViewer = new JasperViewer(this.reporteLleno,false);
		this.reporteViewer.setVisible(true);
	}
}
