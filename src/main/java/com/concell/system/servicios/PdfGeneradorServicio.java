package com.concell.system.servicios;

import com.concell.system.modelos.Cliente;
import com.concell.system.modelos.ProductoVendido;
import com.concell.system.modelos.Venta;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class PdfGeneradorServicio {
  private static final Font TITLE_FONT = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
  private static final Font SUBTITLE_FONT = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD);
  private static final Font NORMAL_FONT = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL);
  private static final Font BOLD_FONT = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);

  public byte[] generateInvoice(Venta venta) throws DocumentException, IOException {
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    Document document = new Document(PageSize.A4);
    PdfWriter.getInstance(document, outputStream);

    document.open();

    addHeader(document, venta);
    addCustomerInfo(document, venta.getCliente());
    addProductsTable(document, venta.getProductosVendidos());
    addTotals(document, venta);
    addFooter(document, venta);

    document.close();
    return outputStream.toByteArray();
  }

  private void addHeader(Document document, Venta venta) throws DocumentException {
    Paragraph title = new Paragraph("FACTURA", TITLE_FONT);
    title.setAlignment(Element.ALIGN_CENTER);
    document.add(title);

    Paragraph invoiceInfo = new Paragraph();
    invoiceInfo.add(new Chunk("Número: ", BOLD_FONT));
    invoiceInfo.add(new Chunk("FAC-" + venta.getIdVenta(), NORMAL_FONT));
    invoiceInfo.add(Chunk.NEWLINE);
    invoiceInfo.add(new Chunk("Fecha: ", BOLD_FONT));
    invoiceInfo.add(new Chunk(venta.getFecha().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), NORMAL_FONT));
    invoiceInfo.add(Chunk.NEWLINE);
    invoiceInfo.add(new Chunk("Vendedor: ", BOLD_FONT));
    invoiceInfo.add(new Chunk(venta.getUsuario().getEmail(), NORMAL_FONT));
    document.add(invoiceInfo);

    document.add(new Paragraph(" "));
  }

  private void addCustomerInfo(Document document, Cliente cliente) throws DocumentException {
    Paragraph customerTitle = new Paragraph("DATOS DEL CLIENTE", SUBTITLE_FONT);
    customerTitle.setSpacingBefore(10f);
    document.add(customerTitle);

    Paragraph customerInfo = new Paragraph();
    customerInfo.add(new Chunk("Nombre: ", BOLD_FONT));
    customerInfo.add(new Chunk(cliente.getNombre() + " " + cliente.getApellidoPaterno() + " " + cliente.getApellidoMaterno(), NORMAL_FONT));
    customerInfo.add(Chunk.NEWLINE);
    customerInfo.add(new Chunk("NIT: ", BOLD_FONT));
    customerInfo.add(new Chunk(cliente.getNit(), NORMAL_FONT));
    customerInfo.add(Chunk.NEWLINE);
    customerInfo.add(new Chunk("Dirección: ", BOLD_FONT));
    customerInfo.add(new Chunk(cliente.getDireccion(), NORMAL_FONT));
    customerInfo.add(Chunk.NEWLINE);
    customerInfo.add(new Chunk("Teléfono: ", BOLD_FONT));
    customerInfo.add(new Chunk(cliente.getTelefono(), NORMAL_FONT));
    document.add(customerInfo);

    document.add(new Paragraph(" "));
  }

  private void addProductsTable(Document document, List<ProductoVendido> productos) throws DocumentException {
    Paragraph productsTitle = new Paragraph("DETALLE DE PRODUCTOS", SUBTITLE_FONT);
    productsTitle.setSpacingBefore(10f);
    document.add(productsTitle);

    PdfPTable table = new PdfPTable(5);
    table.setWidthPercentage(100);

    table.addCell(createCell("Código", true));
    table.addCell(createCell("Producto", true));
    table.addCell(createCell("Cantidad", true));
    table.addCell(createCell("P. Unitario", true));
    table.addCell(createCell("Total", true));

    for (ProductoVendido pv : productos) {
      table.addCell(createCell(pv.getProducto().getIdProducto().toString(), false));
      table.addCell(createCell(pv.getProducto().getNombre(), false));
      table.addCell(createCell(pv.getCantidad().toString(), false));
      table.addCell(createCell("Q" + pv.getPrecioUnitario().toString(), false));
      table.addCell(createCell("Q" + pv.getPrecioUnitario().multiply(BigDecimal.valueOf(pv.getCantidad())).toString(), false));
    }

    document.add(table);
  }

  private PdfPCell createCell(String content, boolean isHeader) {
    PdfPCell cell = new PdfPCell(new Phrase(content, isHeader ? BOLD_FONT : NORMAL_FONT));
    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    cell.setPadding(5f);
    return cell;
  }

  private void addTotals(Document document, Venta venta) throws DocumentException {
    PdfPTable table = new PdfPTable(2);
    table.setWidthPercentage(50);
    table.setHorizontalAlignment(Element.ALIGN_RIGHT);

    table.addCell(createCell("Subtotal:", true));
    table.addCell(createCell("Q" + venta.getTotal().toString(), false));

    table.addCell(createCell("TOTAL:", true));
    table.addCell(createCell("Q" + venta.getTotal().toString(), true));

    document.add(table);
  }

  private void addFooter(Document document, Venta venta) throws DocumentException {
    Paragraph footer = new Paragraph();
    footer.setSpacingBefore(20f);

    if (venta.getDescripcionGarantia() != null && !venta.getDescripcionGarantia().isEmpty()) {
      footer.add(new Chunk("Garantía: ", BOLD_FONT));
      footer.add(new Chunk(venta.getDescripcionGarantia(), NORMAL_FONT));
      footer.add(Chunk.NEWLINE);
    }

    footer.add(new Chunk("Forma de pago: ", BOLD_FONT));
    footer.add(new Chunk(venta.getTipoPago().toString(), NORMAL_FONT));
    footer.add(Chunk.NEWLINE);
    footer.add(new Chunk("Gracias por su compra!", NORMAL_FONT));

    document.add(footer);
  }
}
