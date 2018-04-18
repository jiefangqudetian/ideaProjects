package com.kaishengit.tms.shiro;

import com.kaishengit.tms.entity.Permission;
import com.kaishengit.tms.service.RolePermissionService;
import org.apache.shiro.config.Ini;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CustomerFilterChainDefinition implements FactoryBean<Ini.Section> {

    @Autowired
    private RolePermissionService rolePermissionService;

    private String filterChainDefinition;

    public void setFilterChainDefinition(String filterChainDefinition) {
        this.filterChainDefinition = filterChainDefinition;
    }

    @Override
    public Ini.Section getObject() throws Exception {
        Ini ini = new Ini();
        ini.load(filterChainDefinition);

        //从数据库中查找所有权限对象
        List<Permission> permissionList = rolePermissionService.findAllPermission();
        Ini.Section section = ini.get(Ini.DEFAULT_SECTION_NAME);

        for (Permission permission:permissionList){
            section.put(permission.getUrl(),"perms["+permission.getPermissionCode()+"]");
        }

        section.put("/**","user");

        return section;
    }

    @Override
    public Class<?> getObjectType() {
        return Ini.Section.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
