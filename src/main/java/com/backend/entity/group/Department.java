package com.backend.entity.group;

import com.backend.entity.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("DEPARTMENT")  // group_type이 "DEPARTMENT"인 경우
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
public class Department extends Group {
    @Column(name = "department_status")
    private int status;  // 부서 관련 상태

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private List<User> users = new ArrayList<>();

    @Column(name = "department_name")
    private String name;

    @OneToOne(mappedBy = "department")
    private DepartmentLeader departmentLeader;
}