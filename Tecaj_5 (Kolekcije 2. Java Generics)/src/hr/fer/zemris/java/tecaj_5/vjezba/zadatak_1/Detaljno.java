package hr.fer.zemris.java.tecaj_5.vjezba.zadatak_1;

import java.text.DecimalFormat;
import java.util.*;
import java.io.*;

import org.apache.poi.hssf.usermodel.*;

/**
 * Klasa Detaljno sluzi za zapisivanje detaljnih zapisa svih ispita
 * @author HrvojeTorbasinovic
 */
public class Detaljno implements Ispis {

	LinkedList<Obrazac> obrasci;

	/**
	 * Instancira klasu detaljno
	 * 
	 * @param obrasci the obrasci
	 */
	public Detaljno(LinkedList<Obrazac> obrasci) {
		super();
		this.obrasci = obrasci;
	}

	/* (non-Javadoc)
	 * @see hr.fer.zemris.java.tecaj_5.vjezba.zadatak_1.Ispis#zapisi(java.lang.String)
	 */
	@Override
	public void zapisi(String file) throws IOException {
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file),"UTF-8"));
		Obrazac obrazac;
		Iterator<Obrazac> it = obrasci.descendingIterator();
		DecimalFormat f = new DecimalFormat("0.00");
		while (it.hasNext())
		{
			obrazac = it.next();
			bw.write(obrazac.getStudent().getJmbag() + "\t" + obrazac.getGrupa().getOznakaGrupe() + "\t");
			for (int i = 0; i < obrazac.getOdgovori().length; i++)
			{
				bw.write(
						obrazac.getOdgovor(i) + "\t" +
						obrazac.getGrupa().getTocanOdgovor(i) + "\t" +
						obrazac.getStatus(i) + "\t" +
						f.format(obrazac.getBodovi(i)) + "\t" +
						f.format(obrazac.getGrupa().getMaxBodovi(i)) + "\t"
						);
			}
			bw.newLine();
		}
		bw.close();
	}
	
	/**
	 * Funkcija koja generira Microsoft Excel datoteku koja podatke sprema u detaljnom formatu
	 * 
	 * @param file the file
	 * 
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void zapisiXLS(String file) throws IOException
	{
		FileOutputStream out = new FileOutputStream(file);
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet();
		HSSFRow row = null;
		HSSFCell cell = null;
		
		sheet.setColumnWidth((short)0, (short)(256*12));
		sheet.setColumnWidth((short)1, (short)(256*18));
		HSSFCellStyle cellStyle = wb.createCellStyle();
	    cellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("0.00"));
		Obrazac obrazac;
		Iterator<Obrazac> it = obrasci.descendingIterator();
		int rownum = 0;
		while (it.hasNext())
		{
			obrazac = it.next();
			row = sheet.createRow(rownum);
			
			cell = row.createCell((short)0);
			cell.setCellValue(new HSSFRichTextString(obrazac.getStudent().getJmbag()));
			cell = row.createCell((short)1);
			cell.setCellValue(new HSSFRichTextString(obrazac.getGrupa().getOznakaGrupe()));
			
			for (int i = 0; i < obrazac.getOdgovori().length; i++)
			{
				cell = row.createCell((short)(row.getLastCellNum()+1));
				cell.setCellValue(new HSSFRichTextString(
						obrazac.getOdgovor(i))
				);
				
				cell = row.createCell((short)(row.getLastCellNum()+1));
				cell.setCellValue(new HSSFRichTextString(
						Character.toString(obrazac.getGrupa().getTocanOdgovor(i)))
				);
				
				cell = row.createCell((short)(row.getLastCellNum()+1));
				cell.setCellValue(new HSSFRichTextString(
						Character.toString(obrazac.getStatus(i)))
				);
				
				cell = row.createCell((short)(row.getLastCellNum()+1));
				cell.setCellValue(obrazac.getBodovi(i));
				cell.setCellStyle(cellStyle);
				
				cell = row.createCell((short)(row.getLastCellNum()+1));
				cell.setCellValue(obrazac.getGrupa().getMaxBodovi(i));
				cell.setCellStyle(cellStyle);
			}
			rownum++;
		}
		wb.write(out);
	}
		

}
