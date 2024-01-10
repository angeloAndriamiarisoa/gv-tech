package com.techimage.projectjfx;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.techimage.projectjfx.util.ResourceUtil;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;

public class InvoicePdf {



    public void generate () throws FileNotFoundException, MalformedURLException {
        float fullWidth = 400f;

        String path = "invoice.pdf";
        PdfWriter pdfWriter = new PdfWriter(path);
        PdfDocument pdfDocument = new PdfDocument(pdfWriter);
        pdfDocument.setDefaultPageSize(PageSize.A5);
        Document document = new Document(pdfDocument);

        float col1 = 150f;
        float col2 = fullWidth - col1;

        document.setMargins(20f,9f,20f,9f);

        float twoColumnWidth[] = {col1, col2};

        Paragraph next = new Paragraph("\n");

        String thePath = ResourceUtil.getIconUrl("logo.png").getPath();
        ImageData imageData = ImageDataFactory.create(thePath);
        Image image = new Image(imageData);
        image.setHeight(30f);
        image.setWidth(100f);


        Table nestedTable = new Table(new float[]{col2-100f , 100f});
        nestedTable.addCell(boldText("Point de vente", false));
        nestedTable.addCell(boldText("TechImage", true));
        nestedTable.addCell(boldText("Contact", false));
        nestedTable.addCell(boldText("034 XX XXX XX", true));
        nestedTable.addCell(boldText("Adresse", false));
        nestedTable.addCell(boldText("Imerinafovoany", true));

        Border border = new SolidBorder(1f/2);
        Table full = new Table(new float[]{fullWidth});
        full.setBorder(border);
        Table table = new Table(twoColumnWidth);
        table.addCell(new Cell().add(image).setBorder(Border.NO_BORDER));
        table.addCell(new Cell().add(nestedTable).setBorder(Border.NO_BORDER));
        table.setMarginBottom(5f);

        float threeColumn = 133f;
        Table thead = new Table(new float[]{threeColumn,threeColumn,threeColumn});
        thead.addCell(new Cell().add(new Paragraph("PRODUIT"))
                .setBorderBottom(Border.NO_BORDER));


        document.add(table);
        document.add(full);
        document.add(next);

        document.close();
    }


    static Cell boldText(String text, Boolean isBold) {
        Cell cell = new Cell().add(new Paragraph(text)).setFontSize(9f).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT);

        return isBold ? cell.setBold() : cell;
    }
    static Cell boldTexts(String text, Boolean isBold) {
        Cell cell = new Cell().add(new Paragraph(text)).setFontSize(9f).setBorder(Border.NO_BORDER);

        return isBold ? cell.setBold() : cell;
    }
}
