package book.book.security.auto.api;

import book.book.security.auto.dto.AuthResponse;
import book.book.security.auto.dto.RegisterRequest;
import book.book.security.auto.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {


    private final AuthService authService;
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(
            @RequestBody RegisterRequest registerRequest,
            @RequestHeader("Authorization") String authorizationHeader) {
        AuthResponse authenticationResponse = authService.register(registerRequest, authorizationHeader);
        return ResponseEntity.ok(authenticationResponse);
    }

    @PostMapping("/renew")
    public ResponseEntity<AuthResponse> renewToken(@RequestHeader("Authorization") String refreshTokenWithBearer) {
        AuthResponse authResponse = authService.renewAccessToken(refreshTokenWithBearer);
        return new ResponseEntity<>(authResponse, HttpStatus.OK);
    }





}
