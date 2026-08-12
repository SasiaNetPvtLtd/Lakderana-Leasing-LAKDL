var servlet_client_url = "http://www.lolc-comfacv1.lk";
var html_client_url    = "http://www.lolc-comfacv1.lk/";
var client_t3_port     = "/comfac/servlet";
var client_name        = "COMFACV_";
var header_name        = "Asset System";
var company_prfix        = "CLC";



function load_lock() {
    setTimeout('form_disable()', 1000);
}


function form_disable() {
    //document.oncontextmenu = new Function("return false"); // Should enabled for live system
}


/* Reload The Current Screen */
function reloadScreen() {
    window.location.reload();
}


function checklength(inputStr, size, message) {
    var m_length=0;
    var m_difference=0;	
    var m_fulllength="";
    chk_stat=isEmptymessage(inputStr,message);
    if (!chk_stat){
        m_length=inputStr.value.length;
        if (m_length > size){
            alert(message+" should not be greater than "+ size.toString()+ " characters");
            inputStr.focus();
        }
    }
    return true
}


function format_number(object1,size){
    var comsize=20;
    m_format_1=object1.value;
    if (m_format_1 == null || m_format_1 == ""){
        alert("Please enter a number");
        object1.focus();
        return false;
    }
    m_dot_count=0;
    var chk_brk=false;
    var m_integer="";
    var m_decimal="";
    var m_format="";
    m_minus=false;
    for (var i = 0; i < m_format_1.length; i++){
        var oneChar = m_format_1.charAt(i)
        if (oneChar==null || oneChar == "" || oneChar == " "){
            var k=1;
        }
        else{
            m_format=m_format+oneChar;
        }
    }
    for (var i = 0; i < m_format.length; i++){
        var oneChar = m_format.charAt(i)
        if (oneChar== "."){
            m_dot_count=m_dot_count+1;
        }
        if (oneChar== "-"){
            m_minus=true;
        }
        if (isNaN(oneChar) && oneChar != "," && oneChar != "." && oneChar != "-"){
            alert("You have typed an incorrect charactor as a number.");
            chk_brk=true;
            i=m_format.length;
            break;
        }
        if (!isNaN(oneChar) && m_dot_count==0 ){
            m_integer=m_integer+oneChar;
        }
        if (m_dot_count>0){
            m_decimal=m_decimal+oneChar;
        }
    }
    if (chk_brk){
        object1.value="";
        object1.focus();
        return false;
    }
    if (m_dot_count>1){
        alert("You have typed more than one decimal separator");
        object1.focus();
        return false;
    }
    m_format=m_integer; 
    
    m_length=m_integer.length;
    if (m_length > size){
        alert("The length of the number cannot be more than "+size.toString());
        object1.focus();
        return false;
    }
    var m_formatted="";
    var m_new_str="";
    m_end=m_format.length;
    m_stat_pos=m_end-4;
    var m_last_pos=m_end;
    while (m_stat_pos>=0){
        m_chk_str=m_format.substr(m_stat_pos,1);
        if (m_chk_str!=null){
            m_add_str=","+m_format.substr(m_stat_pos+1,3);
            m_new_str=m_add_str+m_new_str;
        }
        m_last_pos=m_stat_pos;
        m_stat_pos=m_stat_pos-3;
    }
    
    if (m_decimal == ""){
        m_decimal=".00";
    }
    else if (m_decimal == "."){
        m_decimal=".00";
    }
    else if (m_decimal == ".0"){
        m_decimal=".00";
    }
    else if (m_decimal == ". 0"){
        m_decimal=".00";
    }
    
    m_new_str=m_format.substr(0,m_last_pos+1)+m_new_str+m_decimal;
    if (m_minus){
        m_new_str="-"+m_new_str;
    }
    
    var m_left_str="";
    rem_len=16-m_new_str.length;
    while (rem_len>0){
        m_left_str=m_left_str+" ";
        rem_len=rem_len-1;
    }
    object1.value=m_new_str;
    return true;
}


//Additional function
function format_number2(object1,size){
    var comsize=20;
    m_format_1=object1.value;
    m_dot_count=0;
    var chk_brk=false;
    var m_integer="";
    var m_decimal="";
    var m_format="";
    m_minus=false;
    for (var i = 0; i < m_format_1.length; i++){
        var oneChar = m_format_1.charAt(i)
        if (oneChar==null || oneChar == "" || oneChar == " "){
            var k=1;
        }
        else{
            m_format=m_format+oneChar;
        }
    }
    for (var i = 0; i < m_format.length; i++){
        var oneChar = m_format.charAt(i)
        if (oneChar== ".") {
            m_dot_count=m_dot_count+1;
        }
        if (oneChar== "-") {
            m_minus=true;
        }
        if (isNaN(oneChar) && oneChar != "," && oneChar != "." && oneChar != "-") {
            alert("You have typed an incorrect charactor as a number.");
            chk_brk=true;
            i=m_format.length;
            break;
        }
        if (!isNaN(oneChar) && m_dot_count==0 ) {
            m_integer=m_integer+oneChar;
        }
        if (m_dot_count>0) {
            m_decimal=m_decimal+oneChar;
        }
    }
    if (chk_brk) {
        object1.value="";
        object1.focus();
        return false;
    }
    if (m_dot_count>1){
        alert("You have typed more than one decimal separator");
        object1.focus();
        return false;
    }
    m_format=m_integer; 
    
    m_length=m_integer.length;
    if (m_length > size){
        alert("The length of the number cannot be more than "+size.toString());
        object1.focus();
        return false;
    }
    var m_formatted="";
    var m_new_str="";
    m_end=m_format.length;
    m_stat_pos=m_end-4;
    var m_last_pos=m_end;
    while (m_stat_pos>=0)	{
        m_chk_str=m_format.substr(m_stat_pos,1);
        if (m_chk_str!=null) {
            m_add_str=","+m_format.substr(m_stat_pos+1,3);
            m_new_str=m_add_str+m_new_str;
        }
        m_last_pos=m_stat_pos;
        m_stat_pos=m_stat_pos-3;
    }
    
    if (m_decimal == "") {
        m_decimal=".0000";
    }
    else if (m_decimal == ".") {
        m_decimal=".0000";
    }
    else if (m_decimal == ".0") {
        m_decimal=".0000";
    }
    else if (m_decimal == ". 0") {
        m_decimal=".0000";
    }
    
    m_new_str=m_format.substr(0,m_last_pos+1)+m_new_str+m_decimal;
    if (m_minus) {
        m_new_str="-"+m_new_str;
    }
    
    var m_left_str="";
    rem_len=16-m_new_str.length;
    while (rem_len>0) {
        m_left_str=m_left_str+" ";
        rem_len=rem_len-1;
    }
    object1.value=m_new_str;
    return true;
}


//To check the number but not to display message
function isnumberok(object1,size) {
    m_format_1=object1.value;
    if (m_format_1 == null || m_format_1 == "") {
        return false;
    }
    m_dot_count=0;
    var m_integer="";
    var m_decimal="";
    var m_format="";
    m_minus=false;
    for (var i = 0; i < m_format_1.length; i++) {
        var oneChar = m_format_1.charAt(i)
        if (oneChar==null || oneChar == "" || oneChar == " "){
            var k=1;
        }
        else {
            m_format=m_format+oneChar;
        }
    }
    for (var i = 0; i < m_format.length; i++) {
        var oneChar = m_format.charAt(i)
        if (oneChar== ".") {
            m_dot_count=m_dot_count+1;
        }
        if (oneChar== "-") {
            m_minus=true;
        }
        
        if (isNaN(oneChar) && oneChar != "," && oneChar != "." && oneChar != "-") {
            return false;
        }
        if (!isNaN(oneChar) && m_dot_count==0 ) {
            m_integer=m_integer+oneChar;
        }
        if (m_dot_count>0) {
            m_decimal=m_decimal+oneChar;
        }
    }
    if (m_dot_count>1) {
        return false;
    }
    m_format=m_integer; 
    
    m_length=m_integer.length;
    if (m_length > size){
        return false;
    }
    return true;
    
}


//Function to validate NIC
function val_nic(object){
    m_objval = object.value;
    m_length = m_objval.toString().length;
    
    if(m_length<10){
        alert(" NIC length should be 10");
        object.focus();
        return false;
    }
    else{
        for(var i = 0; i<m_length; i++){
            m_char = m_objval.charAt(i);
            if(i==9){
                if(m_char != 'v' && m_char != 'V' && m_char != 'x' && m_char != 'X'){
                    alert("NIC Final Character should be X or V ");
                    object.focus();
                    return false;
                }
            }
            else if(i>=0 && i<=8){
                if(isNaN(m_char)){
                    i++;
                    alert("NIC contains an Invalid Character at position "+i+" ?");
                    object.focus();
                    return false;
                    //break;
                }
            }
            
        }
    }
    return false;
    
}


function format_noobject_nodecimal(value){//$$##SC
    m_format=value.toString();
    m_dot_count=0;
    var m_integer="";
    var m_decimal="";
    m_minus=false;
    for (var i = 0; i < m_format.length; i++)	{
        var oneChar = m_format.charAt(i)
        if (oneChar== ".") 	{
            m_dot_count=m_dot_count+1;
        }
        if (oneChar== "-"){
            m_minus=true;
        }
        if (isNaN(oneChar) && oneChar != "," && oneChar != "." && oneChar != "-") {
            alert("You have typed an incorrect charactor as a number.");
            return oneChar
        }
        if (!isNaN(oneChar) && m_dot_count==0 ){
            m_integer=m_integer+oneChar;
        }
        if (m_dot_count>0) {
            m_decimal=m_decimal+oneChar;
        }
    }//for
    
    if (m_dot_count>1) {
        alert("You have typed more than one decimal separator");
        return false
    }
    m_format=m_integer; 
    
    var m_formatted="";
    var m_new_str="";
    m_end=m_format.length;
    m_stat_pos=m_end-4;
    var m_last_pos=m_end;
    while (m_stat_pos>=0) {
        m_chk_str=m_format.substr(m_stat_pos,1);
        if  (m_chk_str!=null) {
            m_add_str=","+m_format.substr(m_stat_pos+1,3);
            m_new_str=m_add_str+m_new_str;
        }
        m_last_pos=m_stat_pos;
        m_stat_pos=m_stat_pos-3;
    }
    if (m_decimal == "") {
        m_decimal="";
    }
    else if (m_decimal == ".") {
        m_decimal="";
    }
    else if (m_decimal == ".0") {
        m_decimal="";
    }
    else if (m_decimal == ". 0") {
        m_decimal="";
    }
    else {
        m_decimal=m_decimal.substr(0,3);
    }
    
    /*if (m_decimal.length<3) {
    m_decimal=m_decimal+"0";
    } */
    m_new_str=m_format.substr(0,m_last_pos+1)+m_new_str+m_decimal;
    if (m_minus) {
        m_new_str="-"+m_new_str;
    }
    value=m_new_str;
    return value;
}


