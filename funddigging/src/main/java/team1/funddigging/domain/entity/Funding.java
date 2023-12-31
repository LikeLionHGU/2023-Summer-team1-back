package team1.funddigging.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import team1.funddigging.application.dto.FundingDto;
//import com.likelion.backend.domain.entity.common.BaseEntity;
import lombok.*;
import org.hibernate.annotations.SQLDelete;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
//@Where(clause = "deleted = false")
@SQLDelete(sql = "UPDATE funding SET deleted = true WHERE funding_id = ?")
public class Funding {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long funding_id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Room room_id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Member seller_user_id;


    private String funding_title;

    private String title_image;

    private Double goal_amount;

    private Double price;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String funding_image;

    @Column(columnDefinition = "TEXT")
    private String funding_budget;

    @Column(columnDefinition = "TEXT")
    private String funding_schedule;

    @Column(columnDefinition = "TEXT")
    private String team_introduction;

    private String created_date;

    private String start_date;

    private String end_date;

    private String budget_date;

    private Integer like_num;

    private Integer is_active;

    private Double current_amount;
    private Integer backers_count;
    private Double progress;









    public static Funding toFunding(FundingDto dto, Room room, Member user) {



        return Funding.builder()
                .room_id(room)
                .seller_user_id(user)
                .funding_title(dto.getFunding_title())
                .title_image(dto.getTitle_image())
                .goal_amount(dto.getGoal_amount())
                .price(dto.getPrice())
                .description(dto.getDescription())
                .funding_image(dto.getFunding_image())
                .funding_budget(dto.getFunding_budget())
                .funding_schedule(dto.getFunding_schedule())
                .team_introduction(dto.getTeam_introduction())
                .created_date(dto.getCreated_date())
                .start_date(dto.getStart_date())
                .end_date(dto.getEnd_date())
                .budget_date(dto.getBudget_date())
                .like_num(dto.getLike_num())
                .is_active(dto.getIs_active())
                .backers_count(dto.getBackers_count())
                .current_amount(dto.getCurrent_amount())
                .progress(dto.getProgress())
                .build();


    }





}
