package jjfactory.simpleapi.business.delivery.domain;


import jjfactory.simpleapi.business.delivery.dto.req.DeliveryCreate;
import jjfactory.simpleapi.business.rider.domain.Rider;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Delivery {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "rider_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Rider rider;
    private String deliveryId;
    @Embedded
    private Address address;
    @Comment("요청사 명")
    private String clientName;
    @Comment("요청사로부터 요청받은 콜 키 값")
    @Column(length = 16)
    private String receiveCallId;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus deliveryStatus;

    @Comment(value = "주문 요청시간")
    private LocalDateTime requestTime;

    @Comment(value = "배차시간")
    private LocalDateTime appointTime;

    @Comment(value = "픽업시간")
    private LocalDateTime pickUpTime;

    @Comment(value = "배달 완료시간")
    private LocalDateTime completeTime;

    @LastModifiedDate
    private LocalDateTime modifiedDate;
    @Builder
    public Delivery(Rider rider, String deliveryId, Address address, String clientName, String receiveCallId, DeliveryStatus deliveryStatus, LocalDateTime requestTime, LocalDateTime appointTime, LocalDateTime pickUpTime, LocalDateTime completeTime, LocalDateTime modifiedDate) {
        this.rider = rider;
        this.deliveryId = deliveryId;
        this.address = address;
        this.clientName = clientName;
        this.receiveCallId = receiveCallId;
        this.deliveryStatus = deliveryStatus;
        this.requestTime = requestTime;
        this.appointTime = appointTime;
        this.pickUpTime = pickUpTime;
        this.completeTime = completeTime;
        this.modifiedDate = modifiedDate;
    }

    public static Delivery create(Rider rider, DeliveryCreate dto){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        return Delivery
                .builder()
                .rider(rider)
                .address(Address.builder()
                        .deliveryAddress1(dto.getDeliveryAddress1())
                        .deliveryAddress2(dto.getDeliveryAddress2())
                        .pickUpAddress1(dto.getPickUpAddress1())
                        .pickUpAddress2(dto.getPickUpAddress2())
                        .build())
                .receiveCallId(dto.getReceiveId())
                .clientName(dto.getClientName())
                .requestTime(LocalDateTime.parse(dto.getRequestTime(),formatter))
                .appointTime(LocalDateTime.parse(dto.getAppointTime(),formatter))
                .deliveryStatus(DeliveryStatus.REQUEST)
                .build();
    }

    public void pickUp() {
        this.pickUpTime = LocalDateTime.now();
    }
    public void complete(LocalDateTime completeTime) {
        this.completeTime = completeTime;
    }

}
