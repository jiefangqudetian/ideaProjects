package com.kaishengit.tms.service;

import com.kaishengit.tms.entity.Permission;

import java.util.List;

/**   
 * 角色和权限的业务类
 * @author drm  
 * @date 2018/4/13   
 */ 
public interface RolePermissionService {

    /**  
     * 添加权限
     * @date 2018/4/13
     * @param [permission]  
     * @return void  
     */ 
    void savePermission(Permission permission);
    
    /**  
     * 根据权限类型查询权限列表
     * @date 2018/4/13
     * @param [PermissionType]  权限类型 菜单|按钮
     * @return java.util.List<com.kaishengit.tms.entity.Permission>  
     */ 
    List<Permission> findPermissionByPermissionType(String permissionType);
    
    /**  
     * 查询所有权限
     * @date 2018/4/13
     * @param []  
     * @return java.util.List<com.kaishengit.tms.entity.Permission>  
     */ 
    List<Permission> findAllPermission();
}
