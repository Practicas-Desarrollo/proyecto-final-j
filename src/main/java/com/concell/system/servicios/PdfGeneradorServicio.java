package com.concell.system.servicios;

import com.concell.system.modelos.Cliente;
import com.concell.system.modelos.DetalleVenta;
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
  private static final Font TITULO = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
  private static final Font SUBTITULO = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD);
  private static final Font NORMAL = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL);
  private static final Font NEGRILLA = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);

  public byte[] generarFactura(Venta venta) throws DocumentException, IOException {
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    Document document = new Document(PageSize.A4);
    PdfWriter.getInstance(document, outputStream);

    document.open();

    agregarTitulo(document, venta);
    agregarInformacionCliente(document, venta.getCliente());
    agregarTablaProductos(document, venta.getProductosVendidos());
    agregarTotales(document, venta);
    agregarInformacionExtra(document, venta);

    document.close();
    return outputStream.toByteArray();
  }

  private void agregarTitulo(Document document, Venta venta) throws DocumentException {
    Paragraph title = new Paragraph("FACTURA", TITULO);
    title.setAlignment(Element.ALIGN_CENTER);
    document.add(title);

    Paragraph informacionFactura = new Paragraph();
    informacionFactura.add(new Chunk("Número: ", NEGRILLA));
    informacionFactura.add(new Chunk("FAC-" + venta.getIdVenta(), NORMAL));
    informacionFactura.add(Chunk.NEWLINE);
    informacionFactura.add(new Chunk("Fecha: ", NEGRILLA));
    informacionFactura.add(new Chunk(venta.getFecha().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), NORMAL));
    informacionFactura.add(Chunk.NEWLINE);
    informacionFactura.add(new Chunk("Vendedor: ", NEGRILLA));
    informacionFactura.add(new Chunk(venta.getUsuario().getEmail(), NORMAL));
    document.add(informacionFactura);

    document.add(new Paragraph(" "));
  }

  private void agregarInformacionCliente(Document document, Cliente cliente) throws DocumentException {
    Paragraph customerTitle = new Paragraph("DATOS DEL CLIENTE", SUBTITULO);
    customerTitle.setSpacingBefore(10f);
    document.add(customerTitle);

    Paragraph customerInfo = new Paragraph();
    customerInfo.add(new Chunk("Nombre: ", NEGRILLA));
    customerInfo.add(new Chunk(cliente.getNombre() + " " + cliente.getApellidoPaterno() + " " + cliente.getApellidoMaterno(), NORMAL));
    customerInfo.add(Chunk.NEWLINE);
    customerInfo.add(new Chunk("NIT: ", NEGRILLA));
    customerInfo.add(new Chunk(cliente.getNit(), NORMAL));
    customerInfo.add(Chunk.NEWLINE);
    customerInfo.add(new Chunk("Dirección: ", NEGRILLA));
    customerInfo.add(new Chunk(cliente.getDireccion(), NORMAL));
    customerInfo.add(Chunk.NEWLINE);
    customerInfo.add(new Chunk("Teléfono: ", NEGRILLA));
    customerInfo.add(new Chunk(cliente.getTelefono(), NORMAL));
    document.add(customerInfo);

    document.add(new Paragraph(" "));
  }

  private void agregarTablaProductos(Document document, List<DetalleVenta> productos) throws DocumentException {
    Paragraph productsTitle = new Paragraph("DETALLE DE PRODUCTOS", SUBTITULO);
    productsTitle.setSpacingBefore(10f);
    document.add(productsTitle);

    document.add(new Paragraph(" "));

    PdfPTable table = new PdfPTable(5);
    table.setWidthPercentage(100);

    table.addCell(crearCelda("Código", true));
    table.addCell(crearCelda("Producto", true));
    table.addCell(crearCelda("Cantidad", true));
    table.addCell(crearCelda("P. Unitario", true));
    table.addCell(crearCelda("Total", true));

    for (DetalleVenta pv : productos) {
      table.addCell(crearCelda(pv.getProducto().getIdProducto().toString(), false));
      table.addCell(crearCelda(pv.getProducto().getNombre(), false));
      table.addCell(crearCelda(pv.getCantidad().toString(), false));
      table.addCell(crearCelda(pv.getPrecioUnitario().toString(), false));
      table.addCell(crearCelda(pv.getPrecioUnitario().multiply(BigDecimal.valueOf(pv.getCantidad())).toString(), false));
    }

    document.add(table);
  }

  private PdfPCell crearCelda(String content, boolean isHeader) {
    PdfPCell cell = new PdfPCell(new Phrase(content, isHeader ? NEGRILLA : NORMAL));
    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    cell.setPadding(5f);
    return cell;
  }

  private void agregarTotales(Document document, Venta venta) throws DocumentException {
    PdfPTable table = new PdfPTable(2);
    table.setWidthPercentage(50);
    table.setHorizontalAlignment(Element.ALIGN_RIGHT);

    table.addCell(crearCelda("Subtotal:", true));
    table.addCell(crearCelda(venta.getTotal().toString() + " Bs.", false));

    table.addCell(crearCelda("TOTAL:", true));
    table.addCell(crearCelda(venta.getTotal().toString() + " Bs.", true));

    document.add(table);
  }

  private void agregarInformacionExtra(Document document, Venta venta) throws DocumentException {
    Paragraph footer = new Paragraph();
    footer.setSpacingBefore(20f);

    if (venta.getDescripcionGarantia() != null && !venta.getDescripcionGarantia().isEmpty()) {
      footer.add(new Chunk("Garantía: ", NEGRILLA));
      footer.add(new Chunk(venta.getDescripcionGarantia(), NORMAL));
      footer.add(Chunk.NEWLINE);
    }

    footer.add(new Chunk("Forma de pago: ", NEGRILLA));
    footer.add(new Chunk(venta.getTipoPago().toString(), NORMAL));
    footer.add(Chunk.NEWLINE);
    footer.add(new Chunk("Gracias por su compra!", NORMAL));

    document.add(footer);
  }
}
