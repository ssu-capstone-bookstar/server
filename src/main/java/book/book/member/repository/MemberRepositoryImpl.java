package book.book.member.repository;

import book.book.image.ImageCategory;
import book.book.member.dto.MinimmumMemberProfile;
import book.book.member.dto.QMinimmumMemberProfile;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import static book.book.image.QImage.image;
import static book.book.member.entity.QMember.member;

@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    @Override
    public MinimmumMemberProfile findNickNameAndImage(Long memberId) {
        return queryFactory
                .select(new QMinimmumMemberProfile(
                        member.nickName,
                        image.path
                ))
                .from(member)
                .leftJoin(image)
                .on(member.id.eq(image.domainId)
                        .and(image.imageCategory.eq(ImageCategory.MEMBER_PROFILE)))
                .where(member.id.eq(memberId))
                .fetchOne();
    }
}
