package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
// 관례적, 구현체가 하나만 있을 때는 뒤에 impl을 붙임
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Autowired // 스프링 빈 등록 시 의존관계 자동 주입을 위해
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    // for test
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
