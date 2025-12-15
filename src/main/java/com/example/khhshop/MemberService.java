package com.example.khhshop;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;


    public void register(String username,
                         String password,
                         String displayName){
        Member member = new Member();
        member.setUsername(username);
        String hash = passwordEncoder.encode(password);
        member.setPassword(hash);
        member.setDisplayName(displayName);
        memberRepository.save(member);
    }

    public Member login(String username, String password){
        Optional<Member> member = memberRepository.findByUsername(username);
        return member.orElse(null);
    }

}
