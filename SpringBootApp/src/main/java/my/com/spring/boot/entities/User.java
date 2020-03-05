package my.com.spring.boot.entities;

import my.com.spring.boot.entities.enums.GenderUser;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class User extends AuditEntity{

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Email
    private String email;

    @Size(max = 50)
    private String fullname;

    private Date birthday;

    private GenderUser genderUser;

    private String address;

    @Size(max = 20)
    private String mobile;

    @Type(type = "text")
    private String about;

    @ManyToMany(fetch = FetchType.LAZY,
        cascade = {
                CascadeType.PERSIST,
                CascadeType.MERGE
        }
    )
    @JoinTable(
        name = "user_role",
        joinColumns = {@JoinColumn(name = "user_id")},
        inverseJoinColumns = {@JoinColumn(name = "role_id")}
    )
    Set<Role> roles = new HashSet<>();

    public User() {

    }
    public User(String username, String password, String fullName,
                String email, String mobile) {
        this.username = username;
        this.password = password;
        this.fullname = fullName;
        this.email = email;
        this.mobile = mobile;
    }



    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public GenderUser getGenderUser() {
        return genderUser;
    }

    public void setGenderUser(GenderUser genderUser) {
        this.genderUser = genderUser;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