function format_noobject_nodecimal1(object){//$$##SC
    value=object.value;
    m_format=value.toString();
    m_dot_count=0;
    var m_integer="";
    var m_decimal="";
    m_minus=false;
    for (var i = 0; i < m_format.length; i++)	{
        var oneChar = m_format.charAt(i)
        if (oneChar== ".") {
            m_dot_count=m_dot_count+1;
        }
        if (oneChar== "-"){
            m_minus=true;
        }
        if (isNaN(oneChar) && oneChar != "," && oneChar != "." && oneChar != "-") {
            alert("You have typed an incorrect charactor as a number.");
            object.value="";
            object.focus();
            return oneChar
                
        }
        if (!isNaN(oneChar) && m_dot_count==0 ){
            m_integer=m_integer+oneChar;
        }
        if (m_dot_count>0) {
            m_decimal=m_decimal+oneChar;
        }
    }//for
    
    if (m_dot_count>1) {
        alert("You have typed more than one decimal separator");
        object.focus();
        return false
            
    }
    m_format=m_integer; 
    
    var m_formatted="";
    var m_new_str="";
    m_end=m_format.length;
    m_stat_pos=m_end-4;
    var m_last_pos=m_end;
    while (m_stat_pos>=0) {
        m_chk_str=m_format.substr(m_stat_pos,1);
        if  (m_chk_str!=null) {
            m_add_str=","+m_format.substr(m_stat_pos+1,3);
            m_new_str=m_add_str+m_new_str;
        }
        m_last_pos=m_stat_pos;
        m_stat_pos=m_stat_pos-3;
    }
    if (m_decimal == "") {
        m_decimal="";
    }
    else if (m_decimal == ".") {
        m_decimal="";
    }
    else if (m_decimal == ".0") {
        m_decimal="";
    }
    else if (m_decimal == ". 0") {
        m_decimal="";
    }
    else {
        m_decimal=m_decimal.substr(0,3);
    }
    m_new_str=m_format.substr(0,m_last_pos+1)+m_new_str+m_decimal;
    if (m_minus) {
        m_new_str="-"+m_new_str;
    }
    value=m_new_str;
    object.value=value;
}


/* Function Re-done By Samitha Kulatilaka On 2010-07-07 */
function format_noobject(value) {
    
    var new_value = format_noobject0(value, 2, null, null);
    
    return new_value;
    
    
    
    
    /* Commented By Samitha Kulatilaka 2010-07-07 */
    // m_format=value.toString();
    // m_dot_count=0;
    // var m_integer="";
    // var m_decimal="";
    // m_minus=false;
    // for (var i = 0; i < m_format.length; i++){
        // var oneChar = m_format.charAt(i)
        // if (oneChar== "."){
            // m_dot_count=m_dot_count+1;
        // }
        // if (oneChar== "-"){
            // m_minus=true;
        // }
        
        // if (isNaN(oneChar) && oneChar != "," && oneChar != "." && oneChar != "-"){
            // alert("You have typed an incorrect charactor as a number");
            // return oneChar
        // }
        // if (!isNaN(oneChar) && m_dot_count==0 ){
            // m_integer=m_integer+oneChar;
        // }
        // if (m_dot_count>0){
            // m_decimal=m_decimal+oneChar;
        // }
    // }
    // if (m_dot_count>1){
        // alert("You have typed more than one decimal separator");
        // return false
    // }
    // m_format=m_integer; 
    
    // var m_formatted="";
    // var m_new_str="";
    // m_end=m_format.length;
    // m_stat_pos=m_end-4;
    // var m_last_pos=m_end;
    // while (m_stat_pos>=0){
        // m_chk_str=m_format.substr(m_stat_pos,1);
        // if  (m_chk_str!=null){
            // m_add_str=","+m_format.substr(m_stat_pos+1,3);
            // m_new_str=m_add_str+m_new_str;
        // }
        // m_last_pos=m_stat_pos;
        // m_stat_pos=m_stat_pos-3;
    // }
    // if (m_decimal == ""){
        // m_decimal=".00";
    // }
    // else if (m_decimal == "."){
        // m_decimal=".00";
    // }
    // else if (m_decimal == ".0"){
        // m_decimal=".00";
    // }
    // else if (m_decimal == ". 0"){
        // m_decimal=".00";
    // }
    // else{
        // m_decimal=m_decimal.substr(0,3);
    // }
    // if (m_decimal.length<3){
        // m_decimal=m_decimal+"0";
    // }
    
    // m_new_str=m_format.substr(0,m_last_pos+1)+m_new_str+m_decimal;
    // if (m_minus){
        // m_new_str="-"+m_new_str;
    // }
    // value=m_new_str;
    // return value;
}





/* Function Re-done By Samitha Kulatilaka On 2010-07-02 */
function format_noobject0(value, passed_decimalsize, m_alert, m_brackets) {
    
    /* Trim The data */
    // var new_value = value.replace(/^\s+|\s+$/g, '');
    new_value = value;
    new_value = unformat_noobject(new_value);
    // alert('value : ' + new_value + '     length : ' + new_value.length);
    
    /* Check Whether The Value Is Actually A Number */
    if (isNaN(new_value)) {
        if (m_alert != 'N') {
            alert("You have typed an incorrect charactor as a number.");
            return value;
        }
    }
    
    /* Check Whether The Number Is Negative */
    var is_number_negative = false;
    if (new_value < 0) {
        is_number_negative = true;
        new_value = new_value.slice(1);
    }
    
    /* Create A Number Object And Round It As Necessary */
    var number_object = new Number(new_value);
    number_object = number_object.toFixed(passed_decimalsize);
    
    
    /* Format The Number */
    new_value = number_object.toString();
    
    var index_of_dot = new_value.indexOf('.');
    /* If Clauses Added By Samitha Kulatilaka On 2010-08-10 */
    var integer_part = '';
    var fraction_part = '';
    if (index_of_dot != -1) {
        integer_part = new_value.substring(0, index_of_dot);
        fraction_part = new_value.substring(index_of_dot + 1);
    }
    else {
        integer_part = new_value;
    }
    
    new_value = '';
    var j = 0;
    if (integer_part.length > 3) {
        for (var i = integer_part.length - 1; i >= 0; i--) {
            j = j + 1;
            new_value = integer_part.charAt(i) + new_value;
            if (j == 3) {
                /* Only Prefix A Comma If There Are More Numbers To The Left */
                if (i > 0) {
                    new_value = ',' + new_value;
                }
                j = 0;
            }
        }
    }
    /* Commas Are Not Necessary For This If Clause */
    else {
        new_value = integer_part;
    }
    
    /* If Clause Added By Samitha Kulatilaka On 2010-08-10 */
    if (index_of_dot != -1) {
        new_value = new_value.concat('.', fraction_part);
    }
    
    if (is_number_negative == true) {
        new_value = '-' + new_value;
    }
    
    
    /* Put Brackets For Negative Numbers */
    if (m_brackets == 'BRACKETS') {
        // alert(new_value);
        if (new_value.charAt(0) == '-') {
            new_value = new_value.slice(1);
            new_value = '(' + new_value + ')';
        }
    }
    
    return new_value;
    
    
    
    
    /* Commented By Samitha Kulatilaka 2010-07-02 */
    // var decimalsize = 4;
    // if (!isNaN(passed_decimalsize)) {
        // decimalsize = passed_decimalsize;
    // }
    
    // m_format = value.toString();
    // m_dot_count = 0;
    // var m_integer = "";
    // var m_decimal = "";
    // m_minus = false;
    
    // for (var i = 0; i < m_format.length; i++) {
        // var oneChar = m_format.charAt(i);
        // if (oneChar ==  ".") {
            // m_dot_count = m_dot_count + 1;
        // }
        // if (oneChar == "-") {
            // m_minus = true;
        // }
        // if ((isNaN(oneChar)) && (oneChar != ",") && (oneChar != ".") && (oneChar != "-")) {
            // /* If Conditon Added By Samitha Kulatilaka On 2010-06-10 */
            // if (m_alert != 'N') {
                // alert("You have typed an incorrect charactor as a number.");
                // return oneChar;
            // }
        // }
        // if (!isNaN(oneChar) && m_dot_count == 0) {
            // m_integer = m_integer + oneChar;
        // }
        // if (m_dot_count > 0 && oneChar != ".") {
            // m_decimal = m_decimal + oneChar;
        // }
    // }
    
    // if (m_dot_count > 1) {
        // alert("You have typed more than one decimal separator.");
        // return false;
    // }
    
    // m_format = m_integer;
    // var m_formatted = "";
    // var m_new_str = "";
    // m_end = m_format.length;
    // m_stat_pos = m_end - 4;
    // var m_last_pos = m_end;
    // while (m_stat_pos >= 0) {
        // m_chk_str = m_format.substr(m_stat_pos, 1);
        // if (m_chk_str != null) {
            // m_add_str = "," + m_format.substr(m_stat_pos + 1, 3);
            // m_new_str = m_add_str + m_new_str;
        // }
        // m_last_pos = m_stat_pos;
        // m_stat_pos = m_stat_pos - 3;
    // }
    
    // if (m_decimal.length > decimalsize){
        // m_decimal = m_decimal.substr(0, decimalsize);
    // }
    // else {
        // i = 1;
        // bal_length = decimalsize - m_decimal.length;
        // while (i <= bal_length) {
            // m_decimal = m_decimal + "0";
            // i = i + 1;
        // }
    // }
    // m_decimal = "." + m_decimal;
    
    // m_new_str = m_format.substr(0, m_last_pos + 1) + m_new_str + m_decimal;
    
    // if (m_minus) {
        // m_new_str = "-" + m_new_str;
    // }
    // value = m_new_str;
    
    // return value;
}


function unformat_number(object){
    m_format=object.value;
    var m_number="";
    for (var i = 0; i < m_format.length; i++){
        var oneChar = m_format.charAt(i)
        if (oneChar != ","){
            m_number=m_number+oneChar;
        }
    }
    return m_number;
}


function unformat_noobject(m_value) {
    var str_value       = m_value.toString();
    var str_new_value   = '';
    var str_one_char    = '';
    for (var i = 0; i < str_value.length; i++) {
        str_one_char = str_value.charAt(i);
        if (str_one_char != ",") {
            str_new_value = str_new_value + str_one_char;
        }
    }
    
    return str_new_value;
}


function elsefocus (object){
    object.focus();
}


// general purpose function to see if an input value has been entered at all
function username(username){
    alert("sas");
    return true
}


function date(component){
    now= new Date();
    component.value=now.getDate();
    return true
}


function month(component){
    now= new Date();
    component.value=now.getMonth() + 1;
    return true
}


function year(component){
    now= new Date();
    component.value=1900 + now.getYear();
    return true
}


function isEmptymessage(inputStr,message){
    if (inputStr.value == null || inputStr.value == "") {
        //if (message!="No") { 
        alert(message+" should be entered");
        inputStr.focus();
        //}
        return true;
    }
    return false
}


function islengthok(inputStr,size){
    var m_length=0;
    var m_difference=0;	
    var m_fulllength="";
    chk_stat=isEmpty(inputStr);
    if (!chk_stat){
        m_length=inputStr.value.length;
        if (m_length > size){
            return false
        }
    }
    else{
        return false
    }
    return true
}


function isEmpty(inputStr){
    if (inputStr.value == null || inputStr.value == ""){
        return true
    }
    return false
}


// general purpose function to see if a suspected numeric input is a positive integer
function isPosInteger(inputVal){
    inputStr = inputVal.toString()
    for (var i = 0; i < inputStr.length; i++){
        var oneChar = inputStr.charAt(i)
        if (oneChar < "0" || oneChar > "9"){
            return false
        }
    }
    return true
}


function isGreatZero(object){
    inputStr = unformat_number(object);
    if (inputStr<=0){
        return false
    }
    return true
}


// general purpose function to see if a suspected numeric input is a positive or negative integer
function isInteger(inputVal){
    inputStr = inputVal.toString()
    for (var i = 0; i < inputStr.length; i++){
        var oneChar = inputStr.charAt(i)
        if (i == 0 && oneChar == "-"){
            continue
        }
        if (oneChar < "0" || oneChar > "9"){
            return false
        }
    }
    return true
}


