package com.ca.fire.controller;

import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.metadata.Table;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.ca.fire.dao.UserDao;
import com.ca.fire.domain.BaseResult;
import com.ca.fire.domain.bean.User;
import com.ca.fire.domain.bean.UserModel;
import com.ca.fire.service.UserService;
import com.ca.fire.until.ExportExcel;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class UserController {
    @Resource
    private UserService userService;
    @Resource
    private UserDao userDao;
    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @ResponseBody
    @RequestMapping("/getUser")
    public User getUser(@RequestParam("id") Long id) {
        logger.debug("id:" + id);
        User user = userService.getUser(id);
        return user;
    }

    @RequestMapping("/index")
    public String index(Model model) {
        User user = new User();
        user.setUserName("杉菜");
        User user1 = userService.getUser(user);
        model.addAttribute("user", user1);
        logger.error("计算其他业务");
        User user2 = userService.getUser(user);
        logger.error("user2" + user2.toString());
        model.addAttribute("user2", user1);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
        User user3 = userService.getUser(user);
        model.addAttribute("user3", user3);

        logger.error("user3" + user3.toString());

        return "index";
    }


    @ResponseBody
    @RequestMapping(value = "/userList", method = {RequestMethod.POST, RequestMethod.GET})
    public Object gotoUserList(User user) {
        BaseResult baseResult = new BaseResult();
        if (user != null) {
            List<User> users = userService.queryUser(user);
            baseResult.setSuccess(true);
            baseResult.setData(users);
        } else {
            baseResult.setSuccess(false);
        }
        return baseResult;
    }

    @RequestMapping(value = "/userListPage", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView gotoUserListPage(User user) {
        ModelAndView mv = new ModelAndView("userList");
        List<User> users = userService.queryUser(user);
        mv.addObject("users", users);
        return mv;
    }

    @RequestMapping(value = "/userEditPage", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView gotoUpdateUserPage(HttpSession session, User queryUser) {
        ModelAndView mv = new ModelAndView("userEdit");
        if (queryUser == null || queryUser.getId() == null) {
            User requestUser = (User) session.getAttribute("user");
            if (requestUser != null) {
                User dbUser = userService.getUser(requestUser);
                mv.addObject("user", dbUser);
            } else {
                User dbUser = userService.getUser(queryUser);
                mv.addObject("user", dbUser);
            }
        } else {
            User dbUser = userService.getUser(queryUser);
            mv.addObject("user", dbUser);
        }
        return mv;
    }

    @RequestMapping(value = "/userUpdate", method = {RequestMethod.POST, RequestMethod.GET})
    public String updateUserPage(HttpSession session, User updateUser) {
        BaseResult baseResult = new BaseResult();
        if (updateUser != null) {
            Integer update = null;
            try {
                update = userService.update(updateUser);
                if (update > 0) {
                    baseResult.setSuccess(true);
                    User user = userService.getUser(updateUser.getId());
                    session.setAttribute("user", user);
//                    return "redirect:/userEditPage.do";
                } else {
                    logger.error("update fail");
                    session.setAttribute("user", updateUser);
                }
            } catch (Exception e) {
                logger.error("update error" + e.getMessage(), e);
                session.setAttribute("user", updateUser);
            }
        } else {
            session.setAttribute("user", updateUser);
        }
        return "redirect:/userListPage.do";
    }

    @RequestMapping(value = "/gotoNewUserPage", method = {RequestMethod.POST, RequestMethod.GET})
    public String gotoNewUserPage() {
        logger.error("gotoNewUserPage");

        return "user/userIndex";
    }

    @RequestMapping(value = "/getUserList", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public Object getUserList(User user) {
        List<User> users = userService.queryUser(user);
        return users;
    }


    @RequestMapping("/export")
    @ResponseBody
    public Object export(HttpServletResponse response, @RequestParam("count") Long count) {
        User user = new User();
        user.setPageNum(1);
        user.setPageSize(1000);
        long st = System.currentTimeMillis();
        List users = userService.queryUser(user);
        String[] PROPERTYS = {"ID", "姓名", "电话", "邮箱", "修改人", "修改时间"};
        String[] TITLES = {"id", "userName", "telPhone", "email",
                "updateUser", "updateTime"};
        try {
            ExportExcel.exportFile(response, "用户信息", PROPERTYS, TITLES, users, "1");

        } catch (Exception e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("spend time:" + (end - st) + " ms");
        return (end - st) + " ms";
    }

    @RequestMapping("/easyExcel")
    @ResponseBody
    public Object easyExcel(HttpServletResponse response, @RequestParam("count") Long count) throws IOException {
        User user = new User();
        user.setPageNum(1);
        user.setPageSize(1000000);
        long st = System.currentTimeMillis();
        ServletOutputStream out = null;
        try {
            out = response.getOutputStream();
            ExcelWriter writer = new ExcelWriter(out, ExcelTypeEnum.XLSX);

            // 设置EXCEL名称
            String fileName = new String(("SystemExcel").getBytes(), "UTF-8");

            // 设置SHEET名称
            Sheet sheet = new Sheet(1, 0);
            sheet.setSheetName("系统列表sheet1");

            // 设置标题
            Table table = new Table(1);
            List<List<String>> titles = new ArrayList<List<String>>();
            titles.add(Arrays.asList("姓名"));
            titles.add(Arrays.asList("邮箱"));
            titles.add(Arrays.asList("手机"));
            titles.add(Arrays.asList("创建人"));
            titles.add(Arrays.asList("创建时间"));
            table.setHead(titles);

            // 查数据写EXCEL
            List<List<String>> dataList = new ArrayList<>();
            List<UserModel> users = userDao.queryAllUser(user);
            if (!CollectionUtils.isEmpty(users)) {
                users.forEach(eachSysSystemVO -> {
                    dataList.add(Arrays.asList(
                            eachSysSystemVO.getUserName(),
                            eachSysSystemVO.getEmail(),
                            eachSysSystemVO.getTelPhone(),
                            eachSysSystemVO.getCreateUser().toString(),
                            eachSysSystemVO.getCreateTime().toString()
                    ));
                });
            }
            writer.write0(dataList, sheet, table);

            // 下载EXCEL
            response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
            response.setContentType("multipart/form-data");
            response.setCharacterEncoding("utf-8");
            writer.finish();
            out.flush();

        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("spend time:" + (end - st) + " ms");
        return (end - st) + " ms";
    }
}
