//txpmenu3
var menu_preddfontcol = "#AA0000";//tpressedFontColor
var menu_img_path = "https://dev-lakdl.sasianet.com/images/";//tpathPrefix_img 

var tlevelDX = 20;
var ttoggleMode = 1;

var texpanded = 0;
var tcloseExpanded   = 1;
var tcloseExpandedXP = 1;

var tblankImage      = "https://dev-lakdl.sasianet.com/images/blank.gif";
var tmenuWidth       = 230;
var tmenuHeight      = 0;

var tabsolute        = 1;
var tleft            = 20;
var ttop             = 20;

var tfloatable       = 0;
var tfloatIterations = 10;

var tmoveable        = 0;
var tmoveImage       = "https://dev-lakdl.sasianet.com/images/movepic.gif";
var tmoveImageHeight = 12;

var tfontStyle       = "normal 9pt Tahoma"; // font size 8 change in to 9 by Prabash on 28-02-2012
var tfontColor       = ["#000000","#000000"];
var tfontDecoration  = ["none","underline"];

var titemBackColor   = ["#FFFFFF","#FFFFFF"];
var titemAlign       = "left";
var titemBackImage   = ["",""];
var titemCursor      = "pointer";
var titemHeight      = 22;
var titemTarget      = "_blank";

var ticonWidth       = 21;
var ticonHeight      = 15;
var ticonAlign       = "left";

var tmenuBackImage   = "";
var tmenuBackColor   = "";
var tmenuBorderColor = "#FFFFFF";
var tmenuBorderStyle = "solid";
var tmenuBorderWidth = 1;

var texpandBtn       =["https://dev-lakdl.sasianet.com/images/expandbtn2.gif","https://dev-lakdl.sasianet.com/images/expandbtn2.gif","https://dev-lakdl.sasianet.com/images/collapsebtn2.gif"];
var texpandBtnW      = 9;
var texpandBtnH      = 9;
var texpandBtnAlign  = "left"

var tpoints       = 2;
var tpointsImage  = "";
var tpointsVImage = "";
var tpointsCImage = "";

// XP-Style Parameters
var tXPStyle = 2;
var tXPIterations = 8;                  // expand/collapse speed
var tXPTitleTopBackColor = "";
var tXPTitleBackColor    = "#A3B2CC";
var tXPTitleLeft    = "https://dev-lakdl.sasianet.com/images/xptitleleft_o.gif";
var tXPTitleLeftWidth = 4;
var tXPExpandBtn    = ["https://dev-lakdl.sasianet.com/images/xpexpand1_o.gif","https://dev-lakdl.sasianet.com/images/xpexpand1_o.gif","https://dev-lakdl.sasianet.com/images/xpcollapse1_o.gif","https://dev-lakdl.sasianet.com/images/xpcollapse1_o.gif"];
var tXPTitleBackImg = "https://dev-lakdl.sasianet.com/images/xptitle_o.gif";

var tXPBtnWidth  = 25;
var tXPBtnHeight = 23;

var tXPIconWidth  = 0;
var tXPIconHeight = 0;

var tXPFilter=0 ;

var tXPBorderWidth = 1;
var tXPBorderColor = '#FFFFFF';


var tstyles =
[
    ["tfontStyle=bold 8pt Tahoma","tfontColor=#FFFFFF,#E0E7B8", "tfontDecoration=none,none"],
    ["tfontStyle=bold 8pt Tahoma","tfontColor=#56662D,#72921D", "tfontDecoration=none,none"],
    ["tfontDecoration=none,none"],
    ["tfontStyle=bold 8pt Tahoma","tfontColor=#444444,#5555FF"],
];

var tXPStyles =
[
    ["tXPTitleBackColor=#E2E9BC", "tXPExpandBtn=xpexpand3_o.gif,xpexpand4_o.gif,xpcollapse3_o.gif,xpcollapse4_o.gif", "tXPTitleBackImg=xptitle2_o.gif"]
];

initialize_menu_parameters();//apy_tmenuInit
