package org.bpmnwithactiviti.jee6.task;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@Named
public class BeerOrderTask {
	Log log = LogFactory.getLog(this.getClass());
	@Inject
	private Party party;

	public void order() {
		log.info("ordering " + party.getWantedBeers() + " beers.");

	}
}
