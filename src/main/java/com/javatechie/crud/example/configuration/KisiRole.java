package com.javatechie.crud.example.configuration;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

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

    // Method based authenteication islemi icin role birlestirme metodu
    public Set<SimpleGrantedAuthority> otoriteleriAl(){
        Set <SimpleGrantedAuthority> permission = getKisiPermission().stream().map(x->new SimpleGrantedAuthority(x.getPermission())).collect(Collectors.toSet());
    }

}
