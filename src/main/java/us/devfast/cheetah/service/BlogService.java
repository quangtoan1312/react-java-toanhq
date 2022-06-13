package us.devfast.cheetah.service;

import us.devfast.cheetah.entity.BlogEntity;
import us.devfast.cheetah.entity.ResourceNotFoundException;

import java.util.List;

public interface BlogService {
    List<BlogEntity> getAllBlogs();

    BlogEntity getBlogById(int id) throws ResourceNotFoundException;

    BlogEntity createBlog(BlogEntity blog);

    BlogEntity updateBlog(int id, BlogEntity blog) throws ResourceNotFoundException;

    void deleteBlog(int id) throws ResourceNotFoundException;

}
