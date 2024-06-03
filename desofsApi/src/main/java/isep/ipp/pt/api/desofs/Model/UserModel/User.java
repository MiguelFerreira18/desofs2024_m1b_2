package isep.ipp.pt.api.desofs.Model.UserModel;

import isep.ipp.pt.api.desofs.Model.Encomenda;
import isep.ipp.pt.api.desofs.Model.Review;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.*;

@Getter
@Setter
@Entity
@ToString
public class User implements UserDetails {



    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String userId;

    @Column(unique = true)
    @Email
    private String username;

    private String password;

    private String fullName;

    @ElementCollection
    private Set<Role> authorities = new HashSet<>();

    @Column(nullable = false, unique = true)
    private String nif;

    @Column(nullable = false)
    private String morada;

    protected User(){

    }

    //WITH ALL
    public User(String userId, String username, String password, String fullName, Set<Role> authorities, String nif, String morada) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.authorities = authorities;
        this.nif = nif;
        this.morada = morada;
    }
    //WITHOUT AUTHORITIES AND LISTAENCOMENDAS
    public User(String userId, String username, String password, String fullName, String nif, String morada) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.nif = nif;
        this.morada = morada;
    }

    // WITHOUT ID BUT WHIT AUTHORITIES
    public User(String username, String password, String fullName, Set<Role> authorities, String nif, String morada) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.authorities = authorities;
        this.nif = nif;
        this.morada = morada;

    }
    //WITHOUT ID AND AUTHORITIES
    public User(String username, String password, String fullName, String nif, String morada) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.nif = nif;
        this.morada = morada;
    }



    public void addAuthority(Role r){
        authorities.add(r);
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


    public User copy(PasswordEncoder encoder){
        return new User(this.userId, this.username, null, encoder.encode(this.fullName), this.authorities, encoder.encode(this.nif), encoder.encode(this.morada));
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("userId='").append(userId).append('\'');
        sb.append(", username='").append(username).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", fullName='").append(fullName).append('\'');
        sb.append(", authorities=").append(authorities);
        sb.append(", nif='").append(nif).append('\'');
        sb.append(", morada='").append(morada).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
