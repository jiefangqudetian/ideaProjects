package com.kaishengit.controller;

import com.kaishengit.entity.Customer;
import com.kaishengit.exception.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    //如果GetMapping没有加参数value,那么直接访问/customer就会访问该方法,RequestParam注解可设置参数p的默认值
    //如果有使用p带的值 如果没有使用默认值
    @GetMapping
    public  String list(Model model,@RequestParam(name = "p",defaultValue = "1") String pageNo){
        System.out.println("pageNo:"+pageNo);
        model.addAttribute("pageNo",pageNo);
        return "customer/list";
    }



    @GetMapping("/new")
    public String newCustomer(){
        return "customer/new";
    }

   /* @PostMapping("/new") //方式1
    public String newCustomer(String name,String address,String level){
        System.out.println("name:"+name+" address:"+address+" level:"+level);
        return "redirect:/customer/new";
    }*/

    @PostMapping("/new") //方式二
    public String newCustomer(Customer customer,String level){
        System.out.println(customer.getName()+"  "+customer.getAddress()+"  "+level);
        return "redirect:/customer/new";//重定向到servlet页面
    }



    @GetMapping("/delete")
    public String deleteCustomer(){
        return "customer/delete";
    }
    //GetMapping参数value值使用正则表达式
   /* @GetMapping("/{id}")//规则为数字，参数值和变量类型不匹配抛出400
    public String viewCustomer1(@PathVariable(name = "id")Integer customerId, Model model){
        System.out.println(">>>>>view customer :" + customerId);
        model.addAttribute("customerId",customerId);
        return "customer/view";
    }*/


    @GetMapping("/{id:\\d+}")//规则为数字
    public String viewCustomer2(@PathVariable(name = "id")Integer customerId, Model model){
        System.out.println(">>>>>view customer :" + customerId);

        if (customerId.equals(1025)){
            throw new NotFoundException();
        }
        model.addAttribute("customerId",customerId);
        return "customer/view";
    }

    //返回值可以为ModelAndView
/*    @GetMapping("/{typeName:d-\\d+}/{customerId:\\d+}")
    public ModelAndView viewCustomerByType(@PathVariable String typeName,
                                           @PathVariable Integer customerId){
        System.out.println(">>>typeName:"+typeName+"customerId"+customerId);
        //设定跳转视图的名称
        //ModelAndView modelAndView = new ModelAndView();
        //modelAndView.setViewName("customer/view");

        ModelAndView modelAndView = new ModelAndView("customer/view");
        modelAndView.addObject("customerId",customerId);
        modelAndView.addObject("typeName",typeName);
        return modelAndView;

    }*/

    //链接参数为中文时出现乱码
    @GetMapping(value = "/{typeName:d-.+}/{customerId:\\d+}")
    public ModelAndView viewCustomerByType1(@PathVariable String typeName,
                                           @PathVariable Integer customerId){
        System.out.println(">>>typeName:"+typeName+"customerId"+customerId);
        //设定跳转视图的名称
        //ModelAndView modelAndView = new ModelAndView();
        //modelAndView.setViewName("customer/view");

        ModelAndView modelAndView = new ModelAndView("customer/view");
        modelAndView.addObject("customerId",customerId);
        modelAndView.addObject("typeName",typeName);
        return modelAndView;

    }

    //三个save例子 1.ResponBody可以加在方法上，可以加在返回值前 2.GetMapping参数中的/可以省略
   // 3.返回中文，默认的响应类型为text/html;character=ISO-8859-1,要改为UTF-8才不会乱码
   @GetMapping("/save")
   public @ResponseBody String saveCustomer(){
       return "save success";
   }
   @GetMapping("saves")
   public @ResponseBody String saveCustomer1(){
       return "save success1";
   }
   @GetMapping(value = "/save2",produces = "text/html;charset=UTF-8")
   @ResponseBody
   public  String saveCustomer2(){
       return "保存成功";
   }

   //json格式响应体举例
    @GetMapping("/{id}.json")
   public @ResponseBody Customer viewCustomer(@PathVariable Integer id){

        Customer customer = new Customer(id,"李宁","北京");
        return customer;
   }
    @GetMapping("/all.json")
   public @ResponseBody List<Customer> viewCustomerList(){
        return  Arrays.asList(
                new Customer(1,"姚明","北京"),
                new Customer(2,"刘翔","北京"),
                new Customer(3,"张继科","北京")
        );
   }


}
