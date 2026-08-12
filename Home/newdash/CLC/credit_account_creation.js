           function clear_screen_values() {
               document.Form1.TXT_CLIENT_CODE.value = "";
               document.Form1.TXT_CLIENT_NAME.value = "";
               document.Form1.TXT_CLIENT_ACCOUNT_NUMBER.value = "";
               document.Form1.TXT_S01_OWNERSHIP.value = "";
               document.Form1.TXT_S01_ADDRESS_LINE_1.value = "";
               document.Form1.TXT_S01_ADDRESS_LINE_2.value = "";
               document.Form1.TXT_S01_CITY.value = "";
               document.Form1.TXT_S01_CITY_NAME.value = "";
               document.Form1.TXT_S01_DISTRICT_NAME.value = "";
               document.Form1.TXT_S01_PROVINCE_NAME.value = "";
               document.Form1.TXT_S01_COUNTRY_NAME.value = "";
               document.Form1.hid_contact_person_count.value = "0";
               document.Form1.hid_third_party_guarantors_details_count.value = "0";
               document.Form1.hid_accounts_and_banking_details_count.value = "0";
               document.Form1.hid_auditors_details_count.value = "0";
               document.Form1.hid_details_of_credit_facilities_count.value = "0";
               document.Form1.hid_product_details_count.value = "0";
               document.Form1.hid_suppliers_customers_details_count.value = "0";
               document.Form1.hid_top_debtors_count.value = "0";
               document.getElementById("DIV_CONTACT_PERSON").innerHTML = "";
               document.getElementById("DIV_THIRD_PARTY_GUARANTORS_DETAILS").innerHTML = "";
                   document.getElementById("DIV_AUDITORS_DETAILS").innerHTML = "";
                   document.getElementById("DIV_DETAILS_OF_CREDIT_FACILITIES").innerHTML = "";
               document.getElementById("DIV_ACCOUNTS_AND_BANKING_DETAILS").innerHTML = "";
               document.getElementById("DIV_CLIENT_DEBTOR_DETAILS").innerHTML = "";
               document.getElementById("DIV_PRODUCT_DETAILS").innerHTML = "";
               document.getElementById("DIV_SUPPLIERS_CUSTOMERS_DETAILS").innerHTML = "";
               document.getElementById("DIV_TOP_DEBTORS").innerHTML = "";
               add_contact_person();
               delete_third_party_guarantors_details(0);
               delete_accounts_and_banking_details(0);
                   delete_auditors_details(0);
                   delete_details_of_credit_facilities(0);
               add_client_debtor_details();
               delete_product_details(0);
               delete_suppliers_customers_details(0);
               delete_top_debtors(0);
           }



function load_data_sections() {
               add_contact_person();
               delete_third_party_guarantors_details(0);
               delete_accounts_and_banking_details(0);
                   delete_auditors_details(0);
                   delete_details_of_credit_facilities(0);
               add_client_debtor_details();
               delete_product_details(0);
               delete_suppliers_customers_details(0);
               delete_top_debtors(0);
               delete_details_of_credit_facilities(0);
               var filestyle_class = "TXT_S01_MAP";
               init_filestyle(filestyle_class);
               filestyle_class = "TXT_S01_PICTURE";
               init_filestyle(filestyle_class);
           }
           function load_calendar_2(calendar_row_number) {
               document.Form1.hid_calendar_row_number.value = calendar_row_number;
           }
           function load_calendar_1(calendar_type) {
               document.Form1.hid_calendar_type.value = calendar_type;
               load_calendar(calendar_type);
           }
           function load_calendar(calendar_type) {
               popupwin = window.open(servlet_client_url+":"+client_t3_port+"/"+client_name+"CO_Calendar_Window", "oBj","left=450,top=200,width=320,height=230");
           }
           function load_c_date(m_value) {
               m_value_date = m_value.substr(0, m_value.indexOf('-'));
               if (m_value_date.length < 2) {
                   m_value_date = 0 + m_value_date;
               }
               m_value = m_value.substr(m_value.indexOf('-') + 1, m_value.length);
               m_value_month = m_value.substr(0, m_value.indexOf('-'));
               if (m_value_month.length < 2) {
                   m_value_month = 0 + m_value_month;
               }
               m_value_year = m_value.substr(m_value.indexOf('-') + 1, m_value.length);
               if (document.Form1.hid_calendar_type.value == "s03_c_doi") {
                   document.Form1.TXT_S03_C_DATE_OF_INCORPORATION_DD.value = m_value_date;
                   document.Form1.TXT_S03_C_DATE_OF_INCORPORATION_MM.value = m_value_month;
                   document.Form1.TXT_S03_C_DATE_OF_INCORPORATION_YY.value = m_value_year;
               }
               if (document.Form1.hid_calendar_type.value == "s03_c_dob") {
                   document.Form1.TXT_S03_I_DATE_OF_BIRTH_DD.value = m_value_date;
                   document.Form1.TXT_S03_I_DATE_OF_BIRTH_MM.value = m_value_month;
                   document.Form1.TXT_S03_I_DATE_OF_BIRTH_YY.value = m_value_year;
               }
               if (document.Form1.hid_calendar_type.value == "s07_sd") {
               }
           }
 




