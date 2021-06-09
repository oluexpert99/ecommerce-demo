package com.tinybeans.ecommerce.controller;

import com.tinybeans.ecommerce.domain.data.ProductDTO;
import com.tinybeans.ecommerce.domain.data.SalesOrderDTO;
import com.tinybeans.ecommerce.service.ProductService;
import com.tinybeans.ecommerce.service.SalesOrderService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/** User: ayoade_farooq@yahoo.com Date: 08/06/2021 Time: 04:06 */
@Controller
@RequestMapping("/products")
public class ProductController {
  @Value("${stripe.public.key}")
  private String stripePublicKey;

  private ProductService productService;
  private SalesOrderService salesOrderService;

  @Autowired
  public void setSalesOrderService(SalesOrderService salesOrderService) {
    this.salesOrderService = salesOrderService;
  }

  @Autowired
  public void setProductService(ProductService productService) {
    this.productService = productService;
  }

  @GetMapping
  public ModelAndView getAllProducts() {
    List<ProductDTO> products = productService.getAllProducts();
    return new ModelAndView("products", "products", products);
  }

  @GetMapping("/{id}/checkout")
  public String productCheckout(@PathVariable Long id, Model model) {
    ProductDTO product = productService.getProductById(id);
    model.addAttribute("amount", product.getProductPrice() * 100); // In cents
    model.addAttribute("productId", id); // In cents
    model.addAttribute("stripePublicKey", stripePublicKey);
    model.addAttribute("product", product);
    return "product-checkout";
  }

  @PostMapping(value = "/charge")
  public String chargeCard(HttpServletRequest request, Model model) throws Exception {
    String token = request.getParameter("stripeToken");
    double amount = Double.parseDouble(request.getParameter("amount"));
    long productId = Long.parseLong(request.getParameter("productId"));
    String zipCode = request.getParameter("zipCode");
    String address = request.getParameter("address");
    String state = request.getParameter("state");
    String city = request.getParameter("city");
    String country = request.getParameter("country");
    SalesOrderDTO salesOrderDTO=  salesOrderService.createSalesOrder(token,amount, productId,zipCode, address, state, country, city);

    model.addAttribute("orderId", StringUtils.leftPad(""+salesOrderDTO.getId(),6,'0'));
    model.addAttribute("transactionRef", salesOrderDTO.getTransactionRef());
    return "product-checkout-confirmation";
  }
}
