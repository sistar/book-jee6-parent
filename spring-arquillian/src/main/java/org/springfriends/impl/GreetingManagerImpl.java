package org.springfriends.impl;


import org.springfriends.GreeterService;

import javax.inject.Inject;

public class GreetingManagerImpl {

    public GreeterService getGreeterService() {
        return greeterService;
    }

    public void setGreeterService(GreeterService greeterService) {
        this.greeterService = greeterService;
    }

    @Inject
    GreeterService greeterService;

    public String doGreet(){
        return greeterService.sayHello("kermit");
    }
}