// general purpose function to see if a suspected numeric input is a positive or negative number
/* Commented By Samitha Kulatilaka On 2010-08-10 */
/* Reason : Creates Conflicts With JQuery Grid */
// function isNumber(inputVal){
    // oneDecimal = false
        // inputStr = inputVal.toString()
    // for (var i = 0; i < inputStr.length; i++){
        // var oneChar = inputStr.charAt(i)
        // if (i == 0 && oneChar == "-"){
            // continue
        // }
        // if (oneChar == "." && !oneDecimal){
            // oneDecimal = true
                // continue
        // }
        // if (oneChar < "0" || oneChar > "9"){
            // alert('Please Enter a Number');
            // return false
        // }
    // }
    // return true
// }


// check the entered month for too high a value
function checkMonthLength(c_dd,c_mm,c_yyyy) {
    mm=c_mm.value;
    dd=c_dd.value;
    yyyy=parseInt(c_yyyy.value);
    inputyearStr = c_yyyy.value.toString();
    if (inputyearStr.length != 4){
        alert("Please enter year in four digit(YYYY) number format");
        c_yyyy.value="";
        c_yyyy.focus();
        return false
    }
    
    if (isNaN(dd)){
        alert("Day should be a number");
        c_dd.focus();
        c_dd.value="";
        return false
    }
    else if (dd<1 || dd>31){
        alert("Date must be between 1 and 31");
        c_dd.focus();
        c_dd.value="";
        return false
    }
    else if (isNaN(mm)){
        alert("Month should be a number");
        c_mm.focus();
        c_mm.value="";
        return false
    }
    else if (mm<1 || mm>12){
        alert("Month must be between 1 and 12");
        c_mm.focus();
        c_mm.value="";
        return false
    }
    else if (isNaN(yyyy)){
        alert("Year should be a number");
        c_yyyy.value="";
        c_yyyy.focus();
        return false
    }
    else if (yyyy<1900 || yyyy>3000){
        alert("Year should be between 1900 and 3000");
        c_yyyy.value="";
        c_yyyy.focus();
        return false
    }
    
    
    var months = new
        Array("","January","February","March","April","May","June","July","August","September","October","November","December")
    if (mm==2){
        //	checkLeapMonth(c_mm,c_dd,c_yyyy);
        //***************************************checking february*************************
        mm=parseInt(c_mm.value);
        dd=parseInt(c_dd.value);
        yyyy=parseInt(c_yyyy.value);
        
        if (yyyy % 4 > 0 && dd > 28){
            alert("February of " + yyyy + " has only 28 days.")
            c_dd.value="";
            c_dd.focus();
            return false
        } 
        else if (dd > 29){
            alert("February of " + yyyy + " has only 29 days.")
            c_dd.value="";
            c_dd.focus();
            return false
        }
        else if (dd < 1 || dd > 29){
            alert("Date between 1 and 29.")
            c_dd.value="";
            c_dd.focus();
            return false
        }
        else{
            return true
        }
        //************************************************************
    }
    else if ((mm == 4 || mm == 6 || mm == 9 || mm == 11) && dd > 30){
        if(mm=="04"){
            alert(months[04] + " has only 30 days.")
        }
        else if(mm=="06"){
            alert(months[06] + " has only 30 days.")
        }
        else if(mm=="09"){
            alert(months[09] + " has only 30 days.")
        }
        else if(mm=="11"){
            alert(months[11] + " has only 30 days.")
        }
        else {
            alert("selcted month has only 30 days");
        }
        c_dd.focus();
        // c_dd.value="";
        return false
    } 
    else if ( dd > 31){
        alert(months[mm] + " has only 31 days.")
        c_dd.focus();
        c_dd.value="";
        return false
    }
    else if ( dd < 1){
        alert("Please check the day.")
        c_dd.focus();
        c_dd.value="";
        return false
    }
    
    return true
}


// check the entered month but do not display message
function isMonthok(c_dd,c_mm,c_yyyy){
    
    mm=parseInt(c_mm.value);
    dd=parseInt(c_dd.value);
    yyyy=parseInt(c_yyyy.value);
    inputyearStr = c_yyyy.value.toString();
    if (inputyearStr.length != 4){
        alert("Year should be between 1900 and 3000");
        c_yyyy.value="";
        c_yyyy.focus();
        return false
    }
    if (isNaN(dd)) {
        c_dd.focus();
        c_dd.value="";
        return false
    }
    else if (dd<1 && dd>31){
        alert("Date must be between 1 and 31");
        c_dd.focus();
        c_dd.value="";
        return false
    }
    else if (isNaN(mm)){
        c_mm.focus();
        c_mm.value="";
        return false
    }
    else if (isNaN(yyyy)){
        c_yyyy.value="";
        c_yyyy.focus();
        return false
    }
    var months = new
        Array("","January","February","March","April","May","June","July","August","September","October","November","December")
    if (mm==2){
        isLeapMonth(c_mm,c_dd,c_yyyy);
    }
    else if ((mm == 4 || mm == 6 || mm == 9 || mm == 11) && dd > 30){
        c_dd.focus();
        c_dd.value="";
        return false
    } 
    else if (dd > 31){
        c_dd.focus();
        c_dd.value="";
        return false
    }
    else if (dd < 1){
        c_dd.focus();
        c_dd.value="";
        return false
    }
    return true
}


// check the entered February date for too high a value but do not display message
function isLeapMonth(c_mm,c_dd,c_yyyy){
    mm=parseInt(c_mm.value);
    dd=parseInt(c_dd.value);
    yyyy=parseInt(c_yyyy.value);
    
    if (yyyy % 4 > 0 && dd > 28){
        c_dd.focus();
        c_dd.value="";
        return false
    } 
    else if (dd > 29){
        c_dd.focus();
        c_dd.value="";
        return false
    }
    else if (dd < 1){
        c_dd.focus();
        c_dd.value="";
        return false
    }
    return true
}


function numbersToChar(obj) {
    
    var ar_ones=new Array("","one","two","three","four","five","six","seven","eight","nine");
    var ar_tens=new Array("","ten","twenty","thirty","forty","fifty","sixty","seventy","eighty","ninety");
    var ar_teens=new Array("","eleven","twelve","thirteen","fourteen","fifteen","sixteen","seventeen","eighteen","nineteen");
    var m_value;
    var m_cents="";
    
    var m_full_str="";
    var m_digit=0;
    var m_length=obj.value.length;
    var m_value=obj.value;
    
    var m_dot=obj.value.indexOf(".");
    
    if (m_dot!= -1 ) {
        m_cents=obj.value.substring(m_dot+1,m_length);
        if (m_cents.length==1) {
            m_cents=m_cents+"0";
        }
        else if (m_cents.length==2) {
            m_cents=m_cents+"00";
        }
        m_length=m_value.substring(0,m_dot).length;
        m_value=obj.value.substring(0,m_dot);
    }
    
    
    for (j=1 ; j<= 15-m_length; j++) {
        m_value="0"+m_value;
    }
    var m_cnt=15-m_length;
    for (i=m_cnt; i<=14; i++) {
        m_digit = m_value.substring(m_cnt,m_cnt+1);
        
        
        if (parseInt(m_cnt)==3) {  // 100,000,000,000
            if (m_digit!=0) {
                if ((parseInt(m_value.substring(m_cnt+1,m_cnt+2))==0) && (parseInt(m_value.substring(m_cnt+2,m_cnt+3))==0)) { 
                    m_full_str=m_full_str+ar_ones[m_digit]+" hundred billion ";
                    m_cnt+=2;
                }
                else {
                    m_full_str=m_full_str+ar_ones[m_digit]+" hundred ";
                }
            }
        }
        else if (parseInt(m_cnt)==4) {  // 10,000,000,000
            if (m_digit!=0) {
                if ((parseInt(m_value.substring(m_cnt,m_cnt+1))==1) && (parseInt(m_value.substring(m_cnt+1,m_cnt+2))!=0)) { 
                    m_full_str=m_full_str+ar_teens[parseInt(m_value.substring(m_cnt+1,m_cnt+2))]+" billion ";
                    m_cnt+=1;
                }
                else if ( parseInt(m_value.substring(m_cnt+1,m_cnt+2))==0) { 
                    m_full_str=m_full_str+ar_tens[m_digit]+" billion ";
                }
                else {
                    m_full_str=m_full_str+ar_tens[m_digit]+" ";
                }
            }
        }
        else if (parseInt(m_cnt)==5) {  // 1,000,000,000
            if (m_digit!=0) {
                m_full_str=m_full_str+ar_ones[m_digit]+" billion ";
            }
        }
        else if (parseInt(m_cnt)==6) {  // 100,000,000
            if (m_digit!=0) {
                if ((parseInt(m_value.substring(m_cnt+1,m_cnt+2))==0) && (parseInt(m_value.substring(m_cnt+2,m_cnt+3))==0)) { 
                    m_full_str=m_full_str+ar_ones[m_digit]+" hundred million ";
                    m_cnt+=2;
                }
                else {
                    m_full_str=m_full_str+ar_ones[m_digit]+" hundred ";
                }
            }
        }
        else if (parseInt(m_cnt)==7) {  // 10,000,000
            if (m_digit!=0) {
                if ((parseInt(m_value.substring(m_cnt,m_cnt+1))==1) && (parseInt(m_value.substring(m_cnt+1,m_cnt+2))!=0)) { 
                    m_full_str=m_full_str+ar_teens[parseInt(m_value.substring(m_cnt+1,m_cnt+2))]+" million ";
                    m_cnt+=1;
                }
                else if ( parseInt(m_value.substring(m_cnt+1,m_cnt+2))==0) { 
                    m_full_str=m_full_str+ar_tens[m_digit]+" million ";
                    m_cnt+=1;
                }
                else {
                    m_full_str=m_full_str+ar_tens[m_digit]+" ";
                }
            }
        }
        else if (parseInt(m_cnt)==8) {  // 1,000,000
            if (m_digit!=0) {
                m_full_str=m_full_str+ar_ones[m_digit]+" million ";
            }
        }
        else if (parseInt(m_cnt)==9) {  // 100,000
            if (m_digit!=0) {
                if ((parseInt(m_value.substring(m_cnt+1,m_cnt+2))==0) && (parseInt(m_value.substring(m_cnt+2,m_cnt+3))==0)) {
                    m_full_str=m_full_str+ar_ones[m_digit]+" hundred thousand ";
                    m_cnt+=2;
                }
                else {
                    m_full_str=m_full_str+ar_ones[m_digit]+" hundred ";
                }
            }
        }
        else if (parseInt(m_cnt)==10) {  // 10,000
            if (m_digit!=0) {
                if (parseInt(m_value.substring(m_cnt+1,m_cnt+2))==0) { 
                    m_full_str=m_full_str+ar_tens[m_digit]+" thousand ";
                    m_cnt+=1;
                }
                else if ((parseInt(m_value.substring(m_cnt,m_cnt+1))==1) && (parseInt(m_value.substring(m_cnt+1,m_cnt+2))!=0)) { 
                    m_full_str=m_full_str+ar_teens[parseInt(m_value.substring(m_cnt+1,m_cnt+2))]+" thousand ";
                    m_cnt+=1;
                }
                else {
                    m_full_str=m_full_str+ar_tens[m_digit]+" ";
                }
            }
        }
        else if (parseInt(m_cnt)==11) {  // 1,000
            if (m_digit!=0) {
                m_full_str=m_full_str+ar_ones[m_digit]+" thousand ";
            }
        }
        else if (parseInt(m_cnt)==12) {  // 100
            if (m_digit!=0) {
                m_full_str=m_full_str+ar_ones[m_digit]+" hundred ";
            }
        }
        else if (parseInt(m_cnt)==13) {  // 10
            if (m_digit!=0) {
                if ((parseInt(m_digit)==1) && parseInt(m_value.substring(m_cnt+1,m_cnt+2))!=0) {
                    m_full_str=m_full_str+ar_teens[parseInt(m_value.substring(m_cnt+1,m_cnt+2))]+" ";
                    m_cnt+=1;
                }
                else {
                    m_full_str=m_full_str+ar_tens[m_digit]+" ";
                }
            }
        }
        else if (parseInt(m_cnt)==14) {  // 1
            if (m_digit!=0) {
                m_full_str=m_full_str+ar_ones[m_digit]+" ";
            }
        }
        m_cnt++;
    }
    m_cnt=0;
    for (i=m_cnt; i<=1; i++) {
        m_digit = m_cents.substring(m_cnt,m_cnt+1);
        if (parseInt(m_cnt)==0) {  // 10
            if (m_digit!=0) {
                if ((parseInt(m_digit)==1) && parseInt(m_cents.substring(m_cnt+1,m_cnt+2))!=0) {
                    m_full_str=m_full_str+"and cents "+ar_teens[parseInt(m_cents.substring(m_cnt+1,m_cnt+2))]+" ";
                    m_cnt+=1;
                }
                else {
                    m_full_str=m_full_str+"and cents "+ar_tens[m_digit]+" ";
                }
            }
        }
        else if (parseInt(m_cnt)==1) {  // 1
            if (m_digit!=0) {
                if (parseInt(m_cents.substring(m_cnt-1,m_cnt))==0) {
                    m_full_str=m_full_str+"and cents "+ar_ones[m_digit];						
                }
                else {
                    m_full_str=m_full_str+ar_ones[m_digit];	
                }
            }
        }
        m_cnt++;
    }
    
    m_full_str = changeCase(m_full_str);
    return m_full_str
}


