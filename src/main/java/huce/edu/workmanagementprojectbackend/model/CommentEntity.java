package huce.edu.workmanagementprojectbackend.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@AllArgsConstructor
@Table(name = "Comment")
public class CommentEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @ManyToOne
  private WorkProgressEntity workProgressEntity;

  @ManyToOne
  private EmployeeEntity employeeEntity;

  @Column(name = "content")
  private String content;

  @Column(name = "create_date")
  private Date createDate;

  @Column(name = "update_date")
  private Date updateDate;

  @Column(name = "delete_date")
  private String deleteDate;

  @Column(name = "active")
  private boolean active;
}
