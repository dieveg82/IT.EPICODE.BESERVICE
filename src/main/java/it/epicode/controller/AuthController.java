
package it.epicode.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.epicode.model.Role;
import it.epicode.model.RoleType;
import it.epicode.model.User;
import it.epicode.security.JwtUtils;
import it.epicode.security.LoginRequest;
import it.epicode.security.LoginResponse;
import it.epicode.security.SignupRequest;
import it.epicode.security.SignupResponse;
import it.epicode.security.service.UserDetailsImpl;
import it.epicode.service.RoleService;
import it.epicode.service.UserService;

@RestController
@RequestMapping("/api")
public class AuthController {

	@Autowired
	RoleService roleService;
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	UserService userService;
	@Autowired
	JwtUtils jwtUtils;
	@Autowired
	PasswordEncoder encoder;

	
	@PostMapping("/login")
	public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
		// Usa l'AuthenticationManager per autenticare i parametri della request
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		// Ottiene i privilegi dell'utente
		authentication.getAuthorities();

		// Ottiene il SecurityContext
		SecurityContextHolder.getContext().setAuthentication(authentication);
		// Genera il token
		String jwt = jwtUtils.generateJwtToken(authentication);

		// getPrincipal(), ottiene i dati dell'utente
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());

		// Restituisce la response
		return ResponseEntity.ok(new LoginResponse(jwt, userDetails.getId(), userDetails.getUsername(),
				userDetails.getEmail(), roles, userDetails.getExpirationTime()));

	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@RequestBody SignupRequest signUpRequest) {
		// Verifica l'esistenza di Username e Email già registrate
		if (userService.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity.badRequest().body(new SignupResponse("Errore: Username già in uso!"));
		}
		if (userService.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity.badRequest().body(new SignupResponse("Errore: Email già in uso!"));
		}
		// Crea un nuovo user codificando la password
		User user = new User(null, signUpRequest.getUsername(), signUpRequest.getEmail(),
				encoder.encode(signUpRequest.getPassword()), signUpRequest.getNome(), signUpRequest.getCognome());
		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();
		// Verifica l'esistenza dei Role
		if (strRoles == null) {
			Role userRole = roleService.findByRoleType(RoleType.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Errore: Role non trovato!"));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					Role adminRole = roleService.findByRoleType(RoleType.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Errore: Role non trovato!"));
					roles.add(adminRole);
					break;
				default:
					Role userRole = roleService.findByRoleType(RoleType.ROLE_USER)
							.orElseThrow(() -> new RuntimeException("Errore: Role non trovato!"));
					roles.add(userRole);
				}
			});
		}
		user.setRoles(roles);
		userService.insertUser(user);
		return ResponseEntity.ok(new SignupResponse("User registrato con successo!"));
	}
}