function ChkDateValidation(datecomponent,monthcomponent,yearcomponent) {
    if (checkMonthLength(datecomponent,monthcomponent,yearcomponent)==true) {
    }
}


function ChkValidity(ToDateComp,ToMonthComp,ToYearComp,FromDateComp,FromMonthComp,FromYearComp){
    ToDD=ToDateComp.value;
    ToMM=ToMonthComp.value;
    ToYYYY=ToYearComp.value;
    FromDD=FromDateComp.value;
    FromMM=FromMonthComp.value;
    FromYYYY=FromYearComp.value;
    ToDate=new Date(ToYYYY,ToMM,ToDD);
    FromDate=new Date(FromYYYY,FromMM,FromDD);
    
    if (ToDate < FromDate)   {
        alert("To Date should be greater than From Date");
    }
}


//--------remove leading spaces---------//
function rem_lead_spaces(mobject) {
    m_objval=mobject.value;
    m_length = m_objval.length;
    cnt = 0;
    m_char =  m_objval.charAt(cnt);
    while (m_char==" " && cnt <= m_length) {
        cnt++;
        m_char =  m_objval.charAt(cnt);
    }
    m_objval=m_objval.substring(cnt,m_length);
    //mobject.value = m_objval;
    return m_objval;
}


//--------remove trailing spaces---------//
function rem_trail_spaces(mobject) {
    m_objval=mobject.value;
    m_length = m_objval.length;
    cnt = m_length-1;
    m_char =  m_objval.charAt(cnt);
    while (m_char==" " && cnt >= 0) {
        cnt--;
        m_char =  m_objval.charAt(cnt);
    }
    m_objval=m_objval.substring(0,cnt+1);
    return m_objval;
}


function close_window() {
    if (confirm("Are you sure you want to close the screen?")){ 
        // parent.frames[0].close_window();
        window.close();
        window.location.href = "" + servlet_client_url + ":" + client_t3_port + "/" + client_name + "AF_CO_FollowupAlert?chksql=main_page";
    }
}


function round_number(number, dec_places) {
    /* (c) Copyright 2008, Russell Walker, Netshine Software Limited. www.netshinesoftware.com
     * Version 2.0. Change log:
     * 18/12/08 Fixed bug where digits after decimal point greater than 995
     * 29/01/09 Added support for negative numbers (symmetrical rounding) and strip white space
     * 12/03/09 Fixed bug where first digit is a 9 and needs to be rounded up
     */
    var new_number = '';
    var i = 0; /* Just used in loops */
    var sign = ""; /* If negative, a minus sign will be prefixed to the result */
    number = number.toString(); /* We need to operate on and return a string, not a number */
    number = number.replace(/^\s+|\s+$/g, ''); /* Remove any excess white space */
    
    /* Do we have a negative number? */
    if (number.charCodeAt(0) == 45) { /* minus sign */
        sign = '-';
        number = number.substr(1).replace(/^\s+|\s+$/g, '');
    }
    
    dec_places = dec_places * 1; /* We need an integer */
    dec_point_pos = number.lastIndexOf(".");
   
    /* If there is nothing before the decimal point, prefix with a zero */
    if (dec_point_pos == 0) {
        number = "0" + number;
        dec_point_pos = 1;
    }
   
    /* Has an integer been passed in? */
    if (dec_point_pos == -1 || dec_point_pos == number.length - 1) {
        if (dec_places > 0) {
            new_number = number + ".";
            for (i = 0; i < dec_places; i++) {
                new_number += "0";
            }
            if (new_number == 0) {
                sign = "";
            }
            return sign + new_number;
        }
        else {
            return sign + number;
        }
    }
   
    /* Do we already have the right number of decimal places? */
    var existing_places = (number.length - 1) - dec_point_pos;
    if (existing_places == dec_places) {
        return sign + number; /* If so, just return the input value */
    }
   
    /* Do we already have less than the number of decimal places we want? */
    if (existing_places < dec_places) {
        /* If so, pad out with zeros */
        new_number = number;
        for (i = existing_places; i < dec_places; i++) {
            new_number += "0";
        }
        if (new_number == 0) {
            sign = "";
        }
        return sign + new_number;
    }
   
    /* Work out whether to round up or not */
    var end_pos = (dec_point_pos * 1) + dec_places;
    var round_up = false; /* Whether or not to round up (add 1 to) the next digit along */
    if ((number.charAt(end_pos + 1) * 1) > 4) {
        round_up = true;
    }
   
    /* Record each digit in an array for easier manipulation */
    var digit_array = new Array();
    for (i = 0; i <= end_pos; i++) {
        digit_array[i] = number.charAt(i);
    }
   
    /* Round up the last digit if required, and continue until no more 9's are found */
    for(i=digit_array.length - 1; i>=0; i--) {
        if (digit_array[i] == ".") {
            continue;
        }
        if (round_up) {
            digit_array[i]++;
            if (digit_array[i] < 10) {
                break;
            }
        }
        else {
            break;
        }
    }
   
    /* Reconstruct the string, converting any 10's to 0's (except for first digit which can stay as a 10) */
    for (i=0; i<=end_pos; i++) {
        if (digit_array[i] == "." || digit_array[i] < 10 || i == 0) {
            new_number += digit_array[i];
        }
        else {
            new_number += "0";
        }
    }
   
    /* If there are no decimal places, we don't need a decimal point */
    if (dec_places == 0) {
        new_number = new_number.replace(".", "");
    }
   
    if (new_number == 0) {
        sign = "";
    }
   
    /* That should do it! */
    return sign + new_number;
}


/* Added By Samitha Kulatilaka */
// ***** This Is A Simple Find And Replace Function (For A Single Character) *****
function characterReplace(original_string, find_value, replace_value) {
    var single_char = "";
    var output_string = "";
    for (var i = 0; i < original_string.length; i++) {
        single_char = original_string.charAt(i);
        if (single_char == find_value) {
            single_char = single_char.replace(find_value, replace_value);
        }
        output_string = output_string + single_char;
    }
    return output_string;
}


/* Added By Samitha Kulatilaka */
// ***** This Function Is Used To Move The Decimal Pointer To The Right *****
function moveCharacter(original_string, move_character, move_places) {
    
    var number_integer = "";
    var number_fraction = "";
    var output_string = "";
    
    var needed_zeros = "";
    var zeros = ""
    
    // Set The Number To A Whole Number
    for (var i = 0; i < move_places; i++) {
        original_string = original_string * 10;
    }
    
    output_string = Math.round(original_string);
    output_string = output_string.toString();
    
    // Add Leading Zeros(0's) To The Number
    if (output_string.length < move_places) {
        needed_zeros = move_places - output_string.length;
        for (var i = 0; i < needed_zeros; i++) {
            zeros = "0" + zeros;
        }
        output_string = zeros + output_string;
    }
    
    // This If Statement Is Used Only If Fractional Precision Is Required
    if (move_places > 0) {
        // Extract The Integer Part
        number_integer = output_string.substr(0, output_string.length - move_places);
        
        // Extract The Fractional Part
        for (var i = 0; i < output_string.length; i++) {
            if (i >= output_string.length - move_places) {
                number_fraction = number_fraction + output_string.charAt(i);
            }
        }
        
        if (number_integer == "") {
            number_integer = "0"; // Setting The Integer Part To Zero(0) If It Is Empty
        }
        output_string = number_integer + "." + number_fraction;
    }
    
    return output_string;
    
}


/* Added By Samitha Kulatilaka */
// ***** This Function Is Used To Format A Numeric Value (i.e. Insert Commas As Necessary) *****
// function formatNumber(original_number) {
function formatNumber(original_number, number_or_percentage) {  /* Modification 2010-02-12 */
    
    var count = 0;
    var output_number = "";
    
    var number_passed_decimal = false;
    var decimal_point = "";
    var number_integer = "";
    var number_fraction = "";
    var single_char = "";
    var number_rest = "";
    
    
    // Seperating The Integer Part And The Fraction Part
    for (var i = 0; i < original_number.length; i++) {
        single_char = original_number.charAt(i);
        
        if (single_char == ".") {
            number_passed_decimal = true;
            decimal_point = ".";
        }
        
        if ((number_passed_decimal == false) && !(isNaN(single_char))) {
            number_integer = number_integer + single_char;
        }
        else if ((number_passed_decimal == true) && !(isNaN(single_char))) {
            number_fraction = number_fraction + single_char;
        }
    }
    number_rest = decimal_point + number_fraction; /* Combining The Latter Part Of The Number */
    
    /* Main Operation */
    for (var i = number_integer.length; i > 0; i--) {
        output_number = number_integer.charAt(i-1) + output_number;
        
        count = count + 1;
        if ((count % 3 == 0) && (number_or_percentage == 'number')) {  /* Modification 2010-02-12 */
            output_number = "," + output_number;
        }
    }
    if (output_number.charAt(0) == ",") { /* Remove The Leading Comma In The Integer Part */
        output_number = output_number.slice(1);
    }
    /* Main Operation - End */
    
    output_number = output_number + number_rest; /* Creating The Final Number */
    return output_number;
    
}


