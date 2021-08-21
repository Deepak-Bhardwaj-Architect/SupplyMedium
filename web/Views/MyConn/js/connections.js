var currentSelection = "MyConnections";

/* Define the onload function */

function connectionOnload()
{
	$("#my_conn_tab").click( showMyConnContent );
	$("#add_conn_tab").click( showAddConnContent );
	
	$("#conn_tab").click( showConnContent );
	
	$("#pending_conn_tab").click( showPendingConnContent );
	
	$("#new_conn_list").mCustomScrollbar({theme:"dark-thick",scrollInertia:150});
	
	$("#pending_conn_list").mCustomScrollbar({theme:"dark-thick",scrollInertia:150});
	
	$("#my_conn_list").mCustomScrollbar({theme:"dark-thick",scrollInertia:150});

	
	//readTextFile("file:///C:/Users/Anbu/Desktop/file.txt");
	
	
}

/*
function readTextFile(file)
{
    var rawFile = new XMLHttpRequest();
    rawFile.open("GET", file, true);
    rawFile.onreadystatechange = function ()
    {
        if(rawFile.readyState === 4)
        {
            if(rawFile.status === 200 || rawFile.status == 0)
            {
                var allText = rawFile.responseText;
                
                alert(allText);
            }
        }
    };
    rawFile.send(null);
}*/

/* It is used to display the Myconnection content view 
 * while user click the My connection tab
 */

function showMyConnContent()
{
	getMyConnections();
	
	$("#new_conn_container").hide();
	
	$("#add_conn_tab").addClass("graytab");
	
	$("#add_conn_tab").removeClass("orangetab");
	
	
	$("#my_conn_container").show();
	
	$("#my_conn_tab").removeClass("graytab");
	
	$("#my_conn_tab").addClass("orangetab");
	
	currentSelection = "MyConnections";
	
}

/* It is used to display the Add connection content view 
 * while user click the add connection tab
 */

function showAddConnContent()
{
	$("#new_conn_container").show();
	
	$("#add_conn_tab").removeClass("graytab");
	
	$("#add_conn_tab").addClass("orangetab");
	
	
	$("#my_conn_container").hide();
	
	$("#my_conn_tab").addClass("graytab");
	
	$("#my_conn_tab").removeClass("orangetab");
	
	currentSelection = "AddConnections";
}

/* It is used to display the Connection content view 
 * while user click the Connection from myconnection view tab
 */

function showConnContent()
{
	getMyConnections();
	
	$("#pending_conn_container").hide();
	
	$("#pending_conn_tab").removeClass("bluetab");
	
	$("#pending_conn_tab").addClass("lgraytab");
	
	
	$("#conn_container").show();
	
	$("#conn_tab").addClass("bluetab");
	
	$("#conn_tab").removeClass("lgraytab");
	
	currentSelection = "MyConnections";
	
}

/* It is used to display the pending Connection content view 
 * while user click the pending Connection from my connection view tab
 */

function showPendingConnContent()
{
	getPendingConnections();
	
	$("#pending_conn_container").show();
	
	$("#pending_conn_tab").addClass("bluetab");
	
	$("#pending_conn_tab").removeClass("lgraytab");
	
	
	$("#conn_container").hide();
	
	$("#conn_tab").removeClass("bluetab");
	
	$("#conn_tab").addClass("lgraytab");
	
	currentSelection = "PendingConnections";
	
}



