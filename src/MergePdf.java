/**
 * Created by Rajat on 04-Jul-17.
 */
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;
public class MergePdf
{
    static void mergePdfFiles() throws Exception
    {
        //Create document and pdfReader objects.
        OutputStream outputStream = new FileOutputStream("MergedOutput.pdf");
        Document document = new Document();
        List<PdfReader> readers = new ArrayList<PdfReader>();
        int totalPages = 0;
        //Create pdf Iterator object using inputPdfList.
        Iterator<String> pdfIterator = FileChooser.inputPdfList.iterator();
        // Create reader list for the input pdf files.
        while (pdfIterator.hasNext())
        {
            String pdf = pdfIterator.next();
            System.out.println(pdf);
            PdfReader pdfReader = new PdfReader(pdf);
            readers.add(pdfReader);
            totalPages = totalPages + pdfReader.getNumberOfPages();
        }
        // Create writer for the outputStream
        PdfWriter writer = PdfWriter.getInstance(document, outputStream);
        //Open document.
        document.open();
        //Contain the pdf data.
        PdfContentByte pageContentByte = writer.getDirectContent();
        PdfImportedPage pdfImportedPage;
        int currentPdfReaderPage = 1;
        Iterator<PdfReader> iteratorPDFReader = readers.iterator();
        // Iterate and process the reader list.
        while (iteratorPDFReader.hasNext())
        {
            PdfReader pdfReader = iteratorPDFReader.next();
            //Create page and add content.
            while (currentPdfReaderPage <= pdfReader.getNumberOfPages())
            {
                document.newPage(); pdfImportedPage = writer.getImportedPage( pdfReader,currentPdfReaderPage);
                pageContentByte.addTemplate(pdfImportedPage, 0, 0); currentPdfReaderPage++;
            } currentPdfReaderPage = 1; }
        //Close document and outputStream.
        outputStream.flush();
        document.close();
        outputStream.close();
        FileChooser.inputPdfList.clear();
        System.out.println("Pdf files merged successfully.");
    }
}
