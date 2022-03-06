package com.maltsev.greenhouse.service;

import com.maltsev.greenhouse.model.RefreshToken;
import com.maltsev.greenhouse.repository.RefreshTokenRepository;
import com.maltsev.greenhouse.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenService {
    @Value("${app.jwtRefreshExpirationMs}")
    private Long refreshTokenDurationMs;

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Autowired
    private UserRepository userRepository;

    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    public RefreshToken createRefreshToken(Long userId){
        RefreshToken refreshToken = new RefreshToken();

        refreshToken.setUser(userRepository.findById(userId).get());
        refreshToken.setExpireDate(Instant.now().plusMillis(refreshTokenDurationMs));
        refreshToken.setToken(UUID.randomUUID().toString());

        refreshTokenRepository.deleteByUserId(userId);
        refreshToken = refreshTokenRepository.save(refreshToken);

        return refreshToken;
    }

    public RefreshToken verifyExpiration(RefreshToken refreshToken){
        if (refreshToken.getExpireDate().compareTo(Instant.now()) < 0){
            refreshTokenRepository.delete(refreshToken);
        }
        return refreshToken;
    }

    @Transactional
    public void deleteByUserId(Long userId){
        refreshTokenRepository.deleteByUserId(userId);
        return;
    }
}
