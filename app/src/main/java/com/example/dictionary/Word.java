package com.example.dictionary;

public class Word {
    String word;
    String phonetic;
    String phoneticsURL;
    String partOfSpeech;
    String definition;

    public Word(String word, String phonetic, String phoneticsURL, String partOfSpeech, String definition) {
        this.word = word;
        this.phonetic = phonetic;
        this.phoneticsURL = phoneticsURL;
        this.partOfSpeech = partOfSpeech;
        this.definition = definition;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getPhonetic() {
        return phonetic;
    }

    public void setPhonetic(String phonetic) {
        this.phonetic = phonetic;
    }

    public String getPhoneticsURL() {
        return phoneticsURL;
    }

    public void setPhoneticsURL(String phoneticsURL) {
        this.phoneticsURL = phoneticsURL;
    }

    public String getPartOfSpeech() {
        return partOfSpeech;
    }

    public void setPartOfSpeech(String partOfSpeech) {
        this.partOfSpeech = partOfSpeech;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }
}
