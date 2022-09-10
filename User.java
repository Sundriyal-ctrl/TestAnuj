
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class User extends HttpServlet{  
  @Override
  public void doGet(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException
  {
     PrintWriter out=res.getWriter();
     res.setContentType("text/html");
     try{
         String rex="(.)*(\\d)(.)*";
            Pattern p=Pattern.compile(rex);
            
         String Fname=req.getParameter("fname");
         String Lname=req.getParameter("lname");
         Long mobile=Long.parseLong(req.getParameter("number"));
         String city=req.getParameter("city");
          String edu=req.getParameter("edu");
           String pass=req.getParameter("pass");
           Matcher m=p.matcher(Fname);
         if(!m.matches())
         {
             m=p.matcher(Lname);
                       if(!m.matches())
                     {
                       m=p.matcher(city);
                       if(!m.matches())
                       {
                           
                       
                     
      Class.forName("com.mysql.jdbc.Driver");
      Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/anuj","root","");
      PreparedStatement pt=con.prepareStatement("insert into res values(?,?,?,?,?,?)");
      pt.setString(1,Fname);
      pt.setString(2,Lname);
      pt.setLong(3,mobile);
      pt.setString(4,city);
      pt.setString(5,edu);
      pt.setString(6,pass);
      if(!pt.execute())
      {
          out.println("You Are Registered Successfully");
          ServletContext ser=req.getServletContext();
          ser.setAttribute("Fname",Fname);
          ser.setAttribute("Pass", pass);
          ser.setAttribute("mobile",mobile);
          RequestDispatcher rt=req.getRequestDispatcher("login.html");
          rt.include(req, res);
      }
         }}}
//     String Fname=req.getParameter("fname");
//     for(int i=0;i<Fname.length();i++)
//     {
//         if(Fname.charAt(i)<65 && Fname.charAt(i)>90)
//             out.println("Name is Not Valid");
//         
//     }
//     String Lname=req.getParameter("lname");
//     for(int i=0;i<Lname.length();i++)
//     {
//         if(Lname.charAt(i)<65 && Lname.charAt(i)>90)
//             out.println("LName is Not Valid");
//         
//     }
//     long mobile=Long.parseLong(req.getParameter("number"));
//     String city=req.getParameter("city");
//      for(int i=0;i<city.length();i++)
//     {
//         if(city.charAt(i)<65 && city.charAt(i)>90)
//             out.println("city is Not Valid");
//         
//     }
//     String edu=req.getParameter("edu");
//      for(int i=0;i<Lname.length();i++)
//     {
//         if(edu.charAt(i)<65 && edu.charAt(i)>90)
//             out.println("Education is Not Valid");
//         
//     }
      
      else
         {
         out.println("Wrong Registration");
         }
     }catch(Exception e)
     {
         out.println(e.getMessage());
     }
  }
}

