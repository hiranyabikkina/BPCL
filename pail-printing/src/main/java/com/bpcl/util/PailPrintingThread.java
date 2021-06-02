package com.bpcl.util;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import com.bpcl.dto.LineInformation;
import com.bpcl.dto.PrinterState;
import com.bpcl.dto.ProductionPlan;
import com.bpcl.service.BottleInspectionService;
import com.bpcl.service.PrinterStateService;
import com.bpcl.service.ProductionPlanService;

public class PailPrintingThread extends Thread {
private ProductionPlanService productionPlanService;
	private PrinterStateService printerStateService;
	private Integer quantity;	
	private LineInformation lineInformation;
	private char BRKST = (char) 123; // ASCII character for { symbol
	private char PIPE = (char) 124; // ASCII character for | symbol
	private char BRKEND = (char) 125; // ASCII character for } symbol
	private Socket socket = null;
	private OutputStream output = null;
	private String jobUpdateCMD = null;

	String convertCouponBarcodeString;

	public PailPrintingThread(Integer quantity, 		
			 LineInformation lineInformation, PrinterStateService printerStateService,ProductionPlanService productionPlanService ,String convertCouponBarcodeString) {
		super();
		System.out.println("-------------------0000000000000000000000");
		this.quantity = quantity;
		this.productionPlanService=productionPlanService;
		this.printerStateService = printerStateService;
				
		this.lineInformation = lineInformation;
		this.convertCouponBarcodeString=convertCouponBarcodeString;
	}


