package miu.edu.attendance.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import miu.edu.attendance.dto.PersonRoleDto;
import miu.edu.attendance.dto.RegisterUserDto;
import miu.edu.attendance.dto.RoleDto;
import miu.edu.attendance.service.utils.PersonRoleUtils;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@NoArgsConstructor
@Getter
@ToString
@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(unique = true)
    String username;
    @Setter
    String password;
    String firstName;
    String lastName;
    String barcodeId;

    @JsonIgnore
    @OneToMany(mappedBy = "person", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    Set<PersonRole> roles = new HashSet<>();

    public Person(RegisterUserDto dto) {
        username = dto.getUsername();
        password = dto.getPassword();
        firstName = dto.getFirstName();
        lastName = dto.getLastName();
        barcodeId = UUID.randomUUID().toString();
    }

    public void updateRole(PersonRoleDto personRoleDto) {
        for(RoleDto roleDto : personRoleDto.getRoles()) {

            if(roleDto.getRole().equalsIgnoreCase("STUDENT")) {
                String argStudentId = roleDto.getProperties().get("studentId");
                Optional<PersonRole> existRoleOptional = roles.stream().filter(personRole -> personRole.getName().equalsIgnoreCase("STUDENT")).findAny();
                if(existRoleOptional.isPresent()) {
                    Student student = (Student)existRoleOptional.get();
                    if(argStudentId == null || !argStudentId.equalsIgnoreCase(student.studentId)) {
                        throw new IllegalArgumentException(String.format("Couldn't update role. Provided invalid student Id=%s", argStudentId));
                    }
                    existRoleOptional.get().setActive(roleDto.isActive());
                } else {
                    if(argStudentId == null) {
                        throw new IllegalArgumentException(String.format("Required studentId when assign student role to person id=%d.", id));
                    }
                    Student student = new Student(argStudentId);
                    addRole(student);
                }
            } else {
                Optional<PersonRole> existRoleOptional = roles.stream().filter(personRole -> personRole.getName().equalsIgnoreCase(roleDto.getRole())).findAny();
                if(existRoleOptional.isPresent()) {
                    existRoleOptional.get().setActive(roleDto.isActive());
                } else {
                    Class clazz = PersonRoleUtils.convertRoleNameToPersonRole(roleDto.getRole());
                    try {
                        PersonRole personRole = (PersonRole) clazz.getConstructor().newInstance();
                        addRole(personRole);
                    } catch (Exception ex) {
                        throw new RuntimeException(String.format("Can't create the person role name: %s. Exception: ", roleDto.getRole()), ex);
                    }
                }
            }
        }
    }

    public void addRole(PersonRole role) {
        Optional<PersonRole> roleOptional = roles.stream().filter(personRole -> personRole.getName().equalsIgnoreCase(role.getName())).findAny();
        if (roleOptional.isPresent()) {
            throw new IllegalArgumentException(String.format("Role name=%s already assigned to the personId=%d", role.getName(), id));
        }
        role.setPerson(this);
        roles.add(role);
    }

    private void revokeRole(PersonRole role) {
        Optional<PersonRole> roleOptional = roles.stream().filter(personRole -> personRole.getName().equalsIgnoreCase(role.getName())).findAny();
        if (!roleOptional.isPresent()) {
            throw new IllegalArgumentException(String.format("Role name=%s is not assigned to the personId=%d", role.getName(), id));
        }
        roleOptional.get().setActive(false);
    }

    public Optional<Student> asStudent() {
        for (PersonRole role : roles) {
            if (role.getName().equalsIgnoreCase("STUDENT") && role.active) {
                return Optional.of((Student) role);
            }
        }
        return Optional.empty();
    }

    public Optional<Faculty> asFaculty() {
        for (PersonRole role : roles) {
            if (role.getName().equalsIgnoreCase("FACULTY") && role.active) {
                return Optional.of((Faculty) role);
            }
        }
        return Optional.empty();
    }

    public Optional<Personnel> asPersonnel() {
        for (PersonRole role : roles) {
            if (role.getName().equalsIgnoreCase("PERSONNEL")&& role.active) {
                return Optional.of((Personnel) role);
            }
        }
        return Optional.empty();
    }
}
