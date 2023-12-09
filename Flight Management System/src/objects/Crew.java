/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

import java.util.List;

/**
 *
 * @author Admin
 */
public class Crew {

    private String crewId;
    private List<Staff> listStaff;

    public Crew(String crewId, List<Staff> listStaff) {
        this.crewId = crewId;
        this.listStaff = listStaff;
    }

    public String getCrewId() {
        return crewId;
    }

    public void setCrewId(String crewId) {
        this.crewId = crewId;
    }

    public List<Staff> getListStaff() {
        return listStaff;
    }

    public void setListStaff(List<Staff> listStaff) {
        this.listStaff = listStaff;
    }

    @Override
    public String toString() {
        return crewId + "," + listStaff;
    }
    
    public String toStringIdStaff() {
        String idStaff = "";
        for (Staff staff : listStaff) {
            idStaff += staff.getIdStaff() + " ";
        }
        return idStaff;
    }

}
