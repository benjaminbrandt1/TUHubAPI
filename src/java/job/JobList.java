/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package job;

import java.util.ArrayList;

/**
 *
 * @author Ben
 */
public class JobList {
    public String dbError = "";
    private ArrayList<Job> jobList = new ArrayList();

    public JobList() {
    }

    public void addOption(Job option) {
        this.jobList.add(option);
    }
}
