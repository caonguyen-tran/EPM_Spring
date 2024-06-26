/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.controllers;

import com.epm.dto.response.JoinActivityResponse;
import com.epm.dto.response.ResponseStruct;
import com.epm.mapper.JoinActivityMapper;
import com.epm.pojo.JoinActivity;
import com.epm.pojo.User;
import com.epm.services.JoinActivityService;
import com.epm.services.RegisterService;
import com.epm.services.UserService;
import com.epm.utils.StatusResponse;
import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author ACER
 */
@RestController
@RequestMapping("/api")
@CrossOrigin
public class ApiJoinActivityController {

    @Autowired
    private JoinActivityService joinActivityService;

    @Autowired
    private JoinActivityMapper joinActivityMapper;

    @Autowired
    private RegisterService registerService;

    @Autowired
    private UserService userService;

    @DeleteMapping("/join-activity/{joinId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void rejectJoin(@PathVariable(value = "joinId") int joinId) {
        JoinActivity j = new JoinActivity();
        j.setId(joinId);
        this.joinActivityService.deleteJoinActivity(j);
    }

    @GetMapping("/join-activity/{activityId}")
    @ResponseStatus(HttpStatus.OK)
    public List<JoinActivityResponse> getJoinActivityByActivityId(@PathVariable(value = "activityId") int activityId) {
        List<JoinActivity> lists = this.joinActivityService.getJoinActivityByActivityId(activityId);
        return lists.stream().map(joinActivity -> joinActivityMapper.toJoinActivityResponse(joinActivity)).collect(Collectors.toList());
    }

    @PostMapping(path = "/join-activity")
    public ResponseStruct<JoinActivityResponse> confirmJoin(@RequestParam Map<String, String> data, @RequestPart MultipartFile file, Principal principal) {
        int joinActivityId = Integer.parseInt(data.get("joinActivityId"));
        JoinActivity joinActivity = this.registerService.getRegisterById(joinActivityId);
        User u = this.userService.getUserByUsername(principal.getName());
       
        if (joinActivity.getUserId().getId() == u.getId()) {
            String note = data.get("note");
            joinActivity.setFile(file);
            joinActivity.setNote(note);
            joinActivity.setRollup(true);
            return new ResponseStruct(StatusResponse.SUCCESS_RESPONSE, joinActivityMapper.toJoinActivityResponse(this.joinActivityService.update(joinActivity)));
        }

        return new ResponseStruct(StatusResponse.FAIL_RESPONSE, null);
    }

    
    @GetMapping(path = "/join-activity/detail/{joinActivityId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object[]> getJoinActivity(@PathVariable("joinActivityId") int joinActivityId){
        Object[] joinActivity = this.joinActivityService.getAcByJAId(joinActivityId);
        if (joinActivity != null) {
            return new ResponseEntity<>(joinActivity, HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    
    @GetMapping(path = "/join-activity/activity/{activityId}/user/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JoinActivity> getJAByUserActivity(@PathVariable("userId") int userId, @PathVariable("activityId") int activityId){
        JoinActivity joinActivity = this.joinActivityService.findByUserAndActivity(userId, activityId);
        if (joinActivity != null) {
            return new ResponseEntity<>(joinActivity, HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
    
    @GetMapping("/join-activity/personal/{semesterId}")
    public ResponseStruct<List<JoinActivityResponse>> getJoinActivityByUser(@PathVariable(value="semesterId") int semesterId,Principal principal){
        User user = this.userService.getUserByUsername(principal.getName());
        List<JoinActivity> listJoinActivity = this.joinActivityService.getJoinActivityByUserAndSemester(user.getId(), semesterId, false);
        List<JoinActivityResponse> jar = listJoinActivity.stream().map(joinActivity -> joinActivityMapper.toJoinActivityResponse(joinActivity)).collect(Collectors.toList());
        return new ResponseStruct(StatusResponse.SUCCESS_RESPONSE, jar);
    }
}
