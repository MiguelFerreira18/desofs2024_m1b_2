package isep.ipp.pt.api.desofs.Authentication;

import isep.ipp.pt.api.desofs.Dto.UserDTO.ControllerLayer.UserDTOSignup;
import isep.ipp.pt.api.desofs.Mapper.UserMapper.UserMapper;
import isep.ipp.pt.api.desofs.Model.UserModel.SignInRequest;
import isep.ipp.pt.api.desofs.Model.UserModel.SignUpRequest;
import isep.ipp.pt.api.desofs.Model.UserModel.User;
import isep.ipp.pt.api.desofs.Model.UserModel.UserView;
import isep.ipp.pt.api.desofs.Service.UserService.UserService;
import isep.ipp.pt.api.desofs.Utils.DatabaseLogger;
import isep.ipp.pt.api.desofs.Utils.LoggerStrategy;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

import static java.lang.String.format;
import static java.util.stream.Collectors.joining;

@RestController
@RequestMapping(path = "auth/public")
public class AuthenticationApi {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtEncoder jwtEncoder;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private LoggerStrategy logger;
    @Autowired
    private PasswordEncoder encoder;

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationApi.class);


    @PostMapping("login")
    public ResponseEntity<UserView> login(@RequestBody @Valid final SignInRequest request) {
        try {
            final Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.username(), request.password()));
            final User user = (User) authentication.getPrincipal();
            final Instant now = Instant.now();
            final long expiry = 36000L;

            final String scope = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                    .collect(joining(" "));

            final JwtClaimsSet claims = JwtClaimsSet.builder().issuer("example.io").issuedAt(now)
                    .expiresAt(now.plusSeconds(expiry)).subject(format("%s,%s", user.getUserId(), user.getUsername()))
                    .claim("roles", scope).build();

            final String token = this.jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
            logger.logAuthentication("Successful login for user: " );
            return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, token).body(mapToUSerView(user));
        } catch (final BadCredentialsException ex) {
            logger.logAuthentication("Failed login attempt for user: " + request.copy(encoder));
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
    private UserView mapToUSerView(User user) {
        return new UserView(user.getUserId(), user.getUsername(), user.getFullName(), user.getAuthorities());
    }

    @PostMapping("signup")
    public ResponseEntity<UserView> signup(@RequestBody @Valid final SignUpRequest request) {
        UserDTOSignup u = userMapper.fromSignUpRequestToUserDTOSignup(request);
        UserView userView = userService.registerUser(u);
        if (userView == null) {
            logger.logAuthentication("Failed signup attempt for user: " + request.copy(encoder));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } else {
            return ResponseEntity.ok(userView);
        }
    }
}
