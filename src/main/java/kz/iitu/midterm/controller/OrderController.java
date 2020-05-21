package kz.iitu.midterm.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kz.iitu.midterm.model.CartInfo;
import kz.iitu.midterm.repository.OrderRepo;
import kz.iitu.midterm.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
@Transactional
@Api(value = "Order (Booking) Controller class", description = "This class allows to control the data flow of booking")
public class OrderController {

    @Autowired
    private OrderRepo orderDAO;


    @ApiOperation(value = " [POST] - Update the details of an order", response = String.class)
    @RequestMapping(value = { "/shoppingCart" }, method = RequestMethod.POST)
    public String shoppingCartUpdateQty(HttpServletRequest request, //
                                        Model model, //
                                        @ModelAttribute("cartForm") CartInfo cartForm) {

        CartInfo cartInfo = Session.getCartInSession(request);
        cartInfo.updateQuantity(cartForm);

        return "redirect:/shoppingCart";
    }

    @ApiOperation(value = " [GET] - View the order (cart)", response = String.class)
    @RequestMapping(value = { "/shoppingCart" }, method = RequestMethod.GET)
    public String shoppingCartHandler(HttpServletRequest request, Model model) {
        CartInfo myCart = Session.getCartInSession(request);

        model.addAttribute("cartForm", myCart);
        return "shoppingCart";
    }

    @ApiOperation(value = " [GET] - Confirm the booking ", response = String.class)
    @RequestMapping(value = { "/shoppingCartConfirmation" }, method = RequestMethod.GET)
    public String shoppingCartConfirmationReview(HttpServletRequest request, Model model) {
        CartInfo cartInfo = Session.getCartInSession(request);

        if (cartInfo == null || cartInfo.isEmpty()) {

            return "redirect:/shoppingCart";
        } else if (!cartInfo.isValidCustomer()) {

            return "redirect:/shoppingCartCustomer";
        }
        model.addAttribute("myCart", cartInfo);

        return "shoppingCartConfirmation";
    }


    @ApiOperation(value = " [POST] - Save to the list and send order to admin", response = String.class)
    @RequestMapping(value = { "/shoppingCartConfirmation" }, method = RequestMethod.POST)

    public String shoppingCartConfirmationSave(HttpServletRequest request, Model model) {
        CartInfo cartInfo = Session.getCartInSession(request);

        if (cartInfo.isEmpty()) {

            return "redirect:/shoppingCart";
        } else if (!cartInfo.isValidCustomer()) {

            return "redirect:/shoppingCartCustomer";
        }
        try {
            orderDAO.saveOrder(cartInfo);
        } catch (Exception e) {

            return "shoppingCartConfirmation";
        }

        Session.removeCartInSession(request);

        Session.storeLastOrderedCartInSession(request, cartInfo);

        return "redirect:/shoppingCartFinalize";
    }

    @ApiOperation(value = " [GET] - Complete and Receive the number of your order", response = String.class)
    @RequestMapping(value = { "/shoppingCartFinalize" }, method = RequestMethod.GET)
    public String shoppingCartFinalize(HttpServletRequest request, Model model) {

        CartInfo lastOrderedCart = Session.getLastOrderedCartInSession(request);

        if (lastOrderedCart == null) {
            return "redirect:/shoppingCart";
        }
        model.addAttribute("lastOrderedCart", lastOrderedCart);
        return "shoppingCartFinalize";
    }

}
