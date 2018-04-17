package com.kaishengit.tms.controller;

import com.kaishengit.tms.dto.ResponseBean;
import com.kaishengit.tms.entity.Permission;
import com.kaishengit.tms.exception.ServiceException;
import com.kaishengit.tms.service.RolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
/**
 *权限控制器
 * @author drm
 * @date 2018/4/13
 */
@Controller
@RequestMapping("/manage/permission")
public class PermissionController {

    @Autowired
    private RolePermissionService rolePermissionService;

    /**
     *权限首页
     * @date 2018/4/13
     * @param [model]
     * @return java.lang.String
     */
    @GetMapping
    public String home(Model model){

        List<Permission> permissionList = rolePermissionService.findAllPermission();
        model.addAttribute("permissionList",permissionList);
        return "manage/permission/home";
    }

    /**
     *新增权限
     * @date 2018/4/13
     * @param [model]
     * @return java.lang.String
     */
    @GetMapping("/new")
    public String newPermission(Model model){
        //查询菜单类型的权限列表
        List<Permission> permissionList = rolePermissionService.findPermissionByPermissionType(Permission.MENU_TYPE);
        model.addAttribute("permissionList",permissionList);
        return "manage/permission/new";
    }

    @PostMapping("/new")
    public String newPermission(Permission permission, RedirectAttributes redirectAttributes){
        rolePermissionService.savePermission(permission);
        redirectAttributes.addFlashAttribute("message","新增权限成功");
        return "redirect:/manage/permission";
    }
    
    /**  
     * 删除权限
     * @date 2018/4/15
     * @param [id]  
     * @return com.kaishengit.tms.dto.ResponseBean  
     */ 
    @GetMapping("/{id:\\d+}/del")
    public @ResponseBody ResponseBean deletePermission(@PathVariable Integer id){
        try{
            rolePermissionService.delPermissionById(id);
            return ResponseBean.success();
        }catch (ServiceException ex){
            return ResponseBean.error(ex.getMessage());
        }
    }

    /*@GetMapping("/update")
    public String updatePermission(Model model){

    }

    @PostMapping("/update")
    public String updatePermission(Permission permission){

    }*/
}
