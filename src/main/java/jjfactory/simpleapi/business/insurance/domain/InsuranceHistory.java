package jjfactory.simpleapi.business.insurance.domain;


import jjfactory.simpleapi.business.rider.domain.Rider;
import jjfactory.simpleapi.global.entity.BaseEntity;
import jjfactory.simpleapi.global.entity.DeleteStatus;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Getter
public class InsuranceHistory extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "rider_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Rider rider;

    private int insuranceStep;

    @Enumerated(EnumType.STRING)
    private HistoryType historyType;

    @Builder
    public InsuranceHistory(LocalDateTime createDate, LocalDateTime modifiedDate, DeleteStatus deleteStatus, LocalDateTime deleteDate, Rider rider, int insuranceStep, HistoryType historyType) {
        super(createDate, modifiedDate, deleteStatus, deleteDate);
        this.rider = rider;
        this.insuranceStep = insuranceStep;
        this.historyType = historyType;
    }

    public static InsuranceHistory create(Rider rider, HistoryType type, int insuranceStep){
        return InsuranceHistory.builder()
                .rider(rider)
                .historyType(type)
                .insuranceStep(insuranceStep)
                .build();
    }
}
