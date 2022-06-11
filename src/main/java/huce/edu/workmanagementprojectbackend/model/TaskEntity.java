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
@Table(name = "Task")
public class TaskEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @ManyToOne
  private ProjectEntity projectEntity;

  @Column(name = "title")
  private String title;

  @Column(name = "content")
  private String content;

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