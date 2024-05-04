package isep.ipp.pt.api.desofs.Model.UserModel;

import isep.ipp.pt.api.desofs.Model.ListaEncomenda;
import isep.ipp.pt.api.desofs.Model.Review;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Getter
@Setter
@Entity
public class User implements UserDetails {

    @Id
    @GeneratedValue
    private Long userId;

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

    @OneToMany
    private List<ListaEncomenda> listaEncomendas;

    protected User(){

    }

    //WITH ALL
    public User(Long userId, String username, String password, String fullName, Set<Role> authorities, String nif, String morada, List<ListaEncomenda> listaEncomendas) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.authorities = authorities;
        this.nif = nif;
        this.morada = morada;
        this.listaEncomendas = listaEncomendas;
    }
    //WITHOUT AUTHORITIES AND LISTAENCOMENDAS
    public User(Long userId, String username, String password, String fullName, String nif, String morada) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.nif = nif;
        this.morada = morada;
        this.listaEncomendas = new LinkedList<>();
    }

    // WITHOUT ID BUT WHIT AUTHORITIES
    public User(String username, String password, String fullName, Set<Role> authorities, String nif, String morada, List<ListaEncomenda> listaEncomendas) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.authorities = authorities;
        this.nif = nif;
        this.morada = morada;
        this.listaEncomendas = listaEncomendas;
    }
    //WITHOUT ID AND AUTHORITIES
    public User(String username, String password, String fullName, String nif, String morada, List<ListaEncomenda> listaEncomendas) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.nif = nif;
        this.morada = morada;
        this.listaEncomendas = listaEncomendas;
    }

    public User(String username, String password, String fullName, String nif, String morada) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.nif = nif;
        this.morada = morada;
        listaEncomendas = new LinkedList<>();
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
}