$(document).keydown(function(e) // 38-up, 40-down
{ 
	if(e.keyCode == 13)
	{
		if( currentSelection == "AddConnections" )
		{
			searchConnections();
		}
	}
	
    if (e.keyCode == 40) 
    { 
       if( currentSelection == "AddConnections" )
       {
    	   var arr = selectedNewConnId.split("_"); 
    	   
    	   var i = arr[2];

    	   if (i == newConnCount)
				return false;

			var newId = "new_conn_" + (parseInt(i) + 1);

			var userKey = $("#" + newId + "_email").val();

			getUserProfile(userKey, "new_conn_right");

			if (selectedNewConnId != userKey)
			{
				$("#" + selectedNewConnId).addClass("listcontainer");

				$("#" + selectedNewConnId).removeClass("listcontainer_selected");

				$("#" + newId).removeClass("listcontainer");

				$("#" + newId).addClass("listcontainer_selected");

				selectedNewConnId = newId;
				
				//$(".new_conn_list").mCustomScrollbar("scrollTo",1000);
				
			}
    	   
    	   
       	}
       	else if( currentSelection == "PendingConnections" )
       {
    	   var arr = selectedPenConnId.split("_"); 
    	   
    	   var i = arr[2];

    	   if (i == penConnCount)
				return false;

			var newId = "new_conn_" + (parseInt(i) + 1);

			var userKey = $("#" + newId + "_email").val();

			getUserProfile(userKey, "new_conn_right");

			if (selectedPenConnId != userKey)
			{
				$("#" + selectedPenConnId).addClass("listcontainer");

				$("#" + selectedPenConnId).removeClass("listcontainer_selected");

				$("#" + newId).removeClass("listcontainer");

				$("#" + newId).addClass("listcontainer_selected");

				selectedPenConnId = newId;
				
			}
    	   
       }
       else if( currentSelection == "MyConnections" )
       {
    	   var arr = selectedMyConnId.split("_"); 
    	   
    	   var i = arr[2];

    	   if (i == myConnCount)
				return false;

			var newId = "new_conn_" + (parseInt(i) + 1);

			var userKey = $("#" + newId + "_email").val();

			getUserProfile(userKey, "new_conn_right");

			if (selectedMyConnId != userKey)
			{
				$("#" + selectedMyConnId).addClass("listcontainer");

				$("#" + selectedMyConnId).removeClass("listcontainer_selected");

				$("#" + newId).removeClass("listcontainer");

				$("#" + newId).addClass("listcontainer_selected");

				selectedMyConnId = newId;
			}
       }
			
       
       return false;
    }
     if (e.keyCode == 38) 
    { 
    	if( currentSelection == "AddConnections" )
        {
     	   var arr = selectedNewConnId.split("_"); 
     	   
     	   var i = arr[2];

     	   if (i == 0 )
 				return false;

 			var newId = "new_conn_" + (parseInt(i) - 1);

 			var userKey = $("#" + newId + "_email").val();

 			getUserProfile(userKey, "new_conn_right");

 			if (selectedNewConnId != userKey)
 			{
 				$("#" + selectedNewConnId).addClass("listcontainer");

 				$("#" + selectedNewConnId).removeClass("listcontainer_selected");

 				$("#" + newId).removeClass("listcontainer");

 				$("#" + newId).addClass("listcontainer_selected");

 				selectedNewConnId = newId;
 				
 			}
     	   
        }
    	else if( currentSelection == "PendingConnections" )
        {
     	   var arr = selectedPenConnId.split("_"); 
     	   
     	   var i = arr[2];

     	   if (i == 0 )
 				return false;

 			var newId = "new_conn_" + (parseInt(i) + 1);

 			var userKey = $("#" + newId + "_email").val();

 			getUserProfile(userKey, "new_conn_right");

 			if (selectedPenConnId != userKey)
 			{
 				$("#" + selectedPenConnId).addClass("listcontainer");

 				$("#" + selectedPenConnId).removeClass("listcontainer_selected");

 				$("#" + newId).removeClass("listcontainer");

 				$("#" + newId).addClass("listcontainer_selected");

 				selectedPenConnId = newId;
 				
 			}
     	   
        }
        else if( currentSelection == "MyConnections" )
        {
     	   var arr = selectedMyConnId.split("_"); 
     	   
     	   var i = arr[2];

     	   if (i == 0 )
 				return false;

 			var newId = "new_conn_" + (parseInt(i) + 1);

 			var userKey = $("#" + newId + "_email").val();

 			getUserProfile(userKey, "new_conn_right");

 			if (selectedMyConnId != userKey)
 			{
 				$("#" + selectedMyConnId).addClass("listcontainer");

 				$("#" + selectedMyConnId).removeClass("listcontainer_selected");

 				$("#" + newId).removeClass("listcontainer");

 				$("#" + newId).addClass("listcontainer_selected");

 				selectedMyConnId = newId;
 			}
        }
      
    	
        return false;
     }
    });