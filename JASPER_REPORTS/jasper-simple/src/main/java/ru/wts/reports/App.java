/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package ru.wts.reports;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRRtfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.oasis.JROdsExporter;
import net.sf.jasperreports.engine.export.oasis.JROdtExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRPptxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOdsReportConfiguration;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleWriterExporterOutput;
import net.sf.jasperreports.export.SimpleXlsReportConfiguration;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;

public class App {
    public String getGreeting() {
        return "Hello world.";
    }

    public static void main(String[] args) throws JRException, URISyntaxException{
        
        String reportSource = "/reports/BarbecueReport.jrxml";
        
//        JasperReport jasperReport = JasperCompileManager.compileReport(reportSource);
        
        File file = new File("/reports/123");
        
        System.out.println("file is exists: " + file.exists());
        
        URI uri = App.class.getResource("/reports/123").toURI();
        File file2 = new File(uri);
         System.out.println("file2 is exists: " + file2.exists());
        
        App app = new App();
        System.out.println(app.getGreeting());
        app.test();
    }
    
//    @Override
	public void test() throws JRException, URISyntaxException
	{
		fill();
		pdf();
//		xmlEmbed();
//		xml();
//		html();
//		rtf();
//		xls();
//		csv();
//		odt();
//		ods();
//		docx();
//		xlsx();
//		pptx();
	}


	/**
	 *
	 */
	public void fill() throws JRException, URISyntaxException
	{
               URI uri = App.class.getResource("/reports/BarbecueReport.jasper").toURI();
               File file = new File(uri);
		long start = System.currentTimeMillis();
		JasperFillManager.fillReportToFile(file.getAbsolutePath(), null, new JREmptyDataSource());
		System.err.println("Filling time : " + (System.currentTimeMillis() - start));
	}
	
	
	/**
	 *
	 */
//	public void print() throws JRException
//	{
//		long start = System.currentTimeMillis();
//		JasperPrintManager.printReport("/reports/BarbecueReport.jrprint", true);
//		System.err.println("Printing time : " + (System.currentTimeMillis() - start));
//	}
	
	
	/**
	 *
	 */
	public void pdf() throws JRException, URISyntaxException
	{
              URI uri = App.class.getResource("/reports/BarbecueReport.jrprint").toURI();
               File file = new File(uri);
		long start = System.currentTimeMillis();
		JasperExportManager.exportReportToPdfFile(file.getAbsolutePath());
		System.err.println("PDF creation time : " + (System.currentTimeMillis() - start));
	}
//	
	
//	/**
//	 *
//	 */
//	public void xml() throws JRException
//	{
//		long start = System.currentTimeMillis();
//		JasperExportManager.exportReportToXmlFile("/reports/BarbecueReport.jrprint", false);
//		System.err.println("XML creation time : " + (System.currentTimeMillis() - start));
//	}
	
	
	/**
	 *
	 */
//	public void xmlEmbed() throws JRException
//	{
//		long start = System.currentTimeMillis();
//		JasperExportManager.exportReportToXmlFile("/reports/BarbecueReport.jrprint", true);
//		System.err.println("XML creation time : " + (System.currentTimeMillis() - start));
//	}
	
	
	/**
	 *
	 */
//	public void html() throws JRException
//	{
//		long start = System.currentTimeMillis();
//		JasperExportManager.exportReportToHtmlFile("/reports/BarbecueReport.jrprint");
//		System.err.println("HTML creation time : " + (System.currentTimeMillis() - start));
//	}
	
	
	/**
	 *
	 */
//	public void rtf() throws JRException
//	{
//		long start = System.currentTimeMillis();
//		File sourceFile = new File("/reports/BarbecueReport.jrprint");
//
//		JasperPrint jasperPrint = (JasperPrint)JRLoader.loadObject(sourceFile);
//
//		File destFile = new File(sourceFile.getParent(), jasperPrint.getName() + ".rtf");
//		
//		JRRtfExporter exporter = new JRRtfExporter();
//		
//		exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
//		exporter.setExporterOutput(new SimpleWriterExporterOutput(destFile));
//		
//		exporter.exportReport();
//
//		System.err.println("RTF creation time : " + (System.currentTimeMillis() - start));
//	}
	
	
	/**
	 *
	 */
//	public void xls() throws JRException
//	{
//		long start = System.currentTimeMillis();
//		File sourceFile = new File("/reports/BarbecueReport.jrprint");
//
//		JasperPrint jasperPrint = (JasperPrint)JRLoader.loadObject(sourceFile);
//
//		File destFile = new File(sourceFile.getParent(), jasperPrint.getName() + ".xls");
//		
//		JRXlsExporter exporter = new JRXlsExporter();
//		
//		exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
//		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(destFile));
//		SimpleXlsReportConfiguration configuration = new SimpleXlsReportConfiguration();
//		configuration.setOnePagePerSheet(true);
//		exporter.setConfiguration(configuration);
//		
//		exporter.exportReport();
//
//		System.err.println("XLS creation time : " + (System.currentTimeMillis() - start));
//	}
	
	
	/**
	 *
	 */
//	public void csv() throws JRException
//	{
//		long start = System.currentTimeMillis();
//		File sourceFile = new File("/reports/BarbecueReport.jrprint");
//
//		JasperPrint jasperPrint = (JasperPrint)JRLoader.loadObject(sourceFile);
//
//		File destFile = new File(sourceFile.getParent(), jasperPrint.getName() + ".csv");
//		
//		JRCsvExporter exporter = new JRCsvExporter();
//		
//		exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
//		exporter.setExporterOutput(new SimpleWriterExporterOutput(destFile));
//		
//		exporter.exportReport();
//
//		System.err.println("CSV creation time : " + (System.currentTimeMillis() - start));
//	}
	
	
	/**
	 *
	 */
//	public void odt() throws JRException
//	{
//		long start = System.currentTimeMillis();
//		File sourceFile = new File("/reports/BarbecueReport.jrprint");
//
//		JasperPrint jasperPrint = (JasperPrint)JRLoader.loadObject(sourceFile);
//
//		File destFile = new File(sourceFile.getParent(), jasperPrint.getName() + ".odt");
//		
//		JROdtExporter exporter = new JROdtExporter();
//		
//		exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
//		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(destFile));
//		
//		exporter.exportReport();
//
//		System.err.println("ODT creation time : " + (System.currentTimeMillis() - start));
//	}
	
	
	/**
	 *
	 */
//	public void ods() throws JRException
//	{
//		long start = System.currentTimeMillis();
//		File sourceFile = new File("/reports/BarbecueReport.jrprint");
//
//		JasperPrint jasperPrint = (JasperPrint)JRLoader.loadObject(sourceFile);
//
//		File destFile = new File(sourceFile.getParent(), jasperPrint.getName() + ".ods");
//		
//		JROdsExporter exporter = new JROdsExporter();
//		
//		exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
//		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(destFile));
//		SimpleOdsReportConfiguration configuration = new SimpleOdsReportConfiguration();
//		configuration.setOnePagePerSheet(true);
//		exporter.setConfiguration(configuration);
//		
//		exporter.exportReport();
//
//		System.err.println("ODS creation time : " + (System.currentTimeMillis() - start));
//	}
//	
	
