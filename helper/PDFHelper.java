package helper;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import model.Order;
import model.OrderItem;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.UUID;

public class PDFHelper {


    public static void writeOrder(Order order,float total, float received, float payback){
        Document document = new Document();

        try{
            String bar = "-------------------------------------------------------------------------";
            PdfWriter.getInstance(document, new FileOutputStream("order"+ UUID.randomUUID().toString()+".pdf"));

            document.open();

            //Font font = new Font(Font.FontFamily.TIMES_ROMAN,11f);
            Paragraph paragraph = new Paragraph("Supermercado PGGQ - A casa do Javeiro");
            paragraph.setAlignment(Element.ALIGN_CENTER);
            document.add(paragraph);

            paragraph = new Paragraph(bar);
            paragraph.setAlignment(Element.ALIGN_CENTER);
            document.add(paragraph);

            paragraph = new Paragraph("Nota fiscal dia: " + order.getDate().get(Calendar.DAY_OF_MONTH) + "/" + order.getDate().get(Calendar.MONTH)+"/"+order.getDate().get(Calendar.YEAR));
            paragraph.setAlignment(Element.ALIGN_CENTER);
            document.add(paragraph);

            paragraph = new Paragraph(bar);
            paragraph.setAlignment(Element.ALIGN_CENTER);
            document.add(paragraph);

            paragraph = new Paragraph("Produto  \t \t \t  Valor  \t \t \t  Quantidade  \t \t \t  Total");
            paragraph.setAlignment(Element.ALIGN_CENTER);
            document.add(paragraph);

            paragraph = new Paragraph(bar);
            paragraph.setAlignment(Element.ALIGN_CENTER);
            document.add(paragraph);

            for(int i = 0; i < order.getItemList().size();i++){
                OrderItem oi = order.getItemList().get(i);
                String s = oi.getItemName();
                s += " \t \t \t  ";
                s += oi.getPrice() + " R$ ";
                s += "  \t \t \t  ";
                s += oi.getAmount();
                s += "  \t \t \t  ";
                s += oi.getTotal() + " R$ ";
                paragraph = new Paragraph(s);
                paragraph.setAlignment(Element.ALIGN_CENTER);
                document.add(paragraph);
                paragraph = new Paragraph("\n");
                paragraph.setAlignment(Element.ALIGN_CENTER);
                document.add(paragraph);
            }

            paragraph = new Paragraph(bar);
            paragraph.setAlignment(Element.ALIGN_CENTER);
            document.add(paragraph);

            paragraph = new Paragraph("Total: " + total + "R$ // Recebido: "+ received + "R$ // Troco: " + payback + "R$");
            paragraph.setAlignment(Element.ALIGN_CENTER);
            document.add(paragraph);

            paragraph = new Paragraph(bar);
            paragraph.setAlignment(Element.ALIGN_CENTER);
            document.add(paragraph);

            document.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        } finally {
            document.close();
        }
    }

}
