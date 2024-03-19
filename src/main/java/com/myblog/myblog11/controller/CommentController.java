package com.myblog.myblog11.controller;

import com.myblog.myblog11.payload.CommentDto;
import com.myblog.myblog11.service.CommentService;
import com.myblog.myblog11.service.impl.CommentServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comment")
public class CommentController {
    CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    //http://localhost:8082/api/comment?postId=1
    @PostMapping
    public ResponseEntity<CommentDto>createComment(@RequestBody CommentDto commentDto, @RequestParam long postId){

        CommentDto comment = commentService.createComment(commentDto, postId);
        return new ResponseEntity<>(comment, HttpStatus.CREATED);


    }
    //http://localhost:8082/api/comment/1
    @DeleteMapping("/{id}")
    public ResponseEntity<String>deleteComment(@PathVariable long id){
        commentService.deleteComment(id);
        return new ResponseEntity<>("Comment is deleted!!",HttpStatus.OK);

    }
    //http://localhost:8082/api/comment/1/postId/1
    @PutMapping("/{id}/post/{postId}")
    public ResponseEntity<CommentDto>updateComment(@PathVariable long id,@RequestBody CommentDto commentDto,@PathVariable long postId){
        CommentDto commentDto1 = commentService.updateComment(commentDto, id, postId);
        return new ResponseEntity<>(commentDto1,HttpStatus.OK);


    }
}
