package com.kubermarket.customer.controller;


import com.kubermarket.customer.service.ProductService;
import domain.Product;
import dto.PopularProductDto;
import dto.ProductDto;
import error.ErrorAccess;
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

    @ResponseBody // 일반 유저가 조회할수 있는 기능
    @RequestMapping(value = "/product/{id}",method = RequestMethod.GET)
    @ApiOperation(value = "Product Detail (Client)", notes = "하나의 product를 상세하게 조회")
    public ProductDto DetailProduct(
            @Valid @PathVariable Long id){
        ProductDto productDto= productService.getDetailProduct(id);
        log.info(String.valueOf(productDto));
        return productDto;
    }

    @ResponseBody // 일반 유저가 조회할수 있는 기능
    @RequestMapping(value = "/products",method = RequestMethod.GET)
    @ApiOperation(value = "Products according to conditions (Client)",
            notes = "조건에 따른 product들을 조회 \n " +
                    "조건: popular,category,address,keyword,page \n" +
                    "클릭 한번당 8개의 list 조회")
    public List<? extends Object> productList(
            @RequestParam(value="popular", defaultValue = "false",required = false) boolean popular, //interest_count와 chat_count를 합산해서 보여준다.
            @RequestParam(value="category", defaultValue = "",required = false) String category, //address와 registerDate를 조합해서 보여준다.
            @RequestParam(value="address", defaultValue = "",required = false) String address, //address와 registerDate를 조합해서 보여준다.
            @RequestParam(value="keyword", defaultValue = "",required = false) String keyword, //이름이나 동네로 검색
            @RequestParam(value="page", defaultValue = "1",required = false) Integer pageNum ) {
        if (popular) { //인기 항목순으로 조회
            List<PopularProductDto> productList = productService.getPopularProducts(pageNum);
            //List<ProductDto> ProductDtoList = new ArrayList<>();
//            for (Object o : productList) {
//                Object[] result = (Object[]) o;
//                PopularProductDto popularProductDto = new PopularProductDto(
//                        Long.parseLong(String.valueOf(result[0])), Long.parseLong(String.valueOf(result[1])));
//                popularProductDtoList.add(popularProductDto);
//            }
            return productList;
        } else if (!category.equals("")) { //카테고리+최신으로 조회
            List<ProductDto> productDtoList = productService.getCategoryProducts(category, pageNum);
            return productDtoList;
        } else if (!address.equals("")) { //주소+최신으로 조회
            List<ProductDto> productDtoList = productService.getAddressProducts(address, pageNum);
            // List<AddressProductDto> addressProductDtoList = new ArrayList<>();
//            for(Object o : productList){
//                Object[] result= (Object[]) o;
//                AddressProductDto addressProductDto = new AddressProductDto(
//                        Long.parseLong(String.valueOf(result[0])), (LocalDateTime) result[1],
//                        String.valueOf(result[2])
//                );
//                addressProductDtoList.add(addressProductDto);
//            }
            return productDtoList;
        } else { //키워드로 조회
            log.info(keyword);
            List<ProductDto> productDtoList = productService.getKeywordProducts(keyword, pageNum);
            return productDtoList;
        }
    }

    //Content-Type은 multipart/form-data이고, form-data를 보내므로 @RequestBody 같은 어노테이션을 붙이면 안 됩니다.
    @ResponseBody
    @RequestMapping(value = "/product",method = RequestMethod.POST)//,consumes = {MediaType.MULTIPART_FORM_DATA_VALUE,MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "Product Add (Client)", notes = "product 추가")
    public ResponseEntity<?> addProduct(
            @Valid
            @RequestParam("title") String title,
            @RequestParam("content") String content,
            @RequestParam("price") Integer price,
            //@RequestParam("interestCount") Integer interestCount,
            @RequestParam("status") String status,
            @RequestParam("categoryName") String categoryName,
            @RequestParam(value = "files",required = false) List<MultipartFile> files,
            Authentication authentication
            ) throws URISyntaxException, IOException {
        if(authentication==null){
            throw new ErrorAccess();
        }
        Claims claims= (Claims) authentication.getPrincipal();
        String nickName = claims.get("nickName", String.class);
        LocalDateTime createDate = LocalDateTime.now();
        LocalDateTime updateDate = LocalDateTime.now();
        Product product = productService.addProduct(title, content, createDate, updateDate, price, status, categoryName, nickName, files);
        String url = "/api/product/" + product.getId();
        return ResponseEntity.created(new URI(url)).body("{}");

    }

    @ResponseBody
    @RequestMapping(value = "/product/{id}",method = RequestMethod.PATCH)
    @ApiOperation(value = "Product Update (Client)", notes = "product 수정")
    public String update(@PathVariable("id") Long id,
                         @Valid
                         @RequestParam("title") String title,
                         @RequestParam("content") String content,
                         @RequestParam("price") Integer price,
                         @RequestParam("interestCount") Integer interestCount,
                         @RequestParam("status") String status,
                         @RequestParam("categoryName") String categoryName,
                         @RequestParam(value = "files",required = false) List<MultipartFile> files
                         ,Authentication authentication
    ) throws URISyntaxException, IOException {
        if(authentication==null){
            throw new ErrorAccess();
        }
        Claims claims= (Claims) authentication.getPrincipal();
        String nickName = claims.get("nickName", String.class);
        LocalDateTime updateDate = LocalDateTime.now();
        Product product = productService.updateProduct(id,title, content,updateDate,price,interestCount,status,
                                                        categoryName,nickName,files);
        return "{수정완료}";
    }

    @ResponseBody
    @RequestMapping(value = "/product/{id}",method = RequestMethod.DELETE)
    @ApiOperation(value = "Product Delete (Client)", notes = "product 삭제")
    public String delete(
            @PathVariable("id") Long id,Authentication authentication
    ) throws URISyntaxException {
        if(authentication==null){
            throw new ErrorAccess();
        }
//        Claims claims= (Claims) authentication.getPrincipal();
//        String nickName = claims.get("nickName", String.class);
       productService.deleteProduct(id);
        return "{Product 삭제완료}";
    }

    @ResponseBody
    @RequestMapping(value = "/productImage/{imageId}",method = RequestMethod.DELETE)
    @ApiOperation(value = "Product Image Delete (Client)", notes = "product Image 따로 삭제")
    public String deleteImage(
            @PathVariable("imageId") Long imageId,
            Authentication authentication
    ) throws URISyntaxException {
        if(authentication==null){
            throw new ErrorAccess();
        }
        Claims claims= (Claims) authentication.getPrincipal();
        String nickName = claims.get("nickName", String.class);
        productService.deleteImage(imageId);
        return "{ProductImage 삭제완료}";
    }

}
