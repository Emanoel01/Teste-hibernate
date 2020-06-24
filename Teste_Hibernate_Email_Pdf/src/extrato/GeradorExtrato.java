package extrato;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import javax.swing.table.DefaultTableModel;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class GeradorExtrato {
	
	
	public String generatePFDExtrato(DefaultTableModel model) {
		String nomeUsuario = System.getProperty("user.name");
		String caminhoPast = "C:\\Users\\"+nomeUsuario+"\\Desktop\\extratos";
		
		File diretorio = new File(caminhoPast);
		
		String caminhoFinal = diretorio+"\\extrato_" +model.getValueAt(0, 1) + ".pdf";
		
		if(!diretorio.exists()) {
			diretorio.mkdir();
		}
		
		
		
		Document extrato = new Document();
		
		try {
			
			PdfWriter.getInstance(extrato, new FileOutputStream(diretorio + "\\extrato_" +model.getValueAt(0, 1) + ".pdf"));
			
			extrato.open();
			
			Paragraph paragrafo1 = new Paragraph("Extrato");
			extrato.add(paragrafo1);
			
			PdfPTable tabela = new PdfPTable(4);
			
			PdfPCell c1 = new PdfPCell(new Phrase("Nome"));
			tabela.addCell(c1);
			
			c1 = new PdfPCell(new Phrase("Qntd Pacotes"));
			tabela.addCell(c1);
			
			c1 = new PdfPCell(new Phrase("Diaria"));
			tabela.addCell(c1);
			
			c1 = new PdfPCell(new Phrase("Data"));
			tabela.addCell(c1);
			
			tabela.setHeaderRows(1);
			
			for(int i=0;i<model.getRowCount();i++) {
				tabela.addCell(String.valueOf(model.getValueAt(i, 1)));
				tabela.addCell(String.valueOf(model.getValueAt(i, 3)));
				tabela.addCell(String.valueOf(model.getValueAt(i, 4)));
				tabela.addCell(String.valueOf(model.getValueAt(i, 5)));
			}
			
			extrato.add(tabela);
			
			
			extrato.close();
			
		} catch (FileNotFoundException | DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return caminhoFinal;
	}
	
	public void testar() {
		String nome = System.getProperty("user.name");
		
		if(new File("C:\\Users\\"+nome+"\\Desktop\\extratos").mkdir()) {
			System.out.println("criou");
		}else {
			System.out.println("nao criou");
		}
	}

}
