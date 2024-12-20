package huce.edu.workmanagementprojectbackend.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@AllArgsConstructor
@Table(name = "Work_Progress")
public class WorkProgressEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @ManyToOne
  private TaskEntity task;

  @ManyToOne
  private EmployeeEntity employee;

  @Column(name = "work_progress_level")
  private byte workProgressLevel;

  @Column(name = "title")
  private String title;

  @Column(name = "content")
  private String content;

  @Column(name = "file_name")
  private String fileName;

  @Column(name = "create_date")
  private Date createDate;

  @Column(name = "update_date")
  private Date updateDate;

  @Column(name="create_user")
  private int createUser;

  @Column(name = "active")
  private boolean active;
}
