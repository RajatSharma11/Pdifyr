import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStream;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class TxtToPdfConverter
{
    static void createPdf()
    {
        try
        {
            FileReader fileReader = new FileReader(FileChooser.path);

            String fileContents = "";

            int i ;

            while((i =  fileReader.read())!=-1){
                char ch = (char)i;

                fileContents = fileContents + ch;
            }
            Document doc = new Document();
            OutputStream out = new FileOutputStream(new File(FileChooser.path2+".pdf"));

            PdfWriter.getInstance(doc, out);
            doc.open();
            doc.add(new Paragraph(fileContents) );

            doc.close();
            out.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}