function clear_fields() {
               if (document.Form1.hid_help_type.value == "1") {
                   document.Form1.TXT_CLIENT_CODE.value = "";
                   document.Form1.TXT_CLIENT_NAME.value = "";
                   document.Form1.TXT_S01_ADDRESS_TYPE.selectedIndex = 0;
                   document.Form1.TXT_S01_OWNERSHIP.value = "";
                   document.Form1.TXT_S01_ADDRESS_LINE_1.value = "";
                   document.Form1.TXT_S01_ADDRESS_LINE_2.value = "";
                   document.Form1.TXT_S01_CITY.value = "";
                   document.Form1.TXT_S01_CITY_NAME.value = "";
                   document.Form1.TXT_S01_DISTRICT_NAME.value = "";
                   document.Form1.TXT_S01_PROVINCE_NAME.value = "";
                   document.Form1.TXT_S01_COUNTRY_NAME.value = "";
                   document.Form1.hid_contact_person_count.value = "0";
                   document.Form1.hid_third_party_guarantors_details_count.value = "0";
                   document.Form1.hid_accounts_and_banking_details_count.value = "0";
                   document.Form1.hid_auditors_details_count.value = "0";
                   document.Form1.hid_details_of_credit_facilities_count.value = "0";
                   document.Form1.hid_product_details_count.value = "0";
                   document.Form1.hid_suppliers_customers_details_count.value = "0";
                   document.Form1.hid_top_debtors_count.value = "0";
                   document.getElementById("DIV_CONTACT_PERSON").innerHTML = "";
                   document.getElementById("DIV_THIRD_PARTY_GUARANTORS_DETAILS").innerHTML = "";
                   document.getElementById("DIV_AUDITORS_DETAILS").innerHTML = "";
                   document.getElementById("DIV_DETAILS_OF_CREDIT_FACILITIES").innerHTML = "";
                   document.getElementById("DIV_ACCOUNTS_AND_BANKING_DETAILS").innerHTML = "";
                   document.getElementById("DIV_CLIENT_DEBTOR_DETAILS").innerHTML = "";
                   document.getElementById("DIV_PRODUCT_DETAILS").innerHTML = "";
                   document.getElementById("DIV_SUPPLIERS_CUSTOMERS_DETAILS").innerHTML = "";
                   document.getElementById("DIV_TOP_DEBTORS").innerHTML = "";
                   add_contact_person();
                   delete_third_party_guarantors_details(0);
                   delete_accounts_and_banking_details(0);
                   delete_details_of_credit_facilities(0);
                   add_client_debtor_details();
                   delete_product_details(0);
                   delete_suppliers_customers_details(0);
                   delete_top_debtors(0);
                   delete_auditors_details(0);
                   delete_details_of_credit_facilities(0);
               }
               else if (document.Form1.hid_help_type.value == "2") {
                   document.Form1.TXT_CLIENT_CODE.value = "";
                   document.Form1.TXT_CLIENT_NAME.value = "";
                   document.Form1.TXT_CLIENT_ACCOUNT_NUMBER.value = "";
                   document.Form1.TXT_S01_ADDRESS_TYPE.selectedIndex = 0;
                   document.Form1.TXT_S01_OWNERSHIP.value = "";
                   document.Form1.TXT_S01_ADDRESS_LINE_1.value = "";
                   document.Form1.TXT_S01_ADDRESS_LINE_2.value = "";
                   document.Form1.TXT_S01_CITY.value = "";
                   document.Form1.TXT_S01_CITY_NAME.value = "";
                   document.Form1.TXT_S01_DISTRICT_NAME.value = "";
                   document.Form1.TXT_S01_PROVINCE_NAME.value = "";
                   document.Form1.TXT_S01_COUNTRY_NAME.value = "";
                   document.Form1.hid_contact_person_count.value = "0";
                   document.Form1.hid_third_party_guarantors_details_count.value = "0";
                   document.Form1.hid_accounts_and_banking_details_count.value = "0";
                   document.Form1.hid_auditors_details_count.value = "0";
               document.Form1.hid_details_of_credit_facilities_count.value = "0";
                   document.Form1.hid_product_details_count.value = "0";
                   document.Form1.hid_suppliers_customers_details_count.value = "0";
                   document.Form1.hid_top_debtors_count.value = "0";
                   document.getElementById("DIV_CONTACT_PERSON").innerHTML = "";
                   document.getElementById("DIV_THIRD_PARTY_GUARANTORS_DETAILS").innerHTML = "";
                   document.getElementById("DIV_AUDITORS_DETAILS").innerHTML = "";
                   document.getElementById("DIV_DETAILS_OF_CREDIT_FACILITIES").innerHTML = "";
                   document.getElementById("DIV_ACCOUNTS_AND_BANKING_DETAILS").innerHTML = "";
                   document.getElementById("DIV_CLIENT_DEBTOR_DETAILS").innerHTML = "";
                   document.getElementById("DIV_PRODUCT_DETAILS").innerHTML = "";
                   document.getElementById("DIV_SUPPLIERS_CUSTOMERS_DETAILS").innerHTML = "";
                   document.getElementById("DIV_TOP_DEBTORS").innerHTML = "";
                   add_contact_person();
                   delete_third_party_guarantors_details(0);
                   delete_accounts_and_banking_details(0);
                   add_client_debtor_details();
                   delete_product_details(0);
                   delete_suppliers_customers_details(0);
                   delete_top_debtors(0);
                   delete_auditors_details(0);
                   delete_details_of_credit_facilities(0);
               }
               else if (document.Form1.hid_help_type.value == "3") {
                   document.Form1.TXT_S01_CITY.value = "";
                   document.Form1.TXT_S01_CITY_NAME.value = "";
                   document.Form1.TXT_S01_DISTRICT_NAME.value = "";
                   document.Form1.TXT_S01_PROVINCE_NAME.value = "";
                   document.Form1.TXT_S01_COUNTRY_NAME.value = "";
               }
               else if (document.Form1.hid_help_type.value == "4") {
                   var m_row_number = document.Form1.hid_help_row_number.value;
                   document.Form1.elements["TXT_S03_CITY_" + m_row_number].value = "";
                   document.Form1.elements["TXT_S03_CITY_NAME_" + m_row_number].value = "";
                   document.Form1.elements["TXT_S03_DISTRICT_NAME_" + m_row_number].value = "";
                   document.Form1.elements["TXT_S03_PROVINCE_NAME_" + m_row_number].value = "";
                   document.Form1.elements["TXT_S03_COUNTRY_NAME_" + m_row_number].value = "";
               }
               else if (document.Form1.hid_help_type.value == "5") {
                   var m_row_number = document.Form1.hid_help_row_number.value;
                   document.Form1.elements["TXT_S07_BANK_" + m_row_number].value = "";
                   document.Form1.elements["TXT_S07_BANK_NAME_" + m_row_number].value = "";
                   document.Form1.elements["TXT_S07_BRANCH_" + m_row_number].value = "";
                   document.Form1.elements["TXT_S07_BRANCH_NAME_" + m_row_number].value = "";
               }
               else if (document.Form1.hid_help_type.value == "6") {
                   var m_row_number = document.Form1.hid_help_row_number.value;
                   document.Form1.elements["TXT_S07_BRANCH_" + m_row_number].value = "";
                   document.Form1.elements["TXT_S07_BRANCH_NAME_" + m_row_number].value = "";
               }
               else if (document.Form1.hid_help_type.value == "7") {
                   var m_row_number = document.Form1.hid_help_row_number.value;
                   document.Form1.elements["TXT_S09_PRODUCT_CATEGORY_" + m_row_number].value = "";
                   document.Form1.elements["TXT_S09_PRODUCT_CATEGORY_NAME_" + m_row_number].value = "";
               }
               else if (document.Form1.hid_help_type.value == "8") {
                   var m_row_number = document.Form1.hid_help_row_number.value;
                   document.Form1.elements["TXT_S10_CITY_" + m_row_number].value = "";
                   document.Form1.elements["TXT_S10_CITY_NAME_" + m_row_number].value = "";
                   document.Form1.elements["TXT_S10_DISTRICT_NAME_" + m_row_number].value = "";
                   document.Form1.elements["TXT_S10_PROVINCE_NAME_" + m_row_number].value = "";
                   document.Form1.elements["TXT_S10_COUNTRY_NAME_" + m_row_number].value = "";
               }
               else if (document.Form1.hid_help_type.value == "88") {
                   var m_row_number = document.Form1.hid_help_row_number.value;
                   document.Form1.elements["TXT_S06_CITY_" + m_row_number].value = "";
                   document.Form1.elements["TXT_S06_CITY_NAME_" + m_row_number].value = "";
                   document.Form1.elements["TXT_S06_DISTRICT_NAME_" + m_row_number].value = "";
                   document.Form1.elements["TXT_S06_PROVINCE_NAME_" + m_row_number].value = "";
                   document.Form1.elements["TXT_S06_COUNTRY_NAME_" + m_row_number].value = "";
               }
               else if (document.Form1.hid_help_type.value == "111") {
                   var m_row_number = document.Form1.hid_help_row_number.value;
                   document.Form1.elements["TXT_S08_CITY_" + m_row_number].value = "";
                   document.Form1.elements["TXT_S08_CITY_NAME_" + m_row_number].value = "";
                   document.Form1.elements["TXT_S08_DISTRICT_NAME_" + m_row_number].value = "";
                   document.Form1.elements["TXT_S08_PROVINCE_NAME_" + m_row_number].value = "";
                   document.Form1.elements["TXT_S08_COUNTRY_NAME_" + m_row_number].value = "";
               }
               else if (document.Form1.hid_help_type.value == "9") {
               }
               else if (document.Form1.hid_help_type.value == "10") {
                   var m_row_number = document.Form1.hid_help_row_number.value;
                   document.Form1.elements["TXT_S11_CITY_" + m_row_number].value = "";
                   document.Form1.elements["TXT_S11_CITY_NAME_" + m_row_number].value = "";
                   document.Form1.elements["TXT_S11_DISTRICT_NAME_" + m_row_number].value = "";
                   document.Form1.elements["TXT_S11_PROVINCE_NAME_" + m_row_number].value = "";
                   document.Form1.elements["TXT_S11_COUNTRY_NAME_" + m_row_number].value = "";
               }
           }
           function setData(data) {
               if (document.Form1.hid_help_type.value == "1") {
                   help_update_client_assign_data(data);
               }
               else if (document.Form1.hid_help_type.value == "2") {
                   help_update_client_edit_assign_data(data);
               }
               else if (document.Form1.hid_help_type.value == "3") {
                   help_update_s01_city_assign_data(data);
               }
               else if (document.Form1.hid_help_type.value == "4") {
                   help_update_s03_city_assign_data(data);
               }
               else if (document.Form1.hid_help_type.value == "5") {
                   help_update_s07_bank_name_assign_data(data);
               }
				else if (document.Form1.hid_help_type.value == "6") { // isj
                   help_update_s07_branch_name_assign_data(data);
               }
               else if (document.Form1.hid_help_type.value == "7") {
                   help_update_s09_product_category_assign_data(data);
               }
               else if (document.Form1.hid_help_type.value == "8") {
                   help_update_s10_city_assign_data(data);
               }
               else if (document.Form1.hid_help_type.value == "9") {
                   help_update_s11_debtor_assign_data(data);
               }
               else if (document.Form1.hid_help_type.value == "10") {
                   help_update_s11_city_assign_data(data);
               }
               else if (document.Form1.hid_help_type.value == "11") {
                   help_update_s02_city_assign_data(data);
               }
               else if (document.Form1.hid_help_type.value == "88") {
                   help_update_s06_city_assign_data(data);
               }
               else if (document.Form1.hid_help_type.value == "111") {
                   help_update_s08_city_assign_data(data);
               }
           }

