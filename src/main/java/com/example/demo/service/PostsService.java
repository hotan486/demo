package com.example.demo.service;

import com.example.demo.domain.Posts;
import com.example.demo.domain.PostsRepository;
import com.example.demo.dto.PostsResponseDto;
import com.example.demo.dto.PostsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsSaveRequestDto requestDto) {

        Posts posts = postsRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    public PostsResponseDto findById(Long id){

        return new PostsResponseDto(postsRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException(
                        "해당사용자가 없습니다. id=" + id )));
    }


}
