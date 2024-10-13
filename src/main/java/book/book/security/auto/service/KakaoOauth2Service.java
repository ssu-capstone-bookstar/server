package book.book.security.auto.service;

import book.book.security.oauth.KakaoUserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;


import java.util.Map;

@Service
@RequiredArgsConstructor
public class KakaoOauth2Service extends DefaultOAuth2UserService {
    private static final String KAKAO_USER_INFO_URI = "https://kapi.kakao.com/v2/user/me";
    public KakaoUserInfo getUserInfo(String oauth2AccessToken) {
        WebClient webClient = WebClient.create();

        Map<String, Object> attributes = webClient.get()
                .uri(KAKAO_USER_INFO_URI)
                .headers(httpHeaders -> httpHeaders.setBearerAuth(oauth2AccessToken))
                .retrieve()
                .bodyToMono(Map.class)
                .block();

        return new KakaoUserInfo(attributes);

    }
}
