/*
 * Copyright (c) 2019, ABB and/or its affiliates. All rights reserved.
 * ABB PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.geega.bsc.word.service.controller;

import com.geega.bsc.word.common.ik.IkUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.Set;

/**
 * TestController
 *
 * @author An Jun
 * @date 2021-04-21
 */
@RestController
@RequestMapping(value = "/api/v1/word")
public class SensitiveController {

    @GetMapping(value = "/check")
    public void checkComment(@RequestParam(value = "word") String word) {
        Set<String> analyse = IkUtils.analyse(word);
    }

}
