package controller;

import com.example.kubermarket.dto.PopularProductDto;
import com.example.kubermarket.dto.ProductDto;
import com.example.kubermarket.service.ProductService;
import domain.Product;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.io.Serializable;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.List;

//TODO
//추후에 인기 검색어 개발 redis의 sorted set 활용해서 가장 많이 검색된 단어를 sort해서 보여주는 방식
@Slf4j
@Controller
@RequestMapping(value = "/api")
public class ProductController implements Serializable {

    public final ProductService productService;
    public final CacheManager cacheManager;

    @Autowired
    public ProductController(ProductService productService, CacheManager cacheManager) {
        this.productService = productService;
        this.cacheManager=cacheManager;
    }

    @ResponseBody //모든 상품을 조회할수 있는 관리자 기능
    @RequestMapping(value = "/allproducts",method = RequestMethod.GET)
    @ApiOperation(value = "AllProduct (관리자)", notes = "모든 product 조회")
    public List<Product> AllProductList(){
        List<Product> products = productService.getProducts();
        return products;
    }

}