/* Added By Samitha Kulatilaka */
/* ***** This Function Validates A Numeric Value  ***** */
// function validateNumber(object, min_value, max_value, db_scale) {
// function validateNumber(object, min_value, max_value, db_scale, number_or_percentage) {
function validateNumber(object, min_value, max_value, db_scale, number_or_percentage, focus_textbox) { /* Modification 2010-02-12 */
    // max_value = parseFloat(max_value); /* Modification 2010-05-21 */
    
    
    /* Modification 2010-02-12 */
    /* Function Now Can Be Used For Percentages (i.e. No Commas In Final Number) */
    if ((number_or_percentage != 'number') && (number_or_percentage != 'percentage')) {
        alert('ERROR IN PARAMETER - PLEASE PASS number OR percentage AS THE 5TH PARAMETER!!!');
    }
    
    var number_value = object.value;
    
    var number_is_negative = false;
    var number_sign = "";
    
    number_value = characterReplace(number_value, " ", ""); /* Custom Function */
    number_value = characterReplace(number_value, ",", ""); /* Custom Function */
    
    /* Check If The Value Is A Number */
    if (isNaN(number_value)) {
        alert("Value should be a number.");
        if (focus_textbox != 'N') {                                                                 /* Modification 2010-04-02 */
            var m_object_name = object.name;
            setTimeout('document.forms[0].elements[\'' + m_object_name + '\'].focus()', 1);
            setTimeout('document.forms[0].elements[\'' + m_object_name + '\'].select()', 1);
        }
        return false;
    }
    
    /* Modification 2010-02-12 */
    // number_value = parseFloat(number_value);
    
    /* Removing The Plus/Minus(+/-) Mark Of A Number */
    if (number_value.charAt(0) == "+") {
        number_value = number_value.slice(1);
    }
    else if (number_value.charAt(0) == "-") {
        number_value = number_value.slice(1);
        number_is_negative = true;
        number_sign = "-";
    }
    
    /* Commented By Samitha Kulatilaka On 2010-05-21 */
    number_value = moveCharacter(number_value, ".", db_scale); /* Custom Function */
    // var number_object = new Number(parseFloat(number_value));
    // number_value = round_number(number_value, db_scale);
    // number_value = parseFloat(number_object.toFixed(db_scale));
    // number_value = number_value.toString();
    
    /* Getting The Complete Number (i.e. Putting The Negative Sign) */
    if (number_is_negative == true) {
        number_value = number_sign + number_value;
    }
    /* Doing The Range Validation */
    if (!((number_value >= min_value) && (number_value <= max_value))) {
        // alert("The number should be between " + min_value + " & " + max_value + ".");
        alert("Value out of range.");
        if (focus_textbox != 'N') {                                                                 /* Modification 2010-04-02 */
            var m_object_name = object.name;
            setTimeout('document.forms[0].elements[\'' + m_object_name + '\'].focus()', 1);
            setTimeout('document.forms[0].elements[\'' + m_object_name + '\'].select()', 1);
        }
        return false;
    }
    
    if (number_value.charAt(0) == "-") { // Remove The Minus Sign Before Formatting The Number
        number_value = number_value.slice(1);
    }
    // number_value = formatNumber(number_value); /* Custom Function */
    number_value = formatNumber(number_value, number_or_percentage); /* Custom Function */      /* Modification 2010-02-12 */
    if (number_is_negative == true) { /* Add The Minus Sign After Formatting The Number */
        number_value = number_sign + number_value;
    }
    
    /* Modification 2010-02-12 */
    /* Removing The Minus From -0.00 */
    if ((number_value.charAt(0) == "-") && (number_value.charAt(1) == "0")) {
        var temp_number_value = number_value.slice(1);
        var flag_zero_point_zero = true;
        for (var i = 0; i < temp_number_value.length; i++) {
            if ((temp_number_value.charAt(i) != '0') && (temp_number_value.charAt(i) != '.')) {
                flag_zero_point_zero = false;
                break;
            }
        }
        if (flag_zero_point_zero == true) {
            number_value = number_value.slice(1);
        }
    }
    
    object.value = number_value;
    return true;
    
}


/* Added By Samitha Kulatilaka */
function getAllowedCharacterList(m_type) {
    
    /* Lists Included */
    /* 
     * 'DEFAULT'            This Is The Default List
     * 'NAME'
     * 'ADDRESS'
     * 'ACCOUNT_NO'         (Numbers Only. i.e. 0-9)
     * 'COMMENT'
     * 'NAME_2'             FIXME : To Be Defined
     * 'FULL_NAME'
     * 'USERNAME'
     * 'NO_WHITESPACE'
     * 'NUMBER'
     */
    
    var allowed_character_list    = new Array();
    var allowed_character_list_01 = new Array();
    var allowed_character_list_02 = new Array();
    var allowed_character_list_03 = new Array();
    var allowed_character_list_04 = new Array();
    var allowed_character_list_05 = new Array();
    
    
    /* Define The List */
    /* Uppercase Latin Alphabet */
    if ((m_type == 'DEFAULT') || (m_type == 'NAME') || (m_type == 'ADDRESS') || (m_type == 'COMMENT') || (m_type == 'NAME_2') || (m_type == 'FULL_NAME') || (m_type == 'USERNAME') || (m_type == 'NO_WHITESPACE') || (m_type == 'INVOICE_NUMBER')) {
        allowed_character_list_01[0]    = 'A';
        allowed_character_list_01[1]    = 'B';
        allowed_character_list_01[2]    = 'C';
        allowed_character_list_01[3]    = 'D';
        allowed_character_list_01[4]    = 'E';
        allowed_character_list_01[5]    = 'F';
        allowed_character_list_01[6]    = 'G';
        allowed_character_list_01[7]    = 'H';
        allowed_character_list_01[8]    = 'I';
        allowed_character_list_01[9]    = 'J';
        allowed_character_list_01[10]   = 'K';
        allowed_character_list_01[11]   = 'L';
        allowed_character_list_01[12]   = 'M';
        allowed_character_list_01[13]   = 'N';
        allowed_character_list_01[14]   = 'O';
        allowed_character_list_01[15]   = 'P';
        allowed_character_list_01[16]   = 'Q';
        allowed_character_list_01[17]   = 'R';
        allowed_character_list_01[18]   = 'S';
        allowed_character_list_01[19]   = 'T';
        allowed_character_list_01[20]   = 'U';
        allowed_character_list_01[21]   = 'V';
        allowed_character_list_01[22]   = 'W';
        allowed_character_list_01[23]   = 'X';
        allowed_character_list_01[24]   = 'Y';
        allowed_character_list_01[25]   = 'Z';
    }
    
    /* Lowercase Latin Alphabet */
    if ((m_type == 'DEFAULT') || (m_type == 'NAME') || (m_type == 'ADDRESS') || (m_type == 'COMMENT') || (m_type == 'NAME_2') || (m_type == 'FULL_NAME') || (m_type == 'USERNAME') || (m_type == 'NO_WHITESPACE') || (m_type == 'INVOICE_NUMBER')) {
        allowed_character_list_02[0]    = 'a';
        allowed_character_list_02[1]    = 'b';
        allowed_character_list_02[2]    = 'c';
        allowed_character_list_02[3]    = 'd';
        allowed_character_list_02[4]    = 'e';
        allowed_character_list_02[5]    = 'f';
        allowed_character_list_02[6]    = 'g';
        allowed_character_list_02[7]    = 'h';
        allowed_character_list_02[8]    = 'i';
        allowed_character_list_02[9]    = 'j';
        allowed_character_list_02[10]   = 'k';
        allowed_character_list_02[11]   = 'l';
        allowed_character_list_02[12]   = 'm';
        allowed_character_list_02[13]   = 'n';
        allowed_character_list_02[14]   = 'o';
        allowed_character_list_02[15]   = 'p';
        allowed_character_list_02[16]   = 'q';
        allowed_character_list_02[17]   = 'r';
        allowed_character_list_02[18]   = 's';
        allowed_character_list_02[19]   = 't';
        allowed_character_list_02[20]   = 'u';
        allowed_character_list_02[21]   = 'v';
        allowed_character_list_02[22]   = 'w';
        allowed_character_list_02[23]   = 'x';
        allowed_character_list_02[24]   = 'y';
        allowed_character_list_02[25]   = 'z';
    }
    
    /* Numerical Digits */
    if ((m_type == 'DEFAULT') || (m_type == 'ADDRESS') || (m_type == 'ACCOUNT_NO') || (m_type == 'COMMENT') || (m_type == 'USERNAME') || (m_type == 'NO_WHITESPACE') || (m_type == 'NUMBER') || (m_type == 'INVOICE_NUMBER')) {
        allowed_character_list_03[0]    = '0';
        allowed_character_list_03[1]    = '1';
        allowed_character_list_03[2]    = '2';
        allowed_character_list_03[3]    = '3';
        allowed_character_list_03[5]    = '4';
        allowed_character_list_03[6]    = '5';
        allowed_character_list_03[7]    = '6';
        allowed_character_list_03[8]    = '7';
        allowed_character_list_03[9]    = '8';
        allowed_character_list_03[10]   = '9';
    }
    
    /* Other Special Characters */
    if ((m_type == 'DEFAULT') || (m_type == 'NAME') || (m_type == 'ADDRESS') || (m_type == 'COMMENT') || (m_type == 'FULL_NAME')) {
        allowed_character_list_04[0]    = '\u000A';     /* Line Feed (LF) */
        allowed_character_list_04[1]    = '\u000D';     /* Carriage Return (CR) */
        allowed_character_list_04[2]    = ' ';
        allowed_character_list_04[3]    = '@';
        allowed_character_list_04[4]    = '-';
        allowed_character_list_04[5]    = '+';
        allowed_character_list_04[6]    = '.';
        allowed_character_list_04[7]    = ',';
        allowed_character_list_04[8]    = '(';
        allowed_character_list_04[9]    = ')';
    }
    if (m_type == 'NO_WHITESPACE') {
        allowed_character_list_04[0]    = '-';
    }
    
    if (m_type != 'NO_WHITESPACE') {
        allowed_character_list_04.splice(3, 1);                 /* Removed '@' For Name */
    }
    
    if (m_type == 'COMMENT') {
        allowed_character_list_04.splice(0, 0, '@');            /* Add '@' For Comment */
        allowed_character_list_04.splice(0, 0, '&');            /* Add '&' For Comment */
        allowed_character_list_04.splice(0, 0, '_');            /* Add '_' For Comment */
    }
    if (m_type == 'ADDRESS') {
        allowed_character_list_04.splice(0, 0, '\\', '/');      /* Add '\', '/' For Address */
    }
    if (m_type == 'NAME_2') {
        allowed_character_list_04.splice(0, 0, ' ');            /* Add ' ' For Name (Alphabetical Characters Only) */
    }
    if (m_type == 'FULL_NAME') {
        allowed_character_list_04.splice(0, 0, ' ');            /* Add ' ' For Full Name */
        // allowed_character_list_04.splice(0, 0, '&');            /* Add '&' For Full Name */
    }
    if (m_type == 'USERNAME') {
        allowed_character_list_04.splice(0, 0, '_');            /* Add '_' Username */
    }
    if (m_type == 'INVOICE_NUMBER') {
        allowed_character_list_04[0]    = '/';
        allowed_character_list_04[0]    = '\\';
        allowed_character_list_04[0]    = '.';
        allowed_character_list_04[0]    = '-';
    }
    
    allowed_character_list = allowed_character_list_01.concat(allowed_character_list_02, allowed_character_list_03, allowed_character_list_04);
    
    return allowed_character_list;
}


function removeInvalidCharacters(original_string_object, allowed_character_list, m_screen) {
    var single_char = "";
    var output_string = original_string_object.value;
    var has_invalid_character = true;
    var has_invalid_character_total = false;
    var allowed_character = "";
    
    for (var i = 0; i < original_string_object.value.length; i++) {
        single_char = original_string_object.value.charAt(i);
        has_invalid_character = true;
        
        for (var j = 0; j < allowed_character_list.length; j++) {
            allowed_character = allowed_character_list[j];
            if (single_char == allowed_character) {
                has_invalid_character = false;
                break;
            }
        }
        
        if (has_invalid_character == true) {
            output_string = output_string.replace(single_char, '');
            has_invalid_character_total = true;
        }
    }
    
    if (has_invalid_character_total == true) {
        if (m_screen == 'Login') {
            output_string = '';
            setTimeout('alert(\'Warning: Invalid characters are entered. They have been removed.\')', 10);
            setTimeout('document.getElementById(\'' + original_string_object.id + '\').select()', 1);
        }
        else {
            setTimeout('alert(\'Warning: Invalid characters are entered. They will be removed.\')', 10);
            setTimeout('document.forms[0].elements[\'' + original_string_object.name + '\'].select()', 1);
        }
    }
    
    return output_string;
    // return original_string;
}


