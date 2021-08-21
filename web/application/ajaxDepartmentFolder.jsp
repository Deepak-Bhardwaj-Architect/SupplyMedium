<%@page import="java.io.File"%>
<%@page import="supply.medium.utility.SmProperties"%>
<%@page import="supply.medium.home.bean.FolderBean"%>
<%@page import="supply.medium.home.database.FolderMaster"%>
<%@page import="java.util.ArrayList"%>
<%
    String departmentKey = request.getParameter("departmentKey");
    String companyKey = session.getAttribute("companyKey").toString();
    SmProperties.folderPath = SmProperties.urlPath;
    String folderPath = SmProperties.folderPath + "company-" + companyKey +File.separator+ "/department feed";
    String folderurl = SmProperties.pathUrl + "company-" + companyKey +File.separator+ "/department feed";
    File folder = null;
    File[] files = null;
    int folderSize = 0;
    String folderRow = "even_row";
    String fileRow = "even_row";
    ArrayList groupList = FolderMaster.showFolderOfDepartment(companyKey, departmentKey);
    FolderBean ub = null;
    for (int i = 0; i < groupList.size(); i++) {
        ub = (FolderBean) groupList.get(i);
        if (i % 2 == 0) {
            folderRow = "even_row";
        } else {
            folderRow = "odd_row";
        }
        //out.print("<option value=" + ub.getFolderKey() + ">" + ub.getFolderName() + "</option>");
        folder = new File(folderPath + File.separator + ub.getFolderName());
        if (folder.exists()) {
            folderSize = new File(folderPath + File.separator + ub.getFolderName()).listFiles().length;
        }
%>
<div class="folder_row <%=folderRow%>" onclick="corprateDepartmentFolder('folder_<%=ub.getFolderKey() %>');$('#corporateDepartmentSelectedFolder').val('<%=ub.getFolderName() %>');$('#corporateDepartmentSelectedFolderKey').val('<%=ub.getFolderKey()%>');$('#folderFiles_<%=ub.getFolderKey() %>').toggle(500);$('#departmentFolderId').val('<%=ub.getFolderKey()%>');" id="folder_<%=ub.getFolderKey() %>">
    <div class="folder folder_closed_top">            
    </div>
    <div class="folder_name"><%=ub.getFolderName()%>(<%=folderSize%>)
    </div>            
</div> 
<div style="float: left;display:none;" id="folderFiles_<%=ub.getFolderKey() %>">    
    <%
        if (folder.exists()) {
            files = new File(folderPath + File.separator + ub.getFolderName()).listFiles();
            for (int j = 0; j < files.length; j++) {
                if (files[j].isFile()) {
                    if (j % 2 == 0) {
                        fileRow = "even_row";
                    } else {
                        fileRow = "odd_row";
                    }
    %>
    <div class="file_row <%=fileRow %>" id="folderFile_<%=ub.getFolderKey()+j %>">
        <div class="file file_mid">                
        </div>
        <div class="file_name" onclick="corprateDepartmentFile('folderFile_<%=ub.getFolderKey()+j %>');$('#corporateDepartmentSelectedFile').val('<%=files[j].getName()%>');$('#corporateDepartmentFileUrl').val('<%=folderurl+"/"+ub.getFolderName()+"/"+files[j].getName() %>');"><%=files[j].getName()%>
        </div>                
    </div>
    <%  }
            }
        }
    %>
</div> 
<%
    }
%>
<input type="hidden" id="corporateDepartmentFileUrl"/>
<input type="hidden" id="corporateDepartmentSelectedFile"/>
<input type="hidden" id="corporateDepartmentSelectedFolder"/>
<input type="hidden" id="corporateDepartmentSelectedFolderKey"/>
