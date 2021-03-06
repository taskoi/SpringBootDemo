package com.webfactory.springbootdemo.demoproject.web;

import com.webfactory.springbootdemo.demoproject.exeptions.post.exceptions.PostNotFoundException;
import com.webfactory.springbootdemo.demoproject.exeptions.user.exceptions.UserNotFoundException;
import com.webfactory.springbootdemo.demoproject.model.Location;
import com.webfactory.springbootdemo.demoproject.model.Post;
import com.webfactory.springbootdemo.demoproject.model.User;
import com.webfactory.springbootdemo.demoproject.service.impl.PostServiceImpl;
import com.webfactory.springbootdemo.demoproject.service.impl.UserServiceImpl;
import io.swagger.annotations.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/search")
@Api(value = "demoproject")
public class SearchController {

    private final PostServiceImpl postServiceImpl;

    private final UserServiceImpl userServiceImpl;

    public SearchController(PostServiceImpl postServiceImpl, UserServiceImpl userServiceImpl) {
        this.postServiceImpl = postServiceImpl;
        this.userServiceImpl = userServiceImpl;
    }

    @ApiOperation(value = "Search users by nickname")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrived list"),
            @ApiResponse(code = 401, message = "You are not authorized to use this resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Bearer - Token", paramType = "header",required = true),
    })
    @GetMapping("/findUserByNickname/{nickname}")
    @PreAuthorize("#oauth2.hasScope('read')")
    public List<User> findByNickname(@PathVariable String nickname,@RequestParam("page")int pageIndex, @RequestParam("size") int pageSize) throws UserNotFoundException {
        return userServiceImpl.findByNickname(PageRequest.of(pageIndex,pageSize), nickname).getContent();
    }

    @ApiOperation(value = "Search users by location city")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrived list"),
            @ApiResponse(code = 401, message = "You are not authorized to use this resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Bearer - Token", paramType = "header",required = true),
    })
    @GetMapping("/findUserByLocation/{locationCity}")
    @PreAuthorize("#oauth2.hasScope('read')")
    public List<User> findByLocationCity(@PathVariable String locationCity,@RequestParam("page")int pageIndex,@RequestParam("size") int pageSize) throws UserNotFoundException {
        return userServiceImpl.findByLocationCity(PageRequest.of(pageIndex,pageSize), locationCity).getContent();
    }

    @ApiOperation(value = "Search posts by post title")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrived list"),
            @ApiResponse(code = 401, message = "You are not authorized to use this resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Bearer - Token", paramType = "header",required = true),
    })
    @GetMapping("/findPostByTitle/{postTitle}")
    @PreAuthorize("#oauth2.hasScope('read')")
    public List<Post> findByTitle(@PathVariable String postTitle, @RequestParam("page")int pageIndex,@RequestParam("size") int pageSize) throws PostNotFoundException {
        return postServiceImpl.findByTitle(postTitle, PageRequest.of(pageIndex,pageSize)).getContent();
    }

    @ApiOperation(value = "Search posts by location")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrived list"),
            @ApiResponse(code = 401, message = "You are not authorized to use this resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Bearer - Token", paramType = "header",required = true),
    })
    @PostMapping("/findPostByLocation")
    @PreAuthorize("#oauth2.hasScope('read')")
    public List<Post> findByLocation(@RequestBody Location location, @RequestParam("page")int pageIndex,@RequestParam("size") int pageSize) throws PostNotFoundException {
        return postServiceImpl.findByLocation(location, PageRequest.of(pageIndex,pageSize)).getContent();
    }
}
