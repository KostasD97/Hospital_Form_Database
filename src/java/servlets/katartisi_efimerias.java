/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.text.*;

/**
 *
 * @author kosta
 */
@WebServlet(name = "katartisi_efimerias", urlPatterns = {"/katartisi_efimerias"})
public class katartisi_efimerias extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String dateIn = request.getParameter("date");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            Date date = sdf.parse(dateIn);//.format(dateIn);
            System.out.println("testtt"+date); 
            String doctor1 = request.getParameter("doctor1");
            String doctor2 = request.getParameter("doctor2");
            String nurse1 = request.getParameter("nurse1");
            String nurse2 = request.getParameter("nurse2");
            String nurse3 = request.getParameter("nurse3");
            String admin = request.getParameter("admin");
            
            System.out.println("date:"+date);
            String driver = "com.mysql.cj.jdbc.Driver";
            String url = "jdbc:mysql://localhost:3306/hospital";
            String username = "root";
            String password = "";
            Class.forName(driver);
            Connection con = DriverManager.getConnection(url, username, password);//DriverManager.getConnection(url + ":" + port + "/" + db + "?characterEncoding=UTF-8", username, password);
                       
            String sql = "INSERT INTO `efimeria`(`doctor_1`, `doctor_2`, `nurse_1`, `nurse_2`, `nurse_3`, `admin`, `hmeromhnia`) VALUES ("+doctor1+","
                    + ""+doctor2+","+nurse1+","+nurse2+","+nurse3+","+admin+", '"+dateIn+"')";
            System.out.println("sql:"+sql);
            Statement stmt = con.createStatement(); 
            int rs = stmt.executeUpdate(sql);
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet katartisi_efimerias</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet katartisi_efimerias at " + request.getContextPath() + "</h1>");
      
            out.println("</body>");
            out.println("</html>");
            con.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(katartisi_efimerias.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(katartisi_efimerias.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(katartisi_efimerias.class.getName()).log(Level.SEVERE, null, ex);
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