/* Added By Samitha Kulatilaka */
function strTrim(object, m_textarea, m_screen, m_type) {
    object.value = object.value.replace(/^\s+|\s+$/g, '');          /* Trim The String */
    /* For Textareas, 'm_textarea' Is Set To '1' */
    if (m_textarea != 1) {
        /* Commented By Samitha Kulatilaka On 2010-07-20 */
        // object.value = object.value.replace(/\s+/g, ' ');           /* Remove Multiple Spaces Between Words */
        /* This Is Not Done In Textareas */
    }
    
    var allowed_character_list = new Array();
    if (m_type == null) {
        m_type = 'DEFAULT';
    }
    
    /* Added By Samitha Kulatilaka On 2010-08-09 */
    if (m_type != 'ANY') {
        allowed_character_list = getAllowedCharacterList(m_type);
        object.value = removeInvalidCharacters(object, allowed_character_list, m_screen)
    }
}


/* Added By Samitha Kulatilaka */
function strProperCase(object, mValidationType) {
    
    /* 
     * object           : The Textbox Object
     * mValidationType  : Should The Textbox Contain Numbers
     *                      Allowed Values ('no_numbers' - Textbox Should Not Contain Numbers)
     */
    
    
    var elem = object.value;
    
    /* Check If The Value Contain Numbers */
    if (mValidationType == 'no_numbers') {
        for (var i = 0; i < elem.length; i++) {
            if ((isNaN(elem.charAt(i)) == false) && (elem.charAt(i) != ' ')) {
                alert('Value is invalid because it contain numbers.');
                setTimeout('document.forms[0].elements[\'' + object.name + '\'].select()', 1);
                return false;
            }
        }
    }
    
    object.value = elem.toLowerCase().replace(/^(.)|\s(.)/g,
        function($1) { return $1.toUpperCase(); });
}
// function strProperCase(object) {
// var elem = object.value;
// object.value = elem.toLowerCase().replace(/^(.)|\s(.)/g,
// function($1) { return $1.toUpperCase(); });
// }


/* Added By Samitha Kulatilaka */
/* Year Validation */
function validateYear(object) {
    
    /* 
     * object           : The Year Textbox Object
     */
    
    
    var mMinYear = 1920;
    var mMaxYear = 2020;
    
    if ((object.value.length > 0) && ((isNaN(object.value) == true) || (object.value < mMinYear) || (object.value > mMaxYear))) {
        alert('Value should be between ' + mMinYear + ' and ' + mMaxYear + '.');
        var m_object_name = object.name;
        setTimeout('document.forms[0].elements[\'' + m_object_name + '\'].select()', 1);
    }
}


/* Added By Samitha Kulatilaka */
/* NIC Validation */
function validateNIC(object) {
    
    /* 
     * object           : The NIC Textbox Object
     */
    
    
    var m_expression = new RegExp("[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][VvXx]");
    var m_value = object.value;
    if ((object.value.length > 0) && ((object.value.length != 10) || (m_expression.test(m_value) == false))) {
        alert('NIC is invalid.');
        var m_object_name = object.name;
        setTimeout('document.forms[0].elements[\'' + m_object_name + '\'].select()', 1);
		return false;
    }
    else {
        object.value = m_value.toUpperCase();
		return true;
    }
}


/* Added By Samitha Kulatilaka */
/* Phone Number Validation */
function validatePhoneNumber(object, type) {
    
    /* 
     * object           : The Phone Number Textbox Object
     * type             : The Phone Type As A String
     */
    
    
    var m_expression_10 = new RegExp("[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]");
    var m_expression_13 = new RegExp("[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]");
    var m_expression_14 = new RegExp("[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]");
    
    var m_value = object.value;
    if ((object.value.length > 0) && (object.value != '0094') && (object.value.length == 13) && (m_expression_13.test(m_value) == true)) {
        alert('Please re-check the ' + type.toLowerCase() + ' number.');
        // var m_object_name = object.name;
        // setTimeout('document.forms[0].elements[\'' + m_object_name + '\'].select()', 1);
    }
    else if ((object.value.length > 0) && (object.value != '0094') && ((object.value.length != 14) || (m_expression_10.test(m_value) == false))) {
        alert(type + ' number is invalid.');
        var m_object_name = object.name;
				object.value='';
        setTimeout('document.forms[0].elements[\'' + m_object_name + '\'].focus()', 1);
    }
    
    // if (object.value.length > 0) {
        // alert(object.maxlength);
        // // if ((object.value.length == 10) && (m_expression_10.test(m_value) == false)) {
            // // alert(type + ' number is invalid.');
            // // var m_object_name = object.name;
            // // setTimeout('document.forms[0].elements[\'' + m_object_name + '\'].select()', 1);
        // // }
        // // else if (((object.value.length >= 10) || (object.value.length < 14) || (m_expression_14.test(m_value) == false))) {
            // // alert(type + ' number is invalid.');
            // // var m_object_name = object.name;
            // // setTimeout('document.forms[0].elements[\'' + m_object_name + '\'].select()', 1);
        // // }
    // }
}


/* Added By Samitha Kulatilaka */
/* E-Mail Validation */
function validateEmail(object) {
    
    /* 
     * object           : The E-Mail Textbox Object
     */
    
    
    var filter = /^([a-zA-Z0-9_.-])+@(([a-zA-Z0-9-])+.)+([a-zA-Z0-9]{2,4})+$/;
    if ((object.value.length > 0) && (filter.test(object.value) == false)) {
        alert('E-mail address is invalid.');
        setTimeout('document.forms[0].elements[\'' + object.name + '\'].select()', 1);
    }
}


