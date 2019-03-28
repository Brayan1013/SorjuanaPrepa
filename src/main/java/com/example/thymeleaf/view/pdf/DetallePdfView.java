package com.example.thymeleaf.view.pdf;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.example.thymeleaf.models.entity.Reporte;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;


@Component("reporte/formReporte")
public class DetallePdfView extends AbstractPdfView {

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		Reporte reporte = (Reporte) model.get("reporte");

		// variables del cuerpo
		String catedratico = reporte.getCatedratico();
		String space = "                                                                                           ";
		String materia = reporte.getMateria();
		Date fecha = reporte.getFecha();
		String hora = reporte.getHora();
		String alumno = reporte.getAlumno().getNombre() + " " + reporte.getAlumno().getApellido();
		String semestre = reporte.getSemestre();
		String grupo = reporte.getGrupo();
		String incidencia = reporte.getIncidencia();

		// encabezado:
		Font font1 = new Font(Font.ITALIC, 12);
		Font font2 = new Font(Font.ITALIC, 13);
		Font font3 = new Font(Font.ITALIC, 13, Font.BOLD);
		Paragraph encabezado = new Paragraph("ESCUELA PREPARATORIA INCORPORADA\n " + "\"SOR JUANA INÉS DE LA CRUZ\"\n"
				+ "Clave: EPIUAH-85-09\n" + "Ciclo: " + reporte.getCiclo(), font1);
		encabezado.setAlignment(Paragraph.ALIGN_CENTER);

		// cuerpo:
		Paragraph parrafo1 = new Paragraph("NOMBRE DE CATEDRÁTICO: " + catedratico, font2);
		parrafo1.setSpacingBefore(40f);
		parrafo1.setSpacingAfter(10f);

		PdfPTable table = new PdfPTable(3);
		table.setWidthPercentage(100);
		table.addCell(getCell("MATERIA: " + materia, PdfPCell.ALIGN_LEFT, font2));
		table.addCell(getCell("FECHA: " + fecha, PdfPCell.ALIGN_CENTER, font2));
		table.addCell(getCell("HORA: " + hora, PdfPCell.ALIGN_RIGHT, font2));
		Paragraph parrafo2 = new Paragraph("NOMBRE DEL ALUMNO: " + alumno, font2);
		parrafo2.setSpacingBefore(10f);
		parrafo2.setSpacingAfter(10f);

		PdfPTable table2 = new PdfPTable(2);
		table2.setWidthPercentage(100);
		table2.addCell(getCell("SEMESTRE: " + semestre, PdfPCell.ALIGN_LEFT, font2));
		table2.addCell(getCell("GRUPO: " + grupo, PdfPCell.ALIGN_RIGHT, font2));

		table2.setSpacingAfter(10f);

		Paragraph parrafo3 = new Paragraph("INCIDENCIA", font3);
		parrafo3.setAlignment(Paragraph.ALIGN_CENTER);

		Paragraph parrafo4 = new Paragraph(incidencia, font3);
		parrafo4.setAlignment(Paragraph.ALIGN_CENTER);

		// dibujar lineas para firmas
		PdfContentByte canvas = writer.getDirectContent();
		PdfContentByte canvas2 = writer.getDirectContent();
		// Initial point of the line
		canvas.moveTo(40, 450);
		canvas.lineTo(230, 450);
		canvas.closePathStroke();
		
		canvas2.moveTo(360, 450);
		canvas2.lineTo(550, 450);
		canvas2.closePathStroke();
		
		//Firmas 
		Paragraph firmaCatedratico = new Paragraph("      FIRMA DEL CATEDRÁTICO", font2);
		firmaCatedratico.setSpacingBefore(110f);
		Paragraph firmaAlumno = new Paragraph(space + "  FIRMA DEL ALUMNO", font2);
		firmaAlumno.setSpacingBefore(-20f);
		firmaAlumno.setAlignment(100);
		firmaAlumno.setAlignment(Paragraph.ALIGN_CENTER);
		
		
		//Imagenes
		
		Image imagen = Image.getInstance("src/main/resources/static/Imagenes/Imagen1.jpg");
		imagen.setAbsolutePosition(490, 700);
		
		Image imagen2 = Image.getInstance("src/main/resources/static/Imagenes/Imagen2.png");
		imagen2.setAbsolutePosition(30, 720);

		document.add(encabezado);
		document.add(parrafo1);
		document.add(table);
		document.add(parrafo2);
		document.add(table2);
		document.add(parrafo3);
		document.add(parrafo4);
		document.add(firmaCatedratico);
		document.add(firmaAlumno);
		document.add(imagen);
		document.add(imagen2);

	}

	public PdfPCell getCell(String text, int alignment, Font font) {
		PdfPCell cell = new PdfPCell(new Phrase(text, font));
		cell.setPadding(0);
		cell.setHorizontalAlignment(alignment);
		cell.setBorder(PdfPCell.NO_BORDER);
		return cell;
	}

}
