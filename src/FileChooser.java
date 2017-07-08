import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileSystemView;

public class FileChooser {

    static String path,path2;
    static String startPage = "0", endPage = "0";
    static List<String> inputPdfList = new ArrayList<String>();
    public static int start(int choice) {

        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

        int returnValue = jfc.showOpenDialog(null);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = jfc.getSelectedFile();
            path = selectedFile.getAbsolutePath();
            path2 = selectedFile.getName();
            if(choice == 1)
            {
                TxtToPdfConverter.createPdf();
            }
            else if(choice == 2)
            {
                if(!path2.contains(".pdf"))
                {
                    JFrame frame = new JFrame("Error!!!");
                    JOptionPane.showMessageDialog(frame, "Please Check your File Format");
                }
                else
                {
                    JFrame frame = new JFrame("Page Number");
                    startPage = JOptionPane.showInputDialog(frame, "Starting Page Number to Split");
                    System.out.println(startPage);
                    if(startPage != null)
                    {
                        while(startPage.isEmpty())
                        {
                            JOptionPane.showMessageDialog(frame, "Please Enter Valid Start Page");
                            startPage = JOptionPane.showInputDialog(frame, "Starting Page Number to Split");
                        }
                        endPage = JOptionPane.showInputDialog(frame, "Ending Page Number to Split");
                        if(endPage != null)
                        {
                            while(endPage.isEmpty())
                            {
                                JOptionPane.showMessageDialog(frame, "Please Enter Valid Ending Page");
                                endPage = JOptionPane.showInputDialog(frame, "Ending Page Number to Split");
                            }
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(frame, "Sorry you Cancelled it!");
                        }
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(frame, "Sorry you Cancelled it!");
                    }
                    PdfSplitter.splitPdfFile();
                }
            }
            else if(choice == 3)
            {
                if(path.contains(".pdf"))
                {
                    inputPdfList.add(path);
                }
                else
                {
                    JFrame frame = new JFrame("Error!!!");
                    JOptionPane.showMessageDialog(frame,"Please Check Your File Format");
                    FileChooser.start(3);
                }
            }
        }
        else
        {
           return -1;
        }
        return 0;
    }

}