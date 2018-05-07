package com.kaishengit.controller;

import com.github.pagehelper.PageInfo;
import com.kaishengit.entity.Product;
import com.kaishengit.entity.ProductType;
import com.kaishengit.exception.NotFoundException;
import com.kaishengit.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/{id:\\d+}")
    public String viewPoint(@PathVariable Integer id, Model model){

        Product product = productService.findById(id);
        if (product == null){
            throw new NotFoundException();
        }
        model.addAttribute("product",product);
        return "product/product";
    }

    @GetMapping
    public String list(@RequestParam(defaultValue = "1",name = "p",required = false) Integer pageNo,
                       @RequestParam(required = false) String productName,
                       @RequestParam(required = false) String place,
                       @RequestParam(required = false) Float minPrice,
                       @RequestParam(required = false) Float maxPrice,
                       @RequestParam(required = false) Integer typeId,
                       HttpServletRequest request,
                       Model model){
        System.out.println(request.getRequestURL());
        System.out.println(request.getRequestURI());
        Map<String,Object> queryParamMap = new HashMap<>();
        queryParamMap.put("productName",productName);
        queryParamMap.put("place",place);
        queryParamMap.put("minPrice",minPrice);
        queryParamMap.put("maxPrice",maxPrice);
        queryParamMap.put("typeId",typeId);
        PageInfo<Product> pageInfo = productService.findAllProductsByPageNoAndQueryParam(pageNo,queryParamMap);
        //PageInfo<Product> pageInfo = productService.findAllProductsByPageNo(pageNo);

        Integer num = (pageNo-1)*10;
        model.addAttribute("num",num);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("typeList",productService.findAllProductType());
        System.out.println("查询所有");
        return "product/list";
    }


    @GetMapping("/new")
    public String newProduct(Model model){
        List<ProductType> productTypeList = productService.findAllProductType();
        model.addAttribute("productTypeList",productTypeList);
        return "product/new";
    }

    @PostMapping("/new")
    public String saveProduct(Product product, RedirectAttributes redirectAttributes){
        productService.saveProduct(product);
        redirectAttributes.addFlashAttribute("message","添加商品成功");
        return "redirect:/product";
    }

    @GetMapping("/{id:\\d+}/del")
    public String deleteProduct(@PathVariable Integer id,RedirectAttributes redirectAttributes){
        productService.delProductById(id);
        redirectAttributes.addFlashAttribute("message","删除商品成功");
        return "redirect:/product";
    }

    @GetMapping("/{id:\\d+}/edit")
    public String editProduct(@PathVariable Integer id,Model model){
        //根据id查商品
        Product product = productService.findById(id);
        if (product == null){
            throw new NotFoundException();
        }
        //查询商品分类列表
        List<ProductType> productTypeList = productService.findAllProductType();
        model.addAttribute("product",product);
        model.addAttribute("productTypeList",productTypeList);
        return "product/edit";
    }

    @PostMapping("/{id:\\d+}/edit")
    public String editProduct(Product product,RedirectAttributes redirectAttributes){
        productService.updateProduct(product);
        redirectAttributes.addFlashAttribute("message","商品修改成功");
        return "redirect:/product";
    }
}
