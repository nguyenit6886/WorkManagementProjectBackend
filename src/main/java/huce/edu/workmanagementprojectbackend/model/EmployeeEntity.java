package huce.edu.workmanagementprojectbackend.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Builder
@Data
@NoArgsConstructor
@Entity
@AllArgsConstructor
@Table(name = "Employee")
public class EmployeeEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @ManyToOne
  private DepartmentEntity department;

  @Column(name = "first_name")
  private String firstName;

  @Column(name = "last_name")
  private String lastName;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  @Column(name = "birthday")
  private Date birthday;

  @Column(name = "address_employee")
  private String address;

  @Column(name = "email")
  private String email;

  @Column(name = "phone")
  private String phone;

  @Column(name = "username")
  private String username;

  @Column(name = "password_hash")
  private String passwordHash;

  @Column(name = "position")
  private byte position;

  @Column(name = "note")
  private String note;

  @Column(name = "create_date")
  private Date createDate;

  @Column(name = "update_date")
  private Date updateDate;

  @Column(name = "active")
  private boolean active;
}
