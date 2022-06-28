package huce.edu.workmanagementprojectbackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@AllArgsConstructor
@Table(name = "Assignment_Department")
public class AssignmentDepartmentEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @ManyToOne
  private ProjectEntity project;

  @ManyToOne
  private DepartmentEntity department;

  @Column(name="create_user")
  private int createUser;

  @Column(name = "create_date")
  private Date createDate;

  @Column(name = "update_date")
  private Date updateDate;

  @Column(name = "active")
  private boolean active;
}
