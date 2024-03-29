package com.no3.game.repository;

import com.no3.game.dto.CartDetailDto;
import com.no3.game.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    @Transactional
    Optional<CartItem> deleteByCartId(Long cartId);

    CartItem findByCartIdAndItemId(Long cartId, Long itemId);


    @Query("select new com.no3.game.dto.CartDetailDto(ci.id, i.title, i.price, im.imgUrl) " +
            "from CartItem ci, ItemImg im " +
            "join ci.item i " +
            "where ci.cart.id = :cartId " +
            "and im.item.id = ci.item.id " +
            "and im.repimgYn = 'Y' " +
            "order by ci.regTime desc"
    )
    List<CartDetailDto> findCartDetailDtoList(@Param("cartId") Long cartId);
}
