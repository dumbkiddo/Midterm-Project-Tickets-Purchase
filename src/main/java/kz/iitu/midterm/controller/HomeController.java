package kz.iitu.midterm.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kz.iitu.midterm.form.CustomerForm;
import kz.iitu.midterm.model.CartInfo;
import kz.iitu.midterm.validator.CustomerFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Transactional
@Api(value = "Home page Controller class", description = "This class allows to control the data flow of main page")
public class HomeController {

    @Autowired
    private CustomerFormValidator customerFormValidator;

    @ApiOperation(value = "Basic view og the home page", response = String.class)
    @RequestMapping("/")
    public String home() {
        return "index";
    }

    @InitBinder
    public void myInitBinder(WebDataBinder dataBinder) {
        Object target = dataBinder.getTarget();
        if (target == null) {
            return;
        }
        System.out.println("Target=" + target);

        if (target.getClass() == CartInfo.class) {

        }

        else if (target.getClass() == CustomerForm.class) {
            dataBinder.setValidator(customerFormValidator);
        }

    }
    @ApiOperation(value = "Page that appears when you try to acces a page without needed priveleges", response = String.class)
    @RequestMapping("/403")
    public String accessDenied() {
        return "/403";
    }




}