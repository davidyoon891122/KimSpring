package com.example.demo.service;

import com.example.demo.domain.Member;
import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.MemoryMemberRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public class MemberService {

    private MemberRepository repository;

    public MemberService(MemberRepository memberRepository) {
        this.repository = memberRepository;
    }

    // 회원 가입
    public Long join(Member member) {
        validateDuplicatedMember(member); // 중복 회원 검증
        repository.save(member);
        return member.getId();
    }

    private void validateDuplicatedMember(Member member) {
        repository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }


    public List<Member> findMembers() {
        return repository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return repository.findById(memberId);
    }

}
