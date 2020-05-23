package com.iac.webshop.services;

        import com.iac.webshop.models.Discount;
        import com.iac.webshop.models.Product;
        import com.iac.webshop.repositories.IDiscountRepository;
        import com.iac.webshop.repositories.IProductRepository;
        import com.iac.webshop.services.interfaces.IDiscountService;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Service;

        import java.util.List;
        import java.util.Optional;

@Service
public class DiscountService implements IDiscountService {

    @Autowired
    IDiscountRepository discountRepository;

    @Autowired
    IProductRepository productRepository;

    @Override
    public List<Discount> getDiscounts() {
        return discountRepository.findAll();
    }

    @Override
    public Discount addDiscount(Discount discount, Long productID) {
        Optional<Product> product = productRepository.findById(productID);
        discount.setProduct(product.get());
        return discountRepository.save(discount);
    }
}
