package us.devfast.cheetah.controller.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import us.devfast.cheetah.controller.BaseController;
import us.devfast.cheetah.dto.BlogDto;
import us.devfast.cheetah.entity.BlogEntity;
import us.devfast.cheetah.entity.ResourceNotFoundException;
import us.devfast.cheetah.service.BlogService;
import us.devfast.cheetah.service.UserService;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/blog")
@CrossOrigin("http://localhost:3000")
public class BlogController extends BaseController {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private BlogService blogService;

    /**
     * Message error log in file
     */
    private final static String MESSAGE_ERROR_LOG = "UserController error !";

    @GetMapping("/get/all")
    public List<BlogDto> getAllBlogs() {
        return blogService.getAllBlogs().stream().map(blog -> modelMapper.map(blog, BlogDto.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<BlogDto> getBlogById(@PathVariable(name = "id") int id) throws ResourceNotFoundException {
        BlogEntity blog = blogService.getBlogById(id);

        BlogDto blogResponse = modelMapper.map(blog, BlogDto.class);

        return ResponseEntity.ok().body(blogResponse);
    }

    @PostMapping("/add")
    public ResponseEntity<BlogDto> postBlog(@Valid @RequestBody BlogDto blogDto) {
        BlogEntity blogRequest = modelMapper.map(blogDto, BlogEntity.class);
        BlogEntity blog = blogService.createBlog(blogRequest);
        BlogDto blogResponse = modelMapper.map(blog, BlogDto.class);
        return new ResponseEntity<BlogDto>(blogResponse, HttpStatus.CREATED);
    }

    @PutMapping("/put/{id}")
    public ResponseEntity<BlogDto> updateBlog(@PathVariable int id, @RequestBody BlogDto blogDto) throws ResourceNotFoundException {
        // convert DTO to Entity
        BlogEntity blogRequest = modelMapper.map(blogDto, BlogEntity.class);

        BlogEntity blog = blogService.updateBlog(id, blogRequest);

        // entity to DTO
        BlogDto blogResponse = modelMapper.map(blog, BlogDto.class);

        return ResponseEntity.ok().body(blogResponse);
    }

    @DeleteMapping("/delete/{id}")
    public Map<String, Boolean> deleteBlog(@PathVariable(value = "id") int id) throws ResourceNotFoundException {
        blogService.deleteBlog(id);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

}
