package huce.edu.workmanagementprojectbackend.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Data
@Setter
@Getter
@NoArgsConstructor
@Entity
@AllArgsConstructor
@Table(name = "Assignment")
public class AssignmentEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @ManyToOne
  private TaskEntity taskEntity;

  @ManyToOne
  private EmployeeEntity employeeEntity;

  @Column(name = "deadline")
  private Date deadline;

  @Column(name = "begin_date")
  private Date beginDate;

  @Column(name = "end_date")
  private Date endDate;

  @Column(name = "create_date")
  private Date createDate;

  @Column(name = "update_date")
  private Date updateDate;

  @Column(name = "note")
  private String note;

  @Column(name = "active")
  private boolean active;
}