/* Added By Samitha Kulatilaka */
/* URL Validation */
function validateUrl(object) {
    
    /* 
     * object           : The Url Textbox Object
     */
    
    
    var filter = /(ftp|http|https):\/\/(\w+:{0,1}\w*@)?(\S+)(:[0-9]+)?(\/|\/([\w#!:.?+=&%@!\-\/]))?/
    if ((object.value.length > 0) && (filter.test(object.value) == false)) {
        alert('Url is invalid.');
        setTimeout('document.forms[0].elements[\'' + object.name + '\'].select()', 1);
    }
}



/* Added By Samitha Kulatilaka */
/* Insert A New Line Character For The Textarea Fields */
function replaceTextAreaPattern(m_value) {
    var m_return_string = m_value.replace(/%0D%0A/g, '\n');
    return m_return_string;
}


/* Added By Samitha Kulatilaka */
/* Set The Initials For Other Names */
function setInitials(other_names, initials) {
    
    /* 
     * other_names      : The Other Names Textbox Object
     * initials         : The Initials Textbox Object
     */
    
    
    var array_name = new Array();
    var i = 0;
    var m_other_names = "";
    var m_initails = "";
    var m_val = "";
    var m_expression = new RegExp("[A-Z]");
    var m_bool_string_error = false;
    
    if (other_names.value.length > 0) {
        m_other_names = other_names.value;
        m_other_names = m_other_names.replace(/\s+/g, ' ');             /* Remove Multiple Spaces Between Words */
        m_other_names = m_other_names.toUpperCase();
        
        array_name = m_other_names.split(' ', m_other_names.length);
        while (i < array_name.length) {
            m_val = array_name[i].charAt(0);
            if (m_val != ' ') {
                if (m_expression.test(m_val) == false) {                /* Error In Other Names (Must Be Only Letters For 1st Letter Of A Name) */
                    m_bool_string_error = true;
                    break;
                }
                m_initails = m_initails + m_val + "." + " ";
                i = i + 1;
            }
        }
        
        if (m_bool_string_error == false) {
            initials.value = m_initails;
            strTrim(initials);
        }
        else if (m_bool_string_error == true) {
            alert('Please enter only alphabetical characters.');
            other_names.focus();
        }
    }
    else if (other_names.value.length == 0) {                           /* Clear The Two Fields */
        other_names.value = "";
        initials.value = "";
    }
}


/* Added By Samitha Kulatilaka */
/* Clears The 'NULL' Strings In HTML Field Values */
function handle_null() {
    for (var i = 0; i < document.forms[0].elements.length; i++ ) {
        if (((document.forms[0].elements[i].type == "text") || (document.forms[0].elements[i].type == "textarea") || (document.forms[0].elements[i].type == "hidden")) && (document.forms[0].elements[i].value.length == 4) && ((document.forms[0].elements[i].value == "NULL") || (document.forms[0].elements[i].value == "null"))) {
            document.forms[0].elements[i].value = "";
        }
        else if (((document.forms[0].elements[i].type == "text") || (document.forms[0].elements[i].type == "textarea") || (document.forms[0].elements[i].type == "hidden")) && (document.forms[0].elements[i].value.length == 1) && (document.forms[0].elements[i].value == "-")) {
            document.forms[0].elements[i].value = "";
        }
    }
}


/* Added By Samitha Kulatilaka On 2010-04-02 */
/* Opens The File Upload Popup */
function UploadFileOpen(screen_name, file_upload_type, hidden_object, text_object) {
    
    /* 
     * screen_name      : The Name of The Screen Where The File Is Uploaded
     * file_upload_type : The Type of The Textbox Where The File Is Uploaded
     * hidden_object    : The Html Object Where The Actual File Reference Number Is Stored
     * text_object      : The Html Object Where The Client File Path Is Stored
     */
    
    
    var m_hidden_field_name = hidden_object.name;
    var m_text_field_name   = text_object.name;
    
    m_url = servlet_client_url + ":" + client_t3_port + "/" + client_name + "FA_CO_File_Upload_display?screen_name=" + screen_name + "&file_upload_type=" + file_upload_type + "&hidden_field_name=" + m_hidden_field_name + "&text_field_name=" + m_text_field_name;
    window.open(m_url, 'm_popup', 'left = 0, top=0,toolbar=0,location=0,directories=0,status=0,menuBar=0,scrollBars=1,resizable=1,width=640px, height=480px');
}


/* Added By Samitha Kulatilaka On 2010-04-16 */
/* Date Validation */
function checkMonthLength1(mObjectDay, mObjectMonth, mObjectYear, mObjectFullDate, mTriggerObject, mNullable, mSysdate, mDateRangeType) {
    
    /* 
     * mObjectDay       : The Day Textbox Object
     * mObjectMonth     : The Month Textbox Object
     * mObjectYear      : The Year Textbox Object
     * mObjectFullDate  : The Hidden Field Object (i.e. The Object Where The Calendar Icon Is Linked)
     * mTriggerObject   : The Event Triggering Textbox Object
     *                      Allowed Values ('D' - Day Textbox, 'M' - Month Textbox, 'Y' - Year Textbox)
     * mNullable        : Is The Date Textboxes Nullable?
     *                      Allowed Values ('N' - Not Null)
     * mSysdate         : Today In [DD-MM-YYYY] Format - As A String
     * mDateRangeType   : How The Allowable Date Ranges Should Be Handled
     *                      Allowed Values ('no future' - Future Dates Are Not Allowed
     *                                      'no past' - Past Dates Are Not Allowed (i.e. No Backdates)
     *                                      'today' - Only The Sysdate Is Allowed (i.e. Only Today))
     */

    
    /* Trim The Data */
    mObjectDay.value        = mObjectDay.value.replace(/^\s+|\s+$/g, '');
    mObjectMonth.value      = mObjectMonth.value.replace(/^\s+|\s+$/g, '');
    mObjectYear.value       = mObjectYear.value.replace(/^\s+|\s+$/g, '');
    
    /* Added By Samitha Kulatilaka On 2010-06-01 */
    /* Remove The Dot(.) From The String */
    mObjectDay.value        = characterReplace(mObjectDay.value, ".", "");
    mObjectMonth.value      = characterReplace(mObjectMonth.value, ".", "");
    mObjectYear.value       = characterReplace(mObjectYear.value, ".", "");
    
    var mDay                = mObjectDay.value;
    var mMonth              = mObjectMonth.value;
    var mYear               = mObjectYear.value;
    
    var mIsDayValidated     = false;
    var mIsMonthValidated   = false;
    var mIsYearValidated    = false;
    var mIsDateValidated    = false;
    
    var mMinYear            = 1920;
    var mMaxYear            = 2020;
    
    if (mSysdate != null) {
        /* Sysdate Manipulation */
        var mSysdateDay         = mSysdate.substring(0, 2);
        var mSysdateMonth       = mSysdate.substring(3, 5);
        var mSysdateYear        = mSysdate.substring(6);
        var mSysdateObject      = new Date();
        
        var mDateObject         = new Date();
    }
    
    
    if (mTriggerObject == 'D') {
    // if ((mTriggerObject == 'D') && (mObjectFullDate.value.length != 0)) {
        if (mDay.length > 0) {
            if (isNaN(mDay) || (mDay < 1) || (mDay > 31)) {
                mObjectDay.value = '';
                mObjectFullDate.value = '';
                setTimeout('document.forms[0].elements[\'' + mObjectDay.name + '\'].focus()', 10);
                alert('Day should be between 1 and 31.');
                return false;
            }
            else if (mDay.length == 1) {
                mObjectDay.value = '0' + mDay;              /* Prefix Zero To Single Digit Values */
            }
            mIsDayValidated = true;
            
            /* Month Validation Within Day Validation*/
            if (mMonth.length > 0) {
                if (isNaN(mMonth) || (mMonth < 1) || (mMonth > 12)) {
                    mObjectMonth.value = '';
                }
                else if (mMonth.length == 1) {
                    mObjectMonth.value = '0' + mMonth;      /* Prefix Zero To Single Digit Values */
                    mIsMonthValidated = true;
                }
                else if (mMonth.length == 2) {
                    mIsMonthValidated = true;
                }
            }
            
            /* Year Validation Within Day Validation*/
            if (mYear.length > 0) {
                if (isNaN(mYear) || (mYear < mMinYear) || (mYear > mMaxYear)) {
                    mObjectYear.value = '';
                }
                else {
                    mIsYearValidated = true;
                }
            }
        }
        /* Else If Clause Added By Samitha Kulatilaka On 2010-06-01 */
        else if ((mDay.length == 0) && (mNullable == 'N')) {
                mObjectDay.value = '';
                mObjectFullDate.value = '';
                setTimeout('document.forms[0].elements[\'' + mObjectDay.name + '\'].focus()', 10);
                alert('Day should be between 1 and 31.');
                return false;
        }
    }
    
    else if (mTriggerObject == 'M') {
    // else if ((mTriggerObject == 'M') && (mObjectFullDate.value.length != 0)) {
        if (mMonth.length > 0) {
            if (isNaN(mMonth) || (mMonth < 1) || (mMonth > 12)) {
                mObjectMonth.value = '';
                mObjectFullDate.value = '';
                setTimeout('document.forms[0].elements[\'' + mObjectMonth.name + '\'].focus()', 10);
                alert('Month should be between 1 and 12.');
                return false;
            }
            else if (mMonth.length == 1) {
                mObjectMonth.value = '0' + mMonth;          /* Prefix Zero To Single Digit Values */
            }
            mIsMonthValidated = true;
            
            /* Day Validation Within Month Validation*/
            if (mDay.length > 0) {
                if (isNaN(mDay) || (mDay < 1) || (mDay > 31)) {
                    mObjectDay.value = '';
                }
                else if (mDay.length == 1) {
                    mObjectDay.value = '0' + mDay;          /* Prefix Zero To Single Digit Values */
                    mIsDayValidated = true;
                }
                else if (mDay.length == 2) {
                    mIsDayValidated = true;
                }
            }
            
            /* Year Validation Within Month Validation*/
            if (mYear.length > 0) {
                if (isNaN(mYear) || (mYear < mMinYear) || (mYear > mMaxYear)) {
                    mObjectYear.value = '';
                }
                else {
                    mIsYearValidated = true;
                }
            }
        }
        /* Else If Clause Added By Samitha Kulatilaka On 2010-06-01 */
        else if ((mMonth.length == 0) && (mNullable == 'N')) {
                mObjectMonth.value = '';
                mObjectFullDate.value = '';
                setTimeout('document.forms[0].elements[\'' + mObjectMonth.name + '\'].focus()', 10);
                alert('Month should be between 1 and 12.');
                return false;
        }
    }
    
    else if (mTriggerObject == 'Y') {
    // else if ((mTriggerObject == 'Y') && (mObjectFullDate.value.length != 0)) {
        if (mYear.length > 0) {
            if (isNaN(mYear) || (mYear < mMinYear) || (mYear > mMaxYear)) {
                mObjectYear.value = '';
                mObjectFullDate.value = '';
                setTimeout('document.forms[0].elements[\'' + mObjectYear.name + '\'].focus()', 10);
                alert('Year should be between ' + mMinYear + ' and ' + mMaxYear + '.');
                return false;
            }
            else {
                mIsYearValidated = true;
            }
            
            /* Day Validation Within Year Validation*/
            if (mDay.length > 0) {
                if (isNaN(mDay) || (mDay < 1) || (mDay > 31)) {
                    mObjectDay.value = '';
                }
                else if (mDay.length == 1) {
                    mObjectDay.value = '0' + mDay;          /* Prefix Zero To Single Digit Values */
                    mIsDayValidated = true;
                }
                else if (mDay.length == 2) {
                    mIsDayValidated = true;
                }
            }
            
            /* Month Validation Within Year Validation*/
            if (mMonth.length > 0) {
                if (isNaN(mMonth) || (mMonth < 1) || (mMonth > 12)) {
                    mObjectMonth.value = '';
                }
                else if (mMonth.length == 1) {
                    mObjectMonth.value = '0' + mMonth;      /* Prefix Zero To Single Digit Values */
                    mIsMonthValidated = true;
                }
                else if (mMonth.length == 2) {
                    mIsMonthValidated = true;
                }
            }
        }
        /* Else If Clause Added By Samitha Kulatilaka On 2010-06-01 */
        else if ((mYear.length == 0) && (mNullable == 'N')) {
                mObjectYear.value = '';
                mObjectFullDate.value = '';
                setTimeout('document.forms[0].elements[\'' + mObjectYear.name + '\'].focus()', 10);
                alert('Year should be between ' + mMinYear + ' and ' + mMaxYear + '.');
                return false;
        }
    }
    
    
    if (mIsDayValidated && mIsMonthValidated && mIsYearValidated) {
        
        mIsDateValidated = true;
        
        /* Validation 2nd Round */
        /* Month of February Validation */
        if (mMonth == 2) {
            if (mYear % 4 == 0) {
                if ((mDay == 30) || (mDay == 31)) {
                    mObjectDay.value = '';
                    mObjectFullDate.value = '';
                    setTimeout('document.forms[0].elements[\'' + mObjectDay.name + '\'].focus()', 10);
                    alert('February of ' + mYear + ' has only 29 days.');
                    return false;
                }
            }
            else if (mYear % 4 > 0) {
                if ((mDay == 29) || (mDay == 30) || (mDay == 31)) {
                    mObjectDay.value = '';
                    mObjectFullDate.value = '';
                    setTimeout('document.forms[0].elements[\'' + mObjectDay.name + '\'].focus()', 10);
                    alert('February of ' + mYear + ' has only 28 days.');
                    return false;
                }
            }
        }
        /* Validation of Months Which Has Only 30 Days */
        else if ((mMonth == 4) || (mMonth == 6) || (mMonth == 9) || (mMonth == 11)) {
            if (mDay == 31) {
                mObjectDay.value = '';
                mObjectFullDate.value = '';
                setTimeout('document.forms[0].elements[\'' + mObjectDay.name + '\'].focus()', 10);
                if (mMonth == 4) {
                    alert('April has only 30 days.');
                }
                else if (mMonth == 6) {
                    alert('June has only 30 days.');
                }
                else if (mMonth == 9) {
                    alert('September has only 30 days.');
                }
                else if (mMonth == 11) {
                    alert('November has only 30 days.');
                }
                return false;
            }
        }
        
        // /* Set Completed Date To Hidden Variable */
        // if (mIsDateValidated) {
            // mObjectFullDate.value = mObjectDay.value + '-' + mObjectMonth.value + '-' + mObjectYear.value;
            // return true;
        // }
        
    }
    
    
    /* If Any of The Date Fileds Are Empty */
    if ((mDay.length == 0) || (mMonth.length == 0) || (mYear.length == 0)) {
        /* For Not Null Fields */
        if (mNullable == 'N') {
            if ((mTriggerObject == 'D') && (mDay.length == 0)) {
                mObjectFullDate.value = '';
                setTimeout('document.forms[0].elements[\'' + mObjectDay.name + '\'].focus()', 10);
                alert('Day cannot be blank.');
                return false;
            }
            if ((mTriggerObject == 'M') && (mMonth.length == 0)) {
                mObjectFullDate.value = '';
                setTimeout('document.forms[0].elements[\'' + mObjectMonth.name + '\'].focus()', 10);
                alert('Month cannot be blank.');
                return false;
            }
            if ((mTriggerObject == 'Y') && (mYear.length == 0)) {
                mObjectFullDate.value = '';
                setTimeout('document.forms[0].elements[\'' + mObjectYear.name + '\'].focus()', 10);
                alert('Year cannot be blank.');
                return false;
            }
        }
        /* For Nullable Fields */
        else {
            mObjectFullDate.value = '';                         /* Reset The Hidden Variable */
            return false;
        }
    }
    
    
    /* Date Range Validations */
    // if (mSysdate != null) {
    // if ((mSysdate != null) && (mObjectFullDate.value.length > 0)) {
    // if ((mSysdate != null) && (mDay.length > 0) && (mMonth.length > 0) && (mYear.length > 0)) {
    if ((mSysdate != null) && (mIsDateValidated == true)) {
        mSysdateObject.setFullYear(mSysdateYear, mSysdateMonth - 1, mSysdateDay);
        mDateObject.setFullYear(mYear, mMonth - 1, mDay);
        // alert(mDateObject.valueOf());
        
        /* No Future Dates Allowed */
        if (mDateRangeType == 'no future') {
            if (mDateObject > mSysdateObject) {
                mObjectFullDate.value = '';
                
                if (mTriggerObject == 'D') {
                    mObjectDay.value = '';
                    setTimeout('document.forms[0].elements[\'' + mObjectDay.name + '\'].focus()', 10);
                }
                else if (mTriggerObject == 'M') {
                    mObjectMonth.value = '';
                    setTimeout('document.forms[0].elements[\'' + mObjectMonth.name + '\'].focus()', 10);
                }
                else if (mTriggerObject == 'Y') {
                    mObjectYear.value = '';
                    setTimeout('document.forms[0].elements[\'' + mObjectYear.name + '\'].focus()', 10);
                }
                
                alert('Date cannot be in the future.');
                return false;
            }
        }
        
        /* No Past Dates Allowed */
        else if (mDateRangeType == 'no past') {
            if (mDateObject < mSysdateObject) {
                mObjectFullDate.value = '';
                
                if (mTriggerObject == 'D') {
                    mObjectDay.value = '';
                    setTimeout('document.forms[0].elements[\'' + mObjectDay.name + '\'].focus()', 10);
                }
                else if (mTriggerObject == 'M') {
                    mObjectMonth.value = '';
                    setTimeout('document.forms[0].elements[\'' + mObjectMonth.name + '\'].focus()', 10);
                }
                else if (mTriggerObject == 'Y') {
                    mObjectYear.value = '';
                    setTimeout('document.forms[0].elements[\'' + mObjectYear.name + '\'].focus()', 10);
                }
                
                alert('Date cannot be in the past.');
                return false;
            }
        }
    }
    
    
    /* Set Completed Date To Hidden Variable */
    if (mIsDateValidated) {
        mObjectFullDate.value = mObjectDay.value + '-' + mObjectMonth.value + '-' + mObjectYear.value;
        return true;
    }
    
}


/* Added By Samitha Kulatilaka On 2010-06-02 */
/* Date Validation */
function checkTwoDates(mObjectDay1, mObjectMonth1, mObjectYear1, mObjectFullDate1, mObjectDay2, mObjectMonth2, mObjectYear2, mObjectFullDate2, mTriggerObjectSet, mTriggerObject, mTriggerObjectType, mOperator, mLabel_1, mLabel_2 ,mNumberOfDays) {
    
    /* 
     * mObjectDay1          : The Day Textbox Object For Date Set 1
     * mObjectMonth1        : The Month Textbox Object For Date Set 1
     * mObjectYear1         : The Year Textbox Object For Date Set 1
     * mObjectFullDate1     : The Hidden Field Object For Date Set 1 (i.e. The Object Where The Calendar Icon Is Linked)
     * mObjectDay2          : The Day Textbox Object For Date Set 2
     * mObjectMonth2        : The Month Textbox Object For Date Set 2
     * mObjectYear2         : The Year Textbox Object For Date Set 2
     * mObjectFullDate2     : The Hidden Field Object For Date Set 2 (i.e. The Object Where The Calendar Icon Is Linked)
     * mTriggerObjectSet    : The Event Triggering Textbox Object Set
     *                          Allowed Values (1 - Set 1, 2 - Set 2)
     * mTriggerObject       : The Event Triggering Textbox Object
     * mTriggerObjectType   : The Event Triggering Textbox Object Type
     *                          Allowed Values ('D' - Day Textbox, 'M' - Month Textbox, 'Y' - Year Textbox)
     * mOperator            : How The Allowable Date Ranges Should Be Handled
     *                          Allowed Values ('<=', '<', '=', '>', '>=')
     *                              The Operators Should Be Self Explanatory
     * mLabel_1             : The Sting Label of Date Object 1
     * mLabel_2             : The Sting Label of Date Object 2
     * mNumberOfDays        : The Maximum Difference Of The Two Dates [In Days]
     */
    
    
    var mDateObject1 = "";
    var mDateObject2 = "";
    var mBoolIsDateObject1 = false;
    var mBoolIsDateObject2 = false;
    
    // alert(mObjectFullDate1.value.length);
    // alert(mObjectFullDate2.value.length);
    
    /* Create The Date Objects */
    if (mObjectFullDate1.value.length == 10) {
        mDateObject1 = new Date(mObjectFullDate1.value.substring(6), mObjectFullDate1.value.substring(3, 5) - 1, mObjectFullDate1.value.substring(0, 2));
        mBoolIsDateObject1 = true;
    }
    
    if (mObjectFullDate2.value.length == 10) {
        mDateObject2 = new Date(mObjectFullDate2.value.substring(6), mObjectFullDate2.value.substring(3, 5) - 1, mObjectFullDate2.value.substring(0, 2));
        mBoolIsDateObject2 = true;
    }
    
    
    if ((mBoolIsDateObject1 == true) && (mBoolIsDateObject2 == true)) {
        if (mOperator == '<=') {
            // alert(mDateObject2 - mDateObject1);
            if (mDateObject1 <= mDateObject2) {
                /* If Clause Added By Samitha Kulatilaka On 2010-07-28 */
                if ((mNumberOfDays != null) && ((mDateObject2 - mDateObject1) > (1000 * 60 * 60 * 24 * mNumberOfDays))) {
                    // alert('days are too different');
                    
                    if (mTriggerObjectSet == 1) {
                        mObjectFullDate1.value = '';
                    }
                    else if (mTriggerObjectSet == 2) {
                        mObjectFullDate2.value = '';
                    }
                    mTriggerObject.value = '';
                    
                    alert('The difference between ' + mLabel_2 + ' and ' + mLabel_1 + ' should not be larger than ' + mNumberOfDays + ' days.');
                    setTimeout('document.forms[0].elements[\'' + mTriggerObject.name + '\'].focus()', 10);
                    return false;
                }
            }
            else {
                if (mTriggerObjectSet == 1) {
                    mObjectFullDate1.value = '';
                }
                else if (mTriggerObjectSet == 2) {
                    mObjectFullDate2.value = '';
                }
                
                mTriggerObject.value = '';
                alert(mLabel_2 + ' should not be a date earlier than ' + mLabel_1 + '.');
                setTimeout('document.forms[0].elements[\'' + mTriggerObject.name + '\'].focus()', 10);
                return false;
            }
        }
        
        if (mOperator == '<') {
            if (mDateObject1 < mDateObject2) {
            }
            else {
                alert('Error 2.');
                return false;
            
            }
        }
        
        if (mOperator == '=') {
            if (mDateObject1 = mDateObject2) {
            }
            else {
                alert('Error 3.');
                return false;
            
            }
        }
        
        if (mOperator == '>') {
            if (mDateObject1 > mDateObject2) {
            }
            else {
                alert('Error 4.');
                return false;
            
            }
        }
        
        if (mOperator == '>=') {
            if (mDateObject1 >= mDateObject2) {
            }
            else {
                alert('Error 5.');
                return false;
            
            }
        }
    }
    
    return true;
}


/* Added By Samitha Kulatilaka On 2010-07-01 */
function showPointerCursor(m_object) {
    // if (document.forms[0].elements[m_object.name].disabled == false) {
    //     document.forms[0].elements[m_object.name].style.cursor = 'pointer';
    // }
    // /* Test Code To See Whether The onMouseOver Event Runs When The Object Is Disabled */
    // else if (document.forms[0].elements[m_object.name].disabled == true) {
    //     document.forms[0].elements[m_object.name].style.cursor = 'wait';
    // }
}


/* Created By Chatura Jayawardena */
function limitText(m_limit_field, m_limit_count, m_max_characters) {
    var int_new_length = 0;
    
    int_new_length = m_limit_field.value.length;
    
    if (m_limit_field.value.length > m_max_characters) {
        m_limit_field.value = m_limit_field.value.substring(0, m_max_characters);
    }
    else {
        /* If Clause Added By Samitha Kulatilaka On 2010-08-13 */
        if (m_limit_count != null) {
            m_limit_count.value = m_max_characters - m_limit_field.value.length;
        }
    }
}


function imposeMaxLength(Object, MaxLen) {
    return (Object.value.length <= MaxLen);
}


// Added By Disnaka Jayasuriya on 2010-06-28 for Global FollowUp Process
function setFollowUpData(data){
	    document.forms[0].hid_curr_action.value=data.curr_action;
		document.forms[0].hid_next_action.value=data.next_action;
		document.forms[0].hid_action_be_taken_by.value=data.action_set_for;
		document.forms[0].hid_priority.value=data.priority;
		document.forms[0].hid_due_date.value=data.due_date;
		document.forms[0].hid_comment.value=data.comment;
		document.forms[0].hid_screen_name.value=data.screen;
		document.forms[0].hid_division.value=data.division;
		document.forms[0].hid_sub_division.value=data.sub_division;
	    document.forms[0].hid_action_screen.value=data.action_screen;
	    saveData();
}


function initFollowUpDialog(){
	$("#divId").dialog({
			bgiframe: true,
			autoOpen: false,
			modal: true,
			title: 'NetAsset Global Followup',
			buttons: {
				'Submit': function() {
					document.getElementById('modalIframeId').contentWindow.setFollowUpData();
					$(this).dialog('close');
				},
				Cancel: function() {
					$(this).dialog('close');
				}
			},
			close: function() {
			},
			height: 500,
			width: 550
		});
}

function initGlobalDialog(){
	$("#divId2").dialog({
			bgiframe: true,
			autoOpen: false,
			modal: true,
			title: 'NetAsset Global Client',
			buttons: {
				//'Submit': function() {
				//	document.getElementById('modalIframeId2').contentWindow.setFollowUpData();
				//	$(this).dialog('close');
				//},
				//Cancel: function() {
				//	$(this).dialog('close');
				//}
			},
			close: function() {
			},
			height: 300,
			width: 750
		});
}

function initGlobalDetailsDialog(){
	$("#divId3").dialog({
			bgiframe: true,
			autoOpen: false,
			modal: true,
			title: 'NetAsset Global Client Details',
			buttons: {
				//'Submit': function() {
				//	document.getElementById('modalIframeId2').contentWindow.setFollowUpData();
				//	$(this).dialog('close');
				//},
				//Cancel: function() {
				//	$(this).dialog('close');
				//}
			},
			close: function() {
			},
			height: 700,
			width: 750
		});
}




function sleep(milliseconds) {
    var start = new Date().getTime();
    for (var i = 0; i < 1e7; i++) {
        if ((new Date().getTime() - start) > milliseconds){
            break;
        }
    }
}


function format_noobjectNoError(value, passed_decimalsize, m_alert, m_brackets) {
    
    /* Trim The data */
    // var new_value = value.replace(/^\s+|\s+$/g, '');
    new_value = value;
    new_value = unformat_noobject(new_value);
    // alert('value : ' + new_value + '     length : ' + new_value.length);
    
    /* Check Whether The Value Is Actually A Number */
    if (isNaN(new_value)) {
        if (m_alert != 'N') {
            //alert("You have typed an incorrect charactor as a number.");
            return value;
        }
    }
    
    /* Check Whether The Number Is Negative */
    var is_number_negative = false;
    if (new_value < 0) {
        is_number_negative = true;
        new_value = new_value.slice(1);
    }
    
    /* Create A Number Object And Round It As Necessary */
    var number_object = new Number(new_value);
    number_object = number_object.toFixed(passed_decimalsize);
    
    
    /* Format The Number */
    new_value = number_object.toString();
    
    var index_of_dot = new_value.indexOf('.');
    /* If Clauses Added By Samitha Kulatilaka On 2010-08-10 */
    var integer_part = '';
    var fraction_part = '';
    if (index_of_dot != -1) {
        integer_part = new_value.substring(0, index_of_dot);
        fraction_part = new_value.substring(index_of_dot + 1);
    }
    else {
        integer_part = new_value;
    }
    
    new_value = '';
    var j = 0;
    if (integer_part.length > 3) {
        for (var i = integer_part.length - 1; i >= 0; i--) {
            j = j + 1;
            new_value = integer_part.charAt(i) + new_value;
            if (j == 3) {
                /* Only Prefix A Comma If There Are More Numbers To The Left */
                if (i > 0) {
                    new_value = ',' + new_value;
                }
                j = 0;
            }
        }
    }
    /* Commas Are Not Necessary For This If Clause */
    else {
        new_value = integer_part;
    }
    
    /* If Clause Added By Samitha Kulatilaka On 2010-08-10 */
    if (index_of_dot != -1) {
        new_value = new_value.concat('.', fraction_part);
    }
    
    if (is_number_negative == true) {
        new_value = '-' + new_value;
    }
    
    
    /* Put Brackets For Negative Numbers */
    if (m_brackets == 'BRACKETS') {
        // alert(new_value);
        if (new_value.charAt(0) == '-') {
            new_value = new_value.slice(1);
            new_value = '(' + new_value + ')';
        }
    }
    
    return new_value;

}

