/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

/**
 *
 * @author Admin
 */
public class Staff {
    private String idStaff;
    private String role;
    private String name;

    public Staff(String idStaff, String role, String name) {
        this.idStaff = idStaff;
        this.role = role;
        this.name = name;
    }

    public String getIdStaff() {
        return idStaff;
    }

    public void setIdStaff(String idStaff) {
        this.idStaff = idStaff;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return idStaff +  "," + name +  "," + role;
    }
    
    
}