function help_update_s01_city_assign_data(data) { 
 document.Form1.TXT_S01_CITY.value = data.colomn_2; 
document.Form1.TXT_S01_CITY_NAME.value = data.colomn_3; 
         setTimeout('document.Form1.elements[\'BUT_HELP_S01_CITY\'].focus()', 1); 
                         document.Form1.hid_validation_type.value = "1"; 
                         var m_city_code = document.Form1.TXT_S01_CITY.value; 
                         m_url = servlet_client_url+":"+client_t3_port+"/"+client_name+"FA_MK_sql_validations?chksql=get_global_address_codes&city_code="+m_city_code; 
                         load_interface(m_url, 'XML'); 
                     } 
			
		             function help_update_s06_city_assign_data(data) { 
                         var m_row_number = document.Form1.hid_help_row_number.value; 
                         document.Form1.elements["TXT_S06_CITY_" + m_row_number].value = data.colomn_2; 
                         document.Form1.elements["TXT_S06_CITY_NAME_" + m_row_number].value = data.colomn_3; 
                         setTimeout('document.Form1.elements[\'TXT_S06_TELEPHONE_1_' + m_row_number + '\'].select()', 1); 
                         document.Form1.hid_validation_type.value = "152"; 
                         var m_city_code = document.Form1.elements["TXT_S06_CITY_" + m_row_number].value; 
                         m_url = servlet_client_url+":"+client_t3_port+"/"+client_name+"FA_MK_sql_validations?chksql=get_global_address_codes&city_code="+m_city_code; 
                         load_interface(m_url, 'XML'); 
                     } 
			
	                 function help_update_s08_city_assign_data(data) { 
                         var m_row_number = document.Form1.hid_help_row_number.value; 
                         document.Form1.elements["TXT_S08_CITY_" + m_row_number].value = data.colomn_2; 
                         document.Form1.elements["TXT_S08_CITY_NAME_" + m_row_number].value = data.colomn_3; 
                         setTimeout('document.Form1.elements[\'TXT_S08_TYPE_OF_FACILITY_' + m_row_number + '\'].focus()', 1); 
                         document.Form1.hid_validation_type.value = "153"; 
                         var m_city_code = document.Form1.elements["TXT_S08_CITY_" + m_row_number].value; 
                         m_url = servlet_client_url+":"+client_t3_port+"/"+client_name+"FA_MK_sql_validations?chksql=get_global_address_codes&city_code="+m_city_code; 
                         load_interface(m_url, 'XML'); 
                     } 


					
						            function help_update_s02_city_assign_data(data) { 
						                var m_row_number = document.Form1.hid_help_row_number.value; 
                            document.Form1.elements["TXT_S02_CITY_" + m_row_number].value = data.colomn_2; 
                            document.Form1.elements["TXT_S02_CITY_NAME_" + m_row_number].value = data.colomn_3; 
                            document.Form1.hid_validation_type.value = "2222"; 
                            var m_city_code = document.Form1.elements["TXT_S02_CITY_" + m_row_number].value; 
                            m_url = servlet_client_url+":"+client_t3_port+"/"+client_name+"FA_MK_sql_validations?chksql=get_global_address_codes&city_code="+m_city_code; 
                            load_interface(m_url, 'XML'); 
                        } 

         
                     function help_update_s03_city_assign_data(data) { 
                         var m_row_number = document.Form1.hid_help_row_number.value; 
                         document.Form1.elements["TXT_S03_CITY_" + m_row_number].value = data.colomn_2; 
                         document.Form1.elements["TXT_S03_CITY_NAME_" + m_row_number].value = data.colomn_3; 
                         setTimeout('document.Form1.elements[\'TXT_S03_TELEPHONE_1_' + m_row_number + '\'].select()', 1); 
                         document.Form1.hid_validation_type.value = "2"; 
                         var m_city_code = document.Form1.elements["TXT_S03_CITY_" + m_row_number].value; 
                         m_url = servlet_client_url+":"+client_t3_port+"/"+client_name+"FA_MK_sql_validations?chksql=get_global_address_codes&city_code="+m_city_code; 
                         load_interface(m_url, 'XML'); 
                     }



                    function hideCompanyNameLabel(m_row_number) {
                        var str_customer_type_object_name      = 'TXT_S10_CUSTOMER_TYPE_' + m_row_number;
                        var str_company_name_object_name       = 'TXT_S10_COMPANY_NAME_' + m_row_number;
                        var str_company_name_div_tag_id        = 'DIV_TXT_S10_COMPANY_NAME_' + m_row_number;
         
                        var str_title_object_name          = 'TXT_S10_TITLE_2_' + m_row_number;
                        var str_title_div_tag_id           = 'DIV_TXT_S10_TITLE_2_' + m_row_number;
                        var str_name_object_name       		= 'TXT_S10_LAST_NAME_2_' + m_row_number;
                        var str_name_div_tag_id        		= 'DIV_TXT_S10_LAST_NAME_2_' + m_row_number;
                        var str_initials_object_name       = 'TXT_S10_INITIALS_2_' + m_row_number;
                        var str_initials_div_tag_id        = 'DIV_TXT_S10_INITIALS_2_' + m_row_number;
                        var str_detail_name_object_name    = 'TXT_S10_INITIALS_DETAILS_2_' + m_row_number;
                        var str_detail_name_div_tag_id     = 'DIV_TXT_S10_INITIALS_DETAILS_2_' + m_row_number;
         
                        var str_relation_object_name       = 'TXT_S10_RELATION_' + m_row_number;
                        var str_relation_div_tag_id        = 'DIV_TXT_S10_RELATION_' + m_row_number;
                        var str_comment_object_name        = 'TXT_S10_COMMENTS_' + m_row_number;
                        var str_comment_div_tag_id         = 'DIV_TXT_S10_COMMENTS_' + m_row_number;
         
                        var str_contact_person_div_tag_id  = 'DIV_TXT_S10_CONTACT_PERSON_' + m_row_number;
         
                        if (document.forms[0].elements[str_customer_type_object_name].value == 'I') {
                           document.forms[0].elements[str_company_name_object_name].value = '';
                           document.forms[0].elements[str_company_name_object_name].style.visibility = 'hidden';
                           document.getElementById(str_company_name_div_tag_id).style.visibility = 'hidden';
         
                           document.getElementById(str_contact_person_div_tag_id).style.visibility = 'visible';
         
                           document.forms[0].elements[str_title_object_name].style.visibility = 'visible';
                           document.getElementById(str_title_div_tag_id).style.visibility = 'visible';
         
                           document.forms[0].elements[str_name_object_name].style.visibility = 'visible';
                           document.getElementById(str_name_div_tag_id).style.visibility = 'visible';
         
                           document.forms[0].elements[str_initials_object_name].style.visibility = 'visible';
                           document.getElementById(str_initials_div_tag_id).style.visibility = 'visible';
         
                           document.forms[0].elements[str_detail_name_object_name].style.visibility = 'visible';
                           document.getElementById(str_detail_name_div_tag_id).style.visibility = 'visible';
         
                           document.forms[0].elements[str_relation_object_name].style.visibility = 'visible';
                           document.getElementById(str_relation_div_tag_id).style.visibility = 'visible';
         
         //                  document.forms[0].elements[str_comment_object_name].style.visibility = 'visible';
         //                  document.getElementById(str_comment_div_tag_id).style.visibility = 'visible';
         
                        }
                        else if (document.forms[0].elements[str_customer_type_object_name].value == 'C') {
                           document.forms[0].elements[str_company_name_object_name].style.visibility = 'visible';
                           document.getElementById(str_company_name_div_tag_id).style.visibility = 'visible';
         
                           document.getElementById(str_contact_person_div_tag_id).style.visibility = 'hidden';
         
                           document.forms[0].elements[str_title_object_name].value = '';
                           document.forms[0].elements[str_title_object_name].style.visibility = 'hidden';
                           document.getElementById(str_title_div_tag_id).style.visibility = 'hidden';
         
                           document.forms[0].elements[str_name_object_name].value = '';
                           document.forms[0].elements[str_name_object_name].style.visibility = 'hidden';
                           document.getElementById(str_name_div_tag_id).style.visibility = 'hidden';
         
                           document.forms[0].elements[str_initials_object_name].value = '';
                           document.forms[0].elements[str_initials_object_name].style.visibility = 'hidden';
                           document.getElementById(str_initials_div_tag_id).style.visibility = 'hidden';
         
                           document.forms[0].elements[str_detail_name_object_name].value = '';
                           document.forms[0].elements[str_detail_name_object_name].style.visibility = 'hidden';
                           document.getElementById(str_detail_name_div_tag_id).style.visibility = 'hidden';
         
                           document.forms[0].elements[str_relation_object_name].value = '';
                           document.forms[0].elements[str_relation_object_name].style.visibility = 'hidden';
                           document.getElementById(str_relation_div_tag_id).style.visibility = 'hidden';
         
        //                   document.forms[0].elements[str_comment_object_name].value = '';
         //                  document.forms[0].elements[str_comment_object_name].style.visibility = 'hidden';
         //                  document.getElementById(str_comment_div_tag_id).style.visibility = 'hidden';
         
                        }
                    }
         
                    function hideCompanyNameLabelGuarantee(m_row_number) {
                        var str_customer_type_object_name      = 'TXT_S03_CUSTOMER_TYPE_' + m_row_number;
                        var str_company_name_object_name       = 'TXT_S03_COMPANY_NAME_' + m_row_number;
                        var str_company_name_div_tag_id        = 'DIV_TXT_S03_COMPANY_NAME_' + m_row_number;
                        var str_reg_no_object_name             = 'TXT_S03_REG_NO_' + m_row_number;
                        var str_reg_no_div_tag_id              = 'DIV_TXT_S03_REG_NO_' + m_row_number;
					
                        var str_nic_object_name                = 'TXT_S03_NIC_' + m_row_number;
                        var str_nic_div_tag_id                 = 'DIV_TXT_S03_NIC_' + m_row_number;
                        var str_first_name_object_name         = 'TXT_S03_INITIALS_DETAILS_' + m_row_number;
                        var str_first_name_div_tag_id          = 'DIV_TXT_S03_INITIALS_DETAILS_' + m_row_number;
                        var str_intials_object_name            = 'TXT_S03_INITIALS_' + m_row_number;
                        var str_intials_div_tag_id             = 'DIV_TXT_S03_INITIALS_' + m_row_number;
                        var str_last_name_object_name          = 'TXT_S03_LAST_NAME_' + m_row_number;
                        var str_last_name_div_tag_id           = 'DIV_TXT_S03_LAST_NAME_' + m_row_number;
                        var str_title_object_name              = 'TXT_S03_TITLE_' + m_row_number;
                        var str_title_div_tag_id               = 'DIV_TXT_S03_TITLE_' + m_row_number;

					
					
				                if (document.forms[0].elements[str_customer_type_object_name].value == 'I') {
                           document.forms[0].elements[str_company_name_object_name].value = '';
                           document.forms[0].elements[str_company_name_object_name].style.visibility = 'hidden';
                           document.getElementById(str_company_name_div_tag_id).style.visibility = 'hidden';
                           document.forms[0].elements[str_reg_no_object_name].value = '';
				                   document.forms[0].elements[str_reg_no_object_name].style.visibility = 'hidden';
                           document.getElementById(str_reg_no_div_tag_id).style.visibility = 'hidden';
				 
				                   document.forms[0].elements[str_nic_object_name].style.visibility = 'visible';
                           document.getElementById(str_nic_div_tag_id).style.visibility = 'visible';
				                   document.forms[0].elements[str_first_name_object_name].style.visibility = 'visible';
                           document.getElementById(str_first_name_div_tag_id).style.visibility = 'visible';
				                   document.forms[0].elements[str_intials_object_name].style.visibility = 'visible';
                           document.getElementById(str_intials_div_tag_id).style.visibility = 'visible';
				                   document.forms[0].elements[str_last_name_object_name].style.visibility = 'visible';
                           document.getElementById(str_last_name_div_tag_id).style.visibility = 'visible';
				                   document.forms[0].elements[str_title_object_name].style.visibility = 'visible';
                           document.getElementById(str_title_div_tag_id).style.visibility = 'visible';

				                }
                        else if (document.forms[0].elements[str_customer_type_object_name].value == 'C') {
                           document.forms[0].elements[str_company_name_object_name].style.visibility = 'visible';
                           document.getElementById(str_company_name_div_tag_id).style.visibility = 'visible';
                           document.forms[0].elements[str_reg_no_object_name].style.visibility = 'visible';
                           document.getElementById(str_reg_no_div_tag_id).style.visibility = 'visible';
				 
				                   document.forms[0].elements[str_nic_object_name].value = '';
				                   document.forms[0].elements[str_nic_object_name].style.visibility = 'hidden';
                           document.getElementById(str_nic_div_tag_id).style.visibility = 'hidden';
				                   document.forms[0].elements[str_first_name_object_name].value = '';
				                   document.forms[0].elements[str_first_name_object_name].style.visibility = 'hidden';
                           document.getElementById(str_first_name_div_tag_id).style.visibility = 'hidden';
				                   document.forms[0].elements[str_intials_object_name].value = '';
				                   document.forms[0].elements[str_intials_object_name].style.visibility = 'hidden';
                           document.getElementById(str_intials_div_tag_id).style.visibility = 'hidden';
				                   document.forms[0].elements[str_last_name_object_name].value = '';
				                   document.forms[0].elements[str_last_name_object_name].style.visibility = 'hidden';
                           document.getElementById(str_last_name_div_tag_id).style.visibility = 'hidden';
				                   document.forms[0].elements[str_title_object_name].value = '';
				                   document.forms[0].elements[str_title_object_name].style.visibility = 'hidden';
                           document.getElementById(str_title_div_tag_id).style.visibility = 'hidden';
  			                }
                    }


			            function help_update_s10_city_assign_data(data) { 
			                var m_row_number = document.Form1.hid_help_row_number.value; 
			                document.Form1.elements["TXT_S10_CITY_" + m_row_number].value = data.colomn_2; 
			                document.Form1.elements["TXT_S10_CITY_NAME_" + m_row_number].value = data.colomn_3; 
			                setTimeout('document.Form1.elements[\'TXT_S10_TELEPHONE_1_' + m_row_number + '\'].select()', 1); 
			                document.Form1.hid_validation_type.value = "3"; 
			                var m_city_code = document.Form1.elements["TXT_S10_CITY_" + m_row_number].value; 
			                m_url = servlet_client_url+":"+client_t3_port+"/"+client_name+"FA_MK_sql_validations?chksql=get_global_address_codes&city_code="+m_city_code; 
			                load_interface(m_url, 'XML'); 
			            } 

			            function help_update_s11_city_assign_data(data) { 
			                var m_row_number = document.Form1.hid_help_row_number.value; 
			                document.Form1.elements["TXT_S11_CITY_" + m_row_number].value = data.colomn_2; 
			                document.Form1.elements["TXT_S11_CITY_NAME_" + m_row_number].value = data.colomn_3; 
			                setTimeout('document.Form1.elements[\'TXT_S11_TELEPHONE_1_' + m_row_number + '\'].select()', 1); 
			                document.Form1.hid_validation_type.value = "4"; 
			                var m_city_code = document.Form1.elements["TXT_S11_CITY_" + m_row_number].value; 
			                m_url = servlet_client_url+":"+client_t3_port+"/"+client_name+"FA_MK_sql_validations?chksql=get_global_address_codes&city_code="+m_city_code; 
			                load_interface(m_url, 'XML'); 
			            } 

			            function openPopupProposedSecurityDetails(m_type) { 
			                var m_screen_name = document.Form1.SCREEN_NAME.value; 
			                var m_account_number = document.Form1.TXT_CLIENT_ACCOUNT_NUMBER.value; 
			                var m_temp_account_number = document.Form1.hid_temp_account_number.value; 
			                var m_servlet_type = ""; 
			
			                if ((m_screen_name == "EDIT") && (m_account_number.length == 0)) { 
			                    alert('Please select an account.'); 
			                    document.Form1.TXT_CLIENT_CODE.focus(); 
			                    return false; 
			                } 
			
			                if ((m_screen_name == "NEW")) { 
			                    m_account_number = m_temp_account_number; 
			                } 
			
			/* Set The Name of The Popup Servlet */
			                if (m_type == "IA") { 
			                    m_servlet_type = "Immovable_Assets"; 
			                } 
			                else if (m_type == "MA") { 
			                    m_servlet_type = "Movable_Assets"; 
			                } 
			                else if (m_type == "SS") { 
			                    m_servlet_type = "Shares"; 
			                } 
			                else if (m_type == "ST") { 
			                    m_servlet_type = "Stocks"; 
			                } 
			                else if (m_type == "DP") { 
			                    m_servlet_type = "Deposits"; 
			                } 
			                else if (m_type == "BG") { 
			                    m_servlet_type = "Bank_Guarantees"; 
			                } 
			                else if (m_type == "LC") { 
			                    m_servlet_type = "Letter_of_Credit"; 
			                } 
			                else if (m_type == "SC") { 
			                    m_servlet_type = "Security_Cheques"; 
			                } 
			                window.open(servlet_client_url+":"+client_t3_port+"/"+client_name+"FA_CR_Client_Account_Creation_"+m_servlet_type+"_display?screen_name="+m_screen_name+"&account_number="+m_account_number, 'm_popup', 'left=0,top=0,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1,width='+screen.availWidth+',height='+screen.availWidth); 
			            } 
			function help_update_s08_city(link_id, row_number) {
               if(!isOpen) {
                   isOpen = true;
                   document.Form1.hid_help_type.value = "111";
                   m_sql = "m_help_CITY_CODE_sql";
                   document.Form1.hid_help_row_number.value = row_number;
                   var m_textbox = document.Form1.elements["TXT_S08_CITY_NAME_" + row_number].value;
                   m_criteria = m_textbox+"@";
                   HelpBox('1','10','0',link_id);
               }
           }
           function help_update_s06_city(link_id, row_number) {
               if(!isOpen) {
                   isOpen = true;
                   document.Form1.hid_help_type.value = "88";
                   m_sql = "m_help_CITY_CODE_sql";
                   document.Form1.hid_help_row_number.value = row_number;
                   var m_textbox = document.Form1.elements["TXT_S06_CITY_NAME_" + row_number].value;
                   m_criteria = m_textbox+"@";
                   HelpBox('1','10','0',link_id);
               }
           }
