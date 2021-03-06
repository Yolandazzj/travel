package com.qdu.controller;

import com.qdu.page.page;
import com.qdu.service.*;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private GroupService groupService;

    @Autowired
    private FoodService foodService;

    @Autowired
    private SceneService sceneService;

    @Autowired
    private EssayService essayService;

    @Autowired
    private HotelService hotelService;

    @Autowired
    private AdminInfoService adminInfoService;

    @Autowired
    private AdminManageService adminManageService;

    //管理员首页-组团游
    @RequestMapping("admin/group")
    public String adminGroup(Model model, HttpServletRequest request){
        try {
            String pageNo = request.getParameter("pageNo");
            if (pageNo == null) {
                pageNo = "1";
            }
            page page = groupService.queryForPage(Integer.valueOf(pageNo),8);
            model.addAttribute("page",page);
            List groupAdminAll = page.getList();
            model.addAttribute("groupAdminAll", groupAdminAll);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "adminGroup";
    }

    //管理员首页-美食
    @RequestMapping("admin/food")
    public String adminFood(Model model,HttpServletRequest request){
        try {
            String pageNo = request.getParameter("pageNo");
            if (pageNo == null) {
                pageNo = "1";
            }
            page page = foodService.queryForPage(Integer.valueOf(pageNo),8);
            model.addAttribute("page",page);
            List foodAdminAll = page.getList();
            model.addAttribute("foodAdminAll", foodAdminAll);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "adminFood";
    }

    //管理员首页-景点
    @RequestMapping("admin/scene")
    public String adminScene(Model model,HttpServletRequest request){
        try {
            String pageNo = request.getParameter("pageNo");
            if (pageNo == null) {
                pageNo = "1";
            }
            page page = sceneService.queryForPage(Integer.valueOf(pageNo),8);
            model.addAttribute("page",page);
            List sceneAdminAll = page.getList();
            model.addAttribute("sceneAdminAll", sceneAdminAll);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "adminScene";
    }


    //管理员查看所有订单
    @RequestMapping("admin/orderAll")
    public String orderAll(HttpServletRequest request, Model model){
        try {
            String pageNo = request.getParameter("pageNo");
            if (pageNo == null) {
                pageNo = "1";
            }
            page orderPage =adminManageService .queryForPage(Integer.valueOf(pageNo), 3);
            model.addAttribute("orderPage", orderPage);

            List myOrderList = orderPage.getList();
//            for (Object b:myOrderList) {
//                System.out.println(b);
//            }
            model.addAttribute("myOrderList", myOrderList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "adminOrder";
    }

//删除订单
    @RequestMapping(value = "admin/deleteOrder",method = RequestMethod.POST)
    @ResponseBody
    public void deleteScene(int orderId){

        adminManageService.deleteOrder(orderId);
    }

    //管理员首页-攻略
    @RequestMapping("admin/essay")
    public String adminEssay(Model model,HttpServletRequest request){
        try {
            String pageNo = request.getParameter("pageNo");
            if (pageNo == null) {
                pageNo = "1";
            }
            page page = essayService.queryForPage(Integer.valueOf(pageNo),8);
            model.addAttribute("page",page);
            List essayAdminAll = page.getList();
            model.addAttribute("essayAdminAll", essayAdminAll);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "adminEssay";
    }
    //管理员删除攻略
    @RequestMapping(value = "admin/deleteEssayByAdmin",method = RequestMethod.POST)
    @ResponseBody
    public void deleteEssayByAdmin(int essayId){
        essayService.deleteEssay(essayId);
    }

    //管理员首页-酒店预定
    @RequestMapping("admin/hotel")
    public String adminHotel(Model model,HttpServletRequest request){
        try {
            String pageNo = request.getParameter("pageNo");
            if (pageNo == null) {
                pageNo = "1";
            }
            page page = adminInfoService.queryForPage(Integer.valueOf(pageNo),8);
            model.addAttribute("page",page);
            List hotelAdminAll = page.getList();
            model.addAttribute("hotelAdminAll", hotelAdminAll);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "adminHotel";
    }
    //管理员删除预定
    @RequestMapping(value = "admin/deleteHotelOrderByAdmin",method = RequestMethod.POST)
    @ResponseBody
    public void deleteHotelOrderByAdmin(int hotelOrderId){
        hotelService.deleteHotelOrder(hotelOrderId);
    }

}
