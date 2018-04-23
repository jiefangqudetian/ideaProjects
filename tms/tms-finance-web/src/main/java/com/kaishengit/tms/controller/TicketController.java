package com.kaishengit.tms.controller;

import com.kaishengit.tms.dto.ResponseBean;
import com.kaishengit.tms.entity.TicketInRecord;
import com.kaishengit.tms.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.joda.time.DateTime;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 *
 * @author drm
 * @date 2018/4/21
 */
@Controller
@RequestMapping("/ticket")
public class TicketController  {

    @Autowired
    private TicketService ticketService;

    /**
     * 年票入库首页
     * @date 2018/4/21
     * @param
     * @return java.lang.String
     */
    @GetMapping("/storage")
    public String ticketIn(Model model){
        List<TicketInRecord> ticketInRecordList = ticketService.findAllTicketInRecord();
        model.addAttribute("ticketInRecordList",ticketInRecordList);
        return "ticket/storage/home";
    }

    /**
     * 新增入库
     * @date 2018/4/21
     * @param
     * @return java.lang.String
     */
    @GetMapping("/storage/new")
    public String newTicketStorage(Model model){
        String today = DateTime.now().toString("YYYY-MM-dd");

        model.addAttribute("today",today);
        return "ticket/storage/new";
    }


    @PostMapping("/storage/new")
    public String newTicketStorage(TicketInRecord ticketInRecord, RedirectAttributes redirectAttributes){

        ticketService.saveTicketInRecord(ticketInRecord);
        redirectAttributes.addFlashAttribute("message","新增成功");
        return "redirect:/ticket/storage";
    }

    @GetMapping("/storage/{id:\\d+}/del")
    public @ResponseBody ResponseBean delTicketStorage(@PathVariable Integer id){
        ticketService.delTicketInRecordById(id);
        return ResponseBean.success();
    }

    @GetMapping("/storage/{id:\\d+}/edit")
    public String updateTicketStorage(@PathVariable Integer id,Model model){
        TicketInRecord ticketInRecord = ticketService.findTicketInRecordById(id);

        String today = DateTime.now().toString("YYYY-MM-dd");

        model.addAttribute("ticketInRecord",ticketInRecord);
        model.addAttribute("today",today);
        return "ticket/storage/edit";
    }

    @PostMapping("/storage/{id:\\d+}/edit")
    public String updateTicketStorage(TicketInRecord ticketInRecord,RedirectAttributes redirectAttributes){
        ticketService.updateTicketInRecord(ticketInRecord);
        return "redirect:/ticket/storage";
    }


}
