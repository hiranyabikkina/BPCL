package com.bpcl.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import com.bpcl.loccodeprint.TSCTTPPrinter;
import com.bpcl.loccodeprint.ZXingHelper;

public class FileStoredPath {
	public static void filePath(String lotCode) throws IOException, InterruptedException
	{
		
		String projectDir = System.getProperty("user.dir");		
		File outputFile = new File(projectDir);
		if (!outputFile.isDirectory()) {
			System.out.println("if   ???");
			outputFile.mkdir();
		}		
		File outputFile1 = new File(projectDir+"/lotcodeimage.png");
		FileOutputStream out = new FileOutputStream(outputFile1);
		out.write(ZXingHelper.getBarCodeImage(lotCode, 200, 80));
		out.write(lotCode.getBytes());		
		 TSCTTPPrinter.lotCodePrintTcp();
		 
		
	}
	
}
