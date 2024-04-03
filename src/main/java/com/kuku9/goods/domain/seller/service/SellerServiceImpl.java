package com.kuku9.goods.domain.seller.service;

import static com.kuku9.goods.global.exception.ExceptionStatus.INVALID_PRODUCT_EVENT;
import static com.kuku9.goods.global.exception.ExceptionStatus.INVALID_SELLER_EVENT;

import com.kuku9.goods.domain.order_product.repository.OrderProductRepository;
import com.kuku9.goods.domain.product.entity.Product;
import com.kuku9.goods.domain.product.repository.ProductRepository;
import com.kuku9.goods.domain.seller.dto.request.ProductQuantityRequest;
import com.kuku9.goods.domain.seller.dto.request.ProductRegistRequest;
import com.kuku9.goods.domain.seller.dto.request.ProductUpdateRequest;
import com.kuku9.goods.domain.seller.dto.response.SoldProductQuantityResponse;
import com.kuku9.goods.domain.seller.dto.response.SoldProductResponse;
import com.kuku9.goods.domain.seller.dto.response.SoldProductSumPriceResponse;
import com.kuku9.goods.domain.seller.entity.Seller;
import com.kuku9.goods.domain.seller.repository.SellerQuery;
import com.kuku9.goods.domain.seller.repository.SellerRepository;
import com.kuku9.goods.domain.user.entity.User;
import com.kuku9.goods.global.exception.InvalidProductEventException;
import com.kuku9.goods.global.exception.InvalidSellerEventException;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class SellerServiceImpl implements SellerService {

    private final SellerRepository sellerRepository;
    private final ProductRepository productRepository;
    private final OrderProductRepository orderProductRepository;
    private final SellerQuery sellerQuery;

    @Override
    @Transactional
    public Long createProduct(ProductRegistRequest requestDto, User user) {
        Seller seller = findSeller(user);

        Product product = new Product(requestDto, seller);

        Product saveProduct = productRepository.save(product);

        return saveProduct.getSeller().getId();
    }

    @Override
    @Transactional
    public Long updateProductStatus(Long productId, User user) {
        Seller seller = findSeller(user);

        Product product = findProduct(productId, seller);

        product.updateOrderStatus(product.getStatus());

        return product.getSeller().getId();
    }

    @Override
    @Transactional
    public Long updateProductQuantity(Long productId, ProductQuantityRequest request, User user) {
        Product product = findProduct(productId, findSeller(user));

        product.updateQuantitySeller(request);

        return product.getSeller().getId();
    }

    @Override
    @Transactional
    public Long updateProduct(
        Long productId, ProductUpdateRequest requestDto, User user) {
        Product product = findProduct(productId, findSeller(user));

        product.updateProduct(requestDto);

        return product.getSeller().getId();
    }

    @Override
    @Transactional(readOnly = true)
    public List<SoldProductResponse> getSoldProduct(
        User user, LocalDate startDate, LocalDate endDate) {
        return sellerQuery.getSoldProduct(findSeller(user), startDate, endDate);
    }

    @Override
    @Transactional(readOnly = true)
    public SoldProductSumPriceResponse getSoldProductSumPrice(
        User user, LocalDate startDate, LocalDate endDate) {
        return sellerQuery.getSoldProductSumPrice(findSeller(user), startDate, endDate);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SoldProductQuantityResponse> getSoldProductQuantityTopTen(
        User user, LocalDate startDate, LocalDate endDate) {
        return sellerQuery.getSoldProductQuantityTopTen(findSeller(user), startDate, endDate);
    }

    private Seller findSeller(User user) {
        return sellerRepository.findByUserId(user.getId()).orElseThrow(() ->
            new InvalidSellerEventException(INVALID_SELLER_EVENT));
    }

    private Product findProduct(Long productId, Seller seller) {
        return productRepository.findByIdAndSellerId(productId, seller.getId()).orElseThrow(() ->
            new InvalidProductEventException(INVALID_PRODUCT_EVENT));
    }

    @Override
    public Seller save(Seller seller) {
        return sellerRepository.save(seller);
    }

    @Override
    public Boolean checkSellerExistsByUserId(Long userId) {
        return sellerRepository.existsByUserId(userId);
    }

    @Override
    public Boolean checkBrandNameExist(String brandName) {
        return sellerRepository.existsByBrandName(brandName);
    }

    @Override
    public Boolean checkDomainNameExist(String domainName) {
        return sellerRepository.existsByDomainName(domainName);
    }

    @Override
    public Boolean checkEmailExist(String email) {
        return sellerRepository.existsByEmail(email);
    }

    @Override
    public Boolean checkPhoneNumberExist(String phoneNumber) {
        return sellerRepository.existsByPhoneNumber(phoneNumber);
    }

}
