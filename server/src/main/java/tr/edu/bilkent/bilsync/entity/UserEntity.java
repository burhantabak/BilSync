package tr.edu.bilkent.bilsync.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import tr.edu.bilkent.bilsync.dto.UserDto;

import java.util.Collection;
import java.util.List;

/**
 * The UserEntity class represents a user entity in the application.
 * It implements the UserDetails interface to integrate with Spring Security.
 * This class is annotated with JPA annotations for entity mapping.
 */
@Entity
@Table(name = "app_user")
public class UserEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;
    private String bio;

    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private boolean isBanned;

    @Column
    private String profileImageName = "OUR_DEFAULT_IMAGE_PATH";

    public UserType getAccountType() {
        return accountType;
    }

    public void setAccountType(UserType accountType) {
        this.accountType = accountType;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    private UserType accountType;
    public boolean isBanned() {
        return isBanned;
    }

    public void setBanned(boolean banned) {
        isBanned = banned;
    }

    /**
     * Default constructor for UserEntity.
     */
    public UserEntity() {}

    /**
     * Constructor to create a UserEntity with specified email, password, and name.
     *
     * @param email    The user's email address.
     * @param password The user's password.
     * @param name     The user's name.
     */
    public UserEntity(String email, String password, String name) {
        this.password = password;
        this.name = name;
        this.email = email;
        this.isBanned=false;
    }

    /**
     * Constructor to create a UserEntity from a UserDto.
     *
     * @param dto The UserDto containing user information.
     */
    public UserEntity(UserDto dto)
    {
        this.isBanned=false;
        this.name= dto.getName();
        this.email=dto.getEmail();
        this.accountType=dto.getAccountType();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("User"));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfileImageName() { return profileImageName; }

    public void setProfileImageName(String profileImagePath) { this.profileImageName = profileImagePath; }
}
