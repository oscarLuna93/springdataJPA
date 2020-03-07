package com.oscar.springboot.app.view.xlsx;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import com.oscar.springboot.app.models.entity.Factura;
import com.oscar.springboot.app.models.entity.ItemFactura;

@Component("factura/ver.xlsx")
public class FacturaXlsxView extends AbstractXlsxView{

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		Factura factura = (Factura) model.get("factura");
		Sheet sheet = workbook.createSheet("Factura Spring");
		
		Row row = sheet.createRow(0);
		Cell cell = row.createCell(0);
		
		cell.setCellValue("Datos del cliente");
		row = sheet.createRow(1);
		cell = row.createCell(0);
		cell.setCellValue(factura.getCliente().getNombre().concat(" ").concat(factura.getCliente().getApellido()));
		
		row = sheet.createRow(2);
		cell = row.createCell(0);
		cell.setCellValue(factura.getCliente().getEmail());
		
		sheet.createRow(4).createCell(0).setCellValue("Datos de la factura");
		sheet.createRow(5).createCell(0).setCellValue("Folio: " + factura.getId());
		sheet.createRow(6).createCell(0).setCellValue("Descripcion: " + factura.getDescripcion());
		sheet.createRow(7).createCell(0).setCellValue("Fecha: " + factura.getCreateAt());
		
		Row header = sheet.createRow(9);
		header.createCell(0).setCellValue("Producto");
		header.createCell(0).setCellValue("Precio");
		header.createCell(0).setCellValue("Cantidad");
		header.createCell(0).setCellValue("Total");
		
		int rownum = 10;
		for(ItemFactura item: factura.getItems()) {
			Row fila = sheet.createRow(rownum++);
			
			fila.createCell(0).setCellValue(item.getProducto().getNombre());
			fila.createCell(1).setCellValue(item.getProducto().getPrecio());
			fila.createCell(2).setCellValue(item.getCantidad());
			fila.createCell(3).setCellValue(item.calcularImporte());
		}
		
		Row filaTotal = sheet.createRow(rownum);
		filaTotal.createCell(2).setCellValue("Gran Total: ");
		filaTotal.createCell(3).setCellValue(factura.getTotal());
	}

}
