/* 
 * 
 * 
 * 
 */
var watchlistKey="";
function createWatchlist()
{
    var watchlistName=$('#new_watchlist_input').val();
    $('#new_watchlist_input').val('');
    if(watchlistName.trim()!=="")
    {
        $('#add_watchlist_popup').hide();
        $.ajax({
        type: "POST",
        dataType: 'text',
        url: "Watchlist",
        data:
                {
                    'eventType':'createWatchlist',
                    'watchlistName': watchlistName
                },
        success: function(data)
        {
            showWatchlist();
            //alert(data);
            ShowToast("Watchlist Created",2000);
        },
        error: function(xhr, textStatus, errorThrown)
        {
            //alert(errorThrown);
            ShowToast("Watchlist not Created",2000);
        }
    });
}
else
{
        $('#new_watchlist_input_err').html("Enter Watchlist Name");
}
}

function showWatchlist()
{
 $.ajax({
        type: "POST",
        dataType: 'text',
        url: "Watchlist",
        data:
                {
                    'eventType':'showWatchlist'
                },
        success: function(data)
        {
            //alert(data);
            $('#watchlists').html(data);
        },
        error: function(xhr, textStatus, errorThrown)
        {
            //alert(errorThrown);
            ShowToast("Watchlist not fetched errer :"+errorThrown);
        }
    });    
}

function deleteWatchlist(watchlistKey)
{
 //alert(watchlistKey);
    $.ajax({
        type: "POST",
        dataType: 'text',
        url: "Watchlist",
        data:
                {
                    'eventType':'deleteWatchlist',
                    'watchlistKey':watchlistKey
                },
        success: function(data)
        {
            //alert(data);
            showWatchlist();
            ShowToast("Watchlist Deleted");
        },
        error: function(xhr, textStatus, errorThrown)
        {
            //alert(errorThrown);
            ShowToast("Watchlist not Deleted errer :"+errorThrown);
        }
    });    
}

function searchMemberToAdd(value)
{
 //alert(watchlistKey);
    $.ajax({
        type: "POST",
        dataType: 'text',
        url: "Watchlist",
        data:
                {
                    'eventType':'searchMemberToAdd',
                    'value':value,
                    'watchlistKey':watchlistKey
                },
        success: function(data)
        {
//            /alert(data);
            $('#search_result').html(data);
            $('#search_result').show();
        },
        error: function(xhr, textStatus, errorThrown)
        {
            //alert(errorThrown);
            ShowToast("Watchlist not fetched errer :"+errorThrown);
        }
    });    
}
function addWatchlistMember(watchlistKey,userKey)
{
    $.ajax({
        type: "POST",
        dataType: 'text',
        url: "Watchlist",
        data:
                {
                    'eventType':'addWatchlistMember',
                    'userKey':userKey,
                    'watchlistKey':watchlistKey
                },
        success: function(data)
        {
            $('#search_result').html(data);
            $('#search_result').show();          
            document.getElementById("search_mem_textbox").value="";
            showWatchlistMember(watchlistKey); 
        },
        error: function(xhr, textStatus, errorThrown)
        {
            //alert(errorThrown);
            ShowToast("Watchlist not fetched errer :"+errorThrown);
        }
    });    
}

function deleteMemberByWatchListMemberKey()
{
    var watchlistMemberKey=$('#txtDeleteMember').val();
    var watchlistKey=$('#watchlistKey').val();
    $.ajax({
        type: "POST",
        dataType: 'text',
        url: "Watchlist",
        data:
                {
                    'eventType':'deleteWatchlistMember',
                    'watchlistMemberKey':watchlistMemberKey,
                    'watchlistKey':watchlistKey
                },
        success: function(data)
        {
//            $('#search_result').html(data);
            showWatchlistMember(watchlistKey);
        },
        error: function(xhr, textStatus, errorThrown)
        {
            //alert(errorThrown);
            ShowToast("Watchlist not fetched errer :"+errorThrown);
        }
    });    
}

function searchAddedMemberInWatchlist()
{
    var userPartialMemberName=$('#userPartialMemberName').val();
    $.ajax({
        type: "POST",
        dataType: 'text',
        url: "Watchlist",
        data:
                {
                    'eventType':'searchAddedMemberInWatchlist',
                    'watchlistKey':watchlistKey,
                    'userPartialMemberName':userPartialMemberName
                },
        success: function(data)
        {
            $('#memberlist_container').html(data);
            //showWatchlistMember(watchlistKey);
           // alert(data);
        },
        error: function(xhr, textStatus, errorThrown)
        {
            //alert(errorThrown);
            ShowToast("Watchlist not fetched errer :"+errorThrown);
        }
    });    
}

function showWatchlistMember(watchlistKey)
{
    $.ajax({
        type: "POST",
        dataType: 'text',
        url: "Watchlist",
        data:
                {
                    'eventType':'showWatchlistMember',
                    'watchlistKey':watchlistKey
                },
        success: function(data)
        {            
            $('#memberlist_container').html(data);
            $('#search_mem_textbox').html("");
            var abc=$('#watchlistMemberCount').val();
            if(abc===undefined)
            {
                abc="0";
                $('#delete_members_btn').hide();
            }
            else
            {
                $('#delete_members_btn').show();
            }
            $('#view_mem').html("View Members("+abc+")");
        },
        error: function(xhr, textStatus, errorThrown)
        {
            alert(errorThrown);
            ShowToast("Watchlist not fetched errer :"+errorThrown);
        }
    });    
}





