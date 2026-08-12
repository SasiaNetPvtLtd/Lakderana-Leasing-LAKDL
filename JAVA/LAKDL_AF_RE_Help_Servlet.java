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
public class LAKDL_AF_RE_Help_Servlet extends HttpServlet
{
	
	/*
	Connection conn;
	Statement stmt;
	Class t;
	public ResultSet rs;
	public ResultSetMetaData rms;
	public String m_chksql;
	public Object Ret_Obj;
	*/
	
	//public synchronized void service(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse)
	public  void service(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse)
		throws IOException
	{
		
		Connection conn=null;
		Statement stmt=null;
		Class t=null;
		ResultSet rs=null;
		ResultSetMetaData rms=null;
		String m_chksql=null;
		Object Ret_Obj=null;
		
		
		try
		{
			/* 29 */       ServletOutputStream localServletOutputStream1 = paramHttpServletResponse.getOutputStream();
			/*    */ 
			/* 33 */       LAKDL_AF_CO_conn_methods localLAKDL_AF_CO_conn_methods = new LAKDL_AF_CO_conn_methods();
			/* 34 */       conn = localLAKDL_AF_CO_conn_methods.met_user_validate(paramHttpServletRequest);
			/* 35 */       String str1 = localLAKDL_AF_CO_conn_methods.bg_color1;
			/* 36 */       String str2 = localLAKDL_AF_CO_conn_methods.bg_color2;
			/* 37 */       String str3 = localLAKDL_AF_CO_conn_methods.fo_color1; 
			/* 38 */       String str4 = localLAKDL_AF_CO_conn_methods.fo_color2;
			               String m_html_client_home_url = localLAKDL_AF_CO_conn_methods.servlet_client_url;
			/*    */ 
			/* 43 */       String str5 = localLAKDL_AF_CO_conn_methods.html_client_url;
			/*    */ 
			/* 49 */       paramHttpServletResponse.setStatus(200);
			/* 50 */       paramHttpServletResponse.setContentType("text/html");
			/*    */ 
			/* 52 */       String str6 = paramHttpServletRequest.getParameter("class_in");
			/* 53 */       String str7 = paramHttpServletRequest.getParameter("Sql_in");
			/* 54 */       String str8 = paramHttpServletRequest.getParameter("Start_in");
			/* 55 */       String str9 = paramHttpServletRequest.getParameter("End_in");
			/* 56 */       String str10 = paramHttpServletRequest.getParameter("Hid_No");
			/* 57 */       String str11 = paramHttpServletRequest.getParameter("Max_in");
			/*    */ 
			/* 59 */       String str12 = str7 + "_Header";
			/* 60 */       String str13 = paramHttpServletRequest.getParameter("Crit_In").toUpperCase();
			/*    */ 
			/* 63 */       Class localClass = Class.forName(str6);
			/* 64 */       Object localObject1 = localClass.newInstance();
			/* 65 */       Method[] arrayOfMethod = localClass.getDeclaredMethods();
			/*    */ 
			/* 68 */       String str14 = str7;
			/* 69 */       String str15 = str8;
			/* 70 */       String str16 = str9;
			/* 71 */       String str17 = str13;
			/*    */ 
			/* 74 */       Object[] arrayOfObject = new Object[4];
			/* 75 */       arrayOfObject[0] = str14;
			/* 76 */       arrayOfObject[1] = str15;
			/* 77 */       arrayOfObject[2] = str16;
			/* 78 */       arrayOfObject[3] = str17;
			/*    */ 
			/* 81 */       Object localObject2 = arrayOfMethod[0].invoke(localObject1, arrayOfObject);
			/*    */ 
			/* 83 */       Field localField1 = localClass.getField(str7);
			/*    */ 
			/* 85 */       Object localObject3 = new Object();
			/* 86 */       Field localField2 = localClass.getField(str12);
			/*    */ 
			/* 88 */       Object localObject4 = new Object();
			/*    */       try
				/*    */       {
				/* 92 */         localObject3 = localField1.get(localObject1);
				/* 93 */         localObject4 = localField2.get(localObject1);
				/*    */       }
			/*    */       catch (IllegalAccessException localIllegalAccessException)
				/*    */       {
				/* 97 */         localServletOutputStream1.println("Invalid");
				/*    */       }
			/*    */ 
			/* 104 */       String str18 = localObject3.toString();
			/*    */ 
			/* 106 */       String str19 = localObject4.toString();
			/*    */ 
			/* 108 */       String[] arrayOfString = new String[10];
			/* 109 */       int i = 1;
			/*    */ 
			/* 112 */       stmt = conn.createStatement();
			/*    */       try
				/*    */       {
				/* 116 */         rs = stmt.executeQuery(str18);
				/*    */       }
			/*    */       catch (SQLException localSQLException) {
				/* 119 */         localServletOutputStream1.println(localSQLException.toString());
				/*    */       }
			/* 121 */       rms = rs.getMetaData();
			/* 122 */       boolean bool = rs.next();
			/*    */ 
			/* 125 */       int j = rms.getColumnCount();
			/*    */       try
				/*    */       {
				/* 129 */         localServletOutputStream1.println("<HTML><HEAD>");
				/* 130 */         localServletOutputStream1.println("<TITLE>Net Asset</TITLE>");
				/* 131 */         localServletOutputStream1.println("<link href=\"" + str5 + "/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
				/* 132 */         localServletOutputStream1.println("<SCRIPT language = \"javascript\">");
				/*    */ 
				/* 134 */         localServletOutputStream1.println("function onmouseover_action(m_col1){");
				/* 135 */         localServletOutputStream1.println(" m_col1.style.background='" + str2 + "';");
				/* 136 */         localServletOutputStream1.println(" m_col1.style.color='" + str4 + "';");
				/* 137 */         localServletOutputStream1.println("}");
				/*    */ 
				/* 139 */         localServletOutputStream1.println("function MyDialog(){");
				/*    */ 
				/* 141 */         localServletOutputStream1.println(" this.valin;");
				/* 142 */         localServletOutputStream1.println(" this.valout = new Array(10);");
				/* 143 */         localServletOutputStream1.println("}");
				/*    */ 
				/* 145 */         localServletOutputStream1.println("function onmouseout_action(m_col1){");
				/* 146 */         localServletOutputStream1.println(" m_col1.style.background='" + str1 + "';");
				/* 147 */         localServletOutputStream1.println(" m_col1.style.color='" + str3 + "';");
				/* 148 */         localServletOutputStream1.println("}");
				/*    */ 
				/* 150 */         localServletOutputStream1.println("function Selected(ref,val,veri,Start,End){");
				/* 151 */         localServletOutputStream1.println("if(veri=='1'){");
				/* 152 */         localServletOutputStream1.println("window.dialogArguments.valout [1] = 'Next';");
				/* 153 */         localServletOutputStream1.println("window.dialogArguments.valout [2] =  Start  ;");
				/* 154 */         localServletOutputStream1.println("window.dialogArguments.valout [3] =  End;");
				/*    */ 
				/* 156 */         localServletOutputStream1.println(" window.close();");
				/* 157 */         localServletOutputStream1.println("}else{");
				/* 158 */         localServletOutputStream1.println("if(veri=='2'){");
				/* 159 */         localServletOutputStream1.println("window.dialogArguments.valout [1] = 'Prev';");
				/* 160 */         localServletOutputStream1.println("window.dialogArguments.valout [2] =  Start  ;");
				/* 161 */         localServletOutputStream1.println("window.dialogArguments.valout [3] =  End;");
				/* 162 */         localServletOutputStream1.println(" window.close();");
				/* 163 */         localServletOutputStream1.println("}else{");
				/* 164 */         localServletOutputStream1.println("for(i=1;i<=" + j + ";i++){");
				/* 165 */         //localServletOutputStream1.println("window.dialogArguments.valout [i] =ref[val][i];}");
				                  localServletOutputStream1.println("window.dialogArguments.valout [i] = replace_and_sign(ref[val][i]);}");//ADDED MILINDA FOR REPLACE SPECIAL CHARACTOR
				/* 166 */         localServletOutputStream1.println(" window.close();");
				/* 167 */         localServletOutputStream1.println("  }}");
				/* 168 */         localServletOutputStream1.println("}"); 
				/*    */ 
				/* 170 */         localServletOutputStream1.println("function Next() {");
				/* 171 */         int k = Integer.parseInt(str9) + 10;
				/* 172 */         int m = Integer.parseInt(str9) + 1;
				/* 173 */         localServletOutputStream1.println("oBj = new MyDialog()");
				/* 174 */         localServletOutputStream1.println(" Selected(oBj.valout,'1','1','" + m + "','" + k + "');");
				/* 175 */         localServletOutputStream1.println("}");
				/*    */ 
				/* 177 */         localServletOutputStream1.println("function Last() {");
				/* 178 */         int n = Integer.parseInt(str11);
				/* 179 */         int i1 = Integer.parseInt(str11) - 10;
				/* 180 */         localServletOutputStream1.println("oBj = new MyDialog()");
				/* 181 */         localServletOutputStream1.println(" Selected(oBj.valout,'1','1','" + i1 + "','" + n + "');");
				/* 182 */         localServletOutputStream1.println("}");
				/*    */ 
				/* 184 */         localServletOutputStream1.println("function First() {");
				/* 185 */         localServletOutputStream1.println("oBj = new MyDialog()");
				/* 186 */         localServletOutputStream1.println(" Selected(oBj.valout,'1','1','1','10');");
				/* 187 */         localServletOutputStream1.println("}");
				/*    */ 
				/* 189 */         localServletOutputStream1.println("function Prev() {");
				/* 190 */         int i2 = Integer.parseInt(str8) - 10;
				/* 191 */         int i3 = Integer.parseInt(str8) - 1;
				/* 192 */         localServletOutputStream1.println("oBj= new MyDialog()");
				/* 193 */         localServletOutputStream1.println(" Selected(oBj.valout,'1','2','" + i2 + "','" + i3 + "');");
				/* 194 */         localServletOutputStream1.println("}");
				/*    */ 
				/* 196 */         localServletOutputStream1.println(" function Close(){");
				/* 197 */         localServletOutputStream1.println("  window.dialogArguments.valout [1] = 'Close';");
				/* 198 */         localServletOutputStream1.println("  window.close();");
				/* 199 */         localServletOutputStream1.println(" }");
				/*    */ 
				/* 201 */         localServletOutputStream1.println("</SCRIPT>");
				/* 202 */         localServletOutputStream1.println("</HEAD>");
				/* 203 */         localServletOutputStream1.println("<BODY BGCOLOR='white' topmargin=0 leftmargin=0 onload=MyDialog();>");
				/* 204 */         localServletOutputStream1.println("<FORM NAME=\"form1\">");
				/*    */ 
				/* 207 */         if (bool)
					/*    */         {
					/* 211 */           localServletOutputStream1.println("<TABLE WIDTH='100%' class=pdn_table2>");
					/* 212 */           localServletOutputStream1.println("<TR class=txt_input_help_head>");
					/* 213 */           int i4 = Integer.parseInt(str10);
					/* 214 */           for (int i5 = 1; i5 <= j - i4; i5++) {
						/* 215 */             String str20 = rms.getColumnLabel(i5);
						/* 216 */             localServletOutputStream1.println("<TD >" + str20 + "</TD>");
						/*    */           }
					/* 218 */           localServletOutputStream1.println("</TR>");
					/* 219 */           int i6 = 0;
					/* 220 */           int i7 = 0;
					/* 221 */           localServletOutputStream1.println("<SCRIPT LANGUAGE = 'JavaScript'>");
					/* 222 */           localServletOutputStream1.println("ColCount = " + j + ";");
					/* 223 */           localServletOutputStream1.println(" NewArr = new Array(60);");
					/* 224 */           localServletOutputStream1.println(" for(i=1;i<=60;i++){");
					/* 225 */           localServletOutputStream1.println(" NewArr[i] = new Array(ColCount);}");
					/* 226 */           localServletOutputStream1.println("</SCRIPT>");
					/*    */           int i8;
					/*    */           int i9;
					/* 227 */           while (bool)
						/*    */           {
						/* 229 */             if (rs.getString(1).equals("1")) {
							/* 230 */               i6 = 1;
							/*    */             }
						/* 232 */             if (rs.getString(1).equals(str11)) {
							/* 233 */               i7 = 1;
							/*    */             }
						/* 235 */             if (i < 11) {
							/* 236 */               localServletOutputStream1.println("<TR ID=M_LOC" + i + " class=txt_input_help  onmouseover=\"onmouseover_action(M_LOC" + i + ")\" onmouseout=\"onmouseout_action(M_LOC" + i + ");\" >");
							/* 237 */               for (i8 = 1; i8 <= j; i8++) {
								/* 238 */                 localServletOutputStream1.println("<SCRIPT LANGUAGE = 'JavaScript'>");
								/* 239 */                 localServletOutputStream1.println(" NewArr[" + i + "][" + i8 + "]=\"" + rs.getString(i8) + "\";");
								/* 240 */                 localServletOutputStream1.println("</SCRIPT>");
								/*    */               }
							/* 242 */               for (i9 = 1; i9 <= j - i4; i9++)
								/*    */               {
								/* 247 */                 localServletOutputStream1.println("<TD onclick=\" Selected(NewArr,'" + i + "','0')\" >" + rs.getString(i9) + "</TD>");
								/*    */               }
							/*    */ 
							/* 250 */               localServletOutputStream1.println("</TR>");
							/*    */             }
						/* 252 */             i += 1;
						/* 253 */             bool = rs.next();
						/*    */           }
					/* 255 */           rs.close();
					/* 256 */           if (i <= 10) {
						/* 257 */             localServletOutputStream1.println("<TABLE width='100%' class=pdn_table2>");
						/* 258 */             for (i8 = i; i8 <= 10; i8++) {
							/* 259 */               localServletOutputStream1.println("<TR>");
							/* 260 */               for (i9 = 1; i9 <= j; i9++) {
								/* 261 */                 localServletOutputStream1.println("<TD>-</TD>");
								/*    */               }
							/* 263 */               localServletOutputStream1.println("</TR>");
							/*    */             }
						/*    */           }
					/* 266 */           localServletOutputStream1.println("</TABLE>");
					/* 267 */           localServletOutputStream1.println("<TABLE width='100%' cellpadding='2'>");
					/* 268 */           localServletOutputStream1.println("<TR ><TD width='50%'></TD><TD>");
					/*    */ 
					/* 271 */           if (i6 != 0) {
						/* 272 */             localServletOutputStream1.println("<INPUT TYPE ='Button' NAME ='First_Val' VALUE ='First 10' style='{cursor:hand; color: black; font: bold 8pt arial; border-color: silver; background: silver;}' DISABLED ></TD><TD>");
						/* 273 */             localServletOutputStream1.println("<INPUT TYPE ='Button' NAME ='Prev_Val' VALUE ='Prev 10' style='{cursor:hand; color: black; font: bold 8pt arial; border-color: silver; background: silver;}' DISABLED ></TD><TD>");
						/*    */           }
					/* 275 */           else if (Integer.parseInt(str11) <= 10) {
						/* 276 */             localServletOutputStream1.println("<INPUT TYPE ='Button' NAME ='First_Val' VALUE ='First 10' style='{cursor:hand; color: black; font: bold 8pt arial; border-color: silver; background: silver;}' DISABLED ></TD><TD>");
						/* 277 */             localServletOutputStream1.println("<INPUT TYPE ='Button' NAME ='Prev_Val' VALUE ='Prev 10' style='{cursor:hand; color: black; font: bold 8pt arial; border-color: silver; background: silver;}' OnClick='Prev();'></TD><TD>");
						/*    */           }
					/*    */           else {
						/* 280 */             localServletOutputStream1.println("<INPUT TYPE ='Button' NAME ='First_Val' VALUE ='First 10' style='{cursor:hand; color: black; font: bold 8pt arial; border-color: silver; background: silver;}' OnClick='First();'></TD><TD>");
						/* 281 */             localServletOutputStream1.println("<INPUT TYPE ='Button' NAME ='Prev_Val' VALUE ='Prev 10' style='{cursor:hand; color: black; font: bold 8pt arial; border-color: silver; background: silver;}' OnClick='Prev();'></TD><TD>");
						/*    */           }
					/*    */ 
					/* 285 */           if (i <= 11) {
						/* 286 */             localServletOutputStream1.println("<INPUT TYPE ='Button' NAME ='Next_Val' VALUE ='Next 10' style='{ color: black; font: bold 8pt arial; border-color: silver; background: silver;}' DISABLED ></TD><TD>");
						/* 287 */             localServletOutputStream1.println("<INPUT TYPE ='Button' NAME ='Last_Val' VALUE ='Last 10' style='{ color: black; font: bold 8pt arial; border-color: silver; background: silver;}' DISABLED ></TD><TD>");
						/*    */           }
					/* 289 */           else if (i7 != 0) {
						/* 290 */             localServletOutputStream1.println("<INPUT TYPE ='Button' NAME ='Next_Val' VALUE ='Next 10' style='{ color: black; font: bold 8pt arial; border-color: silver; background: silver;}' DISABLED ></TD><TD>");
						/* 291 */             localServletOutputStream1.println("<INPUT TYPE ='Button' NAME ='Last_Val' VALUE ='Last 10' style='{ color: black; font: bold 8pt arial; border-color: silver; background: silver;}' DISABLED ></TD><TD>");
						/*    */           }
					/*    */           else {
						/* 294 */             localServletOutputStream1.println("<INPUT TYPE ='Button' NAME ='Next_Val' VALUE ='Next 10' style='{cursor:hand; color: black; font: bold 8pt arial; border-color: silver; background: silver;}'  OnClick='Next();' ></TD><TD>");
						/* 295 */             localServletOutputStream1.println("<INPUT TYPE ='Button' NAME ='Last_Val' VALUE ='Last 10' style='{cursor:hand; color: black; font: bold 8pt arial; border-color: silver; background: silver;}'  OnClick='Last();' ></TD><TD>");
						/*    */           }
					/*    */ 
					/* 302 */           localServletOutputStream1.println("<INPUT TYPE ='Button' NAME ='Exit'  VALUE ='   Exit   ' style='{cursor:hand; color: black; font: bold 8pt arial; border-color: silver; background: silver;}' OnClick='Close();'></TD><TD width='5%'></TD></TR>");
					/* 303 */           localServletOutputStream1.println("</TABLE>");
					/*    */         }
				/*    */         else
					/*    */         {
					/* 308 */           localServletOutputStream1.println("<br><br><br><br><br><p align='center'><font face=' sans-serif, arial' color=white size=4 boldness=700 >No records available</font></p>");
					/*    */         }
				/*    */  localServletOutputStream1.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_home_url+"/ajax_data_gateway.js'></SCRIPT>");//[ADDED BY MILIND FOR JB07102021-15043]
				/* 313 */         localServletOutputStream1.println("</BODY>");
				/* 314 */         localServletOutputStream1.println("</HTML>");
				/*    */       }
			/*    */       catch (Exception localException3)
				/*    */       {
				/* 319 */         localServletOutputStream1.println("Exception  " + localException3.toString());
				/*    */       }
			/*    */       try {
				/* 322 */         conn.close();
				/*    */       }
			/*    */       catch (Exception localException4) {
				/* 325 */         localServletOutputStream1.println("Exception at help_exe " + localException4.toString());
				/*    */       }
			/* 327 */       localServletOutputStream1.close();
			/* 328 */       destroy();
			/*    */     }
		/*    */     catch (Exception localException1)
			/*    */     {
			/*    */       try
				/*    */       {
				/* 334 */         conn.close();
				/*    */       } catch (Exception localException2) {
				/*    */       }
			/* 337 */       ServletOutputStream localServletOutputStream2 = paramHttpServletResponse.getOutputStream();
			/* 338 */       localServletOutputStream2.close();
			/*    */     }
		/*    */   }
	/*    */ }

/* Location:           E:\DESKTOP FILES\Transfer\LAKDL\2017\June\03-06-2017\From Live\
 * Qualified Name:     LAKDL_AF_RE_Help_Servlet
 * JD-Core Version:    0.6.2
 */