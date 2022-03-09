package com.javatechie.crud.example.configuration;

import com.google.common.collect.Sets;

import java.util.Set;

import static com.javatechie.crud.example.configuration.KisiPermission.*;

public enum KisiRole {

    USER(Sets.newHashSet(USER_READ)),
    ADMIN(Sets.newHashSet(ADMIN_READ,ADMIN_WRITE));

    private Set<KisiPermission> kisiPermission;
    public Set<KisiPermission> getKisiPermission(){
        return kisiPermission;
    }
    KisiRole(Set<KisiPermission> kisiPermission){
        this.kisiPermission=kisiPermission;
    }

}
