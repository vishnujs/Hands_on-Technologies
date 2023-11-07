package com.delibot.orderservice.services;

import com.delibot.orderservice.datasource.entities.*;
import com.delibot.orderservice.datasource.repositories.*;
import com.delibot.orderservice.util.UserType;
import com.delibot.orderservice.web.models.Order;
import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class OderServiceImplTest {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private FeedBackBasedRankingRepository feedBackBasedRankingRepository;

    @Autowired
    private DeliveryExecutiveStatusRepository deliveryExecutiveStatusRepository;

    @Autowired
    private OrderService orderService;

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private UserAuthorityRepository userAuthorityRepository;

    @Autowired
    private UserRepository userRepository;

    private Order order=null;

    private Long userId;

    private Long deliveryExecutiveId;

    private Long orderId;

    @BeforeAll
    void setUp() {

        UserEntity userEntity1 = new UserEntity();
        userEntity1.setAddress("trivandrum");
        userEntity1.setCreatedAt("2021-08-15");
        userEntity1.setEmail("testUserEntity@gmail.com");
        userEntity1.setFirstName("TestUserEntityCustomer");
        userEntity1.setLastName("Django");
        userEntity1.setPassword("a3876fafbc8b9b9d3820b6e3a610e3d2");
        userEntity1.setPhoneNumber("4567890987");
        userEntity1.setUserName("testcustomer");
        UserEntity persistedUser1 = userRepository.save(userEntity1);
        UserAuthorityEntity userAuthorityEntity1= new UserAuthorityEntity();
        userAuthorityEntity1.setUserType(UserType.CUSTOMER);
        userAuthorityEntity1.setUserId(persistedUser1.getUserId());
        userAuthorityRepository.save(userAuthorityEntity1);
        userId = persistedUser1.getUserId();


        UserEntity userEntity = new UserEntity();
        userEntity.setAddress("trivandrum");
        userEntity.setCreatedAt("2021-08-15");
        userEntity.setEmail("testUserEntity@gmail.com");
        userEntity.setFirstName("TestUserEntity");
        userEntity.setLastName("Django");
        userEntity.setPassword("a3876fafbc8b9b9d3820b6e3a610e3d2");
        userEntity.setPhoneNumber("4567890987");
        userEntity.setUserName("testuser");
        UserEntity persistedUser = userRepository.save(userEntity);
        UserAuthorityEntity userAuthorityEntity= new UserAuthorityEntity();
        userAuthorityEntity.setUserType(UserType.DELIVERY_EXECUTIVE);
        userAuthorityEntity.setUserId(persistedUser.getUserId());
        userAuthorityRepository.save(userAuthorityEntity);
        deliveryExecutiveId =persistedUser.getUserId();

        DeliveryExecutiveStatusEntity deliveryExecutiveStatus = new DeliveryExecutiveStatusEntity();
        deliveryExecutiveStatus.setUserId(deliveryExecutiveId);
        deliveryExecutiveStatus.setDeliveryExecutiveState(DeliveryExecutiveState.WORKING_WAITING_FOR_ORDER);
        deliveryExecutiveStatus.setUpdatedLocation("trivandrum");
        deliveryExecutiveStatusRepository.save(deliveryExecutiveStatus);

        FeedBackBasedRankingEntity feedBackBasedRankingEntity = new FeedBackBasedRankingEntity();
        feedBackBasedRankingEntity.setAverageRating(7);
        feedBackBasedRankingEntity.setUserId(deliveryExecutiveId);
        feedBackBasedRankingEntity.setTotalDeliveryCount(5);
        feedBackBasedRankingRepository.save(feedBackBasedRankingEntity);

        Order order = new Order();
        order.setDeliveryLocation("trivandrum");
        order.setUserId(userId);
        order.setStoreId(1234L);
        order.setOrderState(OrderState.ORDERED);
        order.setItemId(1235L);

        Optional<Order> persistedOrder = orderService.createOrder(order);
        orderId=persistedOrder.get().getOrderId();
    }

    @AfterAll
    void tearDown() {
        userRepository.deleteAll();
        userAuthorityRepository.deleteAll();
        deliveryExecutiveStatusRepository.deleteAll();
        feedBackBasedRankingRepository.deleteAll();
    }

    @Test
    @org.junit.jupiter.api.Order(1)
    void getOrderById() {
        Optional<Order> orderById = orderService.getOrderById(orderId);
        if(orderById.isPresent())
            Assert.assertTrue(orderById.get().getOrderId().equals(orderId));
        else
            Assert.fail();
    }

    @Test
    @org.junit.jupiter.api.Order(2)
    void createOrder() {
        Order order = new Order();
        order.setDeliveryLocation("trivandrum");
        order.setUserId(1L);
        order.setStoreId(1234L);
        order.setOrderState(OrderState.ORDERED);
        order.setItemId(1235L);

        Optional<Order> persistedOrder = orderService.createOrder(order);
        if(persistedOrder.isPresent())
            Assert.assertTrue(true);
        else
            Assert.fail();
    }

    @Test
    @org.junit.jupiter.api.Order(3)
    void updateOrder() {
        Order order = new Order();
        order.setOrderId(orderId);
        order.setDelivererID(deliveryExecutiveId);
        order.setItemId(12L);
        Optional<Order> peristedOrder = orderService.updateOrder(order);
        if(peristedOrder.isPresent())
            Assert.assertTrue(order.getOrderId()==orderId);
        else
            Assert.fail();
    }

    @Test
    @org.junit.jupiter.api.Order(4)
    void getOrderByUserId() {
        List<Order> orderByUserId = orderService.getOrderByUserId(userId);
        if(orderByUserId.isEmpty())
            Assert.fail();
        else
            Assert.assertTrue(true);
    }

    @Test
    @org.junit.jupiter.api.Order(5)
    void getOrderByDeliveryExecutiveId() {
        List<Order> orderByDeliveryExecutiveId = orderService.getOrderByDeliveryExecutiveId(deliveryExecutiveId);
        if (orderByDeliveryExecutiveId.isEmpty())
            Assert.fail();
        else
            Assert.assertTrue(true);
    }

    @Test
    @org.junit.jupiter.api.Order(6)
    void checkIfDeliveryStateHasChanged(){
        Optional<Long> delivererIdFromOrder = orderRepository.findById(orderId).map(OrderEntity::getDelivererID);
        Optional<DeliveryExecutiveState> deliveryExecutiveState = deliveryExecutiveStatusRepository.findById(delivererIdFromOrder.get()).map(DeliveryExecutiveStatusEntity::getDeliveryExecutiveState);
        if(deliveryExecutiveState.isPresent())
            Assert.assertTrue(!deliveryExecutiveState.get().toString().equals(DeliveryExecutiveState.WORKING_WAITING_FOR_ORDER.toString()));
        else
            Assert.fail();
    }
}