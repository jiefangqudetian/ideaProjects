package com.kaishengit.tms.controller;

import com.google.common.collect.Maps;
import com.kaishengit.tms.controller.exception.NotFoundException;
import com.kaishengit.tms.dto.ResponseBean;
import com.kaishengit.tms.entity.Account;
import com.kaishengit.tms.entity.Roles;
import com.kaishengit.tms.exception.ServiceException;
import com.kaishengit.tms.service.AccountService;
import com.kaishengit.tms.service.RolePermissionService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;

/**
 * 账号管理控制器
 * @author drm
 * @date 2018/4/16
 */
@Controller
@RequestMapping("/manage/account")
public class AccountController {

    @Autowired
    private AccountService accountService;
    @Autowired
    private RolePermissionService rolePermissionService;

    @GetMapping
    public String home(Model model,
                       @RequestParam(required = false)Integer rolesId,
                       @RequestParam(required = false)String nameMobile){

        Map<String,Object> requestParam = Maps.newHashMap();
        requestParam.put("nameMobile",nameMobile);
        requestParam.put("rolesId",rolesId);

        model.addAttribute("accountList",accountService.findAllAccountWithRolesByQueryParam(requestParam));
        model.addAttribute("rolesList",rolePermissionService.findAllRoles());

        List<Account> accountList = accountService.findAllAccountWithRolesByQueryParam(requestParam);
        return "manage/account/home";
    }

    /**
     * 新增角色
     * @date 2018/4/16
     * @param
     * @return java.lang.String
     */
    @GetMapping("/new")
    public String newAccount(Model model){
        List<Roles> rolesList = rolePermissionService.findAllRoles();

        model.addAttribute("rolesList",rolesList);
        return "manage/account/new";
    }

    @PostMapping("/new")
    public String newAccount(Account account,Integer[] rolesIds){

        accountService.saveAccount(account,rolesIds);
        return "redirect:/manage/account";
    }

    @GetMapping("/{id:\\d+}/edit")
    public String edit(@PathVariable Integer id,Model model){
        System.out.println(123);
        Account account = accountService.findById(id);
        if (account == null){
            throw new NotFoundException();
        }
        //查询所有角色
        List<Roles> rolesList = rolePermissionService.findAllRoles();
        //查询当前账号所拥有的角色
        List<Roles> accountRolesList = rolePermissionService.findRolesByAccountId(id);

        model.addAttribute("rolesList",rolesList);
        model.addAttribute("accountRolesList",accountRolesList);
        model.addAttribute("account",account);
        System.out.println(456);
        return "manage/account/edit";
    }


    @PostMapping("/{id:\\d+}/edit")
    public String edit(Account account, Integer[] rolesIds, RedirectAttributes redirectAttributes){
        accountService.updateAccount(account,rolesIds);
        redirectAttributes.addFlashAttribute("message","修改账号成功");

        return "redirect:/manage/account";
    }

    @GetMapping("/{id:\\d+}/del")
    public @ResponseBody ResponseBean deleteAccount(@PathVariable Integer id){

        try{
            accountService.delAccountById(id);
            return ResponseBean.success();
        }catch(ServiceException ex){
            return ResponseBean.error(ex.getMessage());
        }
    }




}
