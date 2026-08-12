/*    */ import java.io.IOException;
/*    */ import java.io.PrintStream;
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
/*    */ public class LAKDL_AF_MK_Help_Servlet extends HttpServlet
	/*    */ {
	/*
		Connection conn;
		Statement stmt;
		Class t;
		public ResultSet rs;
		public ResultSetMetaData rms;
		public String m_chksql;
		public Object Ret_Obj;
		
	*/
	/*    */ 
	/*    */   //public synchronized void service(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse)
	public  void service(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse)
		/*    */     throws IOException
		/*    */   {
		
		/*    */   Connection conn=null;
		/*    */   Statement stmt=null;
		/*    */   Class t=null;
		/*    */   ResultSet rs=null;
		/*    */   ResultSetMetaData rms=null;
		/*    */   String m_chksql=null;
		/*    */   Object Ret_Obj=null;
		
		
		/*    */     try
			/*    */     {
			/* 29 */       ServletOutputStream localServletOutputStream1 = paramHttpServletResponse.getOutputStream();
			/*    */ 
			/* 33 */       LAKDL_AF_CO_conn_methods localLAKDL_AF_CO_conn_methods = new LAKDL_AF_CO_conn_methods();
			/* 34 */       conn = localLAKDL_AF_CO_conn_methods.met_user_validate(paramHttpServletRequest);
			               String m_html_client_home_url = localLAKDL_AF_CO_conn_methods.servlet_client_url;
			/* 35 */       String str1 = localLAKDL_AF_CO_conn_methods.bg_color1;
			/* 36 */       String str2 = localLAKDL_AF_CO_conn_methods.bg_color2;
			/* 37 */       String str3 = localLAKDL_AF_CO_conn_methods.fo_color1;
			/* 38 */       String str4 = localLAKDL_AF_CO_conn_methods.fo_color2;
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
				/* 93 */         localObject3 = localField1.get(localObject1);
				/* 94 */         localObject4 = localField2.get(localObject1);
				/*    */       }
			/*    */       catch (IllegalAccessException localIllegalAccessException)
				/*    */       {
				/* 98 */         localServletOutputStream1.println("Invalid");
				/*    */       }
			/*    */ 
			/* 105 */       String str18 = localObject3.toString();
			/*    */ 
			/* 107 */       String str19 = localObject4.toString();
			/*    */ 
			/* 109 */       String[] arrayOfString = new String[10];
			/* 110 */       int i = 1;
			/*    */ 
			/* 113 */       stmt = conn.createStatement();
			/*    */       try
				/*    */       {
				/* 118 */         rs = stmt.executeQuery(str18);
				/*    */       }
			/*    */       catch (SQLException localSQLException)
				/*    */       {
				/* 122 */         localServletOutputStream1.println(localSQLException.toString());
				/*    */       }
			/* 124 */       rms = rs.getMetaData();
			/* 125 */       boolean bool = rs.next();
			/*    */ 
			/* 128 */       int j = rms.getColumnCount();
			/*    */       try
				/*    */       {
				/* 133 */         localServletOutputStream1.println("<HTML><HEAD>");
				/* 134 */         localServletOutputStream1.println("<TITLE>Net Asset</TITLE>");
				/* 135 */         localServletOutputStream1.println("<link href=\"" + str5 + "/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
				/* 136 */         localServletOutputStream1.println("<SCRIPT language = \"javascript\">");
				/*    */ 
				/* 138 */         localServletOutputStream1.println("function onmouseover_action(m_col1){");
				/* 139 */         localServletOutputStream1.println(" m_col1.style.background='" + str2 + "';");
				/* 140 */         localServletOutputStream1.println(" m_col1.style.color='" + str4 + "';");
				/* 141 */         localServletOutputStream1.println("}");
				/*    */ 
				/* 143 */         localServletOutputStream1.println("function MyDialog(){");
				/*    */ 
				/* 145 */         localServletOutputStream1.println(" this.valin;");
				/* 146 */         localServletOutputStream1.println(" this.valout = new Array(10);");
				/* 147 */         localServletOutputStream1.println("}");
				/*    */ 
				/* 149 */         localServletOutputStream1.println("function onmouseout_action(m_col1){");
				/* 150 */         localServletOutputStream1.println(" m_col1.style.background='" + str1 + "';");
				/* 151 */         localServletOutputStream1.println(" m_col1.style.color='" + str3 + "';");
				/* 152 */         localServletOutputStream1.println("}");
				/*    */ 
				/* 154 */         localServletOutputStream1.println("function Selected(ref,val,veri,Start,End){");
				/* 155 */         localServletOutputStream1.println("if(veri=='1'){");
				/* 156 */         localServletOutputStream1.println("window.dialogArguments.valout [1] = 'Next';");
				/* 157 */         localServletOutputStream1.println("window.dialogArguments.valout [2] =  Start  ;");
				/* 158 */         localServletOutputStream1.println("window.dialogArguments.valout [3] =  End;");
				/*    */ 
				/* 160 */         localServletOutputStream1.println(" window.close();");
				/* 161 */         localServletOutputStream1.println("}else{");
				/* 162 */         localServletOutputStream1.println("if(veri=='2'){");
				/* 163 */         localServletOutputStream1.println("window.dialogArguments.valout [1] = 'Prev';");
				/* 164 */         localServletOutputStream1.println("window.dialogArguments.valout [2] =  Start  ;");
				/* 165 */         localServletOutputStream1.println("window.dialogArguments.valout [3] =  End;");
				/* 166 */         localServletOutputStream1.println(" window.close();");
				/* 167 */         localServletOutputStream1.println("}else{");
				/* 168 */         localServletOutputStream1.println("for(i=1;i<=" + j + ";i++){");
				/* 169 */         //localServletOutputStream1.println("window.dialogArguments.valout [i] =ref[val][i];}");
				                  localServletOutputStream1.println("window.dialogArguments.valout [i] = replace_and_sign(ref[val][i]);}");//ADDED MILINDA FOR REPLACE SPECIAL CHARACTOR ON 15-10-2021
				/* 170 */         localServletOutputStream1.println(" window.close();");
				/* 171 */         localServletOutputStream1.println("  }}");
				/* 172 */         localServletOutputStream1.println("}"); 
				/*    */ 
				/* 174 */         localServletOutputStream1.println("function Next() {");
				/* 175 */         int k = Integer.parseInt(str9) + 10;
				/* 176 */         int m = Integer.parseInt(str9) + 1;
				/* 177 */         localServletOutputStream1.println("oBj = new MyDialog()");
				/* 178 */         localServletOutputStream1.println(" Selected(oBj.valout,'1','1','" + m + "','" + k + "');");
				/* 179 */         localServletOutputStream1.println("}");
				/*    */  
				/* 181 */         localServletOutputStream1.println("function Last() {"); 
				/* 182 */         int n = Integer.parseInt(str11); 
				/* 183 */         int i1 = Integer.parseInt(str11) - 10;
				/* 184 */         localServletOutputStream1.println("oBj = new MyDialog()");
				/* 185 */         localServletOutputStream1.println(" Selected(oBj.valout,'1','1','" + i1 + "','" + n + "');");
				/* 186 */         localServletOutputStream1.println("}");
				/*    */ 
				/* 188 */         localServletOutputStream1.println("function First() {");
				/* 189 */         localServletOutputStream1.println("oBj = new MyDialog()");
				/* 190 */         localServletOutputStream1.println(" Selected(oBj.valout,'1','1','1','10');");
				/* 191 */         localServletOutputStream1.println("}");
				/*    */ 
				/* 193 */         localServletOutputStream1.println("function Prev() {");
				/* 194 */         int i2 = Integer.parseInt(str8) - 10;
				/* 195 */         int i3 = Integer.parseInt(str8) - 1;
				/* 196 */         localServletOutputStream1.println("oBj= new MyDialog()");
				/* 197 */         localServletOutputStream1.println(" Selected(oBj.valout,'1','2','" + i2 + "','" + i3 + "');");
				/* 198 */         localServletOutputStream1.println("}");
				/*    */ 
				/* 200 */         localServletOutputStream1.println(" function Close(){");
				/* 201 */         localServletOutputStream1.println("  window.dialogArguments.valout [1] = 'Close';");
				/* 202 */         localServletOutputStream1.println("  window.close();");
				/* 203 */         localServletOutputStream1.println(" }");
				/*    */ 
				/* 205 */         localServletOutputStream1.println("</SCRIPT>");
				/* 206 */         localServletOutputStream1.println("</HEAD>");
				/* 207 */         localServletOutputStream1.println("<BODY BGCOLOR='white' topmargin=0 leftmargin=0 onload=MyDialog();>");
				/* 208 */         localServletOutputStream1.println("<FORM NAME=\"form1\">");
				/*    */ 
				/* 211 */         if (bool)
					/*    */         {
					/* 216 */           localServletOutputStream1.println("<TABLE WIDTH='100%' class=pdn_table2>");
					/* 217 */           localServletOutputStream1.println("<TR class=txt_input_help_head>");
					/* 218 */           int i4 = Integer.parseInt(str10);
					/* 219 */           for (int i5 = 1; i5 <= j - i4; i5++)
						/*    */           {
						/* 221 */             String str20 = rms.getColumnLabel(i5);
						/* 222 */             localServletOutputStream1.println("<TD >" + str20 + "</TD>");
						/*    */           }
					/* 224 */           localServletOutputStream1.println("</TR>");
					/* 225 */           int i6 = 0;
					/* 226 */           int i7 = 0;
					/* 227 */           localServletOutputStream1.println("<SCRIPT LANGUAGE = 'JavaScript'>");
					/* 228 */           localServletOutputStream1.println("ColCount = " + j + ";");
					/* 229 */           localServletOutputStream1.println(" NewArr = new Array(60);");
					/* 230 */           localServletOutputStream1.println(" for(i=1;i<=60;i++){");
					/* 231 */           localServletOutputStream1.println(" NewArr[i] = new Array(ColCount);}");
					/* 232 */           localServletOutputStream1.println("</SCRIPT>");
					/*    */           int i8;
					/*    */           int i9;
					/* 233 */           while (bool)
						/*    */           {
						/* 236 */             if (rs.getString(1).equals("1"))
							/*    */             {
							/* 238 */               i6 = 1;
							/*    */             }
						/* 240 */             if (rs.getString(1).equals(str11))
							/*    */             {
							/* 242 */               i7 = 1;
							/*    */             }
						/* 244 */             if (i < 11)
							/*    */             {
							/* 246 */               localServletOutputStream1.println("<TR ID=M_LOC" + i + " class=txt_input_help  onmouseover=\"onmouseover_action(M_LOC" + i + ")\" onmouseout=\"onmouseout_action(M_LOC" + i + ");\" >");
							/* 247 */               for (i8 = 1; i8 <= j; i8++)
								/*    */               {
								/* 249 */                 localServletOutputStream1.println("<SCRIPT LANGUAGE = 'JavaScript'>");
								/* 250 */                 localServletOutputStream1.println(" NewArr[" + i + "][" + i8 + "]=\"" + rs.getString(i8) + "\";");
								/* 251 */                 localServletOutputStream1.println("</SCRIPT>");
								/*    */               }
							/* 253 */               for (i9 = 1; i9 <= j - i4; i9++)
								/*    */               {
								/* 259 */                 localServletOutputStream1.println("<TD onclick=\" Selected(NewArr,'" + i + "','0')\" >" + rs.getString(i9) + "</TD>");
								/*    */               }
							/*    */ 
							/* 262 */               localServletOutputStream1.println("</TR>");
							/*    */             }
						/* 264 */             i += 1;
						/* 265 */             bool = rs.next();
						/*    */           }
					/* 267 */           rs.close();
					/* 268 */           if (i <= 10)
						/*    */           {
						/* 270 */             localServletOutputStream1.println("<TABLE width='100%' class=pdn_table2>");
						/* 271 */             for (i8 = i; i8 <= 10; i8++)
							/*    */             {
							/* 273 */               localServletOutputStream1.println("<TR>");
							/* 274 */               for (i9 = 1; i9 <= j; i9++)
								/*    */               {
								/* 276 */                 localServletOutputStream1.println("<TD>-</TD>");
								/*    */               }
							/* 278 */               localServletOutputStream1.println("</TR>");
							/*    */             }
						/*    */           }
					/* 281 */           localServletOutputStream1.println("</TABLE>");
					/* 282 */           localServletOutputStream1.println("<TABLE width='100%' cellpadding='2'>");
					/* 283 */           localServletOutputStream1.println("<TR ><TD width='50%'></TD><TD>");
					/* 284 */           if (i6 != 0)
						/*    */           {
						/* 286 */             localServletOutputStream1.println("<INPUT TYPE ='Button' NAME ='First_Val' VALUE ='First 10' style='{cursor:hand; color: black; font: bold 8pt arial; border-color: silver; background: silver;}' DISABLED ></TD><TD>");
						/* 287 */             localServletOutputStream1.println("<INPUT TYPE ='Button' NAME ='Prev_Val' VALUE ='Prev 10' style='{cursor:hand; color: black; font: bold 8pt arial; border-color: silver; background: silver;}' DISABLED ></TD><TD>");
						/*    */           }
					/* 290 */           else if (Integer.parseInt(str11) <= 10)
						/*    */           {
						/* 292 */             localServletOutputStream1.println("<INPUT TYPE ='Button' NAME ='First_Val' VALUE ='First 10' style='{cursor:hand; color: black; font: bold 8pt arial; border-color: silver; background: silver;}' DISABLED ></TD><TD>");
						/* 293 */             localServletOutputStream1.println("<INPUT TYPE ='Button' NAME ='Prev_Val' VALUE ='Prev 10' style='{cursor:hand; color: black; font: bold 8pt arial; border-color: silver; background: silver;}' OnClick='Prev();'></TD><TD>");
						/*    */           }
					/*    */           else
						/*    */           {
						/* 297 */             localServletOutputStream1.println("<INPUT TYPE ='Button' NAME ='First_Val' VALUE ='First 10' style='{cursor:hand; color: black; font: bold 8pt arial; border-color: silver; background: silver;}' OnClick='First();'></TD><TD>");
						/* 298 */             localServletOutputStream1.println("<INPUT TYPE ='Button' NAME ='Prev_Val' VALUE ='Prev 10' style='{cursor:hand; color: black; font: bold 8pt arial; border-color: silver; background: silver;}' OnClick='Prev();'></TD><TD>");
						/*    */           }
					/*    */ 
					/* 302 */           if (i <= 11)
						/*    */           {
						/* 304 */             localServletOutputStream1.println("<INPUT TYPE ='Button' NAME ='Next_Val' VALUE ='Next 10' style='{ color: black; font: bold 8pt arial; border-color: silver; background: silver;}' DISABLED ></TD><TD>");
						/* 305 */             localServletOutputStream1.println("<INPUT TYPE ='Button' NAME ='Last_Val' VALUE ='Last 10' style='{ color: black; font: bold 8pt arial; border-color: silver; background: silver;}' DISABLED ></TD><TD>");
						/*    */           }
					/* 308 */           else if (i7 != 0)
						/*    */           {
						/* 310 */             localServletOutputStream1.println("<INPUT TYPE ='Button' NAME ='Next_Val' VALUE ='Next 10' style='{ color: black; font: bold 8pt arial; border-color: silver; background: silver;}' DISABLED ></TD><TD>");
						/* 311 */             localServletOutputStream1.println("<INPUT TYPE ='Button' NAME ='Last_Val' VALUE ='Last 10' style='{ color: black; font: bold 8pt arial; border-color: silver; background: silver;}' DISABLED ></TD><TD>");
						/*    */           }
					/*    */           else
						/*    */           {
						/* 315 */             localServletOutputStream1.println("<INPUT TYPE ='Button' NAME ='Next_Val' VALUE ='Next 10' style='{cursor:hand; color: black; font: bold 8pt arial; border-color: silver; background: silver;}'  OnClick='Next();' ></TD><TD>");
						/* 316 */             localServletOutputStream1.println("<INPUT TYPE ='Button' NAME ='Last_Val' VALUE ='Last 10' style='{cursor:hand; color: black; font: bold 8pt arial; border-color: silver; background: silver;}'  OnClick='Last();' ></TD><TD>");
						/*    */           }
					/*    */ 
					/* 319 */           localServletOutputStream1.println("<INPUT TYPE ='Button' NAME ='Exit'  VALUE ='   Exit   ' style='{cursor:hand; color: black; font: bold 8pt arial; border-color: silver; background: silver;}' OnClick='Close();'></TD><TD width='5%'></TD></TR>");
					/* 320 */           localServletOutputStream1.println("</TABLE>");
					/*    */         }
				/*    */         else
					/*    */         {
					/* 325 */           localServletOutputStream1.println("<br><br><br><br><br><p align='center'><font face=' sans-serif, arial' color=black size=4 boldness=700 >No records available</font></p>");
					/*    */         }
				/*    */ localServletOutputStream1.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_home_url+"/ajax_data_gateway.js'></SCRIPT>");//[ADDED BY MILIND FOR JB07102021-15043]
				/* 328 */         localServletOutputStream1.println("</BODY>");
				/* 329 */         localServletOutputStream1.println("</HTML>");
				/*    */       }
			/*    */       catch (Exception localException3)
				/*    */       {
				/* 334 */         System.out.println("Exception  " + localException3.toString());
				/*    */       }
			/*    */       try
				/*    */       {
				/* 338 */         conn.close();
				/*    */       }
			/*    */       catch (Exception localException4)
				/*    */       {
				/* 342 */         System.out.println("Exception at help_exe " + localException4.toString());
				/*    */       }
			/* 344 */       localServletOutputStream1.close();
			/* 345 */       destroy();
			/*    */     }
		/*    */     catch (Exception localException1)
			/*    */     {
			/*    */       try
				/*    */       {
				/* 354 */         conn.close();
				/*    */       } catch (Exception localException2) {
				/*    */       }
			/* 357 */       ServletOutputStream localServletOutputStream2 = paramHttpServletResponse.getOutputStream();
			/* 358 */       localServletOutputStream2.close();
			/*    */     }
		/*    */   }
	/*    */ }

/* Location:           E:\DESKTOP FILES\Transfer\LAKDL\2017\May\19-05-2017\From Live\
 * Qualified Name:     LAKDL_AF_MK_Help_Servlet
 * JD-Core Version:    0.6.2
 */