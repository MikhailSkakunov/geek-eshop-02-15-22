package ru.geekbrains.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.controller.dto.ProductDto;
import ru.geekbrains.sevice.ProductService;

import java.util.Optional;

@RestController
@RequestMapping("/v1/product")
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/all")
    public Page<ProductDto> findAll(
            @RequestParam("categoryId") Optional<Long> categoryId,
            @RequestParam("brandId") Optional<Long> brandId,
            @RequestParam("namePattern") Optional<String> namePattern,
            @RequestParam("page") Optional<Integer> page,
            @RequestParam("size") Optional<Integer> size,
            @RequestParam("sortField") Optional<String> sortField, Model model) {
         return productService.findAll(
                categoryId,
                brandId,
                namePattern,
                page.orElse(1) - 1,
                size.orElse(5),
                sortField.filter(fld -> !fld.isBlank()).orElse("id"));
    }

//    @ModelAttribute
//    public void addAttributes(Model model) {
//        model.addAttribute("activePage", "Product");
//    }
//
//    @GetMapping("/new")
//    public String newProductForm(Model model) {
//        logger.info("New product page requested");
//
//        model.addAttribute("product", new ProductDto());
//        model.addAttribute("categories", categoryService.findAll());
//        return "product_form";
//    }
//
//    @GetMapping("/{id}")
//    public String editProduct(@PathVariable("id") Long id, Model model) {
//        logger.info("Edit product page requested");
//
//        model.addAttribute("product", productService.findById(id)
//                .orElseThrow(() -> new NotFoundException("Product not found")));
//        model.addAttribute("categories", categoryService.findAll());
//        return "product_form";
//    }
//
//    @PostMapping
//    public String update(@Valid @ModelAttribute("product") ProductDto product, BindingResult result, Model model) {
//        logger.info("Saving product");
//
//        if (result.hasErrors()) {
//            logger.error(result.getAllErrors().toString());
//            model.addAttribute("categories", categoryService.findAll());
//            return "product_form";
//        }
//        productService.save(product);
//        return "redirect:/product";
//    }
//
//    @DeleteMapping("/{id}")
//    public String deleteProduct(@PathVariable("id") Long id) {
//        logger.info("Deleting product with id {}", id);
//
//        productService.deleteById(id);
//        return "redirect:/product";
//    }
//
//    @ExceptionHandler
//    public ModelAndView notFoundExceptionHandler(NotFoundException ex) {
//        ModelAndView modelAndView = new ModelAndView("not_found");
//        modelAndView.addObject("message", ex.getMessage());
//        modelAndView.setStatus(HttpStatus.NOT_FOUND);
//        return modelAndView;
//    }

}
