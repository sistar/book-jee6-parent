package org.bpmnwithactiviti.controller;

import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.enterprise.event.Event;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.ProcessDefinition;
import org.bpmnwithactiviti.model.Member;

// The @Stateful annotation eliminates the need for manual transaction demarcation
@Stateful
// The @Model stereotype is a convenience mechanism to make this a request-scoped bean that has an
// EL name
// Read more about the @Model stereotype in this FAQ:
// http://sfwk.org/Documentation/WhatIsThePurposeOfTheModelAnnotation
@Model
public class MemberRegistration {

    @EJB(lookup="java:global/processEngine/default")
    private ProcessEngine processEngine;

    @Inject
    private Logger log;

    @Inject
    private EntityManager em;

    @Inject
    private Event<Member> memberEventSrc;

    private Member newMember;

    @Produces
    @Named
    public Member getNewMember() {
        return newMember;
    }

    public RepositoryService getRepositoryService(){
        return processEngine.getRepositoryService();
    }

    @Produces
    @Named
    public List<ProcessDefinition> getProcessDefinitionList(){
        return getRepositoryService().createProcessDefinitionQuery().list();
    }

    public void register() throws Exception {
        log.info("Registering " + newMember.getName());
        em.persist(newMember);
        memberEventSrc.fire(newMember);
        initNewMember();
    }

    @PostConstruct
    public void initNewMember() {
        newMember = new Member();
    }


}
