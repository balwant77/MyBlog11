package com.myblog.myblog11.controller;

import com.myblog.myblog11.payload.PostDto;
import com.myblog.myblog11.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
//import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {


   private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    //To post data to the database
    //@PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto){
        PostDto dto = postService.createPost(postDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }
    //http://localhost:8082/api/posts/particular?id=1
    @GetMapping ("particular")
    public ResponseEntity<PostDto>findPostById(@RequestParam long id){
        PostDto dto=new PostDto();
        PostDto dataById = postService.findDataById(id);
        return new ResponseEntity<>(dataById,HttpStatus.OK);

    }
    //To get all the data from database.Here we are also using table pagination
    //http://localhost:8082/api/posts?pageNo=0&pageSize=3&sortBy=title&sortDir=asc
    @GetMapping
    public List<PostDto>getAllPost(@RequestParam(name = "pageNo",required = false,defaultValue = "0") int pageNo,// This annotation is used to extract the value of the "pageNo" query parameter from the request URL. If the parameter is not present, it defaults to 0.
    @RequestParam(name="pageSize",required = false,defaultValue = "3") int pageSize ,
    @RequestParam (name="sortBy",required = false,defaultValue = "id") String sortBy,
    @RequestParam(name="sortDir",required = false,defaultValue = "id")String sortDir){
      List<PostDto> postDtos = postService.getAllPost(pageNo,pageSize,sortBy,sortDir);

      return postDtos;

    }


}
