import jdk.nashorn.internal.scripts.JO;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

class MainFrame extends JFrame {
    JButton bTxtToPdf; // reference to the button object
    JButton bSplitPdf;
    JButton bMergePdf;
    int flag = 0;
    Insets margin = new Insets(20,150,20,150);

    // constructor for ButtonFrame
    MainFrame(String title) {
        super(title); // invoke the JFrame constructor
        setResizable(false);
        setLayout(new GridBagLayout());

        setLocationByPlatform(true);

        // ensures the frame is the minimum size it needs to be
        // in order display the components within it
        pack();
        setMinimumSize(getSize());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        bTxtToPdf = new JButton("Txt To PDF!"); // construct a JButton
        bTxtToPdf.setFont(new Font("Comic Sans ms", Font.BOLD, 23));
        bTxtToPdf.setMargin(margin);
        bSplitPdf = new JButton("Split PDF!");
        bSplitPdf.setFont(new Font("Comic Sans ms", Font.BOLD, 23));
        bSplitPdf.setMargin(margin);
        bMergePdf = new JButton("Merge PDF!");
        bMergePdf.setFont(new Font("Comic Sans ms", Font.BOLD, 23));
        bMergePdf.setMargin(margin);

        add(bTxtToPdf, gbc); // add the button to the JFrame
        add(bSplitPdf, gbc);
        add(bMergePdf, gbc);

        Color color = Color.decode("#79b5be");
        getContentPane().setBackground(color);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        bTxtToPdf.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                FileChooser.start(1);
            }
        });
        bSplitPdf.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                FileChooser.start(2);
            }
        });
        bMergePdf.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
                JFrame frame = new JFrame("Merge!!!");
                String totalPdf = JOptionPane.showInputDialog(frame, "How many Pdf You want to merge? ");
                if(totalPdf != null)
                {
                    while(totalPdf.isEmpty())
                    {
                        JOptionPane.showMessageDialog(frame, "Please Enter Number of Pdf to be Merged!!! ");
                        totalPdf = JOptionPane.showInputDialog(frame, "How many Pdf You want to merge? ");
                    }
                    int pdfNumber = Integer.parseInt(totalPdf);
                    while (pdfNumber > 0)
                    {
                        int reply = FileChooser.start(3);
                        if(reply == -1)
                        {
                            pdfNumber = 0;
                            flag = -1;
                        }
                        else
                        {
                            pdfNumber--;
                        }
                    }
                    try
                    {
                        if(flag == 0)
                        {
                            MergePdf.mergePdfFiles();
                        }
                        JOptionPane.showMessageDialog(null,"You Cancelled it!");
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                else if(totalPdf == null)
                {
                    JOptionPane.showMessageDialog(frame, "You Cancelled it! ");
                }
            }
        });
    }
}

public class StartScreen {
    public static void main(String[] args) {

        try {
            // Set cross-platform Java L&F (also called "Metal")
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (UnsupportedLookAndFeelException e) {
            // handle exception
        } catch (ClassNotFoundException e) {
            // handle exception
        } catch (InstantiationException e) {
            // handle exception
        } catch (IllegalAccessException e) {
            // handle exception
        }

        MainFrame frm = new MainFrame("Pdifyr");

        frm.setSize(600, 600);
        frm.setVisible(true);
    }
}