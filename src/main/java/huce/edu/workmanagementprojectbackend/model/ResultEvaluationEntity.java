package huce.edu.workmanagementprojectbackend.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@AllArgsConstructor
@Table(name = "Result_Evaluation")
public class ResultEvaluationEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @ManyToOne
  private AssignmentEntity assignmentEntity;

  @Column(name = "evaluation_level")
  private byte evaluationLevel;

  @Column(name = "content")
  private String content;

  @Column(name = "create_date")
  private Date createDate;

  @Column(name = "update_date")
  private Date updateDate;

  @Column(name = "note")
  private String note;

  @Column(name = "active")
  private boolean active;
}
