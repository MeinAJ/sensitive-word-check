/*
 * Copyright (c) 2019, ABB and/or its affiliates. All rights reserved.
 * ABB PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.geega.bsc.word.common.ik;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.wltea.analyzer.lucene.IKAnalyzer;
import java.io.StringReader;
import java.util.HashSet;
import java.util.Set;

/**
 * IkUtils
 *
 * @author Jun.An3
 * @date 2022/08/03
 */
public class IkUtils {

    private static Analyzer anal = new IKAnalyzer(true);

    public static Set<String> analyse(String comment) {
        TokenStream ts;
        StringReader reader;
        Set<String> wordSet = new HashSet<>();
        try {
            reader = new StringReader(comment);
            //分词
            ts = anal.tokenStream("", reader);
            ts.reset();
            CharTermAttribute term = ts.getAttribute(CharTermAttribute.class);
            //遍历分词数据
            while (ts.incrementToken()) {
                wordSet.add(term.toString());
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return wordSet;
    }


}
