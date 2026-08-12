/*    */ import java.io.IOException;
/*    */ import java.lang.reflect.Field;
/*    */ import java.lang.reflect.Method;
/*    */ import java.sql.Connection;
/*    */ import java.sql.ResultSet;
/*    */ import java.sql.ResultSetMetaData;
/*    */ import java.sql.SQLException;
/*    */ import java.sql.Statement;
/*    */ import javax.servlet.ServletOutputStream;
/*    */ import javax.servlet.http.HttpServlet;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import javax.servlet.http.HttpServletResponse;
/*    */ 
 public class LAKDL_AF_MK_View_Help_Servlet extends HttpServlet
 {
		/*
   Connection conn;
   Statement stmt;
   public ResultSet rs;
   public ResultSetMetaData rms;
   public Object Ret_Obj;
	*/
 
   //public synchronized void service(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse)
	public void service(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse)
     throws IOException
   {
		
		Connection conn=null;
   Statement stmt=null;
    ResultSet rs=null;
    ResultSetMetaData rms=null;
    Object Ret_Obj=null;
		
		
     try
/*    */     {
/* 23 */       LAKDL_AF_CO_conn_methods localLAKDL_AF_CO_conn_methods = new LAKDL_AF_CO_conn_methods();
/* 24 */       conn = localLAKDL_AF_CO_conn_methods.met_user_validate(paramHttpServletRequest);
               String m_html_client_home_url = localLAKDL_AF_CO_conn_methods.servlet_client_url;
/* 25 */       String str1 = localLAKDL_AF_CO_conn_methods.schema_name.trim();
/* 26 */       String str2 = localLAKDL_AF_CO_conn_methods.username;
/* 27 */       String str3 = localLAKDL_AF_CO_conn_methods.html_client_url.trim();
/*    */ 
/* 29 */       paramHttpServletResponse.setStatus(200);
/* 30 */       paramHttpServletResponse.setContentType("text/html");
/* 31 */       paramHttpServletResponse.setHeader("Cache-Control", "No-Cache");
/* 32 */       paramHttpServletResponse.setDateHeader("Expires", 0L);
/*    */ 
/* 34 */       String str4 = paramHttpServletRequest.getParameter("class_in");
/* 35 */       String str5 = paramHttpServletRequest.getParameter("Sql_in");
/* 36 */       String str6 = paramHttpServletRequest.getParameter("Start_in");
/* 37 */       String str7 = paramHttpServletRequest.getParameter("End_in"); 
/* 38 */       String str8 = paramHttpServletRequest.getParameter("Hid_No");
/* 39 */       String str9 = str5 + "_Header";
/* 40 */       String str10 = paramHttpServletRequest.getParameter("Crit_In").toUpperCase();
/*    */ 
/* 42 */       Class localClass = Class.forName(str4);
/* 43 */       Object localObject1 = localClass.newInstance();
/* 44 */       Method[] arrayOfMethod = localClass.getDeclaredMethods();
/* 45 */       ServletOutputStream localServletOutputStream2 = paramHttpServletResponse.getOutputStream();
/*    */ 
/* 47 */       String str11 = str5;
/* 48 */       String str12 = str6;
/* 49 */       String str13 = str7;
/* 50 */       String str14 = str10;
/* 51 */       String str15 = str2;
/*    */ 
/* 54 */       Object[] arrayOfObject = new Object[5];
/*    */ 
/* 56 */       arrayOfObject[0] = str11;
/* 57 */       arrayOfObject[1] = str12;
/* 58 */       arrayOfObject[2] = str13;
/* 59 */       arrayOfObject[3] = str14;
/* 60 */       arrayOfObject[4] = str15;
/*    */ 
/* 62 */       Object localObject2 = arrayOfMethod[0].invoke(localObject1, arrayOfObject);
/*    */ 
/* 64 */       Field localField1 = localClass.getField(str5);
/* 65 */       Object localObject3 = new Object();
/* 66 */       Field localField2 = localClass.getField(str9);
/* 67 */       Object localObject4 = new Object();
/*    */       try
/*    */       {
/* 70 */         localObject3 = localField1.get(localObject1);
/* 71 */         localObject4 = localField2.get(localObject1);
/*    */       }
/*    */       catch (IllegalAccessException localIllegalAccessException) {
/* 74 */         localServletOutputStream2.println("Invalid");
/*    */       }
/*    */ 
/* 77 */       String str16 = localObject3.toString();
/* 78 */       String str17 = localObject4.toString();
/* 79 */       String[] arrayOfString = new String[10];
/* 80 */       int i = 1;
/* 81 */       stmt = conn.createStatement();
/*    */       try
/*    */       {
/* 86 */         rs = stmt.executeQuery(str16);
/*    */       }
/*    */       catch (SQLException localSQLException) {
/* 89 */         localServletOutputStream2.println(localSQLException.toString());
/*    */       }
/*    */ 
/* 92 */       rms = rs.getMetaData();
/* 93 */       boolean bool = rs.next();
/* 94 */       int j = rms.getColumnCount();
/*    */ 
/* 98 */       localServletOutputStream2.println("<HTML><HEAD>");
/* 99 */       localServletOutputStream2.println("<TITLE> " + str17 + "</TITLE>");
/* 100 */       localServletOutputStream2.println("<link REL='STYLESHEET' HREF='" + str3 + "/css/Asset_Financing_System.css' TYPE=\"text/css\">");
/*    */ 
/* 102 */       localServletOutputStream2.println("<SCRIPT>");
/*    */ 
/* 104 */       localServletOutputStream2.println("function onmouseover_action(m_col1){");
/* 105 */       localServletOutputStream2.println(" m_col1.style.background='darkblue';");
/* 106 */       localServletOutputStream2.println(" m_col1.style.color='white';");
/* 107 */       localServletOutputStream2.println("}");
/*    */ 
/* 109 */       localServletOutputStream2.println("function MyDialog(){");
/* 110 */       localServletOutputStream2.println(" this.valin;");
/* 111 */       localServletOutputStream2.println(" this.valout = new Array(50);");
/* 112 */       localServletOutputStream2.println("}");
/*    */ 
/* 114 */       localServletOutputStream2.println("function onmouseout_action(m_col1){");
/* 115 */       localServletOutputStream2.println(" m_col1.style.color='black';");
/* 116 */       localServletOutputStream2.println(" m_col1.style.background='white';");
/* 117 */       localServletOutputStream2.println("}");
/*    */ 
/* 119 */       localServletOutputStream2.println("function Selected(ref,val,veri,Start,End){");
/* 120 */       localServletOutputStream2.println("if(veri=='1'){");
/* 121 */       localServletOutputStream2.println("window.dialogArguments.valout [1] = 'Next';");
/* 122 */       localServletOutputStream2.println("window.dialogArguments.valout [2] =  Start  ;");
/* 123 */       localServletOutputStream2.println("window.dialogArguments.valout [3] =  End;");
/* 124 */       localServletOutputStream2.println(" window.close();");
/* 125 */       localServletOutputStream2.println("}else{");
/* 126 */       localServletOutputStream2.println("if(veri=='2'){");
/* 127 */       localServletOutputStream2.println("window.dialogArguments.valout [1] = 'Prev';");
/* 128 */       localServletOutputStream2.println("window.dialogArguments.valout [2] =  Start  ;");
/* 129 */       localServletOutputStream2.println("window.dialogArguments.valout [3] =  End;");
/* 130 */       localServletOutputStream2.println(" window.close();");
/* 131 */       localServletOutputStream2.println("}else{");
/* 132 */       localServletOutputStream2.println("for(i=1;i<=" + j + ";i++){");
/* 133 */       //localServletOutputStream2.println("window.dialogArguments.valout [i] =ref[val][i];}");
                localServletOutputStream2.println("window.dialogArguments.valout [i] = replace_and_sign(ref[val][i]);}");//ADDED MILINDA FOR REPLACE SPECIAL CHARACTOR
/*    */ 
/* 135 */       localServletOutputStream2.println("  }}");
/* 136 */       localServletOutputStream2.println("}"); 
/*    */ 
/* 138 */       localServletOutputStream2.println("function Next() {");
/* 139 */       int k = Integer.parseInt(str7) + 50;
/* 140 */       int m = Integer.parseInt(str7) + 1;
/* 141 */       localServletOutputStream2.println("oBj = new MyDialog()");
/* 142 */       localServletOutputStream2.println(" Selected(oBj.valout,'1','1','" + m + "','" + k + "');");
/* 143 */       localServletOutputStream2.println("}");
/*    */ 
/* 145 */       localServletOutputStream2.println("function Prev() {");
/* 146 */       int n = Integer.parseInt(str6) - 50;
/* 147 */       int i1 = Integer.parseInt(str6) - 1;
/* 148 */       localServletOutputStream2.println("oBj= new MyDialog()");
/* 149 */       localServletOutputStream2.println(" Selected(oBj.valout,'1','2','" + n + "','" + i1 + "');");
/* 150 */       localServletOutputStream2.println("}");
/*    */ 
/* 152 */       localServletOutputStream2.println(" function Close(){");
/* 153 */       localServletOutputStream2.println("  window.dialogArguments.valout [1] = 'Close';");
/* 154 */       localServletOutputStream2.println("  window.close();");
/* 155 */       localServletOutputStream2.println(" }");
/*    */ 
/* 157 */       localServletOutputStream2.println("</SCRIPT>");
/* 158 */       localServletOutputStream2.println("</HEAD>");
/* 159 */       localServletOutputStream2.println("<BODY class='body & txt-body' topmargin=0 leftmargin=0 >");
/* 160 */       localServletOutputStream2.println("<FORM NAME=\"form1\">");
/*    */ 
/* 163 */       if (bool) {
/* 164 */         localServletOutputStream2.println("<TABLE WIDTH='100%' class='pdn_table' border='1'>");
/* 165 */         localServletOutputStream2.println("<TR class='txt_input_help_head' align='center'>");
/* 166 */         int i2 = Integer.parseInt(str8);
/* 167 */         for (int i3 = 1; i3 <= j - i2; i3++) {
/* 168 */           String str18 = rms.getColumnLabel(i3);
/* 169 */           localServletOutputStream2.println("<TD class='txt_input_help_head'>" + str18 + "</TD>");
/*    */         }
/* 171 */         localServletOutputStream2.println("</TR>");
/* 172 */         int i4 = 0;
/* 173 */         localServletOutputStream2.println("<SCRIPT LANGUAGE = 'JavaScript'>");
/* 174 */         localServletOutputStream2.println("ColCount = " + j + ";");
/* 175 */         localServletOutputStream2.println(" NewArr = new Array(150);");
/* 176 */         localServletOutputStream2.println(" for(i=1;i<=150;i++){");
/* 177 */         localServletOutputStream2.println(" NewArr[i] = new Array(ColCount);}");
/* 178 */         localServletOutputStream2.println("</SCRIPT>");
/*    */         int i5;
/*    */         int i6;
/* 179 */         while (bool) {
/* 180 */           if (rs.getString(1).equals("1")) {
/* 181 */             i4 = 1;
/*    */           }
/* 183 */           if (i < 51) {
/* 184 */             localServletOutputStream2.println("<TR ID=M_LOC" + i + " class='txt_input_help'  onmouseover=\"onmouseover_action(M_LOC" + i + ")\" onmouseout=\"onmouseout_action(M_LOC" + i + ");\" >");
/* 185 */             for (i5 = 1; i5 <= j; i5++) {
/* 186 */               localServletOutputStream2.println("<SCRIPT LANGUAGE = 'JavaScript'>");
/* 187 */               localServletOutputStream2.println(" NewArr[" + i + "][" + i5 + "]=\"" + rs.getString(i5) + "\";");
/* 188 */               localServletOutputStream2.println("</SCRIPT>");
/*    */             }
/* 190 */             for (i6 = 1; i6 <= j - i2; i6++) {
/* 191 */               localServletOutputStream2.println("<TD onclick=\"Selected(NewArr,'" + i + "','0')\" class='txt_input_help'>" + rs.getString(i6) + "</TD>");
/*    */             }
/*    */ 
/* 194 */             localServletOutputStream2.println("</TR>");
/*    */           }
/* 196 */           i += 1;
/* 197 */           bool = rs.next();
/*    */         }
/* 199 */         rs.close();
/* 200 */         if (i <= Integer.parseInt(str7)) {
/* 201 */           localServletOutputStream2.println("<TABLE width='100%' bgcolor='white'>");
/* 202 */           for (i5 = i; i5 <= 50; i5++) {
/* 203 */             localServletOutputStream2.println("<TR STYLE='{font: 7pt Verdana;}'>");
/* 204 */             for (i6 = 1; i6 <= j; i6++) {
/* 205 */               localServletOutputStream2.println("<TD> </TD>");
/*    */             }
/* 207 */             localServletOutputStream2.println("</TR>");
/*    */           }
/*    */         }
/* 210 */         localServletOutputStream2.println("</TABLE>");
/* 211 */         localServletOutputStream2.println("<TABLE width='100%' cellpadding='1'>");
/* 212 */         localServletOutputStream2.println("<TR ><TD width='20%'></TD><TD>");
/* 213 */         if (i4 != 0) {
/* 214 */           localServletOutputStream2.println("<INPUT TYPE ='Button' NAME ='Prev_Val' VALUE ='Previous' class='mainbut' DISABLED ></TD><TD>");
/*    */         }
/*    */         else {
/* 217 */           localServletOutputStream2.println("<INPUT TYPE ='Button' NAME ='Prev_Val' VALUE ='Previous' class='mainbut' OnClick='Prev();'></TD><TD>");
/*    */         }
/*    */ 
/* 221 */         if (i <= 51) {
/* 222 */           localServletOutputStream2.println("<INPUT TYPE ='Button' NAME ='Next_Val' VALUE ='Next' class='mainbut' DISABLED ></TD><TD>");
/*    */         }
/*    */         else {
/* 225 */           localServletOutputStream2.println("<INPUT TYPE ='Button' NAME ='Next_Val' VALUE ='Next' class='mainbut'  OnClick='Next();' ></TD><TD>");
/*    */         }
/*    */ 
/* 228 */         localServletOutputStream2.println("<INPUT TYPE ='Button' NAME ='Exit'  VALUE ='   Exit   ' class='mainbut' OnClick='Close();'></TD><TD align='right'></TD><TD width='20%'></TD></TR>");
/* 229 */         localServletOutputStream2.println("</TABLE>");
/*    */       }
/*    */       else {
/* 232 */         localServletOutputStream2.println("<br><br><br><br><br><p align='center'><font face='Verdana' color=darkblue size=4 boldness=700 >No records available</font></p>");
/*    */       }
/*    */        localServletOutputStream2.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_home_url+"/ajax_data_gateway.js'></SCRIPT>");//[ADDED BY MILIND FOR JB07102021-15043]
/* 235 */       localServletOutputStream2.println("</BODY>");
/* 236 */       localServletOutputStream2.println("</HTML>");
/*    */       try
/*    */       {
/* 239 */         conn.close();
/*    */       }
/*    */       catch (Exception localException3) {
/* 242 */         localServletOutputStream2.println("Exception at help_exe " + localException3.toString());
/*    */       }
/* 244 */       localServletOutputStream2.close();
/* 245 */       destroy();
/*    */     }
/*    */     catch (Exception localException1) {
/*    */       try {
/* 249 */         conn.close();
/*    */       } catch (Exception localException2) {
/*    */       }
/* 252 */       ServletOutputStream localServletOutputStream1 = paramHttpServletResponse.getOutputStream();
/* 253 */       localServletOutputStream1.close();
/*    */     }
/*    */   }
/*    */ }

/* Location:           E:\DESKTOP FILES\Transfer\LAKDL\2017\June\03-06-2017\From Live\
 * Qualified Name:     LAKDL_AF_MK_View_Help_Servlet
 * JD-Core Version:    0.6.2
 */