function show_log_drill() {
if ($("#DIV_LOG").dialog("isOpen")) {
$("#DIV_LOG").dialog("close");
}
$("#DIV_LOG").dialog("option", "title", "Details");
$("#DIV_LOG").dialog("open");
if(document.Form1.TXT_CLIENT_ACCOUNT_NUMBER.value != ""){
obj = document.Form1.TXT_CLIENT_ACCOUNT_NUMBER.value;
$("#IFRAME_LOG").attr("src", "http://www.lolc-comfacv1.lk:/comfac/servlet/COMFACV_FA_RE_PRO_drill_downs_2?chksql=SHOW_CLIENT_ACCOUNT_DETAIL_DRILL&inquiry_no=" + obj );
}
else{
obj = document.Form1.TXT_CLIENT_CODE.value;
$("#IFRAME_LOG").attr("src", "http://www.lolc-comfacv1.lk:/comfac/servlet/COMFACV_FA_RE_PRO_drill_downs_2?chksql=SHOW_CLIENT_DETAIL_DRILL&inquiry_no=" + obj );
}
return false;
}
function initLogDialog(){
$("#DIV_LOG").dialog({
bgiframe: true,
autoOpen: false,
modal: false,
height: 600,
width: getW,
close: function() {
}
});
}
function getW() {
var w = 0;
if (window.document.innerWidth > w) {
w = window.document.innerWidth;
}
if (window.document.documentElement.clientWidth > w) {
w = window.document.documentElement.clientWidth;
}
return (w - 100);
}
           function checkBankAccount(m_row_number) {
           	var branch = document.Form1.elements["TXT_S07_BRANCH_" + m_row_number].value;
           	var acc = document.Form1.elements["TXT_S07_ACCOUNT_NO_" + m_row_number].value;
           	if (branch !="" && acc !="" ) {
			        document.Form1.hid_validation_type.value=555;
			        m_url="http://www.lolc-comfacv1.lk:/comfac/servlet/COMFACV_FA_CR_sql_validations?chksql=m_check_bank_account&branch_no="+branch+"&acc_no="+acc+"&row_number="+m_row_number;
		            load_interface(m_url,'XML');
          	 }
           }
