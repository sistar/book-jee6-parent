package org.springfriends.impl;

import org.springfriends.GreeterService;

public class GreeterServiceImpl implements GreeterService {
    @Override
    public String sayHello(String name) {
        return "hello " + name + "!";
    }

}
