package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService{

//    private final MemberRepository memberRepository = new MemoryMemberRepository();

    private final MemberRepository memberRepository;

    // constructor를 통해서 어떤 구현객체를 할당하던지 생성해줄 수 있다.


    //AppConfig와 달리 @Component 만 스캔하여 MemberServiceImpl을 스프링 빈으로 등록하기 때문에
    //어떤 구현체를 주입할 지 명시할 수 없다. @Autowired를 사용하여 의존성을 주입해줄 수 있다.
    @Autowired
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

    // 테스트 용도로 생성한다.
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
