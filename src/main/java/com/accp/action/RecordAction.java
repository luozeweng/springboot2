package com.accp.action;

import com.accp.biz.RecordBiz;
import com.accp.biz.UserBiz;
import com.accp.pojo.Record;
import com.accp.pojo.Student;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
@RequestMapping("/c/recode")
public class RecordAction {

    @Resource
    private RecordBiz biz;

    @Resource
    private UserBiz userBiz;

    @GetMapping("findByStuCode")
    public String selectByStuCode(String stuCode, Model model){
        model.addAttribute("list", biz.selectByStuCode(stuCode));
        return "rlist";
    }

    @GetMapping("cf")
    public String selectRecord(String name, String sname, Integer pageNum, Integer pageSize, Model model){
        model.addAttribute("name",name);
        model.addAttribute("sname",sname);
        model.addAttribute("pageinfo",biz.selectRecord(name, sname, pageNum, pageSize));
        return "cf";
    }

    @GetMapping("del/{id}")
    public String del(@PathVariable Integer id){
        biz.deleteByPrimaryKey(id);
        return "redirect:/c/recode/cf?pageNum=1&pageSize=3";
    }

    @GetMapping("toAdd")
    public String toAdd(Model model){
        model.addAttribute("stuList",userBiz.findAll());
        return "add";
    }

    @PostMapping("add")
    public String add(Record record, HttpSession session){
        Student stu = (Student)session.getAttribute("user") ;
        record.setCreateby(stu.getName());
        record.setCreatedate(new Date());
        biz.insert(record);
        return "redirect:/c/recode/cf?pageNum=1&pageSize=3";
    }

    @GetMapping("upd/{id}")
    public String upd(@PathVariable Integer id, Model model){
        model.addAttribute("record",biz.selectById(id));
        model.addAttribute("stuList",userBiz.findAll());
        return "upd";
    }

    @PostMapping("update")
    public String update(Record record){
        biz.updateRecord(record.getName(), record.getDes(), record.getId());
        return "redirect:/c/recode/cf?pageNum=1&pageSize=3";
    }
}
