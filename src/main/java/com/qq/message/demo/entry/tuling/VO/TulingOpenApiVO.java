package com.qq.message.demo.entry.tuling.VO;

import java.util.List;

public class TulingOpenApiVO {
    Object emotion;

    Object userEmotion;

    public Object getEmotion() {
        return emotion;
    }

    public void setEmotion(Object emotion) {
        this.emotion = emotion;
    }

    public Object getUserEmotion() {
        return userEmotion;
    }

    public void setUserEmotion(Object userEmotion) {
        this.userEmotion = userEmotion;
    }

    Intent intent;

    List<Results> results;

    public Intent getIntent() {
        return intent;
    }

    public void setIntent(Intent intent) {
        this.intent = intent;
    }

    public List<Results> getResults() {
        return results;
    }

    public void setResults(List<Results> results) {
        this.results = results;
    }
}
