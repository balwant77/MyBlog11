package com.myblog.myblog11.service.impl;

import com.myblog.myblog11.entity.Comment;
import com.myblog.myblog11.entity.Post;
import com.myblog.myblog11.payload.CommentDto;
import com.myblog.myblog11.repository.CommentRepository;
import com.myblog.myblog11.repository.PostRepository;
import com.myblog.myblog11.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {
    private PostRepository postRepository;
    private CommentRepository commentRepository;
    private ModelMapper modelMapper;

    public CommentServiceImpl(PostRepository postRepository, CommentRepository commentRepository,ModelMapper modelMaper) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
        this.modelMapper=modelMaper;
    }

    @Override
    public CommentDto createComment(CommentDto commentDto, Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("Post not found by id" + postId));
        Comment comment=new Comment();
        comment.setEmail(commentDto.getEmail());
        comment.setText(commentDto.getText());
        comment.setPost(post);//The line comment.setPost(post); is setting the Post association for a Comment entity.

        Comment saved = commentRepository.save(comment);
        CommentDto dto=new CommentDto();
        dto.setId(saved.getId());
        dto.setEmail(saved.getEmail());
        dto.setText(saved.getText());


        return dto;
    }

    @Override
    public void deleteComment(long id) {
        commentRepository.deleteById(id);

    }



    @Override
    public CommentDto updateComment(CommentDto commentDto, long id,long postId) {
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new RuntimeException("Comment not found by id" + id));
        Post post = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("Post not find by the PostId" + id));
        Comment c = mapToEntity(commentDto);
        c.setId(comment.getId());
        c.setPost(post);
        Comment save = commentRepository.save(c);
        CommentDto comntDto = mapToDto(save);
        return comntDto;
    }
    Comment mapToEntity(CommentDto commentDto){
        Comment comment = modelMapper.map(commentDto, Comment.class);
        return comment;

    }
    CommentDto mapToDto(Comment comment){
        CommentDto dto = modelMapper.map(comment, CommentDto.class);
        return dto;
    }
}
