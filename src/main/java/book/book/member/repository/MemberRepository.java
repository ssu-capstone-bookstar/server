package book.book.member.repository;

import book.book.common.CustomException;
import book.book.common.ResultCode;
import book.book.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findById(Long memberId);
    Optional<Member> findByEmail(String memberEmail);

    boolean existsById(Long id);

    Optional<Member> findByProviderId(String providerId);

    boolean existsByProviderId(String providerId);

    default Member findByIdOrElseThrow(Long id) {
        return findById(id)
                .orElseThrow(() -> new CustomException(ResultCode.MEMBER_NOT_FOUND));
    }
    default Member findByEmailOrElseThrow(String email) {
        return findByEmail(email)
                .orElseThrow(() -> new CustomException(ResultCode.MEMBER_NOT_FOUND));
    }

    default Member findByproviderIdOrElseThrow(String providerId) {
        return findByProviderId(providerId)
                .orElseThrow(() -> new CustomException(ResultCode.MEMBER_NOT_FOUND));
    }
}