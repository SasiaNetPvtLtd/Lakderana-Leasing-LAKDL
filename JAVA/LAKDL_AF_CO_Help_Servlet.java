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
/*    */ public class LAKDL_AF_CO_Help_Servlet extends HttpServlet
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
	
	//public synchronized void service(HttpServletRequest req, HttpServletResponse res)
	public  void service(HttpServletRequest req, HttpServletResponse res)
		/*    */     throws IOException
		/*    */   {
		
		
		Connection conn=null;
		Statement stmt=null;
		Class t=null;
		ResultSet rs=null;
		ResultSetMetaData rms=null;
		String m_chksql=null;
		Object Ret_Obj=null;
		
		/*    */     try
			/*    */     {
			/* 29 */       ServletOutputStream out = res.getOutputStream();
			/*    */ 
			/* 33 */       LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods();
			/* 34 */       conn = m_sn_methods.met_user_validate(req);
			               String m_html_client_home_url = m_sn_methods.servlet_client_url;
			/* 35 */       String bg_color1 = m_sn_methods.bg_color1;
			/* 36 */       String bg_color2 = m_sn_methods.bg_color2; 
			/* 37 */       String fo_color1 = m_sn_methods.fo_color1;
			/* 38 */       String fo_color2 = m_sn_methods.fo_color2;
			/*    */ 
			/* 43 */       String m_html_client_url = m_sn_methods.html_client_url;
			/*    */ 
			/* 49 */       res.setStatus(200);
			/* 50 */       res.setContentType("text/html");
			/*    */ 
			/* 52 */       String Class_Name = req.getParameter("class_in");
			/* 53 */       String SqlName = req.getParameter("Sql_in");
			/* 54 */       String Start_Val = req.getParameter("Start_in");
			/* 55 */       String Stop_Val = req.getParameter("End_in"); 
			/* 56 */       String Hid_No = req.getParameter("Hid_No");
			/* 57 */       String Max_val = req.getParameter("Max_in");
			/*    */ 
			/* 59 */       String Head_Name = SqlName + "_Header";
			/* 60 */       String Criteria = req.getParameter("Crit_In").toUpperCase();
			/*    */ 
			/* 63 */       Class New_Class = Class.forName(Class_Name);
			/* 64 */       Object Obj_in = New_Class.newInstance();
			/* 65 */       Method[] mygetSql = New_Class.getDeclaredMethods();
			/*    */ 
			/* 68 */       Object Obj_Input1 = SqlName;
			/* 69 */       Object Obj_Input2 = Start_Val;
			/* 70 */       Object Obj_Input3 = Stop_Val;
			/* 71 */       Object Obj_Input4 = Criteria;
			/*    */ 
			/* 74 */       Object[] New_Arr = new Object[4];
			/* 75 */       New_Arr[0] = Obj_Input1;
			/* 76 */       New_Arr[1] = Obj_Input2;
			/* 77 */       New_Arr[2] = Obj_Input3;
			/* 78 */       New_Arr[3] = Obj_Input4;
			/*    */ 
			/* 81 */        Ret_Obj = mygetSql[0].invoke(Obj_in, New_Arr);
			/*    */ 
			/* 83 */       Field Field_in = New_Class.getField(SqlName);
			/*    */ 
			/* 85 */       Object New_Object = new Object();
			/* 86 */       Field Field_in2 = New_Class.getField(Head_Name);
			/*    */ 
			/* 88 */       Object New_Object2 = new Object();
			/*    */       try
				/*    */       {
				/* 92 */         New_Object = Field_in.get(Obj_in);
				/* 93 */         New_Object2 = Field_in2.get(Obj_in);
				/*    */       }
			/*    */       catch (IllegalAccessException e)
				/*    */       {
				/* 97 */         out.println("Invalid");
				/*    */       }
			/*    */ 
			/* 104 */       String Sql_Field = New_Object.toString();
			/*    */ 
			/* 106 */       String Header_Field = New_Object2.toString();
			/*    */ 
			/* 108 */       String[] StrArr = new String[10];
			/* 109 */       int i = 1;
			/*    */ 
			/* 112 */       stmt = conn.createStatement();
			/*    */       try
				/*    */       {
				/* 116 */         rs = stmt.executeQuery(Sql_Field);
				/*    */       }
			/*    */       catch (SQLException e) {
				/* 119 */         out.println(e.toString());
				/*    */       }
			/* 121 */       rms = rs.getMetaData();
			/* 122 */       boolean more = rs.next();
			/*    */ 
			/* 125 */       int count = rms.getColumnCount();
			/*    */       try
				/*    */       {
				/* 129 */         out.println("<HTML><HEAD>");
				/* 130 */         out.println("<TITLE>" + Head_Name + "</TITLE>");
				/* 131 */         out.println("<link href=\"" + m_html_client_url + "/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
				/* 132 */         out.println("<SCRIPT language = \"javascript\">");
				/*    */ 
				/* 134 */         out.println("function onmouseover_action(m_col1){");
				/* 135 */         out.println(" m_col1.style.background='" + bg_color2 + "';");
				/* 136 */         out.println(" m_col1.style.color='" + fo_color2 + "';");
				/* 137 */         out.println("}");
				/*    */ 
				/* 139 */         out.println("function MyDialog(){");
				/*    */ 
				/* 141 */         out.println(" this.valin;");
				/* 142 */         out.println(" this.valout = new Array(10);");
				/* 143 */         out.println("}");
				/*    */ 
				/* 145 */         out.println("function onmouseout_action(m_col1){");
				/* 146 */         out.println(" m_col1.style.background='" + bg_color1 + "';");
				/* 147 */         out.println(" m_col1.style.color='" + fo_color1 + "';");
				/* 148 */         out.println("}");
				/*    */ 
				/* 150 */         out.println("function Selected(ref,val,veri,Start,End){");
				/* 151 */         out.println("if(veri=='1'){");
				/* 152 */         out.println("window.dialogArguments.valout [1] = 'Next';");
				/* 153 */         out.println("window.dialogArguments.valout [2] =  Start  ;");
				/* 154 */         out.println("window.dialogArguments.valout [3] =  End;");
				/*    */ 
				/* 156 */         out.println(" window.close();");
				/* 157 */         out.println("}else{");
				/* 158 */         out.println("if(veri=='2'){");
				/* 159 */         out.println("window.dialogArguments.valout [1] = 'Prev';");
				/* 160 */         out.println("window.dialogArguments.valout [2] =  Start  ;");
				/* 161 */         out.println("window.dialogArguments.valout [3] =  End;");
				/* 162 */         out.println(" window.close();");
				/* 163 */         out.println("}else{");
				/* 164 */         out.println("for(i=1;i<=" + count + ";i++){");
				/* 165 */         //out.println("window.dialogArguments.valout [i] =ref[val][i];}");
				                  out.println("window.dialogArguments.valout [i] = replace_and_sign(ref[val][i]);}");//ADDED MILINDA FOR REPLACE SPECIAL CHARACTOR
				/* 166 */         out.println(" window.close();");
				/* 167 */         out.println("  }}");
				/* 168 */         out.println("}"); 
				/*    */ 
				/* 170 */         out.println("function Next() {");
				/* 171 */         int Next_Val = Integer.parseInt(Stop_Val) + 10;
				/* 172 */         int new_Start = Integer.parseInt(Stop_Val) + 1;
				/* 173 */         out.println("oBj = new MyDialog()");
				/* 174 */         out.println(" Selected(oBj.valout,'1','1','" + new_Start + "','" + Next_Val + "');");
				/* 175 */         out.println("}");
				/*    */ 
				/* 177 */         out.println("function Last() {");
				/* 178 */         int Last_Val = Integer.parseInt(Max_val);
				/* 179 */         int Last_Start = Integer.parseInt(Max_val) - 10;
				/* 180 */         out.println("oBj = new MyDialog()");
				/* 181 */         out.println(" Selected(oBj.valout,'1','1','" + Last_Start + "','" + Last_Val + "');");
				/* 182 */         out.println("}");
				/*    */ 
				/* 184 */         out.println("function First() {");
				/* 185 */         out.println("oBj = new MyDialog()");
				/* 186 */         out.println(" Selected(oBj.valout,'1','1','1','10');");
				/* 187 */         out.println("}");
				/*    */ 
				/* 189 */         out.println("function Prev() {");
				/* 190 */         int Prev_Val = Integer.parseInt(Start_Val) - 10;
				/* 191 */         int new_pre_Val = Integer.parseInt(Start_Val) - 1;
				/* 192 */         out.println("oBj= new MyDialog()");
				/* 193 */         out.println(" Selected(oBj.valout,'1','2','" + Prev_Val + "','" + new_pre_Val + "');");
				/* 194 */         out.println("}");
				/*    */ 
				/* 196 */         out.println(" function Close(){");
				/* 197 */         out.println("  window.dialogArguments.valout [1] = 'Close';");
				/* 198 */         out.println("  window.close();");
				/* 199 */         out.println(" }");
				/*    */ 
				/* 201 */         out.println("</SCRIPT>");
				/* 202 */         out.println("</HEAD>");
				/* 203 */         out.println("<BODY BGCOLOR='white' topmargin=0 leftmargin=0 onload=MyDialog();>");
				/* 204 */         out.println("<FORM NAME=\"form1\">");
				/*    */ 
				/* 207 */         if (more)
					/*    */         {
					/* 211 */           out.println("<TABLE WIDTH='100%' class=pdn_table2>");
					/* 212 */           out.println("<TR class=txt_input_help_head>");
					/* 213 */           int Hidden_Val = Integer.parseInt(Hid_No);
					/* 214 */           for (int j = 1; j <= count - Hidden_Val; j++) {
						/* 215 */             String Coloumn_Name = rms.getColumnLabel(j);
						/* 216 */             out.println("<TD >" + Coloumn_Name + "</TD>");
						/*    */           }
					/* 218 */           out.println("</TR>");
					/* 219 */           boolean m_first_record = false;
					/* 220 */           boolean m_last_record = false;
					/* 221 */           out.println("<SCRIPT LANGUAGE = 'JavaScript'>");
					/* 222 */           out.println("ColCount = " + count + ";");
					/* 223 */           out.println(" NewArr = new Array(60);");
					/* 224 */           out.println(" for(i=1;i<=60;i++){");
					/* 225 */           out.println(" NewArr[i] = new Array(ColCount);}");
					/* 226 */           out.println("</SCRIPT>");
					/* 227 */           while (more)
						/*    */           {
						/* 229 */             if (rs.getString(1).equals("1")) {
							/* 230 */               m_first_record = true;
							/*    */             }
						/* 232 */             if (rs.getString(1).equals(Max_val)) {
							/* 233 */               m_last_record = true;
							/*    */             }
						/* 235 */             if (i < 11) {
							/* 236 */               out.println("<TR ID=M_LOC" + i + " class=txt_input_help  onmouseover=\"onmouseover_action(M_LOC" + i + ")\" onmouseout=\"onmouseout_action(M_LOC" + i + ");\" >");
							/* 237 */               for (int j = 1; j <= count; j++) {
								/* 238 */                 out.println("<SCRIPT LANGUAGE = 'JavaScript'>");
								/* 239 */                 out.println(" NewArr[" + i + "][" + j + "]=\"" + rs.getString(j) + "\";");
								/* 240 */                 out.println("</SCRIPT>");
								/*    */               }
							/* 242 */               for (int j = 1; j <= count - Hidden_Val; j++)
								/*    */               {
								/* 247 */                 out.println("<TD onclick=\" Selected(NewArr,'" + i + "','0')\" >" + rs.getString(j) + "</TD>");
								/*    */               }
							/*    */ 
							/* 250 */               out.println("</TR>");
							/*    */             }
						/* 252 */             i += 1;
						/* 253 */             more = rs.next();
						/*    */           }
					/* 255 */           rs.close();
					/* 256 */           if (i <= 10) {
						/* 257 */             out.println("<TABLE width='100%' class=pdn_table2>");
						/* 258 */             for (int k = i; k <= 10; k++) {
							/* 259 */               out.println("<TR>");
							/* 260 */               for (int h = 1; h <= count; h++) {
								/* 261 */                 out.println("<TD>-</TD>");
								/*    */               }
							/* 263 */               out.println("</TR>");
							/*    */             }
						/*    */           }
					/* 266 */           out.println("</TABLE>");
					/* 267 */           out.println("<TABLE width='100%' cellpadding='2'>");
					/* 268 */           out.println("<TR ><TD width='50%'></TD><TD>");
					/* 269 */           if (m_first_record) {
						/* 270 */             out.println("<INPUT TYPE ='Button' NAME ='First_Val' VALUE ='First 10' style='{cursor:hand; color: black; font: bold 8pt arial; border-color: silver; background: silver;}' DISABLED ></TD><TD>");
						/* 271 */             out.println("<INPUT TYPE ='Button' NAME ='Prev_Val' VALUE ='Prev 10' style='{cursor:hand; color: black; font: bold 8pt arial; border-color: silver; background: silver;}' DISABLED ></TD><TD>");
						/*    */           }
					/* 273 */           else if (Integer.parseInt(Max_val) <= 10) {
						/* 274 */             out.println("<INPUT TYPE ='Button' NAME ='First_Val' VALUE ='First 10' style='{cursor:hand; color: black; font: bold 8pt arial; border-color: silver; background: silver;}' DISABLED ></TD><TD>");
						/* 275 */             out.println("<INPUT TYPE ='Button' NAME ='Prev_Val' VALUE ='Prev 10' style='{cursor:hand; color: black; font: bold 8pt arial; border-color: silver; background: silver;}' OnClick='Prev();'></TD><TD>");
						/*    */           }
					/*    */           else {
						/* 278 */             out.println("<INPUT TYPE ='Button' NAME ='First_Val' VALUE ='First 10' style='{cursor:hand; color: black; font: bold 8pt arial; border-color: silver; background: silver;}' OnClick='First();'></TD><TD>");
						/* 279 */             out.println("<INPUT TYPE ='Button' NAME ='Prev_Val' VALUE ='Prev 10' style='{cursor:hand; color: black; font: bold 8pt arial; border-color: silver; background: silver;}' OnClick='Prev();'></TD><TD>");
						/*    */           }
					/*    */ 
					/* 283 */           if (i <= 11) {
						/* 284 */             out.println("<INPUT TYPE ='Button' NAME ='Next_Val' VALUE ='Next 10' style='{ color: black; font: bold 8pt arial; border-color: silver; background: silver;}' DISABLED ></TD><TD>");
						/* 285 */             out.println("<INPUT TYPE ='Button' NAME ='Last_Val' VALUE ='Last 10' style='{ color: black; font: bold 8pt arial; border-color: silver; background: silver;}' DISABLED ></TD><TD>");
						/*    */           }
					/* 287 */           else if (m_last_record) {
						/* 288 */             out.println("<INPUT TYPE ='Button' NAME ='Next_Val' VALUE ='Next 10' style='{ color: black; font: bold 8pt arial; border-color: silver; background: silver;}' DISABLED ></TD><TD>");
						/* 289 */             out.println("<INPUT TYPE ='Button' NAME ='Last_Val' VALUE ='Last 10' style='{ color: black; font: bold 8pt arial; border-color: silver; background: silver;}' DISABLED ></TD><TD>");
						/*    */           }
					/*    */           else {
						/* 292 */             out.println("<INPUT TYPE ='Button' NAME ='Next_Val' VALUE ='Next 10' style='{cursor:hand; color: black; font: bold 8pt arial; border-color: silver; background: silver;}'  OnClick='Next();' ></TD><TD>");
						/* 293 */             out.println("<INPUT TYPE ='Button' NAME ='Last_Val' VALUE ='Last 10' style='{cursor:hand; color: black; font: bold 8pt arial; border-color: silver; background: silver;}'  OnClick='Last();' ></TD><TD>");
						/*    */           }
					/*    */ 
					/* 296 */           out.println("<INPUT TYPE ='Button' NAME ='Exit'  VALUE ='   Exit   ' style='{cursor:hand; color: black; font: bold 8pt arial; border-color: silver; background: silver;}' OnClick='Close();'></TD><TD width='5%'></TD></TR>");
					/* 297 */           out.println("</TABLE>");
					/*    */         }
				/*    */         else {
					/* 300 */           out.println("<br><br><br><br><br><p align='center'><font face=' sans-serif, arial' color=white size=4 boldness=700 >No records available</font></p>");
					/*    */         }
				/*    */ out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_home_url+"/ajax_data_gateway.js'></SCRIPT>");//[ADDED BY MILIND FOR JB07102021-15043]
				/* 303 */         out.println("</BODY>");
				/* 304 */         out.println("</HTML>");
				/*    */       }
			/*    */       catch (Exception exe)
				/*    */       {
				/* 308 */         out.println("Exception  " + exe.toString());
				/*    */       }
			/*    */       try {
				/* 311 */         conn.close();
				/*    */       }
			/*    */       catch (Exception help_exe) {
				/* 314 */         out.println("Exception at help_exe " + help_exe.toString());
				/*    */       }
			/* 316 */       out.close();
			/* 317 */       destroy();
			/*    */     }
		/*    */     catch (Exception e)
			/*    */     {
			/*    */       try
				/*    */       {
				/* 323 */         conn.close();
				/*    */       } catch (Exception eti) {
				/*    */       }
			/* 326 */       ServletOutputStream out = res.getOutputStream();
			/* 327 */       out.close();
			/*    */     }
		/*    */   }
	/*    */ }

/* Location:           E:\DESKTOP FILES\Transfer\LAKDL\2017\June\03-06-2017\From Live\
 * Qualified Name:     LAKDL_AF_CO_Help_Servlet
 * JD-Core Version:    0.6.2
 */