import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;

public class PdfSplitter
{
    public static void splitPdfFile() {
        // TODO Auto-generated method stub
        Document doc = new Document();
        try {
            int start = Integer.parseInt(FileChooser.startPage);
            OutputStream output = new FileOutputStream("Out.pdf");
            PdfReader reader = new PdfReader(FileChooser.path);
            int totalPages = reader.getNumberOfPages();
            if(Integer.parseInt(FileChooser.startPage) > Integer.parseInt(FileChooser.endPage) || Integer.parseInt(FileChooser.endPage) > totalPages)
            {
                System.out.println("Kindly pass the valid range of pages");
                JFrame frame = new JFrame("Error!!!");
                JOptionPane.showMessageDialog(frame, "Please Enter Valid Page Range");
            }
            else
            {
                try {
                    PdfWriter writer = PdfWriter.getInstance(doc, output);
                    doc.open();
                    PdfContentByte pdfContentByte = writer.getDirectContent();
                    PdfImportedPage pages;
                    while (start <= Integer.parseInt(FileChooser.endPage))
                    {
                        doc.newPage();
                        pages = writer.getImportedPage(reader, start);
                        pdfContentByte.addTemplate(pages,0,0);
                        start++;
                    }
                    output.flush();
                    doc.close();
                    output.close();
                } catch (DocumentException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}