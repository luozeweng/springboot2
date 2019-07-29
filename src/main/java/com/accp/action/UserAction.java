package com.accp.action;

import com.accp.biz.UserBiz;
import com.accp.pojo.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/c/users")
public class UserAction {

    @Resource
    private UserBiz biz;

    @PostMapping("loginIn")
    public String validateUsersInfo(String name, String pwd, Model model, HttpSession session){
        Student stu = biz.selectStudent(name, null);
        String view = "login";
        if (stu != null) {
            stu = biz.selectStudent(name, pwd);
            if (stu != null) {
                session.setAttribute("user", stu);
                return "redirect:/view/index";
            } else {
                model.addAttribute("msg", "用户名或密码错误");
            }
        } else {
            model.addAttribute("msg", "用户名不存在");
        }
        return view;
    }


    @GetMapping("list")
    public String getList(Integer pageNum, Integer pageSize, Model model){
        model.addAttribute("pageinfo", biz.selectAll(pageNum, pageSize));
        return "stuList";
    }
}
