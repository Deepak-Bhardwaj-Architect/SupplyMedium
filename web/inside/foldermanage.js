/* 
 * 
 * 
 * 
 */
function showGroupUser(groupKey)
{
       if ($('#group_users_'+groupKey).css('display')==="block") 
        {
                ($("#group_plus_minus_"+groupKey)).addClass("expand_plus");
                ($("#group_plus_minus_"+groupKey)).removeClass("collapse_minus");
        }
        else 
        {
                ($("#group_plus_minus_"+groupKey)).removeClass("expand_plus");
                ($("#group_plus_minus_"+groupKey)).addClass("collapse_minus"); 
        }        
    $.ajax({
        type: "POST",
        dataType: 'text',
        url: "GroupUser",
        data:
                {
                    'eventType': 'showExistingUserOfGroup',
                    'companyKey':$('#companyKey').val(),
                    'groupKey':groupKey
                },
        success: function(data)
        {
            $('#group_users_'+groupKey).html(data);
            $('#group_users_'+groupKey).toggle(250);
        },
        error: function(xhr, textStatus, errorThrown)
        {
            alert("error :"+errorThrown)
        }
    });
}

