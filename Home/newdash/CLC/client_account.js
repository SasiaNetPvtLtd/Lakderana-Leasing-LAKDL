


function assign_top_debtor_contact(m_row_number) {
	

	document.Form1.elements["TXT_S11_TELEPHONE_1_" + m_row_number].value = data_vec[0];
	document.Form1.elements["TXT_S11_TELEPHONE_2_" + m_row_number].value = data_vec[1];
	document.Form1.elements["TXT_S11_MOBILE_" + m_row_number].value = data_vec[2];
	document.Form1.elements["TXT_S11_FAX_" + m_row_number].value = data_vec[3];
	document.Form1.elements["TXT_S11_EMAIL_" + m_row_number].value = data_vec[4];
	document.Form1.elements["TXT_S11_TITLE_" + m_row_number].value = data_vec[5];
	document.Form1.elements["TXT_S11_LAST_NAME_" + m_row_number].value = data_vec[6];
	document.Form1.elements["TXT_S11_INITIALS_" + m_row_number].value = data_vec[7];
	document.Form1.elements["TXT_S11_INITIALS_DETAILS_" + m_row_number].value = data_vec[8];
	
	
}



