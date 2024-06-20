/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.controllers;

import com.epm.dto.response.JoinActivityResponse;
import com.epm.dto.response.ResponseStruct;
import com.epm.mapper.JoinActivityMapper;
import com.epm.pojo.JoinActivity;
import com.epm.services.JoinActivityService;
import com.epm.services.RegisterService;
import com.epm.utils.StatusResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @PostMapping(path = "/join-activity/{joinActivityId}")
    public ResponseStruct<JoinActivityResponse> confirmJoin(@RequestParam Map<String, String> data, @RequestPart MultipartFile file, @PathVariable("joinActivityId") int joinActivityId) {
        JoinActivity joinActivity = this.registerService.getRegisterById(joinActivityId);
        int userId = Integer.parseInt(data.get("userId"));
        String note = data.get("note");
        joinActivity.setFile(file);
        joinActivity.setNote(note);
        joinActivity.setRollup(true);

        if (joinActivity.getUserId().getId() == userId) {
            return new ResponseStruct(StatusResponse.SUCCESS_RESPONSE, joinActivityMapper.toJoinActivityResponse(this.joinActivityService.update(joinActivity)));
        }

        return new ResponseStruct(StatusResponse.FAIL_RESPONSE, null);
    }
}
