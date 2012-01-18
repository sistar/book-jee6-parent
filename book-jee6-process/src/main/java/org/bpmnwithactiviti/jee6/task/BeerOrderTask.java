package org.bpmnwithactiviti.jee6.task;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.logging.Logger;

@Named
public class BeerOrderTask {
    Logger log = Logger.getLogger(this.getClass().getName());

    @Inject
	private Party party;

	public void order() {
		log.info("ordering " + party.getWantedBeers() + " beers.");


	}
}
