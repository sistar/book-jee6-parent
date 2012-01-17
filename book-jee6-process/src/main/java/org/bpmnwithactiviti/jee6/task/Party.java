package org.bpmnwithactiviti.jee6.task;

import org.activiti.cdi.annotation.BusinessProcessScoped;

import javax.inject.Named;
import java.io.Serializable;

@BusinessProcessScoped
@Named
public class Party implements Serializable {
    private static final long serialVersionUID = 1L;

    private boolean approved;
    private int wantedBeers;

    public int getWantedBeers() {
        return wantedBeers;
    }

    public void setWantedBeers(int wantedBeers) {
        this.wantedBeers = wantedBeers;
    }

    public boolean isApproved() {
        return approved;
    }
    public void setApproved(boolean approved) {
        this.approved = approved;
    }
}