	/**
	 *
	 */
//	public void docx() throws JRException
//	{
//		long start = System.currentTimeMillis();
//		File sourceFile = new File("/reports/BarbecueReport.jrprint");
//
//		JasperPrint jasperPrint = (JasperPrint)JRLoader.loadObject(sourceFile);
//
//		File destFile = new File(sourceFile.getParent(), jasperPrint.getName() + ".docx");
//		
//		JRDocxExporter exporter = new JRDocxExporter();
//		
//		exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
//		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(destFile));
//		
//		exporter.exportReport();
//
//		System.err.println("DOCX creation time : " + (System.currentTimeMillis() - start));
//	}
	
	
	/**
	 *
	 */
//	public void xlsx() throws JRException
//	{
//		long start = System.currentTimeMillis();
//		File sourceFile = new File("/reports/BarbecueReport.jrprint");
//
//		JasperPrint jasperPrint = (JasperPrint)JRLoader.loadObject(sourceFile);
//
//		File destFile = new File(sourceFile.getParent(), jasperPrint.getName() + ".xlsx");
//		
//		JRXlsxExporter exporter = new JRXlsxExporter();
//		
//		exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
//		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(destFile));
//		SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();
//		configuration.setOnePagePerSheet(true);
//		exporter.setConfiguration(configuration);
//		
//		exporter.exportReport();
//
//		System.err.println("XLSX creation time : " + (System.currentTimeMillis() - start));
//	}
	
	
	/**
	 *
	 */
//	public void pptx() throws JRException
//	{
//		long start = System.currentTimeMillis();
//		File sourceFile = new File("/reports/BarbecueReport.jrprint");
//
//		JasperPrint jasperPrint = (JasperPrint)JRLoader.loadObject(sourceFile);
//
//		File destFile = new File(sourceFile.getParent(), jasperPrint.getName() + ".pptx");
//		
//		JRPptxExporter exporter = new JRPptxExporter();
//		
//		exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
//		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(destFile));
//
//		exporter.exportReport();
//
//		System.err.println("PPTX creation time : " + (System.currentTimeMillis() - start));
//	}

}
