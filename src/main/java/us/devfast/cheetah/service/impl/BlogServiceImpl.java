package us.devfast.cheetah.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.devfast.cheetah.entity.BlogEntity;
import us.devfast.cheetah.entity.ResourceNotFoundException;
import us.devfast.cheetah.repository.BlogRepository;
import us.devfast.cheetah.service.BlogService;

@Service
public class BlogServiceImpl implements BlogService {

    private final BlogRepository blogRepository;

    public BlogServiceImpl(BlogRepository blogRepository) {
        super();
        this.blogRepository = blogRepository;
    }

    @Override
    public List<BlogEntity> getAllBlogs() {
        return blogRepository.findAll();
    }

    @Override
    public BlogEntity getBlogById(int id) throws ResourceNotFoundException {
        Optional<BlogEntity> result = blogRepository.findById(id);
        if (result.isPresent()) {
            return result.get();
        } else {
            throw new ResourceNotFoundException("Blog not exist");
        }

    }

    @Override
    public BlogEntity createBlog(BlogEntity blog) {
        return blogRepository.save(blog);
    }

    @Override
    public BlogEntity updateBlog(int id, BlogEntity blogRequest) throws ResourceNotFoundException {
        BlogEntity blog = blogRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Blog not exist"));

        blog.setTitle(blogRequest.getTitle());
        blog.setDes(blogRequest.getDes());
        blog.setDetail(blogRequest.getDetail());
        blog.setPosition(blogRequest.getPosition());
        blog.setIsPublic(blogRequest.getIsPublic());
        blog.setCategory(blogRequest.getCategory());
        blog.setDataPublic(blogRequest.getDataPublic());
        return blogRepository.save(blog);
    }

    @Override
    public void deleteBlog(int id) throws ResourceNotFoundException {
        BlogEntity blog = blogRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Blog Not exist"));

        blogRepository.delete(blog);
    }


}
