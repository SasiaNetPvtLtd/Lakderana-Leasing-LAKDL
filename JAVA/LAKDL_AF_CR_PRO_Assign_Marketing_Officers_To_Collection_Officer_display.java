/*
 * Created By       :   Samitha Kulatilaka
 * Created Date     :   2012-01-25
 * 
 * Screen Name      :   Collection Officer Assignment
 */

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
// import sun.misc.BASE64Decoder; 
import java.util.*;

// import oracle.jdbc.driver.*;

public class LAKDL_AF_CR_PRO_Assign_Marketing_Officers_To_Collection_Officer_display extends HttpServlet {
    
    Connection conn;
    Statement stmt;
    java.text.NumberFormat nf,nf1;
    public ResultSet rs;
    public String m_chksql;
    String sql;
    
    ServletOutputStream out;
    
    public synchronized void service(HttpServletRequest request, HttpServletResponse response) {
        
        try {
            
            out = response.getOutputStream();
            
            //************************************************************	
            LAKDL_AF_MK_CO_methods   CO_methods = new LAKDL_AF_MK_CO_methods();
            LAKDL_AF_CO_conn_methods con_method = new LAKDL_AF_CO_conn_methods();
            
            conn                            = con_method.met_user_validate(request); 
            String m_html_client_url        = con_method.html_client_url;
            String m_schema_name            = con_method.schema_name;
            String m_servlet_client_url     = con_method.servlet_client_url;
            String m_client_name            = con_method.client_name;
            String m_client_t3_port         = con_method.client_t3_port;
            String m_username               = con_method.username;
            String header_name              = con_method.header_name;
            String m_class_url              = con_method.servlet_client_url.trim()+":"+con_method.client_t3_port.trim(); 
            String m_fschema_name           = con_method.client_name.trim();
            
            CallableStatement callstmt1  = null;
            
            
            //************************************************************
            
            nf = java.text.NumberFormat.getInstance(Locale.US);   
            nf.setMinimumFractionDigits(0);
            
            nf1 = java.text.NumberFormat.getInstance(Locale.US);   
            nf1.setMinimumFractionDigits(6);
            
            response.setStatus(HttpServletResponse.SC_OK);
            response.setContentType("text/html");
            
            m_chksql = request.getParameter("chksql").trim();
            
            stmt     = conn.createStatement ();
            
            
            if (m_chksql.equals("idle")) {
                out.println("idle");
            }
            
            else if (m_chksql.equals("main_page")) {
                
                String m_app_no = request.getParameter("app_no");
                String m_inq_no = request.getParameter("inquiry_no");
                String m_pri_no = request.getParameter("pricing_no");
                String m_screen = request.getParameter("screen_type");
                String amount   = request.getParameter("amount");
                String m_ter_no = request.getParameter("TER_NO");
                String m_ter_am = request.getParameter("TER_AMT");
                String m_ter_ty = request.getParameter("TER_TYPE");
                if (m_ter_no==null){
                    m_ter_no="";
                }
                if (m_ter_am==null){
                    m_ter_am="";
                }
                if (m_ter_ty==null){
                    m_ter_ty="";
                }
                
                out.println("<html>");
                out.println("   <head>");
                out.println("       <title>Asset Financing System</title>");
                // out.println("       <script type=\"text/javascript\" src=\"" + m_html_client_url + "/valuation.js\"></script>");
                // out.println("       <script type=\"text/javascript\" src=\"" + m_html_client_url + "/validate_v1.js\"></script>");
                out.println("       <script type=\"text/javascript\" src=\"" + m_html_client_url + "/validate.js\"></script>");
                out.println("       <script type=\"text/javascript\" src=\"" + m_html_client_url + "/ajax_data_gateway.js\"></script>");
                out.println("       <link rel=\"stylesheet\" type=\"text/css\" href=\"" + m_html_client_url + "/css/Asset_Financing_System.css\" />");
                
                out.println("       <script type=\"text/javascript\">");
                
                out.println("           var g_help_type = '';");
                out.println("           var g_sql_validations_type = '';");
                out.println("           var m_sql = '';");
                out.println("           var m_criteria = '';");
                out.println("           var m_deleted_marketing_officers = new Array();");
                
                
                out.println("           function MyDialog() {");
                out.println("               this.valout = new Array(10);");
                out.println("           }");
                
                
                out.println("           function HelpBox(Start, End, Hid_No, Max) {");
                out.println("               oBj = new MyDialog();");
                out.println("               oBj.valout[1] = ' ';");
                out.println("               oBj.valout[2] = ' ';");
                out.println("               oBj.valout[3] = ' ';");
                
                // out.println("               popupwin = window.showModalDialog(servlet_client_url + ':' + client_t3_port + '/' + client_name + 'AF_RE_Help_Servlet?class_in=' + client_name + 'AF_RE_help_select' +");
                out.println("               popupwin = window.showModalDialog(servlet_client_url + ':' + client_t3_port + '/' + client_name + 'AF_MAS_Help_Servlet?class_in=' + client_name + 'AF_MAS_help_select2' +");
                out.println("                       '&Sql_in=' + m_sql +");
                out.println("                       '&Start_in=' + Start +");
                out.println("                       '&End_in=' + End +");
                out.println("                       '&Crit_In=' + m_criteria +");
                out.println("                       '&Hid_No=' + Hid_No, oBj, 'dialogWidth: 25em; dialogHeight: 18em; center: yes; status: no;');");
                
                out.println("               if (oBj.valout[1] != ' ') {");
                out.println("                   if (oBj.valout[1] != 'Close') {");
                out.println("                       if (oBj.valout[1] != 'Prev') {");
                out.println("                           if (oBj.valout[1] != 'Next') {");
                out.println("                               if (g_help_type != '') {");
                out.println("                                   help_update_value_assign();");
                out.println("                               }");
                out.println("                           }");
                out.println("                           else {");
                out.println("                               Next(oBj.valout[2], oBj.valout[3], Hid_No);");
                out.println("                               return false;");
                out.println("                           }");
                out.println("                       }");
                out.println("                       else {");
                out.println("                           Prev(oBj.valout[2], oBj.valout[3], Hid_No);");
                out.println("                       }");
                out.println("                   }");
                out.println("                   else {");
                out.println("                       clear_data();");
                out.println("                   }");
                out.println("               }"); 
                out.println("               if (oBj.valout[2] == ' ') {");
                out.println("                   Close();"); 
                out.println("               }");
                out.println("           }");
                
                
                out.println("           function Prev(Start, End, Hid_No) {");
                out.println("               HelpBox(Start, End, Hid_No);");
                out.println("           }");
                
                
                out.println("           function Next(Start, End, Hid_No) {");
                out.println("               HelpBox(Start, End, Hid_No);");
                out.println("           }");
                
                
                out.println("           function help_update(m_help_type) {");
                out.println("               g_help_type = m_help_type;");
                out.println("               var m_screen_mode = document.getElementById('HID_SCREEN_MODE').value;");
                
                out.println("               if (g_help_type == 'collection_officer') {");
                // out.println("                   m_sql = 'm_help_active_employee_list_sql';");
                // out.println("                   m_criteria = document.getElementById('TXT_COLLECTION_OFFICER').value + '@';");
                out.println("                   if (m_screen_mode == 'NEW') {");
                out.println("                       m_sql = 'm_help_Assign_Mkt_Col_Collection_Officer_New_sql';");
                out.println("                   }");
                out.println("                   else if (m_screen_mode == 'MOD') {");
                out.println("                       m_sql = 'm_help_Assign_Mkt_Col_Collection_Officer_Edit_sql';");
                out.println("                   }");
                out.println("                   m_criteria = document.getElementById('TXT_COLLECTION_OFFICER').value + '@';");
                out.println("               }");
                out.println("               else if (g_help_type == 'marketing_officer') {");
                out.println("                   if (document.getElementById('TXT_COLLECTION_OFFICER').value.length == 0) {");
                out.println("                       alert('Please select a collection officer.');");
                out.println("                       document.getElementById('TXT_COLLECTION_OFFICER').focus();");
                out.println("                       return false;");
                out.println("                   }");
                
                out.println("                   if (m_screen_mode == 'NEW') {");
                out.println("                       m_sql = 'm_help_Assign_Mkt_Col_Marketing_Officer_New_sql';");
                out.println("                       m_criteria = document.getElementById('TXT_MARKETING_OFFICER').value + '@';");
                out.println("                   }");
                out.println("                   else if (m_screen_mode == 'MOD') {");
                out.println("                       m_sql = 'm_help_Assign_Mkt_Col_Marketing_Officer_Edit_sql';");
                out.println("                       m_criteria = document.getElementById('TXT_MARKETING_OFFICER').value + '@' + document.getElementById('TXT_COLLECTION_OFFICER').value + '@';");
                out.println("                   }");
                out.println("               }");
                
                out.println("               HelpBox('1', '10', '0');"); 
                out.println("           }");
                
                
                out.println("           function help_update_value_assign() {");
                
                out.println("               if (g_help_type == 'collection_officer') {");
                out.println("                   document.getElementById('TXT_COLLECTION_OFFICER').value = oBj.valout[2];");
                out.println("                   document.getElementById('TXT_COLLECTION_OFFICER_NAME').value = oBj.valout[3] + ' ' + oBj.valout[4] + ' ' + oBj.valout[5];");
                out.println("                   if (document.getElementById('HID_SCREEN_MODE').value == 'MOD') {");
                out.println("                       m_deleted_marketing_officers = new Array();");
                
                out.println("                       m_url = '" + m_class_url + "/" + m_fschema_name + "AF_MAS_sql_validations4?chksql=m_prime_chk_get_marketing_officer_list&collection_officer=' + document.getElementById('TXT_COLLECTION_OFFICER').value;");
                out.println("                       g_sql_validations_type = 1;");
                out.println("                       load_interface(m_url, 'XML');");
                out.println("                   }");
                
                
                out.println("               }");
                out.println("               else if (g_help_type == 'marketing_officer') {");
                out.println("                   document.getElementById('TXT_MARKETING_OFFICER').value = oBj.valout[2];");
                out.println("                   document.getElementById('TXT_MARKETING_OFFICER_NAME').value = oBj.valout[3] + ' ' + oBj.valout[4] + ' ' + oBj.valout[5];");
                out.println("               }");
                
                out.println("           }");
                
                
                out.println("           function get_vector(data_vec) {");
                out.println("               if ((g_sql_validations_type == 1) && (data_vec.length > 0)) {");
                out.println("                   document.getElementById('HID_MARKETING_OFFICER_COUNT').value = 0;");
                out.println("                   document.getElementById('DIV_MARKETING_OFFICER').innerHTML = '';");
                
                out.println("                   var m_row_count = data_vec.length / 2;");
                out.println("                   var j = 0;");
                out.println("                   for (var i = 1; i <= m_row_count; i++) {");
                out.println("                       add_marketing_officer('edit_mode', data_vec[j], data_vec[j + 1]);");
                out.println("                       j += 2;");
                out.println("                   }");
                out.println("               }");
                out.println("           }");
                
                
                out.println("           function add_marketing_officer_to_list() {");
                
                out.println("               var m_row_count = parseInt(document.getElementById('HID_MARKETING_OFFICER_COUNT').value);");
                out.println("               var m_marketining_officer = document.getElementById('TXT_MARKETING_OFFICER').value");
                
                out.println("               if (m_marketining_officer.length == 0) {");
                out.println("                   alert('Please select a marketing officer to add.');");
                out.println("                   document.getElementById('TXT_MARKETING_OFFICER').focus();");
                out.println("                   return false;");
                out.println("               }");
                
                out.println("               for (var i = 1; i <= m_row_count; i++) {");
                out.println("                   var m_marketining_officer_in_loop = document.getElementById('HID_MARKETING_OFFICER_' + i).value;");
                out.println("                   if (m_marketining_officer_in_loop == m_marketining_officer) {");
                out.println("                       alert('Warning: The selected marketing officer has already been added to the list.');");
                out.println("                       return false;");
                out.println("                   }");
                out.println("               }");
                
                // In Edit Mode, When Adding A Marketing Officer Who Has Already Been Deleted From The Front End, Delete It From The Deleted Array
                out.println("               if (document.getElementById('HID_SCREEN_MODE').value == 'MOD') {");
                out.println("                   for (var i = 0; i < m_deleted_marketing_officers.length; i++) {");
                out.println("                       var m_marketining_officer_in_array = m_deleted_marketing_officers[i];");
                out.println("                       if (m_marketining_officer_in_array == m_marketining_officer) {");
                out.println("                           m_deleted_marketing_officers.splice(i, 1);");
                out.println("                           break;");
                out.println("                       }");
                out.println("                   }");
                out.println("               }");
                
                out.println("               add_marketing_officer();");
                
                // out.println("               if (g_help_type == 'collection_officer') {");
                // out.println("                   document.getElementById('TXT_COLLECTION_OFFICER').value = oBj.valout[2];");
                // out.println("                   document.getElementById('TXT_COLLECTION_OFFICER_NAME').value = oBj.valout[3] + ' ' + oBj.valout[4] + ' ' + oBj.valout[5];");
                // out.println("               }");
                // out.println("               else if (g_help_type == 'marketing_officer') {");
                // out.println("                   document.getElementById('TXT_MARKETING_OFFICER').value = oBj.valout[2];");
                // out.println("                   document.getElementById('TXT_MARKETING_OFFICER_NAME').value = oBj.valout[3] + ' ' + oBj.valout[4] + ' ' + oBj.valout[5];");
                // out.println("               }");
                
                out.println("           }");
                
                
                out.println("           function add_marketing_officer(m_mode, m_marketing_officer, m_marketing_officer_name) {");
                out.println("               var m_new_total_count = parseInt(document.getElementById('HID_MARKETING_OFFICER_COUNT').value) + 1;");
                out.println("               if (parseInt(document.getElementById('HID_MARKETING_OFFICER_COUNT').value) == 0) {");
                out.println("                   document.getElementById('DIV_MARKETING_OFFICER').innerHTML = '';");
                out.println("               }");
                out.println("               createDIV('DIV_MARKETING_OFFICER', m_new_total_count);");
                // out.println("               if (parseInt(document.getElementById('HID_MARKETING_OFFICER_COUNT').value) == 0) {");
                out.println("               if ((m_mode == 'delete_mode') || (m_mode == 'edit_mode')) {");
                out.println("                   document.getElementById('DIV_MARKETING_OFFICER' + m_new_total_count).innerHTML = '' + "); // New 2010-02-12
                out.println("                       '   <table width=\"100%\" border=\"0\"> ' + ");
                out.println("                       '       <tr bgcolor=\"#CCCCCC\"> ' + ");
                out.println("                       '           <td>' + ");
                out.println("                       '               <input type=\"hidden\" name=\"HID_MARKETING_OFFICER_' + m_new_total_count + '\" id=\"HID_MARKETING_OFFICER_' + m_new_total_count + '\" value=\"' + m_marketing_officer + '\" />' + ");
                out.println("                                       m_marketing_officer + ");
                out.println("                       '           </td>' + ");
                out.println("                       '           <td>' + ");
                out.println("                       '               <input type=\"hidden\" name=\"HID_MARKETING_OFFICER_NAME_' + m_new_total_count + '\" id=\"HID_MARKETING_OFFICER_NAME_' + m_new_total_count + '\" value=\"' + m_marketing_officer_name + '\" />' + ");
                out.println("                                       m_marketing_officer_name + ");
                out.println("                       '               <input class=\"but_input\" type=\"button\" name=\"BUT_DELETE_MARKETING_OFFICER_' + m_new_total_count + '\" id=\"BUT_DELETE_MARKETING_OFFICER_' + m_new_total_count + '\" value=\"Delete\" onclick=\"delete_marketing_officer(' + m_new_total_count + ');\" />' + ");
                out.println("                       '           </td>' + ");
                out.println("                       '       </tr> ' + ");
                out.println("                       '   </table> '; ");
                out.println("               }");
                // out.println("               if (m_mode != 'delete_mode') {");
                out.println("               else {");
                /* Table Body - 1st Row */
                out.println("                   document.getElementById('DIV_MARKETING_OFFICER' + m_new_total_count).innerHTML = '' + "); // New 2010-02-12
                out.println("                       '   <table width=\"100%\" border=\"0\"> ' + ");
                // out.println("                       '   <table align=\"left\" border=\"0\"> ' + ");
                out.println("                       '       <tr bgcolor=\"#CCCCCC\"> ' + ");
                out.println("                       '           <td>' + ");
                // out.println("                       '           <td style=\"width: 200px;\">' + ");
                out.println("                       '               <input type=\"hidden\" name=\"HID_MARKETING_OFFICER_' + m_new_total_count + '\" id=\"HID_MARKETING_OFFICER_' + m_new_total_count + '\" value=\"' + document.getElementById('TXT_MARKETING_OFFICER').value + '\" />' + ");
                out.println("                                       document.getElementById('TXT_MARKETING_OFFICER').value + ");
                out.println("                       '           </td>' + ");
                out.println("                       '           <td>' + ");
                // out.println("                       '           <td style=\"width: 300px;\">' + ");
                out.println("                       '               <input type=\"hidden\" name=\"HID_MARKETING_OFFICER_NAME_' + m_new_total_count + '\" id=\"HID_MARKETING_OFFICER_NAME_' + m_new_total_count + '\" value=\"' + document.getElementById('TXT_MARKETING_OFFICER_NAME').value + '\" />' + ");
                out.println("                                       document.getElementById('TXT_MARKETING_OFFICER_NAME').value + ");
                out.println("                       '               <input class=\"but_input\" type=\"button\" name=\"BUT_DELETE_MARKETING_OFFICER_' + m_new_total_count + '\" id=\"BUT_DELETE_MARKETING_OFFICER_' + m_new_total_count + '\" value=\"Delete\" onclick=\"delete_marketing_officer(' + m_new_total_count + ');\" />' + ");
                out.println("                       '           </td>' + ");
                // out.println("                       '           <td class=\"div_input\" align=\"center\" style=\"width: 50px;\"></td> ' + ");
                // out.println("                       '           <td class=\"div_input\" align=\"center\" style=\"width: 50px;\"></td> ' + ");
                // out.println("                       '           <td class=\"div_input\" align=\"center\" style=\"width: 75px;\"><b>Title</b></td> ' + ");
                // out.println("                       '           <td class=\"div_input\" align=\"center\" style=\"width: 125px;\"><b>Last Name</b></td> ' + ");
                // out.println("                       '           <td class=\"div_input\" align=\"center\" style=\"width: 75px;\"><b>Initials</b></td> ' + ");
                // out.println("                       '           <td class=\"div_input\" align=\"center\" style=\"width: 250px;\"><b>Name Denoted by Initials</b></td> ' + ");
                // out.println("                       '           <td class=\"div_input\" align=\"center\" style=\"width: 175px;\"><b>Designation</b></td> ' + ");
                out.println("                       '       </tr> ' + ");
                out.println("                       '   </table> '; ");
                out.println("               }");
                // out.println("               else {");
                // /* Table Body - Subsequent Rows */
                // out.println("                   document.getElementById('DIV_MARKETING_OFFICER' + m_new_total_count).innerHTML = '' + "); // New 2010-02-12
                // out.println("                       '   <table align=\"left\" border=\"0\"> ' + ");
                // out.println("                       '       <tr bgcolor=\"#CCCCCC\"> ' + ");
                // out.println("                       '           <td style=\"width: 200px;\">' + ");
                // out.println("                       '               <input type=\"hidden\" name=\"HID_MARKETING_OFFICER_' + m_new_total_count + '\" id=\"HID_MARKETING_OFFICER_' + m_new_total_count + '\" value=\"' + document.getElementById('TXT_MARKETING_OFFICER').value + '\" />' + ");
                // out.println("                       '               <div id=\"DIV_MARKETING_OFFICER_' + m_new_total_count + '\">' + document.getElementById('TXT_MARKETING_OFFICER').value + '</DIV>' + ");
                // out.println("                       '           </td>' + ");
                // out.println("                       '           <td style=\"width: 300px;\">' + ");
                // out.println("                       '               <input type=\"hidden\" name=\"HID_MARKETING_OFFICER_NAME_' + m_new_total_count + '\" id=\"HID_MARKETING_OFFICER_NAME_' + m_new_total_count + '\" value=\"' + document.getElementById('TXT_MARKETING_OFFICER_NAME').value + '\" />' + ");
                // out.println("                       '               <div id=\"DIV_MARKETING_OFFICER_NAME_' + m_new_total_count + '\">' + document.getElementById('TXT_MARKETING_OFFICER_NAME').value + '</DIV>' + ");
                // out.println("                       '           </td>' + ");
                // out.println("                       '       </tr> ' + ");
                // out.println("                       '   </table> '; ");
                // out.println("               }");
                out.println("               document.getElementById('HID_MARKETING_OFFICER_COUNT').value = m_new_total_count;");
                out.println("           }");
                
                
                out.println("           function delete_marketing_officer(m_selected_row_number) {");
                out.println("               var m_flag_clear = false;");
                out.println("               if ((parseInt(m_selected_row_number) > 0) && (confirm('Are you sure you want to delete?'))) {");
                out.println("                   var m_total_count = parseInt(document.getElementById('HID_MARKETING_OFFICER_COUNT').value);");
                out.println("                   document.getElementById('HID_MARKETING_OFFICER_COUNT').value = 0;");
                out.println("                   var m_count = 0;");
                out.println("                   var value01 = new Array();");
                out.println("                   var value02 = new Array();");
                /* Assign Values To Variables */
                out.println("                   for (var i = 1; i <= m_total_count; i++) {");
                out.println("                       if (i != m_selected_row_number) {");
                out.println("                           m_count = m_count + 1;");
                out.println("                           value01[m_count] = document.getElementById('HID_MARKETING_OFFICER_' + i).value;");
                out.println("                           value02[m_count] = document.getElementById('HID_MARKETING_OFFICER_NAME_' + i).value;");
                out.println("                       }");
                // In Edit Mode, Add The Deleted Marketing Officer To An Array
                out.println("                       else if ((i == m_selected_row_number) && (document.getElementById('HID_SCREEN_MODE').value == 'MOD')) {");
                out.println("                           m_deleted_marketing_officers.push(document.getElementById('HID_MARKETING_OFFICER_' + i).value);");
                out.println("                       }");
                out.println("                   }");
                
                /* Create HTML Objects */
                out.println("                   for (var i = 1; i <= m_count; i++) {");
                out.println("                       add_marketing_officer('delete_mode', value01[i], value02[i]);");
                out.println("                   }");
                
                // /* Set The Values To HTML Objects */
                // out.println("                   for (var i = 1; i <= m_count; i++) {;");
                // out.println("                       document.getElementById('HID_MARKETING_OFFICER_' + i).value = value01[i];");
                // out.println("                       document.getElementById('HID_MARKETING_OFFICER_NAME_' + i).value = value02[i];");
                // out.println("                       document.getElementById('DIV_MARKETING_OFFICER_' + i).innerHTML = value01[i];");
                // out.println("                       document.getElementById('DIV_MARKETING_OFFICER_NAME_' + i).innerHTML = value02[i];");
                // out.println("                   }");
                out.println("                   document.getElementById('HID_MARKETING_OFFICER_COUNT').value = m_count;");
                
                /* The 0th Dummy Row Creation Flag */
                out.println("                   if (m_count == 0) {");
                out.println("                       m_flag_clear = true;");
                out.println("                   }");
                out.println("               }");
                
                /* The Initial Load Flag */
                out.println("               if (m_selected_row_number == 0) {");
                out.println("                   m_flag_clear = true;");
                out.println("               }");
                
                /* Create A Dummy 0th Row AND Initiial No Row State */
                out.println("               if (m_flag_clear == true) {");
                out.println("                   document.getElementById('DIV_MARKETING_OFFICER').innerHTML = '';");
                // out.println("                       '   <table> ' + ");
                // out.println("                       '       <tr> ' + ");
                // out.println("                       '           <td class = \"div_input\" align = \"center\" style = \"width: 50px;\"></td> ' + ");
                // out.println("                       '       </tr> ' + ");
                // out.println("                       '       <tr> ' + ");
                // out.println("                       '           <td><input class = \"but_input\" type = \"button\" name = \"BUT_ADD_CONTACT_PERSON\" value = \"+\" onClick = \"add_contact_person();\" /></td> ' + ");
                // out.println("                       '       </tr> ' + ");
                // out.println("                       '   </table> '; ");
                out.println("               }");
                
                out.println("           }");
                
                
                out.println("           function createDIV(div_name, row_number) {");
                out.println("               var dynDiv = document.createElement('div');");
                // out.println("               var dynBr = document.createElement('br');");
                out.println("               dynDiv.id = div_name + row_number;");
                // out.println("               document.getElementById(div_name).appendChild(dynBr);");
                out.println("               document.getElementById(div_name).appendChild(dynDiv);");
                out.println("           }");
                
                
                out.println("           function before_submit() {");
                
                // Before Submit Validations
                out.println("               if (document.getElementById('TXT_COLLECTION_OFFICER').value.length == 0) {");
                out.println("                   alert('Please select a collection officer.');");
                out.println("                   document.getElementById('TXT_COLLECTION_OFFICER').focus();");
                out.println("                   return false;");
                out.println("               }");
                
                out.println("               if ((document.getElementById('HID_SCREEN_MODE').value == 'NEW') && (parseInt(document.getElementById('HID_MARKETING_OFFICER_COUNT').value) == 0)) {");
                out.println("                   alert('Please add at least one marketing officer.');");
                out.println("                   document.getElementById('TXT_MARKETING_OFFICER').focus();");
                out.println("                   return false;");
                out.println("               }");
                
                
                out.println("               if (confirm('Are you sure you want to save?')) {");
                out.println("                   if (document.getElementById('HID_SCREEN_MODE').value == 'MOD') {");
                out.println("                       document.getElementById('HID_MARKETING_OFFICER_DELETE_COUNT').value = m_deleted_marketing_officers.length");
                
                out.println("                       for (var i = 0; i < m_deleted_marketing_officers.length; i++) {");
                out.println("                           var dynamicHiddenInput = document.createElement('input');");
                out.println("                           dynamicHiddenInput.setAttribute('type', 'hidden');");
                out.println("                           dynamicHiddenInput.setAttribute('id', 'HID_DELETED_MARKETING_OFFICER_' + (i + 1));");
                out.println("                           dynamicHiddenInput.setAttribute('name', 'HID_DELETED_MARKETING_OFFICER_' + (i + 1));");
                out.println("                           dynamicHiddenInput.setAttribute('value', m_deleted_marketing_officers[i]);");
                out.println("                           document.getElementById('DIV_MARKETING_OFFICER_DELETED_LIST').appendChild(dynamicHiddenInput);");
                out.println("                       }");
                out.println("                   }");
                
                
                out.println("                   document.Form1.action = '" + m_class_url + "/" + m_fschema_name + "AF_CR_PRO_Assign_Marketing_Officers_To_Collection_Officer_save';"); 
                out.println("                   document.Form1.submit();");
                out.println("               }");
                
                out.println("           }");
                
                
                out.println("           function main_button_click(mode) {");
                out.println("               if (mode == 'NEW') {");
                out.println("                   if (confirm('Are you sure you want to enter a new record?')) {");
                out.println("                       window.location.href = window.location.href;");
                out.println("                   }");
                out.println("               }");
                out.println("               else if (mode == 'MOD') {");
                out.println("                   if (confirm('Are you sure you want to edit an existing record?')) {");
                out.println("                       document.getElementById('HID_SCREEN_MODE').value = 'MOD';");
                out.println("                       document.getElementById('HID_MARKETING_OFFICER_COUNT').value = '0';");
                out.println("                       document.getElementById('DIV_MARKETING_OFFICER').innerHTML = '';");
                
                out.println("                       document.getElementById('TXT_COLLECTION_OFFICER').value = '';");
                out.println("                       document.getElementById('TXT_COLLECTION_OFFICER_NAME').value = '';");
                out.println("                       document.getElementById('TXT_MARKETING_OFFICER').value = '';");
                out.println("                       document.getElementById('TXT_MARKETING_OFFICER_NAME').value = '';");
                
                out.println("                       m_deleted_marketing_officers = new Array();");
                out.println("                   }");
                out.println("               }");
                out.println("               else if (mode == 'CANCEL') {");
                out.println("                   if (confirm('Are you sure you want to clear the screen?')) {");
                out.println("                       window.location.href = window.location.href;");
                out.println("                   }");
                out.println("               }");
                out.println("           }");
                
                
                out.println("           function show_allocations() {");
                out.println("               m_url=\""+m_class_url+"/"+m_fschema_name+"AF_CR_PRO_Assign_Marketing_Officers_To_Collection_Officer_display?chksql=show_allocations\";");
                out.println("               popupwin=window.open(m_url,'displayWindow1','left=110,top=110,width=850,height=200,toolbar=0,location=0,center:yes,direction=0,menuBar=0,status=0,scrollbars=1,resizable=1');");
                out.println("           }");
                
                out.println("       </script>");
                out.println("   </head>");
                
                
                out.println("   <body class=\"body & txt-body\" leftmargin=\"0\" topmargin=\"0\" marginwidth=\"0\" marginheight=\"0\">");
                out.println("       <form name=\"Form1\" method=\"post\">");
                
                out.println("           <input type=\"hidden\" name=\"HID_SCREEN_MODE\" id=\"HID_SCREEN_MODE\" value=\"NEW\" />");
                out.println("           <input type=\"hidden\" name=\"HID_MARKETING_OFFICER_COUNT\" id=\"HID_MARKETING_OFFICER_COUNT\" value=\"0\" />");
                out.println("           <input type=\"hidden\" name=\"HID_MARKETING_OFFICER_DELETE_COUNT\" id=\"HID_MARKETING_OFFICER_DELETE_COUNT\" value=\"0\" />");
                
                out.println("           <table class=\"table\" width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">");
                out.println("               <tr>");
                out.println("                   <td valign=\"top\" style=\"width: 8px;\"></td>");
                out.println("                   <td class=\"border_wht\" valign=\"top\">");
                out.println("                       <table class=\"table\" width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">");
                out.println("                           <tr>");
                out.println("                               <td class=\"pdn_mainHD\" style=\"height: 30px;\">" + header_name + "</td>");
                out.println("                           </tr>");
                out.println("                           <tr>");
                out.println("                               <td style=\"height: 2px;\"></td>");
                out.println("                           </tr>");
                out.println("                           <tr>");
                out.println("                               <td>");
                out.println("                                   <table class=\"table\" width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">");
                out.println("                                       <tr>");
                out.println("                                           <td align=\"left\" class=\"pdn_txtpos2\" style=\"height: 18px;\" id=\"help_box\">Assign Marketing Officers To Collection Officer - New</td>");
                out.println("                                       </tr>");
                out.println("                                       <tr>");
                out.println("                                           <td height=\"10px\" class=\"pdn_txtpos\">");
                out.println("                                               <table class=\"table\" border=\"0\" cellpadding=\"2\" cellspacing=\"2\">");
                out.println("                                                   <tr>");
                // out.println("                                                       <td><input class=\"mainbut\" type=\"button\" name=new      value=\"New\"       onclick=befor_new();      onMouseOver='load_roll_value(\"New\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);' /></td>");
                // out.println("                                                       <td><input class=\"mainbut\" type=\"button\" name=edit     value=\"Edit\"      onclick=befor_modify();   onMouseOver='load_roll_value(\"Edit\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);' /></td>");
                out.println("                                                       <td><input class=\"mainbut\" type=\"button\" name=\"BUT_MAIN_NEW\" id=\"BUT_MAIN_NEW\" value=\"New\" onclick=\"main_button_click('NEW');\" /></td>");
                out.println("                                                       <td><input class=\"mainbut\" type=\"button\" name=\"BUT_MAIN_MOD\" id=\"BUT_MAIN_MOD\" value=\"Edit\" onclick=\"main_button_click('MOD');\" /></td>");
                out.println("                                                       <td width=\"100%\">&nbsp;</td>");
                // out.println("                                                       <td><input class=\"mainbut\" type=\"button\" name=b_submit value=\"Save\"      onclick=before_submit();   onMouseOver='load_roll_value(\"Save\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);' /></td>");
                out.println("                                                       <td><input class=\"mainbut\" type=\"button\" name=\"BUT_MAIN_ALLOCATIONS\" id=\"BUT_MAIN_ALLOCATIONS\" value=\"Show Allocations\" onclick=\"show_allocations();\" style=\"width: 150px;\" /></td>");
                out.println("                                                       <td><input class=\"mainbut\" type=\"button\" name=\"BUT_MAIN_SAVE\" id=\"BUT_MAIN_SAVE\" value=\"Save\" onclick=\"before_submit();\" /></td>");
                // out.println("                                                       <td><input class=\"mainbut\" type=\"button\" name=b_submit value=\"Save\"      onclick=before_submit();   /></td>");
                out.println("                                                       <td><input class=\"mainbut\" type=\"button\" name=\"BUT_MAIN_HELP\" id=\"BUT_MAIN_HELP\" value=\"Help\" onclick=\"HelpBox_msg();\" /></td>");
                // out.println("                                                       <td><input class=\"mainbut\" type=\"button\" name=delete value=\"Help\" onclick=HelpBox_msg(); onMouseOver='load_roll_value(\"Help\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);' /></td>");
                out.println("                                                       <td><input class=\"mainbut\" type=\"button\" name=\"BUT_MAIN_CANCEL\" id=\"BUT_MAIN_CANCEL\" value=\"Cancel\" onclick=\"main_button_click('CANCEL');\" /></td>");
                // out.println("                                                       <td><input class=\"mainbut\" type=\"button\" name=reset_   value=\"Cancel\"     onclick=befor_reset();    onMouseOver='load_roll_value(\"Cancel\");'     onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);' /></td>");
                out.println("                                                       <td><input class=\"mainbut\" type=\"button\" name=\"BUT_MAIN_CLOSE\" id=\"BUT_MAIN_CLOSE\" value=\"Close\" onclick=\"close_window();\" /></td>");
                // out.println("                                                       <td><input class=\"mainbut\" type=\"button\" name=back     value=\"Close\"     onclick=befor_back();     onMouseOver='load_roll_value(\"Close\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);' /></td>");
                out.println("                                                   </tr>");
                out.println("                                               </table>");
                out.println("                                           </td>");
                out.println("                                       </tr>");
                out.println("                                       <tr>");
                out.println("                                           <td class=\"line\" height=\"1\" align=\"center\">");
                out.println("                                       <tr>");
                
                out.println("                                       <tr>");
                out.println("                                           <td>");
                out.println("                                               <table class=\"table\" border=\"0\" cellpadding=\"2\" cellspacing=\"2\">");
                out.println("                                                   <tr>");
                out.println("                                                       <td style=\"height: 20px; width: 200px;\"></td>");
                out.println("                                                       <td></td>");
                out.println("                                                       <td></td>");
                out.println("                                                   </tr>");
                out.println("                                                   <tr>");
                out.println("                                                       <td>Collection Officer</td>");
                out.println("                                                       <td>");
                out.println("                                                           <input class=\"txt_input\" type=\"text\" name=\"TXT_COLLECTION_OFFICER\" id=\"TXT_COLLECTION_OFFICER\" maxlength=\"10\" style=\"width: 125px;\" onblur=\"\" />");
                out.println("                                                           <input class=\"but_input\" type=\"button\" name=\"BUT_HELP_COLLECTION_OFFICER\" id=\"BUT_HELP_COLLECTION_OFFICER\" value=\"...\" onclick=\"help_update('collection_officer');\" />");
                out.println("                                                       </td>");
                out.println("                                                       <td>");
                out.println("                                                           <input class=\"txt_input\" type=\"text\" name=\"TXT_COLLECTION_OFFICER_NAME\" id=\"TXT_COLLECTION_OFFICER_NAME\" style=\"width: 250px;\" disabled=\"disabled\" />");
                out.println("                                                       </td>");
                out.println("                                                   </tr>");
                out.println("                                                   <tr>");
                out.println("                                                       <td>Marketing Officer</td>");
                out.println("                                                       <td>");
                out.println("                                                           <input class=\"txt_input\" type=\"text\" name=\"TXT_MARKETING_OFFICER\" id=\"TXT_MARKETING_OFFICER\" maxlength=\"10\" style=\"width: 125px;\" onblur=\"\" />");
                out.println("                                                           <input class=\"but_input\" type=\"button\" name=\"BUT_HELP_MARKETING_OFFICER\" id=\"BUT_HELP_MARKETING_OFFICER\" value=\"...\" onclick=\"help_update('marketing_officer');\" />");
                out.println("                                                           <input class=\"but_input\" type=\"button\" name=\"BUT_ADD_MARKETING_OFFICER\" id=\"BUT_ADD_MARKETING_OFFICER\" value=\"Add\" onclick=\"add_marketing_officer_to_list();\" />");
                out.println("                                                       </td>");
                out.println("                                                       <td>");
                out.println("                                                           <input class=\"txt_input\" type=\"text\" name=\"TXT_MARKETING_OFFICER_NAME\" id=\"TXT_MARKETING_OFFICER_NAME\" style=\"width: 250px;\" disabled=\"disabled\" />");
                out.println("                                                       </td>");
                out.println("                                                   </tr>");
                out.println("                                               </table>");
                
                out.println("                                               <br />");
                out.println("                                               <table width=\"100%\" border=\"0\">");
                out.println("                                                   <tr>");
                out.println("                                                       <td style=\"width: 200px;\"><u><b>Assigned Marketing Officers</b></u></td>");
                out.println("                                                   </tr>");
                out.println("                                               </table>");
                out.println("                                               <table width=\"100%\" border=\"0\">");
                // out.println("                                               <table align=\"left\" border=\"0\">");
                out.println("                                                   <tr bgcolor=\"#80\">");
                out.println("                                                       <td><u><b>Employee Code</b></u></td>");
                out.println("                                                       <td><u><b>Employee Name</b></u></td>");
                // out.println("                                                       <td style=\"width: 200px;\"><u><b>Employee Code</b></u></td>");
                // out.println("                                                       <td style=\"width: 300px;\"><u><b>Employee Name</b></u></td>");
                out.println("                                                   </tr>");
                out.println("                                               </table>");
                // out.println("                                                   <tr bgcolor=\"#CCCCCC\">");
                // out.println("                                                       <td><u><b>Employee Code</b></u></td>");
                // out.println("                                                       <td><u><b>Employee Name</b></u></td>");
                // out.println("                                                   </tr>");
                // out.println("                                                   <tr bgcolor=\"#CCCCCC\">");
                // out.println("                                                       <td><u><b>Employee Code</b></u></td>");
                // out.println("                                                       <td><u><b>Employee Name</b></u></td>");
                // out.println("                                                   </tr>");
                out.println("                                               <div id=\"DIV_MARKETING_OFFICER\"></div>");
                out.println("                                               <div id=\"DIV_MARKETING_OFFICER_DELETED_LIST\"></div>");
                out.println("                                               <br />");
                
                
                out.println("                                           </td>");
                out.println("                                       </tr>");
                
                // out.println("                                       <tr>");
                // out.println("                                           <td class=\"line\" height=\"1\" align=center><b></tr>");
                // out.println("                                       <tr>");
                // out.println("                                           <td class=\"line\" height=\"1\"><img height=\"1\" src=\""+m_html_client_url+"/images/spacer.gif\" width=\"1\" ></td>");
                // out.println("                                       </tr>");
                // out.println("                                       <tr>");
                // out.println("                                           <td class=\"pdn_txtpos\" height=\"150\" valign=\"top\">");
                // out.println("                                       </tr>");
                out.println("                                   </table>");
                out.println("                               </td>");
                out.println("                           </tr>");
                out.println("                       </table>");
                out.println("                   </td>");
                out.println("               </tr>");
                out.println("               <tr class=\"tr_input\">");
                out.println("                   <td colspan=\"2\" valign=\"bottom\" style=\"width: 20px;\"></td>");
                out.println("               </tr>");
                out.println("           </table>");
                
                out.println("       </form>");
                out.println("   </body>");
                out.println("</html>");
                
            }
            
            
            else if (m_chksql.equals("show_allocations")) {
                
                String m_app_no = request.getParameter("app_no");
                String m_inq_no = request.getParameter("inquiry_no");
                String m_pri_no = request.getParameter("pricing_no");
                String m_screen = request.getParameter("screen_type");
                String amount   = request.getParameter("amount");
                String m_ter_no = request.getParameter("TER_NO");
                String m_ter_am = request.getParameter("TER_AMT");
                String m_ter_ty = request.getParameter("TER_TYPE");
                if (m_ter_no==null){
                    m_ter_no="";
                }
                if (m_ter_am==null){
                    m_ter_am="";
                }
                if (m_ter_ty==null){
                    m_ter_ty="";
                }
                
                out.println("<html>");
                out.println("   <head>");
                out.println("       <title>Asset Financing System</title>");
                // out.println("       <script type=\"text/javascript\" src=\"" + m_html_client_url + "/valuation.js\"></script>");
                // out.println("       <script type=\"text/javascript\" src=\"" + m_html_client_url + "/validate_v1.js\"></script>");
                out.println("       <script type=\"text/javascript\" src=\"" + m_html_client_url + "/validate.js\"></script>");
                out.println("       <script type=\"text/javascript\" src=\"" + m_html_client_url + "/ajax_data_gateway.js\"></script>");
                out.println("       <link rel=\"stylesheet\" type=\"text/css\" href=\"" + m_html_client_url + "/css/Asset_Financing_System.css\" />");
                
                out.println("       <script type=\"text/javascript\">");
                out.println("       </script>");
                out.println("   </head>");
                
                
                out.println("   <body class=\"body & txt-body\" leftmargin=\"0\" topmargin=\"0\" marginwidth=\"0\" marginheight=\"0\">");
                out.println("       <form name=\"Form1\" method=\"post\">");
                
                out.println("           <input type=\"hidden\" name=\"HID_SCREEN_MODE\" id=\"HID_SCREEN_MODE\" value=\"NEW\" />");
                out.println("           <input type=\"hidden\" name=\"HID_MARKETING_OFFICER_COUNT\" id=\"HID_MARKETING_OFFICER_COUNT\" value=\"0\" />");
                out.println("           <input type=\"hidden\" name=\"HID_MARKETING_OFFICER_DELETE_COUNT\" id=\"HID_MARKETING_OFFICER_DELETE_COUNT\" value=\"0\" />");
                
                out.println("           <table class=\"table\" width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">");
                out.println("               <tr>");
                out.println("                   <td valign=\"top\" style=\"width: 8px;\"></td>");
                out.println("                   <td class=\"border_wht\" valign=\"top\">");
                out.println("                       <table class=\"table\" width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">");
                out.println("                           <tr>");
                out.println("                               <td class=\"pdn_mainHD\" style=\"height: 30px;\">" + header_name + "</td>");
                out.println("                           </tr>");
                out.println("                           <tr>");
                out.println("                               <td style=\"height: 2px;\"></td>");
                out.println("                           </tr>");
                out.println("                           <tr>");
                out.println("                               <td>");
                out.println("                                   <table class=\"table\" width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">");
                out.println("                                       <tr>");
                out.println("                                           <td align=\"left\" class=\"pdn_txtpos2\" style=\"height: 18px;\" id=\"help_box\">Marketing Officers To Collection Officer Assignments</td>");
                out.println("                                       </tr>");
                out.println("                                       <tr>");
                out.println("                                           <td height=\"10px\" class=\"pdn_txtpos\">");
                out.println("                                               <table class=\"table\" border=\"0\" cellpadding=\"2\" cellspacing=\"2\">");
                out.println("                                                   <tr>");
                // // out.println("                                                       <td><input class=\"mainbut\" type=\"button\" name=new      value=\"New\"       onclick=befor_new();      onMouseOver='load_roll_value(\"New\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);' /></td>");
                // // out.println("                                                       <td><input class=\"mainbut\" type=\"button\" name=edit     value=\"Edit\"      onclick=befor_modify();   onMouseOver='load_roll_value(\"Edit\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);' /></td>");
                // out.println("                                                       <td><input class=\"mainbut\" type=\"button\" name=\"BUT_MAIN_NEW\" id=\"BUT_MAIN_NEW\" value=\"New\" onclick=\"main_button_click('NEW');\" /></td>");
                // out.println("                                                       <td><input class=\"mainbut\" type=\"button\" name=\"BUT_MAIN_MOD\" id=\"BUT_MAIN_MOD\" value=\"Edit\" onclick=\"main_button_click('MOD');\" /></td>");
                // out.println("                                                       <td width=\"100%\">&nbsp;</td>");
                // // out.println("                                                       <td><input class=\"mainbut\" type=\"button\" name=b_submit value=\"Save\"      onclick=before_submit();   onMouseOver='load_roll_value(\"Save\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);' /></td>");
                // out.println("                                                       <td><input class=\"mainbut\" type=\"button\" name=\"BUT_MAIN_ALLOCATIONS\" id=\"BUT_MAIN_ALLOCATIONS\" value=\"Show Allocations\" onclick=\"show_allocations();\" style=\"width: 150px;\" /></td>");
                // out.println("                                                       <td><input class=\"mainbut\" type=\"button\" name=\"BUT_MAIN_SAVE\" id=\"BUT_MAIN_SAVE\" value=\"Save\" onclick=\"before_submit();\" /></td>");
                // // out.println("                                                       <td><input class=\"mainbut\" type=\"button\" name=b_submit value=\"Save\"      onclick=before_submit();   /></td>");
                // out.println("                                                       <td><input class=\"mainbut\" type=\"button\" name=\"BUT_MAIN_HELP\" id=\"BUT_MAIN_HELP\" value=\"Help\" onclick=\"HelpBox_msg();\" /></td>");
                // // out.println("                                                       <td><input class=\"mainbut\" type=\"button\" name=delete value=\"Help\" onclick=HelpBox_msg(); onMouseOver='load_roll_value(\"Help\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);' /></td>");
                // out.println("                                                       <td><input class=\"mainbut\" type=\"button\" name=\"BUT_MAIN_CANCEL\" id=\"BUT_MAIN_CANCEL\" value=\"Cancel\" onclick=\"main_button_click('CANCEL');\" /></td>");
                // // out.println("                                                       <td><input class=\"mainbut\" type=\"button\" name=reset_   value=\"Cancel\"     onclick=befor_reset();    onMouseOver='load_roll_value(\"Cancel\");'     onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);' /></td>");
                out.println("                                                       <td><input class=\"mainbut\" type=\"button\" name=\"BUT_MAIN_CLOSE\" id=\"BUT_MAIN_CLOSE\" value=\"Close\" onclick=\"close_window();\" /></td>");
                // // out.println("                                                       <td><input class=\"mainbut\" type=\"button\" name=back     value=\"Close\"     onclick=befor_back();     onMouseOver='load_roll_value(\"Close\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);' /></td>");
                out.println("                                                   </tr>");
                out.println("                                               </table>");
                out.println("                                           </td>");
                out.println("                                       </tr>");
                out.println("                                       <tr>");
                out.println("                                           <td class=\"line\" height=\"1\" align=\"center\">");
                out.println("                                       <tr>");
                
                
                sql = " "  +
                    "   SELECT A.COLLECTION_OFFICER, " +
                    "          " + m_schema_name + ".AF_CO_GET_EMP_NAME(A.COLLECTION_OFFICER) COLLECTION_OFFICER_NAME, " +
                    "          A.MARKETING_OFFICER, " +
                    "          " + m_schema_name + ".AF_CO_GET_EMP_NAME(A.MARKETING_OFFICER) MARKETING_OFFICER_NAME " +
                    "   FROM   " + m_schema_name + ".AF_CR_PRO_MKT_COLL_MAP A " +
                    "   ORDER BY A.COLLECTION_OFFICER ASC, " +
                    "            A.MARKETING_OFFICER ASC " +
                    " ";
                
                stmt = conn.createStatement();
                rs = stmt.executeQuery(sql);
                
                
                
                out.println("                                       <tr>");
                out.println("                                           <td>");
                out.println("                                               <br />");
                out.println("                                               <table class=\"table\" border=\"1\" cellpadding=\"2\" cellspacing=\"2\">");
                out.println("                                                   <tr>");
                out.println("                                                       <td style=\"height: 20px; width: 200px; font-weight: bold;\" colspan=\"2\">Collection Officer</td>");
                out.println("                                                       <td style=\"height: 20px; width: 200px; font-weight: bold;\" colspan=\"2\">Marketing Officer</td>");
                out.println("                                                   </tr>");
                out.println("                                                   <tr>");
                out.println("                                                       <td style=\"height: 20px; width: 50px; font-weight: bold;\">Code</td>");
                out.println("                                                       <td style=\"height: 20px; width: 150px; font-weight: bold;\">Name</td>");
                out.println("                                                       <td style=\"height: 20px; width: 50px; font-weight: bold;\">Code</td>");
                out.println("                                                       <td style=\"height: 20px; width: 150px; font-weight: bold;\">Name</td>");
                out.println("                                                   </tr>");
                
                String m_temp_collection_officer = null;
                while (rs.next()) {
                    
                    out.println("                                                   <tr>");
                    if ((m_temp_collection_officer == null) || (!m_temp_collection_officer.equals(rs.getString("COLLECTION_OFFICER")))) {
                        out.println("                                                       <td>" + rs.getString("COLLECTION_OFFICER") + "</td>");
                        out.println("                                                       <td>" + rs.getString("COLLECTION_OFFICER_NAME") + "</td>");
                    }
                    else {
                        out.println("                                                       <td>&nbsp;</td>");
                        out.println("                                                       <td>&nbsp;</td>");
                    }
                    out.println("                                                       <td>" + rs.getString("MARKETING_OFFICER") + "</td>");
                    out.println("                                                       <td>" + rs.getString("MARKETING_OFFICER_NAME") + "</td>");
                    out.println("                                                   </tr>");
                    
                    m_temp_collection_officer = rs.getString("COLLECTION_OFFICER");
                    
                }
                rs.close();
                stmt.close();
                
                out.println("                                               </table>");
                out.println("                                               <br />");
                
                
                // Unassigned Marketing Officers
                sql = " "  +
                    "   SELECT A.EMP_CODE, " +
                    "          " + m_schema_name + ".AF_CO_GET_EMP_NAME(A.EMP_CODE) EMP_NAME " +
                    "   FROM   " + m_schema_name + ".CO_CO_MAS_EMPLOYEE A " +
                    "   WHERE A.EMP_CODE NOT IN ( " +
                    "       SELECT B.MARKETING_OFFICER " +
                    "       FROM   " + m_schema_name + ".AF_CR_PRO_MKT_COLL_MAP B " +
                    "   ) " +
                    " ";
                
                stmt = conn.createStatement();
                rs = stmt.executeQuery(sql);
                
                
                
                out.println("                                       <tr>");
                out.println("                                           <td>");
                out.println("                                               <br />");
                out.println("                                               <table class=\"table\" border=\"1\" cellpadding=\"2\" cellspacing=\"2\">");
                out.println("                                                   <tr>");
                out.println("                                                       <td style=\"height: 20px; width: 400px; font-weight: bold;\" colspan=\"2\">Unassigned Collection Officers</td>");
                out.println("                                                   </tr>");
                out.println("                                                   <tr>");
                out.println("                                                       <td style=\"height: 20px; width: 100px; font-weight: bold;\">Code</td>");
                out.println("                                                       <td style=\"height: 20px; width: 300px; font-weight: bold;\">Name</td>");
                out.println("                                                   </tr>");
                
                while (rs.next()) {
                    
                    out.println("                                                   <tr>");
                    out.println("                                                       <td>" + rs.getString("EMP_CODE") + "</td>");
                    out.println("                                                       <td>" + rs.getString("EMP_NAME") + "</td>");
                    out.println("                                                   </tr>");
                    
                }
                
                out.println("                                               </table>");
                out.println("                                               <br />");
                
                
                
                out.println("                                           </td>");
                out.println("                                       </tr>");
                
                // out.println("                                       <tr>");
                // out.println("                                           <td class=\"line\" height=\"1\" align=center><b></tr>");
                // out.println("                                       <tr>");
                // out.println("                                           <td class=\"line\" height=\"1\"><img height=\"1\" src=\""+m_html_client_url+"/images/spacer.gif\" width=\"1\" ></td>");
                // out.println("                                       </tr>");
                // out.println("                                       <tr>");
                // out.println("                                           <td class=\"pdn_txtpos\" height=\"150\" valign=\"top\">");
                // out.println("                                       </tr>");
                out.println("                                   </table>");
                out.println("                               </td>");
                out.println("                           </tr>");
                out.println("                       </table>");
                out.println("                   </td>");
                out.println("               </tr>");
                out.println("               <tr class=\"tr_input\">");
                out.println("                   <td colspan=\"2\" valign=\"bottom\" style=\"width: 20px;\"></td>");
                out.println("               </tr>");
                out.println("           </table>");
                
                out.println("       </form>");
                out.println("   </body>");
                out.println("</html>");
                
            }
            
            // else if(m_chksql.equals("get_advance_price_cal")){
                
                // String m_rate		    = request.getParameter("rate");
                // String m_value      = request.getParameter("value");
                // String m_terms	    = request.getParameter("terms");
                // String m_freq		    = request.getParameter("freq");
                // String m_type       = request.getParameter("type");
                // String m_vat_per    = request.getParameter("tax");
                // String m_install    = request.getParameter("installments");
                // String m_factor     = request.getParameter("factor");
                // String m_pracent    = request.getParameter("pracent");
                // String m_percentage = request.getParameter("percentage");
                // String m_ami        = request.getParameter("ami");
                // String m_vat_app    = request.getParameter("tax_app");
                // String m_tax_rent   = request.getParameter("tax_rent");
                // String m_sup_cr_per = request.getParameter("supcrper");
                // String m_nibsm      = request.getParameter("nibsm");
                // String m_residual   = request.getParameter("residual");
                // String m_sup_credit = request.getParameter("sup_cr");
                // String m_other_cha  = request.getParameter("other_cha");
                // String m_maintenance= request.getParameter("maintan");
                // String m_int_base_m = request.getParameter("nitbasemar");
                // //String m_last_rent  = request.getParameter("last_rent");
                // String m_cash_out   = request.getParameter("cashout");
                // String m_trn_sub    = request.getParameter("trnsub");
                // String m_int_type   = request.getParameter("nittype");
                // String m_trn_type   = request.getParameter("trn_type");
                // String m_option     = request.getParameter("option");
                // String m_nitmar     = request.getParameter("nitmar");
                // String m_vat_val    = request.getParameter("vat_val");
                // String m_gro_val    = request.getParameter("gro_val");
                // String m_rental     = request.getParameter("rental");
                // String m_1rental    = request.getParameter("1rental");
                
                
                
                // //out.println("m_percentage="+m_percentage);											
                // //out.println("m_install="+m_install);											
                // callstmt1 = conn.prepareCall( "BEGIN "+m_schema_name+"."+ 
                    // "AF_MK_TEMP_PRICE_SAVE(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12,:13,:14,:15,:16,:17,:18,:19,:20,:21,:22,:23,:24,:25,:26,:27,:28,:29,:30);END;");
                // callstmt1.setString(1 ,m_terms);
                // //out.println("m_terms="+m_terms);											
                // callstmt1.setString(2 ,m_freq);
                // //out.println("m_freq="+m_freq);											
                // callstmt1.setString(3 ,m_install);
                // //out.println("m_install="+m_install);											
                // callstmt1.setString(4 ,m_pracent);
                // //out.println("m_pracent="+m_pracent);											
                // callstmt1.setString(5 ,m_factor);
                // //out.println("m_factor="+m_factor);											
                // callstmt1.setString(6 ,m_type);
                // //out.println("m_type="+m_type);											
                // callstmt1.setString(7 ,m_username);
                // //out.println("m_username="+m_username);											
                // callstmt1.setString(8 ,m_rate);
                // //out.println("m_rate="+m_rate);											
                // callstmt1.setString(9 ,m_value);
                // //out.println("m_value="+m_value);											
                // callstmt1.setString(10,m_vat_per);
                // //out.println("m_vat_per="+m_vat_per);											
                // callstmt1.setString(11,m_percentage);	
                // //out.println("m_percentage="+m_percentage);											
                // callstmt1.setString(12,m_vat_app);	
                // //out.println("m_vat_app="+m_vat_app);											
                // callstmt1.setString(13,m_ami);	
                // //out.println("m_ami="+m_ami);											
                // callstmt1.setString(14,m_sup_credit);	
                // //out.println("m_sup_credit="+m_sup_credit);											
                // callstmt1.setString(15,m_sup_cr_per);	
                // //out.println("m_sup_cr_per="+m_sup_cr_per);											
                // callstmt1.setString(16,m_nibsm);	
                // //out.println("m_nibsm="+m_nibsm);											
                // callstmt1.setString(17,m_residual);	
                // //out.println("m_residual="+m_residual);											
                // callstmt1.setString(18,m_other_cha);	
                // //out.println("m_other_cha="+m_other_cha);											
                // callstmt1.setString(19,m_maintenance);	
                // //out.println("m_maintenance="+m_maintenance);											
                // callstmt1.setString(20,m_option);	
                // //out.println("m_option="+m_option);											
                // callstmt1.setString(21,m_trn_type);
                // //out.println("m_trn_type="+m_trn_type);											
                // callstmt1.setString(22,m_trn_sub);
                // //out.println("m_trn_sub="+m_trn_sub);											
                // callstmt1.setString(23,m_int_type);
                // //out.println("m_int_type="+m_int_type);											
                // callstmt1.setString(24,m_nitmar);
                // //out.println("m_nitmar="+m_nitmar);											
                // callstmt1.setString(25,m_cash_out);
                // //out.println("m_cash_out="+m_cash_out);											
                // callstmt1.setString(26,m_vat_val);
                // //out.println("m_vat_val="+m_vat_val);											
                // callstmt1.setString(27,m_gro_val);
                // //out.println("m_gro_val="+m_gro_val);											
                // callstmt1.setString(28,m_rental);
                // //out.println("m_rental="+m_rental);											
                // callstmt1.setString(29,m_1rental);
                // //out.println("m_1rental="+m_1rental);											
                // callstmt1.setString(30,m_tax_rent);
                
                // //out.println("t5");
                // callstmt1.execute();
                // //out.println("t6");
                
                // /*     
          // rs = stmt.executeQuery ("SELECT "+m_rate+","+m_rate+"/100,("+m_rate+"/100)/"+m_freq+","+m_freq+","+m_terms+","+m_value+","+
                                            // "       "+m_schema_name+".AF_CO_CAL_FACTOR("+m_rate+","+m_freq+","+m_terms+",'"+m_type+"','"+m_ami+"'),"+
                                                                    // "       "+m_vat_per+",/*"+m_freq+"*****"+m_terms+",'"+m_ami+"' "+
                                                                    // " FROM DUAL");
                                                                    
          // /*
                    // out.println("<html><head>");
          // out.println("<title>PMT Value - Formulation</title></head>");
          // out.println("<body bgcolor='white'>");
          // out.println("<form name='Form1'>");
          // out.println("<br>");
          // */
                
                // double mm_tot_fact 				= 0;
                // double mm_tot_term 				= 0;
                // out.println("<table class=table border='1' width='100%' >");
                // out.println("<tr class=tr_input><td  width='100%' >");
                // //out.println("<div id=plsw> </div>");
                
                // /*
        // if(rs.next()){
            
            // mm_tot_fact				= rs.getDouble(7);
                        // mm_tot_term				= rs.getInt(5);
            
                // }	*/	
                // /*		        
            // out.println("<table class=table border='1' width='100%' >");
            // out.println("<tr class=tr_input>");
                        // out.println("<td  width='20%' >Total Factor</td>");
            // out.println("<td width='30%' >"+nf.format(rs.getDouble(7))+"</td>");
                        // out.println("<td  width='20%' >Total Gross Rental</td>");
            // out.println("<td width='30%' ID=GRENT></td>");
                        // out.println("</tr>");
            // out.println("<tr class=tr_input>");
                        // out.println("<td  width='20%' >Total Present Value</td>");
            // out.println("<td width='30%' ID=PVAL></td>");
                        // out.println("<td  width='20%' >Avg. Gross Rental</td>");
            // out.println("<td width='30%' ID=A_GRENT></td>");
                        // out.println("</tr>");
                        // out.println("</table>");
                        
        // }
        
        // out.println("<br>");		
                // */
                // out.println("<table class=table border='0' width='100%' >");
                // out.println("<tr class=tr_input>");
                // out.println("<td colspan=6 align=right><input type=button name=cash_f  value=\"Cash Flow\" class=mainbut onclick=load_flow();             onMouseOver='load_roll_value(\"Cash Outflow\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
                // //out.println("<td colspan=2 id=plsw> </td>");
                // //out.println("<td colspan=2 align=right><input type=button name=end_b   value=\"Go to End\" class=mainbut onclick=befor_end(document.Form1.top_b); onMouseOver='load_roll_value(\"End\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
                // out.println("</tr>");
                // out.println("<tr class=tr_input>");
                // out.println("<td  colspan=3 >1% of Net Amount </td>");
                // out.println("<td  colspan=4 id=n1per> </td>");
                // out.println("</tr>");
                
                // out.println("<tr class=tr_input><td  width='8%' >Inst</td>");
                // out.println("<td  width='8%' > </td>");
                // //out.println("<td  width='20%' >Rate per Month(RM)</td>");
                // //out.println("<td  width='30%' >(RM+1)=X</td>");
                // out.println("<td  width='10%' align=right>Factor</td>");
                // out.println("<td  width='10%' align=right>Rental Factor</td>");
                // out.println("<td  width='20%' align=right>Net Rental</td>");
                // out.println("<td  width='20%' align=right>VAT</td>");
                // out.println("<td  width='20%' align=right>Gross Rental</td>");
                // out.println("<td  width='20%' align=right>P.V.</td>");
                // //out.println("<td  width='20%' >AMI</td>");
                
                
                // out.println("</tr>");
                // out.println("<tr class=tr_input><td  width='8%' ></td><td  width='8%' ></td>");
                // out.println("<td id=m_fact align=right></td>");
                // out.println("<td ></td>");
                // out.println("<td id=n_re align=right></td>");
                // out.println("<td id=v_re align=right></td>");
                // out.println("<td id=g_re align=right></td>");
                // out.println("<td id=p_va align=right></td>");
                // //out.println("<td ><input name=\"AMI"+j+"\" type=\"text\" value=\""+nf.format(rs.getDouble(7))+"\" maxlength=\"25\" class=\"txt_input2\" ></td>");
                // out.println("</tr>");
                
                // double sum_rate=0;
                // double sum_rent=0;
                // double sum_p_re=0;
                // double sum_g_re=0;
                // double sum_v_re=0;
                // int m_start=0;
                // /*int m_end  =mm_term;
                
                // if(m_type.equals("ARREASE")){
                    // m_start=1;
                    // m_end  =mm_term+1;
                // }
                // */
                // int j=0;
                // //for(j=m_start;j<m_end;j++){
                // //out.println("J="+j);
                // //out.println("<td  width='20%' >"+nf.format(mm_rate_per_month)+"</td>");
                
                // rs = stmt.executeQuery (" SELECT A.INSTALLMENT, A.FACTOR, A.NEW_NET, A.NEW_GROSS,"+
                    // //" A.NEW_PRACENT,  "+  //comment by inditha 27-11-2007
                    // " CEIL(A.NEW_NET*A.FACTOR),"+// add by inditha 27-11-2007
                    // " A.PERCENTAGE, "+
                    // " A.AMI,AMOUNT,PERIOD,INT_RATE,TERM, "+
                    // " A.NEW_GROSS-A.NEW_NET,INTEREST_AMOUNT,CAPITAL_AMOUNT "+
                    // " FROM  "+m_schema_name+".AF_MK_TBD_PRICE_CAL A "+
                    // "	WHERE  ENT_USER  = '"+m_username+"'"+
                    // " ORDER BY INSTALLMENT");
                
                // boolean more = rs.next();
                // if(more){
                    // m_value      = rs.getString(8);
                    // m_freq		    = rs.getString(9);
                    // m_rate		    = rs.getString(10);
                    // m_terms	    = rs.getString(11);
                    // while(more){
                        // //out.println("<td  width='30%' >"+nf.format(rs.getDouble(1))+"</td>");
                        // out.println("<tr class=tr_input><td>"+(j+1)+"</td>");
                        // out.println("<td>"+rs.getString(1)+"<input type=hidden name=\"INSTALLMENT"+j+"\" value=\""+rs.getString(1)+"\"></td>");
                        // out.println("<td align=right><input name=\"FACTOR"+j+"\" type=\"text\" value="+nf1.format(rs.getDouble(2))+" maxlength=\"10\" class=\"txt_input1\" disabled></td>");
                        // out.println("<td align=right><input name=\"PERCENTAGE"+j+"\" type=\"text\" value=\""+nf1.format(rs.getDouble(6))+"\" maxlength=\"8\" class=\"txt_input1\"  onchange=format_num(document.Form1.PERCENTAGE"+j+",'6');cha_val(\""+j+"\");cal_count()></td>");
                        // out.println("<td align=right><input name=\"NETAMT"+j+"\" type=\"text\" value="+nf.format(rs.getDouble(3))+" maxlength=\"25\" class=\"txt_input2\" disabled></td>");
                        // out.println("<td align=right><input name=\"VATAMT"+j+"\" type=\"text\" value="+nf.format(rs.getDouble(12))+" maxlength=\"25\" class=\"txt_input2\" disabled></td>");
                        // out.println("<td align=right><input name=\"GROSS"+j+"\" type=\"text\" value="+nf.format(rs.getDouble(4))+" maxlength=\"25\" class=\"txt_input2\" onchange=format_num(document.Form1.GROSS"+j+",'4');cha_amt_val(\""+j+"\");cal_count()></td>");
                        // out.println("<td align=right><input name=\"PRACENT"+j+"\" type=\"text\" value="+nf.format(rs.getDouble(5))+" maxlength=\"25\" class=\"txt_input2\" disabled> ");
                        // out.println("<input type=hidden name=\"CAP_"+j+"\" value=\""+nf.format(rs.getDouble(14))+"\"><input type=hidden name=\"INST_"+j+"\" value=\""+nf.format(rs.getDouble(13))+"\"></td>");
                        // //out.println("<td ><input name=\"AMI"+j+"\" type=\"text\" value=\""+nf.format(rs.getDouble(7))+"\" maxlength=\"25\" class=\"txt_input2\" ></td>");
                        // out.println("</tr>");
                        // sum_rate=sum_rate+(rs.getDouble(2));
                        // sum_rent=sum_rent+(rs.getDouble(3));
                        // sum_p_re=sum_p_re+(rs.getDouble(5));
                        // sum_g_re=sum_g_re+(rs.getDouble(4));
                        // sum_v_re=sum_v_re+(rs.getDouble(12));
                        // j=j+1;
                        // more=rs.next();
                        // if(more){
                            // out.println("<tr class=tr_input1><td>"+(j+1)+"</td>");
                            // out.println("<td>"+rs.getString(1)+"<input type=hidden name=\"INSTALLMENT"+j+"\" value=\""+rs.getString(1)+"\"></td>");
                            // out.println("<td align=right><input name=\"FACTOR"+j+"\" type=\"text\" value="+nf1.format(rs.getDouble(2))+" maxlength=\"10\" class=\"txt_input1\" disabled></td>");
                            // out.println("<td align=right><input name=\"PERCENTAGE"+j+"\" type=\"text\" value=\""+nf1.format(rs.getDouble(6))+"\" maxlength=\"8\" class=\"txt_input1\" onchange=format_num(document.Form1.PERCENTAGE"+j+",'6');cha_val(\""+j+"\");cal_count()></td>");
                            // out.println("<td align=right><input name=\"NETAMT"+j+"\" type=\"text\" value="+nf.format(rs.getDouble(3))+" maxlength=\"25\" class=\"txt_input2\" disabled></td>");
                            // out.println("<td align=right><input name=\"VATAMT"+j+"\" type=\"text\" value="+nf.format(rs.getDouble(12))+" maxlength=\"25\" class=\"txt_input2\" disabled></td>");
                            // out.println("<td align=right><input name=\"GROSS"+j+"\" type=\"text\" value="+nf.format(rs.getDouble(4))+" maxlength=\"25\" class=\"txt_input2\" onchange=format_num(document.Form1.GROSS"+j+",'4');cha_amt_val(\""+j+"\");cal_count()></td>");
                            // out.println("<td align=right><input name=\"PRACENT"+j+"\" type=\"text\" value="+nf.format(rs.getDouble(5))+" maxlength=\"25\" class=\"txt_input2\" disabled>");
                            // out.println("<input type=hidden name=\"CAP_"+j+"\" value=\""+nf.format(rs.getDouble(14))+"\"><input type=hidden name=\"INST_"+j+"\" value=\""+nf.format(rs.getDouble(13))+"\"></td>");
                            // //out.println("<td ><input name=\"AMI"+j+"\" type=\"text\" value=\""+nf.format(rs.getDouble(7))+"\" maxlength=\"25\" class=\"txt_input2\" ></td>");
                            // out.println("</tr>");
                            // sum_rate=sum_rate+(rs.getDouble(2));
                            // sum_rent=sum_rent+(rs.getDouble(3));
                            // sum_p_re=sum_p_re+(rs.getDouble(5));
                            // sum_g_re=sum_g_re+(rs.getDouble(4));
                            // sum_v_re=sum_v_re+(rs.getDouble(12));
                            // j=j+1;
                            // more=rs.next();
                        // }
                    // }
                // }		
                // //}
                
                
                // out.println("<tr class=tr_input><td  ></td><td  ></td>");
                // //out.println("<td  width='20%' ></td>");
                // out.println("<td  align=right><input type=hidden name=hid_cou value="+j+">"+nf1.format(sum_rate)+"<input type=hidden name=sun_fact value="+nf1.format(sum_rate)+"></td>");
                // out.println("<td><input type=hidden name=hid_am value="+m_value+"><input type=hidden name=hid_ra value="+m_rate+"><input type=hidden name=hid_pe value="+m_terms+"></td>");
                // out.println("<td align=right>"+nf.format(sum_rent)+"<input type=hidden name=sun_rent value="+nf.format(sum_rent)+"></td>");
                // out.println("<td align=right>"+nf.format(sum_v_re)+"<input type=hidden name=sun_v_nt value="+nf.format(sum_v_re)+"></td>");
                // out.println("<td align=right>"+nf.format(sum_g_re)+"<input type=hidden name=sun_g_nt value="+nf.format(sum_g_re)+"></td>");
                // out.println("<td align=right>"+nf.format(sum_p_re)+"<input type=hidden name=sun_p_nt value="+nf.format(sum_p_re)+">");
                // rs = stmt.executeQuery (" SELECT '"+sum_rent+"'*(1/100) FROM DUAL");
                // //out.println(" SELECT '"+sum_rent+"'*(1/100) FROM DUAL");		
                // more = rs.next();
                // if(more){
                    // out.println("<input type=hidden name=n_1per value="+nf.format(rs.getDouble(1))+">");
                // }
                // out.println("</td>");
                
                // out.println("</tr>");					
                // out.println("<tr class=tr_input>");
                // out.println("<td align=right colspan=6><!--input type=button name=top_b     value=\"Go to Top\" class=mainbut onclick=befor_end(document.Form1.end_b);  onMouseOver='load_roll_value(\"Top\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'--></td>");
                
                // out.println("</tr></table>");
                
                
            // }
            
            
            
            // else if(m_chksql.equals("loadPrice")){
                
                // String m_rate		    = request.getParameter("rate");
                // String m_value      = request.getParameter("value");
                // String m_terms	    = request.getParameter("terms");
                // String m_freq		    = request.getParameter("freq");
                // String m_type       = request.getParameter("type");
                // String m_vat_per    = request.getParameter("tax");
                // String m_install    = request.getParameter("installments");
                // String m_factor     = request.getParameter("factor");
                // String m_pracent    = request.getParameter("pracent");
                // String m_percentage = request.getParameter("percentage");
                // String m_ami        = request.getParameter("ami");
                // String m_vat_app    = request.getParameter("tax_app");
                // String m_tax_rent   = request.getParameter("tax_rent");
                // String m_sup_cr_per = request.getParameter("supcrper");
                // String m_nibsm      = request.getParameter("nibsm");
                // String m_residual   = request.getParameter("residual");
                // String m_sup_credit = request.getParameter("sup_cr");
                // String m_other_cha  = request.getParameter("other_cha");
                // String m_maintenance= request.getParameter("maintan");
                // String m_int_base_m = request.getParameter("nitbasemar");
                // //String m_last_rent  = request.getParameter("last_rent");
                // String m_cash_out   = request.getParameter("cashout");
                // String m_trn_sub    = request.getParameter("trnsub");
                // String m_int_type   = request.getParameter("nittype");
                // String m_trn_type   = request.getParameter("trn_type");
                // String m_option     = request.getParameter("option");
                // String m_price_no   = request.getParameter("price_no");
                
                
                // /*     
          // rs = stmt.executeQuery ("SELECT "+m_rate+","+m_rate+"/100,("+m_rate+"/100)/"+m_freq+","+m_freq+","+m_terms+","+m_value+","+
                                            // "       "+m_schema_name+".AF_CO_CAL_FACTOR("+m_rate+","+m_freq+","+m_terms+",'"+m_type+"','"+m_ami+"'),"+
                                                                    // "       "+m_vat_per+",/*"+m_freq+"*****"+m_terms+",'"+m_ami+"' "+
                                                                    // " FROM DUAL");
                                                                    
          // /*
                    // out.println("<html><head>");
          // out.println("<title>PMT Value - Formulation</title></head>");
          // out.println("<body bgcolor='white'>");
          // out.println("<form name='Form1'>");
          // out.println("<br>");
          // */
                
                // double mm_tot_fact 				= 0;
                // double mm_tot_term 				= 0;
                // out.println("<table class=table border='1' width='100%' >");
                // out.println("<tr class=tr_input><td  width='100%' >");
                // /*
                // if(rs.next()){
                    
                    // mm_tot_fact				= rs.getDouble(7);
                                // mm_tot_term				= rs.getInt(5);
                    
                        // }	*/	
                // /*		        
            // out.println("<table class=table border='1' width='100%' >");
            // out.println("<tr class=tr_input>");
                        // out.println("<td  width='20%' >Total Factor</td>");
            // out.println("<td width='30%' >"+nf.format(rs.getDouble(7))+"</td>");
                        // out.println("<td  width='20%' >Total Gross Rental</td>");
            // out.println("<td width='30%' ID=GRENT></td>");
                        // out.println("</tr>");
            // out.println("<tr class=tr_input>");
                        // out.println("<td  width='20%' >Total Present Value</td>");
            // out.println("<td width='30%' ID=PVAL></td>");
                        // out.println("<td  width='20%' >Avg. Gross Rental</td>");
            // out.println("<td width='30%' ID=A_GRENT></td>");
                        // out.println("</tr>");
                        // out.println("</table>");
                        
        // }
        
        // out.println("<br>");		
                // */
                // out.println("<table class=table border='0' width='100%' >");
                // out.println("<tr class=tr_input>");
                // out.println("<td colspan=6 align=right><input type=button name=cash_f  value=\"Cash Flow\" class=mainbut onclick=load_flow();             onMouseOver='load_roll_value(\"Cash Outflow\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
                // //out.println("<td colspan=2 align=right><input type=button name=end_b   value=\"Go to End\" class=mainbut onclick=befor_end(document.Form1.top_b); onMouseOver='load_roll_value(\"End\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
                // out.println("</tr>");
                
                // out.println("<tr class=tr_input><td  width='8%' >Inst</td>");
                // out.println("<td  width='8%' > </td>");
                // //out.println("<td  width='20%' >Rate per Month(RM)</td>");
                // //out.println("<td  width='30%' >(RM+1)=X</td>");
                // out.println("<td  width='10%' align=right>Factor</td>");
                // out.println("<td  width='10%' align=right>Rental Factor</td>");
                // out.println("<td  width='20%' align=right>Net Rental</td>");
                // out.println("<td  width='20%' align=right>VAT</td>");
                // out.println("<td  width='20%' align=right>Gross Rental</td>");
                // out.println("<td  width='20%' align=right>P.V.</td>");
                // //out.println("<td  width='20%' >AMI</td>");
                
                
                // out.println("</tr>");
                // out.println("<tr class=tr_input><td  width='8%' ></td><td  ></td>");
                // out.println("<td id=m_fact align=right></td>");
                // out.println("<td ></td>");
                // out.println("<td id=n_re align=right></td>");
                // out.println("<td id=v_re align=right></td>");
                // out.println("<td id=g_re align=right></td>");
                // out.println("<td id=p_va align=right></td>");
                // //out.println("<td ><input name=\"AMI"+j+"\" type=\"text\" value=\""+nf.format(rs.getDouble(7))+"\" maxlength=\"25\" class=\"txt_input2\" ></td>");
                // out.println("</tr>");
                
                // double sum_rate=0;
                // double sum_rent=0;
                // double sum_p_re=0;
                // double sum_g_re=0;
                // double sum_v_re=0;
                // int m_start=0;
                // /*int m_end  =mm_term;
                
                // if(m_type.equals("ARREASE")){
                    // m_start=1;
                    // m_end  =mm_term+1;
                // }
                // */
                // int j=0;
                // //for(j=m_start;j<m_end;j++){
                // //out.println("J="+j);
                // //out.println("<td  width='20%' >"+nf.format(mm_rate_per_month)+"</td>");
                
                // rs = stmt.executeQuery (" SELECT A.INSTALLMENT_NO, "+
                    // "        PERCENTAGE / POWER((('"+m_rate+"' / 100) / '"+m_freq+"' +1), INSTALLMENT_NO), "+
                    // "        NET_RENTAL_AMOUNT,GRENTAL_AMOUNT,A.PRACENT_AMOUNT,  "+
                    // "        PERCENTAGE, '"+m_ami+"','"+m_value+"','"+m_freq+"',"+
                    // "        '"+m_rate+"','"+m_terms+"',GRENTAL_AMOUNT-NET_RENTAL_AMOUNT, "+
                    // "        INTEREST_AMOUNT,CAPITAL_AMOUNT "+
                    // "  FROM  "+m_schema_name+".AF_MK_PRO_PRICING_INSTALLMENT A "+
                    // "	WHERE  PRICING_NO  = '"+m_price_no+"'"+
                    // " ORDER BY TO_NUMBER(INSTALLMENT_NO)");
                
                
                
                // boolean more = rs.next();
                // if(more){
                    // m_value      = rs.getString(8);
                    // m_freq		    = rs.getString(9);
                    // m_rate		    = rs.getString(10);
                    // m_terms	    = rs.getString(11);
                    
                    // while(more){
                        // //out.println("<td  width='30%' >"+nf.format(rs.getDouble(1))+"</td>");
                        // out.println("<tr class=tr_input><td>"+(j+1)+"</td>");
                        // out.println("<td>"+rs.getString(1)+"<input type=hidden name=\"INSTALLMENT"+j+"\" value=\""+rs.getString(1)+"\"></td>");
                        // out.println("<td align=right><input name=\"FACTOR"+j+"\" type=\"text\" value="+nf1.format(rs.getDouble(2))+" maxlength=\"10\" class=\"txt_input1\" disabled></td>");
                        // out.println("<td align=right><input name=\"PERCENTAGE"+j+"\" type=\"text\" value=\""+nf1.format(rs.getDouble(6))+"\" maxlength=\"8\" class=\"txt_input1\" onchange=format_num(document.Form1.PERCENTAGE"+j+",'6');cha_val(\""+j+"\");cal_count()></td>");
                        // out.println("<td align=right><input name=\"NETAMT"+j+"\" type=\"text\" value="+nf.format(rs.getDouble(3))+" maxlength=\"25\" class=\"txt_input2\" disabled></td>");
                        // out.println("<td align=right><input name=\"VATAMT"+j+"\" type=\"text\" value="+nf.format(rs.getDouble(12))+" maxlength=\"25\" class=\"txt_input2\" disabled></td>");
                        // out.println("<td align=right><input name=\"GROSS"+j+"\" type=\"text\" value="+nf.format(rs.getDouble(4))+" maxlength=\"25\" class=\"txt_input2\"  onchange=format_num(document.Form1.GROSS"+j+",'4');cha_amt_val(\""+j+"\");cal_count()></td>");
                        // out.println("<td align=right><input name=\"PRACENT"+j+"\" type=\"text\" value="+nf.format(rs.getDouble(5))+" maxlength=\"25\" class=\"txt_input2\" disabled>");
                        // out.println("<input type=hidden name=\"CAP_"+j+"\" value=\""+nf.format(rs.getDouble(14))+"\"><input type=hidden name=\"INST_"+j+"\" value=\""+nf.format(rs.getDouble(13))+"\"></td>");
                        // //out.println("<td ><input name=\"AMI"+j+"\" type=\"text\" value=\""+nf.format(rs.getDouble(7))+"\" maxlength=\"25\" class=\"txt_input2\" ></td>");
                        // out.println("</tr>");
                        // sum_rate=sum_rate+(rs.getDouble(2));
                        // sum_rent=sum_rent+(rs.getDouble(3));
                        // sum_p_re=sum_p_re+(rs.getDouble(5));
                        // sum_g_re=sum_g_re+(rs.getDouble(4));
                        // sum_v_re=sum_v_re+(rs.getDouble(12));
                        // j=j+1;
                        // more=rs.next();
                        // if(more){
                            // out.println("<tr class=tr_input1><td>"+(j+1)+"</td>");
                            // out.println("<td>"+rs.getString(1)+"<input type=hidden name=\"INSTALLMENT"+j+"\" value=\""+rs.getString(1)+"\"></td>");
                            // out.println("<td align=right><input name=\"FACTOR"+j+"\" type=\"text\" value="+nf1.format(rs.getDouble(2))+" maxlength=\"10\" class=\"txt_input1\" disabled></td>");
                            // out.println("<td align=right><input name=\"PERCENTAGE"+j+"\" type=\"text\" value=\""+nf1.format(rs.getDouble(6))+"\" maxlength=\"8\" class=\"txt_input1\" onchange=format_num(document.Form1.PERCENTAGE"+j+",'6');cha_val(\""+j+"\");cal_count()></td>");
                            // out.println("<td align=right><input name=\"NETAMT"+j+"\" type=\"text\" value="+nf.format(rs.getDouble(3))+" maxlength=\"25\" class=\"txt_input2\" disabled></td>");
                            // out.println("<td align=right><input name=\"VATAMT"+j+"\" type=\"text\" value="+nf.format(rs.getDouble(12))+" maxlength=\"25\" class=\"txt_input2\" disabled></td>");
                            // out.println("<td align=right><input name=\"GROSS"+j+"\" type=\"text\" value="+nf.format(rs.getDouble(4))+" maxlength=\"25\" class=\"txt_input2\"  onchange=format_num(document.Form1.GROSS"+j+",'4');cha_amt_val(\""+j+"\");cal_count()></td>");
                            // out.println("<td align=right><input name=\"PRACENT"+j+"\" type=\"text\" value="+nf.format(rs.getDouble(5))+" maxlength=\"25\" class=\"txt_input2\" disabled>");
                            // out.println("<input type=hidden name=\"CAP_"+j+"\" value=\""+nf.format(rs.getDouble(14))+"\"><input type=hidden name=\"INST_"+j+"\" value=\""+nf.format(rs.getDouble(13))+"\"></td>");
                            // //out.println("<td ><input name=\"AMI"+j+"\" type=\"text\" value=\""+nf.format(rs.getDouble(7))+"\" maxlength=\"25\" class=\"txt_input2\" ></td>");
                            // out.println("</tr>");
                            // sum_rate=sum_rate+(rs.getDouble(2));
                            // sum_rent=sum_rent+(rs.getDouble(3));
                            // sum_p_re=sum_p_re+(rs.getDouble(5));
                            // sum_g_re=sum_g_re+(rs.getDouble(4));
                            // sum_v_re=sum_v_re+(rs.getDouble(12));
                            // j=j+1;
                            // more=rs.next();
                        // }
                    // }
                // }		
                // //}
                // out.println("<tr class=tr_input><td  ></td><td  ></td>");
                // //out.println("<td  width='20%' ></td>");
                // out.println("<td  align=right><input type=hidden name=hid_cou value="+j+">"+nf1.format(sum_rate)+"<input type=hidden name=sun_fact value="+nf1.format(sum_rate)+"></td>");
                // out.println("<td><input type=hidden name=hid_am value="+m_value+"><input type=hidden name=hid_ra value="+m_rate+"><input type=hidden name=hid_pe value="+m_terms+"></td>");
                // out.println("<td align=right>"+nf.format(sum_rent)+"<input type=hidden name=sun_rent value="+nf.format(sum_rent)+"></td>");
                // out.println("<td align=right>"+nf.format(sum_v_re)+"<input type=hidden name=sun_v_nt value="+nf.format(sum_v_re)+"></td>");
                // out.println("<td align=right>"+nf.format(sum_g_re)+"<input type=hidden name=sun_g_nt value="+nf.format(sum_g_re)+"></td>");
                // out.println("<td align=right>"+nf.format(sum_p_re)+"<input type=hidden name=sun_p_nt value="+nf.format(sum_p_re)+"></td>");
                // //out.println("<td></td>");
                
                // out.println("</tr>");					
                // out.println("<tr class=tr_input>");
                // out.println("<td align=right colspan=6><!--input type=button name=top_b     value=\"Go to Top\" class=mainbut onclick=befor_end(document.Form1.end_b);  onMouseOver='load_roll_value(\"Top\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'--></td>");
                
                // out.println("</tr></table>");
                
                
            // }
            
            // else if(m_chksql.equals("chkCity")){
                // rs = stmt.executeQuery("SELECT A.CITY_CODE, A.CITY_DESC, B.DESCRIPTION "+
                    // "FROM   "+m_schema_name+".AF_CO_MAS_CITY A,"+
                    // "       "+m_schema_name+".AF_CO_MAS_POSTAL_CODES B"+
                    // "WHERE  A.CITY_CODE = B.CITY_CODE AND A.ACTIVE_STATUS='Y' AND "+
                    // "       B.ACTIVE_STATUS='Y'");
                // boolean more = rs.next();
                // if(more){
                    // out.println(rs.getString(1)+"@#"+rs.getString(2)+"@#"+rs.getString(3)+"@##@");
                // }	    
                
            // }
            
            // else if(m_chksql.equals("chkSubType")){
                
                // String type = request.getParameter("type");
                // //out.println(CO_methods.getTrnSubType(m_schema_name,"Y",type,""));
                // rs = stmt.executeQuery(CO_methods.getTrnSubType(m_schema_name,"Y",type,""));
                // boolean more = rs.next();
                // while(more){
                    // out.println(rs.getString(1)+"@#"+rs.getString(2)+"@#~#@");
                    // more = rs.next();	
                // }	    
                
            // }
            // else if(m_chksql.equals("loadMaintenance")){
                
                // String terms = request.getParameter("terms");
                // String freq  = request.getParameter("freq");
                // String make  = request.getParameter("make");
                // String model  = request.getParameter("model");
                // String sub_type  = request.getParameter("sub_type");
                // String m_pr        = request.getParameter("price_no");
                
                
                // /*
                // out.println(" SELECT DISTINCT B.DESCRIPTION, A.charge_sub_code	,NVL(A.AMOUNT,'0'),NVL(A.INCREASE_DECREASE*100,'0'),   "+
               // "        D.ITEM_SUB_CAT,D.ITEM_SUB_CAT,A.MILEAGE_CODE,A.ACTIVE_STATUS "+//A.MAKE_CODE,A.SUB_MODEL_CODE
                     // " FROM  "+m_schema_name+".AF_CO_MAS_MAINTENANCE_RATE A,"+m_schema_name+".AF_CO_MAS_SUB_CHARGES B, "+
           // "       "+m_schema_name+".AF_CO_MAS_SUB_MODLE C,"+m_schema_name+".AF_CO_MAS_MODEL D "+
                     // " WHERE A.CHARGE_SUB_CODE=B.SUB_TYPE_CODE AND D.ITEM_SUB_CAT LIKE '"+make+"%' AND "+
                     // "       A.CHARGE_SUB_CODE LIKE '"+sub_type+"%' AND "+//A.SUB_MODEL_CODE LIKE '"+model+"%' AND "+
                     // "       TYPE_CODE='MAINTENANC'  AND A.SUB_MODEL_CODE = C.SUB_CODE AND C.MODEL_CODE = D.MODEL_CODE"+
                     // " ORDER BY	B.DESCRIPTION DESC");
                // */		
                
                // String typ="";
                // String pay="";
                // if(m_pr!=null && !m_pr.equals("")){ 
                    
                    // rs = stmt.executeQuery("SELECT DESCRIPTION,SUB_CHAGE_CODE,ROUND(AMOUNT),"+
                        // "       PERCENT_AMOUNT, YEAR_NO,NVL(ACCOUNT_TYPE,'-'),NVL(PAYEE_CODE,'-') "+//PERCENT_AMOUNT, AMOUNT, YEAR_NO
                        // "FROM   "+m_schema_name+".AF_MK_PRO_PRICING_MAINTENANCE A,"+
                        // "       "+m_schema_name+".AF_CO_MAS_SUB_CHARGES B "+
                        // "WHERE  PRICING_NO = '"+m_pr+"' AND "+
                        // "       B.SUB_TYPE_CODE=A.SUB_CHAGE_CODE "+
                        // "ORDER BY SUB_CHAGE_CODE,YEAR_NO");
                    
                    // //if(more){
                    
                    // double years    = (Double.parseDouble(terms)/Integer.parseInt(freq))-1;
                    // //out.println(CO_methods.getMaintenancApp(m_schema_name,"Y","","","","MAINTENANC"));
                    // //rs = stmt.executeQuery(CO_methods.getMaintenancApp(m_schema_name,"Y",make,model,sub_type,"MAINTENANC"));
                    // boolean more = rs.next();
                    // if(more){
                        // int t=0;
                        // out.println("<table>");
                        // out.println("<tr class=tr_input >");
                        // out.println("<td >Type</td>");
                        // out.println("<td >Amount</td>");
                        // out.println("<td >%</td>");
                        // for(int j=0;j<=years;j++){ 					
                            // out.println("<td >");
                            // out.println("Year"+j+"");
                            // out.println("</td>");
                        // }	
                        // out.println("<td >Payee Code</td>");
                        // out.println("</tr>");	
                        
                        // while(more){
                            // out.println("<tr class=tr_input>");
                            // out.println("<td >"+rs.getString(1)+"<input type=hidden name=\"MAINTE_NAME_"+t+"\" value="+rs.getString(2)+"></td>");
                            // out.println("<td >0<input type=hidden name=\"AMOUNT_"+t+"\"      value=0></td>");
                            // out.println("<td >0<input type=hidden name=\"AMOUNT_PER_"+t+"\"  value=0></td>");
                            // for(int j=0;j<=years;j++){
                                // out.println("<td>");
                                // if(more){
                                    // out.println("<input name=\"MAINTANENCE_"+t+"_"+j+"\" type=\"text\" maxlength=\"20\" class=\"txt_input1\" value=\""+rs.getString(3)+"\" onchange=format_num(document.Form1.MAINTANENCE_"+t+"_"+j+",'4');cal_count()>");
                                // }else{
                                    // out.println("<input name=\"MAINTANENCE_"+t+"_"+j+"\" type=\"text\" maxlength=\"20\" class=\"txt_input1\" value=\"0\" onchange=format_num(document.Form1.MAINTANENCE_"+t+"_"+j+",'4');cal_count()>");
                                // }
                                // out.println("</td>");
                                // typ=rs.getString(6);
                                // pay=rs.getString(7);
                                // more = rs.next();	
                            // }
                            // if(!typ.equals("L")){
                                // out.println("<td ><input type=hidden name=\"PAYEE_"+t+"\"  value='-'></td>");
                            // }else{
                                // if(pay!=null){
                                    // out.println("<td ><input type=text name=\"PAYEE_"+t+"\" class=\"txt_input1\" value=\""+pay+"\" onchange=getPAYEE(\""+t+"\")><input type=button name=\"Pay_help_"+t+"\" value=Help class=\"but_input\" onclick=\"PAY_help('1','10','0','PAYSql','27',document.Form1.PAYEE_"+t+",'"+t+"')\"></td>");
                                // }else{
                                    // out.println("<td ><input type=text name=\"PAYEE_"+t+"\" class=\"txt_input1\" value=\"\" onchange=getPAYEE(\""+t+"\")><input type=button name=\"Pay_help_"+t+"\" value=Help class=\"but_input\" onclick=\"PAY_help('1','10','0','PAYSql','27',document.Form1.PAYEE_"+t+",'"+t+"')\"></td>");
                                // }	
                            // }
                            // out.println("</tr>");
                            // t = t+1;
                            // //more = rs.next();	
                        // }
                        // out.println("<input type=hidden name=hid_m_count value="+t+">");
                        // out.println("<input type=hidden name=hid_y_count value="+years+">");
                        // out.println("<table>");
                        
                    // }else{
                        
                        // rs = stmt.executeQuery(" SELECT DISTINCT B.DESCRIPTION, A.CHARGE_SUB_CODE	,"+
                            // "        NVL(A.AMOUNT,'0'),NVL(A.INCREASE_DECREASE,'0'),NVL(ACCOUNT_TYPE,'-') "+
                            // " FROM  "+m_schema_name+".AF_CO_MAS_MAINTENANCE_RATE A, "+
                            // "       "+m_schema_name+".AF_CO_MAS_SUB_CHARGES B "+
                            // " WHERE A.CHARGE_SUB_CODE=B.SUB_TYPE_CODE AND "+
                            // "       A.ITEM_CAT_CODE LIKE '"+make+"' AND  "+
                            // "       A.CHARGE_SUB_CODE LIKE '"+sub_type+"%' "+
                            // " ORDER BY	B.DESCRIPTION DESC");/*(" SELECT DISTINCT B.DESCRIPTION, A.charge_sub_code	,NVL(A.AMOUNT,'0'),NVL(A.INCREASE_DECREASE,'0'),   "+
               // "        D.ITEM_SUB_CAT,D.ITEM_SUB_CAT,'',A.ACTIVE_STATUS "+//A.MILEAGE_CODE,A.MAKE_CODE,A.SUB_MODEL_CODE
                     // " FROM  "+m_schema_name+".AF_CO_MAS_MAINTENANCE_RATE A,"+m_schema_name+".AF_CO_MAS_SUB_CHARGES B, "+
           // "       "+m_schema_name+".AF_CO_MAS_SUB_MODLE C,"+m_schema_name+".AF_CO_MAS_MODEL D "+
                     // " WHERE A.CHARGE_SUB_CODE=B.SUB_TYPE_CODE AND D.ITEM_SUB_CAT LIKE '"+make+"' AND "+
                     // "       A.CHARGE_SUB_CODE LIKE '"+sub_type+"%' AND "+//A.SUB_MODEL_CODE LIKE '"+model+"%' AND "+
                     // "       /*TYPE_CODE='MAINTENANC'  AND* / A.SUB_MODEL_CODE = C.SUB_CODE AND C.MODEL_CODE = D.MODEL_CODE"+
                     // " ORDER BY	B.DESCRIPTION DESC");*/
                        
                        
                        // years    = (Double.parseDouble(terms)/Integer.parseInt(freq))-1;
                        // //out.println(CO_methods.getMaintenancApp(m_schema_name,"Y","","","","MAINTENANC"));
                        // //rs = stmt.executeQuery(CO_methods.getMaintenancApp(m_schema_name,"Y",make,model,sub_type,"MAINTENANC"));
                        // more = rs.next();
                        // if(more){
                            // int t=0;
                            // out.println("<table>");
                            // out.println("<tr class=tr_input >");
                            // out.println("<td >Type</td>");
                            // out.println("<td >Amount</td>");
                            // out.println("<td >%</td>");
                            // for(int j=0;j<=years;j++){ 					
                                // out.println("<td >");
                                // out.println("Year"+j+"");
                                // out.println("</td>");
                            // }	
                            // out.println("<td >Payee Code</td>");
                            // out.println("</tr>");	
                            
                            // while(more){
                                // out.println("<tr class=tr_input>");
                                // out.println("<td >"+rs.getString(1)+"<input type=hidden name=\"MAINTE_NAME_"+t+"\" value="+rs.getString(2)+"></td>");
                                // out.println("<td >"+nf.format(rs.getDouble(3))+" <input type=hidden name=\"AMOUNT_"+t+"\"      value="+rs.getString(3)+"></td>");
                                // out.println("<td >"+nf1.format(rs.getDouble(4))+"<input type=hidden name=\"AMOUNT_PER_"+t+"\"  value="+rs.getString(4)+"></td>");
                                // for(int j=0;j<=years;j++){ 					
                                    // out.println("<td>");
                                    // out.println("<input name=\"MAINTANENCE_"+t+"_"+j+"\" type=\"text\" maxlength=\"20\" class=\"txt_input1\" value=\"0\" onchange=format_num(document.Form1.MAINTANENCE_"+t+"_"+j+",'4');cal_count()>");
                                    // out.println("</td>");
                                // }
                                
                                // if(!rs.getString(5).equals("L")){
                                    // out.println("<td ><input type=hidden name=\"PAYEE_"+t+"\"  value='-' ></td>");
                                // }else{
                                    // out.println("<td ><input type=text name=\"PAYEE_"+t+"\" class=\"txt_input1\" onchange=getPAYEE(\""+t+"\")><input type=button name=\"Pay_help_"+t+"\" value=Help class=\"but_input\" onclick=\"PAY_help('1','10','0','PAYSql','27',document.Form1.PAYEE_"+t+",'"+t+"')\"></td>");
                                // }
                                // out.println("</tr>");
                                // t = t+1;
                                // more = rs.next();	
                            // }
                            // out.println("<input type=hidden name=hid_m_count value="+t+">");
                            // out.println("<input type=hidden name=hid_y_count value="+years+">");
                            // out.println("<table>");
                            
                            
                        // }else{
                            // out.println("<table>");
                            // out.println("<input type=hidden name=hid_m_count value=0>");
                            // out.println("<input type=hidden name=hid_y_count value=0>");
                            // out.println("<table>");
                        // }
                    // }											
                    
                // }else{
                    
                    // rs = stmt.executeQuery(" SELECT DISTINCT B.DESCRIPTION, A.CHARGE_SUB_CODE	,"+
                        // "        NVL(A.AMOUNT,'0'),NVL(A.INCREASE_DECREASE,'0'),NVL(ACCOUNT_TYPE,'-') "+
                        // " FROM  "+m_schema_name+".AF_CO_MAS_MAINTENANCE_RATE A, "+
                        // "       "+m_schema_name+".AF_CO_MAS_SUB_CHARGES B "+
                        // " WHERE A.CHARGE_SUB_CODE=B.SUB_TYPE_CODE AND "+
                        // "       A.ITEM_CAT_CODE LIKE '"+make+"' AND  "+
                        // "       A.CHARGE_SUB_CODE LIKE '"+sub_type+"%' "+
                        // " ORDER BY	B.DESCRIPTION DESC");
                    // /*(" SELECT DISTINCT B.DESCRIPTION, A.charge_sub_code	,NVL(A.AMOUNT,'0'),NVL(A.INCREASE_DECREASE,'0'),   "+
                    // "        D.ITEM_SUB_CAT,D.ITEM_SUB_CAT,'',A.ACTIVE_STATUS "+//A.MILEAGE_CODE,A.MAKE_CODE,A.SUB_MODEL_CODE
                            // " FROM  "+m_schema_name+".AF_CO_MAS_MAINTENANCE_RATE A,"+m_schema_name+".AF_CO_MAS_SUB_CHARGES B, "+
                // "       "+m_schema_name+".AF_CO_MAS_SUB_MODLE C,"+m_schema_name+".AF_CO_MAS_MODEL D "+
                            // " WHERE A.CHARGE_SUB_CODE=B.SUB_TYPE_CODE AND D.ITEM_SUB_CAT LIKE '"+make+"' AND "+
                            // "       A.CHARGE_SUB_CODE LIKE '"+sub_type+"%' AND "+//A.SUB_MODEL_CODE LIKE '"+model+"%' AND "+
                            // "       /*TYPE_CODE='MAINTENANC'  AND*  / A.SUB_MODEL_CODE = C.SUB_CODE AND C.MODEL_CODE = D.MODEL_CODE"+
                            // " ORDER BY	B.DESCRIPTION DESC");*/
                    
                    
                    // double years    = (Double.parseDouble(terms)/Integer.parseInt(freq))-1;
                    // //out.println(CO_methods.getMaintenancApp(m_schema_name,"Y","","","","MAINTENANC"));
                    // //rs = stmt.executeQuery(CO_methods.getMaintenancApp(m_schema_name,"Y",make,model,sub_type,"MAINTENANC"));
                    // boolean more = rs.next();
                    // if(more){
                        // int t=0;
                        // out.println("<table>");
                        // out.println("<tr class=tr_input >");
                        // out.println("<td >Type</td>");
                        // out.println("<td >Amount</td>");
                        // out.println("<td >%</td>");
                        // for(int j=0;j<=years;j++){ 					
                            // out.println("<td >");
                            // out.println("Year"+j+"");
                            // out.println("</td>");
                        // }	
                        // out.println("<td >Payee Code</td>");
                        // out.println("</tr>");	
                        
                        // while(more){
                            // out.println("<tr class=tr_input>");
                            // out.println("<td >"+rs.getString(1)+"<input type=hidden name=\"MAINTE_NAME_"+t+"\" value="+rs.getString(2)+"></td>");
                            // out.println("<td >"+nf.format(rs.getDouble(3))+" <input type=hidden name=\"AMOUNT_"+t+"\"      value="+rs.getString(3)+"></td>");
                            // out.println("<td >"+nf1.format(rs.getDouble(4))+"<input type=hidden name=\"AMOUNT_PER_"+t+"\"  value="+rs.getString(4)+"></td>");
                            // for(int j=0;j<=years;j++){ 					
                                // out.println("<td>");
                                // out.println("<input name=\"MAINTANENCE_"+t+"_"+j+"\" type=\"text\" maxlength=\"20\" class=\"txt_input1\" value=\"0\" onchange=format_num(document.Form1.MAINTANENCE_"+t+"_"+j+",'4');cal_count()>");
                                // out.println("</td>");
                            // }
                            
                            // if(!rs.getString(5).equals("L")){
                                // out.println("<td ><input type=hidden name=\"PAYEE_"+t+"\"  value='-'></td>");
                            // }else{
                                // out.println("<td ><input type=text name=\"PAYEE_"+t+"\" class=\"txt_input1\" onchange=getPAYEE(\""+t+"\")><input type=button name=\"Pay_help_"+t+"\" value=Help class=\"but_input\" onclick=\"PAY_help('1','10','0','PAYSql','27',document.Form1.PAYEE_"+t+",'"+t+"')\"></td>");
                            // }
                            // out.println("</tr>");
                            // t = t+1;
                            // more = rs.next();	
                        // }
                        // out.println("<input type=hidden name=hid_m_count value="+t+">");
                        // out.println("<input type=hidden name=hid_y_count value="+years+">");
                        // out.println("<table>");
                        
                        
                    // }else{
                        // out.println("<table>");
                        // out.println("<input type=hidden name=hid_m_count value=0>");
                        // out.println("<input type=hidden name=hid_y_count value=0>");
                        // out.println("<table>");
                    // }
                // }	
            // }
            
            // else if(m_chksql.equals("loadcharges")){
                
                // String SubItemCat  = request.getParameter("item_sub_cat");
                // String m_pr        = request.getParameter("price_no");
                // //out.println("SubItemCat="+SubItemCat+"---m_pr="+m_pr);
                // if(m_pr!=null && !m_pr.equals("")){ 
                    
                    // rs = stmt.executeQuery("SELECT DESCRIPTION,SUB_CHAGE_CODE,ROUND(AMOUNT),PERCENT_AMOUNT, "+
                        // "       DECODE(B.CHARGE_TYPE,'AMO','0','1'),NVL(ACCOUNT_TYPE,'-'),NVL(PAYEE_CODE,'-') "+
                        // "FROM   "+m_schema_name+".AF_MK_PRO_PRICING_CHARGES A,"+
                        // "       "+m_schema_name+".AF_CO_MAS_SUB_CHARGES B "+
                        // "WHERE  PRICING_NO = '"+m_pr+"' AND "+
                        // "       B.SUB_TYPE_CODE=A.SUB_CHAGE_CODE ");
                // }else{
                    
                    
                    // rs = stmt.executeQuery( " SELECT B.DESCRIPTION, A.SUB_TYPE_CODE,NVL(ROUND(A.AMOUNT),'0'),NVL(A.PERCENTAGE,'0')/100,DECODE(B.CHARGE_TYPE,'AMO','0','1'),   "+ //modified by nuwan de silva on 22-10-07 change the 0 to 1
                        // "        NVL(ACCOUNT_TYPE,'-'),'-',A.ITEM_SUB_CAT,A.FUAL_TYPE_CODE,A.FROM_DATE,A.TO_DATE,A.ACTIVE_STATUS "+
                        // " FROM  "+m_schema_name+".AF_CO_MAS_CHARGES_APPLICABLE A,"+m_schema_name+".AF_CO_MAS_SUB_CHARGES B "+
                        // " WHERE A.SUB_TYPE_CODE=B.SUB_TYPE_CODE AND A.ITEM_SUB_CAT LIKE '"+SubItemCat+"' AND "+
                        // //"       A.SUB_TYPE_CODE LIKE '"+SubChargType+"%' AND A.SUB_TYPE_CODE LIKE '"+Fual+"%' AND "+
                        // "  A.ACTIVE_STATUS='Y' AND "+
                        // " FROM_DATE<=TO_DATE(TO_CHAR(SYSDATE,'DD-MON-YYYY'),'DD-MON-YYYY') AND "+
                        // " TO_DATE>=TO_DATE(TO_CHAR(SYSDATE,'DD-MON-YYYY'),'DD-MON-YYYY') "+
                        // " ORDER BY	A.DEFAULT_VALUE DESC");																	
                // }
                
                // boolean more = rs.next();
                // if(more){
                    // int i=0;
                    // out.println("<table>");
                    
                    // while(more){
                        // out.println("<tr class=tr_input>");
                        // out.println("<td width=41% >"+rs.getString(1)+"<input type=hidden name=\"CHARGE_NAME_"+i+"\" value="+rs.getString(2)+"></td>");
                        // out.println("<td><input type=hidden name=\"CHARGE_PER_"+i+"\" value="+rs.getString(4)+">");
                        // out.println("<input name=\"CHARGE_"+i+"\" type=\"text\" maxlength=\"20\" class=\"txt_input1\" value=\""+nf.format(rs.getDouble(3))+"\" STYLE=\"{text-align:right;}\" onchange=chk_charge('"+i+"')>");
                        // out.println("<select name=\"CHARGE_TYPE_"+i+"\" class=\"txt_input1\" >");
                        // if(rs.getString(5).equals("0")){
                            // out.println("<OPTION value=\"AMO\" selected>Amortised</option>");
                            // out.println("<OPTION value=\"INV\">Upfront</option>");
                        // }else{
                            // out.println("<OPTION value=\"AMO\">Amortised</option>");
                            // out.println("<OPTION value=\"INV\" selected>Upfront</option>");
                        // }
                        // out.println("</SELECT>");
                        // if(!rs.getString(6).equals("L")){
                            // out.println("<input type=hidden name=\"C_PAYEE_"+i+"\"  value='-'></td>");
                        // }else{
                            // out.println("<input type=text name=\"C_PAYEE_"+i+"\" class=\"txt_input1\" value=\""+rs.getString(7)+"\" onchange=CgetPAYEE(\""+i+"\")><input type=button name=\"CPay_help_"+i+"\" value=Help class=\"but_input\" onclick=\"CPAY_help('1','10','0','PAYSql','12',document.Form1.C_PAYEE_"+i+",'"+i+"')\"></td>");
                        // } 
                        // out.println("</tr>");
                        // i = i+1;
                        // more = rs.next();	
                    // }
                    
                    // out.println("<tr class=tr_input>");
                    // out.println("<td >Total</td>");
                    // out.println("<td>");
                    // out.println("<input name=\"Total_CHARGE\" type=\"text\" maxlength=\"20\" class=\"txt_input1\" value=\""+nf.format(0)+"\" STYLE=\"{text-align:right;}\" >");
                    // out.println("</td>");
                    // out.println("</tr>");
                    // out.println("<input type=hidden name=\"c_c_count\" value=\""+i+"\"><table>");
                    
                // }else{
                    // out.println("<table><tr class=tr_input><td></td><td></td></tr>");
                    // out.println("<input type=hidden name=\"c_c_count\" value=\"0\"><table>");
                    
                // }
            // }
            // /*
            // else if(m_chksql.equals("chkClient")){
            
              // String clientName = request.getParameter("clientName");
                // String addrees1   = request.getParameter("addrees1");
                // String city_code 	= request.getParameter("city_code");
                // String tel_no 		= request.getParameter("tel_no");
                // String email 			= request.getParameter("email");
                // String nic_no 		= request.getParameter("nic_no");
                // String bc_no		  = request.getParameter("bc_no");
                
              // rs = stmt.executeQuery( " SELECT FULL_NAME, ADDRESS1, CITY_CODE,TEL_NO,EMAIL,NIC_NO "+
                                                                // " FROM "+m_schema_name+".AF_CO_MAS_CLIENT "+
                                                                // " WHERE FULL_NAME = UPPER('"+clientName+"') OR "+
                                                                // "       (ADDRESS1 = UPPER('"+addrees1+"') AND "+
                                                                // "	      CITY_CODE = UPPER('"+city_code+"')) OR "+
                                                                // "       TEL_NO    = UPPER('"+tel_no+"') OR "+
                                                                // "	      EMAIL     = UPPER('"+email+"') OR "+
                                                                // "       NIC_NO    = UPPER('"+nic_no+"') OR "+
                                                                // "	      BUSINESS_CERTIFICATE_NO = UPPER('"+bc_no+"') AND "+
                                    // "       ACTIVE_STATUS='Y'");
                // boolean more = rs.next();
                // if(more){
                   // out.println(rs.getString(1)+"@#"+rs.getString(2)+"@#"+rs.getString(3)+"@#"+rs.getString(4)
                                   // +"@#"+rs.getString(5)+"@#"+rs.getString(6)+"@##@");
                // }	    
                        
            // }
            // */
            // else if(m_chksql.equals("get_outflow_cal")){
                
                // String m_rate		    = request.getParameter("rate");
                // String m_value      = request.getParameter("value");
                // String m_terms	    = request.getParameter("terms");
                // String m_freq		    = request.getParameter("freq");
                // String m_type       = request.getParameter("type");
                // String m_vat_per    = request.getParameter("tax");
                // String m_install    = request.getParameter("installments");
                // String m_factor     = request.getParameter("factor");
                // String m_pracent    = request.getParameter("pracent");
                // String m_percentage = request.getParameter("percentage");
                // String m_ami        = request.getParameter("ami");
                // String m_vat_app    = request.getParameter("tax_app");
                // String m_tax_rent   = request.getParameter("tax_rent");
                
                // String m_sup_cr_per = request.getParameter("supcrper");
                // String m_nibsm      = request.getParameter("nibsm");
                // String m_residual   = request.getParameter("residual");
                // String m_sup_credit = request.getParameter("sup_cr");
                // String m_other_cha  = request.getParameter("other_cha");
                // String m_maintenance= request.getParameter("maintan");
                // String m_int_base_m = request.getParameter("nitbasemar");
                // String m_nitmar     = request.getParameter("nitmar");
                
                // //String m_last_rent  = request.getParameter("last_rent");
                // String m_cash_out   = request.getParameter("cashout");
                // String m_trn_sub    = request.getParameter("trnsub");
                // String m_int_type   = request.getParameter("nittype");
                // String m_trn_type   = request.getParameter("trn_type");
                // String m_option     = request.getParameter("option");
                // String m_vat_val    = request.getParameter("vat_val");
                // String m_gro_val    = request.getParameter("gro_val");
                // String m_rental     = request.getParameter("rental");
                // String m_1rental    = request.getParameter("1rental");
                // /*			
                // //out.println("m_install="+m_install);											
        // callstmt1 = conn.prepareCall( "BEGIN "+m_schema_name+"."+ 
                            // "AF_MK_TEMP_PRICE_SAVE(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12,:13,:14,:15,:16,:17,:18,:19,:20,:21,:22,:23,:24,:25,:26,:27,:28,:29,:30);END;");
                // callstmt1.setString(1 ,m_terms);
        // callstmt1.setString(2 ,m_freq);
        // callstmt1.setString(3 ,m_install);
                // callstmt1.setString(4 ,m_pracent);
                // callstmt1.setString(5 ,m_factor);
        // callstmt1.setString(6 ,m_type);
                // callstmt1.setString(7 ,m_username);
                // callstmt1.setString(8 ,m_rate);
                // callstmt1.setString(9 ,m_value);
                // callstmt1.setString(10,m_vat_per);
                // callstmt1.setString(11,m_percentage);	
                // callstmt1.setString(12,m_vat_app);	
                // callstmt1.setString(13,m_ami);	
                // callstmt1.setString(14,m_sup_credit);	
                // callstmt1.setString(15,m_sup_cr_per);	
                // callstmt1.setString(16,m_nibsm);	
                // callstmt1.setString(17,m_residual);	
                // callstmt1.setString(18,m_other_cha);	
                // callstmt1.setString(19,m_maintenance);	
                // callstmt1.setString(20,m_option);	
                // callstmt1.setString(21,m_trn_type);
                // callstmt1.setString(22,m_trn_sub);
                // callstmt1.setString(23,m_int_type);
                // callstmt1.setString(24,m_nitmar);
                // callstmt1.setString(25,m_cash_out);
                // callstmt1.setString(26,m_vat_val);
                // callstmt1.setString(27,m_gro_val);
                // callstmt1.setString(28,m_rental);
                // callstmt1.setString(29,m_1rental);	
                // callstmt1.setString(30,m_tax_rent);
                // //out.println("t5");
            // callstmt1.execute();
                // //out.println("t6");
            // */
                
                // rs = stmt.executeQuery ("SELECT "+m_rate+","+m_rate+"/100,("+m_rate+"/100)/"+m_freq+","+m_freq+","+m_terms+","+m_value+","+
                    // "       "+m_schema_name+".AF_CO_CAL_FACTOR("+m_rate+","+m_freq+","+m_terms+",'"+m_type+"','"+m_ami+"'),"+
                    // "       "+m_vat_per+",/*"+m_freq+"**/"+m_terms+",'"+m_ami+"' "+
                    // " FROM DUAL");
                
                // /*
                        // out.println("<html><head>");
                // out.println("<title>PMT Value - Formulation</title></head>");
                // out.println("<body bgcolor='white'>");
                // out.println("<form name='Form1'>");
                // out.println("<br>");
                // */
                
                // out.println("<html>");
                // out.println("<head>");
                // out.println("<title>Asset Financing System</title>    ");
                // out.println("<link href=\""+m_html_client_url+"/css/Asset_Financing_System.css\" rel=\"stylesheet\" type=\"text/css\" >");
                // out.println("</head>");
                // out.println("<Script>");
                // out.println("var m_bsubmit = '0';");
                // out.println("var arr_assign= new Array();");
                // out.println("var m_send_val= '';");
                
                // out.println("function makeRequest(url,opt,opt1) {");
                // out.println("var http_request = false;");
                // out.println("if (window.XMLHttpRequest) {"); // Mozilla, Safari,...
                // out.println("    http_request = new XMLHttpRequest();");
                // out.println("    if (http_request.overrideMimeType) {");
                // out.println("        http_request.overrideMimeType('text/xml');");
                // out.println("    }");
                // out.println("} else if (window.ActiveXObject) { ");// IE
                // out.println("    try {");
                // out.println("        http_request = new ActiveXObject(\"Msxml2.XMLHTTP\");");
                // out.println("    } catch (e) {");
                // out.println("        try {");
                // out.println("            http_request = new ActiveXObject(\"Microsoft.XMLHTTP\");");
                // out.println("        } catch (e) {}");
                // out.println("    }");
                // out.println("}");
                // out.println("if (!http_request) {");
                // out.println("    alert('Giving up :( Cannot create an XMLHTTP instance');");
                // out.println("    return false;");
                // out.println("}");
                
                // out.println(" if(opt1=='YES'){");
                // out.println("  http_request.onreadystatechange = function() { alertContents(http_request); };");
                // out.println("  http_request.open('POST',url, true);");
                // out.println("	 http_request.setRequestHeader(\"Content-Type\",\"application/x-www-form-urlencoded\");");
                // //out.println("  alert(construct_URL(\"get_advance_price_cal\",opt));");
                // //out.println("  window.open(url+'?'+construct_URL(\"get_advance_price_cal\",opt));");
                // out.println("	 m_fact_val = \"\";"); 
                // out.println("	 m_prac_val = \"\";"); 
                // out.println("  m_percentage = \"\";"); 
                // out.println("  m_char_val = \"0\";"); 
                // out.println("  m_main_val = \"0\";"); 
                // out.println("  m_mainten  = \"\";"); 
                // out.println("  m_cash_out = \"\";"); 
                // out.println("  m_cashOAmo = \"0\";"); 
                // out.println("  m_vat_val  = \"0\";"); 
                // out.println("  m_gro_val  = \"0\";"); 
                
                
                // out.println("	 document.Form1.CASH_OUTFLOW.value=m_cash_out;");
                // out.println("	 window.opener.document.Form1.CASH_OUTFLOW.value=m_cash_out;");
                // //out.println("	 alert(m_cashOAmo+'!='+document.Form1.GROSS_AMOUNT.value);");
                // out.println("	 if(parseFloat(m_cashOAmo)!=parseFloat(document.Form1.GROSS_AMOUNT.value)){");
                // out.println("	   alert('Please check the cash outflow amounts.');");
                // out.println("	   return false;");
                // out.println("	 }");
                
                // out.println("	 m_send_val = \"chksql=get_outflow_recal&rate=\"+unformat_noobject(document.Form1.RATE.value)+\"&value=\"+unformat_noobject(document.Form1.GROSS_AMOUNT.value)+");
                // out.println("	              \"&terms=\"+unformat_noobject(document.Form1.PERIOD.value)+\"&freq=\"+document.Form1.REPAYMENT_INTERVAL.value+\"&type=\"+document.Form1.PAYMENT_MODE.value+\"&option=\"+");
                // out.println("	              \"&last_rent=&sup_cr=\"+document.Form1.SUPPLIER_CREDIT.value+\"&residual=\"+unformat_noobject(document.Form1.RESIDUAL_VALUE.value)+");
                // out.println("	              \"&supcrper=0&cashout=\"+document.Form1.CASH_OUTFLOW.value+\"&trnsub=\"+document.Form1.TRANSACTION_SUB.value+\"&nittype=\"+document.Form1.INTEREST_TYPE.value+\"&nibsm=\"+unformat_noobject(document.Form1.NIBSM.value)+");
                // out.println("	              \"&trn_type=\"+document.Form1.TRANSACTION_TYPE.value+\"&tax=\"+document.Form1.VAT_PERCENTAGE.value+\"&tax_app=\"+document.Form1.VAT_PER_APP.value+\"&tax_rent=\"+document.Form1.VAT_PER_RENT.value+\"\";");
                
                // out.println("	 if(document.Form1.INTEREST_TYPE.value=='VARIABLE'){");
                // out.println("	   m_send_val = m_send_val+\"&nitbasemar=\"+document.Form1.INTEREST_BASE.value+\"&nitmar=\"+document.Form1.INTEREST_MARGIN.value+\"&installments=\";");						
                // out.println("	 }else{");
                // out.println("	   m_send_val = m_send_val+\"&nitbasemar=&nitmar=&installments=\";");						
                // out.println("	 }");
                
                // out.println("  for(i=0;i<parseFloat(document.Form1.hid_count.value);i++){");
                // out.println("    m_send_val   = m_send_val+unformat_noobject(document.Form1.elements[\"NETAMT\"+i].value)+\"@\";");
                // out.println("    m_fact_val   = m_fact_val+/*unformat_noobject*/(document.Form1.elements[\"FACTOR\"+i].value)+\"@\";");
                // out.println("    m_prac_val   = m_prac_val+unformat_noobject(document.Form1.elements[\"PRACENT\"+i].value)+\"@\";");
                // out.println("    m_cash_out   = m_cash_out+unformat_noobject(document.Form1.elements[\"CASHOUT\"+i].value)+\"@\";");
                // out.println("    m_cashOAmo   = parseFloat(m_cashOAmo)+parseFloat(unformat_noobject(document.Form1.elements[\"CASHOUT\"+i].value));");
                // out.println("    m_vat_val    = m_vat_val+unformat_noobject(document.Form1.elements[\"VATAMT\"+i].value)+\"@\";");
                // out.println("    m_gro_val    = m_gro_val+unformat_noobject(document.Form1.elements[\"GROSS\"+i].value)+\"@\";");
                
                // out.println("    m_percentage = m_percentage+unformat_noobject(document.Form1.elements[\"PERCENTAGE\"+i].value)+\"@\";");
                // //out.println("    alert('m_percentage'+m_percentage);");
                // out.println("	 }");
                
                // out.println("    m_send_val = m_send_val+\"&maintan=\"+document.Form1.MAINTANENCE.value+\"&factor=\"+m_fact_val+\"&pracent=\"+m_prac_val+\"&ami=\"+document.Form1.AMI.value+\"&other_cha=\"+document.Form1.CHARGE.value+\"&percentage=\"+m_percentage+");
                // out.println("                 \"&vat_val=\"+m_vat_val+\"&gro_val=\"+m_gro_val;");
                // //out.println("  window.open(url+'?'+m_send_val);");
                // out.println("  document.write(url+'?'+m_send_val);");
                
                // out.println("  http_request.send(m_send_val);");
                // out.println(" }else{");
                
                // out.println("  http_request.onreadystatechange = function() { alertGetContents(http_request,opt); };");
                // out.println("  http_request.open('GET',url, true);");
                // //out.println("  window.open(url);");
                // //out.println("  alert('opt-'+opt);");
                // out.println("  http_request.send(null);");
                // out.println(" }");
                // out.println("}");
                
                
                // out.println("function construct_URL(chk_sql,opt) {");
                // out.println("	 m_send_val = \"chksql=\"+chk_sql+\"&rate=\"+unformat_noobject(document.Form1.RATE.value)+\"&value=\"+unformat_noobject(document.Form1.GROSS_AMOUNT.value)+");
                // out.println("	              \"&terms=\"+unformat_noobject(document.Form1.PERIOD.value)+\"&freq=\"+document.Form1.REPAYMENT_INTERVAL.value+\"&type=\"+document.Form1.PAYMENT_MODE.value+\"&option=\"+opt+");
                // out.println("	              \"&last_rent=&sup_cr=\"+document.Form1.SUPPLIER_CREDIT.value+\"&residual=\"+unformat_noobject(document.Form1.RESIDUAL_VALUE.value)+");
                // out.println("	              \"&supcrper=0&cashout=\"+document.Form1.CASH_OUTFLOW.value+\"&trnsub=\"+document.Form1.TRANSACTION_SUB.value+\"&nittype=\"+document.Form1.INTEREST_TYPE.value+\"&nibsm=\"+unformat_noobject(document.Form1.NIBSM.value)+");
                // out.println("	              \"&trn_type=\"+document.Form1.TRANSACTION_TYPE.value+\"&tax=\"+document.Form1.VAT_PERCENTAGE.value+\"&tax_app=\"+document.Form1.VAT_PER_APP.value+\"\";");
                
                // out.println("	 if(document.Form1.INTEREST_TYPE.value=='VARIABLE'){");
                // out.println("	   m_send_val = m_send_val+\"&nitbasemar=\"+document.Form1.INTEREST_BASE.value+\"&nitmar=\"+document.Form1.INTEREST_MARGIN.value+\"&installments=\";");						
                // out.println("	 }else{");
                // out.println("	   m_send_val = m_send_val+\"&nitbasemar=&nitmar=&installments=\";");						
                // out.println("	 }");
                // out.println("	 m_fact_val = \"\";"); 
                // out.println("	 m_prac_val = \"\";"); 
                // out.println("  m_percentage = \"\";"); 
                // out.println("  m_char_val = \"0\";"); 
                // out.println("  m_main_val = \"0\";"); 
                // out.println("  m_mainten  = \"\";"); 
                // out.println("	 m_vat_val = \"\";"); 
                // out.println("	 m_gro_val = \"\";"); 
                
                // out.println("  for(i=0;i<parseFloat(document.Form1.hid_count.value);i++){");
                // out.println("    m_send_val   = m_send_val+unformat_noobject(document.Form1.elements[\"NETAMT\"+i].value)+\"@\";");
                // out.println("    m_fact_val   = m_fact_val+/*unformat_noobject*/(document.Form1.elements[\"FACTOR\"+i].value)+\"@\";");
                // out.println("    m_prac_val   = m_prac_val+unformat_noobject(document.Form1.elements[\"PRACENT\"+i].value)+\"@\";");
                // out.println("    m_vat_val    = m_vat_val+unformat_noobject(document.Form1.elements[\"VATAMT\"+i].value)+\"@\";");
                // out.println("    m_gro_val    = m_gro_val+unformat_noobject(document.Form1.elements[\"GROSS\"+i].value)+\"@\";");
                
                // out.println("    m_percentage = m_percentage+unformat_noobject(document.Form1.elements[\"PERCENTAGE\"+i].value)+\"@\";");
                // //out.println("    alert('m_percentage'+m_percentage);");
                // out.println("	 }");
                
                // out.println("    m_send_val = m_send_val+\"&maintan=\"+document.Form1.MAINTANENCE.value+\"&factor=\"+m_fact_val+\"&pracent=\"+m_prac_val+\"&ami=\"+document.Form1.AMI.value+\"&other_cha=\"+m_char_val+\"&percentage=\"+m_percentage+");
                // out.println("                 \"&vat_val=\"+m_vat_val+\"&gro_val=\"+m_gro_val;");
                // out.println("    return m_send_val;");
                // out.println("	 }");
                
                
                // out.println("function alertContents(http_request) {");
                // //out.println(" alert('test');");
                // out.println(" if (http_request.readyState == 4) {");
                // out.println("    if (http_request.status == 200) {");
                // //out.println("      alert(http_request.responseText);");
                // out.println("         price_cal.innerHTML=http_request.responseText; ");
                // out.println("         for(i=0;i<100000000;i++){o=i;}");
                // out.println("         asign_div();");
                // out.println("    } else {");
                // out.println("        alert('There was a problem with the request.');");
                // out.println("    }");
                // out.println(" }");
                // out.println("}");
                
                // out.println("function alertGetContents(http_request,opt) {");
                // //out.println(" alert('test--'+opt);");
                // out.println(" if (http_request.readyState == 4) {");
                // out.println("    if (http_request.status == 200) {");
                // out.println("      if(opt==\"Main\"){");
                // //out.println("         alert(http_request.responseText);");
                // out.println("         main_id.innerHTML=http_request.responseText; ");
                // out.println("      }else if(opt==\"SubType\"){");
                // //out.println("         main_id.innerHTML=http_request.responseText; ");
                // out.println("         Curr_no_opts=document.Form1.TRANSACTION_SUB.options;");
                // out.println("         Curr_no_opts.length=0;");
                // out.println("         m_string=http_request.responseText; ");
                // out.println("         m_index =m_string.indexOf(\"~#@\");"); 
                // out.println("         z=0;");
                // out.println("         while(m_index!=-1){");
                // out.println("           arr_assign[z]  = m_string.substring(0,m_string.indexOf(\"~#@\")); ");
                // out.println("           m_string       = m_string.substring(m_string.indexOf(\"~#@\")+3); ");
                // out.println("           z              = z+1;");
                // out.println("           m_index =m_string.indexOf(\"~#@\");"); 
                // out.println("         }");
                // out.println("         for(x=0; x<arr_assign.length; x++){");
                // out.println("           m_string1      = arr_assign[x].substring(0,arr_assign[x].indexOf(\"@#\")); ");
                // out.println("           m_string2      = arr_assign[x].substring(arr_assign[x].indexOf(\"@#\")+2); ");
                // out.println("           m_string       = m_string2.substring(0,m_string2.indexOf(\"@#\")); ");
                // //out.println("           alert('m_string1='+m_string1+'--m_string='+m_string+'');");
                // out.println("           Curr_no_opts[x]= new Option(m_string,m_string1);");
                // out.println("         }");
                
                // out.println("      }");
                // out.println("    } else {");
                // out.println("        main_id.innerHTML='';");
                // out.println("    }");
                // out.println(" }");
                // out.println("}");
                // //End Of Checking Values
                
                // out.println("function asign_div(){");
                // out.println("format_num(document.Form1.sun_g_nt,0)");
                // out.println("format_num(document.Form1.sun_rent,0)");
                // out.println("format_num(document.Form1.sun_p_nt,0)");
                // out.println("format_num(document.Form1.sun_fact,4)");
                // out.println("g_re.innerHTML   =document.Form1.sun_g_nt.value");
                // out.println("n_re.innerHTML   =document.Form1.sun_rent.value");
                // out.println("p_va.innerHTML   =document.Form1.sun_p_nt.value");
                // out.println("m_fact.innerHTML =document.Form1.sun_fact.value");
                // out.println("document.Form1.hid_count.value=document.Form1.hid_cou.value;");
                // out.println("}");
                
                // //Help Function
                // out.println("function MyDialog(){");
                // out.println("this.valout   = new Array(10);");
                // out.println("}");	
                // /*
                // out.println("function get_help(Start,End,Hid_No,Crit,Sql,IfCount) {");			
                
                // out.println("oBj = new MyDialog();");
                // out.println("oBj.valout[3]  = \" \";");
                // out.println("oBj.valout[4]  = \" \";");
                // out.println("oBj.valout[5]  = \" \";");
                // //out.println("window.open('"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_MK_Help_Servlet?class_in="+m_client_name+"AF_MK_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=');");
                // out.println("popupwin = window.showModalDialog('"+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_MK_Help_Servlet?class_in="+m_client_name+"AF_MK_help_select&Sql_in='+Sql+'&Start_in='+Start+'&End_in='+End+'&Crit_In='+Crit+'&Hid_No='+Hid_No+'&Max_in='+Hid_No+'&Args=', oBj,\"dialogWidth:25em; dialogHeight:26em; center:yes; status:no\");");
                // //out.println("alert('333333--'+oBj.valout[0]+'--'+oBj.valout[1]+'--');");
                
                // out.println("if(oBj.valout[0]=='Next')  {");
                // out.println("Next(oBj.valout[1],oBj.valout[2],Hid_No,Sql,IfCount);");
                // out.println("}");
                // out.println("else if  (oBj.valout[0]=='Prev') {");
                // out.println("Prev(oBj.valout[1],oBj.valout[2],Hid_No,Sql,IfCount);");
                // out.println("}		");
                // out.println("else if(oBj.valout[0] == 'Exit'){");
                // out.println("}");
                // out.println("else if(oBj.valout[0] != '' && oBj.valout[1] != '' && oBj.valout[1] != 'undefined'){");
                // out.println("if(IfCount=='1'){"); 
                // out.println("client_assign(oBj);");
                // //out.println("load_data(document.Form1.txt_aff_code.value);");
                // out.println("}");
                // out.println("else if(IfCount=='2'){"); 
                // out.println("modle_assign(oBj);");
                // out.println("}");
                // out.println("else if(IfCount=='3'){"); 
                // out.println("");
                // out.println("}");
                // out.println("else if(IfCount=='4'){"); 
                // out.println("vendor_assign(oBj);");
                // out.println("}");
                // out.println("else if(IfCount=='5'){"); 
                // out.println("mk_trn_assign(oBj);");
                // out.println("}");
                // out.println("else if(IfCount=='6'){"); 
                // out.println("mk_sub_trn_assign(oBj);");
                // out.println("}");
                // out.println("else if(IfCount=='7'){"); 
                // out.println("inq_assign(oBj);");
                // out.println("}");
                // out.println("else if(IfCount=='7'){"); 
                // out.println("price_assign(oBj);");
                // out.println("}");
                
                
                // out.println("}");
                // out.println("else if(oBj.valout[4] != \" \"){ ");
                // out.println("Crit = oBj.valout[4];");
                // out.println("criteria_1('1','10','1',oBj.valout[4],Sql,IfCount);");
                // out.println("}	");
                // out.println("}");	
                
                // out.println("function Prev(Start,End,Hid_No,Crit,Sql,IfCount){");
                // out.println("get_help(Start,End,Hid_No,Crit,Sql,IfCount)");
                // out.println("}");
                
                // out.println("function Next(Start,End,Hid_No,Crit,Sql,IfCount){");
                // out.println("get_help(Start,End,Hid_No,Crit,Sql,IfCount)");
                // out.println("}");
                
                // out.println("function criteria_1(Start,End,Hid_No,Crit,Sql,IfCount){");
                // out.println("get_help(Start,End,Hid_No,Crit,Sql,IfCount);");
                // out.println("}");		
                // //client Help
                // out.println("function model_help(Start,End,Hid_No,Sql,IfCount){");
                // out.println("Crit=document.Form1.ASSET_MAKE.value+\"@\"+document.Form1.SUB_MODEL.value+\"@Y@\";");
                // out.println("get_help(Start,End,Hid_No,Crit,Sql,IfCount);");
                // out.println("}");		
                // out.println("function modle_assign(oBj){");
                // out.println(" document.Form1.SUB_MODEL.value      =oBj.valout[2]");
                // out.println(" document.Form1.VAT_PERCENTAGE.value =oBj.valout[4]");
                // out.println(" document.Form1.MODEL_CODE.value =oBj.valout[4]");
                // out.println("}");		
                // //Marketing Officer
                // out.println("function vendor_help(Start,End,Hid_No,Sql,IfCount){");
                // out.println("Crit=document.Form1.VENDOR.value+\"@Y@\";");
                // out.println("get_help(Start,End,Hid_No,Crit,Sql,IfCount);");
                // out.println("}");		
                // out.println("function vendor_assign(oBj){");
                // out.println(" document.Form1.VENDOR.value =oBj.valout[2]");
                // out.println(" document.Form1.VENDOR_CODE.value =oBj.valout[1]");
                // out.println("}");				
                
                // out.println("function trn_help(Start,End,Hid_No,Sql,IfCount){");
                // out.println("Crit=document.Form1.TRANSACTION_CODE.value+\"@\";");
                // out.println("get_help(Start,End,Hid_No,Crit,Sql,IfCount);");
                // out.println("}");		
                // out.println("function mk_trn_assign(oBj){");
                // out.println(" document.Form1.TRANSACTION_CODE.value =oBj.valout[2]");
                // //out.println(" document.Form1.txt_aff_desc.value =oBj.valout[0]");
                // out.println("}");		
                
                // out.println("function sub_trn_help(Start,End,Hid_No,Sql,IfCount){");
                // out.println("Crit=document.Form1.TRANSACTION_SUB.value+\"@\"+document.Form1.TRANSACTION_TYPE.value+\"@A@\";");
                // out.println("get_help(Start,End,Hid_No,Crit,Sql,IfCount);");
                // out.println("}");		
                // out.println("function mk_sub_trn_assign(oBj){");
                // out.println(" document.Form1.TRANSACTION_SUB.value =oBj.valout[2]");
                // //out.println(" document.Form1.txt_aff_desc.value =oBj.valout[0]");
                // out.println("}");		
                
                // out.println("function inq_help(Start,End,Hid_No,Sql,IfCount){");
                // out.println("Crit=document.Form1.INQ_NO.value+\"@\";");
                // out.println("get_help(Start,End,Hid_No,Crit,Sql,IfCount);");
                // out.println("}");		
                // out.println("function inq_assign(oBj){");
                // out.println(" document.Form1.INQ_NO.value =oBj.valout[2]");
                // out.println("}");		
                
                // out.println("function price_help(Start,End,Hid_No,Sql,IfCount){");
                // out.println("Crit=document.Form1.PRICE_NO.value+\"@\";");
                // out.println("get_help(Start,End,Hid_No,Crit,Sql,IfCount);");
                // out.println("}");		
                // out.println("function price_assign(oBj){");
                // out.println(" document.Form1.PRICE_NO.value =oBj.valout[2]");
                // out.println("}");		
                // */
                
                // //End of Help Function
                // //change required DIV
                // /*
                // out.println("function change_div(){");
                // out.println(" if(document.Form1.CLIENT_TYPE.options[document.Form1.CLIENT_TYPE.selectedIndex].text!='Individual' &&");
                // out.println("    document.Form1.CONTACT_PERSON.value==''){ ");
                // out.println("   conp.innerHTML=\"Contact Person *\";");
                // out.println(" }else{");
                // out.println("   conp.innerHTML=\"Contact Person\";");
                // out.println(" }");
                // out.println("}");
                
                
                // //end of DIV change
                
                // out.println("function change_repay(m_type){");
                // out.println(" if(m_type=='TYPE'){");
                // out.println("    document.Form1.REPAYMENT_INTERVAL.selectedIndex=document.Form1.REPAYMENT_TYPE.selectedIndex ");
                // out.println(" }else{");
                // out.println("    document.Form1.REPAYMENT_TYPE.selectedIndex=document.Form1.REPAYMENT_INTERVAL.selectedIndex ");
                // out.println(" }");
                // out.println("}");
                // */
                // //Main Button Action
                // //Submit
                // out.println("function before_submit(){");
                // out.println(" m_str='<table><tr><td colspan=2>Cash Outflow Details</td></tr> <tr><td>Month</td><td>Amount</td></tr>'; ");
                // out.println(" m_cashOAmo ='0'; ");
                // out.println(" m_cash_out =''; ");
                // out.println(" m_cashcount='0'; ");
                
                // out.println("   for(i=0;i<parseFloat(document.Form1.hid_count.value);i++){");
                // out.println("    if(parseFloat(document.Form1.elements[\"CASHOUT\"+i].value)!=0){");
                // out.println("     m_str      = m_str+'<tr><td><input type=text name=\"month'+m_cashcount+'\"       value='+document.Form1.elements[\"INSTALLMENT\"+i].value+' class=\"txt_input2\" disabled></td>';");
                // out.println("     m_str      = m_str+'    <td><input type=text name=\"cash_amount'+m_cashcount+'\" value='+document.Form1.elements[\"CASHOUT\"+i].value+'     class=\"txt_input2\" disabled></td></tr>';");
                // out.println("     m_cashOAmo = parseFloat(m_cashOAmo)+parseFloat(unformat_noobject(document.Form1.elements[\"CASHOUT\"+i].value));");
                // out.println("     m_cashcount= parseFloat(m_cashcount)+1");
                // out.println("    }");
                // out.println("    m_cash_out   = m_cash_out+unformat_noobject(document.Form1.elements[\"CASHOUT\"+i].value)+\"@\";");
                // out.println("   }");
                // out.println("	 if(parseFloat(m_cashOAmo)!=parseFloat(document.Form1.GROSS_AMOUNT.value)){");
                // out.println("	   alert('Please check the cash outflow amounts.');");
                // out.println("	   return false;");
                // out.println("	 }");
                // out.println("  if(confirm(\"Are you sure you want to Save?\")){  ");
                // out.println("  	window.opener.document.Form1.CASH_OUTFLOW.value=m_cash_out;");
                // out.println("   m_str = m_str+'</table>'; ");
                // out.println("   window.opener.outflow_id.innerHTML = m_str; ");
                // out.println("   window.opener.document.Form1.CAL_COUNT.value = '1'; ");
                // out.println("   window.opener.document.Form1.OUT_COUNT.value = m_cashcount; ");
                // out.println("   window.opener.befor_cal(\"NO\",\"YES\"); ");
                // out.println("   window.close();");
                // out.println("  }");
                // out.println("}");
                // //end of Submit Function
                
                
                // out.println("function befor_new(){");
                // out.println(" if(confirm(\"Are you sure you want to enter new record?\")){  ");
                // //out.println("  Form1.reset() ;  ");
                // out.println("  document.Form1.OPTION_NAME.value=\"NEW\";");
                // out.println("  document.Form1.OPTION_DESC.value=\"New\";");
                // out.println(" }  ");
                // out.println("}");
                
                // out.println("function befor_back(){");
                // //out.println(" if(confirm(\"Are You Sure?\")){  ");
                // out.println("   close_window(); ");
                // //out.println("  top.frames[1].location=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_CO_FollowupAlert?chksql=main_page\";");
                // //out.println("  Form1.reset() ;  ");
                // //out.println("  document.Form1.OPTION_NAME.value=\"MOD\";");
                // //out.println("  document.Form1.OPTION_DESC.value=\"B\";");
                // //out.println("  document.Form1.PRICE_NO.disabled=true;");
                // //out.println("  window.close()");
                // //out.println(" }  ");
                // out.println("}");
                
                // out.println("function befor_modify(){");
                // out.println(" if(confirm(\"Are you sure you want to modify record?\")){  ");
                // //out.println("  Form1.reset();   ");
                // out.println("  document.Form1.OPTION_NAME.value=\"MOD\";");
                // out.println("  document.Form1.OPTION_DESC.value=\"Edit\";");
                // //out.println("  alert(document.Form1.OPTION_DESC.value);");
                // out.println("  document.Form1.PRICE_NO.disabled=false;");
                // out.println("  document.Form1.inqu_help.disabled=false;");
                // out.println(" }  ");
                // out.println("}");
                
                // out.println("function befor_active(){");
                // out.println(" if(confirm(\"Are You Sure?\")){  ");
                // //out.println("  Form1.reset() ;  ");
                // out.println("  document.Form1.OPTION_NAME.value=\"ACT\";");
                // out.println("  document.Form1.OPTION_DESC.value=\"Re-activate\";");
                // //out.println("  alert(document.Form1.OPTION_DESC.value);");
                // out.println("  document.Form1.PRICE_NO.disabled=false;");
                // out.println("  document.Form1.inqu_help.disabled=false;");
                // out.println(" }  ");
                // out.println("}");
                
                // out.println("function befor_deactive(){");
                // out.println(" if(confirm(\"Are You Sure?\")){  ");
                // //out.println("  Form1.reset();   ");
                // out.println("  document.Form1.OPTION_NAME.value=\"INA\";");
                // out.println("  document.Form1.OPTION_DESC.value=\"De-activate\";");
                // out.println("  document.Form1.PRICE_NO.disabled=false;");
                // out.println("  document.Form1.inqu_help.disabled=fales;");
                // out.println(" }  ");
                // out.println("}");
                
                // out.println("function befor_cal(m_stat,opt) {");
                // //out.println(" if(document.Form1.RATE.value!=\"\" && document.Form1.NET_AMOUNT.value !=\"\" && document.Form1.VAT_PERCENTAGE.value !=\"\" && document.Form1.PERIOD.value !=\"\" && document.Form1.GROSS_AMOUNT.value!=\"\"){");
                // out.println("  document.Form1.CAL_COUNT.value=0;");
                // out.println("  if(opt==\"YES\"){");
                // out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_MK_Price\";");
                // out.println(" }else{");
                // //out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_MK_Price?chksql=get_basic_price_cal&rate=\"+unformat_noobject(document.Form1.RATE.value)+\"&value=\"+unformat_noobject(document.Form1.GROSS_AMOUNT.value)+\"&terms=\"+document.Form1.PERIOD.value+\"&freq=\"+document.Form1.REPAYMENT_INTERVAL.value+\"&type=\"+document.Form1.PAYMENT_MODE.value+\"&ami=\"+document.Form1.AMI.value+\"&tax=\"+document.Form1.VAT_PERCENTAGE.value+\"&tax_app=\"+document.Form1.VAT_PER_APP.value+\"&last_rent=&nitbasemar=\"+document.Form1.INTEREST_BASE_MARGIN.value+\"&sup_cr=\"+document.Form1.SUPPLIER_CREDIT.value+\"&residual=\"+unformat_noobject(document.Form1.RESIDUAL_VALUE.value)+\"&cashout=\"+document.Form1.CASH_OUTFLOW.value+\"&trnsub=\"+document.Form1.TRANSACTION_SUB.value+\"&nittype=\"+document.Form1.INTEREST_TYPE.value+\"&nibsm=\"+document.Form1.NIBSM.value+\"&trn_type=\"+document.Form1.TRANSACTION_TYPE.value+\"\";");
                // out.println(" }");
                // out.println("   m_opt=document.Form1.CACULATED.value;");
                // out.println("   document.Form1.CACULATED.value=\"YES\";");
                // out.println("   makeRequest(m_url,m_stat,opt);");
                // //out.println(" }else{");
                // //out.println("   alert('Please Enter ');");
                // //out.println(" }");
                
                // out.println("}");
                // /*
                // out.println("function load_flow() {");
        // out.println(" if(document.Form1.RATE.value!=\"\" && document.Form1.NET_AMOUNT.value !=\"\" && document.Form1.VAT_PERCENTAGE.value !=\"\" && document.Form1.PERIOD.value !=\"\" && document.Form1.GROSS_AMOUNT.value!=\"\"){");
                
                // out.println("	 m_fact_val = \"\";"); 
                // out.println("	 m_prac_val = \"\";"); 
                // out.println("  m_percentage = \"\";"); 
                // out.println("  m_char_val = \"0\";"); 
                // out.println("  m_main_val = \"0\";"); 
                // out.println("  m_mainten  = \"\";"); 
                // out.println("  m_cash_out = \"\";"); 
                // out.println("  m_cashOAmo = \"0\";"); 
                
                // out.println("  for(i=0;i<parseFloat(document.Form1.hid_count.value);i++){");
              // out.println("    m_send_val   = m_send_val+unformat_noobject(document.Form1.elements[\"NETAMT\"+i].value)+\"@\";");
                // out.println("    m_fact_val   = m_fact_val+unformat_noobject(document.Form1.elements[\"FACTOR\"+i].value)+\"@\";");
              // out.println("    m_prac_val   = m_prac_val+unformat_noobject(document.Form1.elements[\"PRACENT\"+i].value)+\"@\";");
                // out.println("    m_cash_out   = m_cash_out+unformat_noobject(document.Form1.elements[\"CASHOUT\"+i].value)+\"@\";");
                // out.println("    m_cashOAmo   = parseFloat(m_cashOAmo)+parseFloat(unformat_noobject(document.Form1.elements[\"CASHOUT\"+i].value));");
                 
                // out.println("    m_percentage = m_percentage+unformat_noobject(document.Form1.elements[\"PERCENTAGE\"+i].value)+\"@\";");
                // //out.println("    alert('m_percentage'+m_percentage);");
              // out.println("	 }");
                // out.println("	 document.Form1.CASH_OUTFLOW.value=m_cash_out;");
                // out.println("	 window.opener.document.Form1.CASH_OUTFLOW.value=m_cash_out;");
                // //out.println("	 alert(m_cashOAmo+'!='+document.Form1.GROSS_AMOUNT.value);");
                
                // out.println("	 if(parseFloat(m_cashOAmo)!=parseFloat(document.Form1.GROSS_AMOUNT.value)){");
                // out.println("	   alert('Please Check the Cash Outflow Amounts.');");
                // out.println("	   return false;");
                // out.println("	 }");
                
                // out.println("	 m_send_val = \"chksql=get_outflow_recal&rate=\"+unformat_noobject(document.Form1.RATE.value)+\"&value=\"+unformat_noobject(document.Form1.GROSS_AMOUNT.value)+");
                // out.println("	              \"&terms=\"+document.Form1.PERIOD.value+\"&freq=\"+document.Form1.REPAYMENT_INTERVAL.value+\"&type=\"+document.Form1.PAYMENT_MODE.value+\"&option=\"+");
                // out.println("	              \"&last_rent=&sup_cr=\"+document.Form1.SUPPLIER_CREDIT.value+\"&residual=\"+unformat_noobject(document.Form1.RESIDUAL_VALUE.value)+");
                // out.println("	              \"&supcrper=0&cashout=\"+document.Form1.CASH_OUTFLOW.value+\"&trnsub=\"+document.Form1.TRANSACTION_SUB.value+\"&nittype=\"+document.Form1.INTEREST_TYPE.value+\"&nibsm=\"+unformat_noobject(document.Form1.NIBSM.value)+");
                // out.println("	              \"&trn_type=\"+document.Form1.TRANSACTION_TYPE.value+\"&tax=\"+document.Form1.VAT_PERCENTAGE.value+\"&tax_app=\"+document.Form1.VAT_PER_APP.value+\"&installments=\";");
                                    
                // out.println("	 if(document.Form1.INTEREST_TYPE.value=='VARIABLE'){");
                // out.println("	   m_send_val = m_send_val+\"&nitbasemar=\"+document.Form1.INTEREST_BASE.value+\"&nitmar=\"+document.Form1.INTEREST_MARGIN.value+\"\";");						
                // out.println("	 }else{");
                // out.println("	   m_send_val = m_send_val+\"&nitbasemar=&nitmar=\";");						
                // out.println("	 }");
                
                // //out.println("  for(i=0;i<parseFloat(document.Form1.c_c_count.value);i++){");
              // //out.println("    m_char_val   = parseFloat(m_char_val)+parseFloat(unformat_noobject(document.Form1.elements[\"CHARGE_\"+i].value));");
                // //out.println("	 }");
                
                // //out.println("  for(j=0;j<=parseFloat(document.Form1.hid_y_count.value);j++){");
                // //out.println("      m_main_val   =0;");
              // //out.println("    for(i=0;i<parseFloat(document.Form1.hid_m_count.value);i++){");
              // //out.println("      m_main_val   = parseFloat(m_main_val)+parseFloat(unformat_noobject(document.Form1.elements[\"MAINTANENCE_\"+i+\"_\"+j].value));");
                // //out.println("	   }");
                // //out.println("    m_mainten   = m_mainten+m_main_val+\"@\";");
                // //out.println("	 }");
                
                // out.println("    m_send_val = m_send_val+\"&maintan=\"+m_mainten+\"&factor=\"+m_fact_val+\"&pracent=\"+m_prac_val+\"&ami=\"+document.Form1.AMI.value+\"&other_cha=\"+m_char_val+\"&percentage=\"+m_percentage;");
                // //out.println("   m_str=construct_URL(\"get_outflow_cal\",\"\");");
                // out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_MK_Price?\"+m_send_val;");
                // out.println("   popupwin = window.open(m_url,'displayWindow1','left=50,top=280,width=900,height=390,toolbar=0,location=0,directories=0,status=0,menuBar=1,scrollBars=1,resizable=1');");
        // out.println(" }else{");
                // out.println("   alert('Please Enter ');");
        // out.println(" }");
        
        // out.println("}");
        
                // out.println("function befor_main(m_stat,opt) {");
        // //out.println("   alert('func ');");
        // out.println(" if(document.Form1.MAINTENANCE_APP.options[document.Form1.MAINTENANCE_APP.selectedIndex].value!=\"N\"){");
        // //out.println("   alert('if ');");
        // out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_MK_Price?chksql=loadMaintenance&rate=\"+unformat_noobject(document.Form1.RATE.value)+\"&value=\"+unformat_noobject(document.Form1.GROSS_AMOUNT.value)+\"&terms=\"+document.Form1.PERIOD.value+\"&freq=\"+document.Form1.REPAYMENT_INTERVAL.value+\"&type=\"+document.Form1.PAYMENT_MODE.value+\"&ami=\"+document.Form1.AMI.value+\"&tax=\"+document.Form1.VAT_PERCENTAGE.value+\"&tax_app=\"+document.Form1.VAT_PER_APP.value+\"&last_rent=&nitbasemar=\"+document.Form1.INTEREST_BASE_MARGIN.value+\"&sup_cr=\"+document.Form1.SUPPLIER_CREDIT.value+\"&residual=\"+unformat_noobject(document.Form1.RESIDUAL_VALUE.value)+\"&cashout=\"+document.Form1.CASH_OUTFLOW.value+\"&trnsub=\"+document.Form1.TRANSACTION_SUB.value+\"&nittype=\"+document.Form1.INTEREST_TYPE.value+\"&nibsm=\"+document.Form1.NIBSM.value+\"&trn_type=\"+document.Form1.TRANSACTION_TYPE.value+\"\";");
        // out.println("   makeRequest(m_url,\"Main\",'NO');");
        // out.println(" }else{");
                // out.println("   main_id.innerHTML='';;");
        // out.println(" }");
        // out.println("}");
                
                
                // out.println("function befor_subtype(m_stat,opt) {");
        // //out.println("   alert('func ');");
        // //out.println(" if(document.Form1.MAINTENANCE_APP.options[document.Form1.MAINTENANCE_APP.selectedIndex].value!=\"N\"){");
        // //out.println("   alert('if ');");
        // out.println("   m_url=\""+m_servlet_client_url+":"+m_client_t3_port+"/"+m_client_name+"AF_MK_Price?chksql=chkSubType&rate=\"+unformat_noobject(document.Form1.RATE.value)+\"&value=\"+unformat_noobject(document.Form1.GROSS_AMOUNT.value)+\"&terms=\"+document.Form1.PERIOD.value+\"&freq=\"+document.Form1.REPAYMENT_INTERVAL.value+\"&type=\"+document.Form1.TRANSACTION_TYPE.value+\"\";");
        // out.println("   makeRequest(m_url,\"SubType\",'NO');");
        // //out.println(" }else{");
                // //out.println("   main_id.innerHTML='';;");
        // //out.println(" }");
        
        // out.println("}");
                // */
                // out.println("function befor_end(m_obj) {");
                // out.println("   m_obj.focus();");
                // out.println("}");
                
                // //end of Main Button Action
                // //onload Action
                
                // out.println("function befor_onload(){");
                // /*out.println("  document.Form1.PRICE_NO.disabled=true;");
                // out.println("  document.Form1.inqu_help.disabled=true;");
                // out.println("  document.Form1.OPTION_NAME.value=\"NEW\";");
                // out.println("  document.Form1.OPTION_DESC.value=\"New\";"); */
                // out.println("  load_roll_value(\"New\");");
                // out.println("}");
                // //end of onload
                // /*
                // out.println("function cal_VatNet(){");
                // out.println("  if(document.Form1.GROSS_AMOUNT.value!=\"\" && document.Form1.VAT_PERCENTAGE.value!=\"\"){");
                // out.println("   document.Form1.NET_AMOUNT.value=format_noobject(parseFloat(unformat_noobject(document.Form1.GROSS_AMOUNT.value))/((parseFloat(unformat_noobject(document.Form1.VAT_PERCENTAGE.value))/100)+1));");
                // out.println("   document.Form1.VAT_AMOUNT.value=format_noobject(parseFloat(unformat_noobject(document.Form1.NET_AMOUNT.value))*(parseFloat(unformat_noobject(document.Form1.VAT_PERCENTAGE.value))/100));");
                
                // //out.println("   for(j=0;j<parseFloat(document.Form1.c_c_count.value);j++){");
                // //out.println("    if(document.Form1.elements['CHARGE_'+j].value=='0' || document.Form1.elements['CHARGE_'+j].value==null){");
                // //out.println("     document.Form1.elements['CHARGE_'+j].value=format_noobject(parseFloat(unformat_noobject(document.Form1.NET_AMOUNT.value))*document.Form1.elements['CHARGE_PER_'+j].value);");
                // //out.println("    }");
                // //out.println("   }");
                
                // out.println("   document.Form1.GROSS_AMOUNT.value=format_noobject(unformat_noobject(document.Form1.GROSS_AMOUNT.value));");
                // out.println("  }");
                // out.println("}");
                
                 // out.println("function cal_VatGross(){");
                // out.println("  if(document.Form1.NET_AMOUNT.value!=\"\" && document.Form1.VAT_PERCENTAGE.value!=\"\"){");
                // out.println("   document.Form1.GROSS_AMOUNT.value=format_noobject(parseFloat(unformat_noobject(document.Form1.NET_AMOUNT.value))*((parseFloat(unformat_noobject(document.Form1.VAT_PERCENTAGE.value))/100)+1));");
                // out.println("   document.Form1.VAT_AMOUNT.value=format_noobject(parseFloat(unformat_noobject(document.Form1.NET_AMOUNT.value))*(parseFloat(unformat_noobject(document.Form1.VAT_PERCENTAGE.value))/100));");
                
                // //out.println("   for(j=0;j<parseFloat(document.Form1.c_c_count.value);j++){");
                // //out.println("    if(document.Form1.elements['CHARGE_'+j].value=='0' || document.Form1.elements['CHARGE_'+j].value==null){");
                // //out.println("     document.Form1.elements['CHARGE_'+j].value=format_noobject(parseFloat(unformat_noobject(document.Form1.NET_AMOUNT.value))*document.Form1.elements['CHARGE_PER_'+j].value);");
                // //out.println("    }");
                // //out.println("   }");
                
                // out.println("   document.Form1.NET_AMOUNT.value=format_noobject(unformat_noobject(document.Form1.NET_AMOUNT.value));");
                // out.println("  }");
                // out.println("}");
                // */
                // out.println("function load_roll_value(m_val){"); 
                // out.println(" if(m_val=='New'){");
                // out.println("  help_box.innerHTML=\"Marketing - Pricing - Cash Flow  \";"); 
                // out.println(" }else{");				
                // out.println("  help_box.innerHTML=\"Marketing - Pricing - Cash Flow - \"+m_val;"); 
                // out.println(" }");
                // out.println("}");
                
                // out.println("   ");
                // /*
                // out.println("function chan_residual(){"); 
              // out.println(" if(parseFloat(unformat_noobject(document.Form1.NIBSM.value))>0){"); 
              // out.println("  document.Form1.RESIDUAL_VALUE.value=format_noobject(document.Form1.NIBSM.value);"); 
              // out.println("  document.Form1.NIBSM.value=format_noobject(document.Form1.NIBSM.value);"); 
                // out.println("  document.Form1.RESIDUAL_VALUE.disabled=true;");
              // out.println(" }else{"); 
              // out.println("  document.Form1.RESIDUAL_VALUE.value='0';");
              // out.println("  document.Form1.RESIDUAL_VALUE.disabled=false;");
              // out.println(" }"); 
              // out.println("}");
                // */
                // out.println("function format_text(obj){"); 
                // out.println("  obj.value=format_noobject(obj.value);"); 
                // out.println("}");
                
                // out.println("function format_num(obj,i){");
                // out.println("  if(!isNaN(obj)){");
                // out.println("   alert('Please Enter Number!');");
                // out.println("   obj='0';");
                // out.println("  }else{");
                // out.println("   if(i!='6'){");
                // out.println("    obj.value=unformat_noobject(obj.value)");
                // out.println("    obj.value=format_noobject0(obj.value,i);");
                // out.println("   }");
                
                // out.println("  }");
                // out.println("}");
                
                
                // out.println("function cha_val(count){"); 
                // out.println(" if(document.Form1.TRANSACTION_SUB.value==\"STEP-UP\" || document.Form1.TRANSACTION_SUB.value==\"STEP-DOWN\"){ ");
                // out.println("  if(confirm(\"Do you want apply this change to all the below installments?\")){"); 
                // out.println("    for(i=parseFloat(count)+1;i<parseFloat(document.Form1.hid_count.value);i++){"); 
                // out.println("     document.Form1.elements[\"PERCENTAGE\"+i].value = unformat_noobject(document.Form1.elements[\"PERCENTAGE\"+count].value) ");// unformat_noobject(
                // out.println("    }");
                // out.println("  }");
                // out.println(" }");
                // out.println("}");
                
                // out.println("function cal_count(){"); 
                // out.println("  document.Form1.CAL_COUNT.value=parseFloat(document.Form1.CAL_COUNT.value)+1;"); 
                // out.println("}");
                // /*
                // out.println("function load_fild(){"); 
              // out.println(" if(document.Form1.INTEREST_TYPE.value=='VARIABLE'){");
            
                // //out.println("   int_base.innerHTML='Variable Interest Base'; ");
                // //out.println("   int_base_txt.innerHTML='<input name=\"INTEREST_BASE\" type=\"text\" maxlength=\"50\" class=\"txt_input\" >';; ");
                // //out.println("   int_mar.innerHTML='Variable Interest Margin'; ");
                // //out.println("   int_mar_txt.innerHTML='<input name=\"INTEREST_MARGIN\" type=\"text\" maxlength=\"50\" class=\"txt_input\" >';; ");
                
                // out.println("   int_type.innerHTML='<table class=table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" >'+");
                // out.println("                      '<tr class=tr_input><td width=25%>Variable Interest Base</td>'+");
                // out.println("                      '<td  ><input name=\"INTEREST_BASE\" type=\"text\" maxlength=\"10\" class=\"txt_input\" >'+");
                // out.println("                      '<input type=button name=intbase_help value=Help class=\"but_input\" onclick=\"int_base_help('1','10','0','IntBaseSql','9')\"></td></tr>'+");
        // out.println("                      '<tr class=tr_input><td >Variable Interest Margin</td>'+");
                // out.println("                      '<td ><input name=\"INTEREST_MARGIN\" type=\"text\" maxlength=\"6\" class=\"txt_input\" ></td></tr></table>';");
                // out.println(" }else{");
                // out.println("   int_type.innerHTML='';");
                // out.println(" }");
                // out.println("}");
                // */
                
                
                // //Check Values Using AJAX
                // out.println("function makeRequest(url,opt,opt1) {");
                // out.println("var http_request = false;");
                // out.println("if (window.XMLHttpRequest) {"); // Mozilla, Safari,...
                // out.println("    http_request = new XMLHttpRequest();");
                // out.println("    if (http_request.overrideMimeType) {");
                // out.println("        http_request.overrideMimeType('text/xml');");
                // out.println("    }");
                // out.println("} else if (window.ActiveXObject) { ");// IE
                // out.println("    try {");
                // out.println("        http_request = new ActiveXObject(\"Msxml2.XMLHTTP\");");
                // out.println("    } catch (e) {");
                // out.println("        try {");
                // out.println("            http_request = new ActiveXObject(\"Microsoft.XMLHTTP\");");
                // out.println("        } catch (e) {}");
                // out.println("    }");
                // out.println("}");
                // out.println("if (!http_request) {");
                // out.println("    alert('Giving up :( Cannot create an XMLHTTP instance');");
                // out.println("    return false;");
                // out.println("}");
                
                // out.println(" if(opt1=='YES'){");
                // //window.open("http://www.ofscl-leasing.lk:/myserver/servlet/LAKDL_AF_MK_Price?"+m_send_val);
                // out.println("  http_request.onreadystatechange = function() { alertContents(http_request); };");
                // out.println("  http_request.open('POST',url, true);");
                // out.println("	 http_request.setRequestHeader(\"Content-Type\",\"application/x-www-form-urlencoded\");");
                // out.println("	 m_fact_val = \"\";"); 
                // out.println("	 m_prac_val = \"\";"); 
                // out.println("  m_percentage = \"\";"); 
                // out.println("  m_char_val = \"0\";"); 
                // out.println("  m_main_val = \"0\";"); 
                // out.println("  m_mainten  = \"\";"); 
                // out.println("  m_cash_out = \"\";"); 
                // out.println("  m_cashOAmo = \"0\";"); 
                // out.println("	 m_vat_val = \"\";"); 
                // out.println("	 m_gro_val = \"\";"); 
                
                
                
                
                // out.println("	 m_send_val = \"chksql=get_outflow_recal&rate=\"+unformat_noobject(document.Form1.RATE.value)+\"&value=\"+unformat_noobject(document.Form1.GROSS_AMOUNT.value)+");
                // out.println("	              \"&rental=\"+unformat_noobject(document.Form1.RENTAL_AMOUNT.value)+\"&1rental=\"+unformat_noobject(document.Form1.RENTAL1_AMOUNT.value)+");				
                // out.println("	              \"&terms=\"+unformat_noobject(document.Form1.PERIOD.value)+\"&freq=\"+document.Form1.REPAYMENT_INTERVAL.value+\"&type=\"+document.Form1.PAYMENT_MODE.value+\"&option=\"+");
                // out.println("	              \"&last_rent=&sup_cr=\"+document.Form1.SUPPLIER_CREDIT.value+\"&residual=\"+unformat_noobject(document.Form1.RESIDUAL_VALUE.value)+");
                // out.println("	              \"&supcrper=0&trnsub=\"+document.Form1.TRANSACTION_SUB.value+\"&nittype=\"+document.Form1.INTEREST_TYPE.value+\"&nibsm=\"+unformat_noobject(document.Form1.NIBSM.value)+");
                // out.println("	              \"&trn_type=\"+document.Form1.TRANSACTION_TYPE.value+\"&tax=\"+document.Form1.VAT_PERCENTAGE.value+\"&tax_app=\"+document.Form1.VAT_PER_APP.value+\"&tax_rent=\"+document.Form1.VAT_PER_RENT.value+\"\";");
                
                // out.println("	 if(document.Form1.INTEREST_TYPE.value=='VARIABLE'){");
                // out.println("	   m_send_val = m_send_val+\"&nitbasemar=\"+document.Form1.INTEREST_BASE.value+\"&nitmar=\"+document.Form1.INTEREST_MARGIN.value+\"&installments=\";");						
                // out.println("	 }else{");
                // out.println("	   m_send_val = m_send_val+\"&nitbasemar=&nitmar=&installments=\";");						
                // out.println("	 }");
                
                // out.println("  for(i=0;i<parseFloat(document.Form1.hid_count.value);i++){");
                // out.println("    m_send_val   = m_send_val+unformat_noobject(document.Form1.elements[\"NETAMT\"+i].value)+\"@\";");
                // out.println("    m_fact_val   = m_fact_val+/*unformat_noobject*/(document.Form1.elements[\"FACTOR\"+i].value)+\"@\";");
                // out.println("    m_prac_val   = m_prac_val+unformat_noobject(document.Form1.elements[\"PRACENT\"+i].value)+\"@\";");
                // out.println("    m_cash_out   = m_cash_out+unformat_noobject(document.Form1.elements[\"CASHOUT\"+i].value)+\"@\";");
                // out.println("    m_cashOAmo   = parseFloat(m_cashOAmo)+parseFloat(unformat_noobject(document.Form1.elements[\"CASHOUT\"+i].value));");
                // out.println("    m_vat_val    = m_vat_val+unformat_noobject(document.Form1.elements[\"VATAMT\"+i].value)+\"@\";");
                // out.println("    m_gro_val    = m_gro_val+unformat_noobject(document.Form1.elements[\"GROSS\"+i].value)+\"@\";");
                
                // out.println("    m_percentage = m_percentage+unformat_noobject(document.Form1.elements[\"PERCENTAGE\"+i].value)+\"@\";");
                // //out.println("    alert('m_percentage'+m_percentage);");
                // out.println("	 }");
                // out.println("	 document.Form1.CASH_OUTFLOW.value=m_cash_out;");
                // out.println("	 window.opener.document.Form1.CASH_OUTFLOW.value=m_cash_out;");
                // //out.println("	 alert(m_cashOAmo+'!='+document.Form1.GROSS_AMOUNT.value);");
                
                // out.println("    m_send_val = m_send_val+\"&cashout=\"+document.Form1.CASH_OUTFLOW.value+\"&maintan=\"+m_mainten+\"&factor=\"+m_fact_val+\"&pracent=\"+m_prac_val+\"&ami=\"+document.Form1.AMI.value+\"&other_cha=\"+m_char_val+\"&percentage=\"+m_percentage+");
                // out.println("                 \"&vat_val=\"+m_vat_val+\"&gro_val=\"+m_gro_val;");
                
                // //out.println("  construct_URL(\"get_advance_price_cal\",opt);");
                // //out.println("  window.open(url+'?'+m_send_val);");
                // out.println("	 if(parseFloat(m_cashOAmo)!=parseFloat(document.Form1.GROSS_AMOUNT.value)){");
                // out.println("	   alert('Please check the cash outflow amounts.');");
                // out.println("	   return false;");
                // out.println("	 }");
                
                
                // //out.println("  document.write(url+'?'+m_send_val);");
                // out.println("  http_request.send(m_send_val);");
                // out.println(" }else{");
                
                // out.println("  http_request.onreadystatechange = function() { alertGetContents(http_request,opt); };");
                // out.println("  http_request.open('GET',url, true);");
                // //out.println("  window.open(url);");
                // //out.println("  alert('opt-'+opt);");
                // out.println("  http_request.send(null);");
                // out.println(" }");
                // out.println("}");
                
                // /*
                // out.println("function construct_URL(chk_sql,opt) {");
        // out.println("	 m_send_val = \"chksql=\"+chk_sql+\"&rate=\"+unformat_noobject(document.Form1.RATE.value)+\"&value=\"+unformat_noobject(document.Form1.GROSS_AMOUNT.value)+");
                // out.println("	              \"&terms=\"+document.Form1.PERIOD.value+\"&freq=\"+document.Form1.REPAYMENT_INTERVAL.value+\"&type=\"+document.Form1.PAYMENT_MODE.value+\"&option=\"+opt+");
                // out.println("	              \"&last_rent=&sup_cr=\"+document.Form1.SUPPLIER_CREDIT.value+\"&residual=\"+unformat_noobject(document.Form1.RESIDUAL_VALUE.value)+");
                // out.println("	              \"&supcrper=0&cashout=\"+document.Form1.CASH_OUTFLOW.value+\"&trnsub=\"+document.Form1.TRANSACTION_SUB.value+\"&nittype=\"+document.Form1.INTEREST_TYPE.value+\"&nibsm=\"+unformat_noobject(document.Form1.NIBSM.value)+");
                // out.println("	              \"&trn_type=\"+document.Form1.TRANSACTION_TYPE.value+\"&tax=\"+document.Form1.VAT_PERCENTAGE.value+\"&tax_app=\"+document.Form1.VAT_PER_APP.value+\"&installments=\";");
                                    
                // out.println("	 if(document.Form1.INTEREST_TYPE.value=='VARIABLE'){");
                // out.println("	   m_send_val = m_send_val+\"&nitbasemar=\"+document.Form1.INTEREST_BASE.value+\"&nitmar=\"+document.Form1.INTEREST_MARGIN.value+\"\";");						
                // out.println("	 }else{");
                // out.println("	   m_send_val = m_send_val+\"&nitbasemar=&nitmar=\";");						
                // out.println("	 }");
                // out.println("	 m_fact_val = \"\";"); 
                // out.println("	 m_prac_val = \"\";"); 
                // out.println("	 m_outf_val = \"\";"); 
                // out.println("  m_percentage = \"\";"); 
                // out.println("  m_char_val = \"0\";"); 
                // out.println("  m_main_val = \"0\";"); 
                // out.println("  m_mainten  = \"\";"); 
                
                // out.println("  for(i=0;i<parseFloat(document.Form1.hid_count.value);i++){");
              // out.println("    m_send_val   = m_send_val+unformat_noobject(document.Form1.elements[\"NETAMT\"+i].value)+\"@\";");
                // out.println("    m_fact_val   = m_fact_val+unformat_noobject(document.Form1.elements[\"FACTOR\"+i].value)+\"@\";");
              // out.println("    m_prac_val   = m_prac_val+unformat_noobject(document.Form1.elements[\"PRACENT\"+i].value)+\"@\";");
                // out.println("    m_outf_val   = m_outf_val+unformat_noobject(document.Form1.elements[\"CASHOUT\"+i].value)+\"@\";");
                
                // out.println("    m_percentage = m_percentage+unformat_noobject(document.Form1.elements[\"PERCENTAGE\"+i].value)+\"@\";");
                // //out.println("    alert('m_percentage'+m_percentage);");
              // out.println("	 }");
                // out.println("  for(i=0;i<parseFloat(document.Form1.c_c_count.value);i++){");
              // out.println("    m_char_val   = parseFloat(m_char_val)+parseFloat(unformat_noobject(document.Form1.elements[\"CHARGE_\"+i].value));");
                // out.println("	 }");
                
                // out.println("  for(j=0;j<=parseFloat(document.Form1.hid_y_count.value);j++){");
                // out.println("      m_main_val   =0;");
              // out.println("    for(i=0;i<parseFloat(document.Form1.hid_m_count.value);i++){");
              // out.println("      m_main_val   = parseFloat(m_main_val)+parseFloat(unformat_noobject(document.Form1.elements[\"MAINTANENCE_\"+i+\"_\"+j].value));");
                // out.println("	   }");
                // out.println("    m_mainten   = m_mainten+m_main_val+\"@\";");
                // out.println("	 }");
                
                // out.println("    m_send_val = m_send_val+\"&maintan=\"+m_mainten+\"&factor=\"+m_fact_val+\"&pracent=\"+m_prac_val+\"&ami=\"+document.Form1.AMI.value+\"&other_cha=\"+m_char_val+\"&percentage=\"+m_percentage;");
                // out.println("    return m_send_val;");
                // out.println("	 }");
                // */
                
                // out.println("function alertContents(http_request) {");
                // //out.println(" alert('test');");
                // out.println(" if (http_request.readyState == 4) {");
                // out.println("    if (http_request.status == 200) {");
                // //out.println("      alert(http_request.responseText);");
                // out.println("         price_cal.innerHTML=http_request.responseText; ");
                // //out.println("         for(i=0;i<10000;i++){o=i;}");
                // //out.println("         asign_div();");
                // out.println("    } else {");
                // out.println("        alert('There was a problem with the request.');");
                // out.println("    }");
                // out.println(" }");
                // out.println("}");
                // /*
                // out.println("function alertGetContents(http_request,opt) {");
                        // //out.println(" alert('test--'+opt);");
                // out.println(" if (http_request.readyState == 4) {");
                // out.println("    if (http_request.status == 200) {");
                // out.println("      if(opt==\"Main\"){");
                        // //out.println("         alert(http_request.responseText);");
                        // out.println("         main_id.innerHTML=http_request.responseText; ");
                        // out.println("      }else if(opt==\"SubType\"){");
                        // //out.println("         main_id.innerHTML=http_request.responseText; ");
                        // out.println("         Curr_no_opts=document.Form1.TRANSACTION_SUB.options;");
                        // out.println("         Curr_no_opts.length=0;");
                        // out.println("         m_string=http_request.responseText; ");
                        // out.println("         m_index =m_string.indexOf(\"~#@\");"); 
                        // out.println("         z=0;");
                        // out.println("         while(m_index!=-1){");
                        // out.println("           arr_assign[z]  = m_string.substring(0,m_string.indexOf(\"~#@\")); ");
                        // out.println("           m_string       = m_string.substring(m_string.indexOf(\"~#@\")+3); ");
                        // out.println("           z              = z+1;");
                        // out.println("           m_index =m_string.indexOf(\"~#@\");"); 
                        // out.println("         }");
                        // out.println("         for(x=0; x<arr_assign.length; x++){");
                        // out.println("           m_string1      = arr_assign[x].substring(0,arr_assign[x].indexOf(\"@#\")); ");
                        // out.println("           m_string2      = arr_assign[x].substring(arr_assign[x].indexOf(\"@#\")+2); ");
                        // out.println("           m_string       = m_string2.substring(0,m_string2.indexOf(\"@#\")); ");
                        // out.println("           alert('m_string1='+m_string1+'--m_string='+m_string+'');");
                        // out.println("           Curr_no_opts[x]= new Option(m_string,m_string1);");
                        // out.println("         }");
                        
                        // out.println("      }");
                        // out.println("    } else {");
                // out.println("        main_id.innerHTML='';");
                // out.println("    }");
                // out.println(" }");
                // out.println("}");
                        // */
                // //End Of Checking Values
                
                // out.println("function asign_div(){");
                // out.println("format_num(unformat_noobject(document.Form1.sun_g_nt.value),0)");
                // out.println("format_num(unformat_noobject(document.Form1.sun_rent.value),0)");
                // out.println("format_num(unformat_noobject(document.Form1.sun_p_nt.value),0)");
                // out.println("format_num(unformat_noobject(document.Form1.sun_fact.value),4)");
                // out.println("g_re.innerHTML   =document.Form1.sun_g_nt.value");
                // out.println("n_re.innerHTML   =document.Form1.sun_rent.value");
                // out.println("p_va.innerHTML   =document.Form1.sun_p_nt.value");
                // out.println("m_fact.innerHTML =document.Form1.sun_fact.value");
                // out.println("document.Form1.hid_count.value=document.Form1.hid_cou.value;");
                // out.println("}");
                
                // out.println("function format_text(obj){"); 
                // out.println("  obj.value=format_noobject(obj.value);"); 
                // out.println("}");
                
                // out.println("function format_num(obj,i){");
                // out.println("  if(isNaN(unformat_noobject(obj.value))){");
                // out.println("   alert('Please Enter Number!');");
                // out.println("  }else{");
                // out.println("   obj.value=unformat_noobject(obj.value)");
                // out.println("   obj.value=format_noobject0(obj.value,i);"); 
                // out.println("  }");
                // out.println("}");
                
                // out.println("</Script>");
                // out.println("<body onload=\"befor_onload()\" class=\"body & txt-body\" leftmargin=\"0\" topmargin=\"0\" marginwidth=\"0\" marginheight=\"0\">");
                // out.println("<form name=\"Form1\" method=post>");
                
                // out.println("<INPUT TYPE=HIDDEN NAME=RATE 							VALUE="+m_rate+">");
                // out.println("<INPUT TYPE=HIDDEN NAME=GROSS_AMOUNT 			VALUE="+m_value+">");
                // out.println("<INPUT TYPE=HIDDEN NAME=PERIOD 						VALUE="+m_terms+">");
                // out.println("<INPUT TYPE=HIDDEN NAME=REPAYMENT_INTERVAL VALUE="+m_freq+">");
                // out.println("<INPUT TYPE=HIDDEN NAME=PAYMENT_MODE 			VALUE="+m_type+">");
                // out.println("<INPUT TYPE=HIDDEN NAME=SUPPLIER_CREDIT 		VALUE="+m_sup_credit+">");
                // out.println("<INPUT TYPE=HIDDEN NAME=RESIDUAL_VALUE 		VALUE="+m_residual+">");
                // out.println("<INPUT TYPE=HIDDEN NAME=CASH_OUTFLOW 			VALUE="+m_cash_out+">");
                // out.println("<INPUT TYPE=HIDDEN NAME=TRANSACTION_SUB 		VALUE="+m_trn_sub+">");
                // out.println("<INPUT TYPE=HIDDEN NAME=INTEREST_TYPE 			VALUE="+m_int_type+">");
                // out.println("<INPUT TYPE=HIDDEN NAME=NIBSM 							VALUE="+m_nibsm+">");
                // out.println("<INPUT TYPE=HIDDEN NAME=TRANSACTION_TYPE 	VALUE="+m_trn_type+">");
                // out.println("<INPUT TYPE=HIDDEN NAME=VAT_PERCENTAGE 		VALUE="+m_vat_per+">");
                // out.println("<INPUT TYPE=HIDDEN NAME=VAT_PER_APP 				VALUE="+m_vat_app+">");
                // out.println("<INPUT TYPE=HIDDEN NAME=VAT_PER_RENT				VALUE="+m_tax_rent+">");
                
                // out.println("<INPUT TYPE=HIDDEN NAME=AMI 								VALUE="+m_ami+">");
                // out.println("<INPUT TYPE=HIDDEN NAME=CHARGE 						VALUE="+m_other_cha+">");
                // out.println("<INPUT TYPE=HIDDEN NAME=MAINTANENCE 				VALUE="+m_maintenance+">");
                // out.println("<INPUT TYPE=HIDDEN NAME=INTEREST_MARGIN 		VALUE="+m_nitmar+">");
                // out.println("<INPUT TYPE=HIDDEN NAME=INTEREST_BASE 			VALUE="+m_int_base_m+">");
                // out.println("<INPUT TYPE=HIDDEN NAME=RENTAL_AMOUNT 			VALUE="+m_rental+">");
                // out.println("<INPUT TYPE=HIDDEN NAME=RENTAL1_AMOUNT 		VALUE="+m_1rental+">");
                // out.println("<INPUT TYPE=HIDDEN NAME=OPTION_DESC 			  VALUE=\"\">");
                // out.println("<INPUT TYPE=HIDDEN NAME=CAL_COUNT 			    VALUE=\"\">");
                // out.println("<input type=hidden name=\"CACULATED\"      value=\"NO\">");
                
                
                // out.println("<table width=\"100%\" class=table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" height=\"100%\">");
                // out.println("<tr>");
                
                // out.println("<td width=\"8\" valign=\"top\"><img src=\""+m_html_client_url+"/images/spacer.gif\" width=\"8\" height=\"8\"></td>");
                // out.println("<td class=\"border_wht\" valign=\"top\"> ");
                // out.println("<table class=table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" height=\"100%\">");
                // out.println("<tr> ");
                // out.println("<td height=\"30\" class=\"pdn_mainHD\" class>"+header_name+"</td>");
                // out.println("</tr>");
                // out.println("<tr> ");
                // out.println("<td height=\"1\"><img src=\""+m_html_client_url+"/images/spacer.gif\" width=\"1\" height=\"1\"></td>");
                // out.println("</tr>");
                // out.println("<tr>");
                // out.println("<td style=\"height: 327px\">");
                
                
                
                
                // out.println("<table class=table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" height=\"100%\" width=\"100%\">   ");
                // out.println("<tr>");
                // out.println("<td height=\"1\"><img height=\"1\" src=\""+m_html_client_url+"/images/spacer.gif\" width=\"1\" ></td>");
                // out.println("</tr>");
                // out.println("<tr>");
                // out.println("<td align=\"left\" class=\"pdn_txtpos2\" style=\"height: 18px\" id=help_box></td>");
                // out.println("</tr>");	
                // out.println("<tr>");
                // out.println("<td  height=\"10px\" class=\"pdn_txtpos\">");
                // out.println("<table class=table cellpadding=\"2\" cellspacing=\"2\" border=\"0\">");
                // out.println("<tr>");
                // /*out.println("<td style=\"width: 6px\"><img src=\""+m_html_client_url+"/images/btnback.gif\" ></td>");
                // out.println("<td>&nbsp;</td>");
                // //out.println("<td><img src=\""+m_html_client_url+"/images/btndelete.gif\" ></td>");
                // //out.println("<td>&nbsp;</td>");
                // out.println("<td onclick=befor_deactive();><img src=\""+m_html_client_url+"/images/btndelete.gif\" ></td>");
                // out.println("<td>&nbsp;</td>");
                // out.println("<td onclick=befor_modify();><img src=\""+m_html_client_url+"/images/btnedit.gif\" ></td>");
                // out.println("<td>&nbsp;</td>");
                // out.println("<td onclick=befor_active();><img src=\""+m_html_client_url+"/images/btncancel.gif\" ></td>");
                // out.println("<td>&nbsp;</td>");
                // out.println("<td onclick=befor_reset();><img src=\""+m_html_client_url+"/images/btnreset.gif\" ></td>");
                // out.println("<td>&nbsp;</td>");
                // out.println("<td onclick=before_submit();><img src=\""+m_html_client_url+"/images/btnsubmit.gif\" ></td>");*/
                
                // out.println("<td style=\"width: 6px\">");//<!--img src="http://www.ofscl-leasing.lk/images/btnback.gif" /--></td>
                // //out.println("<td>&nbsp;</td>");
                // //out.println("<td><input type=button name=new      value=\"New\"       class=mainbut onclick=befor_new();      onMouseOver='load_roll_value(\"New\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");//document.Form1.OPTION_DESC.value
                // //out.println("<td>&nbsp;</td>");
                // //out.println("<td><input type=button name=edit     value=\"Edit\"      class=mainbut onclick=befor_modify();   onMouseOver='load_roll_value(\"Edit\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
                // out.println("<td>&nbsp;</td>");
                // out.println("<td>&nbsp;</td>");
                // out.println("<td>&nbsp;</td>");
                // out.println("<td>&nbsp;</td>");
                // //out.println("<td><input type=button name=delete   value=\"De-active\" class=mainbut onclick=befor_deactive(); onMouseOver='load_roll_value(\"Deactivate\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
                // out.println("<td>&nbsp;</td>");
                // //out.println("<td><input type=button name=cancel   value=\"Re-active\" class=mainbut onclick=befor_active();   onMouseOver='load_roll_value(\"Reactivate\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
                // out.println("<td>&nbsp;</td>");
                // out.println("<td><input type=button name=b_submit value=\"Save\"      class=mainbut onclick=before_submit();   onMouseOver='load_roll_value(\"Save\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
                // out.println("<td>&nbsp;</td>");
                // out.println("<td><input type=button name=back     value=\"Close\"     class=mainbut onclick=befor_back();     onMouseOver='load_roll_value(\"Close\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
                // out.println("<td>&nbsp;</td>");
                // //out.println("<td><input type=button name=reset_   value=\"Reset\"     class=mainbut onclick=befor_reset();    onMouseOver='load_roll_value(\"Reset\");'     onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
                // out.println("<td>&nbsp;</td>");
                // out.println("<td>&nbsp;</td>");
                // out.println("<td>&nbsp;</td>");
                // out.println("<td>&nbsp;</td>");
                // out.println("<td>&nbsp;</td>");
                // out.println("<td>&nbsp;</td>");
                // out.println("<td ><input type=button name=cal     value=\"Calculate\" class=mainbut onclick=befor_cal(\"NO\",\"YES\");     onMouseOver='load_roll_value(\"Calculate\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
                // //out.println("<td>&nbsp;</td>");
                // //out.println("<td ><input type=button name=recal     value=\"Re-Cal\" class=mainbut onclick=befor_cal(\"YES\",\"YES\");     onMouseOver='load_roll_value(\"Re-Calculate\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
                // //out.println("<td>&nbsp;</td>");
                // //out.println("<td ><input type=button name=end_b     value=\"Go to End\" class=mainbut onclick=befor_end(document.Form1.top_b);  onMouseOver='load_roll_value(\"End\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
                
                // out.println("</tr></table>");
                // out.println("</td>	");
                // out.println("</tr>");
                
                // //out.println("<tr>");
                // //out.println("<td class=\"line\" height=\"1\"><img height=\"1\" src=\""+m_html_client_url+"/images/spacer.gif\" width=\"1\" ></td>");
                // //out.println("</tr>");
                // out.println("<tr>");
                // out.println("<td class=\"pdn_txtpos\" height=\"150\" valign=\"top\">");
                // //out.println("<table class=table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" >");
                
                // //out.println("<tr class=tr_input>");
                // //out.println("<td width=\"15%\"><img height=\"1\" src=\""+m_html_client_url+"/images/spacer.gif\" width=\"120\" ></td>");
                // //out.println("<td width=\"35%\"><img height=\"1\" src=\""+m_html_client_url+"/images/spacer.gif\" width=\"180\" ></td>");
                // //out.println("<td width=\"15%\">");
                // //out.println("<img height=\"1\" src=\""+m_html_client_url+"/images/spacer.gif\" ></td>");
                // //out.println("<td>");
                // //out.println("<img height=\"1\" src=\""+m_html_client_url+"/images/spacer.gif\" ></td>");
                // //out.println("</tr>");
                // //out.println("</table>");
                
                // out.println("<table class=table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" >");//main table start
                // out.println("<tr class=tr_input>");
                // out.println("<td valign=top  width=40%>");
                // double mm_tot_fact 				= 0;
                // double mm_tot_term 				= 0;
                // out.println("<table class=table border='1' width='100%' >");
                // out.println("<tr class=tr_input><td  width='100%' Id=price_cal>");
                
                // if(rs.next()){
                    
                    // mm_tot_fact				= rs.getDouble(7);
                    // mm_tot_term				= rs.getInt(5);
                    
                // }		
                // /*		        
            // out.println("<table class=table border='1' width='100%' >");
            // out.println("<tr class=tr_input>");
                        // out.println("<td  width='20%' >Total Factor</td>");
            // out.println("<td width='30%' >"+nf.format(rs.getDouble(7))+"</td>");
                        // out.println("<td  width='20%' >Total Gross Rental</td>");
            // out.println("<td width='30%' ID=GRENT></td>");
                        // out.println("</tr>");
            // out.println("<tr class=tr_input>");
                        // out.println("<td  width='20%' >Total Present Value</td>");
            // out.println("<td width='30%' ID=PVAL></td>");
                        // out.println("<td  width='20%' >Avg. Gross Rental</td>");
            // out.println("<td width='30%' ID=A_GRENT></td>");
                        // out.println("</tr>");
                        // out.println("</table>");
                        
        // }
        
        // out.println("<br>");		
                // */
                // out.println("<table class=table border='0' width='100%' >");
                // out.println("<tr class=tr_input>");
                // //out.println("<td colspan=4 align=right><input type=button name=cash_f  value=\"Cash Flow Pattern\" class=mainbut onclick=befor_cal(\"YES\",\"YES\");             onMouseOver='load_roll_value(\"Cash Outflow\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
                // out.println("<td colspan=9 align=right><input type=button name=end_b   value=\"Go to End\" class=mainbut onclick=befor_end(document.Form1.top_b); onMouseOver='load_roll_value(\"End\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
                // out.println("</tr>");
                
                // out.println("<tr class=tr_input><td  width='5%' >Inst</td>");
                // out.println("<td  width='5%' ></td>");
                // //out.println("<td  width='20%' >Rate per Month(RM)</td>");
                // //out.println("<td  width='30%' >(RM+1)=X</td>");
                // out.println("<td  width='5%' align=right>Factor</td>");
                // out.println("<td  width='10%' align=right>Rental Factor</td>");
                // out.println("<td  width='15%' align=right>Net Rental</td>");
                // out.println("<td  width='15%' align=right>VAT </td>");
                // out.println("<td  width='15%' align=right>Gross Rental</td>");
                // out.println("<td  width='15%' align=right>P.V. Inflow</td>");
                // out.println("<td  width='20%' align=right>Cash Outflow</td>");
                // out.println("<td  width='15%' align=right>P.V. Outflow</td>");
                // //out.println("<td  width='20%' >AMI</td>");
                
                
                // out.println("</tr>");
                // out.println("<tr class=tr_input><td  width='8%' ></td><td  ></td>");
                // out.println("<td id=m_fact align=right></td>");
                // out.println("<td ></td>");
                // out.println("<td id=n_re align=right></td>");
                // out.println("<td id=v_re align=right></td>");
                // out.println("<td id=g_re align=right></td>");
                // out.println("<td id=p_va align=right></td>");
                // out.println("<td id=o_va align=right></td>");
                // out.println("<td id=op_va align=right></td>");
                // //out.println("<td ><input name=\"AMI"+j+"\" type=\"text\" value=\""+nf.format(rs.getDouble(7))+"\" maxlength=\"25\" class=\"txt_input2\" ></td>");
                // out.println("</tr>");
                
                // double sum_rate =0;
                // double sum_rent =0;
                // double sum_p_re =0;
                // double sum_g_re =0;
                // double sum_v_re =0;
                // double sum_o_re =0;
                // double sum_op_re=0;
                // int m_start=0;
                // /*int m_end  =mm_term;
                
                // if(m_type.equals("ARREASE")){
                    // m_start=1;
                    // m_end  =mm_term+1;
                // }
                // */
                // int j=0;
                // //for(j=m_start;j<m_end;j++){
                // //out.println("J="+j);
                // //out.println("<td  width='20%' >"+nf.format(mm_rate_per_month)+"</td>");
                
                // rs = stmt.executeQuery (" SELECT A.INSTALLMENT, A.FACTOR, A.NEW_NET, A.NEW_GROSS, "+
                    // "        A.NEW_PRACENT, A.PERCENTAGE, A.AMI,CASH_OUT, "+
                    // "        CASH_OUT*FACTOR,(A.NEW_GROSS-A.NEW_NET),"+
                    // "        INTEREST_AMOUNT,CAPITAL_AMOUNT  "+
                    // "  FROM  "+m_schema_name+".AF_MK_TBD_PRICE_CAL A "+
                    // "	WHERE  ENT_USER  = '"+m_username+"'"+
                    // " ORDER BY INSTALLMENT");
                
                // while(rs.next()){
                    // //out.println("<td  width='30%' >"+nf.format(rs.getDouble(1))+"</td>");
                    // out.println("<tr class=tr_input><td>"+(j+1)+"</td>");
                    // out.println("<td  width='8%' >"+rs.getString(1)+"<input type=hidden name=\"INSTALLMENT"+j+"\" value=\""+rs.getString(1)+"\"></td>");
                    // out.println("<td align=right><input name=\"FACTOR"+j+"\" type=\"text\" value="+nf1.format(rs.getDouble(2))+" maxlength=\"10\" class=\"txt_input1\" disabled></td>");
                    // out.println("<td align=right><input name=\"PERCENTAGE"+j+"\" type=\"text\" value=\""+nf1.format(rs.getDouble(6))+"\" maxlength=\"8\" class=\"txt_input1\" disabled onchange=format_num(document.Form1.PERCENTAGE"+j+",'6');cha_val(\""+j+"\");cal_count()></td>");
                    // out.println("<td align=right><input name=\"NETAMT"+j+"\" type=\"text\" value="+nf.format(rs.getDouble(3))+" maxlength=\"25\" class=\"txt_input2\" disabled></td>");
                    // out.println("<td align=right><input name=\"VATAMT"+j+"\" type=\"text\" value="+nf.format(rs.getDouble(10))+" maxlength=\"25\" class=\"txt_input2\" disabled></td>");
                    // out.println("<td align=right><input name=\"GROSS"+j+"\" type=\"text\" value="+nf.format(rs.getDouble(4))+" maxlength=\"25\" class=\"txt_input2\" disabled></td>");
                    // out.println("<td align=right><input name=\"PRACENT"+j+"\" type=\"text\" value="+nf.format(rs.getDouble(5))+" maxlength=\"25\" class=\"txt_input2\" disabled></td>");
                    // out.println("<td align=right><input name=\"CASHOUT"+j+"\" type=\"text\" value="+nf.format(rs.getDouble(8))+" maxlength=\"25\" class=\"txt_input2\" onchange=format_num(document.Form1.CASHOUT"+j+",'0')></td>");
                    // out.println("<td align=right><input name=\"OUTPVT"+j+"\" type=\"text\" value="+nf.format(rs.getDouble(9))+" maxlength=\"25\" class=\"txt_input2\" disabled>");
                    // out.println("<input type=hidden name=\"CAP_"+j+"\" value=\""+nf.format(rs.getDouble(12))+"\"><input type=hidden name=\"INST_"+j+"\" value=\""+nf.format(rs.getDouble(11))+"\"></td>");
                    // //out.println("<td ><input name=\"AMI"+j+"\" type=\"text\" value=\""+nf.format(rs.getDouble(7))+"\" maxlength=\"25\" class=\"txt_input2\" ></td>");
                    // out.println("</tr>");
                    // sum_rate=sum_rate+(rs.getDouble(2));
                    // sum_rent=sum_rent+(rs.getDouble(3));
                    // sum_p_re=sum_p_re+(rs.getDouble(5));
                    // sum_g_re=sum_g_re+(rs.getDouble(4));
                    // sum_o_re=sum_o_re+(rs.getDouble(8));
                    // sum_op_re=sum_op_re+(rs.getDouble(9));
                    // sum_v_re =sum_v_re+(rs.getDouble(10));
                    // j=j+1;
                    // if(rs.next()){
                        // out.println("<tr class=tr_input1><td>"+(j+1)+"</td>");
                        // out.println("<td  width='8%' >"+rs.getString(1)+"<input type=hidden name=\"INSTALLMENT"+j+"\" value=\""+rs.getString(1)+"\"></td>");
                        // out.println("<td align=right><input name=\"FACTOR"+j+"\" type=\"text\" value="+nf1.format(rs.getDouble(2))+" maxlength=\"10\" class=\"txt_input1\" disabled></td>");
                        // out.println("<td align=right><input name=\"PERCENTAGE"+j+"\" type=\"text\" value=\""+nf1.format(rs.getDouble(6))+"\" maxlength=\"8\" class=\"txt_input1\" disabled onchange=format_num(document.Form1.PERCENTAGE"+j+",'6');cha_val(\""+j+"\");cal_count()></td>");
                        // out.println("<td align=right><input name=\"NETAMT"+j+"\" type=\"text\" value="+nf.format(rs.getDouble(3))+" maxlength=\"25\" class=\"txt_input2\" disabled></td>");
                        // out.println("<td align=right><input name=\"VATAMT"+j+"\" type=\"text\" value="+nf.format(rs.getDouble(10))+" maxlength=\"25\" class=\"txt_input2\" disabled></td>");
                        // out.println("<td align=right><input name=\"GROSS"+j+"\" type=\"text\" value="+nf.format(rs.getDouble(4))+" maxlength=\"25\" class=\"txt_input2\" disabled></td>");
                        // out.println("<td align=right><input name=\"PRACENT"+j+"\" type=\"text\" value="+nf.format(rs.getDouble(5))+" maxlength=\"25\" class=\"txt_input2\" disabled></td>");
                        // out.println("<td align=right><input name=\"CASHOUT"+j+"\" type=\"text\" value="+nf.format(rs.getDouble(8))+" maxlength=\"25\" class=\"txt_input2\" onchange=format_num(document.Form1.CASHOUT"+j+",'0')></td>");
                        // out.println("<td align=right><input name=\"OUTPVT"+j+"\" type=\"text\" value="+nf.format(rs.getDouble(9))+" maxlength=\"25\" class=\"txt_input2\" disabled>");
                        // out.println("<input type=hidden name=\"CAP_"+j+"\" value=\""+nf.format(rs.getDouble(12))+"\"><input type=hidden name=\"INST_"+j+"\" value=\""+nf.format(rs.getDouble(11))+"\"></td>");
                        // //out.println("<td ><input name=\"AMI"+j+"\" type=\"text\" value=\""+nf.format(rs.getDouble(7))+"\" maxlength=\"25\" class=\"txt_input2\" ></td>");
                        // out.println("</tr>");
                        // sum_rate=sum_rate+(rs.getDouble(2));
                        // sum_rent=sum_rent+(rs.getDouble(3));
                        // sum_p_re=sum_p_re+(rs.getDouble(5));
                        // sum_g_re=sum_g_re+(rs.getDouble(4));
                        // sum_o_re=sum_o_re+(rs.getDouble(8));
                        // sum_op_re=sum_op_re+(rs.getDouble(9));
                        // sum_v_re =sum_v_re+(rs.getDouble(10));
                        // j=j+1;
                    // }
                    
                // }
                // //}
                // out.println("<tr class=tr_input><td  ></td><td  ></td>");
                // //out.println("<td  width='20%' ></td>");
                // out.println("<td  align=right><input type=hidden name=hid_count value="+j+">"+nf1.format(sum_rate)+"<input type=hidden name=sun_fact value="+nf.format(sum_rate)+"></td>");
                // out.println("<td></td>");
                // out.println("<td align=right>"+nf.format(sum_rent)+"<input type=hidden name=sun_rent value="+nf.format(sum_rent)+"></td>");
                // out.println("<td align=right>"+nf.format(sum_v_re)+"<input type=hidden name=sun_v_nt value="+nf.format(sum_v_re)+"></td>");
                // out.println("<td align=right>"+nf.format(sum_g_re)+"<input type=hidden name=sun_g_nt value="+nf.format(sum_g_re)+"></td>");
                // out.println("<td align=right>"+nf.format(sum_p_re)+"<input type=hidden name=sun_p_nt value="+nf.format(sum_p_re)+"></td>");
                // out.println("<td align=right>"+nf.format(sum_o_re)+"<input type=hidden name=sun_o_nt value="+nf.format(sum_o_re)+"></td>");
                // out.println("<td align=right>"+nf.format(sum_op_re)+"<input type=hidden name=sun_op_nt value="+nf.format(sum_op_re)+"></td>");
                
                // out.println("</tr>");
                
                // out.println("<tr class=tr_input>");
                // out.println("<td align=right colspan=9><input type=button name=top_b     value=\"Go to Top\" class=mainbut onclick=befor_end(document.Form1.end_b);  onMouseOver='load_roll_value(\"Top\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
                
                // out.println("</tr></table>");
                // out.println("</td>");
                
                // out.println("</table>");    
                // out.println("<tr class=tr_input> ");
                // out.println("<td valign=\"bottom\" height=\"20\"></td>");
                // out.println("</tr>");
                // out.println("</table>");
                // out.println("</form>");
                // out.println("</body>");
                // out.println("<script language=\"JavaScript1.2\" SRC=\""+m_html_client_url+"/validate_v1.js\"></SCRIPT> ");
                // out.println("</html>");
            // } 
            
            // else if(m_chksql.equals("get_outflow_recal")){
                
                // String m_rate		    = request.getParameter("rate");
                // String m_value      = request.getParameter("value");
                // String m_terms	    = request.getParameter("terms");
                // String m_freq		    = request.getParameter("freq");
                // String m_type       = request.getParameter("type");
                // String m_vat_per    = request.getParameter("tax");
                // String m_install    = request.getParameter("installments");
                // String m_factor     = request.getParameter("factor");
                // String m_pracent    = request.getParameter("pracent");
                // String m_percentage = request.getParameter("percentage");
                // String m_ami        = request.getParameter("ami");
                // String m_vat_app    = request.getParameter("tax_app");
                // String m_tax_rent   = request.getParameter("tax_rent");
                
                // String m_sup_cr_per = request.getParameter("supcrper");
                // String m_nibsm      = request.getParameter("nibsm");
                // String m_residual   = request.getParameter("residual");
                // String m_sup_credit = request.getParameter("sup_cr");
                // String m_other_cha  = request.getParameter("other_cha");
                // String m_maintenance= request.getParameter("maintan");
                // String m_int_base_m = request.getParameter("nitbasemar");
                // //String m_last_rent  = request.getParameter("last_rent");nitmar
                // String m_cash_out   = request.getParameter("cashout");
                // String m_trn_sub    = request.getParameter("trnsub");
                // String m_int_type   = request.getParameter("nittype");
                // String m_trn_type   = request.getParameter("trn_type");
                // String m_option     = request.getParameter("option");
                // String m_nitmar     = request.getParameter("nitmar");
                // String m_vat_val    = request.getParameter("vat_val");
                // String m_gro_val    = request.getParameter("gro_val");
                // String m_rental     = request.getParameter("rental");
                // String m_1rental    = request.getParameter("1rental");
                
                // //out.println("m_percentage="+m_percentage);											
                // callstmt1 = conn.prepareCall( "BEGIN "+m_schema_name+"."+ 
                    // "AF_MK_TEMP_PRICE_SAVE(:1,:2,:3,:4,:5,:6,:7,:8,:9,:10,:11,:12,:13,:14,:15,:16,:17,:18,:19,:20,:21,:22,:23,:24,:25,:26,:27,:28,:29,:30);END;");
                // callstmt1.setString(1 ,m_terms);
                // callstmt1.setString(2 ,m_freq);
                // callstmt1.setString(3 ,m_install);
                // callstmt1.setString(4 ,m_pracent);
                // callstmt1.setString(5 ,m_factor);
                // callstmt1.setString(6 ,m_type);
                // callstmt1.setString(7 ,m_username);
                // callstmt1.setString(8 ,m_rate);
                // callstmt1.setString(9 ,m_value);
                // callstmt1.setString(10,m_vat_per);
                // callstmt1.setString(11,m_percentage);	
                // callstmt1.setString(12,m_vat_app);	
                // callstmt1.setString(13,m_ami);	
                // callstmt1.setString(14,m_sup_credit);	
                // callstmt1.setString(15,m_sup_cr_per);	
                // callstmt1.setString(16,m_nibsm);	
                // callstmt1.setString(17,m_residual);	
                // callstmt1.setString(18,m_other_cha);	
                // callstmt1.setString(19,m_maintenance);	
                // callstmt1.setString(20,m_option);	
                // callstmt1.setString(21,m_trn_type);
                // callstmt1.setString(22,m_trn_sub);
                // callstmt1.setString(23,m_int_type);
                // callstmt1.setString(24,m_nitmar);
                // callstmt1.setString(25,m_cash_out);
                // callstmt1.setString(26,m_vat_val);
                // callstmt1.setString(27,m_gro_val);
                // callstmt1.setString(28,m_rental);
                // callstmt1.setString(29,m_1rental);
                // callstmt1.setString(30,m_tax_rent);
                
                // //out.println("t5");
                // callstmt1.execute();
                // //out.println("t6");
                
                
                // rs = stmt.executeQuery ("SELECT "+m_rate+","+m_rate+"/100,("+m_rate+"/100)/"+m_freq+","+m_freq+","+m_terms+","+m_value+","+
                    // "       "+m_schema_name+".AF_CO_CAL_FACTOR("+m_rate+","+m_freq+","+m_terms+",'"+m_type+"','"+m_ami+"'),"+
                    // "       "+m_vat_per+",/*"+m_freq+"**/"+m_terms+",'"+m_ami+"' "+
                    // " FROM DUAL");
                
                
                // out.println("<table class=table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" >");//main table start
                // out.println("<tr class=tr_input>");
                // out.println("<td valign=top  width=40%>");
                // double mm_tot_fact 				= 0;
                // double mm_tot_term 				= 0;
                // out.println("<table class=table border='1' width='100%' >");
                // out.println("<tr class=tr_input><td  width='100%' >");
                
                // if(rs.next()){
                    
                    // mm_tot_fact				= rs.getDouble(7);
                    // mm_tot_term				= rs.getInt(5);
                    
                // }	
                // out.println("<table class=table border='0' width='100%' >");
                // out.println("<tr class=tr_input>");
                // //out.println("<td colspan=4 align=right><input type=button name=cash_f  value=\"Cash Flow Pattern\" class=mainbut onclick=load_flow();             onMouseOver='load_roll_value(\"Cash Outflow\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
                // out.println("<td colspan=9 align=right><input type=button name=end_b   value=\"Go to End\" class=mainbut onclick=befor_end(document.Form1.top_b); onMouseOver='load_roll_value(\"End\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
                // out.println("</tr>");
                
                
                
                // out.println("<tr class=tr_input><td  width='5%' >Inst</td>");
                // out.println("<td  width='5%' ></td>");
                // //out.println("<td  width='20%' >Rate per Month(RM)</td>");
                // //out.println("<td  width='30%' >(RM+1)=X</td>");
                // out.println("<td  width='5%' align=right>Factor</td>");
                // out.println("<td  width='10%' align=right>Rental Factor</td>");
                // out.println("<td  width='15%' align=right>Net Rental</td>");
                // out.println("<td  width='15%' align=right>VAT</td>");
                // out.println("<td  width='15%' align=right>Gross Rental</td>");
                // out.println("<td  width='15%' align=right>P.V Inflow</td>");
                // out.println("<td  width='20%' align=right>Cash Outflow</td>");
                // out.println("<td  width='15%' align=right>P.V. Outflow</td>");
                // //out.println("<td  width='20%' >AMI</td>");
                
                
                // out.println("</tr>");
                // out.println("<tr class=tr_input><td  width='8%' ></td><td  ></td>");
                // out.println("<td id=m_fact align=right></td>");
                // out.println("<td ></td>");
                // out.println("<td id=n_re align=right></td>");
                // out.println("<td id=v_re align=right></td>");
                // out.println("<td id=g_re align=right></td>");
                // out.println("<td id=p_va align=right></td>");
                // out.println("<td id=o_va align=right></td>");
                // out.println("<td id=op_va align=right></td>");
                // //out.println("<td ><input name=\"AMI"+j+"\" type=\"text\" value=\""+nf.format(rs.getDouble(7))+"\" maxlength=\"25\" class=\"txt_input2\" ></td>");
                // out.println("</tr>");
                
                // double sum_rate=0;
                // double sum_rent=0;
                // double sum_p_re=0;
                // double sum_g_re=0;
                // double sum_v_re=0;
                // double sum_o_re=0;
                // double sum_op_re=0;
                // int m_start=0;
                // /*int m_end  =mm_term;
                
                // if(m_type.equals("ARREASE")){
                    // m_start=1;
                    // m_end  =mm_term+1;
                // }
                // */
                // int j=0;
                // //for(j=m_start;j<m_end;j++){
                // //out.println("J="+j);
                // //out.println("<td  width='20%' >"+nf.format(mm_rate_per_month)+"</td>");
                
                // rs = stmt.executeQuery (" SELECT A.INSTALLMENT, A.FACTOR, A.NEW_NET, A.NEW_GROSS, "+
                    // "        A.NEW_PRACENT, A.PERCENTAGE, A.AMI,CASH_OUT,CASH_OUT*FACTOR, "+
                    // "        A.NEW_GROSS-A.NEW_NET,INTEREST_AMOUNT,CAPITAL_AMOUNT "+ 
                    // "  FROM  "+m_schema_name+".AF_MK_TBD_PRICE_CAL A "+
                    // "	WHERE  ENT_USER  = '"+m_username+"'"+
                    // " ORDER BY INSTALLMENT");
                
                // while(rs.next()){
                    // //out.println("<td  width='30%' >"+nf.format(rs.getDouble(1))+"</td>");
                    // out.println("<tr class=tr_input><td>"+(j+1)+"</td>");
                    // out.println("<td  width='8%' >"+rs.getString(1)+"<input type=hidden name=\"INSTALLMENT"+j+"\" value=\""+rs.getString(1)+"\"></td>");
                    // out.println("<td ><input name=\"FACTOR"+j+"\" type=\"text\" value="+nf1.format(rs.getDouble(2))+" maxlength=\"10\" class=\"txt_input1\" disabled></td>");
                    // out.println("<td ><input name=\"PERCENTAGE"+j+"\" type=\"text\" value=\""+nf1.format(rs.getDouble(6))+"\" maxlength=\"8\" class=\"txt_input1\" disabled onchange=format_num(document.Form1.PERCENTAGE"+j+",'6');cha_val(\""+j+"\");cal_count()></td>");
                    // out.println("<td align=right><input name=\"NETAMT"+j+"\" type=\"text\" value="+nf.format(rs.getDouble(3))+" maxlength=\"25\" class=\"txt_input2\" disabled></td>");
                    // out.println("<td align=right><input name=\"VATAMT"+j+"\" type=\"text\" value="+nf.format(rs.getDouble(10))+" maxlength=\"25\" class=\"txt_input2\" disabled></td>");
                    // out.println("<td align=right><input name=\"GROSS"+j+"\" type=\"text\" value="+nf.format(rs.getDouble(4))+" maxlength=\"25\" class=\"txt_input2\" disabled></td>");
                    // out.println("<td align=right><input name=\"PRACENT"+j+"\" type=\"text\" value="+nf.format(rs.getDouble(5))+" maxlength=\"25\" class=\"txt_input2\" disabled></td>");
                    // out.println("<td align=right><input name=\"CASHOUT"+j+"\" type=\"text\" value="+nf.format(rs.getDouble(8))+" maxlength=\"25\" class=\"txt_input2\" onchange=format_num(document.Form1.CASHOUT"+j+",'0')></td>");
                    // out.println("<td align=right><input name=\"OUTPVT"+j+"\" type=\"text\" value="+nf.format(rs.getDouble(9))+" maxlength=\"25\" class=\"txt_input2\" disabled>");
                    // out.println("<input type=hidden name=\"CAP_"+j+"\" value=\""+nf.format(rs.getDouble(12))+"\"><input type=hidden name=\"INST_"+j+"\" value=\""+nf.format(rs.getDouble(11))+"\"></td>");
                    // //out.println("<td ><input name=\"AMI"+j+"\" type=\"text\" value=\""+nf.format(rs.getDouble(7))+"\" maxlength=\"25\" class=\"txt_input2\" ></td>");
                    // out.println("</tr>");
                    // sum_rate=sum_rate+(rs.getDouble(2));
                    // sum_rent=sum_rent+(rs.getDouble(3));
                    // sum_p_re=sum_p_re+(rs.getDouble(5));
                    // sum_g_re=sum_g_re+(rs.getDouble(4));
                    // sum_v_re=sum_v_re+(rs.getDouble(10));
                    // sum_o_re=sum_o_re+(rs.getDouble(8));
                    // sum_op_re=sum_op_re+(rs.getDouble(9));
                    // j=j+1;
                    
                    // if(rs.next()){
                        // out.println("<tr class=tr_input1><td>"+(j+1)+"</td>");
                        // out.println("<td  width='8%' >"+rs.getString(1)+"<input type=hidden name=\"INSTALLMENT"+j+"\" value=\""+rs.getString(1)+"\"></td>");
                        // out.println("<td ><input name=\"FACTOR"+j+"\" type=\"text\" value="+nf1.format(rs.getDouble(2))+" maxlength=\"10\" class=\"txt_input1\" disabled></td>");
                        // out.println("<td ><input name=\"PERCENTAGE"+j+"\" type=\"text\" value=\""+nf1.format(rs.getDouble(6))+"\" maxlength=\"8\" class=\"txt_input1\" disabled onchange=format_num(document.Form1.PERCENTAGE"+j+",'6');cha_val(\""+j+"\");cal_count()></td>");
                        // out.println("<td align=right><input name=\"NETAMT"+j+"\" type=\"text\" value="+nf.format(rs.getDouble(3))+" maxlength=\"25\" class=\"txt_input2\" disabled></td>");
                        // out.println("<td align=right><input name=\"VATAMT"+j+"\" type=\"text\" value="+nf.format(rs.getDouble(10))+" maxlength=\"25\" class=\"txt_input2\" disabled></td>");
                        // out.println("<td align=right><input name=\"GROSS"+j+"\" type=\"text\" value="+nf.format(rs.getDouble(4))+" maxlength=\"25\" class=\"txt_input2\" disabled></td>");
                        // out.println("<td align=right><input name=\"PRACENT"+j+"\" type=\"text\" value="+nf.format(rs.getDouble(5))+" maxlength=\"25\" class=\"txt_input2\" disabled></td>");
                        // out.println("<td align=right><input name=\"CASHOUT"+j+"\" type=\"text\" value="+nf.format(rs.getDouble(8))+" maxlength=\"25\" class=\"txt_input2\" onchange=format_num(document.Form1.CASHOUT"+j+",'0')></td>");
                        // out.println("<td align=right><input name=\"OUTPVT"+j+"\" type=\"text\" value="+nf.format(rs.getDouble(9))+" maxlength=\"25\" class=\"txt_input2\" disabled>");
                        // out.println("<input type=hidden name=\"CAP_"+j+"\" value=\""+nf.format(rs.getDouble(12))+"\"><input type=hidden name=\"INST_"+j+"\" value=\""+nf.format(rs.getDouble(11))+"\"></td>");
                        // //out.println("<td ><input name=\"AMI"+j+"\" type=\"text\" value=\""+nf.format(rs.getDouble(7))+"\" maxlength=\"25\" class=\"txt_input2\" ></td>");
                        // out.println("</tr>");
                        // sum_rate=sum_rate+(rs.getDouble(2));
                        // sum_rent=sum_rent+(rs.getDouble(3));
                        // sum_p_re=sum_p_re+(rs.getDouble(5));
                        // sum_g_re=sum_g_re+(rs.getDouble(4));
                        // sum_v_re=sum_v_re+(rs.getDouble(10));
                        // sum_o_re=sum_o_re+(rs.getDouble(8));
                        // sum_op_re=sum_op_re+(rs.getDouble(9));
                        // j=j+1;
                    // }
                // }
                // //}
                // out.println("<tr class=tr_input><td  ></td><td  ></td>");
                // //out.println("<td  width='20%' ></td>");
                // out.println("<td align=right><input type=hidden name=hid_count value="+j+">"+nf1.format(sum_rate)+"<input type=hidden name=sun_fact value="+nf.format(sum_rate)+"></td>");
                // out.println("<td></td>");
                // out.println("<td align=right>"+nf.format(sum_rent)+"<input type=hidden name=sun_rent value="+nf.format(sum_rent)+"></td>");
                // out.println("<td align=right>"+nf.format(sum_g_re)+"<input type=hidden name=sun_g_nt value="+nf.format(sum_g_re)+"></td>");
                // out.println("<td align=right>"+nf.format(sum_p_re)+"<input type=hidden name=sun_p_nt value="+nf.format(sum_p_re)+"></td>");
                // out.println("<td align=right>"+nf.format(sum_o_re)+"<input type=hidden name=sun_o_nt value="+nf.format(sum_o_re)+"></td>");
                // out.println("<td align=right>"+nf.format(sum_op_re)+"<input type=hidden name=sun_op_nt value="+nf.format(sum_op_re)+"></td>");
                // //out.println("<td></td>");
                
                // out.println("</tr>");
                
                // out.println("<tr class=tr_input>");
                // out.println("<td align=right colspan=9><input type=button name=top_b     value=\"Go to Top\" class=mainbut onclick=befor_end(document.Form1.end_b);  onMouseOver='load_roll_value(\"Top\");' onmouseout='load_roll_value(document.Form1.OPTION_DESC.value);'></td>");
                
                // out.println("</tr>");
                // out.println("</table>");
                
                
            // } 	
            // /* else if(m_chksql.equals("get_basic_price_cal")){
          // String m_rate		    = request.getParameter("rate");
          // String m_value      = request.getParameter("value");
          // String m_terms	    = request.getParameter("terms");
          // String m_freq		    = request.getParameter("freq");
          // String m_type       = request.getParameter("type");
                    // String m_vat_per    = request.getParameter("tax");
          // String m_install    = request.getParameter("installments");
                    // String m_factor     = request.getParameter("factor");
                    // String m_pracent    = request.getParameter("pracent");
                    // String m_percentage = request.getParameter("percentage");
                    // String m_ami        = request.getParameter("ami");
                    // String m_vat_app    = request.getParameter("tax_app");
                    // String m_tax_rent   = request.getParameter("tax_rent");
                             
          // String m_sup_cr_per = request.getParameter("supcrper");
                    // String m_nibsm      = request.getParameter("nibsm");
          // String m_residual   = request.getParameter("residual");
                    // String m_sup_credit = request.getParameter("sup_cr");
          // String m_other_cha  = request.getParameter("other_cha");
                    // String m_maintanance= request.getParameter("maintan");
            // String m_int_base_m = request.getParameter("nitbasemar");
          // String m_last_rent  = request.getParameter("last_rent");
          // String m_cash_out   = request.getParameter("cashout");
          // String m_trn_sub    = request.getParameter("trnsub");
          // String m_int_type   = request.getParameter("nittype");
          // String m_trn_type   = request.getParameter("trn_type");
                                                    
          
          // rs = stmt.executeQuery ("SELECT "+m_rate+","+m_rate+"/100,("+m_rate+"/100)/"+m_freq+","+m_freq+","+m_terms+","+m_value+","+
                                            // "       "+m_schema_name+".AF_CO_CAL_FACTOR("+m_rate+","+m_freq+","+m_terms+",'"+m_type+"','"+m_ami+"'),"+
                                                                    // "       "+m_vat_per+",/*"+m_freq+"**  /"+m_terms+",'"+m_ami+"' "+
                                                                    // " FROM DUAL");
                                                                    
          // /*
                    // out.println("<html><head>");
          // out.println("<title>PMT Value - Formulation</title></head>");
          // out.println("<body bgcolor='white'>");
          // out.println("<form name='Form1'>");
          // out.println("<br>");
          // *  /
          // int 	 mm_freq					  = 0;
          // int 	 mm_terms						= 0;
          // int    mm_term            = 0;
                    // double mm_value						= 0;
          // double mm_rate_per_month  = 0;
                    // double mm_tot_fact 				= 0;
                    // double mm_vat_per 				= 0;
                    // int mm_ami             = 0;
                    
          // if(rs.next()){
              
              // mm_rate_per_month = rs.getDouble(3);
              // mm_value					= rs.getDouble(6);
              // mm_terms					= rs.getInt(5);
              // mm_term 					= rs.getInt(9);
              // mm_freq						= rs.getInt(4);
                            // mm_tot_fact				= rs.getDouble(7);
                            // mm_vat_per				= rs.getDouble(8);
              // mm_ami     				= rs.getInt(10);
               
              // out.println("<table class=table border='1' width='100%' >");
              // out.println("<tr class=tr_input><td  width='40%' >Total Factor</td>");
              // out.println("<td width='60%' >"+nf.format(rs.getDouble(7))+"</td></tr>");
              // /*out.println("<tr><td  width='40%' >Rate (%)</td>");
              // out.println("<td width='60%' style='{text-align:left; font: 9pt arial; }'>"+nf.format(rs.getDouble(1))+"</td></tr>");
              // out.println("<tr><td  width='40%' >Rate </td>");
              // out.println("<td width='60%' style='{text-align:left; font: 9pt arial; }'>"+nf.format(rs.getDouble(2))+"</td></tr>");
              // out.println("<tr><td  width='40%' >Rate per Month</td>");
              // out.println("<td width='60%' style='{text-align:left; font: 9pt arial; }'>"+nf.format(rs.getDouble(3))+"</td></tr>");
              // out.println("<tr><td  width='40%' >Payment Frequency</td>");
              // out.println("<td width='60%' style='{text-align:left; font: 9pt arial; }'>"+nf.format(rs.getDouble(4))+"</td></tr>");
              // out.println("<tr><td  width='40%' >No of Installaments</td>");
              // out.println("<td width='60%' style='{text-align:left; font: 9pt arial; }'>"+nf.format(rs.getDouble(5))+"</td></tr>");
              // * /
                            // out.println("</table>");
                            
          // }
          
          // out.println("<br>");
          
          // out.println("<table class=table border='0' width='100%' class=table>");
          // out.println("<tr class=tr_input><td  width='10%' >Installment No(Y)</td>");
          // //out.println("<td  width='20%' >Rate per Month(RM)</td>");
          // //out.println("<td  width='30%' >(RM+1)=X</td>");
          // out.println("<td  width='10%' >Factor</td>");
                    // out.println("<td  width='10%' >Percentage</td>");
                    // out.println("<td  width='20%' >Net Rental</td>");
                    // out.println("<td  width='20%' >VAT</td>");
                    // out.println("<td  width='20%' >Gross Rental</td>");
                    // out.println("<td  width='20%' >Pracent Value</td>");
                    // //out.println("<td  width='20%' >AMI</td>");
                    
                    
          // out.println("</tr>");
          // double sum_rate=0;
          // double sum_rent=0;
                    // double sum_p_re=0;
                    // int m_start=0;
                    // int m_end  =mm_term;
                    
                    // if(m_type.equals("ARREASE")){
                      // m_start=1;
                        // m_end  =mm_term+1;
                    // }
                    // int j=0;
          // for(j=m_start;j<m_end;j++){
              // //out.println("J="+j);
              // out.println("<tr class=tr_input><td  width='8%' >"+j+"<input type=hidden name=\"INSTALLMENT"+j+"\" value=\""+rs.getString(1)+"\"></td>");
              // //out.println("<td  width='20%' >"+nf.format(mm_rate_per_month)+"</td>");
                                                    
                            // rs = stmt.executeQuery ("SELECT "+(mm_rate_per_month+1)+",1/POWER("+(mm_rate_per_month+1)+","+j+"),"+
                                                            // ""+m_value+"/"+mm_tot_fact+"/(1+("+m_vat_app+"/100)),"+m_value+"/"+mm_tot_fact+","+//"+mm_vat_per+"
                                                                                            // ""+m_value+"/"+mm_tot_fact+"/1/POWER("+(mm_rate_per_month+1)+","+j+") "+
                                                                            // "FROM   DUAL ");

              // if(rs.next()){
                  // //out.println("<td  width='30%' >"+nf.format(rs.getDouble(1))+"</td>");
                  // out.println("<td ><input name=\"FACTOR"+j+"\" type=\"text\" value="+nf.format(rs.getDouble(2))+" maxlength=\"10\" class=\"txt_input1\" disabled></td>");
                  // out.println("<td ><input name=\"PERCENTAGE"+j+"\" type=\"text\" value=\"1\" maxlength=\"5\" class=\"txt_input1\" ></td>");
                  // out.println("<td ><input name=\"NETAMT"+j+"\" type=\"text\" value="+nf.format(rs.getDouble(3))+" maxlength=\"25\" class=\"txt_input2\" ></td>");
                  // out.println("<td ><input name=\"GROSS"+j+"\" type=\"text\" value="+nf.format(rs.getDouble(4))+" maxlength=\"25\" class=\"txt_input2\" ></td>");
                  // out.println("<td ><input name=\"PRACENT"+j+"\" type=\"text\" value="+nf.format(rs.getDouble(5))+" maxlength=\"25\" class=\"txt_input2\" ></td>");
                  // //out.println("<td ><input name=\"AMI"+j+"\" type=\"text\" value=\"0\" maxlength=\"25\" class=\"txt_input2\" ></td>");
                  // out.println("</tr>");
                  // sum_rate=sum_rate+(rs.getDouble(2));
                                    // sum_rent=sum_rent+(rs.getDouble(3));
                                    // sum_p_re=sum_p_re+(rs.getDouble(5));
              // }
          // }
          // out.println("<tr class=tr_input><td  ></td>");
          // //out.println("<td  width='20%' ></td>");
          // out.println("<td  align=right><input type=hidden name=hid_cou value="+j+">"+nf.format(sum_rate)+"</td>");
          // out.println("<td></td>");
          // out.println("<td align=right>"+nf.format(sum_rent)+"</td>");
          // out.println("<td></td>");
          // out.println("<td align=right>"+nf.format(sum_p_re)+"</td>");
          // //out.println("<td></td>");
          // out.println("</tr></table>");
          
        
      // }
            // */
            
            // //=========================================================================================================================			
            // /*else {
                // out.println("Undefined");
            // }
            // */
            // //out.close();
            // //conn.close();
            // //this.destroy();
            
            
        }
        catch (Exception e) {
            try{out.println(e.toString());}catch(Exception e1){}
            //return null;
        }finally{
            if(rs    !=null){try{rs.close();   }catch(Exception e){}}
            if(stmt  !=null){try{stmt.close(); }catch(Exception e){}}
            if(conn  !=null){try{conn.close(); }catch(Exception e){}}
            if(out   !=null){try{out.close();  }catch(Exception e){}}
            //try{conn.setAutoCommit(true);
        }
    }
}
