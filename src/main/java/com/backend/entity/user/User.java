package com.backend.entity.user;

import com.backend.dto.request.admin.user.PatchAdminUserApprovalDto;
import com.backend.dto.response.GetAdminUsersApprovalRespDto;
import com.backend.dto.response.GetAdminUsersDtailRespDto;
import com.backend.dto.response.GetAdminUsersRespDto;
import com.backend.dto.response.UserDto;
import com.backend.entity.calendar.CalendarMapper;
import com.backend.entity.group.GroupMapper;
import com.backend.util.Role;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "user_status")
    private Integer status; // 상태

    @Column(name = "uid")
    private String uid; // 유아이디

    @Column(name = "pwd")
    private String pwd; // 비밀번호

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role; // 역할

    @Column(name = "level")
    private Integer level;

    @Column(name = "grade")
    private Integer grade; // 결제등급 enum 변경

    @Column(name = "email")
    private String email; 

    @Column(name = "hp")
    private String hp;

    @Column(name = "name")
    private String name;

    @Column(name = "city")
    private String city; 

    @Column(name = "country")
    private String country;

    @Column(name = "address")
    private String address;

    @Column(name = "company")
    private String company;

    @Column(name = "payment_token")
    private String paymentToken; // 결제정보

    @Column(name = "payment_day")
    private String day;

    @Column(name = "refresh_token")
    private String refreshToken;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    @ToString.Exclude
    private List<GroupMapper> groupMappers;

    @Column(name = "create_at")
    @CreationTimestamp
    private LocalDateTime createAt;

    @Column(name = "last_login")
    private LocalDateTime lastLogin;

    @Column(name = "join_date")
    private LocalDate joinDate;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "user")
    private ProfileImg profileImg;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    @ToString.Exclude
    private List<Attendance> attendance;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    @ToString.Exclude
    private List<CalendarMapper> calendars;

    public GetAdminUsersRespDto toGetAdminUsersRespDto() {
        return GetAdminUsersRespDto.builder()
                .email(email)
                .uid(uid)
                .id(id)
                .build();
    }

    public GetAdminUsersDtailRespDto toGetAdminUsersDtailRespDto() {
        String levelString;
        switch (level) {
            case 1:
                levelString = "사원";
                break;
            case 2:
                levelString = "주임";
                break;
            case 3:
                levelString = "대리";
                break;
            case 4:
                levelString = "과장";
                break;
            case 5:
                levelString = "차장";
                break;
            case 6:
                levelString = "부장";
                break;
            default:
                levelString = "Unknown";  // Handle unexpected levels
                break;
        }
        String yearMonth = this.todaysYearMonth();
        Attendance attendance1 = attendance.stream().filter(v->v.getYearMonth().equals(yearMonth)).findFirst().get();
        String todayAttendance;
        if(attendance1.getStatus()==1){
            todayAttendance = "결근";
        }else if(attendance1.getStatus()==2){
            todayAttendance = "출근";
        } else {
            todayAttendance = "퇴사";
        }
        return GetAdminUsersDtailRespDto.builder()
                .email(email)
                .level(levelString)
                .attendance(todayAttendance)
                .createAt("아직 안뽑")
                .status(status)
                .name(name)
                .id(id)
                .build();
    }

    public GetAdminUsersApprovalRespDto toGetAdminUsersApprovalRespDto() {
        return GetAdminUsersApprovalRespDto.builder()
                .id(id)
                .createAt("아직 안뽑")
                .email(email)
                .name(name)
                .uid(uid)
                .build();
    }

    public void updateRole(Role role) {
        this.role = role;
    }

    public void patchUserApproval(PatchAdminUserApprovalDto dto) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(dto.getJoinDate(), formatter);
        this.status = 1;
        this.joinDate = date;
        this.level = dto.getLevel();
    }

    private String todaysYearMonth(){
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");
        return date.format(formatter);
    }


    public UserDto toDto() {
        return UserDto.builder()
                .id(this.id)
                .status(this.status)
                .uid(this.uid)
                .pwd(this.pwd)
                .role(this.role)
                .level(this.level)
                .grade(this.grade)
                .email(this.email)
                .hp(this.hp)
                .name(this.name)
                .city(this.city)
                .country(this.country)
                .address(this.address)
                .company(this.company)
                .paymentToken(this.paymentToken)
                .day(this.day)
                .refreshToken(this.refreshToken)
                .groupMappers(this.groupMappers)
                .profileImg(this.profileImg != null ? this.profileImg.getSName() : "default.png") // 기본값 설정
                .createAt(this.createAt)
                .lastLogin(this.lastLogin)
                .joinDate(this.joinDate)
                .attendance(this.attendance)
                .build();
    }

    public UserDto toSliceDto() {

        String sname = profileImg != null ? profileImg.getSName() : ""; // null 체크 추가
        return UserDto.builder()
                .id(this.id)
                .uid(this.uid)
                .role(this.role)
                .level(this.level)
                .grade(this.grade)
                .email(this.email)
                .name(this.name)
                .company(this.company)
                .profileImg(sname)
                .createAt(this.createAt)
                .build();
    }
}
