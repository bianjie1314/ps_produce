
package com.ps.produce.order.controller;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.font.TextAttribute;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.net.URLDecoder;
import java.text.AttributedCharacterIterator;
import java.text.AttributedString;

import javax.imageio.ImageIO;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.Barcode128;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;


@Controller
public class BarCodeController {

	@RequestMapping("/barcode/{code:.+}")
	public ResponseEntity<byte[]> barcode(@PathVariable("code") String code)
			throws Exception {
		code = URLDecoder.decode(code);
		BufferedImage bufferedImage =this.createBarcodeImage(200, 80, code);
		final HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.IMAGE_PNG);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(bufferedImage, "PNG", baos);
		byte[] bytes = baos.toByteArray();
		return new ResponseEntity<byte[]>(bytes, headers, HttpStatus.CREATED);
	}
	
	
	@RequestMapping("/printPdf/{code:.+}")
	public ResponseEntity<byte[]> prindPdf(@PathVariable("code") String code)
			throws Exception {
			ByteArrayOutputStream baos = new ByteArrayOutputStream(); 
	          //  Rectangle pageSize = new Rectangle(1000, 3000);
			      Document document = new Document();
		            document.setPageSize(PageSize.A4);

	            PdfWriter writer = PdfWriter.getInstance(document, baos);
	            document.open();
	            document.setMargins(0, 0, 0, 0);
	            // 添加 中文信息
	            BaseFont bfCN = BaseFont.createFont("STSongStd-Light", "UniGB-UCS2-H", false);
	            // 设置字体大小
	            Font fontCN40 = new Font(bfCN, 15, Font.BOLD);
	            Font fontCN32 = new Font(bfCN, 14, Font.NORMAL);
	            Font fontCN30 = new Font(bfCN, 14, Font.NORMAL);

	            // 打开文档
	            document.open();

	            PdfPCell cell = null;
	            // 创建需要填入文档的元素
	            PdfPTable table = new PdfPTable(4);
	            table.setHorizontalAlignment(Element.ALIGN_CENTER);
	            table.setWidthPercentage(98);
	            table.setWidths(new int[] { 25, 25, 25, 25 });

	            // cell = new PdfPCell(new Paragraph(new Paragraph("21312312312321312", new
	            // Font(fontCN40))));
	            // cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	            // cell.setColspan(2);
	            // cell.setPadding(5);
	            // table.addCell(cell);

	            PdfContentByte cd = writer.getDirectContent();
	            Barcode128 code128 = new Barcode128();
	            code128.setCode("12316235855790");

	            code128.setBarHeight(90f); // great! but what about width???
	            code128.setX(3f);
	            code128.setBaseline(20f);
	            code128.setSize(15);
	            Image image128 = code128.createImageWithBarcode(cd, null, null);
	            image128.scaleToFit(240, 200);
	            PdfPCell barcodeCell = new PdfPCell(image128);
	            barcodeCell.setColspan(2);
	            barcodeCell.setHorizontalAlignment(Element.ALIGN_LEFT);
	            barcodeCell.setPadding(5);
	            barcodeCell.setBorderWidth(0);
	            table.addCell(barcodeCell);

	         
	            cell = new PdfPCell(new Paragraph(new Paragraph("", new Font(fontCN40))));
	            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	            cell.setColspan(2);
	            cell.setPadding(5);
	            cell.setBorderWidth(0);

	            table.addCell(cell);
	            //横线
	            cell = new PdfPCell(new Paragraph(new Paragraph("", new Font(fontCN40))));
	            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	            cell.setColspan(4);
	            cell.setPadding(0.1F);
	            cell.setBorderColor(BaseColor.LIGHT_GRAY);
	            table.addCell(cell);

	            cell = new PdfPCell(new Paragraph(new Paragraph("订单详情：", new Font(fontCN40))));
	            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	            cell.setColspan(2);
	            cell.setPadding(5);
	            cell.setBorderWidth(0);
	            table.addCell(cell);
	            
	            cell = new PdfPCell(new Paragraph(new Paragraph("用户信息：", new Font(fontCN40))));
	            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	            cell.setColspan(2);
	            cell.setPadding(5);
	            cell.setBorderWidth(0);
	            table.addCell(cell);

	            cell = new PdfPCell(new Paragraph(new Paragraph("订单编号： 1223-1923-12312312", new Font(fontCN32))));
	            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	            cell.setPadding(5);
	            cell.setBorderWidth(0);
	            table.addCell(cell);
	            
	            cell = new PdfPCell(new Paragraph(new Paragraph("下单人： 尼姑哈", new Font(fontCN32))));
	            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	            cell.setPadding(5);
	            cell.setBorderWidth(0);
	            table.addCell(cell);
	            
	            cell = new PdfPCell(new Paragraph(new Paragraph("用户姓名： 收到哈", new Font(fontCN32))));
	            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	            cell.setPadding(5);
	            cell.setBorderWidth(0);
	            table.addCell(cell);


	            cell = new PdfPCell(new Paragraph(new Paragraph("备注：\n罚款的风景阿莱克斯多夫阿的江发的 父节点发的将发动机分开多久副科级的法兰克发的合法 ", new Font(fontCN30))));
	            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	            cell.setRowspan(32);
	            cell.setBorderWidth(0);
	            table.addCell(cell);
	            
	            cell = new PdfPCell(new Paragraph(new Paragraph("下单时间： 2018-01-01", new Font(fontCN32))));
	            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	            cell.setPadding(5);
	            cell.setBorderWidth(0);
	            table.addCell(cell);
	            
	            cell = new PdfPCell(new Paragraph(new Paragraph("", new Font(fontCN32))));
	            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	            cell.setPadding(5);
	            cell.setBorderWidth(0);
	            table.addCell(cell);
	            
	            cell = new PdfPCell(new Paragraph(new Paragraph("联系方式： 13367856787", new Font(fontCN32))));
	            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	            cell.setPadding(5);
	            cell.setBorderWidth(0);
	            table.addCell(cell);
	            
	            cell = new PdfPCell(new Paragraph(new Paragraph("", new Font(fontCN32))));
	            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	            cell.setPadding(5);
	            cell.setBorderWidth(0);
	            table.addCell(cell);
	            
	            cell = new PdfPCell(new Paragraph(new Paragraph("", new Font(fontCN32))));
	            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	            cell.setPadding(5);
	            cell.setBorderWidth(0);
	            cell.setBorderColor(BaseColor.LIGHT_GRAY);

	            table.addCell(cell);
	            
	            cell = new PdfPCell(new Paragraph(new Paragraph("收货地址：的将发觉", new Font(fontCN32))));
	            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	            cell.setPadding(5);
	            cell.setPaddingBottom(10);
	            cell.setBorderWidth(0);
	            table.addCell(cell);


	            //横线
	            cell = new PdfPCell(new Paragraph(new Paragraph("", new Font(fontCN40))));
	            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	            cell.setColspan(4);
	            cell.setPadding(0.1F);
	            cell.setBorderColor(BaseColor.LIGHT_GRAY);

	            table.addCell(cell);

	            image128 = Image.getInstance("http://127.0.0.1:8080/ps_produce/resources/images/u9.png");
	            image128.scaleToFit(120, 120);
	            barcodeCell = new PdfPCell(image128);
	            barcodeCell.setColspan(2);
	            barcodeCell.setRowspan(2);
	            barcodeCell.setHorizontalAlignment(Element.ALIGN_CENTER);
	            barcodeCell.setPadding(5);
	            cell.setPaddingTop(10);
	            cell.setPaddingBottom(10);
	            barcodeCell.setBorderWidth(0);
	            table.addCell(barcodeCell);

	            cell = new PdfPCell(new Paragraph(new Paragraph("商品信息：\n商品编号：sdff \n 颜色 ：", new Font(fontCN30))));
	            // cell.setPadding(5);
	            cell.setPaddingTop(10);
	            cell.setBorderWidth(0);
	            table.addCell(cell);

	            cell = new PdfPCell(new Paragraph(new Paragraph("尺寸名：\n数量：", new Font(fontCN30))));
	            // cell.setColspan(2);
	            // cell.setPadding(5);
	            cell.setPaddingTop(10);
	            cell.setPaddingBottom(10);
	            cell.setBorderWidth(0);
	            table.addCell(cell);

	            code128.setCode("EV21312312321312");

	            code128.setBarHeight(50f); // great! but what about width???
	            code128.setX(1f);
	            code128.setBaseline(20f);
	            code128.setSize(15);

	            image128 = code128.createImageWithBarcode(cd, null, null);
	            barcodeCell = new PdfPCell(image128);
	            barcodeCell.setColspan(2);
	            barcodeCell.setHorizontalAlignment(Element.ALIGN_LEFT);
	            barcodeCell.setPadding(5);
	            barcodeCell.setBorderWidth(0);
	            table.addCell(barcodeCell);

	          

	           
	              cell = new PdfPCell(new Paragraph(new Paragraph("", new Font(fontCN40))));
	            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	            cell.setColspan(4);
	            cell.setPadding(0.1F);
	            cell.setBorderColor(BaseColor.LIGHT_GRAY);

	            table.addCell(cell);
	            image128 = Image.getInstance("http://127.0.0.1:8080/ps_produce/resources/images/u9.png");
	            image128.scaleToFit(120, 120);
	            barcodeCell = new PdfPCell(image128);
	            barcodeCell.setColspan(2);
	            barcodeCell.setRowspan(2);
	            barcodeCell.setHorizontalAlignment(Element.ALIGN_CENTER);
	            barcodeCell.setPadding(5);
	            barcodeCell.setBorderWidth(0);
	            table.addCell(barcodeCell);

	            cell = new PdfPCell(new Paragraph(new Paragraph("商品信息：\n商品编号：sdff \n 颜色 ：", new Font(fontCN30))));
	            // cell.setPadding(5);
	            cell.setBorderWidth(0);
	            table.addCell(cell);

	            cell = new PdfPCell(new Paragraph(new Paragraph("尺寸名：\n数量：", new Font(fontCN30))));
	            // cell.setColspan(2);
	            // cell.setPadding(5);
	            cell.setBorderWidth(0);
	            table.addCell(cell);

	            code128.setCode("EV3127693312");

	            // code128.setBarHeight(50f); // great! but what about width???
	            // code128.setX(1f);
	            // code128.setBaseline(20f);
	            // code128.setSize(15);

	            image128 = code128.createImageWithBarcode(cd, null, null);
	            barcodeCell = new PdfPCell(image128);
	            barcodeCell.setColspan(2);
	            barcodeCell.setHorizontalAlignment(Element.ALIGN_LEFT);
	            barcodeCell.setPadding(5);
	            barcodeCell.setBorderWidth(0);
	            table.addCell(barcodeCell);
	            document.add(table);
        
	            document.newPage();
	    
	             document.add(new Paragraph("This PageSize is A3."));

	          
	            // 6.关闭文档
	            document.close();
	            writer.close();
	            byte[] bytes = baos.toByteArray();
	            final HttpHeaders headers = new HttpHeaders();
	            headers.add("Content-Type", "application/pdf");
	    		return new ResponseEntity<byte[]>(bytes, headers, HttpStatus.CREATED);
		
	}
	
	 public  BufferedImage createBarcodeImage(int barCodeWidth, int barCodeHeight,   
		        String code) throws Exception {   
		    // list不能为空   
		    // 图片宽度   
		   // int imageWidth = (barCodeWidth + barCodeWidth / 2) * 1 + barCodeWidth / 2;   
		    int imageWidth = barCodeWidth;  
		    // 图片高度   
		    int imageHeight = barCodeHeight + 20 * 2;   
		    BufferedImage img = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);   
		    Graphics2D g = (Graphics2D) img.getGraphics();   
		    g.fillRect(0, 0, imageWidth, imageHeight);   
		    java.awt.Font font = new java.awt.Font("", java.awt.Font.PLAIN, 16);   
		    Barcode128 barcode128 = new Barcode128();   
		    FontRenderContext fontRenderContext = g.getFontRenderContext();   
		    // 条形码（文字）的高度   
		    int stringHeight = (int) font.getStringBounds("", fontRenderContext).getHeight();   
		    // 图片横坐标开始位置   
		    int startX = 0;   
		    // 图片纵坐标开始位置   
		    int imageStartY = (imageHeight - barCodeHeight - stringHeight) / 2;   
		    int stringStartY = imageStartY * 2 + barCodeHeight+10;// 条形码（文字）开始位置   
		        int codeWidth = (int) font.getStringBounds(code, fontRenderContext).getWidth();   
		        barcode128.setCode(code);   
		        java.awt.Image codeImg = barcode128.createAwtImage(Color.black, Color.white);   
		        g.drawImage(codeImg, startX, imageStartY, barCodeWidth, barCodeHeight, Color.white, null);   
		        // 为图片添加条形码（文字），位置为条形码图片的下部居中   
		        AttributedString ats = new AttributedString(code);   
		        ats.addAttribute(TextAttribute.FONT, font, 0, code.length());   
		        AttributedCharacterIterator iter = ats.getIterator();   
		        // 设置条形码（文字）的颜色为蓝色   
		        g.setColor(Color.BLACK);   
		        // 绘制条形码（文字）   
		        g.drawString(iter, startX + (barCodeWidth - codeWidth) / 2, stringStartY);   
		        // 更改图片横坐标开始位置，图片之间的空隙为条形码的宽度的一半   
		        startX = startX + barCodeWidth / 2 + barCodeWidth;   
		     
		    g.dispose();   
		    return img;
		   // OutputStream os = new BufferedOutputStream(new FileOutputStream("C:\\Users\\fanice\\Desktop\\考勤\\tt.png"));   
		   // ImageIO.write(img, "PNG", os);   
		}  
	 
	

  }
