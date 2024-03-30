package minsang.member.repository;


import minsang.member.entity.Members;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Members, Long> {

    Members findByMemberIdAndPassword(String memberId, String password);
}
