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
public class LAKDL_AF_PRO_Help_Servlet extends HttpServlet
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
			/* 25 */       String str1 = localLAKDL_AF_CO_conn_methods.schema_name.trim();
			/* 26 */       String str2 = localLAKDL_AF_CO_conn_methods.username;
			/* 27 */       String str3 = localLAKDL_AF_CO_conn_methods.html_client_url.trim();
			/* 28 */       String str4 = localLAKDL_AF_CO_conn_methods.bg_color1;
			/* 29 */       String str5 = localLAKDL_AF_CO_conn_methods.bg_color2;
			/* 30 */       String str6 = localLAKDL_AF_CO_conn_methods.fo_color1;
			/* 31 */       String str7 = localLAKDL_AF_CO_conn_methods.fo_color2;
			               String m_html_client_home_url = localLAKDL_AF_CO_conn_methods.servlet_client_url;
			/*    */ 
			/* 33 */       paramHttpServletResponse.setStatus(200);
			/* 34 */       paramHttpServletResponse.setContentType("text/html");
			/* 35 */       paramHttpServletResponse.setHeader("Cache-Control", "No-Cache");
			/* 36 */       paramHttpServletResponse.setDateHeader("Expires", 0L); 
			/*    */ 
			/* 38 */       String str8 = paramHttpServletRequest.getParameter("class_in");
			/* 39 */       String str9 = paramHttpServletRequest.getParameter("Sql_in");
			/* 40 */       String str10 = paramHttpServletRequest.getParameter("Start_in");
			/* 41 */       String str11 = paramHttpServletRequest.getParameter("End_in");
			/* 42 */       String str12 = paramHttpServletRequest.getParameter("Hid_No");
			/* 43 */       String str13 = str9 + "_Header";
			/* 44 */       String str14 = paramHttpServletRequest.getParameter("Crit_In").toUpperCase();
			/*    */ 
			/* 46 */       Class localClass = Class.forName(str8);
			/* 47 */       Object localObject1 = localClass.newInstance();
			/* 48 */       Method[] arrayOfMethod = localClass.getDeclaredMethods();
			/* 49 */       ServletOutputStream localServletOutputStream2 = paramHttpServletResponse.getOutputStream();
			/*    */ 
			/* 51 */       String str15 = str9;
			/* 52 */       String str16 = str10;
			/* 53 */       String str17 = str11;
			/* 54 */       String str18 = str14;
			/* 55 */       String str19 = str2;
			/*    */ 
			/* 58 */       Object[] arrayOfObject = new Object[5];
			/*    */ 
			/* 60 */       arrayOfObject[0] = str15;
			/* 61 */       arrayOfObject[1] = str16;
			/* 62 */       arrayOfObject[2] = str17;
			/* 63 */       arrayOfObject[3] = str18;
			/* 64 */       arrayOfObject[4] = str19;
			/*    */ 
			/* 66 */       Object localObject2 = arrayOfMethod[0].invoke(localObject1, arrayOfObject);
			/*    */ 
			/* 68 */       Field localField1 = localClass.getField(str9);
			/* 69 */       Object localObject3 = new Object();
			/* 70 */       Field localField2 = localClass.getField(str13);
			/* 71 */       Object localObject4 = new Object();
			/*    */       try
				/*    */       {
				/* 74 */         localObject3 = localField1.get(localObject1);
				/* 75 */         localObject4 = localField2.get(localObject1);
				/*    */       }
			/*    */       catch (IllegalAccessException localIllegalAccessException) {
				/* 78 */         localServletOutputStream2.println("Invalid");
				/*    */       }
			/*    */ 
			/* 81 */       String str20 = localObject3.toString();
			/* 82 */       String str21 = localObject4.toString();
			/* 83 */       String[] arrayOfString = new String[10];
			/* 84 */       int i = 1;
			/* 85 */       stmt = conn.createStatement();
			/*    */       try
				/*    */       {
				/* 90 */         rs = stmt.executeQuery(str20);
				/*    */       }
			/*    */       catch (SQLException localSQLException) {
				/* 93 */         localServletOutputStream2.println(localSQLException.toString());
				/*    */       }
			/*    */ 
			/* 96 */       rms = rs.getMetaData();
			/* 97 */       boolean bool = rs.next();
			/* 98 */       int j = rms.getColumnCount();
			/*    */ 
			/* 100 */       localServletOutputStream2.println("<HTML><HEAD>");
			/* 101 */       localServletOutputStream2.println("<TITLE> " + str21 + "</TITLE>");
			/* 102 */       localServletOutputStream2.println("<link REL='STYLESHEET' HREF='" + str3 + "/css/Asset_Financing_System.css' TYPE=\"text/css\">");
			/*    */ 
			/* 104 */       localServletOutputStream2.println("<SCRIPT>");
			/*    */ 
			/* 121 */       localServletOutputStream2.println("function onmouseover_action(m_col1){");
			/* 122 */       localServletOutputStream2.println(" m_col1.style.background='darkblue';");
			/* 123 */       localServletOutputStream2.println(" m_col1.style.color='white';");
			/* 124 */       localServletOutputStream2.println("}");
			/*    */ 
			/* 126 */       localServletOutputStream2.println("function MyDialog(){");
			/* 127 */       localServletOutputStream2.println(" this.valin;");
			/* 128 */       localServletOutputStream2.println(" this.valout = new Array(10);");
			/* 129 */       localServletOutputStream2.println("}");
			/*    */ 
			/* 131 */       localServletOutputStream2.println("function onmouseout_action(m_col1){");
			/* 132 */       localServletOutputStream2.println(" m_col1.style.color='black';");
			/* 133 */       localServletOutputStream2.println(" m_col1.style.background='white';");
			/* 134 */       localServletOutputStream2.println("}");
			/*    */ 
			/* 136 */       localServletOutputStream2.println("function Selected(ref,val,veri,Start,End){");
			/* 137 */       localServletOutputStream2.println("if(veri=='1'){");
			/* 138 */       localServletOutputStream2.println("window.dialogArguments.valout [1] = 'Next';");
			/* 139 */       localServletOutputStream2.println("window.dialogArguments.valout [2] =  Start  ;");
			/* 140 */       localServletOutputStream2.println("window.dialogArguments.valout [3] =  End;");
			/* 141 */       localServletOutputStream2.println(" window.close();");
			/* 142 */       localServletOutputStream2.println("}else{");
			/* 143 */       localServletOutputStream2.println("if(veri=='2'){");
			/* 144 */       localServletOutputStream2.println("window.dialogArguments.valout [1] = 'Prev';");
			/* 145 */       localServletOutputStream2.println("window.dialogArguments.valout [2] =  Start  ;");
			/* 146 */       localServletOutputStream2.println("window.dialogArguments.valout [3] =  End;");
			/* 147 */       localServletOutputStream2.println(" window.close();");
			/* 148 */       localServletOutputStream2.println("}else{");
			/* 149 */       localServletOutputStream2.println("for(i=1;i<=" + j + ";i++){");
			/* 150 */       //localServletOutputStream2.println("window.dialogArguments.valout [i] =ref[val][i];}");
			                localServletOutputStream2.println("window.dialogArguments.valout [i] = replace_and_sign(ref[val][i]);}");//ADDED MILINDA FOR REPLACE SPECIAL CHARACTOR
			/* 151 */       localServletOutputStream2.println(" window.close();");
			/* 152 */       localServletOutputStream2.println("  }}");
			/* 153 */       localServletOutputStream2.println("}");
			/*    */ 
			/* 155 */       localServletOutputStream2.println("function Next() {");
			/* 156 */       int k = Integer.parseInt(str11) + 10;
			/* 157 */       int m = Integer.parseInt(str11) + 1;
			/* 158 */       localServletOutputStream2.println("oBj = new MyDialog()");
			/* 159 */       localServletOutputStream2.println(" Selected(oBj.valout,'1','1','" + m + "','" + k + "');");
			/* 160 */       localServletOutputStream2.println("}");
			/*    */ 
			/* 162 */       localServletOutputStream2.println("function Prev() {"); 
			/* 163 */       int n = Integer.parseInt(str10) - 10;
			/* 164 */       int i1 = Integer.parseInt(str10) - 1;
			/* 165 */       localServletOutputStream2.println("oBj= new MyDialog()");
			/* 166 */       localServletOutputStream2.println(" Selected(oBj.valout,'1','2','" + n + "','" + i1 + "');");
			/* 167 */       localServletOutputStream2.println("}");
			/*    */ 
			/* 169 */       localServletOutputStream2.println(" function Close(){");
			/* 170 */       localServletOutputStream2.println("  window.dialogArguments.valout [1] = 'Close';");
			/* 171 */       localServletOutputStream2.println("  window.close();");
			/* 172 */       localServletOutputStream2.println(" }");
			/*    */ 
			/* 174 */       localServletOutputStream2.println("</SCRIPT>");
			/* 175 */       localServletOutputStream2.println("</HEAD>");
			/* 176 */       localServletOutputStream2.println("<BODY class='body & txt-body' topmargin=0 leftmargin=0 >");
			/* 177 */       localServletOutputStream2.println("<FORM NAME=\"form1\">");
			/*    */ 
			/* 180 */       if (bool) {
				/* 181 */         localServletOutputStream2.println("<TABLE WIDTH='100%' class='pdn_table2' border='1'>");
				/* 182 */         localServletOutputStream2.println("<TR class='txt_input_help_head' align='center'>");
				/* 183 */         int i2 = Integer.parseInt(str12);
				/* 184 */         for (int i3 = 1; i3 <= j - i2; i3++) {
					/* 185 */           String str22 = rms.getColumnLabel(i3);
					/* 186 */           localServletOutputStream2.println("<TD class='txt_input_help_head'>" + str22 + "</TD>");
					/*    */         }
				/* 188 */         localServletOutputStream2.println("</TR>");
				/* 189 */         int i4 = 0;
				/* 190 */         localServletOutputStream2.println("<SCRIPT LANGUAGE = 'JavaScript'>");
				/* 191 */         localServletOutputStream2.println("ColCount = " + j + ";");
				/* 192 */         localServletOutputStream2.println(" NewArr = new Array(30);");
				/* 193 */         localServletOutputStream2.println(" for(i=1;i<=30;i++){");
				/* 194 */         localServletOutputStream2.println(" NewArr[i] = new Array(ColCount);}");
				/* 195 */         localServletOutputStream2.println("</SCRIPT>");
				/*    */         int i5;
				/*    */         int i6;
				/* 196 */         while (bool) {
					/* 197 */           if (rs.getString(1).equals("1")) {
						/* 198 */             i4 = 1;
						/*    */           }
					/* 200 */           if (i < 11) {
						/* 201 */             localServletOutputStream2.println("<TR ID=M_LOC" + i + " class='txt_input_help'  onmouseover=\"onmouseover_action(M_LOC" + i + ")\" onmouseout=\"onmouseout_action(M_LOC" + i + ");\" >");
						/* 202 */             for (i5 = 1; i5 <= j; i5++) {
							/* 203 */               localServletOutputStream2.println("<SCRIPT LANGUAGE = 'JavaScript'>");
							/* 204 */               localServletOutputStream2.println(" NewArr[" + i + "][" + i5 + "]=\"" + rs.getString(i5) + "\";");
							/* 205 */               localServletOutputStream2.println("</SCRIPT>");
							/*    */             }
						/* 207 */             for (i6 = 1; i6 <= j - i2; i6++) {
							/* 208 */               localServletOutputStream2.println("<TD onclick=\" Selected(NewArr,'" + i + "','0')\" class='txt_input_help'>" + rs.getString(i6) + "</TD>");
							/*    */             }
						/* 210 */             localServletOutputStream2.println("</TR>");
						/*    */           }
					/* 212 */           i += 1;
					/* 213 */           bool = rs.next();
					/*    */         }
				/* 215 */         rs.close();
				/* 216 */         if (i <= 10) {
					/* 217 */           localServletOutputStream2.println("<TABLE width='100%'  class=pdn_table2>");
					/* 218 */           for (i5 = i; i5 <= 10; i5++) {
						/* 219 */             localServletOutputStream2.println("<TR STYLE='{font: 7pt Verdana;}'>");
						/* 220 */             for (i6 = 1; i6 <= j; i6++) {
							/* 221 */               localServletOutputStream2.println("<TD>-</TD>");
							/*    */             }
						/* 223 */             localServletOutputStream2.println("</TR>");
						/*    */           }
					/*    */         }
				/* 226 */         localServletOutputStream2.println("</TABLE>");
				/* 227 */         localServletOutputStream2.println("<TABLE width='100%' cellpadding='1'>");
				/* 228 */         localServletOutputStream2.println("<TR ><TD width='20%'></TD><TD>");
				/* 229 */         if (i4 != 0) {
					/* 230 */           localServletOutputStream2.println("<INPUT TYPE ='Button' NAME ='Prev_Val' VALUE ='Prev 10' style='{cursor:hand; color: black; font: bold 8pt arial; border-color: silver; background: silver;}' DISABLED ></TD><TD>");
					/*    */         }
				/*    */         else {
					/* 233 */           localServletOutputStream2.println("<INPUT TYPE ='Button' NAME ='Prev_Val' VALUE ='Prev 10' style='{cursor:hand; color: black; font: bold 8pt arial; border-color: silver; background: silver;}' OnClick='Prev();'></TD><TD>");
					/*    */         }
				/*    */ 
				/* 236 */         if (i <= 11) {
					/* 237 */           localServletOutputStream2.println("<INPUT TYPE ='Button' NAME ='Next_Val' VALUE ='Next 10' style='{cursor:hand; color: black; font: bold 8pt arial; border-color: silver; background: silver;}' DISABLED ></TD><TD>");
					/*    */         }
				/*    */         else {
					/* 240 */           localServletOutputStream2.println("<INPUT TYPE ='Button' NAME ='Next_Val' VALUE ='Next 10' style='{cursor:hand; color: black; font: bold 8pt arial; border-color: silver; background: silver;}'  OnClick='Next();' ></TD><TD>");
					/*    */         }
				/*    */ 
				/* 243 */         localServletOutputStream2.println("<INPUT TYPE ='Button' NAME ='Exit'  VALUE ='   Exit   ' style='{cursor:hand; color: black; font: bold 8pt arial; border-color: silver; background: silver;}' OnClick='Close();'></TD><TD align='right'></TD><TD width='20%'></TD></TR>");
				/* 244 */         localServletOutputStream2.println("</TABLE>");
				/*    */       }
			/*    */       else {
				/* 247 */         localServletOutputStream2.println("<br><br><br><br><br><p align='center'><font face='Verdana' color=darkblue size=4 boldness=700 >No records available</font></p>");
				/*    */       }
			/*    */ localServletOutputStream2.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_home_url+"/ajax_data_gateway.js'></SCRIPT>");//[ADDED BY MILIND FOR JB07102021-15043]
			/* 250 */       localServletOutputStream2.println("</BODY>");
			/* 251 */       localServletOutputStream2.println("</HTML>");
			/*    */       try
				/*    */       {
				/* 254 */         conn.close();
				/*    */       }
			/*    */       catch (Exception localException3) {
				/* 257 */         localServletOutputStream2.println("Exception at help_exe " + localException3.toString());
				/*    */       }
			/* 259 */       localServletOutputStream2.close();
			/* 260 */       destroy();
			/*    */     }
		/*    */     catch (Exception localException1) {
			/*    */       try {
				/* 264 */         conn.close();
				/*    */       } catch (Exception localException2) {
				/*    */       }
			/* 267 */       ServletOutputStream localServletOutputStream1 = paramHttpServletResponse.getOutputStream();
			/* 268 */       localServletOutputStream1.close();
			/*    */     }
		/*    */   }
	/*    */ }

/* Location:           E:\DESKTOP FILES\Transfer\LAKDL\2017\June\03-06-2017\From Live\
 * Qualified Name:     LAKDL_AF_PRO_Help_Servlet
 * JD-Core Version:    0.6.2
 */