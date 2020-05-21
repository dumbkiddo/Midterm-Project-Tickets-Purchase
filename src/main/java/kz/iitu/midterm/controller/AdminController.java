package kz.iitu.midterm.controller;

import io.swagger.annotations.ApiOperation;
import kz.iitu.midterm.entity.Movie;
import kz.iitu.midterm.form.MovieForm;
import kz.iitu.midterm.model.OrderDetailInfo;
import kz.iitu.midterm.model.OrderInfo;
import kz.iitu.midterm.pagination.PaginationResult;
import kz.iitu.midterm.repository.MovieRepo;
import kz.iitu.midterm.repository.OrderRepo;
import kz.iitu.midterm.validator.MovieFormValidator;
import org.apache.commons.lang.exception.ExceptionUtils;
//import org.apache.tomcat.util.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import io.swagger.annotations.Api;

import java.util.List;

@Controller
@Transactional
@Api(value = "Administrator Controller class", description = "This class allows to control the data flow of Admin panel")
public class AdminController {

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private MovieRepo movieRepo;

    @Autowired
    private MovieFormValidator movieFormValidator;

    @InitBinder
    public void myInitBinder(WebDataBinder dataBinder) {
        Object target = dataBinder.getTarget();
        if (target == null) {
            return;
        }
        System.out.println("Target=" + target);

        if (target.getClass() == MovieForm.class) {
            dataBinder.setValidator(movieFormValidator);
        }
    }

    @ApiOperation(value = " [GET] - View the account details of Admin", response = String.class)
    @RequestMapping(value = { "/admin/accountInfo" }, method = RequestMethod.GET)
    public String accountInfo(Model model) {

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(userDetails.getPassword());
        System.out.println(userDetails.getUsername());
        System.out.println(userDetails.isEnabled());

        model.addAttribute("userDetails", userDetails);
        return "accountInfo";
    }

    @ApiOperation(value = " [GET] - View the list of orders (bookings made by users)", response = String.class)
    @RequestMapping(value = { "/admin/orderList" }, method = RequestMethod.GET)
    public String orderList(Model model, //
                            @RequestParam(value = "page", defaultValue = "1") String pageStr) {
        int page = 1;
        try {
            page = Integer.parseInt(pageStr);
        } catch (Exception e) {
        }
        final int MAX_RESULT = 5;
        final int MAX_NAVIGATION_PAGE = 10;

        PaginationResult<OrderInfo> paginationResult //
                = orderRepo.listOrderInfo(page, MAX_RESULT, MAX_NAVIGATION_PAGE);

        model.addAttribute("paginationResult", paginationResult);
        return "orderList";
    }

    @ApiOperation(value = " [GET] - View the details of movie from the list", response = String.class)
    @RequestMapping(value = { "/admin/movie" }, method = RequestMethod.GET)
    public String movie(Model model, @RequestParam(value = "code", defaultValue = "") String code) {
        MovieForm movieForm = null;

        if (code != null && code.length() > 0) {
            Movie movie = movieRepo.findMovie(code);
            if (movie != null) {
                movieForm = new MovieForm(movie);
            }
        }
        if (movieForm == null) {
            movieForm = new MovieForm();
            movieForm.setNewMovie(true);
        }
        model.addAttribute("movieForm", movieForm);
        return "movie";
    }

    @ApiOperation(value = " [POST] - Save the edited movie details", response = String.class)
    @RequestMapping(value = { "/admin/movie" }, method = RequestMethod.POST)
    public String productSave(Model model, //
                              @ModelAttribute("movieForm") @Validated MovieForm movieForm, //
                              BindingResult result, //
                              final RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            return "movie";
        }
        try {
            movieRepo.save(movieForm);
        } catch (Exception e) {
            Throwable rootCause = ExceptionUtils.getRootCause(e);
            String message = rootCause.getMessage();
            model.addAttribute("errorMessage", message);
            // Show product form.
            return "movie";
        }

        return "redirect:/movieList";
    }

    @ApiOperation(value = " [GET] - View the details of a certain order", response = String.class)
    @RequestMapping(value = { "/admin/order" }, method = RequestMethod.GET)
    public String orderView(Model model, @RequestParam("orderId") String orderId) {
        OrderInfo orderInfo = null;
        if (orderId != null) {
            orderInfo = this.orderRepo.getOrderInfo(orderId);
        }
        if (orderInfo == null) {
            return "redirect:/admin/orderList";
        }
        List<OrderDetailInfo> details = this.orderRepo.listOrderDetailInfos(orderId);
        orderInfo.setDetails(details);

        model.addAttribute("orderInfo", orderInfo);

        return "order";
    }

}