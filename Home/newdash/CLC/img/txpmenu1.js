var tpressedFontColor = "#AA0000";

var tpathPrefix_img = "img/";

var tlevelDX = 20;
var ttoggleMode = 1;

var texpanded = 0;
var tcloseExpanded   = 0;
var tcloseExpandedXP = 0;

var tblankImage      = "img/blank.gif";
var tmenuWidth       = "230px";
var tmenuHeight      = "auto";

var tabsolute        = 1;
var tleft            = 20;
var ttop             = 80;

var tfloatable       = 0;
var tfloatIterations = 10;

var tmoveable        = 0;
var tmoveImage       = "img/movepic.gif";
var tmoveImageHeight = 12;

var tfontStyle       = "normal 8pt Tahoma";
var tfontColor       = ["#215DC6","#428EFF"];
var tfontDecoration  = ["none","underline"];

var titemBackColor   = ["#D6DFF7","#D6DFF7"];
var titemAlign       = "left";
var titemBackImage   = ["blank.gif","blank.gif"];
var titemCursor      = "pointer";
var titemHeight      = 22;
var titemTarget      = "_self";

var ticonWidth       = 21;
var ticonHeight      = 15;
var ticonAlign       = "left";

var tmenuBackImage   = "blank.gif";
var tmenuBackColor   = "";
var tmenuBorderColor = "#FFFFFF";
var tmenuBorderStyle = "solid";
var tmenuBorderWidth = 0;

var texpandBtn       =["expandbtn2.gif","expandbtn2.gif","collapsebtn2.gif"];
var texpandBtnW      = 9;
var texpandBtnH      = 9;
var texpandBtnAlign  = "left"

var tpoints       = 0;
var tpointsImage  = "";
var tpointsVImage = "";
var tpointsCImage = "";

// XP-Style Parameters
var tXPStyle = 1;
var tXPIterations = 10;                  // expand/collapse speed
var tXPTitleBackColor    = "#265BCC";
var tXPExpandBtn    = ["xpexpand1.gif","xpexpand2.gif","xpcollapse1.gif","xpcollapse2.gif"];
var tXPTitleBackImg = "xptitle.gif";

var tXPTitleLeft      = "xptitleleft.gif";
var tXPTitleLeftWidth = 4;

var tXPBtnWidth  = 25;
var tXPBtnHeight = 25;

var tXPIconWidth  = 31;
var tXPIconHeight = 32;

var tXPFilter=1;

var tXPBorderWidth = 1;
var tXPBorderColor = '#FFFFFF';



var tstyles =
[
    ["tfontStyle=bold 8pt Tahoma","titemBackColor=#265BCC,#265BCC","tfontColor=#FFFFFF,#428EFF", "tfontDecoration=none,none"],
    ["tfontStyle=bold 8pt Tahoma","titemBackColor=#265BCC,#265BCC","tfontColor=#215DC6,#428EFF", "tfontDecoration=none,none"],
    ["tfontDecoration=none,none"],
    ["tfontStyle=bold 8pt Tahoma","tfontColor=#444444,#5555FF"],
];

var tXPStyles =
[
    ["tXPTitleBackColor=#D0DAF8", "tXPExpandBtn=xpexpand3.gif,xpexpand4.gif,xpcollapse3.gif,xpcollapse4.gif", "tXPTitleBackImg=xptitle2.gif"]
];

var tmenuItems =
[
    ["+DHTML Tree: XP Style", "", "xpicon1.gif","","", "XP Title Tip",,"0"],
    ["|Home", "http://DHTML-tree.com", "icon1.gif", "icon1o.gif", "", "Home Page Tip","_blank"],
    ["|+Product Info", "", "icon2.gif", "icon2o.gif", "", "Product Info Tip"],
        ["||What's New", "testlink.htm", "iconarr.gif"],
        ["||Features", "testlink.htm", "iconarr.gif"],
        ["||Installation", "testlink.htm", "iconarr.gif"],
        ["||Functions", "testlink.htm", "iconarr.gif"],
        ["||Supported Browsers", "testlink.htm", "iconarr.gif"],
    ["|Samples", "", "icon3.gif", "icon3o.gif", "", "Samples Tip"],
        ["||Sample 1", "testlink.htm", "iconarr.gif"],
        ["||Sample 2", "testlink.htm", "iconarr.gif"],
        ["||Sample 3", "testlink.htm", "iconarr.gif"],
        ["||Sample 4", "testlink.htm", "iconarr.gif"],
        ["||Sample 5", "testlink.htm", "iconarr.gif"],
        ["||Sample 6", "testlink.htm", "iconarr.gif"],
        ["||More Samples", "", "icon3.gif", "icon3o.gif"],
            ["|||New Sample 1", "testlink.htm", "iconarr.gif"],
            ["|||New Sample 2", "testlink.htm", "iconarr.gif"],
            ["|||New Sample 3", "testlink.htm", "iconarr.gif"],
            ["|||New Sample 4", "testlink.htm", "iconarr.gif"],
            ["|||New Sample 5", "testlink.htm", "iconarr.gif"],
    ["|Purchase", "testlink.htm", "icon4.gif", "icon4o.gif", "", "Purchase Tip"],
    ["|Support", "", "icon5.gif", "icon5o.gif", "", "Support Tip"],
        ["||Write Us", "mailto:dhtml@dhtml-menu.com", "iconarr.gif"],

    ["Samples Gallery", "", "","","", "XP Title Tip",,"1","0"],
        ["|Samples Block 1", "", "icon3.gif", "icon3o.gif"],
            ["||New Sample 1", "testlink.htm", "iconarr.gif"],
            ["||New Sample 2", "testlink.htm", "iconarr.gif"],
            ["||New Sample 3", "testlink.htm", "iconarr.gif"],
            ["||New Sample 4", "testlink.htm", "iconarr.gif"],
            ["||New Sample 5", "testlink.htm", "iconarr.gif"],
        ["|Samples Block 2", "", "icon3.gif", "icon3o.gif"],
            ["||New Sample 1", "testlink.htm", "iconarr.gif"],
            ["||New Sample 2", "testlink.htm", "iconarr.gif"],
            ["||New Sample 3", "testlink.htm", "iconarr.gif"],
            ["||New Sample 4", "testlink.htm", "iconarr.gif"],
            ["||New Sample 5", "testlink.htm", "iconarr.gif"],
        ["|Samples Block 3", "", "icon3.gif", "icon3o.gif"],
            ["||New Sample 1", "testlink.htm", "iconarr.gif"],
            ["||New Sample 2", "testlink.htm", "iconarr.gif"],
            ["||New Sample 3", "testlink.htm", "iconarr.gif"],
            ["||New Sample 4", "testlink.htm", "iconarr.gif"],
            ["||New Sample 5", "testlink.htm", "iconarr.gif"],
];



dtree_init();
