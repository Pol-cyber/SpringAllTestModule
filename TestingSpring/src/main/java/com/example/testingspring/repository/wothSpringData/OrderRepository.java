package com.example.testingspring.repository.wothSpringData;

import com.example.testingspring.DataClass.ObjectForTaco.TacoOrder;
import com.example.testingspring.DataClass.ObjectForTaco.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface OrderRepository extends CrudRepository<TacoOrder,Integer>{

    List<TacoOrder> findByCcCVV(String ccCVV);

    @Query(value = "SELECT tO FROM TacoOrder tO")
    List<TacoOrder> findOrdersWithQuery();

    @Query("SELECT o FROM TacoOrder o WHERE o.deliveryZip = :zip")
    List<TacoOrder> findOrdersByDeliveryZip(@Param("zip") String zip);

    List<TacoOrder> findByUserOrderByDateDesc(User user, Pageable pageable);

}
