package huce.edu.workmanagementprojectbackend.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@AllArgsConstructor
@Table(name = "Department")
public class DepartmentEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "name_department")
  private String name;

  @Column(name = "create_date")
  private Date createDate;

  @Column(name = "update_date")
  private Date updateDate;

  @Column(name="create_user")
  private int createUser;

  @Column(name = "note")
  private String note;

  @Column(name = "active")
  private boolean active;
}
