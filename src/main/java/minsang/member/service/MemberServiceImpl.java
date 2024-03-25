package minsang.member.service;

import lombok.RequiredArgsConstructor;
import minsang.member.domain.MemberDTO;
import minsang.member.entity.Members;
import minsang.member.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public MemberDTO getMember(long memberNo) {
        Optional<Members> membersOptional = memberRepository.findById(memberNo);
        if (membersOptional.isEmpty()) {
            return MemberDTO.emptyOf();
        }
        return MemberDTO.of(membersOptional.get());
    }

    @Override
    public String getUserName(long memberNo) {
        var member = memberRepository.findById(memberNo);
        return member.get().getMemberName();
    }
}
