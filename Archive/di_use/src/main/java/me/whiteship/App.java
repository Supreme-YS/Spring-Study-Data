package me.whiteship;

import me.whiteship.di.ContainerService;

public class App {
    public static void main(String[] args) {
        AccountService accountService = ContainerService.getObject(AccountService.class);
        accountService.join();
    }
}
