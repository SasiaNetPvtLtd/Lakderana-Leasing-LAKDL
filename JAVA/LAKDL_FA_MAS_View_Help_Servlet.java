import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; 
import sun.misc.BASE64Decoder; 
import java.lang.*;
import java.lang.reflect.*; 
import oracle.jdbc.driver.*;


public class LAKDL_FA_MAS_View_Help_Servlet extends HttpServlet {
	
	Connection conn;
	Statement stmt;
	public ResultSet rs;
	public ResultSetMetaData rms; 
	public Object Ret_Obj;
	
	public synchronized void service(HttpServletRequest req, HttpServletResponse res) throws IOException{
		
		try {
			
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods();
			conn = m_sn_methods.met_user_validate(req); 
			String m_schema_name = m_sn_methods.schema_name.trim();       
			String LoginUser=m_sn_methods.username;
			String m_html_client_url=m_sn_methods.html_client_url.trim();
			String m_html_client_home_url = m_sn_methods.servlet_client_url;
			res.setStatus(HttpServletResponse.SC_OK);
			res.setContentType("text/html");
			res.setHeader("Cache-Control", "No-Cache");
			res.setDateHeader("Expires", 0);
			
			String Class_Name=req.getParameter("class_in");
			String SqlName=req.getParameter("Sql_in");		 
			String Start_Val=req.getParameter("Start_in");		  
			String Stop_Val=req.getParameter("End_in");
			String Hid_No=req.getParameter("Hid_No");
			String Head_Name= SqlName+"_Header"; 
			String Criteria	= req.getParameter("Crit_In").toUpperCase();
			
			Class New_Class =Class.forName(Class_Name);
			Object Obj_in = New_Class.newInstance();
			Method[] mygetSql =New_Class.getDeclaredMethods();	
			ServletOutputStream out = res.getOutputStream();
			
			Object Obj_Input1 = (Object) SqlName;
			Object Obj_Input2 = (Object) Start_Val;
			Object Obj_Input3 = (Object) Stop_Val ;
			Object Obj_Input4 = (Object) Criteria;					
			Object Obj_Input5 = (Object) LoginUser;
			
			
			Object[] New_Arr  = new Object[5];
			
			New_Arr[0]        = Obj_Input1;
			New_Arr[1]        = Obj_Input2;
			New_Arr[2]        = Obj_Input3; 
			New_Arr[3]		  	=	Obj_Input4;
			New_Arr[4]        = Obj_Input5;
			
			Object Ret_Obj = mygetSql[0].invoke(Obj_in,New_Arr);
			
			Field Field_in     = New_Class.getField(SqlName);
			Object New_Object  = new Object();
			Field Field_in2    = New_Class.getField(Head_Name);
			Object New_Object2 = new Object();
			
			try{
				New_Object  = Field_in.get(Obj_in);
				New_Object2 = Field_in2.get(Obj_in); 
			}
			catch (IllegalAccessException e){
				out.println("Invalid");
			}			
			
			String Sql_Field    = New_Object.toString();
			String Header_Field = New_Object2.toString();
			String [] StrArr = new String[10] ;
			int i=1;
			stmt  = conn.createStatement ();
			
			//out.println(Sql_Field);
			
			try{
				rs  = stmt.executeQuery(Sql_Field);
			}
			catch(SQLException e) {
				out.println(e.toString());
			}	
			
			rms = rs.getMetaData();
			boolean more = rs.next() ;
			int count    = rms.getColumnCount();
			
			//out.println(Stop_Val);
			
			out.println("<HTML><HEAD>");
			out.println("<TITLE> "+Header_Field+"</TITLE>");
			out.println("<link REL='STYLESHEET' HREF='"+m_html_client_url+"/css/Asset_Financing_System.css' TYPE=\"text/css\">");
			
			out.println("<SCRIPT>");
			
			out.println("function onmouseover_action(m_col1){");
			out.println(" m_col1.style.background='darkblue';");
			out.println(" m_col1.style.color='white';");
			out.println("}");
			
			out.println("function MyDialog(){");
			out.println(" this.valin;");
			out.println(" this.valout = new Array(50);");
			out.println("}");	
			
			out.println("function onmouseout_action(m_col1){");
			out.println(" m_col1.style.color='black';");
			out.println(" m_col1.style.background='white';");
			out.println("}");
			
			out.println("function Selected(ref,val,veri,Start,End){");
			out.println("if(veri=='1'){");
			out.println("window.dialogArguments.valout [1] = 'Next';");
			out.println("window.dialogArguments.valout [2] =  Start  ;");
			out.println("window.dialogArguments.valout [3] =  End;");
			out.println(" window.close();");
			out.println("}else{");
			out.println("if(veri=='2'){");
			out.println("window.dialogArguments.valout [1] = 'Prev';");	
			out.println("window.dialogArguments.valout [2] =  Start  ;");
			out.println("window.dialogArguments.valout [3] =  End;");
			out.println(" window.close();");
			out.println("}else{");
			out.println("for(i=1;i<="+count+";i++){");
			//out.println("window.dialogArguments.valout [i] =ref[val][i];}");
			out.println("window.dialogArguments.valout [i] = replace_and_sign(ref[val][i]);}");//ADDED MILINDA FOR REPLACE SPECIAL CHARACTOR
			// out.println(" window.close();");
			out.println("  }}"); 
			out.println("}");									
			
			out.println("function Next() {");
			int Next_Val = Integer.parseInt(Stop_Val)+50;
			int new_Start= Integer.parseInt(Stop_Val)+1;
			out.println("oBj = new MyDialog()");
			out.println(" Selected(oBj.valout,'1','1','"+new_Start+"','"+Next_Val+"');");
			out.println("}");
			
			out.println("function Prev() {");
			int Prev_Val  =Integer.parseInt(Start_Val)-50;
			int new_pre_Val = Integer.parseInt(Start_Val)-1;
			out.println("oBj= new MyDialog()");
			out.println(" Selected(oBj.valout,'1','2','"+Prev_Val+"','"+new_pre_Val+"');");
			out.println("}");
			
			out.println(" function Close(){");
			out.println("  window.dialogArguments.valout [1] = 'Close';");	
			out.println("  window.close();");
			out.println(" }");
			
			out.println("</SCRIPT>");
			out.println("</HEAD>");
			out.println("<BODY class='body & txt-body' topmargin=0 leftmargin=0 >");
			out.println("<FORM NAME=\"form1\">");
			String Coloumn_Name;
			
			if (more) {
				out.println("<TABLE WIDTH='100%' class='pdn_table' border='1'>");
				out.println("<TR class='txt_input_help_head' align='center'>");
				int Hidden_Val  = Integer.parseInt(Hid_No);
				for(int j=1;j<=count-Hidden_Val;j++){
					Coloumn_Name= rms.getColumnLabel(j);
					out.println("<TD class='txt_input_help_head'>"+Coloumn_Name+"</TD>");
				}
				out.println("</TR>");
				boolean m_first_record = false;
				out.println("<SCRIPT LANGUAGE = 'JavaScript'>");
				out.println("ColCount = "+count+";");
				out.println(" NewArr = new Array(150);");
				out.println(" for(i=1;i<=150;i++){");
				out.println(" NewArr[i] = new Array(ColCount);}");
				out.println("</SCRIPT>");
				while (more) {
					if (rs.getString(1).equals("1")){
						m_first_record = true;
					}	
					if (i<51) {
						out.println("<TR ID=M_LOC"+i+" class='txt_input_help'  onmouseover=\"onmouseover_action(M_LOC"+i+")\" onmouseout=\"onmouseout_action(M_LOC"+i+");\" >");
						for(int j=1;j<=count;j++){
							out.println("<SCRIPT LANGUAGE = 'JavaScript'>");
							out.println(" NewArr["+i+"]["+j+"]=\""+rs.getString(j)+"\";");
							out.println("</SCRIPT>");	
						}
						for(int j=1;j<=count-Hidden_Val;j++){	
							out.println("<TD onclick=\"Selected(NewArr,'"+i+"','0')\" class='txt_input_help'>"+rs.getString(j)+"</TD>");
						}
						// Selected(NewArr,'"+i+"','0');
						out.println("</TR>");
					}
					i=i+1;
					more= rs.next(); 
				}
				rs.close();
				if(i<=Integer.parseInt(Stop_Val)){
					out.println("<TABLE width='100%' bgcolor='white'>");	
					for(int k = i; k<=50;k++) {
						out.println("<TR STYLE='{font: 7pt Verdana;}'>");
						for(int h = 1; h<=count;h++){
							out.println("<TD> </TD>");
						}	
						out.println("</TR>"); 
					}
				}	
				out.println("</TABLE>");
				out.println("<TABLE width='100%' cellpadding='1'>");	
				out.println("<TR ><TD width='20%'></TD><TD>");
				if(m_first_record){
					out.println("<INPUT TYPE ='Button' NAME ='Prev_Val' VALUE ='Previous' class='mainbut' DISABLED ></TD><TD>");	
				}
				else{
					out.println("<INPUT TYPE ='Button' NAME ='Prev_Val' VALUE ='Previous' class='mainbut' OnClick='Prev();'></TD><TD>");	
				}		
				
				if(i<=Integer.parseInt(Start_Val)+1){
					out.println("<INPUT TYPE ='Button' NAME ='Next_Val' VALUE ='Next' class='mainbut' DISABLED ></TD><TD>");
				}
				else{   
					out.println("<INPUT TYPE ='Button' NAME ='Next_Val' VALUE ='Next' class='mainbut'  OnClick='Next();' ></TD><TD>");
				}
				
				out.println("<INPUT TYPE ='Button' NAME ='Exit'  VALUE ='   Exit   ' class='mainbut' OnClick='Close();'></TD><TD align='right'></TD><TD width='20%'></TD></TR>");
				out.println("</TABLE>");		
			}
			else{
				out.println("<br><br><br><br><br><p align='center'><font face='Verdana' color=darkblue size=4 boldness=700 >No records available</font></p>");
			}
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_home_url+"/ajax_data_gateway.js'></SCRIPT>");//[ADDED BY MILIND FOR JB07102021-15043]		
			out.println("</BODY>");
			out.println("</HTML>");
			
			try {
				conn.close();
			}	
			catch (Exception help_exe) {
				out.println("Exception at help_exe "+help_exe.toString());
			}
			out.close();
			this.destroy();
		}
		catch (Exception e) {
			try {
				conn.close();
			}	
			catch (Exception eti) {}
			ServletOutputStream out = res.getOutputStream();
			out.close();
			
		}
	}
}
