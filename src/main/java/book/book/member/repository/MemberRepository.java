package book.book.member.repository;

import book.book.common.CustomException;
import book.book.common.ResultCode;
import book.book.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmail(String memberEmail);

    boolean existsById(Long id);

    Optional<Member> findByProviderId(String providerId);

    boolean existsByProviderId(String providerId);

    default Member findByEmailOrElseThrow(String email) {
        return findByEmail(email)
                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, ResultCode.MEMBER_NOT_FOUND));
    }

    default Member getByproviderIdOrElseThrow(String providerId) {
        return findByProviderId(providerId)
                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, ResultCode.MEMBER_NOT_FOUND));
    }
}