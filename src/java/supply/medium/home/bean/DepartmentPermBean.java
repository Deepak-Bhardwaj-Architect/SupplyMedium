/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supply.medium.home.bean;

/**
 *
 * @author VIC
 */
public class DepartmentPermBean {
    
    private String department_perm_key;
    private String add_folder;
    private String delete_folder;
    private String add_file;
    private String delete_file;
    private String post_announcements;

    public String getDepartment_perm_key() {
        return department_perm_key;
    }

    public void setDepartment_perm_key(String department_perm_key) {
        this.department_perm_key = department_perm_key;
    }

    public String getAdd_folder() {
        return add_folder;
    }

    public void setAdd_folder(String add_folder) {
        this.add_folder = add_folder;
    }

    public String getDelete_folder() {
        return delete_folder;
    }

    public void setDelete_folder(String delete_folder) {
        this.delete_folder = delete_folder;
    }

    public String getAdd_file() {
        return add_file;
    }

    public void setAdd_file(String add_file) {
        this.add_file = add_file;
    }

    public String getDelete_file() {
        return delete_file;
    }

    public void setDelete_file(String delete_file) {
        this.delete_file = delete_file;
    }

    public String getPost_announcements() {
        return post_announcements;
    }

    public void setPost_announcements(String post_announcements) {
        this.post_announcements = post_announcements;
    }
    
}
