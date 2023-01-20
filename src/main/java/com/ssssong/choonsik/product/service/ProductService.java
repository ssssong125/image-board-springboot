package com.ssssong.choonsik.product.service;

import com.ssssong.choonsik.common.paging.SelectCriteria;
import com.ssssong.choonsik.product.dao.ProductMapper;
import com.ssssong.choonsik.product.dto.ProductDTO;
import com.ssssong.util.FileUploadUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
@Slf4j
@Service
public class ProductService {


    @Value("${image.image-dir}")
    private String IMAGE_DIR;
    @Value("${image.image-url}")
    private String IMAGE_URL;

    private final ProductMapper productMapper;

    public ProductService(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    public ProductDTO selectProduct(String productCode) {
        log.info("[ProductService] selectProduct Start ===================================");
        ProductDTO productDto = productMapper.selectProduct(productCode);
        productDto.setProductImageUrl(IMAGE_URL + productDto.getProductImageUrl());
        log.info("[ProductService] selectProduct End ===================================");
        return productDto;
    }


    public int selectProductTotal() {
        log.info("[ProductService] selectProductTotal Start ===================================");
        int result = productMapper.selectProductTotal();

        log.info("[ProductService] selectProductTotal End ===================================");
        return result;
    }

    public Object selectProductListWithPaging(SelectCriteria selectCriteria) {
        log.info("[ProductService] selectProductListWithPaging Start ===================================");
        List<ProductDTO> productList = productMapper.selectProductListWithPaging(selectCriteria);

        for(int i = 0 ; i < productList.size() ; i++) {
            productList.get(i).setProductImageUrl(IMAGE_URL + productList.get(i).getProductImageUrl());
        }
        log.info("[ProductService] selectProductListWithPaging End ===================================");
        return productList;
    }

    public Object selectProductListWithPagingForAdmin(SelectCriteria selectCriteria) {
        log.info("[ProductService] selectProductListWithPagingForAdmin Start ===================================");
        List<ProductDTO> productList = productMapper.selectProductListWithPagingForAdmin(selectCriteria);

        for(int i = 0 ; i < productList.size() ; i++) {
            productList.get(i).setProductImageUrl(IMAGE_URL + productList.get(i).getProductImageUrl());
        }
        log.info("[ProductService] selectProductListWithPagingForAdmin End ===================================");
        return productList;
    }


    public Object selectProductForAdmin(String productCode) {
        log.info("[ProductService] selectProductForAdmin Start ===================================");
        ProductDTO productDto = productMapper.selectProductForAdmin(productCode);
        productDto.setProductImageUrl(IMAGE_URL + productDto.getProductImageUrl());
        log.info("[ProductService] selectProductForAdmin End ===================================");
        return productDto;
    }

    public int selectProductTotalForAdmin() {
        log.info("[ProductService] selectProductTotal Start ===================================");
        int result = productMapper.selectProductTotalForAdmin();

        log.info("[ProductService] selectProductTotal End ===================================");
        return result;
    }


    @Transactional
    public Object updateProduct(ProductDTO productDto) {
        log.info("[ProductService] updateProduct Start ===================================");
        log.info("[ProductService] productDto : " + productDto);
        String replaceFileName = null;
        int result = 0;

        try {
            String oriImage = productMapper.selectProduct(String.valueOf(productDto.getProductCode())).getProductImageUrl();
            log.info("[updateProduct] oriImage : " + oriImage);

            if(productDto.getProductImage() != null){
                // 이미지 변경 진행
                String imageName = UUID.randomUUID().toString().replace("-", "");
                replaceFileName = FileUploadUtils.saveFile(IMAGE_DIR, imageName, productDto.getProductImage());

                log.info("[updateProduct] IMAGE_DIR!!"+ IMAGE_DIR);
                log.info("[updateProduct] imageName!!"+ imageName);

                log.info("[updateProduct] InsertFileName : " + replaceFileName);
                productDto.setProductImageUrl(replaceFileName);

                log.info("[updateProduct] deleteImage : " + oriImage);
                boolean isDelete = FileUploadUtils.deleteFile(IMAGE_DIR, oriImage);
                log.info("[update] isDelete : " + isDelete);
            } else {
                // 이미지 변경 없을 시
                productDto.setProductImageUrl(oriImage);
            }

            result = productMapper.updateProduct(productDto);

        } catch (IOException e) {
            log.info("[updateProduct] Exception!!");
            FileUploadUtils.deleteFile(IMAGE_DIR, replaceFileName);
            throw new RuntimeException(e);
        }
        log.info("[ProductService] updateProduct End ===================================");
        log.info("[ProductService] result > 0 성공: "+ result);

        return (result > 0) ? "상품 업데이트 성공" : "상품 업데이트 실패";
    }

    @Transactional
    public String insertProduct(ProductDTO productDto) {
        log.info("[ProductService] insertProduct Start ===================================");
        log.info("[ProductService] productDto : " + productDto);
        String imageName = UUID.randomUUID().toString().replace("-", "");
        String replaceFileName = null;
        int result = 0;
        log.info("[ProductService] IMAGE_DIR : " + IMAGE_DIR);
        log.info("[ProductService] imageName : " + imageName);
        try {
            replaceFileName = FileUploadUtils.saveFile(IMAGE_DIR, imageName, productDto.getProductImage());
            log.info("[ProductService] replaceFileName : " + replaceFileName);

            productDto.setProductImageUrl(replaceFileName);

            log.info("[ProductService] insert Image Name : "+ replaceFileName);

            result = productMapper.insertProduct(productDto);

        } catch (IOException e) {
            log.info("[ProductService] IOException IMAGE_DIR : "+ IMAGE_DIR);

            log.info("[ProductService] IOException deleteFile : "+ replaceFileName);

            FileUploadUtils.deleteFile(IMAGE_DIR, replaceFileName);
            throw new RuntimeException(e);
        }
        log.info("[ProductService] result > 0 성공: "+ result);
        return (result > 0) ? "상품 입력 성공" : "상품 입력 실패";
    }


    public List<ProductDTO> selectProductListAboutMeal() {
        log.info("[ProductService] selectProductListAboutMeal Start ===================================");

        List<ProductDTO> productListAboutMeal = productMapper.selectProductListAboutMeal();

        for(int i = 0 ; i < productListAboutMeal.size() ; i++) {
            productListAboutMeal.get(i).setProductImageUrl(IMAGE_URL + productListAboutMeal.get(i).getProductImageUrl());
        }

        log.info("[ProductService] selectProductListAboutMeal End ==============================");

        return productListAboutMeal;
    }

    public List<ProductDTO> selectProductListAboutDessert() {
        log.info("[ProductService] selectProductListAboutDessert Start ===================================");

        List<ProductDTO> productListAboutDessert = productMapper.selectProductListAboutDessert();

        for(int i = 0 ; i < productListAboutDessert.size() ; i++) {
            productListAboutDessert.get(i).setProductImageUrl(IMAGE_URL + productListAboutDessert.get(i).getProductImageUrl());
        }

        log.info("[ProductService] selectProductListAboutDessert End ==============================");

        return productListAboutDessert;
    }

    public List<ProductDTO> selectProductListAboutBeverage() {

        log.info("[ProductService] selectProductListAboutBeverage Start ===================================");

        List<ProductDTO> productListAboutBeverage = productMapper.selectProductListAboutBeverage();

        for(int i = 0 ; i < productListAboutBeverage.size() ; i++) {
            productListAboutBeverage.get(i).setProductImageUrl(IMAGE_URL + productListAboutBeverage.get(i).getProductImageUrl());
        }

        log.info("[ProductService] selectProductListAboutBeverage End ==============================");

        return productListAboutBeverage;
    }


    public List<ProductDTO> selectSearchProductList(String search) {
        log.info("[ProductService] selectSearchProductList Start ===================================");
        log.info("[ProductService] searchValue : " + search);
        List<ProductDTO> productListWithSearchValue = productMapper.productListWithSearchValue(search);
        log.info("[ProductService] productListWithSearchValue : " + productListWithSearchValue);

        for(int i = 0 ; i < productListWithSearchValue.size() ; i++) {
            productListWithSearchValue.get(i).setProductImageUrl(IMAGE_URL + productListWithSearchValue.get(i).getProductImageUrl());
        }
        log.info("[ProductService] selectSearchProductList End ===================================");

        return productListWithSearchValue;
    }



}
