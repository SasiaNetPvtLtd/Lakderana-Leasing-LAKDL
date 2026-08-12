
//import org.apache.jasper.tagplugins.jstl.core.Catch;
import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.sql.*; 
import java.util.*; 
import org.w3c.dom.*;
import org.xml.sax.*;
import javax.xml.parsers.*;
import javax.xml.transform.*; 
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.net.URL;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.lang.*;
import java.net.URLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.jsoup.*;
import org.jsoup.nodes.*;


public class LAKDL_MK_MAS_FA_CHART_xml_data_Test1 extends javax.servlet.http.HttpServlet { 
	
	ServletOutputStream out = null;
	String header_id = "";	
	Statement stmt,stmt1,stmt2;
	public ResultSet rs,rs1,rs2;
	String m_x_sql = null;
	public String getElementValue(Element parent,String label) {
		return getCharacterDataFromElement((Element)parent.getElementsByTagName(label).item(0));
	}
	
	public String getCharacterDataFromElement(Element e) {
						try {
							Node child = e.getFirstChild();
							if(child instanceof CharacterData) {
								CharacterData cd = (CharacterData) child;
								return cd.getData();
							}
						}
						catch(Exception ex) {
							
						}
						return " ";
					} //private String getCharacterDataFromElement
	
	public  void service(HttpServletRequest req, HttpServletResponse res)  throws IOException { 
		
				
		
		try { 
			
			
			
			LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods(); 
			String m_html_client_url=m_sn_methods.html_client_url.trim(); 
			String m_class_url=m_sn_methods.servlet_client_url.trim()+":"+m_sn_methods.client_t3_port.trim(); 
			String m_fschema_name=m_sn_methods.client_name.trim();
			String header_name=m_sn_methods.header_name.trim();
			java.sql.Connection conn = m_sn_methods.met_user_validate(req);
			res.setStatus(HttpServletResponse.SC_OK); 
			res.setContentType("text/html"); 
			out = res.getOutputStream(); 
			/*********************************************************/
			String  m_dash_id = req.getParameter("dash_id");
			String  m_draw_exc_section = null;
			String m_schema_name = m_sn_methods.schema_name;
			stmt = conn.createStatement ();
			stmt1= conn.createStatement();
			stmt2=conn.createStatement();
			//out.println(m_dash_id);
			if (true){
				
				
				PreparedStatement pstmt = conn.prepareStatement("   SELECT  B.DRAW_EXC_SECTION "+
					"   FROM    "+m_schema_name+".DH_DASH_GRAPH_MAIN A,"+
					"           "+m_schema_name+". DH_REF_GRAPH_TYPES B "+
					"    WHERE   A.GRAPH_TYPE_ID =B.GRAPH_TYPE_ID "+
					"    AND  DASH_ID ='"+m_dash_id+"' "
					); 
				pstmt.setString(1, m_dash_id);
				rs1 = pstmt.executeQuery();
				while(rs1.next()){
					m_draw_exc_section = rs1.getString(1);
				}
				
				if ( m_draw_exc_section != null &&  m_draw_exc_section.equals("MULTI_CHARTS")){
					res.setContentType("text/xml");
					
					//out.print("<graph  bgColor='f1f1f1' outCnvBaseFontColor='666666' caption='Overdue' xAxisName='Year' yAxisName='Amount'   showNames='1' showValues='0'   areaAlpha='70'  numVDivLines='10' showAlternateVGridColor='1' AlternateVGridColor='e1f5ff' alternateVGridAlpha='20' showAlternateHGridColor='1' alternateHGridColor='F5F6F7' divLineColor='e1f5ff' vdivLineColor='e1f5ff'  baseFontColor='666666' canvasBorderThickness='1'   formatNumberScale='1' decimalPrecision='1' limitsdecimalPrecision='0' divlinedecimalPrecision='0'>");
					out.print("<graph ");
					pstmt = conn.prepareStatement(" SELECT A.DASH_ID,A.PROPERTY_NAME, A.PROPERTY_VALUE "+
						" FROM   "+m_schema_name+".DH_GRAPH_MAIN_PROPERTY A "+
						" WHERE  DASH_ID = '"+m_dash_id+"' "
						); 
					pstmt.setString(1, m_dash_id);
					rs1 = pstmt.executeQuery();
					while(rs1.next()){
						out.print(" "+rs1.getString(2)+"='"+rs1.getString(3)+"' ");							
					}
					
					out.print(">");
					
					rs1.close();
					pstmt.close();
					
					
					
					
					
					out.print("<categories>");
					
					rs = stmt.executeQuery(  " SELECT A.DASH_ID, A.SQL_TYPE, "+
						" TRIM(A.SQL_STR) "+
						" FROM "+m_schema_name+".DH_GRAPH_SQL A "+
						" WHERE DASH_ID = '"+m_dash_id+"' "+
						" AND SQL_TYPE  = 'X_1_SQL' ");
					
					while(rs.next()){
						
						m_x_sql = rs.getString(3);
						//--draw X asis -------
						pstmt = conn.prepareStatement(m_x_sql); 
						rs1 = pstmt.executeQuery();
						
						while(rs1.next()){
							out.print("<category name='"+rs1.getString(1)+"'/>");
						}
						rs1.close();
						pstmt.close();
						
					}
					
					out.print("</categories>");
					
					String color = "";
					int count = 0;
					
					
					rs = stmt.executeQuery(  " SELECT A.DASH_ID, A.SQL_TYPE, "+
						" TRIM(A.SQL_STR) "+
						" FROM  "+m_schema_name+".DH_GRAPH_SQL A "+
						" WHERE DASH_ID = '"+m_dash_id+"' "+
						" AND   SQL_TYPE  LIKE 'DATA%' ");
					
					while(rs.next()){
						
						//out.print("<dataset seriesName='2012' color='008ED6' areaBorderColor='7EB7C7' areaBorderThickness='2' areaAlpha='40'>");
						out.print("<dataset");
						
						pstmt = conn.prepareStatement(" SELECT A.DASH_ID, A.SQL_TYPE, A.PROPERTY_NAME, A.PROPERTY_VALUE "+
							" FROM   "+m_schema_name+".DH_GRAPH_SQL_PROPERTY A "+
							" WHERE  DASH_ID = '"+m_dash_id+"' "+
							" AND    SQL_TYPE = '"+m_dash_id+"' " ); 
						pstmt.setString(1, rs.getString(1));
						pstmt.setString(2, rs.getString(2));
						rs1 = pstmt.executeQuery();
						while(rs1.next()){
							out.print(" "+rs1.getString(3)+"='"+rs1.getString(4)+"' ");							
						}
						
						out.print(">");
						rs1.close();
						pstmt.close();
						
						pstmt = conn.prepareStatement(rs.getString(3)); 
						rs1 = pstmt.executeQuery();
						while(rs1.next()){
							
							out.print("<set value='"+rs1.getString(1)+"'/>");
						}
						rs1.close();
						pstmt.close();
						out.print("</dataset>");
						
					}
					
					out.print("</graph>");
					
				} else if ( m_draw_exc_section != null &&  m_draw_exc_section.equals("CHART2")){  //-----------------------------------------------------------------
					res.setContentType("text/xml");
					
					out.print("<graph ");
					pstmt = conn.prepareStatement(" SELECT A.DASH_ID,A.PROPERTY_NAME, A.PROPERTY_VALUE "+
						" FROM   "+m_schema_name+".DH_GRAPH_MAIN_PROPERTY A "+
						" WHERE  DASH_ID = '"+m_dash_id+"' "
						); 
					pstmt.setString(1, m_dash_id);
					rs1 = pstmt.executeQuery();
					while(rs1.next()){
						out.print(" "+rs1.getString(2)+"='"+rs1.getString(3)+"' ");							
					}
					
					out.print(">");
					
					rs1.close();
					pstmt.close();
					
					
					rs = stmt.executeQuery(  " SELECT A.DASH_ID, A.SQL_TYPE, "+
						" TRIM(A.SQL_STR) "+
						" FROM  "+m_schema_name+".DH_GRAPH_SQL A "+
						" WHERE DASH_ID = '"+m_dash_id+"' "+
						" AND   SQL_TYPE  LIKE 'DATA%' ");
					
					while(rs.next()){
						
						//out.print("<dataset seriesName='2012' color='008ED6' areaBorderColor='7EB7C7' areaBorderThickness='2' areaAlpha='40'>");
						
						/*
							pstmt = conn.prepareStatement(" SELECT A.DASH_ID, A.SQL_TYPE, A.PROPERTY_NAME, A.PROPERTY_VALUE "+
																			" FROM   "+m_schema_name+".DH_GRAPH_SQL_PROPERTY A "+
																			" WHERE  DASH_ID =? "+
																			" AND    SQL_TYPE = ? " ); 
							pstmt.setString(1, rs.getString(1));
							pstmt.setString(2, rs.getString(2));
							rs1 = pstmt.executeQuery();
							while(rs1.next()){
									out.print(" "+rs1.getString(3)+"='"+rs1.getString(4)+"' ");							
							}
					
						out.print(">");
						rs1.close();
						pstmt.close();
						*/
						
						pstmt = conn.prepareStatement(rs.getString(3)); 
						rs1 = pstmt.executeQuery();
						while(rs1.next()){
							
							out.print("<set label='"+rs1.getString(1)+"'  value='"+rs1.getString(2)+"'/>");
						}
						rs1.close();
						pstmt.close();
						
						
					}
					
					
					out.print("	<styles>");
					
					out.print("	<definition>");
					
					out.print("	<style type=\"font\" name=\"CaptionFont\" size=\"15\" color=\"666666\" />");
					
					out.print("	<style type=\"font\" name=\"SubCaptionFont\" bold=\"0\" />");
					
					out.print("	</definition>");
					
					out.print("	<application>");
					
					out.print("	<apply toObject=\"caption\" styles=\"CaptionFont\" />");
					
					out.print("	<apply toObject=\"SubCaption\" styles=\"SubCaptionFont\" />");
					
					out.print("	</application>");
					
					out.print("</styles>");
					
					
					out.print("</graph>");
					
				} else if ( m_draw_exc_section != null &&  m_draw_exc_section.equals("CHART3")){  //-----------------------------------------------------------------
					res.setContentType("text/html");
					String m_caption = null;
					String m_no_of_head = "1";
					
					
					
					pstmt = conn.prepareStatement(" SELECT A.GRAPH_HEIGHT, A.GRAPH_WIDHT "+
						" FROM   "+m_schema_name+".DH_DASH_GRAPH_MAIN A "+
						" WHERE  DASH_ID = '"+m_dash_id+"' "
						); 
					pstmt.setString(1, m_dash_id);
					rs1 = pstmt.executeQuery();
					while(rs1.next()){
						out.print("<DIV style='height:"+rs1.getString(1)+"px;width:"+rs1.getString(2)+"px; overflow:auto; border-width: 1px;  border-style: solid; border-color: #11088e;' >");
						
					}
					rs1.close();
					pstmt.close();
					
					//get main property
					pstmt = conn.prepareStatement("   SELECT A.DASH_ID, A.PROPERTY_NAME, A.PROPERTY_VALUE "+
						"   FROM   "+m_schema_name+".DH_GRAPH_MAIN_PROPERTY A "+
						"   WHERE  DASH_ID = '"+m_dash_id+"' "
						); 
					pstmt.setString(1, m_dash_id);
					rs1 = pstmt.executeQuery();
					while(rs1.next()){
						
						if(rs1.getString(2).toString().toUpperCase().equals("CAPTION")){
							m_caption = rs1.getString(3); 
						}else if(rs1.getString(2).toString().toUpperCase().equals("NOOFHEAD")){
							m_no_of_head = rs1.getString(3); 
						}
						
						
					}
					rs1.close();
					pstmt.close();
					
					
					if (m_no_of_head!=null && m_no_of_head.equals("2")){
						out.print("<DIV class=\"doubleheadgridTable\" >");
					}else{
						out.print("<DIV class=\"gridTable\" >");	
					}		
					
					
					out.print("<TABLE  ");
					pstmt = conn.prepareStatement(" SELECT A.DASH_ID,A.PROPERTY_NAME, A.PROPERTY_VALUE "+
						" FROM   "+m_schema_name+".DH_GRAPH_MAIN_PROPERTY A "+
						" WHERE  DASH_ID = '"+m_dash_id+"' "
						); 
					pstmt.setString(1, m_dash_id);
					rs1 = pstmt.executeQuery();
					while(rs1.next()){
						out.print(" "+rs1.getString(2)+"='"+rs1.getString(3)+"' ");							
					}
					
					out.print(">");
					
					if(m_caption!=null){
						out.print("<caption>"+m_caption+"</caption>");	
					}
					
					rs1.close();
					pstmt.close();
					
					//-----HEADER CREATION -------------------------------------------------------------
					pstmt = conn.prepareStatement("   SELECT A.DASH_ID, A.PROPERTY_NAME, A.PROPERTY_VALUE "+
						"   FROM   "+m_schema_name+".DH_GRAPH_MAIN_PROPERTY A "+
						"   WHERE  DASH_ID = '"+m_dash_id+"' "+
						"   AND    UPPER(PROPERTY_NAME) LIKE UPPER('HEADER%NAME') "+
						"   ORDER BY 2 " ); 
					pstmt.setString(1, m_dash_id);
					rs1 = pstmt.executeQuery();
					boolean m_flag_1 = rs1.next();
					if(m_flag_1){
						out.print("<TR>"); 
						
						while(m_flag_1){
							
							
							PreparedStatement pstmt1  = conn.prepareStatement("   SELECT A.DASH_ID, A.PROPERTY_NAME, A.PROPERTY_VALUE "+
								"   FROM   "+m_schema_name+".DH_GRAPH_MAIN_PROPERTY A "+
								"   WHERE  DASH_ID = '"+m_dash_id+"' "+
								"   AND    UPPER(PROPERTY_NAME) LIKE UPPER(?||'%COLSPAN') "+
								"   ORDER BY 2 " );
							pstmt1.setString(1, m_dash_id);
							pstmt1.setString(2, rs1.getString(2));
							rs2 = pstmt1.executeQuery();
							
							if(rs2.next()){
								
								out.print("<TD COLSPAN=\""+rs2.getString(3)+"\">"+rs1.getString(3)+"</TD>"); 
								
							}else{	
								
								out.print("<TD>"+rs1.getString(3)+"</TD>"); 
								
							}	
							m_flag_1= rs1.next();
							
						}
						out.print("</TR>"); 
					}
					rs1.close();
					pstmt.close();
					//------[END]HEADER CREATION --------------------------------------------------------
					rs = stmt.executeQuery(  " SELECT A.DASH_ID, A.SQL_TYPE, "+
						" TRIM(A.SQL_STR) "+
						" FROM  "+m_schema_name+".DH_GRAPH_SQL A "+
						" WHERE DASH_ID = '"+m_dash_id+"' "+
						" AND   SQL_TYPE  LIKE 'DATA%' ");
					
					while(rs.next()){
						
						
						pstmt = conn.prepareStatement(rs.getString(3)); 
						rs1 = pstmt.executeQuery();
						
						out.print("<TR>");   
						int m_max_col_count=rs1.getMetaData().getColumnCount();
						
						for(int m_col_count=1;m_col_count<=m_max_col_count;m_col_count++){
							
							String m_column_name = rs1.getMetaData().getColumnName(m_col_count);
							
							
							out.print("<TD>"+ m_column_name+"</TD>");
							
							
						}out.print("</tr>");
						
						
						while(rs1.next()){
							
							
							out.print("<TR>"); 
							
							for(int j=1;j<=m_max_col_count;j++){ 
								
								
								out.print("<TD>"+rs1.getString(j)+"</TD>");
								
								
							}
							out.print("</TR>");
							
							
						}
						rs1.close();
						pstmt.close();
						
						
					}
					
					out.print("</TABLE>");
					out.print("</div>");
					out.print("</div>");
					
					
				}	//chart3
				else if ( m_draw_exc_section != null &&  m_draw_exc_section.equals("RSS")){  //-----------------------------------------------------------------
					String m_graph_swf_location = null;
					String desc=null;
					String m_caption = "";
					
					
					
					pstmt = conn.prepareStatement(
						" SELECT DASH_ID,GRAPH_SWF_LOCATION,GRAPH_HEIGHT,GRAPH_WIDHT,DRAW_EXC_SECTION "+
						" FROM  "+m_schema_name+"DH_DASH_GRAPH_MAIN A,"+
						"       "+m_schema_name+"DH_REF_GRAPH_TYPES B "+
						" WHERE  ACTIVE_STATUS='Y' "+
						" AND    A.GRAPH_TYPE_ID = B.GRAPH_TYPE_ID "+
						" AND    DASH_ID = '"+m_dash_id+"' "
						);
					pstmt.setString(1, m_dash_id);
					rs1 = pstmt.executeQuery();
					
					while(rs1.next()){
						
						m_graph_swf_location = rs1.getString(2);
						out.print("<DIV  style='height:"+rs1.getString(3)+"px;width:"+rs1.getString(4)+"px; overflow:auto; border-width: 1px;  border-style: solid; border-color: #ad0f0f;' >");
					}
					rs1.close();
					pstmt.close();
					
					
					
					
					//get main property
					pstmt = conn.prepareStatement("   SELECT A.DASH_ID, A.PROPERTY_NAME, A.PROPERTY_VALUE "+
						"   FROM   "+m_schema_name+".DH_GRAPH_MAIN_PROPERTY A "+
						"   WHERE  DASH_ID = '"+m_dash_id+"' "
						); 
					pstmt.setString(1, m_dash_id);
					rs1 = pstmt.executeQuery();
					while(rs1.next()){
						
						if(rs1.getString(2).toString().toUpperCase().equals("CAPTION")){
							m_caption = rs1.getString(3); 
						}
						
						
					}
					rs1.close();
					pstmt.close();
					
					
					
					
					
					out.print("<DIV class=\"gridRSS\" >");
					out.print("<TABLE>");
					//caption----------------------------------------------
					
					
					if(m_caption!=null){
						out.print("<caption>"+m_caption+"</caption>");	
					}
					
					
					//----end caption---------------------------------------------
					
					
					try
					{
						DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
						URL u = new URL(m_graph_swf_location);   // feed address
						Document doc = builder.parse(u.openStream());
						String title;
						NodeList nodes = doc.getElementsByTagName("item");
						
						for(int i=0;i<nodes.getLength();i++) {
							Element element = (Element)nodes.item(i);
							
							out.print("<TABLE class=\"rssdate\"  >");
							
							out.print("<TR>");
							//out.println("Title: " + getElementValue(element,"title"));
							out.print("<TD> <U><a  href=\""+getElementValue(element,"link")+"\" target=\"_blank\" >"+getElementValue(element,"title")+"</a></U>");
							out.print("</TD>");
							out.print("</TR>");
							
							out.print("<TD >");
							out.print(""+getElementValue(element,"pubDate")+"</TD>");
							out.print("</TR>");
							
							out.print("<TD >");
							out.print(""+getElementValue(element,"description")+"</TD>");
							out.print("</TR>");
							
							
							out.print("</TABLE>");
							// out.println("Link: " + getElementValue(element,"link"));
							//	out.println("<BR>");	
							//out.println("Publish Date: " + getElementValue(element,"pubDate"));
							//	 out.println("<BR>");	
							//out.println("author: " + getElementValue(element,"dc:creator"));
							//	out.println("<BR>");	
							//out.println("comments: " + getElementValue(element,"wfw:comment"));
							//	out.println("<BR>");	
							// desc=getElementValue(element,"description");
							//	out.println("<BR>");	
							//out.println("description: " + desc);
							//	out.println("<BR>");	
							//out.println();
							//   out.println("<BR>");	
							
						}//for
					}//try
					catch(Exception ex) {
						ex.printStackTrace();
						out.println(" tested"+ex);	
					}
						
					out.print("</TABLE>");
					out.print("</DIV");
					out.print("</DIV");
					
					
					
					
				}
				else if (m_chksql.trim().equals("CHART1")){
				
				/*String batchNo = req.getParameter("data_val");
				
				rs = stmt.executeQuery("SELECT A.BATCH_NO,A.SERIAL_NO,TO_CHAR(A.RECEIVED_DATE,'DD'),TO_CHAR(A.RECEIVED_DATE,'MM'),TO_CHAR(A.RECEIVED_DATE,'YYYY'), "+
					" NVL("+m_schema_name+".FA_CO_GET_CURRENCY_SYMBOL(A.CURR_CODE),'-'), "+
					" A.TOTAL_BATCH_AMOUNT, NVL(A.NO_OF_CHEQUES,0),ISSUED_BY,SETTLE_MODE,A.CURR_CODE, "+
					" NVL(A.ADDED_NO_OF_CHEQUES,0),A.BALANCE_BATCH_AMOUNT,(A.TOTAL_BATCH_AMOUNT - A.BALANCE_BATCH_AMOUNT) ADDED_AMOUNT,A.CLIENT_CODE , NVL(" + m_schema_name + ".FA_GET_CLIENT_FULL_NAME(A.CLIENT_CODE),'-'), A.FACILITY_NO"+
					" FROM " + m_schema_name + ".FA_OP_PRO_CMS_BATCH A WHERE A.ACTIVE_STATUS='Y' AND A.BATCH_NO='" + batchNo + "'");
				
				*/
				
				
				out.print("<graph caption='Monthly Unit Sales' xAxisName='Month' yAxisName='Units' showNames='1' decimalPrecision='0' formatNumberScale='0'>");
				out.print("<set name='Jan' value='462' color='AFD8F8' />");
				out.print("<set name='Feb' value='857' color='F6BD0F' />");
				out.print("<set name='Mar' value='671' color='8BBA00' />");
				out.print("<set name='Apr' value='494' color='FF8E46' />");
				out.print("<set name='May' value='761' color='008E8E' />");
				out.print("<set name='Jun' value='960' color='D64646' />");
				out.print("<set name='Jul' value='629' color='8E468E' />");
				out.print("<set name='Aug' value='622' color='588526' />");
				out.print("<set name='Sep' value='376' color='B3AA00' />");
				out.print("<set name='Oct' value='494' color='008ED6' />");
				out.print("<set name='Nov' value='761' color='9D080D' />");
				out.print("<set name='Dec' value='970' color='A186BE' />");
				out.print("</graph>");
				
				
				/*out.print("<DATA>");
				while(rs.next()){
					out.print("<ITEM>");
					out.print("<R1>"+rs.getString(1)+"</R1>"); //BATCH NO
					out.print("<R2>"+rs.getString(2)+"</R2>"); //SERIAL NO
					out.print("<R3>"+rs.getString(3)+"</R3>");//DD
					out.print("<R4>"+rs.getString(4)+"</R4>");//MM
					out.print("<R5>"+rs.getString(5)+"</R5>");//YY
					out.print("<R6>"+rs.getString(6)+"</R6>");//CURR SYM
					
					BigDecimal amount = new BigDecimal("0.00");
					amount = rs.getBigDecimal(7).setScale(2, BigDecimal.ROUND_HALF_EVEN);	
					BigDecimal balAmount = new BigDecimal("0.00");
					balAmount = rs.getBigDecimal(13).setScale(2, BigDecimal.ROUND_HALF_EVEN);
					BigDecimal addedAmount = new BigDecimal("0.00");
					addedAmount = rs.getBigDecimal(14).setScale(2, BigDecimal.ROUND_HALF_EVEN);
					
					out.print("<R7>"+amount+"</R7>");//BATCH AMOUNT
					out.print("<R8>"+rs.getString(8)+"</R8>");//NO OF CHQ
					out.print("<R9>"+rs.getString(9)+"</R9>");//ISSUED_BY
					out.print("<R10>"+rs.getString(10)+"</R10>");//SETTLE_MODE
					out.print("<R11>"+rs.getString(11)+"</R11>");//CURR CODE
					out.print("<R12>"+rs.getInt(12)+"</R12>");//ADDED CHEQUES
					out.print("<R13>"+balAmount+"</R13>");//BALANCE AMOUNT
					out.print("<R14>"+addedAmount+"</R14>");//ADDED AMOUNT
					out.print("<R15>"+rs.getString(15)+"</R15>");//CLIENT CODE
					out.print("<R16>"+rs.getString(16)+"</R16>");//CLIENT  NAME
					out.print("<R17>"+rs.getString(17)+"</R17>");//FACILITY NO
					out.print("</ITEM>");
				}
				out.print("</DATA>");*/
				
				
			}
			
				
				//RSS	
				else if ( m_draw_exc_section != null &&  m_draw_exc_section.equals("CSE")){  //-----------------------------------------------------------------
					
					String m_graph_swf_location = null;
					String desc=null;
					String m_caption = "";
					
					
					pstmt = conn.prepareStatement(
						" SELECT DASH_ID,GRAPH_SWF_LOCATION,GRAPH_HEIGHT,GRAPH_WIDHT,DRAW_EXC_SECTION "+
						" FROM  "+m_schema_name+"DH_DASH_GRAPH_MAIN A,"+
						"       "+m_schema_name+"DH_REF_GRAPH_TYPES B "+
						" WHERE  ACTIVE_STATUS='Y' "+
						" AND    A.GRAPH_TYPE_ID = B.GRAPH_TYPE_ID "+
						" AND    DASH_ID = '"+m_dash_id+"' "
						);
					pstmt.setString(1, m_dash_id);
					rs1 = pstmt.executeQuery();
					
					while(rs1.next()){
						
						m_graph_swf_location = rs1.getString(2);
						out.print("<DIV  style='height:"+rs1.getString(3)+"px;width:"+rs1.getString(4)+"px; overflow:auto; border-width: 1px;  border-style: solid; border-color: #11088e;' >");
					}
					rs1.close();
					pstmt.close();
					
					
					
					
					//get main property
					pstmt = conn.prepareStatement("   SELECT A.DASH_ID, A.PROPERTY_NAME, A.PROPERTY_VALUE "+
						"   FROM   "+m_schema_name+".DH_GRAPH_MAIN_PROPERTY A "+
						"   WHERE  DASH_ID = '"+m_dash_id+"' "
						); 
					pstmt.setString(1, m_dash_id);
					rs1 = pstmt.executeQuery();
					while(rs1.next()){
						
						if(rs1.getString(2).toString().toUpperCase().equals("CAPTION")){
							m_caption = rs1.getString(3); 
						}
						
						
					}
					rs1.close();
					pstmt.close();
					
					
					
					
					
					out.print("<DIV class=\"gridTable\" >");
					out.print("<TABLE>");
					//caption----------------------------------------------
					
					
					if(m_caption!=null){
						out.print("<caption>"+m_caption+"</caption>");	
					}
					
					
					//----end caption---------------------------------------------
					
					
					try {
						
						
						File file = new File("my.html");
						FileWriter fr = new FileWriter(file);
						BufferedWriter br  = new BufferedWriter(fr);
						
						URL google = new URL(m_graph_swf_location);
						URLConnection yc = google.openConnection();
						
						BufferedReader in = new BufferedReader(new InputStreamReader(yc
							.getInputStream()));
						String inputLine;
						
						while ((inputLine = in.readLine()) != null) {
							//System.out.println(inputLine);
							//br.write(new Scanner(System.in).nextLine());
							br.write(inputLine);
							
						}
						
						in.close();
						
						//File file = new File("/path/to/file.html");
						org.jsoup.nodes.Document document = Jsoup.parse(file, "UTF-8");
						org.jsoup.nodes.Element something = document.select("div[id=share-price-list]").first();
						out.println(""+something);
						
						
						//out.print("</TABLE>");
						out.print("</DIV>");			
						
						
					} catch (Exception e) {
						e.printStackTrace();
					}	
					
				}//CSE
				
			}else {
				out.println("Undefined");
			}
			
			/*out.println("</form>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/leasing_drill_down.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/validate.js'></SCRIPT>"); 
			out.println("<SCRIPT language1.2='JavaScript' src='"+m_html_client_url+"/ajax_data_gateway.js'></SCRIPT>"); 
			out.println("</body>"); 
			out.println("</html>"); 
			out.flush();**/
		}
		catch (Exception ex) {
			try{out.println("Error:"+ex.toString());}catch(Exception e){}
		}
		finally{
			if(out!=null){try{out.close();  }catch(Exception e){}}
		}
	} 
}