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
 public class LAKDL_AF_MAS_View_Help_Servlet extends HttpServlet
 {
		
		/*
   Connection conn;
   Statement stmt;
   public ResultSet rs;
   public ResultSetMetaData rms;
   public Object Ret_Obj;
	*/
 
   //public synchronized void service(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse)
	public  void service(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse)
     throws IOException
   {
		
		Connection conn=null;
   Statement stmt=null;
    ResultSet rs=null;
    ResultSetMetaData rms=null;
    Object Ret_Obj=null;
		
		
/*    */     try
/*    */     {
/* 24 */       LAKDL_AF_CO_conn_methods localLAKDL_AF_CO_conn_methods = new LAKDL_AF_CO_conn_methods();
/* 25 */       conn = localLAKDL_AF_CO_conn_methods.met_user_validate(paramHttpServletRequest);
               String m_html_client_home_url = localLAKDL_AF_CO_conn_methods.servlet_client_url;
/* 26 */       String str1 = localLAKDL_AF_CO_conn_methods.schema_name.trim();
/* 27 */       String str2 = localLAKDL_AF_CO_conn_methods.username;
/* 28 */       String str3 = localLAKDL_AF_CO_conn_methods.html_client_url.trim();
/* 29 */       String str4 = localLAKDL_AF_CO_conn_methods.servlet_client_url.trim() + ":" + localLAKDL_AF_CO_conn_methods.client_t3_port.trim();
/* 30 */       String str5 = localLAKDL_AF_CO_conn_methods.client_name.trim();
/*    */ 
/* 32 */       paramHttpServletResponse.setStatus(200);
/* 33 */       paramHttpServletResponse.setContentType("text/html");
/* 34 */       paramHttpServletResponse.setHeader("Cache-Control", "No-Cache");
/* 35 */       paramHttpServletResponse.setDateHeader("Expires", 0L);
/*    */ 
/* 37 */       String str6 = paramHttpServletRequest.getParameter("class_in");
/* 38 */       String str7 = paramHttpServletRequest.getParameter("Sql_in");
/* 39 */       String str8 = paramHttpServletRequest.getParameter("Start_in");
/* 40 */       String str9 = paramHttpServletRequest.getParameter("End_in");
/* 41 */       String str10 = paramHttpServletRequest.getParameter("Hid_No");
/* 42 */       String str11 = str7 + "_Header";
/* 43 */       String str12 = paramHttpServletRequest.getParameter("Crit_In").toUpperCase();
/* 44 */       String str13 = "";
/*    */  
/* 46 */       if (paramHttpServletRequest.getParameter("Screen_Name") != null) {
/* 47 */         str13 = paramHttpServletRequest.getParameter("Screen_Name");
/*    */       }
/*    */ 
/* 50 */       Class localClass = Class.forName(str6);
/* 51 */       Object localObject1 = localClass.newInstance();
/* 52 */       Method[] arrayOfMethod = localClass.getDeclaredMethods();
/* 53 */       ServletOutputStream localServletOutputStream2 = paramHttpServletResponse.getOutputStream();
/*    */ 
/* 55 */       String str14 = str7;
/* 56 */       String str15 = str8;
/* 57 */       String str16 = str9;
/* 58 */       String str17 = str12;
/* 59 */       String str18 = str2;
/*    */ 
/* 62 */       Object[] arrayOfObject = new Object[5];
/*    */ 
/* 64 */       arrayOfObject[0] = str14;
/* 65 */       arrayOfObject[1] = str15;
/* 66 */       arrayOfObject[2] = str16;
/* 67 */       arrayOfObject[3] = str17;
/* 68 */       arrayOfObject[4] = str18;
/*    */ 
/* 70 */       Object localObject2 = arrayOfMethod[0].invoke(localObject1, arrayOfObject);
/*    */ 
/* 72 */       Field localField1 = localClass.getField(str7);
/* 73 */       Object localObject3 = new Object();
/* 74 */       Field localField2 = localClass.getField(str11);
/* 75 */       Object localObject4 = new Object();
/*    */       try
/*    */       {
/* 78 */         localObject3 = localField1.get(localObject1);
/* 79 */         localObject4 = localField2.get(localObject1);
/*    */       }
/*    */       catch (IllegalAccessException localIllegalAccessException) {
/* 82 */         localServletOutputStream2.println("Invalid");
/*    */       }
/*    */ 
/* 85 */       String str19 = localObject3.toString();
/* 86 */       String str20 = localObject4.toString();
/* 87 */       String[] arrayOfString = new String[10];
/* 88 */       int i = 1;
/* 89 */       stmt = conn.createStatement();
/*    */       try
/*    */       {
/* 94 */         rs = stmt.executeQuery(str19);
/*    */       }
/*    */       catch (SQLException localSQLException) {
/* 97 */         localServletOutputStream2.println(localSQLException.toString());
/*    */       }
/*    */ 
/* 100 */       rms = rs.getMetaData();
/* 101 */       boolean bool = rs.next();
/* 102 */       int j = rms.getColumnCount();
/*    */ 
/* 106 */       localServletOutputStream2.println("<HTML><HEAD>");
/* 107 */       localServletOutputStream2.println("<TITLE> " + str20 + "</TITLE>");
/* 108 */       localServletOutputStream2.println("<link REL='STYLESHEET' HREF='" + str3 + "/css/Asset_Financing_System.css' TYPE=\"text/css\">");
/*    */ 
/* 110 */       localServletOutputStream2.println("<SCRIPT>");
/*    */ 
/* 112 */       localServletOutputStream2.println("function show_emp_details(m_emp_code) {");
/* 113 */       localServletOutputStream2.println("\t\t m_url=\"" + str4 + "/" + str5 + "AF_MAS_emp_details_drill_down?chksql=LOAD_EMP_DETAILS_DRILL&emp_code=\"+m_emp_code;");
/* 114 */       localServletOutputStream2.println("    popupwin=window.open(m_url,'displayWindow1','left=80,top=110,width=1000,height=450,toolbar=0,location=0,center:yes,direction=0,menuBar=1,status=0,scrollbars=1,resizable=1');");
/* 115 */       localServletOutputStream2.println("}");
/*    */ 
/* 117 */       localServletOutputStream2.println("function onmouseover_action(m_col1){");
/* 118 */       localServletOutputStream2.println(" m_col1.style.background='darkblue';");
/* 119 */       localServletOutputStream2.println(" m_col1.style.color='white';");
/* 120 */       localServletOutputStream2.println("}");
/*    */ 
/* 122 */       localServletOutputStream2.println("function MyDialog(){");
/* 123 */       localServletOutputStream2.println(" this.valin;");
/* 124 */       localServletOutputStream2.println(" this.valout = new Array(50);");
/* 125 */       localServletOutputStream2.println("}");
/*    */ 
/* 127 */       localServletOutputStream2.println("function onmouseout_action(m_col1){");
/* 128 */       localServletOutputStream2.println(" m_col1.style.color='black';");
/* 129 */       localServletOutputStream2.println(" m_col1.style.background='white';");
/* 130 */       localServletOutputStream2.println("}");
/*    */ 
/* 132 */       localServletOutputStream2.println("function Selected(ref,val,veri,Start,End){");
/* 133 */       localServletOutputStream2.println("if(veri=='1'){");
/* 134 */       localServletOutputStream2.println("window.dialogArguments.valout [1] = 'Next';");
/* 135 */       localServletOutputStream2.println("window.dialogArguments.valout [2] =  Start  ;");
/* 136 */       localServletOutputStream2.println("window.dialogArguments.valout [3] =  End;");
/* 137 */       localServletOutputStream2.println(" window.close();");
/* 138 */       localServletOutputStream2.println("}else{");
/* 139 */       localServletOutputStream2.println("if(veri=='2'){");
/* 140 */       localServletOutputStream2.println("window.dialogArguments.valout [1] = 'Prev';");
/* 141 */       localServletOutputStream2.println("window.dialogArguments.valout [2] =  Start  ;");
/* 142 */       localServletOutputStream2.println("window.dialogArguments.valout [3] =  End;");
/* 143 */       localServletOutputStream2.println(" window.close();");
/* 144 */       localServletOutputStream2.println("}else{");
/* 145 */       localServletOutputStream2.println("for(i=1;i<=" + j + ";i++){");
/* 146 */       //localServletOutputStream2.println("window.dialogArguments.valout [i] =ref[val][i];}");
                localServletOutputStream2.println("window.dialogArguments.valout [i] = replace_and_sign(ref[val][i]);}");//ADDED MILINDA FOR REPLACE SPECIAL CHARACTOR
/*    */ 
/* 148 */       localServletOutputStream2.println("  }}");
/* 149 */       localServletOutputStream2.println("}");
/*    */  
/* 151 */       localServletOutputStream2.println("function Next() {");
/* 152 */       int k = Integer.parseInt(str9) + 50;
/* 153 */       int m = Integer.parseInt(str9) + 1;
/* 154 */       localServletOutputStream2.println("oBj = new MyDialog()");
/* 155 */       localServletOutputStream2.println(" Selected(oBj.valout,'1','1','" + m + "','" + k + "');");
/* 156 */       localServletOutputStream2.println("}");
/*    */ 
/* 158 */       localServletOutputStream2.println("function Prev() {");
/* 159 */       int n = Integer.parseInt(str8) - 50;
/* 160 */       int i1 = Integer.parseInt(str8) - 1;
/* 161 */       localServletOutputStream2.println("oBj= new MyDialog()");
/* 162 */       localServletOutputStream2.println(" Selected(oBj.valout,'1','2','" + n + "','" + i1 + "');");
/* 163 */       localServletOutputStream2.println("}");
/*    */ 
/* 165 */       localServletOutputStream2.println(" function Close(){");
/* 166 */       localServletOutputStream2.println("  window.dialogArguments.valout [1] = 'Close';");
/* 167 */       localServletOutputStream2.println("  window.close();");
/* 168 */       localServletOutputStream2.println(" }");
/*    */ 
/* 170 */       localServletOutputStream2.println("</SCRIPT>");
/* 171 */       localServletOutputStream2.println("</HEAD>");
/* 172 */       localServletOutputStream2.println("<BODY class='body & txt-body' topmargin=0 leftmargin=0 >");
/* 173 */       localServletOutputStream2.println("<FORM NAME=\"form1\">");
/*    */ 
/* 176 */       if (bool) {
/* 177 */         localServletOutputStream2.println("<TABLE WIDTH='100%' class='pdn_table' border='1'>");
/* 178 */         localServletOutputStream2.println("<TR class='txt_input_help_head' align='center'>");
/* 179 */         int i2 = Integer.parseInt(str10);
/* 180 */         for (int i3 = 1; i3 <= j - i2; i3++) {
/* 181 */           String str21 = rms.getColumnLabel(i3);
/* 182 */           localServletOutputStream2.println("<TD class='txt_input_help_head'>" + str21 + "</TD>");
/*    */         }
/*    */ 
/* 185 */         localServletOutputStream2.println("</TR>");
/* 186 */         int i4 = 0;
/* 187 */         localServletOutputStream2.println("<SCRIPT LANGUAGE = 'JavaScript'>");
/* 188 */         localServletOutputStream2.println("ColCount = " + j + ";");
/* 189 */         localServletOutputStream2.println(" NewArr = new Array(150);");
/* 190 */         localServletOutputStream2.println(" for(i=1;i<=150;i++){");
/* 191 */         localServletOutputStream2.println(" NewArr[i] = new Array(ColCount);}");
/* 192 */         localServletOutputStream2.println("</SCRIPT>");
/*    */         int i5;
/*    */         int i6;
/* 193 */         while (bool) {
/* 194 */           if (rs.getString(1).equals("1")) {
/* 195 */             i4 = 1;
/*    */           }
/* 197 */           if (i < 51) {
/* 198 */             localServletOutputStream2.println("<TR ID=M_LOC" + i + " class='txt_input_help'  onmouseover=\"onmouseover_action(M_LOC" + i + ")\" onmouseout=\"onmouseout_action(M_LOC" + i + ");\" >");
/* 199 */             for (i5 = 1; i5 <= j; i5++) {
/* 200 */               localServletOutputStream2.println("<SCRIPT LANGUAGE = 'JavaScript'>");
/* 201 */               localServletOutputStream2.println(" NewArr[" + i + "][" + i5 + "]=\"" + rs.getString(i5) + "\";");
/* 202 */               localServletOutputStream2.println("</SCRIPT>");
/*    */             }
/* 204 */             for (i6 = 1; i6 <= j - i2; i6++) {
/* 205 */               if ((i6 == 2) && (str13.equals("EMP"))) {
/* 206 */                 localServletOutputStream2.println("<TD onclick=\"show_emp_details('" + rs.getString(i6) + "')\" style='cursor:hand' class='txt_input_help'><u>" + rs.getString(i6) + "</u></TD>");
/*    */               }
/* 208 */               else if ((i6 != 2) && (str13.equals("EMP"))) {
/* 209 */                 localServletOutputStream2.println("<TD onclick=\"Selected(NewArr,'" + i + "','0')\" class='txt_input_help'>" + rs.getString(i6) + "</TD>");
/*    */               }
/* 211 */               if (str13.equals("")) {
/* 212 */                 localServletOutputStream2.println("<TD onclick=\"Selected(NewArr,'" + i + "','0')\" class='txt_input_help'>" + rs.getString(i6) + "</TD>");
/*    */               }
/*    */             }
/*    */ 
/* 216 */             localServletOutputStream2.println("</TR>");
/*    */           }
/* 218 */           i += 1;
/* 219 */           bool = rs.next();
/*    */         }
/* 221 */         rs.close();
/* 222 */         if (i <= Integer.parseInt(str9)) {
/* 223 */           localServletOutputStream2.println("<TABLE width='100%' bgcolor='white'>");
/* 224 */           for (i5 = i; i5 <= 50; i5++) {
/* 225 */             localServletOutputStream2.println("<TR STYLE='{font: 7pt Verdana;}'>");
/* 226 */             for (i6 = 1; i6 <= j; i6++) {
/* 227 */               localServletOutputStream2.println("<TD> </TD>");
/*    */             }
/* 229 */             localServletOutputStream2.println("</TR>");
/*    */           }
/*    */         }
/* 232 */         localServletOutputStream2.println("</TABLE>");
/* 233 */         localServletOutputStream2.println("<TABLE width='100%' cellpadding='1'>");
/* 234 */         localServletOutputStream2.println("<TR ><TD width='20%'></TD><TD>");
/* 235 */         if (i4 != 0) {
/* 236 */           localServletOutputStream2.println("<INPUT TYPE ='Button' NAME ='Prev_Val' VALUE ='Previous' class='mainbut' DISABLED ></TD><TD>");
/*    */         }
/*    */         else {
/* 239 */           localServletOutputStream2.println("<INPUT TYPE ='Button' NAME ='Prev_Val' VALUE ='Previous' class='mainbut' OnClick='Prev();'></TD><TD>");
/*    */         }
/*    */ 
/* 243 */         if (i <= 51) {
/* 244 */           localServletOutputStream2.println("<INPUT TYPE ='Button' NAME ='Next_Val' VALUE ='Next' class='mainbut' DISABLED ></TD><TD>");
/*    */         }
/*    */         else {
/* 247 */           localServletOutputStream2.println("<INPUT TYPE ='Button' NAME ='Next_Val' VALUE ='Next' class='mainbut'  OnClick='Next();' ></TD><TD>");
/*    */         }
/*    */ 
/* 250 */         localServletOutputStream2.println("<INPUT TYPE ='Button' NAME ='Exit'  VALUE ='   Exit   ' class='mainbut' OnClick='Close();'></TD><TD align='right'></TD><TD width='20%'></TD></TR>");
/* 251 */         localServletOutputStream2.println("</TABLE>");
/*    */       }
/*    */       else {
/* 254 */         localServletOutputStream2.println("<br><br><br><br><br><p align='center'><font face='Verdana' color=darkblue size=4 boldness=700 >No Records Available</font></p>");
/*    */       }
/*    */ localServletOutputStream2.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_home_url+"/ajax_data_gateway.js'></SCRIPT>");//[ADDED BY MILIND FOR JB07102021-15043]
/* 257 */       localServletOutputStream2.println("</BODY>");
/* 258 */       localServletOutputStream2.println("</HTML>");
/*    */       try
/*    */       {
/* 261 */         conn.close();
/*    */       }
/*    */       catch (Exception localException3) {
/* 264 */         localServletOutputStream2.println("Exception at help_exe " + localException3.toString());
/*    */       }
/* 266 */       localServletOutputStream2.close();
/* 267 */       destroy();
/*    */     }
/*    */     catch (Exception localException1) {
/*    */       try {
/* 271 */         conn.close();
/*    */       } catch (Exception localException2) {
/*    */       }
/* 274 */       ServletOutputStream localServletOutputStream1 = paramHttpServletResponse.getOutputStream();
/* 275 */       localServletOutputStream1.close();
/*    */     }
/*    */   }
/*    */ }

/* Location:           E:\DESKTOP FILES\Transfer\LAKDL\2017\June\03-06-2017\From Live\
 * Qualified Name:     LAKDL_AF_MAS_View_Help_Servlet
 * JD-Core Version:    0.6.2
 */