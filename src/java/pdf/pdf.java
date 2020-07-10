
package pdf;

import DAO.CategoriaDAO;
import DAO.CategoriaDAOImplementar;
import Model.Categoria;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "pdf", urlPatterns = {"/pdf"})
public class pdf extends HttpServlet {
    private Object Chunck;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/pdf");
        OutputStream out = response.getOutputStream();
        try  {
            CategoriaDAOImplementar cat = new CategoriaDAOImplementar();
            try{
                Document documento = new Document();
                PdfWriter.getInstance(documento, out);
                
                documento.open();
                
                Paragraph parte1 = new Paragraph();
             
                Font titulo = new Font(Font.FontFamily.TIMES_ROMAN,20,Font.ITALIC,BaseColor.BLACK);
                parte1.add(new Phrase("CATEGORIAS",titulo));
                parte1.setAlignment(Element.ALIGN_CENTER);
                //espacios
                parte1.add(new Phrase(Chunk.NEWLINE));
                parte1.add(new Phrase(Chunk.NEWLINE));
                documento.add(parte1);
                PdfPTable tabla = new PdfPTable(3);
                PdfPCell UNO = new PdfPCell(new Paragraph("Codigo",FontFactory.getFont("Arial",12,Font.BOLD,BaseColor.BLACK)));
                PdfPCell DOS = new PdfPCell(new Paragraph("Nombres",FontFactory.getFont("Arial",12,Font.BOLD,BaseColor.BLACK)));
                PdfPCell TRES = new PdfPCell(new Paragraph("Estado",FontFactory.getFont("Arial",12,Font.BOLD,BaseColor.BLACK)));
                tabla.addCell(UNO);
                tabla.addCell(DOS);
                tabla.addCell(TRES);
                
        CategoriaDAO categoria = new CategoriaDAOImplementar(); //To change body of generated methods, choose Tools | Templates.
       java.util.List<Categoria> listar = categoria.Listar();
        
        for ( Categoria categoriaListar : listar) {
            String v1 = String.valueOf(categoriaListar.getId_categoria());
            String v2 = String.valueOf(categoriaListar.getEstado_categoria());
            tabla.addCell(v1);
            tabla.addCell(categoriaListar.getNom_categoria());
            tabla.addCell(v2);
            
        }
        documento.add(tabla);
      documento.close();//su accion termina cierra el documento
            }catch(DocumentException ex){
            ex.getMessage();
            }
           
        } finally{
        out.close();
        }
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
