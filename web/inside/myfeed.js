/* 
 * 
 * 
 * 
 */

function splitFeedKeys()
{
    var feedkeys=$('#fechedFeedKeys').val().split(",");
    for(var i=0;i<feedkeys.length-1;i++)
    {
        feed_likes_comments(feedkeys[i]); 
        //alert('ok');
    }
}
function feed_likes_comments( id ) 
{
    var feedContent="";
    var lk_cmnt=null;
    var lk="Like";
    var cmnt="Comment";
    $.ajax({
		type : "POST",
                dataType: 'text',
		url : "FeedLikeComment",
		data : {
			'id' : id
		},		
		success : function(data)
		{
                     var likeStatus="1";
                    var likeEvent="";
                    lk_cmnt=data.toString().split("*");
                    if(lk_cmnt.length===3)
                    {
                        if(lk_cmnt[0]<2)
                        {    
                        lk=lk_cmnt[0]+" Like";
                        }
                        else
                        {
                         lk=lk_cmnt[0]+" Likes";   
                        }
                        if(lk_cmnt[1]<2)
                        {
                         cmnt=lk_cmnt[1]+" Comment";   
                        }   
                        else
                        {
                         cmnt=lk_cmnt[1]+" Comments";   
                        } 
                        likeStatus=lk_cmnt[2];
                        if(likeStatus==="0")
                        {    
                        likeEvent="onclick=\"javascript:save_like_comment_of_feed('"+id+"','like',document.getElementById('lks_cnt_"+id+"').value);this.onclick='';\"";
                    }
                    }   
                    feedContent +="<div class='dilbag-lca'>";
                        feedContent +="<div id='lk_"+id+"' class='dilbag-like' "+likeEvent+">"+lk+"</div><input type='hidden' value='"+lk+"' id='lks_cnt_"+id+"'/>";
                        feedContent +="<div id='cmnt2_"+id+"' class='dilbag-comment' onclick=\"javascript:hide_show('cmnt_"+id+"');select_feed_comment('"+id+"');\">"+cmnt+"</div><input type='hidden' value='"+cmnt+"' id='cmnts_cnt_"+id+"'/>";
                        feedContent +="<div style='width:100px;float:left;'>";
                            //feedContent +="<div class='Newsroom-action' onclick=\"javascript:hide_show('optn_"+id+"');\">Action</div>";
                            feedContent +="<select class='dilbag-action' id='optn_"+id+"'>";
                                feedContent +="<option class='dilbag-action-list'>-Action-</option>";
                                feedContent +="<option class='dilbag-action-list'>Hide</option>";
                                feedContent +="<option class='dilbag-action-list'>Mute</option>";
                                feedContent +="<option class='dilbag-action-list'>Spam</option>";
                            feedContent +="</select>";    
                        feedContent +="</div>";
                    feedContent +="</div>";
                    feedContent +="<div style='background:#F8F8F8;float:left;width:650px;display:none;padding:15px;' id='cmnt_"+id+"'>";
                        feedContent +="<textarea id='cmntddd_"+id+"' placeholder='Write comment' style='width:600px;height:50px;background:#FFFFFF;'></textarea>";
                        feedContent +="<div style='background:#FF9933;color:white;width:30px;border:1px solid #F8F8F8;float:right;text-align:center;cursor:pointer;vertical-align: bottom;' onclick=\"javascript:save_like_comment_of_feed('"+id+"',document.getElementById('cmntddd_"+id+"').value,document.getElementById('cmnts_cnt_"+id+"').value);\">Post</div>";
                    feedContent +="<ul id='all_comment_"+id+"' style='background:#F8F8F8;float:left;width:600px;padding-top:15px;'></ul></div>";
                    document.getElementById('mn_'+id).innerHTML=feedContent;                    
                    //return data;
		},
		error : function(xhr, textStatus, errorThrown) 
		{
			//return "0*0";
		}
	});        
}

function save_like_comment_of_feed(id,cmnt,cnt)
{
    //alert('ok');
    var cnt2=parseInt(cnt)+1;
    var val="";
    if(cmnt==="like")
    {
      if(cnt2<2)
      {
          val=cnt2+" Like";
          document.getElementById('lks_cnt_'+id).value=cnt2;
      }
      else
      {
         val=cnt2+" Likes"; 
         document.getElementById('lks_cnt_'+id).value=cnt2;
      }    
      document.getElementById("lk_"+id).innerHTML=val;  
    }
    else
    {
      if(cnt2<2)
      {
          val=cnt2+" Comment";
          document.getElementById('cmnts_cnt_'+id).value=cnt2;
      }
      else
      {
         val=cnt2+" Comments"; 
         document.getElementById('cmnts_cnt_'+id).value=cnt2;
      }    
      document.getElementById("cmnt2_"+id).innerHTML=val;  
    }  
    //alert('ok');
    document.getElementById("cmntddd_"+id).value="";
    //alert('2');
    $.ajax({
		type : "POST",
                dataType: 'text',
		url : "FeedLikeComment",
		data : {
			'ids' : id,
                        'cmnt':cmnt
		},		
		success : function(data)
		{
                    //alert(data)
                    select_feed_comment(id)
		},
		error : function(xhr, textStatus, errorThrown) 
		{
			//alert('error')
		}
	}); 
}
function select_feed_comment(id)
{
    $.ajax({
		type : "POST",
                dataType: 'text',
		url : "FeedLikeComment",
		data : {
			'ids' : id,
                        'slct_cmnt':'slct_cmnt'
		},		
		success : function(data)
		{
                    //alert(data);
                   document.getElementById('all_comment_'+id).innerHTML=data;                    
		},
		error : function(xhr, textStatus, errorThrown) 
		{
			//alert('error'+errorThrown);
		}
	});
}
