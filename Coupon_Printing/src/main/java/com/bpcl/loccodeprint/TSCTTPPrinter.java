package com.bpcl.loccodeprint;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.DocAttributeSet;
import javax.print.attribute.HashDocAttributeSet;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.MediaSizeName;
import javax.swing.JOptionPane;

public class TSCTTPPrinter {
public static void lotCodePrintTcp()
{

	 FileInputStream textStream = null;
	 String projectDir = System.getProperty("user.dir");
    try {
                         textStream = new FileInputStream(projectDir+"/lotcodeimage.png");
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    } 
    
    if (textStream != null) 
    {
                         
        DocFlavor flavor = DocFlavor.INPUT_STREAM.PDF;
                       
        PrintService printService = PrintServiceLookup.lookupDefaultPrintService();
                        
        DocPrintJob job = printService.createPrintJob();
                         // Set the print properties
        PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
                         // Set the paper size, you can also create a new MediaSize class to customize the size
        pras.add(MediaSizeName.ISO_A4);
        DocAttributeSet das = new HashDocAttributeSet();
                         // Specify print content
        Doc doc = new SimpleDoc(textStream, flavor, das);
                         // Do not display the print dialog, print directly
        try {
                                 job.print(doc, pras); // Make specific print operations for each page
        } catch (PrintException pe) {
            pe.printStackTrace();
        }
    } 
    else {
        
JOptionPane.showConfirmDialog(null,
"Sorry, Printer Job is Empty, Print Cancelled!",
"Empty", JOptionPane.DEFAULT_OPTION,
JOptionPane.WARNING_MESSAGE);
}
}
}
