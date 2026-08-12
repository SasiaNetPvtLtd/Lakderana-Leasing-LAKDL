/*    */ import java.io.IOException;
/*    */ import java.lang.reflect.Field;
/*    */ import java.lang.reflect.Method;
/*    */ import javax.servlet.ServletOutputStream;
/*    */ import javax.servlet.http.HttpServlet;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import javax.servlet.http.HttpServletResponse;
/*    */ 
 public class LAKDL_AF_MAS_Help_Msg_Servlet extends HttpServlet
 {
   //public Object Ret_Obj;
 
   //public synchronized void service(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse)
	public void service(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse)
     throws IOException
   {
		
		Object Ret_Obj=null;
		
     try
/*    */     {
/* 14 */       LAKDL_AF_CO_conn_methods localLAKDL_AF_CO_conn_methods = new LAKDL_AF_CO_conn_methods();
/* 15 */       String str1 = localLAKDL_AF_CO_conn_methods.schema_name.trim();
/* 16 */       String str2 = localLAKDL_AF_CO_conn_methods.username;
/*    */ 
/* 18 */       paramHttpServletResponse.setStatus(200);
/* 19 */       paramHttpServletResponse.setContentType("text/html");
/* 20 */       paramHttpServletResponse.setHeader("Cache-Control", "No-Cache");
/* 21 */       paramHttpServletResponse.setDateHeader("Expires", 0L);
/*    */ 
/* 23 */       String str3 = paramHttpServletRequest.getParameter("class_in");
/* 24 */       String str4 = paramHttpServletRequest.getParameter("help_message_in");
/* 25 */       String str5 = str4 + "_Header";
/*    */ 
/* 27 */       Class localClass = Class.forName(str3);
/* 28 */       Object localObject1 = localClass.newInstance();
/* 29 */       Method[] arrayOfMethod = localClass.getDeclaredMethods();
/* 30 */       ServletOutputStream localServletOutputStream = paramHttpServletResponse.getOutputStream();
/*    */ 
/* 33 */       String str6 = str4;
/*    */ 
/* 35 */       Object[] arrayOfObject = new Object[1];
/*    */ 
/* 37 */       arrayOfObject[0] = str6;
/*    */ 
/* 39 */       Object localObject2 = arrayOfMethod[0].invoke(localObject1, arrayOfObject);
/*    */ 
/* 41 */       Field localField1 = localClass.getField(str4);
/* 42 */       Object localObject3 = new Object();
/* 43 */       Field localField2 = localClass.getField(str5);
/* 44 */       Object localObject4 = new Object();
/*    */       try
/*    */       {
/* 47 */         localObject3 = localField1.get(localObject1);
/* 48 */         localObject4 = localField2.get(localObject1);
/*    */       }
/*    */       catch (IllegalAccessException localIllegalAccessException) {
/* 51 */         localServletOutputStream.println("Invalid");
/*    */       }
/*    */ 
/* 54 */       String str7 = localObject3.toString();
/* 55 */       String str8 = localObject4.toString();
/*    */ 
/* 60 */       localServletOutputStream.println("<HTML><HEAD>");
/* 61 */       localServletOutputStream.println("<TITLE> " + str8 + "</TITLE>");
/* 62 */       localServletOutputStream.println("</HEAD>");
/* 63 */       localServletOutputStream.println("<BODY BGCOLOR='white' topmargin=0 leftmargin=0 >");
/* 64 */       localServletOutputStream.println("<FORM NAME=\"form1\">");
/* 65 */       localServletOutputStream.println("<TABLE BGCOLOR='white' WIDTH='100%' STYLE='{color: blue; font: 9pt Verdana;}' border='0'>");
/* 66 */       localServletOutputStream.println("<TR BGCOLOR='white' align='center'>");
/* 67 */       localServletOutputStream.println("<TD STYLE='{font: bold 8pt Verdana;}'><CENTER><U>" + str8 + "</u></CENTER></TD>");
/* 68 */       localServletOutputStream.println("</TR>");
/* 69 */       localServletOutputStream.println("</TABLE>");
/* 70 */       localServletOutputStream.println("<BR>");
/* 71 */       localServletOutputStream.println("<BR>");
/* 72 */       localServletOutputStream.println("<TABLE BGCOLOR='white' WIDTH='100%' STYLE='{color: black; font: 7pt Verdana;}' border='0'>");
/* 73 */       localServletOutputStream.println("<TR BGCOLOR='white' align='left'>");
/* 74 */       localServletOutputStream.println("<TD STYLE='{font: bold 8pt Verdana;}'>" + str7 + "</TD>");
/* 75 */       localServletOutputStream.println("</TR>");
/* 76 */       localServletOutputStream.println("</TABLE>");
/* 77 */       localServletOutputStream.println("</BODY>");
/* 78 */       localServletOutputStream.println("</HTML>");
/* 79 */       localServletOutputStream.close();
/* 80 */       destroy();
/*    */     }
/*    */     catch (Exception localException)
/*    */     {
/*    */     }
/*    */   }
/*    */ }

/* Location:           E:\DESKTOP FILES\Transfer\LAKDL\2017\June\03-06-2017\From Live\
 * Qualified Name:     LAKDL_AF_MAS_Help_Msg_Servlet
 * JD-Core Version:    0.6.2
 */