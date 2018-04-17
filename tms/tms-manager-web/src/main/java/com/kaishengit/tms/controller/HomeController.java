package com.kaishengit.tms.controller;

import com.kaishengit.tms.entity.Account;
import com.kaishengit.tms.exception.ServiceException;
import com.kaishengit.tms.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *首页及登录 登出控制器
 * @author drm
 * @date 2018/4/13
 */
@Controller
public class HomeController {

    @Autowired
    private AccountService accountService;

    /**  
     *系统登录页面
     * @date 2018/4/13
     * @param []  
     * @return java.lang.String  
     */
    @GetMapping("/")
    public String index(){
        return "index";
    }


    /**  
     *系统登录
     * @date 2018/4/13
     * @param []  
     * @return java.lang.String  
     */
    @PostMapping("/")
    public String login(String accountMobile,
                        String password,
                        HttpServletRequest request,
                        HttpSession session,
                        RedirectAttributes redirectAttributes){
        //获取登录的ip地址
        String requestIp = request.getRemoteAddr();
        try {
            Account account = accountService.login(accountMobile,password,requestIp);
            //将登录成功的对象放入session
            session.setAttribute("current_Account",account);
            //进入home页面
            return "redirect:/home";
        } catch (ServiceException ex){
            redirectAttributes.addFlashAttribute("phone",accountMobile);
            redirectAttributes.addFlashAttribute("message",ex.getMessage());
            return "redirect:/";
        }


    }


    /**  
     *登录后首页
     * @date 2018/4/13
     * @param []  
     * @return java.lang.String  
     */
    @GetMapping("/home")
    public String home(){
        return "home";
    }

    @GetMapping("/401")
    public String unauthorizedUrl(){
        return "error/401";
    }


}
