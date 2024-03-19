package com.myblog.myblog11.service.impl;

import com.myblog.myblog11.Exception.ResourceNotFoundException;
import com.myblog.myblog11.entity.Post;
import com.myblog.myblog11.payload.PostDto;
import com.myblog.myblog11.repository.PostRepository;
import com.myblog.myblog11.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;
    private ModelMapper modelMapper;

    public PostServiceImpl(PostRepository postRepository,ModelMapper modelMapper) {
        this.postRepository = postRepository;
        this.modelMapper=modelMapper;
    }

    @Override
    public PostDto createPost(PostDto postDto) {
        //PostDto class will take data from postman and give it to entity(Post)class to save it to database, because only entity class data can store the data to DB
        //After saving PostDto will take the data from Entity class and give it to user as a response through ResponseEntity class.
        Post post=mapToEntity(postDto);//Here we are taking data from postman tool and saving data to database.To create a entry
        // To convert this postdto data into post object we are calling mapToEntity method and passing postDto data to it.And logic of conversion is written in this method.
        Post savedPost = postRepository.save(post);//After converting postDto object to post object we are saving data to database.

        PostDto dto = mapToDto(savedPost);//we are giving method reference here It will redirect to mapToDto method . This method will automatically convert this post object to dto object.
        //By this method will become reusable
        return dto;
    }


    @Override
    public PostDto findDataById(long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("no record found with id" + id));
        PostDto dto = new PostDto();
        dto.setId(post.getId());
        dto.setTitle(post.getTitle());
        dto.setDescription(post.getDescription());
        dto.setContent(post.getContent());
        return dto;
    }



    @Override
    public List<PostDto> getAllPost(int pageNo, int pageSize, String sortBy, String sortDir) {
        //Using ternary operator
        Sort sort=(sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()))?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();//using equalsIgnoreCase string comparison will not happen. It is case-insensitive.
        //sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) checks if the provided sorting direction(sortDir=asc) (case-insensitive) is equal to the string representation of ascending sorting.
       //For descending matching condition
        // Sort sort=(sortDir.equalsIgnoreCase(Sort.Direction.DESC.name()))?Sort.by(sortBy).descending():Sort.by(sortBy).ascending();
        Pageable pageable=PageRequest.of(pageNo,pageSize,sort);// pageable has the information of page no and page size
        Page<Post> pagePost = postRepository.findAll(pageable);//use pageable option instead of simple findAll. findAll wil use the pageable information to fetch the data.It will return data in the form of page<Post>.

        List<Post> posts = pagePost.getContent();// to return back the content from pagePost in the form of list.

        List<PostDto> dtos = posts.stream().map(p -> mapToDto(p)).collect(Collectors.toList());
        return dtos;//it is returning the data stored in the dtos(Object of PostDto)to calling statement i.e getAllPost method in controller layer.

    }

    //We cant return list of post we have to return list postdtos. so we will build a method and will supply post object and return type of this will be postdto.
//    PostDto mapToDto(Post post) {//converting entity to dto
//        PostDto dto = new PostDto();
//        dto.setId(post.getId());
//        dto.setTitle(post.getTitle());
//        dto.setDescription(post.getDescription());
//        dto.setContent(post.getContent());
//        return dto;
//
//    }

    //Using ModelMapper
    PostDto mapToDto(Post post) {
        PostDto map = modelMapper.map(post, PostDto.class);
        return map;
    }
//    Post mapToEntity(PostDto postDto) {//convert dto to entity
//        Post post = new Post();
//        post.setTitle(postDto.getTitle());
//        post.setDescription(postDto.getDescription());
//        post.setContent(postDto.getContent());
//        return post;
//
//
//    }

    //Using ModelMapper
    Post mapToEntity(PostDto postDto) {
        Post map = modelMapper.map(postDto, Post.class);
        return map;
    }
}