function view_upload(){
	if(document.Form1.SCREEN_NAME.value == "EDIT"){
	    m_url="http://www.lolc-comfacv1.lk:/comfac/servlet/COMFACV_FA_CR_Credit_Client_Business_Unit_Creation_view_upload?chksql=main_page&client_code="+ document.Form1.TXT_CLIENT_ACCOUNT_NUMBER.value;
       popupwin=window.open(m_url,'displayWindow2','left=0,top=0,width=1000,height=800,toolbar=0,location=0,center:yes,direction=0,menuBar=1,status=0,scrollbars=1,resizable=1');
   }
   else{
      alert('Enter Client code in Edit mode to view uploads');
   }
}

 function sumValues(m_type, m_screen_name, m_account_number, m_sum_collateral_amount, m_sum_forced_sale_value, m_sum_amount_considered, m_sum_amount_considered_percentage) {
               if (m_screen_name == "NEW") {
                   document.Form1.hid_temp_account_number.value = m_account_number;
               }
               if (m_type == "SS") {
                   document.Form1.TXT_S05_COLLATERAL_AMOUNT_3.value = m_sum_collateral_amount;
                   document.Form1.TXT_S05_FORCED_SALE_VALUE_3.value = m_sum_forced_sale_value;
                   document.Form1.TXT_S05_AMOUNT_CONSIDERED_3.value = m_sum_amount_considered;
                   document.Form1.TXT_S05_AMOUNT_CONSIDERED_PERCENT_3.value = m_sum_amount_considered_percentage;
               }
               else if (m_type == "IA") {
                   document.Form1.TXT_S05_COLLATERAL_AMOUNT_1.value = m_sum_collateral_amount;
                   document.Form1.TXT_S05_FORCED_SALE_VALUE_1.value = m_sum_forced_sale_value;
                   document.Form1.TXT_S05_AMOUNT_CONSIDERED_1.value = m_sum_amount_considered;
                   document.Form1.TXT_S05_AMOUNT_CONSIDERED_PERCENT_1.value = m_sum_amount_considered_percentage;
               }
               else if (m_type == "MA") {
                   document.Form1.TXT_S05_COLLATERAL_AMOUNT_2.value = m_sum_collateral_amount;
                   document.Form1.TXT_S05_FORCED_SALE_VALUE_2.value = m_sum_forced_sale_value;
                   document.Form1.TXT_S05_AMOUNT_CONSIDERED_2.value = m_sum_amount_considered;
                   document.Form1.TXT_S05_AMOUNT_CONSIDERED_PERCENT_2.value = m_sum_amount_considered_percentage;
               }
               else if (m_type == "ST") {
                   document.Form1.TXT_S05_COLLATERAL_AMOUNT_4.value = m_sum_collateral_amount;
                   document.Form1.TXT_S05_FORCED_SALE_VALUE_4.value = m_sum_forced_sale_value;
                   document.Form1.TXT_S05_AMOUNT_CONSIDERED_4.value = m_sum_amount_considered;
                   document.Form1.TXT_S05_AMOUNT_CONSIDERED_PERCENT_4.value = m_sum_amount_considered_percentage;
               }
               else if (m_type == "DP") {
                   document.Form1.TXT_S05_COLLATERAL_AMOUNT_5.value = m_sum_collateral_amount;
                   document.Form1.TXT_S05_FORCED_SALE_VALUE_5.value = m_sum_forced_sale_value;
                   document.Form1.TXT_S05_AMOUNT_CONSIDERED_5.value = m_sum_amount_considered;
                   document.Form1.TXT_S05_AMOUNT_CONSIDERED_PERCENT_5.value = m_sum_amount_considered_percentage;
               }
               else if (m_type == "BG") {
                   document.Form1.TXT_S05_COLLATERAL_AMOUNT_6.value = m_sum_collateral_amount;
                   document.Form1.TXT_S05_FORCED_SALE_VALUE_6.value = m_sum_forced_sale_value;
                   document.Form1.TXT_S05_AMOUNT_CONSIDERED_6.value = m_sum_amount_considered;
                   document.Form1.TXT_S05_AMOUNT_CONSIDERED_PERCENT_6.value = m_sum_amount_considered_percentage;
               }
               else if (m_type == "LC") {
                   document.Form1.TXT_S05_COLLATERAL_AMOUNT_7.value = m_sum_collateral_amount;
                   document.Form1.TXT_S05_FORCED_SALE_VALUE_7.value = m_sum_forced_sale_value;
                   document.Form1.TXT_S05_AMOUNT_CONSIDERED_7.value = m_sum_amount_considered;
                   document.Form1.TXT_S05_AMOUNT_CONSIDERED_PERCENT_7.value = m_sum_amount_considered_percentage;
               }
               else if (m_type == "SC") {
                   document.Form1.TXT_S05_COLLATERAL_AMOUNT_8.value = m_sum_collateral_amount;
                   document.Form1.TXT_S05_FORCED_SALE_VALUE_8.value = m_sum_forced_sale_value;
                   document.Form1.TXT_S05_AMOUNT_CONSIDERED_8.value = m_sum_amount_considered;
                   document.Form1.TXT_S05_AMOUNT_CONSIDERED_PERCENT_8.value = m_sum_amount_considered_percentage;
               }
           totalValues();
           }
           function totalValues() {
                   var total_1	= "0.00"; 
                   total_1 = parseFloat(unformat_noobject(total_1))+parseFloat(unformat_noobject(document.Form1.TXT_S05_COLLATERAL_AMOUNT_1.value))+parseFloat(unformat_noobject(document.Form1.TXT_S05_COLLATERAL_AMOUNT_2.value))+parseFloat(unformat_noobject(document.Form1.TXT_S05_COLLATERAL_AMOUNT_3.value))+parseFloat(unformat_noobject(document.Form1.TXT_S05_COLLATERAL_AMOUNT_4.value));
                   total_1 = parseFloat(unformat_noobject(total_1))+parseFloat(unformat_noobject(document.Form1.TXT_S05_COLLATERAL_AMOUNT_5.value))+parseFloat(unformat_noobject(document.Form1.TXT_S05_COLLATERAL_AMOUNT_6.value))+parseFloat(unformat_noobject(document.Form1.TXT_S05_COLLATERAL_AMOUNT_7.value))+parseFloat(unformat_noobject(document.Form1.TXT_S05_COLLATERAL_AMOUNT_8.value));
                   document.Form1.TXT_S05_COLLATERAL_TOTAL_9.value = parseFloat(unformat_noobject(total_1));
                   validateNumber(document.Form1.TXT_S05_COLLATERAL_TOTAL_9, 0, 9999999999999999.99, 2, 'number');
                   var total_2	= "0.00"; 
                   total_2 = parseFloat(unformat_noobject(total_2))+parseFloat(unformat_noobject(document.Form1.TXT_S05_FORCED_SALE_VALUE_1.value))+parseFloat(unformat_noobject(document.Form1.TXT_S05_FORCED_SALE_VALUE_2.value))+parseFloat(unformat_noobject(document.Form1.TXT_S05_FORCED_SALE_VALUE_3.value))+parseFloat(unformat_noobject(document.Form1.TXT_S05_FORCED_SALE_VALUE_4.value));
                   total_2 = parseFloat(unformat_noobject(total_2))+parseFloat(unformat_noobject(document.Form1.TXT_S05_FORCED_SALE_VALUE_5.value))+parseFloat(unformat_noobject(document.Form1.TXT_S05_FORCED_SALE_VALUE_6.value))+parseFloat(unformat_noobject(document.Form1.TXT_S05_FORCED_SALE_VALUE_7.value))+parseFloat(unformat_noobject(document.Form1.TXT_S05_FORCED_SALE_VALUE_8.value));
                   document.Form1.TXT_S05_FORCED_SALE_VALUE_TOTAL_9.value = parseFloat(unformat_noobject(total_2));
                   validateNumber(document.Form1.TXT_S05_FORCED_SALE_VALUE_TOTAL_9, 0, 9999999999999999.99, 2, 'number');
                   var total_3	= "0.00"; 
                   total_3 = parseFloat(unformat_noobject(total_3))+parseFloat(unformat_noobject(document.Form1.TXT_S05_AMOUNT_CONSIDERED_1.value))+parseFloat(unformat_noobject(document.Form1.TXT_S05_AMOUNT_CONSIDERED_2.value))+parseFloat(unformat_noobject(document.Form1.TXT_S05_AMOUNT_CONSIDERED_3.value))+parseFloat(unformat_noobject(document.Form1.TXT_S05_AMOUNT_CONSIDERED_4.value));
                   total_3 = parseFloat(unformat_noobject(total_3))+parseFloat(unformat_noobject(document.Form1.TXT_S05_AMOUNT_CONSIDERED_5.value))+parseFloat(unformat_noobject(document.Form1.TXT_S05_AMOUNT_CONSIDERED_6.value))+parseFloat(unformat_noobject(document.Form1.TXT_S05_AMOUNT_CONSIDERED_7.value))+parseFloat(unformat_noobject(document.Form1.TXT_S05_AMOUNT_CONSIDERED_8.value));
                   document.Form1.TXT_S05_AMOUNT_CONSIDERED_TOTAL_9.value = parseFloat(unformat_noobject(total_3));
                   validateNumber(document.Form1.TXT_S05_AMOUNT_CONSIDERED_TOTAL_9, 0, 9999999999999999.99, 2, 'number');
                   var total_4	= "0.00"; 
                   total_4 = parseFloat(unformat_noobject(total_4))+parseFloat(unformat_noobject(document.Form1.TXT_S05_AMOUNT_CONSIDERED_PERCENT_1.value))+parseFloat(unformat_noobject(document.Form1.TXT_S05_AMOUNT_CONSIDERED_PERCENT_2.value))+parseFloat(unformat_noobject(document.Form1.TXT_S05_AMOUNT_CONSIDERED_PERCENT_3.value))+parseFloat(unformat_noobject(document.Form1.TXT_S05_AMOUNT_CONSIDERED_PERCENT_4.value));
                   total_4 = parseFloat(unformat_noobject(total_4))+parseFloat(unformat_noobject(document.Form1.TXT_S05_AMOUNT_CONSIDERED_PERCENT_5.value))+parseFloat(unformat_noobject(document.Form1.TXT_S05_AMOUNT_CONSIDERED_PERCENT_6.value))+parseFloat(unformat_noobject(document.Form1.TXT_S05_AMOUNT_CONSIDERED_PERCENT_7.value))+parseFloat(unformat_noobject(document.Form1.TXT_S05_AMOUNT_CONSIDERED_PERCENT_8.value));
                   document.Form1.TXT_S05_AMOUNT_CONSIDERED_PERCENT_TOTAL_9.value = parseFloat(unformat_noobject(total_4));
                   validateNumber(document.Form1.TXT_S05_AMOUNT_CONSIDERED_PERCENT_TOTAL_9, 0, 9999999999999999.99, 2, 'number');
           }
