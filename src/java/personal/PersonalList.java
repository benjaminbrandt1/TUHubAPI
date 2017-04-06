/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personal;

import java.util.ArrayList;

/**
 *
 * @author Ben
 */
public class PersonalList {

    public String dbError = "";
    private ArrayList<Personal> personalList = new ArrayList();

    public PersonalList() {
    }

    public void addOption(Personal option) {
        this.personalList.add(option);
    }
}