	@SuppressWarnings("deprecation")
	public void insertIntoDbtThread() throws IOException {
		// System.out.println(quantity);
System.out.println("-------------------0000000000000000000000");
		PrinterState printerState = printerStateService.getInfoByUuid("9000");
		printerState.setIssued(quantity);
		printerState.setSize("16x25");
		printerState.setPrint(0);
		printerState.setInspect(0);
		printerStateService.update(printerState);

		try {
			System.out.println("\nsocket connection: " + lineInformation.getIpAdd() + ", port "
					+ lineInformation.getPortNo() + "\n");

			printerState.setMessage(
					"socket connection: " + lineInformation.getIpAdd() + ", port " + lineInformation.getPortNo());
			printerStateService.update(printerState);

			socket = new Socket(lineInformation.getIpAdd(), lineInformation.getPortNo());
			output = socket.getOutputStream();

		} catch (UnknownHostException ex) {

			System.out.println("Server not found: " + ex.getMessage());
			printerState.setMessage("Server not found");
			printerStateService.update(printerState);

		} catch (IOException ex) {

			System.out.println("I/O error: " + ex.getMessage());
		}

		if (socket != null && output != null) {
			try {
				System.out.println("\nsocket connection: established\n");

				printerState.setMessage("socket connection: established");
				printerStateService.update(printerState);

				jobUpdateCMD = BRKST + "~ER" + PIPE + "QR" + PIPE + BRKEND;

				output.write(jobUpdateCMD.getBytes());
				output.flush();

				int readClean;
				String outResClean = null;
				System.out.println("\nRequest: buffer clear\n");

				printerState.setMessage("Request: buffer clear");
				printerStateService.update(printerState);

				TimeUnit.SECONDS.sleep(3);
				while ((readClean = socket.getInputStream().read()) > -1) {

					outResClean = outResClean + (char) readClean;
					System.out.println(outResClean + "-------er|external0");
					if (outResClean.contains("ER0")) {
						System.out.println("\nResponse: buffer cleared\n");

						printerState.setMessage("Response: buffer cleared");
						printerStateService.update(printerState);

						break;
					}
				}
				System.out.println("\nRequest: Device status\n");

				printerState.setMessage("Request: Device status");
				printerStateService.update(printerState);

				TimeUnit.SECONDS.sleep(20);
				jobUpdateCMD = BRKST + "~DR" + PIPE + BRKEND;

				output.write(jobUpdateCMD.getBytes());
				output.flush();

				int readDr;
				String outResDr = "";

				while ((readDr = socket.getInputStream().read()) > -1) {

					outResDr = outResDr + (char) readDr;
					if (outResDr.contains("02")) {
						System.out.println("\nResponse: Ready to start\n");

						printerState.setMessage("Response: Ready to start");
						printerStateService.update(printerState);

						break;
					}
				}
				System.out.println("\nRequest: Printer On\n");

				printerState.setMessage("Request: Printer On");
				printerStateService.update(printerState);

				TimeUnit.SECONDS.sleep(10);

				jobUpdateCMD = BRKST + "~ST" + PIPE + "04" + PIPE + BRKEND;

				output.write(jobUpdateCMD.getBytes());
				output.flush();

				int read;
				String outRes = "";
				while ((read = socket.getInputStream().read()) > -1) {

					outRes = outRes + (char) read;
					if (outRes.contains("ST0")) {
						System.out.println("\nResponse: Printer On\n");

						printerState.setMessage("Response: Printer On");
						printerStateService.update(printerState);

						break;
					}
				}
				System.out.println("\nRequest: Device state\n");

				printerState.setMessage("Request: Device state");
				printerStateService.update(printerState);

				TimeUnit.SECONDS.sleep(20);
				jobUpdateCMD = BRKST + "~DR" + PIPE + BRKEND;

				output.write(jobUpdateCMD.getBytes());
				output.flush();

				int readDr6;
				String outResDr6 = "";
				while ((readDr6 = socket.getInputStream().read()) > -1) {

					outResDr6 = outResDr6 + (char) readDr6;
					if (outResDr6.contains("04")) {
						System.out.println("\nResponse: Printer On\n");

						printerState.setMessage("Response: Printer On");
						printerStateService.update(printerState);

						break;
					}
				}
				System.out.println("\nRequest: Ready to print\n");

				printerState.setMessage("Request: print ready");
				printerStateService.update(printerState);

				TimeUnit.SECONDS.sleep(20);
				jobUpdateCMD = BRKST + "~ST" + PIPE + "06" + PIPE + BRKEND;

				output.write(jobUpdateCMD.getBytes());
				output.flush();

				int read6;
				String outRes6 = "";
				while ((read6 = socket.getInputStream().read()) > -1) {

					outRes6 = outRes6 + (char) read6;
					if (outRes6.contains("ST0")) {
						System.out.println("\nResponse: Ready to print\n");

						printerState.setMessage("Response: Ready to print");
						printerStateService.update(printerState);

						break;
					}
				}

				TimeUnit.SECONDS.sleep(10);

				
				StringBuilder sb = new StringBuilder("{~EA|QR");
				sb.append(convertCouponBarcodeString);
				sb.append("}");
				System.out.println(sb);
				jobUpdateCMD = sb.toString();
				System.out.println("\nRequest: Print command\n");

				printerState.setMessage("Request: Send Print command");
				printerStateService.update(printerState);

				output.write(jobUpdateCMD.getBytes());
				output.flush();
				
				int readPrint;
				String outResPrint = "";
				while ((readPrint = socket.getInputStream().read()) > -1) {

					outResPrint = outResPrint + (char) readPrint;
					if (outResPrint.contains("EA0")) {
						System.out.println("\nResponse: Received print command with queue\n");

						printerState.setMessage("Response: Received print command with queue");
						printerStateService.update(printerState);

						break;
					}
				}
				TimeUnit.SECONDS.sleep(10);

				while (true) {
					jobUpdateCMD = BRKST + "~EU" + PIPE + "QR" + PIPE + BRKEND;
					System.out.println("\nRequest: UnPrint count command\n");

					printerState.setMessage("Request: start print command");
					printerStateService.update(printerState);

					output.write(jobUpdateCMD.getBytes());
					output.flush();

					int readExPendingCount;
					String outResExPendingCount = "";
					TimeUnit.SECONDS.sleep(3);

					while ((readExPendingCount = socket.getInputStream().read()) > -1) {
						outResExPendingCount = outResExPendingCount + (char) readExPendingCount;

						if (outResExPendingCount.length() > 3) {
							if (outResExPendingCount.substring(2).contains("}")) {
								System.out.println("\nResponse: Received Unprint queue\n");

								printerState.setMessage("Response: Printer Continues print mode!!!");
								printerStateService.update(printerState);

								break;
							}
						}

					}

					String strNew = outResExPendingCount.replace("|}{~EV0|QR", "{~EV0|QR");
					strNew = strNew.replace("|}", "|");
					strNew = strNew.replace("{~EV0|QR|", "");
					String[] arrOfCpn = strNew.split("\\|");
				

					System.out.println(arrOfCpn[0]);
					printerState.setPrint(quantity - Integer.parseInt(arrOfCpn[0]));
					printerStateService.update(printerState);
					if (arrOfCpn[0].equals("0")) {
						System.out.println("\nPrinting over\n");

						printerState.setMessage("Response: Printing over!!!");
						printerStateService.update(printerState);

						TimeUnit.MILLISECONDS.sleep(5);
						break;
					}

				}

				System.out.println("\nState changing: Idle\n");

				printerState.setMessage("Request: State change to Idle!!!");
				printerStateService.update(printerState);

				TimeUnit.MILLISECONDS.sleep(10);
				jobUpdateCMD = BRKST + "~ST" + PIPE + "11" + PIPE + BRKEND;

				output.write(jobUpdateCMD.getBytes());
				output.flush();

				int readST11;
				String outResST11 = "";
				while ((readST11 = socket.getInputStream().read()) > -1) {

					outResST11 = outResST11 + (char) readST11;
					if (outResST11.contains("ST0")) {
						System.out.println("\nState changed: Idle\n");

						printerState.setMessage("Response: State changed to Idle!!!");
						printerStateService.update(printerState);

						break;
					}
				}
				System.out.println("\nState changing: Stop\n");

				printerState.setMessage("Request: State change to Stop!!!");
				printerStateService.update(printerState);

				TimeUnit.MILLISECONDS.sleep(20);

				jobUpdateCMD = BRKST + "~ST" + PIPE + "02" + PIPE + BRKEND;

				output.write(jobUpdateCMD.getBytes());
				output.flush();

				int readST02;
				String outResST02 = "";
				while ((readST02 = socket.getInputStream().read()) > -1) {

					outResST02 = outResST02 + (char) readST02;
					if (outResST02.contains("ST0")) {
						System.out.println("\nState changed: Stop\n");

						printerState.setMessage("Response: State changed to Stop!!!");
						printerStateService.update(printerState);

//						if (couponProduction != null) {
//							couponProduction.setStatus(2);
//							couponProductionService.update(couponProduction);
//							couponInspectionsService.deleteAll();
//						}
						TimeUnit.MILLISECONDS.sleep(10);
						System.out.println("\nSocket Closed: Stop\n");

						printerState.setMessage("Response: Socket Closed!!!");
						printerStateService.update(printerState);

						socket.close();
						TimeUnit.MILLISECONDS.sleep(5);
						break;
					}
				}
			ProductionPlan productionPlan=	productionPlanService.getByStatus(25);
			productionPlan.setLabelPrint("No");
			productionPlanService.update(productionPlan);
				System.out.println("\nPrinter stopped\n");

				printerState.setMessage("Response: Printer stopped!!!");
				printerStateService.update(printerState);

			} catch (Exception e) {
				System.out.println(e.toString());

			}
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			insertIntoDbtThread();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		super.run();
	}

}