// ----------added by ishani-----------------------------------------------------------
			 /*        function help_update_s07_branch_name(link_id, row_number) {
                         if(!isOpen) {
                              isOpen = true;
                              document.Form1.hid_help_type.value = "06";
            //out.println("                   m_sql = \"m_help_BRANCH_CODE_sql\";");
			                   m_sql = "m_help_BRANCH_CODE_sql_2";
                              document.Form1.hid_help_row_number.value = row_number;
                             var m_textbox = document.Form1.elements["TXT_S07_BRANCH_NAME_" + row_number].value;
                              var m_textbox_2 = document.Form1.elements["TXT_S07_BANK_" + row_number].value;
                              m_criteria = m_textbox+\"@"+m_textbox_2+"@";
                              HelpBox('1','10','0',link_id);
                          }
                       }*/
//------------------------------------------------------------------------------


         
			          /* function load_calendar_2(calendar_row_number) {
			               document.Form1.hid_calendar_row_number.value = calendar_row_number;
			           }
			
			
			           function load_calendar_1(calendar_type) {
			               document.Form1.hid_calendar_type.value = calendar_type;
			               load_calendar(calendar_type); 
			           }
			
			
			           function load_calendar(calendar_type) {
			               popupwin = window.open(servlet_client_url+\":\"+client_t3_port+\"/\"+client_name+\"CO_Calendar_Window\", \"oBj\",\"left=450,top=200,width=320,height=230\ 
			           }
			
			
			           function load_c_date(m_value) {
			               m_value_date = m_value.substr(0, m_value.indexOf('-'));
			               if (m_value_date.length < 2) {
			                   m_value_date = 0 + m_value_date;
			               }
			               m_value = m_value.substr(m_value.indexOf('-') + 1, m_value.length);
			               m_value_month = m_value.substr(0, m_value.indexOf('-'));
			               if (m_value_month.length < 2) {
			                   m_value_month = 0 + m_value_month;
			               }
			               m_value_year = m_value.substr(m_value.indexOf('-') + 1, m_value.length);
			               if (document.Form1.hid_calendar_type.value == \"s03_c_doi\") {
			                   document.Form1.TXT_S03_C_DATE_OF_INCORPORATION_DD.value = m_value_date;
			                   document.Form1.TXT_S03_C_DATE_OF_INCORPORATION_MM.value = m_value_month;
			                   document.Form1.TXT_S03_C_DATE_OF_INCORPORATION_YY.value = m_value_year;
			               }
			               if (document.Form1.hid_calendar_type.value == \"s03_c_dob\") {
			                   document.Form1.TXT_S03_I_DATE_OF_BIRTH_DD.value = m_value_date;
			                   document.Form1.TXT_S03_I_DATE_OF_BIRTH_MM.value = m_value_month;
			                   document.Form1.TXT_S03_I_DATE_OF_BIRTH_YY.value = m_value_year;
			               }
			               if (document.Form1.hid_calendar_type.value == \"s07_sd\") {
			               }
			           }

			function client_detail_dispaly() {
					m_url=\""+m_class_url+"/"+m_fschema_name+"FA_CR_PRO_sql_validations_normal?chksql=CLIENT_NAME_SEARCH_HELP&full_name=\"+document.Form1.TXT_CLIENT_FULL_NAME.value;
					popupwin = window.showModalDialog(m_url,\"dialogWidth:600em; dialogHeight:100em; center:yes; status:no\  
			}
			
			
			
			
			           function set_file_val(filestyle_id) {
			               filestyle_element = document.getElementById(filestyle_id);
			               fake_filestyle_element = document.getElementById(\"fake_\" + filestyle_id);
			               fake_filestyle_element.value = filestyle_element.value;
			           }

			function load_roll_value(m_value) {
			document.getElementById(\"SCREEN_TITLE\").innerHTML = \"" + m_screen_title + " - \" + m_value;
			 }
			
			
			function load_roll_out_value() {
			document.getElementById(\"SCREEN_TITLE\").innerHTML = \"" + m_screen_title + " - \" + document.Form1.hid_screen_status.value;
			}*/

