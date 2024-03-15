package com.group4.eshopping.dao;

import com.group4.eshopping.entity.ShopActivity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShopActivityDAO extends JpaRepository<ShopActivity, Integer> {
    ShopActivity findByShopIdAndActivityIdAndState(int shopId, int activityId, int state);

    List<ShopActivity> findByShopIdAndState(int shopId, int state);

    List<ShopActivity> findByActivityIdAndState(int activityId, int state);

    ShopActivity findByShopIdAndActivityIdAndIsApproved(int shopId, int activityId, int isApproved);

    List<ShopActivity> findByShopIdAndIsApproved(int shopId, int isApproved);

    List<ShopActivity> findByActivityIdAndIsApproved(int activityId, int isApproved);

    ShopActivity findByShopIdAndActivityId(int shopId, int activityId);

    List<ShopActivity> findByActivityId(int activityId);
}
